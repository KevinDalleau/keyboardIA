package algo;

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
        if(!this.parametres.containsKey(s)){
            throw new Error("Paramètre inexistant");
        }
        return this.parametres.get(s);
    }
    public void setDonnee(String s, Number n){
        if(!this.donnees.containsKey(s)){
            throw new Error("Donnée inexistante");
        }
        this.donnees.put(s, n);
        this.setChanged();
        this.notifyObservers();
    }
    protected abstract void launch();
    
    public void resoudre(){
        this.launch();
        this.setChanged();
        this.notifyObservers();
    }
    
    public String toString(){
        return this.getNom();
    }
    
    public abstract String getNom();
    
    public Keyboard getResultat(){
        return this.resultat;
    }
    
    public HashMap<String,Number> getParametres(){
        return this.parametres;
    }
    
    public HashMap<String,Number> getDonnees(){
        return this.donnees;
    }
    
    public void update(){
        this.setChanged();
        this.notifyObservers();
    }
    
    public void updateResultat(Keyboard k, int coutMinimal){
        System.out.println(coutMinimal);
    }
}
