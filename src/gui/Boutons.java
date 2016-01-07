package gui;

import commun.Algorithme;
import commun.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Boutons extends JPanel {
   
    private JComboBox algo;
    private JButton launch;
    private Fenetre fenetre;
    
    public Boutons(Fenetre fenetre){
        this.fenetre = fenetre;
        
        this.algo = new JComboBox();
        this.launch = new JButton("Démarrer");
        
        for(Algorithme a : Main.algorithmes){
            this.algo.addItem(a);
        }
        
        this.launch.addActionListener((ActionEvent e) -> {
            Boutons.this.fenetre.launch();
        });
        
        this.algo.addItemListener((ItemEvent e) -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                Boutons.this.fenetre.setAlgo((Algorithme) e.getItem());
            }
        });
        
        this.add(this.algo);
        this.add(this.launch);
    }
    
}
