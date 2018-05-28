package capaciteSacrifice;

import java.util.ListIterator;

import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;

/**
 * La classe DeusEx2 repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice de la carte DeusEx " Stase"
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx2 implements CapaciteSacrifice {

	/**
	 * nom de la  capacit� sp�ciale 
	 */
	private String nom = "Stase";

	/**
	 * contructeur de la classe
	 */
	public DeusEx2() {

	}

	/**
	 * Cette m�thode permet au joueur en cours de proteger un Guide Spirituel et ses Croyants jusqu�� ce que cette carte soit annul�e ou jusqu�� la prochaine tentative d�Apocalypse
	 * impl�mentation de la m�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {
		Joueur j = Partie.getjoueurEnCours();
		Joueur jAdverse = j.choisirUnJoueur();
		jAdverse.afficherCartesRecuperees();
		GuideSpirituel g = j.choisirUnGuideSpirituel(jAdverse.getcartesRecuperees());
		g.setStase(true);
		jAdverse.getcartesRecuperees().add(g);
		ListIterator<Joueur> it = Partie.listeJoueurs.listIterator();
		Joueur j1;
		while (it.hasNext()) {
			j1 = it.next();
			if (j1 == jAdverse) {
				j1.setcartesRecuperees(jAdverse.getcartesRecuperees());
			}
		}
	}
	
	/**
	 * Cette m�thode permet de r�cup�rer l'attribut nom de la classe
	 * @return le nom de la carte d'action associ�e � la capacit� de sacrifice
	 * impl�mentation de la m�thode getNom d�finie dans l'interface CapaciteSacrifice
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
				"Prot�ge un Guide Spirituel et ses Croyants jusqu�� ce que cette carte soit annul�e ou jusqu�� la prochaine tentative d�Apocalypse");
		return sb.toString();
	}

}
