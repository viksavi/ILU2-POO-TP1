package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois gaulois = new Gaulois("Obelix", 20);
		etal.libererEtal();
		System.out.println("Fin du test1");
		
		try {
			etal.acheterProduit(2, gaulois);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test");
		
		
		
	}
	
}


