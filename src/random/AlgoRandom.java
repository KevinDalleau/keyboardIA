package random;

import commun.Algorithme;
import donnees.Keyboard;
import java.util.List;
import java.util.Observer;

public class AlgoRandom extends Algorithme{
    public AlgoRandom(List<Observer> l){
        super(l);
    }
    private List<Keyboard> kl;

    @Override
    protected void launch() {
        //on recupere les parametres renseignes dans l'interface graphique
        int taille = (int) this.getParametre("Taille population");
       
        //on applique l'algo
        double temps = System.currentTimeMillis();
        for(int i = 0; i < taille; i ++){
            kl.add(Keyboard.GenerateFirstSol());
        }
        temps = System.currentTimeMillis() - temps;
        
        //on actualise les donnees a afficher
        this.setParametre("Duree", temps);
        
        //on met a jour le meilleur individu (a afficher)
        double coutMinimal = kl.get(0).getCost();
        this.resultat = kl.get(0);
        for(int i = 1; i < kl.size(); i ++){
            double cout = kl.get(i).getCost();
            if(cout < coutMinimal){
                coutMinimal = cout;
                this.resultat = kl.get(1);
            }
        }
    }

    @Override
    public void configure() {
        //on met les parametres, les donnees et leurs valeurs par defaut
        this.parametres.put("Taille population", 10);
        this.donnees.put("Duree", 0);
    }

}
