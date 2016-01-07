package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Parametres extends JPanel {
    private HashMap<String, Number> parametres = null;
    public void setParametres(HashMap<String, Number> parametres){
        this.removeAll();
        this.parametres = parametres;
        for(Entry<String, Number> e : this.parametres.entrySet()){
            Component add = this.add(new JLabel(e.getKey()));
            JFormattedTextField j = new JFormattedTextField();
            j.setValue(e.getValue());
            j.setPreferredSize(new Dimension(50,20));
            j.addPropertyChangeListener("value", (PropertyChangeEvent evt) -> {
                this.parametres.put(e.getKey(), (Number) evt.getNewValue());
            });
            this.add(j);
        }
        
    }
}
