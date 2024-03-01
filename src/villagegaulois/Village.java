package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;
import java.util.ArrayList;

import exceptions.VillageSansChefException;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtals);
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
		if (this.chef == null) {
			throw new VillageSansChefException("Il n'y a pas de chef dans le village.");
		}
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}

	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder result = new StringBuilder();
		System.out.println(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " fleurs.");
		int indiceEtal = marche.trouverEtalLibre();
		marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
		System.out.println("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n°" + (indiceEtal + 1) + ".");

		return result.toString();
	}

	public String rechercherVendeursProduit(String produit) {
		StringBuilder result = new StringBuilder();
		Etal[] etalsTrouves = marche.trouverEtals(produit);
		int nbEtalsTrouves = etalsTrouves.length;
		if (nbEtalsTrouves == 0) {
			result.append("Il n'y a pas de vendeur qui propose des " + produit + " au marché.\n");
		} else if (nbEtalsTrouves == 1) {
			result.append("Seul le vendeur " + etalsTrouves[0].getVendeur().getNom() + " propose des " + produit + " au marché.\n");
		} else {
			result.append("Les vendeurs qui proposent des " + produit + " sont :\n");
			for (int i = 0; i < nbEtalsTrouves; i++) {
				result.append("- " + etalsTrouves[i].getVendeur().getNom() + "\n");
			}
		}

		return result.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		return rechercherEtal(vendeur).libererEtal();
	}
	
	public String afficherMarche() {
		StringBuilder result = new StringBuilder();
		result.append("Le marché du village " + this.getNom() + " possède plusieurs étals : \n");
		result.append(marche.afficherMarche());
		return result.toString();
	}

	public class Marche {
		private Etal[] etals;

		public Marche(int nbEtals) {
			etals = new Etal[nbEtals];
			for (int i = 0; i < etals.length; i++) {
				etals[i] = new Etal();
			}
		}

		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (indiceEtal >= 0 && indiceEtal < etals.length) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}
		}

		public int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe())
					return i;
			}
			return -1;
		}

		public Etal[] trouverEtals(String produit) {
			ArrayList<Etal> etalsContientProduit = new ArrayList<Etal>();
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					etalsContientProduit.add(etals[i]);
				}
			}
			
			return etalsContientProduit.toArray(new Etal[etalsContientProduit.size()]);
		}

		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur().equals(gaulois)) {
					return etals[i];
				}
			}
			return null;
		}

		public String afficherMarche() {
			StringBuilder result = new StringBuilder();
			int nbEtalVide = 0;

			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					result.append(etals[i].afficherEtal());
				} else {
					nbEtalVide++;
				}
			}

			if (nbEtalVide != 0) {
				result.append("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n");
			}

			return result.toString();
		}
	}

}