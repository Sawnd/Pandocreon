package capaciteSacrifice;

import java.util.Scanner;

import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import elementsDeBase.TypeOrigine;
import interfaceGraphique.FenetrePrincipale;
/**
 * La classe sacrifice14 represente l'implementation de la capacite speciale de sacrifice de cartes Croyant
 * la classe implemente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice14 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacite speciale 
	 */
	private String nom = "Clerc";
	
/**
 * constructeur de la classe
 */
	public Sacrifice14(){
		
	}
	
	
	/**
	 * Cette methode fait gagner au joueur qui l'a sacrifiee  un nombre de points d'Acton egal au nombre de cartes de Croyants rattachees
	 *  L'Origine des points d'Action est au choix du joueur
	 * implementation de la methode seSacriifer() definie dans l'interface capaciteSacrifice
	 */
	@SuppressWarnings("resource")
	public void seSacrifier() {
		int n;
		Joueur j = Partie.getjoueurEnCours();
		int k = j.nbCroyantsRattachesTotal();
		if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println("Quel Origine pour les points d'actions?  [Jour :1, Nuit :2, Neant:3] ");
			Scanner sc = new Scanner(System.in);
			n = sc.nextInt();
			switch (n) {
			case 1:
				j.setpointsJour(k);
				break;
			case 2:
				j.setpointsNuit(k);
				break;
			case 3:
				j.setpointsNeant(k);

			default:
				j.setpointsNuit(k);
			}
		} else {
			TypeOrigine origine = FenetrePrincipale.choisirUneCosmogonie();
			switch (origine) {
			case JOUR:
				j.setpointsJour(k);
				break;
			case NUIT:
				j.setpointsNuit(k);
				break;
			case NEANT:
				j.setpointsNeant(k);

			default:
				j.setpointsNuit(k);
			}
		}
	}
	
	/**
	 * Cette methode permet de recuperer l'attribut nom de la classe
	 * @return le nom de la carte d'action associee e la capacite de sacrifice
	 * implementation de la methode getNom definie dans l'interface CapaciteSacrifice
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette methode permet un affichage de la capacite de sacrifice de la carte d'action
	 * implementation de la methode  toString() definie dans l'interface CapaciteSacrifice
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(nom + "\n");
		sb.append(
				" Fait gagner un nombre de points d'Acton egal au nombre de cartes de Croyants rattachees. L'Origine des points d'Action est au choix du joueur");
		return sb.toString();
	}

}
