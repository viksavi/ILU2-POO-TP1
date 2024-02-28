package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}
	
	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public class Marche{
		private Etal[] etals;
		
		public Marche(int nbEtals) {
			etals = new Etal[nbEtals];
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (indiceEtal > 0 && indiceEtal < etals.length)
			{
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}
		}
		
		public int trouverEtalLibre() {
			for(int i = 0; i < etals.length; i++)
			{
				if(!etals[i].isEtalOccupe())
					return i;
			}
			return -1;
		}
		
		public Etal[] trouverEtals(String produit) 
		{
			Etal[] etalsContientProduit = new Etal[etals.length];
			int nbEtals = 0;
			for(int i = 0; i < etalsContientProduit.length; i++)
			{
				if(etals[i].contientProduit(produit))
				{
					etalsContientProduit[nbEtals] = etals[i];
					nbEtals++;
				}
			}
			return etalsContientProduit;
		}
		
		public Etal trouverVendeur(Gaulois gaulois)
		{
			for(int i = 0; i < etals.length; i++)
			{
				if(etals[i].getVendeur().equals(gaulois))
				{
					return etals[i];
				}
			}
			return null;
		}
		
		public String afficherMarche() {
			StringBuilder result = new StringBuilder();
			int nbEtalVide = 0;
			
			for(int i = 0; i < etals.length; i++)
			{
				if(etals[i].isEtalOccupe())
				{
					result.append(etals[i].afficherEtal());
				}
				else {
					nbEtalVide++;
				}
			}
			
			if(nbEtalVide != 0)
			{
				result.append("Il reste" + nbEtalVide + " étals non utilisés dans le marché.\n");
			}
			
			return result.toString();
		}
	}

}