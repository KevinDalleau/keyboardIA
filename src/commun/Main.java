package commun;


import gui.Fenetre;

import java.util.ArrayList;
import java.util.List;

import random.AlgoRandom;
import recuit.RecuitSimule;


public class Main {
    public final static List<Algorithme> algorithmes = new ArrayList<>();
    public static void main(String[] args){
        Main.algorithmes.add(new AlgoRandom());
        Main.algorithmes.add(new RecuitSimule());
        Fenetre f = new Fenetre();
    }    
}
