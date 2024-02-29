package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois gaulois = new Gaulois("Obelix", 20);
		etal.libererEtal();
		System.out.println("Fin du test1");
		
		etal.acheterProduit(10, gaulois);
		System.out.println("Fin du test2");
		
		try {
			etal.acheterProduit(-2, gaulois);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test3");
		}
	
}


