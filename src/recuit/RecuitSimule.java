package recuit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import donnees.Keyboard;

public class RecuitSimule {
	private double Tmax;
	private double Tmin;
	private double Alpha;
	private double Nbt;
	private double Nbi;
	private Keyboard finalSolution;
	
	
	public RecuitSimule(){
		this.Tmax = 1;
		this.Tmin = 0.0001;
		this.Alpha = 0.9;
		this.Nbt = 50;
		this.Nbi = 10; 
	}
	
	public RecuitSimule(double tmax, double tmin, double alpha,double nbt,double nbi){
		this.Tmax = tmax;
		this.Tmin = tmin;
		this.Alpha = alpha;
		this.Nbt = nbt;
		this.Nbi = nbi;
	}
	
	public void Compute(){
		Keyboard firstSol = this.GenerateFirstSol();
		//compute cost function
		for(int i=0;i<Nbt;i++){
			for(int j=0;j<Nbi;j++){
				/*if(){
					
				}
				else{
					
				}*/
			}
		}
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
	
	public double acceptanceProbability(int energy, int newEnergy, double temperature) {
        if (newEnergy < energy) {
            return 1.0;
        }
        return Math.exp((energy - newEnergy) / temperature);
    }
}	
