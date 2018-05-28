package capaciteSacrifice;

import java.util.*;
import elementsDeBase.*;
import interfaceGraphique.MessageBox;

/**
 * La classe sacrifice5 repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice de cartes Croyant
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice5 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacit� sp�ciale 
	 */
	String nom = "Guerriers Saints";

	/**
	 * contructeur de la classe
	 */
	public Sacrifice5() {

	}

	/**
	 * Cette m�thode permet au joeur en cours de choisir un adversairequi va r�cup�rer un de ses Guides Spirituels  dans sa main 
	 * Les Croyants rattach�s au guide reviennent au centre de la table
	 * impl�mentation de la m�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {
		Joueur j = Partie.getjoueurEnCours();
		Joueur jAdverse = j.choisirUnJoueur();
		GuideSpirituel guide = null;
		int compteur = 3;
		if (!Partie.getInterfaceGaphiqueActive()) {
		System.out.println("Choisissez le guide Spirituel que vous saitez r�cup�rer");
		}
		else{
		MessageBox.getMessageBox().ajouterMessage("Choisissez le guide Spirituel que vous saitez r�cup�rer");
		}
		jAdverse.afficherCartesRecuperees();
		do {
			guide = j.choisirUnGuideSpirituel(jAdverse.getcartesRecuperees());
			if (compteur < 3) {
				if (!Partie.getInterfaceGaphiqueActive()) {
					System.out.println("Cette carte est bloqu�e! Recommencez.");
				}else{
					  MessageBox.getMessageBox().ajouterMessage("Cette carte est bloqu�e! Recommencez.");
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
	 * Cette m�thode permet de r�cup�rer l'attribut nom de la classe
	 * @return le nom de la carte d'action associ�e � la capacit� de sacrifice
	 * impl�mentation de la m�thode getNom() d�finie dans l'interface CapaciteSacrifice
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette m�thode permet un affichage de la capacit� de sacrifice de la carte d'action
	 * impl�mentation de la m�thode  toString() d�finie dans l'interface CapaciteSacrifice
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(nom + "\n");
		sb.append(
				"Un Guide Spirituel revient dans la main de sa Divinit�. Ses Croyants reviennent au centre de la table.");

		return sb.toString();
	}

}
