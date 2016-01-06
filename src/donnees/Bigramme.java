package donnees;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Bigramme {
    private final static String donnees = "freq.txt";
    private HashMap<CoupleEntiers, Integer> frequences;
    public Bigramme(){
        frequences = new HashMap<>();
        try (
            FileReader fr = new FileReader(donnees);
            Scanner sc = new Scanner (fr);
        ){
            int i = 0;
            while(sc.hasNext()){
                if(sc.hasNextInt()){
                    int frequence = sc.nextInt();
                    frequences.put(new CoupleEntiers(i/26, i%26), frequence); 
                    i ++;
                } else {
                    sc.next();
                }
            }
            if(i != 26 * 26){
                throw new Error("Fichier d'entrée incorrect" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    protected int frequence(int i, int j){
        return this.frequences.get(new CoupleEntiers(i,j));
    }
}
