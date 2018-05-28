package capaciteSacrifice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import elementsDeBase.*;

import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageBox;

/**
 * La classe sacrifice7 repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice de cartes Croyant
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice7 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacit� sp�ciale 
	 */
	private String nom = "Lycanthropes";
	
	/**
	 * contructeur de la classe
	 */
	public Sacrifice7() {

	}

	/**
	 * Cette m�thode permet au joueur en cours de choisir un adversaire et de d�fausser un de ses guides spiriuels
	 * Les croyants rattach�s au guide reournent au centre de la table
	 * impl�mentation de la m�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {
		Joueur j = Partie.getjoueurEnCours();
		Joueur jAdverse = j.choisirUnJoueur();
		GuideSpirituel g = null;
		int compteur = 3;
		LinkedList<GuideSpirituel> cartesRec = jAdverse.getcartesRecuperees();

		if (!Partie.getInterfaceGaphiqueActive()) {
		System.out.println("Quel Guide Spirituel voulez-vous d�poss�der de ses Croyants?");
		jAdverse.afficherCartesRecuperees();
		}else{
			MessageBox.getMessageBox().ajouterMessage("Quel guide spirituel souhaitez vous d�poss�der de ses Croyants?");
			jAdverse.afficherCartesRecuperees();
			LinkedList<CarteAction> liste = new LinkedList<CarteAction>();
			Iterator<GuideSpirituel> it= cartesRec.iterator();
			while(it.hasNext()){
				liste.add(it.next());
			}
			do {
				g = FenetrePrincipale.choisirUnGuideSpirituel(liste);
				if (compteur < 3) {
					MessageBox.getMessageBox().ajouterMessage("Cette carte est bloqu�e, veuillez r�essayer");
					cartesRec.add(g);
				}
				compteur--;
			
		} while (!g.getPeutEtreJouee() && compteur > 0);
		}
			
		
		
		do {
			g = j.choisirUnGuideSpirituel(cartesRec);
			if (compteur < 3) {
				System.out.println("Cette carte est bloqu�e, veuillez r�essayer");
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
				"Retirez tous les Croyants attach�s � l�un des Guides Spirituels d�une autre Divinit�. Les Croyants reviennent au milieu de la table. Le Guide Spirituel est d�fauss�.");
		return sb.toString();
	}

}
