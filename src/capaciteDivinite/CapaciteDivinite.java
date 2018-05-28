package capaciteDivinite;
/**
 * L'interface permet de d�finir un patron de conception strategie afin de changer dynamiquement la capacit� sp�ciale de sacrifice d'une divinit�
 * 
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *
 */
public interface CapaciteDivinite {
	/**
	 *  Cette m�thode permet d'utiliser la capacit� de la divinit� au cours d'une partie
	 * Chaque classe h�ritant de cette interface doit implementer cette m�thode
	 */
	public void utiliserCapacite();
	
	/**
	 * Cette m�thode permet un affichage du pouvoir de la divinit�
	 * Chaque classe h�ritant de cette interface doit implementer cette m�thode
	 */
	public String toString(); 
}
 