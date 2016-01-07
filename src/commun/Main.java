package commun;


import algo.Algorithme;
import gui.Fenetre;

import java.util.ArrayList;
import java.util.List;

import donnees.Bigramme;
import algo.AlgoRandom;
import algo.RechercheTabou;
import algo.RecuitSimule;


public class Main {
    public final static List<Algorithme> algorithmes = new ArrayList<>();
    public static Bigramme bigramme;
    public static void main(String[] args){
    	Main.bigramme = new Bigramme();
        Main.algorithmes.add(new AlgoRandom());
        Main.algorithmes.add(new RecuitSimule());
        Main.algorithmes.add(new RechercheTabou());
        Fenetre f = new Fenetre();
    }    
}
