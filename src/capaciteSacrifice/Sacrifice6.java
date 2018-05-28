package capaciteSacrifice;

import java.util.Iterator;
import elementsDeBase.*;
import interfaceGraphique.MessageBox;

/**
 * La classe sacrifice6 represente l'implementation de la capacite speciale de sacrifice de cartes Croyant
 * la classe implemente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice6 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacite speciale 
	 */
    private String nom="Nihilistes";
    
    /**
	 * contructeur de la classe
	 */
    public Sacrifice6(){
    	
    }
    
    /**
	 * Cette methode annule les points d'actions reeus par les joueurs durant le tours en cours
	 * implementation de la methode seSacriifer() definie dans l'interface capaciteSacrifice
	 */
    public void seSacrifier(){
    	TypeOrigine origine = Partie.getOrigineDuTour();
		Iterator<Joueur> it = Partie.listeJoueurs.iterator();
		boolean aDejaJoue = true;
		Joueur j1 = Partie.getjoueurEnCours();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (j == j1) {
				aDejaJoue = false;
			}
			if (!aDejaJoue) {
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
			MessageBox.getMessageBox().ajouterMessage(toString());
		}
    }
    
    /**
	 * Cette methode permet de recuperer l'attribut nom de la classe
	 * @return le nom de la carte d'action associee e la capacite de sacrifice
	 * implementation de la methode getNom() definie dans l'interface CapaciteSacrifice
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
		sb.append("Jusquee la fin du tour, plus aucune Divinite ne reeoit de points deAction");
	return sb.toString();
}
}
