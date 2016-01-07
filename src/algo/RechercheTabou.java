package algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import donnees.Keyboard;

public class RechercheTabou extends Algorithme {
	private LinkedList<Keyboard> tabuList; //Liste de claviers déjà visités
	private double numberOfLoops;
	private double sizeTabuList;
	private Keyboard finalSolution;
	
	public RechercheTabou() {
		this.tabuList = new LinkedList<Keyboard>();
		this.numberOfLoops = 10000;
		this.sizeTabuList = 4000;
	}
	
	public Keyboard compute() {
		int iteration = 0;
		Keyboard s0 = GenerateFirstSol();
		Keyboard s = s0;
		Keyboard sBest = s;
		double bestCost = s.getCost();
		LinkedList<Keyboard> tabuList = new LinkedList<Keyboard>();
		while(iteration < this.numberOfLoops) { 
			Keyboard bestCandidate = new Keyboard();
			double sBestCost = sBest.getCost();
			for(Keyboard k : s.getNeighborhood()) {
				double kCost = k.getCost();
				k.display();
				if(kCost > bestCost && !tabuList.contains(k)) {
					bestCandidate = k;
				}
			}
			s = bestCandidate;
			if(bestCandidate.getCost() > sBestCost) {
				sBest = bestCandidate;
			}
			tabuList.add(bestCandidate);
			if(tabuList.size()>1000) {
				tabuList.removeFirst();
			}
			System.out.println(sBestCost);
			this.updateResultat(sBest);
			iteration++;
		}
		this.setDonnee("Coût final", bestCost);
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

	public void setSizeTabuList(double d) {
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
		this.parametres.put("Iterations",10000.0);
		this.parametres.put("Taille_liste_taboue",4000.0);		
	}

	@Override
	protected void launch() {
            this.setSizeTabuList((double)this.getParametre("Taille_liste_taboue"));
            this.setNumberOfLoops((double)this.getParametre("Taille_liste_taboue"));
            this.compute();
    	}

	@Override
	public String getNom() {
		
		return "Recherche taboue";
	}

	public double getNumberOfLoops() {
		return numberOfLoops;
	}

	public void setNumberOfLoops(double d) {
		this.numberOfLoops = d;
	}

	
}
