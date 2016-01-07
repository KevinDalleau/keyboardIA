package recuit;

import commun.Algorithme;
import commun.Helpers;
import donnees.Bigramme;
import donnees.Keyboard;

public class RecuitSimule extends Algorithme{
	private double Tmax;
	private double Emax;
	private double finalCost;
	private Bigramme Bigramme;
	
	public RecuitSimule(Bigramme bigramme){
		this.Tmax = 1000;
		this.Emax = 100;
		this.Bigramme = bigramme;
	}

	public RecuitSimule(double tmax,double emax){
		this.Tmax = tmax;
		this.Emax = emax;
	}

	public Keyboard Compute(){
		Keyboard firstSol = Keyboard.GenerateFirstSol();
                Keyboard bestSol = new Keyboard();
		bestSol.copy(firstSol);
		int energy = (int) firstSol.getCost(Bigramme);
		int bestEnergy = energy;
		int t = 0;
		while(t<Tmax && energy>Emax ){
			double T = t/Tmax;
			Keyboard newkey = this.generateNeighbor(firstSol);
			//newEnergy
			int newEnergy = (int) newkey.getCost(Bigramme);
			if(this.acceptanceProbability(energy, newEnergy, T)>Math.random()){
				firstSol.copy(newkey);
				energy = newEnergy;
			}
			if(newEnergy<bestEnergy){
				bestSol.copy(newkey);
				bestEnergy = newEnergy;
			}
			t++;
                        this.updateResultat(bestSol, bestEnergy);
		}
		this.setDonnee("Coût final", bestEnergy);
		return this.resultat;
	}

	public Keyboard generateNeighbor(Keyboard key){
		int array[] = new int[40];
		for(int i=0;i<40;i++){
			array[i]=i;
		}
		boolean done = false;
		Keyboard keyb = new Keyboard();
		keyb.copy(key);
		int[] rand ;
		do{
			rand = Helpers.pickNRandom(array, 2);
			done = keyb.swap(rand[0], rand[1]);
		}while(!done);
		return keyb;
	}

	public double acceptanceProbability(int energy, int newEnergy, double temperature) {
		if (newEnergy < energy) {
			return 1.0;
		}
		return Math.exp((energy - newEnergy) / temperature);
	}

	@Override
	public void configure() {
		this.parametres.put("Temperature",1000.0);
		this.parametres.put("Energy",0.0);
		this.donnees.put("Duree", 0);
                this.donnees.put("Coût final", 0);
	}

	@Override
	protected void launch() {
            this.setTmax((double)this.getParametre("Temperature"));
            this.setEmax((double)this.getParametre("Energy"));
            double temps = System.currentTimeMillis();
            resultat = this.Compute();
	    temps = System.currentTimeMillis() - temps;
	     
	     this.setDonnee("Duree", temps);
	}

	public double getTmax() {
		return Tmax;
	}

	public void setTmax(double tmax) {
		Tmax = tmax;
	}

	public double getEmax() {
		return Emax;
	}

	public void setEmax(double emax) {
		this.Emax = emax;
	}

    @Override
    public String getNom() {
        return "Recuit simulé";
    }
	
	
}	
