package recuit;

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

	public double getDistance(double a, double b) {
		double distance = Math.sqrt((Math.pow((a-(a%10))/10,2))+Math.pow((b%10-a),2));
		return distance;
	}
}
