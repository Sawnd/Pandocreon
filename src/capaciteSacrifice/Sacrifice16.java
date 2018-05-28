package capaciteSacrifice;

import java.util.LinkedList;
import java.util.Scanner;

import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import elementsDeBase.TypeDogme;
import interfaceGraphique.MessageBox;

/**
 * La classe sacrifice16 represente l'implementation de la capacite speciale de sacrifice de cartes Croyant
 * la classe implemente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice16 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacite speciale 
	 */
	private String nom = "Anarchiste";

	
	/**
	 * contructeur de la classe
	 */
	
	public Sacrifice16() {

	}
	
	/**
	 * Cette methode sacrifie un Guide Spirituel, si lui ou sa Divinite ne croit pas au Dogme Chaos. 
	 * Les capacites speciales sont jouees normalement.
	 * implementation de la methode seSacriifer() definie dans l'interface capaciteSacrifice
	 */

	public void seSacrifier() {
		TypeDogme dogme1, dogme2, dogme3;
		Joueur joueurChoisi;
		do {
			joueurChoisi = Partie.getjoueurEnCours().choisirUnJoueur();

			dogme1 = joueurChoisi.getDogmeDivinite(1);
			dogme2 = joueurChoisi.getDogmeDivinite(2);
			dogme3 = joueurChoisi.getDogmeDivinite(3);
		} while ((dogme1 != TypeDogme.CHAOS && dogme2 == TypeDogme.CHAOS && dogme3 == TypeDogme.CHAOS)
				&& !joueurChoisi.croitAuDogme(TypeDogme.CHAOS));

		GuideSpirituel g;
		LinkedList<GuideSpirituel> cartesRec = joueurChoisi.getcartesRecuperees();
		MessageBox.getMessageBox().ajouterMessage("Quel Guide Spirituel Voulez vous Sacrifier?");
		joueurChoisi.afficherCartesRecuperees();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt() - 1;
		while (k >= cartesRec.size() - 1 && (cartesRec.get(k).getdogme1() == TypeDogme.CHAOS
				|| cartesRec.get(k).getdogme2() == TypeDogme.CHAOS)) {
			MessageBox.getMessageBox()
					.ajouterMessage("Veuillez saisir un nombre valide, ce joueur possede le dogme Chaos");
			k = sc.nextInt() - 1;
		}
		g = cartesRec.remove(k);
		g.jouerCarte();
		Partie.piocheCarteAction.listeCartesAction.add(g);
		joueurChoisi.setcartesRecuperees(cartesRec);

	}
	
	/**
	 * Cette methode permet de recuperer l'attribut nom de la classe
	 * @return le nom de la carte d'action associee e la capacite de sacrifice
	 * implementation de la methode getNom() definie dans l'interface CapaciteSacrifice
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
				" Sacrifie un Guide Spirituel, si lui ou sa Divinite ne croit pas au Dogme Chaos. Les capacites speciales sont jouees normalement.");
		return sb.toString();
	}

}
