package capaciteDivinite;

import elementsDeBase.*;
/**
 * La classe Capacit�2 repr�sente l'impl�mentation de la capacit� sp�ciale de la divivnit� "Brewalen"
 * la classe impl�mente l'interface CapaciteDivinite
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Capacite2 implements CapaciteDivinite {
	
	/**
	 * nom de la divinit� qui b�n�ficie de cette capacit� sp�ciale 
	 */
	private String nom = "Brewalen";

	/**
	 * contructeur de la classe
	 */
	public Capacite2() {

	}

	/**
	 * Cette m�thode permet d'utiliser la capacit� sp�ciale de la divinit� au cours d'une partie
	 * Elle emp�che l'utilisation d'une carte Apocalypse en r�initialisant le compteur de carte Apocalypses de la partie
	 */
	public void utiliserCapacite() {
		Partie.resetCompteurApo();
	}

	/**
	 * Cette m�thode permet un affichage dans la console du pouvoir de la divinit�
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(
				nom + "\n : Peut emp�cher l�utilisation d�une carte Apocalypse. La carte est d�fauss�e.");
		return sb.toString();
	}
	
	/**
	 * Cette m�thode permet de r�cup�rer l'attribut nom de la classe
	 * @return le nom de la divinit� associ�e � la capacit�
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette methode permet de modifier l'attribut nom de la classe
	 * @param nom est la valeur qu'on souhaie attribuer � l'attribut nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

}
