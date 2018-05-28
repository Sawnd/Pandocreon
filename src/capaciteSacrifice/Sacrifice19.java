package capaciteSacrifice;

import java.util.ListIterator;
import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import interfaceGraphique.MessageBox;

/**
 * La classe sacrifice19 représente l'implémentation de la capacité spéciale de sacrifice de cartes Croyant
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice19 implements CapaciteSacrifice {

	/**
	 * nom de la  capacité spéciale 
	 */
	private String nom = "Devin";

	/**
	 * contructeur de la classe
	 */
	
	public Sacrifice19() {

	}

	
	/**
	 * Cette méthode permet au joueur en cours de choisir un adversaire ayant le Dogme Nature ou Mystique qu'il oblige  à sacrifier l’un de ses Guildes Spirituels
	 * Les capacités spéciales sont jouées normalement.
	 * implémentation de la méthode seSacriifer() définie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {
		int compteur = 3;
		Joueur jEnCours = Partie.getjoueurEnCours();
		Joueur j = jEnCours.choisirUnJoueur();
		j.afficherCartesRecuperees();

		GuideSpirituel g = null;
		do {
			g = j.choisirUnGuideSpirituel(j.getcartesRecuperees());
			if (compteur < 3) {
				if (!Partie.getInterfaceGaphiqueActive()) {
				System.out.println("Cette carte est bloquée");
				}else{
					MessageBox.getMessageBox().ajouterMessage("Cette carte est bloquée");
				}
				j.getcartesRecuperees().add(g);
			}
			compteur--;
		} while (compteur > 0 && !g.getPeutEtreJouee());
		if (g != null) {
			g.jouerCarte();
			Partie.piocheCarteAction.listeCartesAction.add(g);
			ListIterator<Joueur> it = Partie.listeJoueurs.listIterator();
			Joueur j1;
			while (it.hasNext()) {
				j1 = it.next();
				if (j1 == j) {
					j1.setcartesRecuperees(j.getcartesRecuperees());
				}
			}
		}
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
		sb.append("Oblige une Divinité ayant le Dogme Nature ou Mystique à sacrifier l’un de ses Guildes Spirituels.");
		return sb.toString();
	}
}
