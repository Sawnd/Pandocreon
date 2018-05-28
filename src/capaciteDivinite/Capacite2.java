package capaciteDivinite;

import elementsDeBase.*;
/**
 * La classe Capacité2 représente l'implémentation de la capacité spéciale de la divivnité "Brewalen"
 * la classe implémente l'interface CapaciteDivinite
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Capacite2 implements CapaciteDivinite {
	
	/**
	 * nom de la divinité qui bénéficie de cette capacité spéciale 
	 */
	private String nom = "Brewalen";

	/**
	 * contructeur de la classe
	 */
	public Capacite2() {

	}

	/**
	 * Cette méthode permet d'utiliser la capacité spéciale de la divinité au cours d'une partie
	 * Elle empêche l'utilisation d'une carte Apocalypse en réinitialisant le compteur de carte Apocalypses de la partie
	 */
	public void utiliserCapacite() {
		Partie.resetCompteurApo();
	}

	/**
	 * Cette méthode permet un affichage dans la console du pouvoir de la divinité
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(
				nom + "\n : Peut empêcher l’utilisation d’une carte Apocalypse. La carte est défaussée.");
		return sb.toString();
	}
	
	/**
	 * Cette méthode permet de récupérer l'attribut nom de la classe
	 * @return le nom de la divinité associée à la capacité
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette methode permet de modifier l'attribut nom de la classe
	 * @param nom est la valeur qu'on souhaie attribuer à l'attribut nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

}
