package capaciteSacrifice;

import java.util.ListIterator;

import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;

/**
 * La classe DeusEx2 représente l'implémentation de la capacité spéciale de sacrifice de la carte DeusEx " Stase"
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx2 implements CapaciteSacrifice {

	/**
	 * nom de la  capacité spéciale 
	 */
	private String nom = "Stase";

	/**
	 * contructeur de la classe
	 */
	public DeusEx2() {

	}

	/**
	 * Cette méthode permet au joueur en cours de proteger un Guide Spirituel et ses Croyants jusqu’à ce que cette carte soit annulée ou jusqu’à la prochaine tentative d’Apocalypse
	 * implémentation de la méthode seSacriifer() définie dans l'interface capaciteSacrifice
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
	 * Cette méthode permet de récupérer l'attribut nom de la classe
	 * @return le nom de la carte d'action associée à la capacité de sacrifice
	 * implémentation de la méthode getNom définie dans l'interface CapaciteSacrifice
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
				"Protège un Guide Spirituel et ses Croyants jusqu’à ce que cette carte soit annulée ou jusqu’à la prochaine tentative d’Apocalypse");
		return sb.toString();
	}

}
