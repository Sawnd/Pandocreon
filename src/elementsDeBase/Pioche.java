/**
 * 
 */
package elementsDeBase;
import java.util.*;
/**
 * La classe pioche abstraite est une clsse repr�sentant un tas de carte
 *
 */
public abstract class Pioche {

	/**
	 * collection contenant les cartes de la pioche
	 */
	private LinkedList<Carte> pioche;
	
	
	/**
	 * constructeur de la classe
	 */
	public Pioche() {
		
	}
	
	
	/**
	 * m�thode pour tirer la carte du dessus de la pioche
	 * @return la carte tir�e
	 */
	public Carte tirerCarteduDessus(){ 
		return pioche.pop();
	}
	
	/**
	 * methode pour tirer une carte de la pioche
	 * @return la carte tir�e
	 */
	public Carte tirerCarte(){ 
		/**
		 * on choisit un nombre al�atoire inf�rieur � la dimenssion de la collection
		 */
		int position=(int)Math.round((Carte.NOMBRE_DE_CARTES-1)*Math.random());
		return pioche.remove(position);
	}
	
	/**
	 * permet d'ajouter une carte � la pioche
	 * @param carte
	 */
    public void ajouterCarte(Carte carte){
    	pioche.add(carte);
    }
    
}
