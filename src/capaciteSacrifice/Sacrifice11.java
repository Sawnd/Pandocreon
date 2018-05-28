package capaciteSacrifice;

import java.util.Iterator;
import java.util.Scanner;

import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import elementsDeBase.TypeOrigine;
import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageBox;

/**
 * La classe sacrifice11 represente l'implementation de la capacite speciale de sacrifice d'une carte Croyant
 * la classe implemente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice11 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacite speciale 
	 */
	private String nom = "Messie";

	/**
	 * contructeur de la classe
     */
	public Sacrifice11() {

	}

	/**
	 * Cette methode permet au joueur qui l'a sacrifiee de poser le de de Cosmogonie sur la face queil desire et commencer un nouveau tour de jeu 
	 * implementation de la methode seSacriifer() definie dans l'interface capaciteSacrifice
	 */
	@SuppressWarnings("resource")
	public void seSacrifier() {
		TypeOrigine origineDuTour;
		if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println("Quelle Cosmogonie voulez-vous choisir? \n");
			System.out.println("[1] Jour - [2] Nuit - [3] Neant \n");
			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt();
			switch (n) {
			case 1:
				Partie.setOrigineDuTour(TypeOrigine.JOUR);
				break;
			case 2:
				Partie.setOrigineDuTour(TypeOrigine.NUIT);
				break;
			case 3:
				Partie.setOrigineDuTour(TypeOrigine.NEANT);
				break;
			default:
				System.out.println("Veuillez reesayer!");
				n = sc.nextInt();
				break;
			}
		
	}else{
			Partie.setOrigineDuTour(FenetrePrincipale.choisirUneCosmogonie());
		}
		origineDuTour = Partie.getOrigineDuTour();
		MessageBox.getMessageBox().ajouterMessage("La nouvelle Cosmogonie est " + origineDuTour);
		Iterator<Joueur> it = Partie.listeJoueurs.listIterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (j.getorigineDivinite() == origineDuTour && origineDuTour == TypeOrigine.JOUR) {
				j.setpointsJour(2);
			} else if (j.getorigineDivinite() == TypeOrigine.AUBE && origineDuTour == TypeOrigine.JOUR) {
				j.setpointsJour(1);
			} else if (j.getorigineDivinite() == origineDuTour && origineDuTour == TypeOrigine.NUIT) {
				j.setpointsNuit(2);
			} else if (j.getorigineDivinite() == TypeOrigine.CREPUSCULE && origineDuTour == TypeOrigine.NUIT) {
				j.setpointsNuit(1);
			} else if (j.getorigineDivinite() == TypeOrigine.CREPUSCULE && origineDuTour == TypeOrigine.NEANT) {
				j.setpointsNeant(1);
			} else if (j.getorigineDivinite() == TypeOrigine.AUBE && origineDuTour == TypeOrigine.NEANT) {
				j.setpointsNeant(1);
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
		sb.append("Le joueur pose le de de Cosmogonie sur la face queil desire et commence un nouveau tour de jeu .");
		return sb.toString();

	}

}
