package commun;

import donnees.Keyboard;
import java.util.HashMap;
import java.util.Observable;

public abstract class Algorithme extends Observable{
    protected Keyboard resultat;
    protected HashMap<String,Number> parametres;
    protected HashMap<String, Number> donnees;
    public Algorithme(){
 
        this.parametres = new HashMap<>();
        this.donnees = new HashMap<>();
        
        this.configure();
    }
    public abstract void configure();
    public Number getParametre(String s){
        return this.parametres.get(s);
    }
    public void setParametre(String s, Number n){
        this.parametres.put(s, n);
    }
    public void setDonnee(String s, Number n){
        this.donnees.put(s, n);
    }
    protected abstract void launch();
    
    public void resoudre(){
        this.launch();
        this.setChanged();
        this.notifyObservers();
    }
}
