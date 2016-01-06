package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Fenetre extends JFrame {
    private Clavier clavier;
    private Donnees donnees;
    private Parametres parametres;
    
    public Fenetre(){
        super("Disposition Clavier DKN");
        
        this.clavier = new Clavier();
        this.donnees = new Donnees();
        this.parametres = new Parametres();
        
        this.setLayout(new BorderLayout());
        
        this.add(this.clavier, BorderLayout.CENTER);
        this.add(this.donnees, BorderLayout.EAST);
        this.add(this.parametres, BorderLayout.NORTH);
               
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
