package gui;

import algo.Algorithme;
import donnees.Keyboard;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import static java.time.temporal.TemporalQueries.offset;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class Clavier extends JPanel implements Observer {
    private final static int coteTouche = 40;
    private Keyboard keyboard = null;
    
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
        if(this.keyboard != null){
            Font f = new Font(Font.SERIF, Font.PLAIN, coteTouche/2);
            FontRenderContext frc = new FontRenderContext(null, true, true);
            g.setFont(f);
            for(int i = 0; i < 4; i ++){
                for(int j = 0; j < 10; j ++){
                    String toDraw = "" + this.keyboard.getCharAt(i*10+j);
                    Rectangle2D r = f.getStringBounds(toDraw, frc);
                    g.drawString(toDraw, (j+1) * coteTouche + (int) ((coteTouche - r.getWidth())/2), (i+2) * coteTouche - (int) ((coteTouche - r.getHeight())));
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Algorithme alg = (Algorithme) o;
        this.keyboard = alg.getResultat();
        this.repaint();
    }
}
