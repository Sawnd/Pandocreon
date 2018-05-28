package capaciteSacrifice;

import elementsDeBase.Partie;
import interfaceGraphique.MessageBox;

/**
 * La classe DeusEx10 représente l'implémentation de la capacité spéciale de sacrifice d'une carte DeusEx 
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx10 implements CapaciteSacrifice{
	
	/**
	 * nom de la  capacité spéciale 
	 */
	private String nom = "Miroir";
	
	/**
	 * contructeur de la classe
	 */
	public DeusEx10() {

	}
	
	/**
	 *  Cette méthode permet de retourner les effets d’une carte d’Action sur la Divinité qui l’a posée.
	 * Le sacrifice de la carte est équivalent à la pose d'une carte Apocalypse
	 * implémenttaion de l améthode seSacriifer() définie dans l'interface capaciteSacrifice
	 */

	public void seSacrifier() {
		if (Partie.getcarteEnCours() != null) {
			Partie.getcarteEnCours().jouerCarte();
			Partie.getcarteEnCours().setPeutEtreJouee(false);
		}
		if (Partie.getInterfaceGaphiqueActive()) {
			MessageBox.getMessageBox().ajouterMessage(" Les effets de la carte "+Partie.getcarteEnCours().getStrategie().getNom()+ " sont retournés sur la Divinité qui l’a posée.");
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(nom + "\n");
		sb.append("Retourne les effets d’une carte d’Action sur la Divinité qui l’a posée.");
		return sb.toString();
	}
	public String getNom() {
		return nom;
	}

}
