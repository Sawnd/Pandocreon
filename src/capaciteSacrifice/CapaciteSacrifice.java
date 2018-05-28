package capaciteSacrifice;

/**
 * L'interface permet de définir un patron de conception strategie afin de changer dynamiquement la capacité spéciale de sacrifice des cartes d'action
 * 
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *
 */
public interface CapaciteSacrifice {
	
	/**
	 *  Cette méthode permet d'utiliser la capacité de sacrifice d'une carte et de la défausser 
	 * Chaque classe héritant de cette interface doit implementer cette méthode
	 */	
public void seSacrifier(); 

/**
 * Cette méthode permet un affichage de la capacité de sacrifice de la carte d'action
 * Chaque classe héritant de cette interface doit implementer cette méthode
 */
public String toString(); 

/**
 * Cette méthode permet de récupérer l'attribut nom de la classe
 * @return le nom de la carte d'action associée à la capacité de sacrifice
 */
public String getNom();
}
