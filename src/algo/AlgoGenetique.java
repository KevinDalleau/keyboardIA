package algo;

import donnees.Keyboard;
import java.util.ArrayList;
import java.util.Arrays;

public class AlgoGenetique extends Algorithme{

    @Override
    protected void launch() {
        int iterations = (int) this.getParametre("Itérations");
        int population = (int) this.getParametre("Population");
        int tournoi = (int) this.getParametre("Tournoi");
        int nbCouples = (int) this.getParametre("Couples (> population / 2)");
        if(nbCouples < population / 2) nbCouples = population /2;
        double pMutation  = (double) this.getParametre("P mutation");
        double pCroisement = (double)this.getParametre("P croisement");
        int nbCroisement = 0;
        int nbMutation = 0;
        
        ArrayList<Keyboard> individus = new ArrayList<>();
        
        for(int i = 0; i < population; i ++){
            individus.add(Keyboard.GenerateFirstSol());
        }
        
        this.updateResultat(individus.get(0));
                
        for(int i = 0; i < iterations; i ++){
            ArrayList<Keyboard> nouvellePopulation = new ArrayList<>();
            
            for(int j = 0; j < nbCouples; j++){
                Keyboard[] selection = new Keyboard[tournoi];
                Keyboard[] selection2 = new Keyboard[tournoi];
                for(int k = 0; k < tournoi; k ++){
                    selection[k] = individus.get((int)(Math.random() * individus.size()));
                    selection2[k] = individus.get((int)(Math.random() * individus.size()));
                }
                Arrays.sort(selection);
                Arrays.sort(selection2);
                Keyboard pere = new Keyboard();
                Keyboard mere = new Keyboard();
                pere.copy(selection[0]);
                mere.copy(selection2[0]);
                
                if(Math.random() < pCroisement){
                    Keyboard tmp = pere;
                    pere = Keyboard.croisement(pere, mere);
                    mere = Keyboard.croisement(mere, tmp);
                    nbCroisement ++;
                }
                
                nouvellePopulation.add(pere);
                nouvellePopulation.add(mere);
            }
            
            for(int j = 0; j < nouvellePopulation.size(); j ++){
                if(Math.random() < pMutation){
                    ArrayList<Keyboard> mutations = nouvellePopulation.get(j).getNeighborhood();
                    nouvellePopulation.set(j, mutations.get((int)(Math.random() * mutations.size())));
                    nbMutation ++;
                }
            }
            
            Keyboard[] aTrier = nouvellePopulation.toArray(new Keyboard[nouvellePopulation.size()]);
            Arrays.sort(aTrier);
            
            for(int j = 0; j < population; j ++){
                individus.add(aTrier[j]);
            }
            
            if(this.getResultat().getCost() > aTrier[0].getCost()){
                this.updateResultat(aTrier[0]);
            } else {
                this.updateResultat(this.getResultat());
            }
           
        }
        
        this.setDonnee("Nombre de croisements", nbCroisement);
        this.setDonnee("Nombre de mutations", nbMutation);
    }

    public void configure(){
        super.configure();
        this.parametres.put("Itérations", 1000);
        this.parametres.put("Tournoi", 3);
        this.parametres.put("Population", 10);
        this.parametres.put("Couples (> population / 2)", 7);
        this.parametres.put("P mutation", 0.5);
        this.parametres.put("P croisement", 0.5);
        this.donnees.put("Nombre de croisements", 0);
        this.donnees.put("Nombre de mutations", 0);
    }
    
    @Override
    public String getNom() {
        return "Algorithme génétique";
    }
}
