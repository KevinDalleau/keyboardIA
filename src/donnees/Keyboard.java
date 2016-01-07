package donnees;

import java.util.ArrayList;
import java.util.Random;

import commun.Helpers;
import commun.Main;

public class Keyboard implements Cloneable {
	private char[] clavier;

	public Keyboard(){
		this.clavier = new char[40];
	}
        
        public static Keyboard GenerateFirstSol(){
		Keyboard initialSolution = new Keyboard();
		char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		int array[] = new int[40];
		for(int i=0;i<40;i++){
			array[i]=i;
		}
		int[] picks = Helpers.pickNRandom(array, 26);
		for(int i=0;i<26;i++){
			initialSolution.setCharAt(alphabet[i], picks[i]);
		}
		return initialSolution;
	}

	public void setCharAt(char a,int x){
            this.clavier[x] = a;
	}
        
        public char getCharAt(int x){
            return this.clavier[x];
	}

	public void display(){
		System.out.println("Disposition du clavier");
		for(int i=0;i<4;i++){
			for(int j=(i*10);j<(i*10+10);j++){
				System.out.print(this.clavier[j]+"    ");
			}
			System.out.println("\n");
		}
	}

	public boolean swap(int x, int y){
		boolean swapped = false;
		if(this.clavier[x]!= ' ' || this.clavier[y]!=' '){
			char temp = this.clavier[x];
			this.clavier[x] = this.clavier[y];
			this.clavier[y] = temp;
			swapped = true;
		}
		else{
			swapped = false;
		}
		return swapped;
	}


	public int getLetter(int a) { //Get the letter at a given position (a) on the keyboard.
		int index = this.clavier[a];
		return index;
	}

	public double getCostBinary(int a, int b) { //Calculate the cost between two indexes of letters. a and b : positions on the 4*10 keyboard
		int aLetter = this.getLetter(a)-65;
		int bLetter = this.getLetter(b)-65;
		int freq = Main.bigramme.frequence(aLetter, bLetter);
		double distance = getDistance(a,b);
		double cost = freq*distance;

		return cost;
	}
	
	public double getCost() {
		int cost = 0;
		for(int i=0; i < 40;i++) {
			if(!(clavier[i]==0)) {
				for(int j=0; j<40;j++) {
					if(!(clavier[j]==0) && i!=j) {
						cost += this.getCostBinary(i,j);
					}
				}
			}
		}
		//System.out.println(cost);

		return cost;
	}

	public double getDistance(double a, double b) {
		double distance = Math.sqrt((Math.pow((a-(a%10))/10,2))+Math.pow((b%10-a),2));
		return distance;
	}

	public void copy(Keyboard key){
		for(int i = 0;i<key.clavier.length;i++){
			this.clavier[i] = key.clavier[i];
		}
	}
	
	public ArrayList<Keyboard> getNeighborhood() { //Retrieves 8 keyboards, corresponding to 8 swaps from a random position on the original keyboard and its neighbors
		Keyboard k1 = new Keyboard();
		Keyboard k2 = new Keyboard();
		Keyboard k3 = new Keyboard();
		Keyboard k4 = new Keyboard();
		ArrayList<Keyboard> neighborhoodList = new ArrayList<Keyboard>();
		try {
			k1 = (Keyboard) this.clone();
			k2 = (Keyboard) this.clone();
			k3 = (Keyboard) this.clone();
			k4 = (Keyboard) this.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random random = new Random();
		int pos;
		int i = 0;
		while(((pos = random.nextInt(40)) != ' ') && i < 1) { //Attention, le reste du code est très sale
			System.out.println(pos);
			if(pos%10 != 0) { // Si on n'est pas sur le bord gauche, on swappe avec la lettre à gauche
				k1.swap(pos, pos-1);
			}
			if(pos/10>1) { // Si on n'est pas sur le bord haut, on swappe avec la lettre en haut
				k2.swap(pos, pos-10);
			}
			if(pos%10!=9) { // Si on n'est pas sur le bord droit, on swappe avec la lettre à droite
				k3.swap(pos, pos+1);
			}
			if(pos/10<3){ // Si on n'est pas sur le bord bas, on swappe avec la lettre en bas
				k4.swap(pos, pos+10);
			}
			i++;
		}
		neighborhoodList.add(k1);
		neighborhoodList.add(k2);
		neighborhoodList.add(k3);
		neighborhoodList.add(k4);
		
		return neighborhoodList;
		
	}



}