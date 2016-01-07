package recuit;

import java.util.List;
import java.util.Observer;

import commun.Algorithme;
import commun.Helpers;
import donnees.Keyboard;

public class RecuitSimule extends Algorithme{
	private double Tmax;
	private double emax;
	public RecuitSimule(){
		this.Tmax = 1000;
		this.emax = 100;
	}

	public RecuitSimule(double tmax, double tmin, double alpha,double nbt,double nbi){
		this.Tmax = tmax;
	}

	public Keyboard Compute(){
		Keyboard firstSol = Keyboard.GenerateFirstSol();
		Keyboard bestSol = new Keyboard();
		bestSol.copy(firstSol);
		int energy = (int) firstSol.getCost();
		int bestEnergy = energy;
		int t = 0;
		while(t<Tmax && energy>emax ){
			double T = t/Tmax;
			Keyboard newkey = this.generateNeighbor(firstSol);
			//newEnergy
			int newEnergy = (int) newkey.getCost();
			if(this.acceptanceProbability(energy, newEnergy, T)>Math.random()){
				firstSol.copy(newkey);
				energy = newEnergy;
			}
			if(newEnergy<bestEnergy){
				bestSol.copy(newkey);
				bestEnergy = newEnergy;
			}
			t++;
			
		}
		System.out.println(bestEnergy);
		return bestSol;
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
		// TODO Auto-generated method stub
		this.parametres.put("Temperature",100);
		this.parametres.put("Energy",500000);
		this.donnees.put("Duree", 0);
	}

	@Override
	protected void launch() {
		// TODO Auto-generated method stub
		this.setTmax((double)this.getParametre("Temperature"));
		this.setEmax((double)this.getParametre("Energy"));
		 double temps = System.currentTimeMillis();
		 resultat = this.Compute();
	     temps = System.currentTimeMillis() - temps;
	     
	     this.setParametre("Duree", temps);
	}

	public double getTmax() {
		return Tmax;
	}

	public void setTmax(double tmax) {
		Tmax = tmax;
	}

	public double getEmax() {
		return emax;
	}

	public void setEmax(double emax) {
		this.emax = emax;
	}

    @Override
    public String getNom() {
        return "Recuit simulé";
    }
	
	
}	
