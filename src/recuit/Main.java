package recuit;

import donnees.Keyboard;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecuitSimule rs = new RecuitSimule();
		Keyboard kb = rs.GenerateFirstSol();
		kb.display();
		Keyboard kb2 = rs.generateNeighbor(kb);
		kb2.display();
		
	}

}
