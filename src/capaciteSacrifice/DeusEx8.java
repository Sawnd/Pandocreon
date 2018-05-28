package capaciteSacrifice;

import elementsDeBase.Partie;
import elementsDeBase.TypeOrigine;


/**
 * La classe DeusEx8 représente l'implémentation de la capacité spéciale de sacrifice de la carte DeusEx " Influence"
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx8 implements CapaciteSacrifice {
	
	
	/**
	 * nom de la  capacité spéciale 
	 */ private String 
	 nom = "Influence";
	
	/**
	 * premiere origine possible de la carte possedant la capacité de sacrifice
	 */
	private TypeOrigine origine = TypeOrigine.AUBE;
	
	/**
	 * deuxième origine possible de la carte possedant la capacité de sacrifice
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
	 * Cette méthode annule la capacité spéciale d'une autre carte d'Action
	 * implémentation de la méthode seSacriifer() définie dans l'interface capaciteSacrifice
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
	 * Cette méthode permet de récupérer l'attribut nom de la classe
	 * @return le nom de la carte d'action associée à la capacité de sacrifice
	 * implémentation de la méthode getNom définie dans l'interface CapaciteSacrifice
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette méthode permet un affichage de la capacité de sacrifice de la carte d'action
	 * implémentation de la méthode  toString() définie dans l'interface CapaciteSacrifice
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(nom);
		if (origine == TypeOrigine.AUBE) {
			sb.append(" Nulle \n");
			sb.append("Annule la capacité spéciale d'une autre carte d'Action");
		} else {
			sb.append(" \n Annule la capacité spéciale d'une carte d’Action Origine " + origine + " ou Néant");
		}
		return sb.toString();
	}
	
}
