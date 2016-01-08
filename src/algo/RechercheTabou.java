package algo;

import java.util.ArrayList;
import java.util.LinkedList;

import commun.Helpers;
import donnees.Keyboard;

public class RechercheTabou extends Algorithme {
	private LinkedList<Keyboard> tabuList; //Liste de claviers déjà visités
	private int numberOfLoops;
	private int sizeTabuList;
	private int sizeNeighborhood;
	private Keyboard finalSolution;
        private int tabousExclus;
	
	public ArrayList<Keyboard> generateNeighbor(Keyboard key){
		ArrayList<Keyboard> neighborhood = new ArrayList<Keyboard>();
		int array[] = new int[40];
		for(int i=0;i<40;i++){
			array[i]=i;
		}
		boolean done = false;
		int count = 0;
		
		int[] rand ;
		do{
			Keyboard keyb = new Keyboard();
			keyb.copy(key);
			rand = Helpers.pickNRandom(array, 2);
			done = keyb.swap(rand[0], rand[1]);
			if(done && !neighborhood.contains(keyb)) {
				neighborhood.add(keyb);
				count++;
			}
		}while(count < this.sizeNeighborhood);
		return neighborhood;
	}
	
	public Keyboard compute() {
		int iteration = 0;
		Keyboard s = Keyboard.GenerateFirstSol();
		Keyboard sBest = new Keyboard();
		sBest.copy(s);
		while(iteration < this.numberOfLoops) { 
			Keyboard bestCandidate = new Keyboard();
			for(Keyboard k : this.generateNeighbor(s)) {
				double bestCandidateCost;
				if(bestCandidate.getCost() == 0) {
					bestCandidateCost = 1000000000; 
				}
				else {
					bestCandidateCost = bestCandidate.getCost();
				}		
				if(k.getCost() < bestCandidateCost){
                                    if(!tabuList.contains(k)) {
                                        bestCandidate.copy(k);
                                    } else {
                                        this.tabousExclus ++;
                                    }
				}
			}
			s.copy(bestCandidate);
			if(bestCandidate.getCost() < sBest.getCost()) {
                                sBest = new Keyboard();
				sBest.copy(bestCandidate);
			}
                        if(!tabuList.contains(sBest))
                            tabuList.add(sBest);
			if(tabuList.size()>sizeTabuList) {
				tabuList.removeFirst();
			}
			this.updateResultat(sBest);
			iteration++;
		}
		return sBest;
	}

	public LinkedList<Keyboard> getTabuList() {
		return tabuList;
	}

	public void setTabuList(LinkedList<Keyboard> tabuList) {
		this.tabuList = tabuList;
	}

	public double getSizeTabuList() {
		return sizeTabuList;
	}

	public void setSizeTabuList(int d) {
		this.sizeTabuList = d;
	}

	public Keyboard getFinalSolution() {
		return finalSolution;
	}

	public void setFinalSolution(Keyboard finalSolution) {
		this.finalSolution = finalSolution;
	}

	@Override
	public void configure() {
                super.configure();
		this.parametres.put("Itérations",1000);
		this.parametres.put("Taille liste taboue",40);		
		this.parametres.put("Taille Voisinage", 3);
                this.donnees.put("Exclusions", 0);
                this.donnees.put("Taille finale liste taboue", 0);
	}

	@Override
	protected void launch() {
            this.tabuList = new LinkedList<Keyboard>();
            this.tabousExclus = 0;
            this.setSizeTabuList((int)this.getParametre("Taille liste taboue"));
            this.setNumberOfLoops((int)this.getParametre("Itérations"));
            this.sizeNeighborhood = (int) this.getParametre("Taille Voisinage");
            this.compute();
            this.setDonnee("Exclusions", this.tabousExclus);
            this.setDonnee("Taille finale liste taboue", this.tabuList.size());
    	}

	@Override
	public String getNom() {
		
		return "Recherche taboue";
	}

	public double getNumberOfLoops() {
		return numberOfLoops;
	}

	public void setNumberOfLoops(int d) {
		this.numberOfLoops = d;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((finalSolution == null) ? 0 : finalSolution.hashCode());
		long temp;
		temp = Double.doubleToLongBits(numberOfLoops);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(sizeTabuList);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((tabuList == null) ? 0 : tabuList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj.toString() == this.toString()) {
			return true;
		}
		else {
			return false;
		}
	}

	
}
