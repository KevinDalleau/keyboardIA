package gui;

import commun.Algorithme;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class Donnees extends JPanel implements Observer{
    private final static int OFFSET = 15;
    private final static int TAB = 30;
    private HashMap<String, Number> donnees = null;
    public Donnees(){
        this.setPreferredSize(new Dimension(100, 100));
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(this.donnees != null){
            for(Entry<String, Number> e : this.donnees.entrySet()){
                int i = 1;
                g.drawString(e.getKey(), Donnees.OFFSET, Donnees.OFFSET * (i ++));
                g.drawString("" + e.getValue(), Donnees.OFFSET + Donnees.TAB, Donnees.OFFSET * (i ++));
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Algorithme a = (Algorithme) o;
        this.donnees = a.getDonnees();
        this.repaint();
    }
}
