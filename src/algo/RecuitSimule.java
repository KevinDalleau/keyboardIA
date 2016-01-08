package algo;

import commun.Helpers;
import donnees.Keyboard;

public class RecuitSimule extends Algorithme{
	private double Tmax;
	private double Emax;
	private double finalCost;
	private double Alpha;
	
	public RecuitSimule(){
		this.Tmax = 1000;
		this.Emax = 100;
		this.Alpha = 0.9;
	}

	public RecuitSimule(double tmax,double emax, double alpha){
		this.Tmax = tmax;
		this.Emax = emax;
		this.Alpha = alpha;
	}

	public void Compute(){
		Keyboard firstSol = Keyboard.GenerateFirstSol();
                Keyboard bestSol = new Keyboard();
		bestSol.copy(firstSol);
		int energy = (int) firstSol.getCost();
		int bestEnergy = energy;
		while(1<Tmax && energy>Emax ){
			Keyboard newkey = this.generateNeighbor(firstSol);
			//newEnergy
			int newEnergy = (int) newkey.getCost();
			if(this.acceptanceProbability(energy, newEnergy, Tmax)>Math.random()){
				firstSol.copy(newkey);
				energy = newEnergy;
			}
			if(newEnergy<bestEnergy){
				bestSol.copy(newkey);
				bestEnergy = newEnergy;
			}
			this.Tmax *= this.Alpha;
         this.updateResultat(bestSol);
		}
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
                super.configure();
		this.parametres.put("Temperature",1000.0);
		this.parametres.put("Energy",0.0);
		this.parametres.put("Decay", 0.9);
	}

	@Override
	protected void launch() {
            this.setTmax((double)this.getParametre("Temperature"));
            this.setEmax((double)this.getParametre("Energy"));
            this.setAlpha((double)this.getParametre("Decay"));
            this.Compute();
	}

	public double getAlpha() {
		return Alpha;
	}

	public void setAlpha(double alpha) {
		Alpha = alpha;
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
