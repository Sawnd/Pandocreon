package capaciteSacrifice;

import elementsDeBase.Partie;
import elementsDeBase.TypeOrigine;

import java.util.ListIterator;

import elementsDeBase.Joueur;
/**
 * La classe sacrifice1 repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice de cartes Croyant
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice1 implements CapaciteSacrifice {

	/**
	 * nom de la  capacit� sp�ciale 
	 */
	private String nom;
	
	/**
	 * origine de la carte
	 */
	private TypeOrigine origine;
	

	/**
	 * contructeur de la classe
	 * @param nom repr�sente la valeur � affecter � l'attribut nom et conditionne la valeur � affecter � l'attribut origine
	 */
	public Sacrifice1(String nom) {
		switch (nom) {
		case "Moines":
			origine = TypeOrigine.JOUR;
			this.nom = nom;
			break;
		case "D�mons":
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
	 * Cette m�thode donne au joueur qui l'a sacrifi�e un point d'action d'origine de la carte
	 * impl�mentation de la m�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
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
	 * Cette m�thode permet de r�cup�rer l'attribut nom de la classe
	 * @return le nom de la carte d'action associ�e � la capacit� de sacrifice
	 * impl�mentation de la m�thode getNom() d�finie dans l'interface CapaciteSacrifice
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette m�thode permet un affichage de la capacit� de sacrifice de la carte d'action
	 * impl�mentation de la m�thode  toString() d�finie dans l'interface CapaciteSacrifice
	 */
public String toString(){
	StringBuffer sb =new StringBuffer(nom+"\n");
	sb.append("Donne un point d�action d�Origine "+origine);
	return sb.toString();
}
}
