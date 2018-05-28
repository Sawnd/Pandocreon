/**
 * 
 */
package strategies;

import java.util.*;
import elementsDeBase.*;
import interfaceGraphique.MessageBox;

/**
 * La classe StrategieFacile permet de definir le comportement d'un joeur virtuel de niveau facile
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *
 */
public class StrategieFacile implements Strategie {

	/**
	 * constructeur de la classe
	 */
	public StrategieFacile() {
	
	}

	/**
	 * Permet l'enchainement des methodes relatives au deroulement du tour d'un joueur de niveau facile
	 */
	public void deployerStrategie() {

		this.defausser(Partie.getjoueurEnCours().getmain());
		Partie.getjoueurEnCours().completerSaMain();
		this.mettreCroyantAuCentre(Partie.centreDeTable, (JoueurVirtuel) Partie.getjoueurEnCours());
		GuideSpirituel.guiderCroyant((JoueurVirtuel) Partie.getjoueurEnCours());
		Partie.getjoueurEnCours().defausserGuideVide();
		Partie.getjoueurEnCours().setPeuventEtreJouees(true);
		Partie.getjoueurEnCours().setPeuventEtreSacrifiees(true);
		CentreTable.setCroyantsPeuventEtreGuides();
	}

	/**
	 * Cette Methode permet au joueur virtuel de se defausser de cartes contenues dans sa main
	 * @param main designe la main du joueur virtuel
	 */
	public void defausser(LinkedList<CarteAction> main) {
		int cartedef = 0;
		Joueur j = Partie.getjoueurEnCours();
		LinkedList<CarteAction> mainJ = main;
		Iterator<CarteAction> it = mainJ.iterator();
		LinkedList<CarteAction> cartesADef = new LinkedList<CarteAction>();
		
		/*
		 * le joueur se defausse de cartes qui n'ont pas la meme origine que sa divinite
		 */
		while (it.hasNext()) {
			CarteAction carte = it.next();
			if ((carte instanceof Croyant && (carte.getorigine() != j.getorigineDivinite()))
					|| (carte instanceof GuideSpirituel && (carte.getorigine() != j.getDivinite().getorigine()))) {
				cartesADef.add(carte);
				cartedef++;
			} else if (carte instanceof DeusEx || carte instanceof Apocalypse) {
				cartesADef.add(carte);
				cartedef++;
			}

		}
		mainJ.removeAll(cartesADef);
		j.setmain(mainJ);
		Partie.piocheCarteAction.listeCartesAction.addAll(cartesADef);
/**
 * affiche aux autres joueurs les actions du joueur virtuel
 */
		if (cartedef == 0) {

		MessageBox.getMessageBox().ajouterMessage(Partie.getjoueurEnCours().getNom() + " n'a pas souhaite se defausser de cartes");
		} else {
		
			MessageBox.getMessageBox().ajouterMessage(Partie.getjoueurEnCours().getNom() + " s'est defausse de " + cartedef + " cartes");
		}

		j = Partie.getjoueurEnCours();
	}

	
	/**
	 * Permet au joueur virtuel de mettre des croyants au centre
	 * @param jv Joueur virtuel
	 * @param centre centre de table
	 */
	public void mettreCroyantAuCentre(CentreTable centre, JoueurVirtuel jv) {

		/**
		 * Tant que le joueur a des points et des Croyyants, il le met au centre
		 */
		LinkedList<Croyant> tabcroy = new LinkedList<Croyant>();
		for (Iterator<CarteAction> it = jv.getmain().iterator(); it.hasNext();) {
			CarteAction c1 = it.next();
			if (c1 instanceof Croyant && jv.peutJouer(c1)) {
				tabcroy.add((Croyant) c1);
			}}

			jv.getmain().removeAll(tabcroy);
			centre.ajouterCroyant(tabcroy);

		}

	}

