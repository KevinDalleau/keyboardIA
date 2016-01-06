package donnees;

import java.util.HashMap;
import java.util.Random;

public class Bigramme {
    private HashMap<CoupleEntiers, Integer> frequences;
    public Bigramme(String file){
        
    }
    protected int frequence(int i, int j){
        //TODO replace this
        Random r = new Random(System.currentTimeMillis());
        return r.nextInt(100);
    }
}
