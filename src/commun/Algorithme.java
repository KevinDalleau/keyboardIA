package commun;

import donnees.Keyboard;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class Algorithme extends Observable{
    private Keyboard resultat;
    private HashMap<String,Number> parametres;
    private HashMap<String, Number> donnees;
    public Algorithme(List<Observer> l, HashMap<String,Number> parametres, HashMap<String, Number> donnees){
        for(Observer o : l){
            this.addObserver(o);
        }
        this.parametres = parametres;
        this.donnees = donnees;
        this.setChanged();
        this.notifyObservers();
    }
    public Number getParametre(String s){
        return this.parametres.get(s);
    }
    public void setParametre(String s, Number n){
        this.parametres.put(s, n);
    }
    public void setDonnee(String s, Number n){
        this.donnees.put(s, n);
    }
    protected abstract Keyboard updateMeilleurResultat();
    protected abstract void launch();
    
    public void resoudre(){
        this.launch();
        this.updateMeilleurResultat();
        this.setChanged();
        this.notifyObservers();
    }
}
