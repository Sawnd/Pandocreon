package capaciteSacrifice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import elementsDeBase.*;

import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageBox;

/**
 * La classe sacrifice7 représente l'implémentation de la capacité spéciale de sacrifice de cartes Croyant
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice7 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacité spéciale 
	 */
	private String nom = "Lycanthropes";
	
	/**
	 * contructeur de la classe
	 */
	public Sacrifice7() {

	}

	/**
	 * Cette méthode permet au joueur en cours de choisir un adversaire et de défausser un de ses guides spiriuels
	 * Les croyants rattachés au guide reournent au centre de la table
	 * implémentation de la méthode seSacriifer() définie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {
		Joueur j = Partie.getjoueurEnCours();
		Joueur jAdverse = j.choisirUnJoueur();
		GuideSpirituel g = null;
		int compteur = 3;
		LinkedList<GuideSpirituel> cartesRec = jAdverse.getcartesRecuperees();

		if (!Partie.getInterfaceGaphiqueActive()) {
		System.out.println("Quel Guide Spirituel voulez-vous déposséder de ses Croyants?");
		jAdverse.afficherCartesRecuperees();
		}else{
			MessageBox.getMessageBox().ajouterMessage("Quel guide spirituel souhaitez vous déposséder de ses Croyants?");
			jAdverse.afficherCartesRecuperees();
			LinkedList<CarteAction> liste = new LinkedList<CarteAction>();
			Iterator<GuideSpirituel> it= cartesRec.iterator();
			while(it.hasNext()){
				liste.add(it.next());
			}
			do {
				g = FenetrePrincipale.choisirUnGuideSpirituel(liste);
				if (compteur < 3) {
					MessageBox.getMessageBox().ajouterMessage("Cette carte est bloquée, veuillez réessayer");
					cartesRec.add(g);
				}
				compteur--;
			
		} while (!g.getPeutEtreJouee() && compteur > 0);
		}
			
		
		
		do {
			g = j.choisirUnGuideSpirituel(cartesRec);
			if (compteur < 3) {
				System.out.println("Cette carte est bloquée, veuillez réessayer");
				cartesRec.add(g);
			}
			compteur--;
		} while (!g.getPeutEtreJouee() && compteur > 0);

		if (g != null) {

			g.defausserLesCroyants();

			ListIterator<Joueur> it = Partie.listeJoueurs.listIterator();
			Joueur j1;
			while (it.hasNext()) {
				j1 = it.next();
				if (j1 == jAdverse) {
					j1.ajouterCarte(g);
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
		sb.append(
				"Retirez tous les Croyants attachés à l’un des Guides Spirituels d’une autre Divinité. Les Croyants reviennent au milieu de la table. Le Guide Spirituel est défaussé.");
		return sb.toString();
	}

}
