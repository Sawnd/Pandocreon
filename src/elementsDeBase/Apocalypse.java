/**
 * 
 */
package elementsDeBase;

import capaciteSacrifice.CapaciteSacrifice;

/**
 * La classe Apocalypse repr�sente un des types de cartes du jeu
 * Elle h�rite  de la classe CarteAction et impl�mente ses m�thodes {@link: elementsDeBase.CarteAction}
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Apocalypse extends CarteAction {
/**
 * constructeur de la classe
 * @param sacrifice repr�sente la capacit� de sacrifice de la carte
 * @param id permet d'identifier la carte
 */
	public Apocalypse(CapaciteSacrifice sacrifice,int id) {
		super(sacrifice,id);
	}
	
/**
 *  constructeur de la classe
 *  Permet d'avoir une carte possedant une origine
 * @param origine origine de la carte
 * @param sacrifice, capacit� de sacrifice de la carte
 * @param id, permet d'identifier la carte
 */
	public Apocalypse(TypeOrigine origine,CapaciteSacrifice sacrifice,int id) {
		super(origine,sacrifice,id);
	}
	
	/**
	 * permet de r�cup�rer le nombre de joueurs de la partie
	 * @return un entier contenant le noombre de joueurs
	 */
	public int getNombreDeJoueurs() {
		return Partie.listeJoueurs.size();
	}

	/**
	 * permet de jouer une carte 
	 */
	public void jouerCarte() {
		/**
		 * appelle la m�thode seSacrifier() du sacrifice de la carte
		 */
		this.sacrifice.seSacrifier();
	}

	/**
	 * Cette m�thode permet un affichage des attributs de la carte
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Apocalypse " + this.origines);
		return sb.toString();
	}
}
