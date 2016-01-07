package gui;

import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Parametres extends JPanel {
    private HashMap<String, Number> parametres = null;
    public void setParametres(HashMap<String, Number> parametres){
        this.removeAll();
        this.parametres = parametres;
        for(Entry<String, Number> e : this.parametres.entrySet()){
            this.add(new JLabel(e.getKey()));
            this.add(new JLabel("" +e.getValue()));
        }
        
    }
}
