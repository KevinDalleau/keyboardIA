package algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import commun.Helpers;

import donnees.Keyboard;

public class RechercheTabou extends Algorithme {
	private LinkedList<Keyboard> tabuList; //Liste de claviers déjà visités
	private double numberOfLoops;
	private double sizeTabuList;
	private Keyboard finalSolution;
	
	public RechercheTabou() {
		this.tabuList = new LinkedList<Keyboard>();
		this.numberOfLoops = 100;
		this.sizeTabuList = 4000;
	}
	
	public ArrayList<Keyboard> generateNeighbor(Keyboard key){
		ArrayList<Keyboard> neighborhood = new ArrayList<Keyboard>();
		int array[] = new int[40];
		for(int i=0;i<40;i++){
			array[i]=i;
		}
		boolean done = false;
		int count = 0;
		Keyboard keyb = new Keyboard();
		keyb.copy(key);
		int[] rand ;
		do{
			rand = Helpers.pickNRandom(array, 2);
			done = keyb.swap(rand[0], rand[1]);
			if(done) {
				neighborhood.add(keyb);
				count++;
			}
		}while(count != 4);
		return neighborhood;
	}
	
	public Keyboard compute() {
		int iteration = 0;
		Keyboard s0 = GenerateFirstSol();
		Keyboard s = new Keyboard();
		s.copy(s0);
		Keyboard sBest = new Keyboard();
		sBest.copy(s0);
		double bestCost = s0.getCost();
		LinkedList<Keyboard> tabuList = new LinkedList<Keyboard>();
		while(iteration < this.numberOfLoops) { 
			Keyboard bestCandidate = new Keyboard();
			double sBestCost = sBest.getCost();
			s.display();
			for(Keyboard k : this.generateNeighbor(s)) {
				System.out.println("Generated keyboards (by swap)");
				k.display();
				double kCost = k.getCost();
				if(kCost < bestCost && !tabuList.contains(k)) {
					bestCandidate.copy(k);
				}
			}
			s.copy(bestCandidate);
			if(bestCandidate.getCost() < sBestCost) {
				sBest.copy(bestCandidate);
			}
			tabuList.add(bestCandidate);
			if(tabuList.size()>1000) {
				tabuList.removeFirst();
			}
			System.out.println(sBestCost);
			this.updateResultat(sBest);
			iteration++;
		}
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
		this.parametres.put("Iterations",10000);
		this.parametres.put("Taille_liste_taboue",1000);		
	}

	@Override
	protected void launch() {
            this.setSizeTabuList((int)this.getParametre("Taille_liste_taboue"));
            this.setNumberOfLoops((int)this.getParametre("Taille_liste_taboue"));
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
