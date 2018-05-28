package capaciteSacrifice;

import elementsDeBase.Partie;
import elementsDeBase.TypeOrigine;


/**
 * La classe DeusEx8 repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice de la carte DeusEx " Influence"
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx8 implements CapaciteSacrifice {
	
	
	/**
	 * nom de la  capacit� sp�ciale 
	 */ private String 
	 nom = "Influence";
	
	/**
	 * premiere origine possible de la carte possedant la capacit� de sacrifice
	 */
	private TypeOrigine origine = TypeOrigine.AUBE;
	
	/**
	 * deuxi�me origine possible de la carte possedant la capacit� de sacrifice
	 */
	private TypeOrigine origine2 = TypeOrigine.NEANT;
	
/**
 * constructeur de la classe
 * @param origine permet modifier l'attribut origine 
 */
	public DeusEx8(TypeOrigine origine) {
		this.origine = origine;
	}
/**
 * constructeur de la classe
 */
	public DeusEx8() {

	}

	/**
	 * Cette m�thode annule la capacit� sp�ciale d'une autre carte d'Action
	 * impl�mentation de la m�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {
		switch (origine) {
		case AUBE:
			if(Partie.getcarteEnCours()!=null){ 
			Partie.getcarteEnCours().setPeutEtreJouee(false);
			break;}
		default:
			if (Partie.getcarteEnCours()!=null&&(Partie.getcarteEnCours().getorigine() == origine || Partie.getcarteEnCours().getorigine() == origine2)) {
				Partie.getcarteEnCours().setPeutEtreJouee(false);
			}
			break;
		}
		
	}

	/**
	 * Cette m�thode permet de r�cup�rer l'attribut nom de la classe
	 * @return le nom de la carte d'action associ�e � la capacit� de sacrifice
	 * impl�mentation de la m�thode getNom d�finie dans l'interface CapaciteSacrifice
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette m�thode permet un affichage de la capacit� de sacrifice de la carte d'action
	 * impl�mentation de la m�thode  toString() d�finie dans l'interface CapaciteSacrifice
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(nom);
		if (origine == TypeOrigine.AUBE) {
			sb.append(" Nulle \n");
			sb.append("Annule la capacit� sp�ciale d'une autre carte d'Action");
		} else {
			sb.append(" \n Annule la capacit� sp�ciale d'une carte d�Action Origine " + origine + " ou N�ant");
		}
		return sb.toString();
	}
	
}
