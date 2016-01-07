package commun;


import gui.Fenetre;
import java.util.ArrayList;
import java.util.List;
import random.AlgoRandom;


public class Main {
    public final static List<Algorithme> algorithmes = new ArrayList<>();
    public static void main(String[] args){
        Main.algorithmes.add(new AlgoRandom());
        Fenetre f = new Fenetre();
    }    
}
