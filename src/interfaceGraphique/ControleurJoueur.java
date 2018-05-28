package interfaceGraphique;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import elementsDeBase.CarteAction;
import elementsDeBase.Croyant;
import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
/**
 * Cette classe permet d'implementer le modele MVC et sert de lien entre la classe Joueur et la vue de l'interface graphique
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *
 */

public class ControleurJoueur implements Observer {
	
	/**
	 * atrribut contenant le joueur
	 */
	private Joueur j;
	
	/**
	 * contient un guide spirituel
	 */
	private static GuideSpirituel guide = null;
	
	/**
	 * contient un guide spirituel
	 */
	private static GuideSpirituel ancienGuide = null;

	/**
	 * constructeur de la classe
	 * @param j contient la valeur e affecter e l'attribut j
	 */
	public ControleurJoueur(Joueur j) {
		this.j = j;
	}

	
	/**
	 * Methode permettant aux joueurs de guider un croyant
	 * @param g guide auquel rattacher les croyants
	 */
	public void guiderCroyant(GuideSpirituel g) {
		if (j.peutJouer(g) && !Partie.centreDeTable.getCroyantsCentre().isEmpty()) {
			guide = g;
			ancienGuide = g;
			MessageBox.getMessageBox().ajouterMessage("Quels croyants voulez vous guider?");
		}
		if (Partie.centreDeTable.getCroyantsCentre().isEmpty()) {
			MessageBox.getMessageBox().ajouterMessage("Le centre de table est vide!");
		}

	}
	
	/**
	 * Methode permettant de rattacher un croyant e un guide
	 * @param c croyant e rattacher
	 */

	public void RattacherCroyant(Croyant c) {
		if (guide != null && guide.guiderCroyant(c)) {
			boolean aGuide = false;
			int i = 0;
			while (i < guide.NB_CARTES_RATTACHEES_MAX && !aGuide && !guide.isFull()) {
				if (guide.getcroyantsGuides()[i] == null) {
					guide.getcroyantsGuides()[i] = c;
					aGuide = true;
				}
				i++;
			}
			Partie.centreDeTable.enleverCroyant(c);
		}
		if (guide == null) {
			MessageBox.getMessageBox().ajouterMessage("Choisissez un guide!!");
		}
		if (guide != null && guide.isFull()) {
			this.j.addCarteRecuperee(guide);
			this.j.removeCarte(ancienGuide);
			guide = null;
			ancienGuide = null;
			MessageBox.getMessageBox().ajouterMessage("Vous ne pouvez pas guider de croyants supplementaires");
		}
	}

	
	/**
	 * Methode permettant d'ajouter e la liste des cartes recuperees une guide e qui sont rattaches des croyants
	 */
	public static void guiderCroyantTermine() {
		if (guide != null) {
			Partie.getjoueurEnCours().addCarteRecuperee(guide);
			Partie.getjoueurEnCours().removeCarte(ancienGuide);
			guide = null;
		}
		guide = null;
		ancienGuide = null;
	}

	
	/**
	 * Permet d'utiliser la capacite speciale d'une divinite
	 * elle fait appel e la methode de la divinite
	 */
	public void utiliserCapaciteDivinite() {
		if (!Partie.getjoueurEnCours().getDivinite().getCapaciteUtilisee()) {
			Partie.getjoueurEnCours().getDivinite().utiliserCapacite();
		}else{
			MessageBox.getMessageBox().ajouterMessage("Vous avez dejà utiliser cette capacite...");
		}
	}

/**
 * Permet e un joueur de se defausser d'une carte
 * @param c carte e defausser
 */

	public void defausserUneCarte(CarteAction c) {
		j.defausser(c);
	}

	/**
	 * Permet e un joueur de completer sa main
	 * Elle appelle la methode completerSeMain du joueur 
	 */
	public void completerSamain() {
		j.completerSaMain();
	}

	/**
	 * Methode permettant de mettre un croyant au centre
	 * elle appelle la methode mettreCroyantAuCentre du jeu
	 * @param c croyant e mettre au centre
	 */
	public void mettreCroyantAuCentre(Croyant c) {
		j.mettreCroyantAuCentre(c);
	}
	
	
/**
 * permet de recuperer un croyant
 * @param g guide auquel rattacher le croyant
 * @param c croyant e rattacher
 */
	public void recupererCroyant(GuideSpirituel g, Croyant c) {
		int rep;
		if (j.peutJouer(g)) {
			do {
				if (g.guiderCroyant(c)) {
					Partie.centreDeTable.enleverCroyant(c);
				}
				rep = JOptionPane.showConfirmDialog(null, "Voulez vous guider un autre croyant?");
				if (rep == JOptionPane.YES_OPTION) {
					MessageBox.getMessageBox().ajouterMessage("Selectionner un autre croyant");
				}
			} while (!g.isFull() || rep != JOptionPane.NO_OPTION);
		}
		j.getmain().remove(g);
		j.getcartesRecuperees().add(g);
	}

	/**
	 * Permet de sacrifier une carte d'action
	 * @param c carte e sacrifier
	 */
	public void sacrifierCarte(CarteAction c) {
		j.sacrifierCartes(c);
		Iterator<GuideSpirituel> it = j.getcartesRecuperees().iterator();
		while (it.hasNext()) {
			GuideSpirituel g = it.next();
			for (int i = 0; i < g.NB_CARTES_RATTACHEES_MAX; i++) {
				if (g.getcroyantsGuides()[i] == c) {
					;
					g.getcroyantsGuides()[i] = null;
					j.defausser(c);
				}
			}
		}
	}

	/**
	 * Methode permettant de mettere e jour la main du joueur 
	 */
	@SuppressWarnings("unchecked")
	public void update(Observable o, Object args) {

		if (args instanceof LinkedList<?>) {
			new VueMain((LinkedList<CarteAction>) args);
		}
	}
}
