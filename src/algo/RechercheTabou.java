package algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import commun.Helpers;
import donnees.Keyboard;

public class RechercheTabou extends Algorithme {
	private LinkedList<Keyboard> tabuList; //Liste de claviers déjà visités
	private int numberOfLoops;
	private int sizeTabuList;
	private int sizeNeighborhood;
	private Keyboard finalSolution;
	
	public RechercheTabou() {
		this.tabuList = new LinkedList<Keyboard>();
	}
	
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
		}while(count != 25);
		return neighborhood;
	}
	
	public Keyboard compute() {
		int iteration = 0;
		Keyboard s = GenerateFirstSol();
		Keyboard sBest = new Keyboard();
		sBest.copy(s);
		LinkedList<Keyboard> tabuList = new LinkedList<Keyboard>();
		while(iteration < this.numberOfLoops) { 
			Keyboard bestCandidate = new Keyboard();
			s.display();
			for(Keyboard k : this.generateNeighbor(s)) {
				double bestCandidateCost;
				if(bestCandidate.getCost() == 0) {
					bestCandidateCost = 1000000000; 
				}
				else {
					bestCandidateCost = bestCandidate.getCost();
				}
				
				if(k.getCost() < bestCandidateCost && !tabuList.contains(k)) {
					bestCandidate.copy(k);
				}
			}
			s.copy(bestCandidate);
			if(bestCandidate.getCost() < sBest.getCost()) {
				sBest.copy(bestCandidate);
			}
			tabuList.add(bestCandidate);
			if(tabuList.size()>sizeTabuList) {
				tabuList.removeFirst();
			}
			System.out.println(sBest.getCost());
			this.updateResultat(sBest);
			iteration++;
		}
		System.out.println(tabuList.size());
		return sBest;
	}
	
	public Keyboard GenerateFirstSol(){
		Keyboard initialSolution = new Keyboard();
		char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		int array[] = new int[40];
		for(int i=0;i<40;i++){
			array[i]=i;
		}
		int[] picks = this.pickNRandom(array, 26);
		for(int i=0;i<26;i++){
			initialSolution.setCharAt(alphabet[i], picks[i]);
		}
		return initialSolution;
	}
	
	public int[] pickNRandom(int[] array, int n) {

		List<Integer> list = new ArrayList<Integer>(array.length);
		for (int i : array){
			list.add(i);
		}
		Collections.shuffle(list);

		int[] answer = new int[n];
		for (int i = 0; i < n; i++){
			answer[i] = list.get(i);
		}
		return answer;

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
		this.parametres.put("Iterations",1000);
		this.parametres.put("Taille_liste_taboue",40);		
		this.parametres.put("Taille_Voisinage", 10);
	}

	@Override
	protected void launch() {
            this.setSizeTabuList((int)this.getParametre("Taille_liste_taboue"));
            this.setNumberOfLoops((int)this.getParametre("Iterations"));
            this.sizeNeighborhood = (int) this.getParametre("Taille_Voisinage");
            this.compute();
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
