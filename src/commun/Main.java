package commun;


import gui.Fenetre;

import java.util.ArrayList;
import java.util.List;

import donnees.Bigramme;
import random.AlgoRandom;
import recuit.RecuitSimule;


public class Main {
    public final static List<Algorithme> algorithmes = new ArrayList<>();
    public static void main(String[] args){
    	Bigramme bigramme = new Bigramme();
        Main.algorithmes.add(new AlgoRandom(bigramme));
        Main.algorithmes.add(new RecuitSimule(bigramme));
        Fenetre f = new Fenetre();
    }    
}
