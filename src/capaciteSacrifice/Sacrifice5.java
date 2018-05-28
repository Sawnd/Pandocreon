package capaciteSacrifice;

import java.util.*;
import elementsDeBase.*;
import interfaceGraphique.MessageBox;

/**
 * La classe sacrifice5 représente l'implémentation de la capacité spéciale de sacrifice de cartes Croyant
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice5 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacité spéciale 
	 */
	String nom = "Guerriers Saints";

	/**
	 * contructeur de la classe
	 */
	public Sacrifice5() {

	}

	/**
	 * Cette méthode permet au joeur en cours de choisir un adversairequi va récupérer un de ses Guides Spirituels  dans sa main 
	 * Les Croyants rattachés au guide reviennent au centre de la table
	 * implémentation de la méthode seSacriifer() définie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {
		Joueur j = Partie.getjoueurEnCours();
		Joueur jAdverse = j.choisirUnJoueur();
		GuideSpirituel guide = null;
		int compteur = 3;
		if (!Partie.getInterfaceGaphiqueActive()) {
		System.out.println("Choisissez le guide Spirituel que vous saitez récupérer");
		}
		else{
		MessageBox.getMessageBox().ajouterMessage("Choisissez le guide Spirituel que vous saitez récupérer");
		}
		jAdverse.afficherCartesRecuperees();
		do {
			guide = j.choisirUnGuideSpirituel(jAdverse.getcartesRecuperees());
			if (compteur < 3) {
				if (!Partie.getInterfaceGaphiqueActive()) {
					System.out.println("Cette carte est bloquée! Recommencez.");
				}else{
					  MessageBox.getMessageBox().ajouterMessage("Cette carte est bloquée! Recommencez.");
				}
				if (!Partie.getInterfaceGaphiqueActive()) {
		  jAdverse.getcartesRecuperees().add(guide);
		  }
			}
			compteur--;
		} while (!guide.getPeutEtreJouee() && compteur > 0);

		if (guide != null) {
			guide.defausserLesCroyants();
			ListIterator<Joueur> it = Partie.listeJoueurs.listIterator();
			Joueur j1;
			while (it.hasNext()) {
				j1 = it.next();
				if (j1 == j) {
					j1.ajouterCarte(guide);
				}
			}
		} ;
	}
	
	/**
	 * Cette méthode permet de récupérer l'attribut nom de la classe
	 * @return le nom de la carte d'action associée à la capacité de sacrifice
	 * implémentation de la méthode getNom() définie dans l'interface CapaciteSacrifice
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette méthode permet un affichage de la capacité de sacrifice de la carte d'action
	 * implémentation de la méthode  toString() définie dans l'interface CapaciteSacrifice
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(nom + "\n");
		sb.append(
				"Un Guide Spirituel revient dans la main de sa Divinité. Ses Croyants reviennent au centre de la table.");

		return sb.toString();
	}

}
