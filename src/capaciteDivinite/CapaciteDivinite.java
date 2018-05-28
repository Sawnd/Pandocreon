package capaciteDivinite;
/**
 * L'interface permet de définir un patron de conception strategie afin de changer dynamiquement la capacité spéciale de sacrifice d'une divinité
 * 
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *
 */
public interface CapaciteDivinite {
	/**
	 *  Cette méthode permet d'utiliser la capacité de la divinité au cours d'une partie
	 * Chaque classe héritant de cette interface doit implementer cette méthode
	 */
	public void utiliserCapacite();
	
	/**
	 * Cette méthode permet un affichage du pouvoir de la divinité
	 * Chaque classe héritant de cette interface doit implementer cette méthode
	 */
	public String toString(); 
}
 