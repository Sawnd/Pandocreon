/**
 * 
 */
package elementsDeBase;
import capaciteSacrifice.*;
/**
 * La classe DeusEx représente un des types de cartes du jeu
 * Elle hérite  de la classe CarteAction et implémente ses méthodes {@link: elementsDeBase.CarteAction}
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx extends CarteAction{

	/**
	 * Le constructeur permet ici de créer des cartes DeusEx en passant en paramètres tous les attributs
     * Elle fait appel à un des constructeurs de sa classe-mère CarteAction
	 */
	public DeusEx(TypeOrigine origine,CapaciteSacrifice capacite,int id) {
		super(capacite,origine,id);
	}
	
	
	/**
     * deuxième constructeur de la carte
     * Permet d'avoir des cartes ne possedant pas de dogmes
     * @param origine valeur à affecter à l'origine de la carte
     * @param id valeur à affecter l'identifiant de la carte
     * fait appel au constructeur de la classe dont elle hérite
     */
	
	public DeusEx(CapaciteSacrifice capacite,int id){
		super(capacite,id);
	}
	
	
	/**
	 * La méthode toString() permet d'afficher au joueur les attributs de sa carte
	 * elle fait appel à la méthode toString() de la classe CarteAction et affiche ne plus des caractéristiques propres aux cartes DeusEx
	 */
	public String toString(){
	StringBuffer sb=new StringBuffer();	
	sb.append("\n Deus Ex \n");
    sb.append(super.toString());
		
		return sb.toString();
	}
	
	
	/**
	 * permet de jouer une carte 
	 */
	public void jouerCarte(){
		/**
		 * appelle la méthode seSacrifier() du sacrifice de la carte si la carte peut être sacrifiée
		 */
		if (this.peutEtreSacrifiee){
		this.sacrifice.seSacrifier();}
	}

}
