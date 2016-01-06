package recuit;

import donnees.Keyboard;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecuitSimule rs = new RecuitSimule();
		Keyboard kb = rs.GenerateFirstSol();
		kb.display();
		kb.swap(0, 39);
		kb.display();
		
		
	}

}
