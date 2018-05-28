/**
 * 
 */
package strategies;
import java.util.LinkedList;

import elementsDeBase.*;

/**
 * L'interface permet de definir un patron de conception strategie afin de changer dynamiquement la strategie des joueurs virtuels
 * 
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *
 */
public interface Strategie {

	/**
	 * Methode permettant aux joueurs virtuels d'ajouter des croyants au centre
	 * @param centre designe le centre de table
	 * @param j designe le joueur virtuel es question
	 */
	public void mettreCroyantAuCentre(CentreTable centre, JoueurVirtuel j);
	
	/**
	 * permet au joueur virtuel  de se defausser des cartes
	 * @param main designe la main du joueur virtuel
	 */
	public void defausser(LinkedList<CarteAction> main);
	
	/**
	 * permet de deployer la strategie du joueur 
	 */
	public void deployerStrategie();
}
