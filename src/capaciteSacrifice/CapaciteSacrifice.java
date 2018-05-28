package capaciteSacrifice;

/**
 * L'interface permet de d�finir un patron de conception strategie afin de changer dynamiquement la capacit� sp�ciale de sacrifice des cartes d'action
 * 
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *
 */
public interface CapaciteSacrifice {
	
	/**
	 *  Cette m�thode permet d'utiliser la capacit� de sacrifice d'une carte et de la d�fausser 
	 * Chaque classe h�ritant de cette interface doit implementer cette m�thode
	 */	
public void seSacrifier(); 

/**
 * Cette m�thode permet un affichage de la capacit� de sacrifice de la carte d'action
 * Chaque classe h�ritant de cette interface doit implementer cette m�thode
 */
public String toString(); 

/**
 * Cette m�thode permet de r�cup�rer l'attribut nom de la classe
 * @return le nom de la carte d'action associ�e � la capacit� de sacrifice
 */
public String getNom();
}
