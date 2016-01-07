package algo;

import donnees.Keyboard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public abstract class Algorithme extends Observable{
    private Keyboard resultat;
    protected HashMap<String,Number> parametres;
    protected HashMap<String, Number> donnees;
    private ArrayList<Double> couts;
    
    public Algorithme(){
        this.parametres = new HashMap<>();
        this.donnees = new HashMap<>();
        this.configure();
    }
    
    public void configure(){
        this.donnees.put("Temps de calcul (ms)", 0);
        this.donnees.put("Meilleur coût obtenu", 0.0);
    }
    
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
        this.couts = new ArrayList<>();
        double time = System.currentTimeMillis();
        this.launch();
        time = System.currentTimeMillis() - time;
        this.setDonnee("Temps de calcul (ms)", time);
        this.setDonnee("Meilleur coût obtenu", this.resultat.getCost());
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
    
    public void updateResultat(Keyboard k){
        this.resultat = k;
        this.couts.add(k.getCost());
    }
    
    public List<Double> getCouts(){
        return this.couts;
    }
}
