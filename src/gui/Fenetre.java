package gui;

import algo.Algorithme;
import commun.Main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

public class Fenetre extends JFrame implements Observer{
    private Algorithme algo;
    
    private Clavier clavier;
    private Donnees donnees;
    private Parametres parametres;
    private Boutons boutons;
    private ChartPanel graphique;
    private DefaultXYDataset ds;
    
    public Fenetre(){
        super("Disposition Clavier DKN");
                
        this.clavier = new Clavier();
        this.donnees = new Donnees();
        this.parametres = new Parametres();
        this.boutons = new Boutons(this);
        JPanel rightPanel = new JPanel();
        JPanel resultsPanel = new JPanel();
        
        rightPanel.setLayout(new GridLayout(2, 1));
        this.ds = new DefaultXYDataset();
        JFreeChart j = ChartFactory.createXYLineChart("Comparaison de métaheuristiques",
                "Itérations", "Distance moyenne", ds, PlotOrientation.VERTICAL, true, true,
                false);
        this.graphique = new ChartPanel(j);
        this.graphique.setPreferredSize(new Dimension(400,400));

        this.setLayout(new GridLayout(1,2));
        this.add(this.graphique);
        this.add(rightPanel);
                
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(resultsPanel, BorderLayout.CENTER);
            resultsPanel.setLayout(new GridLayout(2,1));
            resultsPanel.add(this.clavier);
            resultsPanel.add(this.donnees);
        rightPanel.add(this.parametres, BorderLayout.NORTH);
        rightPanel.add(this.boutons, BorderLayout.SOUTH);
        
        this.setAlgo(Main.algorithmes.get(0));
               
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void setAlgo(Algorithme algo){
        this.algo = algo;
        this.parametres.setParametres(algo.getParametres());
        this.algo.addObserver(this);
        this.algo.addObserver(this.clavier);
        this.algo.addObserver(this.donnees);
        this.algo.update();
        this.repaint();
        this.pack();
    };
    
    public void launch(){
        this.algo.resoudre();
    }

    @Override
    public void update(Observable o, Object arg) {
        Algorithme a = (Algorithme) o;
        if(a.getCouts() != null){
            double[][] data = new double[2][a.getCouts().size()];
            for(int i = 0; i < a.getCouts().size(); i ++){
                data[0][i] = i;
                data[1][i] = a.getCouts().get(i);
            }
            this.ds.addSeries(a.getNom(), data);
        }
    }
}
