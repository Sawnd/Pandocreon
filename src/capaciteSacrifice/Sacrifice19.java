package capaciteSacrifice;

import java.util.ListIterator;
import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import interfaceGraphique.MessageBox;

/**
 * La classe sacrifice19 repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice de cartes Croyant
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice19 implements CapaciteSacrifice {

	/**
	 * nom de la  capacit� sp�ciale 
	 */
	private String nom = "Devin";

	/**
	 * contructeur de la classe
	 */
	
	public Sacrifice19() {

	}

	
	/**
	 * Cette m�thode permet au joueur en cours de choisir un adversaire ayant le Dogme Nature ou Mystique qu'il oblige  � sacrifier l�un de ses Guildes Spirituels
	 * Les capacit�s sp�ciales sont jou�es normalement.
	 * impl�mentation de la m�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
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
				System.out.println("Cette carte est bloqu�e");
				}else{
					MessageBox.getMessageBox().ajouterMessage("Cette carte est bloqu�e");
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
		sb.append("Oblige une Divinit� ayant le Dogme Nature ou Mystique � sacrifier l�un de ses Guildes Spirituels.");
		return sb.toString();
	}
}
