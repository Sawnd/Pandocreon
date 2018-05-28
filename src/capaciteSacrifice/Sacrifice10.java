package capaciteSacrifice;

import java.util.Iterator;

import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import elementsDeBase.TypeOrigine;
import interfaceGraphique.MessageBox;

/**
 * La classe sacrifice10 represente l'implementation de la capacite speciale de sacrifice de cartes Croyant
 * la classe implemente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice10 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacite speciale 
	 */
	private String nom;

	/**
	 * contructeur de la classe
	 * @param nom represente la valeur e affecter e l'attribut nom 
	 */
	public Sacrifice10(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Cette methode permet au joueur en cours de relancez le de de Cosmogonie
	 *  Le tour se finit normalement , mais sous cette nouvelle influence
	 * implementation de la methode seSacriifer() definie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {
		TypeOrigine origine = Partie.lancerDe();
		Partie.setOrigineDuTour(origine);
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
					j.setpointsJour(2);
				} else if (j.getorigineDivinite() == TypeOrigine.AUBE && origine == TypeOrigine.JOUR) {
					j.setpointsJour(1);
				} else if (j.getorigineDivinite() == origine && origine == TypeOrigine.NUIT) {
					j.setpointsNuit(2);
				} else if (j.getorigineDivinite() == TypeOrigine.CREPUSCULE && origine == TypeOrigine.NUIT) {
					j.setpointsNuit(1);
				} else if (j.getorigineDivinite() == TypeOrigine.CREPUSCULE && origine == TypeOrigine.NEANT) {
					j.setpointsNeant(1);
				} else if (j.getorigineDivinite() == TypeOrigine.AUBE && origine == TypeOrigine.NEANT) {
					j.setpointsNeant(1);
				}
			}
		}
		if (!Partie.getInterfaceGaphiqueActive()) {
			MessageBox.getMessageBox().ajouterMessage("La nouvelle cosmogoni du tour est "+origine);
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
		sb.append("Relancez le de de Cosmogonie. Le tour se finit normalement , mais sous cette nouvelle influence.");
	return sb.toString();
	}
}
