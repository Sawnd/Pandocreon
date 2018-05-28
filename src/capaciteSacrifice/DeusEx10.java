package capaciteSacrifice;

import elementsDeBase.Partie;
import interfaceGraphique.MessageBox;

/**
 * La classe DeusEx10 repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice d'une carte DeusEx 
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx10 implements CapaciteSacrifice{
	
	/**
	 * nom de la  capacit� sp�ciale 
	 */
	private String nom = "Miroir";
	
	/**
	 * contructeur de la classe
	 */
	public DeusEx10() {

	}
	
	/**
	 *  Cette m�thode permet de retourner les effets d�une carte d�Action sur la Divinit� qui l�a pos�e.
	 * Le sacrifice de la carte est �quivalent � la pose d'une carte Apocalypse
	 * impl�menttaion de l am�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
	 */

	public void seSacrifier() {
		if (Partie.getcarteEnCours() != null) {
			Partie.getcarteEnCours().jouerCarte();
			Partie.getcarteEnCours().setPeutEtreJouee(false);
		}
		if (Partie.getInterfaceGaphiqueActive()) {
			MessageBox.getMessageBox().ajouterMessage(" Les effets de la carte "+Partie.getcarteEnCours().getStrategie().getNom()+ " sont retourn�s sur la Divinit� qui l�a pos�e.");
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(nom + "\n");
		sb.append("Retourne les effets d�une carte d�Action sur la Divinit� qui l�a pos�e.");
		return sb.toString();
	}
	public String getNom() {
		return nom;
	}

}
