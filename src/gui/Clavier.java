package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Clavier extends JPanel{
    private final static int coteTouche = 40;
    public Clavier(){
        super();
        this.setPreferredSize(new Dimension(12 * coteTouche, 6 * coteTouche));
        this.repaint();
        this.validate();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < 5; i ++){
           g.drawLine(coteTouche, coteTouche + i*coteTouche, coteTouche * 11, coteTouche + i*coteTouche); 
        }
        for(int i = 0; i < 11; i ++){
           g.drawLine(coteTouche + i*coteTouche, coteTouche, coteTouche + i*coteTouche, coteTouche*5); 
        }
    }
}
