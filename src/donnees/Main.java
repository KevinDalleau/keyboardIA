package donnees;

import recuit.RechercheTabou;
import recuit.RecuitSimule;

public class Main {

	public static void main(String[] args) {
		Keyboard key = new Keyboard();
		//RecuitSimule recuit = new RecuitSimule();
		RechercheTabou rechTab = new RechercheTabou();
		//key = rechTab.GenerateFirstSol();
		//key.display();
		key = rechTab.compute(100);
		key.display();
	}

}
