package capaciteSacrifice;

import elementsDeBase.Partie;
import elementsDeBase.TypeOrigine;

import java.util.ListIterator;

import elementsDeBase.Joueur;
/**
 * La classe sacrifice1 représente l'implémentation de la capacité spéciale de sacrifice de cartes Croyant
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice1 implements CapaciteSacrifice {

	/**
	 * nom de la  capacité spéciale 
	 */
	private String nom;
	
	/**
	 * origine de la carte
	 */
	private TypeOrigine origine;
	

	/**
	 * contructeur de la classe
	 * @param nom représente la valeur à affecter à l'attribut nom et conditionne la valeur à affecter à l'attribut origine
	 */
	public Sacrifice1(String nom) {
		switch (nom) {
		case "Moines":
			origine = TypeOrigine.JOUR;
			this.nom = nom;
			break;
		case "Démons":
			origine = TypeOrigine.NUIT;
			this.nom = nom;
			break;
		case "Esprits":
			origine = TypeOrigine.NEANT;
			this.nom = nom;
			break;
		default:
			origine = TypeOrigine.JOUR;
			this.nom = "Moines";
			break;
		}
	}

	/**
	 * Cette méthode donne au joueur qui l'a sacrifiée un point d'action d'origine de la carte
	 * implémentation de la méthode seSacriifer() définie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {
		Joueur j = Partie.getjoueurEnCours();
		ListIterator<Joueur> it = Partie.listeJoueurs.listIterator();
		while (it.hasNext()) {
			Joueur j1 = it.next();
			if (j1 == j) {

				switch (origine) {
				case JOUR:
					j1.setpointsJour(1);
					break;
				case NUIT:
					j1.setpointsNuit(1);
					break;
				case NEANT:
					j1.setpointsNeant(1);
				case AUBE:
					break;
				case CREPUSCULE:
					break;
				case NULL:
					break;
				default:
					break;
				}

			}
		}
	}
	
	/**
	 * Cette méthode permet de récupérer l'attribut nom de la classe
	 * @return le nom de la carte d'action associée à la capacité de sacrifice
	 * implémentation de la méthode getNom() définie dans l'interface CapaciteSacrifice
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette méthode permet un affichage de la capacité de sacrifice de la carte d'action
	 * implémentation de la méthode  toString() définie dans l'interface CapaciteSacrifice
	 */
public String toString(){
	StringBuffer sb =new StringBuffer(nom+"\n");
	sb.append("Donne un point d’action d’Origine "+origine);
	return sb.toString();
}
}
