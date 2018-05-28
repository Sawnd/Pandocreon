package interfaceGraphique;

import java.util.ArrayList;

import elementsDeBase.CentreTable;
import elementsDeBase.Partie;
import strategies.StrategieDifficile;
import strategies.StrategieFacile;
import strategies.StrategieMoyen;
/**
 * Cette classe permet d'implementer le modele MVC et sert de lien entre la classe Partie et la vue de l'interface graphique
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *
 */
public class ControleurPartie  {
	
	/**
	 * attribut contenant l'unique instance de la partie 
	 */
	private Partie partie;
	
	/**
	 * attribut contenant l'unique instance de la classe
	 */
	private static ControleurPartie ctrl = null;
	
	/**
	 * Methode permettant de recuperer l'unique instance de la classe
	 * Si aucune instance n'existe, elle est creee
	 * @return
	 */
	public static ControleurPartie getControleurPartie() {
		if (ctrl == null) {
			ctrl = new ControleurPartie();
		}
		return ctrl;
	}

	/**
	 * constructeur de la classe
	 */
	private ControleurPartie() {

		this.partie = Partie.getPartie();

	}
/**
 * Methode permettant de creer des joueurs dans la partie
 * @param jr liste de joueurs reels
 * @param jv liste de jouuers virtuels
 */
	public void ajouterJoueur(ArrayList<String> jr, ArrayList<String> jv) {
		partie.definirNombreDeJoueurs(jr, jv);
		
	}
	
	/**
	 * Methode permettant de passer au tour suivant
	 */
	public void passerAuTourSuivant(){
		ControleurJoueur.guiderCroyantTermine();
		CentreTable.setCroyantsPeuventEtreGuides();
		if(Partie.getjoueurEnCours()!=null){
		Partie.getjoueurEnCours().setPeuventEtreJouees(true);
		Partie.getjoueurEnCours().setPeuventEtreSacrifiees(true);
		Partie.getjoueurEnCours().defausserGuideVide();}
		partie.gererPartie();
	}
	
	/**
	 * Methode permettant de definir la strategie des joueurs virtuels
	 * @param strategie
	 */
	public static void setStrategie(String strategie){
		setStrategieJoueursVirtuels(strategie);
	}
	
	/**
	 * Methode modifiant l'attribut strategie de la classe Partie en fonction du choix du joueur
	 * @param strategie
	 */
	public static void setStrategieJoueursVirtuels(String strategie){
	switch (strategie) {
	case "Facile":
	StrategieFacile stratf = new StrategieFacile();
	 Partie.setStrategie(stratf);
	break;
	case "Moyen":
	StrategieMoyen stratm = new StrategieMoyen();
	Partie.setStrategie(stratm);
	break;
	case "Difficile":
	StrategieDifficile stratd = new StrategieDifficile();
	Partie.setStrategie(stratd);
	break;
	default:
	StrategieFacile stratf1 = new StrategieFacile();
	Partie.setStrategie(stratf1);
	break;
	}
	}
}
