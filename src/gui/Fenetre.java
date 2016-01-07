package gui;

import commun.Algorithme;
import commun.Main;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Fenetre extends JFrame {
    private Algorithme algo;
    
    private Clavier clavier;
    private Donnees donnees;
    private Parametres parametres;
    private Boutons boutons;
    
    public Fenetre(){
        super("Disposition Clavier DKN");
                
        this.clavier = new Clavier();
        this.donnees = new Donnees();
        this.parametres = new Parametres();
        this.boutons = new Boutons(this);
                
        this.setLayout(new BorderLayout());
        
        this.add(this.clavier, BorderLayout.CENTER);
        this.add(this.donnees, BorderLayout.EAST);
        this.add(this.parametres, BorderLayout.NORTH);
        this.add(this.boutons, BorderLayout.SOUTH);
        
        this.setAlgo(0);
               
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void setAlgo(int i){
        this.algo = Main.algorithmes.get(0);
        //TODO UPDATE
        this.algo.addObserver(this.clavier);
    };
    
    public void launch(){
        this.algo.resoudre();
    }
}
