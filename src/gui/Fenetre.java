package gui;

import commun.Algorithme;
import commun.Main;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
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
        rightPanel.setLayout(new GridBagLayout());
        DefaultXYDataset ds = new DefaultXYDataset();
        double[][] data = { {100.0, 30, 0.3}, {1, 200, 300 }};
        ds.addSeries("series1", data);
        JFreeChart j = ChartFactory.createXYLineChart("Test Chart",
                "x", "y", ds, PlotOrientation.VERTICAL, true, true,
                false);
        this.graphique = new ChartPanel(j);
                
        this.setLayout(new BorderLayout());
        
        this.add(this.clavier, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
        rightPanel.add(this.graphique);
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
