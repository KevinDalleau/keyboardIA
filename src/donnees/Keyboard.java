package donnees;

import java.util.Arrays;

public class Keyboard {
	private char[] clavier;
	
	public Keyboard(){
		this.clavier = new char[40];
	}
	
	public void setCharAt(char a,int x){
		this.clavier[x] = a;
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
	
	public int getIndex(char a) {
		int index = Arrays.asList(this.clavier).indexOf(a);
		return index;
	}
	
	public double getCostBinary(int a, int b) { //Calculate the cost between two indexes of letters
		Bigramme bigramme = new Bigramme();
		int freq = bigramme.frequence(a, b);
		double distance = getDistance(a,b);
		double cost = freq*distance;
		
		return cost;
	}

	public double getDistance(double a, double b) {
		double distance = Math.sqrt((Math.pow((a-(a%10))/10,2))+Math.pow((b%10-a),2));
		return distance;
	}
}

