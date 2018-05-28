package capaciteSacrifice;

import java.util.Iterator;

import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import elementsDeBase.TypeOrigine;
import interfaceGraphique.MessageBox;
/**
 * La classe DeusEx6 represente l'implementation de la capacite speciale de sacrifice de la carte DeusEx "Trou Noir"
 * la classe implemente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx6 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacite speciale 
	 */
private String nom="Trou Noir";

/**
 * contructeur de la classe
 */
public DeusEx6(){
	
}

/**
 * Cette methode empeche tous les joueurs de gagner des points deAction durant le tour 
 * Il s'agit donc de supprimer les points qu'ils ont gagne apres le lance du de
 * implementation de la methode seSacriifer() definie dans l'interface capaciteSacrifice
 */
public void seSacrifier(){
	TypeOrigine origine = Partie.getOrigineDuTour();
	Iterator<Joueur> it = Partie.listeJoueurs.iterator();
 Joueur j1 = Partie.getjoueurEnCours();
	while (it.hasNext()) {
		Joueur j = it.next();
		if (j == j1) {
			j.setaDejaJoue(true);
		}
		if (j.getaDejaJoue() == false) {
			if (j.getorigineDivinite() == origine && origine == TypeOrigine.JOUR) {
				j.setpointsJour(-2);
			} else if (j.getorigineDivinite() == TypeOrigine.AUBE && origine == TypeOrigine.JOUR) {
				j.setpointsJour(-1);
			} else if (j.getorigineDivinite() == origine && origine == TypeOrigine.NUIT) {
				j.setpointsNuit(-2);
			} else if (j.getorigineDivinite() == TypeOrigine.CREPUSCULE && origine == TypeOrigine.NUIT) {
				j.setpointsNuit(-1);
			} else if (j.getorigineDivinite() == TypeOrigine.CREPUSCULE && origine == TypeOrigine.NEANT) {
				j.setpointsNeant(-1);
			} else if (j.getorigineDivinite() == TypeOrigine.AUBE && origine == TypeOrigine.NEANT) {
				j.setpointsNeant(-1);
			}
		}
	}
	if (Partie.getInterfaceGaphiqueActive()) {
		MessageBox.getMessageBox().ajouterMessage("Aucun autre joueur ne gagne de points deAction durant ce tour");
	}
}


/**
 * Cette methode permet de recuperer l'attribut nom de la classe
 * @return le nom de la carte d'action associee e la capacite de sacrifice
 * implementation de la methode getNom definie dans l'interface CapaciteSacrifice
 */
public String getNom() {
	return nom;
}

/**
 * Cette methode permet un affichage de la capacite de sacrifice de la carte d'action
 * implementation de la methode  toString() definie dans l'interface CapaciteSacrifice
 */
public String toString(){
	StringBuffer sb=new StringBuffer(nom+"\n");
	sb.append("Aucun autre joueur ne gagne de points deAction durant ce tour .");
	return sb.toString();
}
}
