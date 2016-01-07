package gui;

import algo.Algorithme;
import commun.Main;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

public class Fenetre extends JFrame {
    private Algorithme algo;
    
    private Clavier clavier;
    private Donnees donnees;
    private Parametres parametres;
    private Boutons boutons;
    private ChartPanel graphique;
    
    public Fenetre(){
        super("Disposition Clavier DKN");
                
        this.clavier = new Clavier();
        this.donnees = new Donnees();
        this.parametres = new Parametres();
        this.boutons = new Boutons(this);
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1));
        DefaultXYDataset ds = new DefaultXYDataset();
        double[][] data = { {0}, {0}};
        ds.addSeries("Coût", data);
        JFreeChart j = ChartFactory.createXYLineChart("Évolution du coût",
                "Itérations", "Distance cumulée", ds, PlotOrientation.VERTICAL, true, true,
                false);
        this.graphique = new ChartPanel(j);
                
        this.setLayout(new BorderLayout());
        
        this.add(this.graphique, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
        rightPanel.add(this.clavier);
        rightPanel.add(this.donnees);
        
        this.add(this.parametres, BorderLayout.NORTH);
        this.add(this.boutons, BorderLayout.SOUTH);
        
        this.setAlgo(Main.algorithmes.get(0));
               
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void setAlgo(Algorithme algo){
        this.algo = algo;
        this.parametres.setParametres(algo.getParametres());
        this.algo.addObserver(this.clavier);
        this.algo.addObserver(this.donnees);
        this.algo.update();
        this.repaint();
        this.pack();
    };
    
    public void launch(){
        this.algo.resoudre();
    }
}
