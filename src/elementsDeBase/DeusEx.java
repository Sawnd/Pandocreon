/**
 * 
 */
package elementsDeBase;
import capaciteSacrifice.*;
/**
 * La classe DeusEx repr�sente un des types de cartes du jeu
 * Elle h�rite  de la classe CarteAction et impl�mente ses m�thodes {@link: elementsDeBase.CarteAction}
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx extends CarteAction{

	/**
	 * Le constructeur permet ici de cr�er des cartes DeusEx en passant en param�tres tous les attributs
     * Elle fait appel � un des constructeurs de sa classe-m�re CarteAction
	 */
	public DeusEx(TypeOrigine origine,CapaciteSacrifice capacite,int id) {
		super(capacite,origine,id);
	}
	
	
	/**
     * deuxi�me constructeur de la carte
     * Permet d'avoir des cartes ne possedant pas de dogmes
     * @param origine valeur � affecter � l'origine de la carte
     * @param id valeur � affecter l'identifiant de la carte
     * fait appel au constructeur de la classe dont elle h�rite
     */
	
	public DeusEx(CapaciteSacrifice capacite,int id){
		super(capacite,id);
	}
	
	
	/**
	 * La m�thode toString() permet d'afficher au joueur les attributs de sa carte
	 * elle fait appel � la m�thode toString() de la classe CarteAction et affiche ne plus des caract�ristiques propres aux cartes DeusEx
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
		 * appelle la m�thode seSacrifier() du sacrifice de la carte si la carte peut �tre sacrifi�e
		 */
		if (this.peutEtreSacrifiee){
		this.sacrifice.seSacrifier();}
	}

}
