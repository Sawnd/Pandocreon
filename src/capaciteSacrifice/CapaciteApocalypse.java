package capaciteSacrifice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageBox;

/**
 * La classe CapaciteApocalypse repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice d'une carte Apocalypse 
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class CapaciteApocalypse implements CapaciteSacrifice {
	
	/**
	 * nom de la carte d'action qui b�n�ficie de cette capacit� sp�ciale 
	 * attribut vide s'il s'agit d'une carte Apocalypse
	 */
	private String nom = "";

	/**
	 * contructeur de la classe
	 */
	public CapaciteApocalypse(String nom) {
		if (nom == "Martyr") {
			this.nom = nom;
		}
	}
	
	/**
	 *  Cette m�thode permet de sacrifier la carte qu poss�de la capacit� de sacrifice 	 
	 * Le sacrifice de la carte est �quivalent � la pose d'une carte Apocalypse
	 * impl�menttaion de l am�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {
		int nbj;
		Partie.resetCompteurApo();
		nbj = Partie.listeJoueurs.size();
		boolean premierExAequo = false;
		boolean dernierExAequo = false;

		ListIterator<Joueur> it = Partie.listeJoueurs.listIterator();
		ListIterator<Joueur> it2 = Partie.listeJoueurs.listIterator();
		int max1, min1, points;
		Joueur jmax1, jmax2, jmin1, jmin2, j, j2;
		jmax1 = jmax2 = Partie.listeJoueurs.get(0);
		max1 = jmax1.pointsDePriereTotal();
		jmin1 = jmin2 = Partie.listeJoueurs.get(0);
		min1 = jmin1.pointsDePriereTotal();
		while (it.hasNext()) {
			j = it.next();
			points = j.pointsDePriereTotal();
			if (j != jmax1 && points > max1) {
				jmax1 = j;
				max1 = points;
			}
			if (j != jmin1 && points < min1) {
				jmin1 = j;
				min1 = points;
			}

		}
		while (it2.hasNext()) {
			j2 = it2.next();
			points = j2.pointsDePriereTotal();
			if (j2 != jmax1 && points == max1) {
				premierExAequo = true;
				jmax2 = j2;
			}
			if (j2 != jmin1 && points == min1) {
				dernierExAequo = true;
				jmin2 = j2;
			}
		}
		if (nbj > 3) {
			if (dernierExAequo) {
				MessageBox.getMessageBox().ajouterMessage("Cette carte n'a pas d'effet car plusieurs joueurs sont derniers ( notamment "
						+ jmin1 + " et " + jmin2 + ")");
			} else {
				MessageBox.getMessageBox().ajouterMessage("Le Joueur " + jmin1 + " est �limin� avec " + min1 + " points de pri�res!");
				LinkedList<GuideSpirituel> cartesRec = jmin1.getcartesRecuperees();
				Iterator<GuideSpirituel> it3 = cartesRec.iterator();
				GuideSpirituel g;
				while (it3.hasNext()) {
					g = it3.next();
					g.defausserLesCroyants();
				}
			
				Partie.piocheCarteAction.listeCartesAction.addAll(cartesRec);
				Partie.piocheCarteAction.listeCartesAction.addAll(jmin1.getmain());
				Partie.listeJoueurs.remove(jmin1);
				
			}
		} else {
			if (premierExAequo) {
				MessageBox.getMessageBox().ajouterMessage("Cette carte n'a pas d'effet car plusieurs joueurs sont premiers ( notamment "
						+ jmax1 + " et " + jmax2 + ")");
			} else {
				MessageBox.getMessageBox().ajouterMessage("Le Joueur " + jmax1 + " gagne la partie avec " + max1 + " points de pri�res!");
			     FenetrePrincipale.finirLaPartie(jmax1);
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
		StringBuffer sb = new StringBuffer();
		if (nom == "Martyr") {
			sb.append(nom + "\n");
			sb.append("Equivalent � la pose d'une carte Apocalypse");
		}
		return sb.toString();

	}
}
