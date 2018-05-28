/**
 * 
 */
package elementsDeBase;

import capaciteSacrifice.CapaciteSacrifice;

/**
 * La classe CarteAction represente un des types de cartes du jeu
 * Elle herite  de la classe Carte et implemente ses methodes {@link: elementsDeBase.Carte}
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public abstract class CarteAction extends Carte {
	/**
	 *  Cet attribut represente comme son nom l'indique la capacite speciale de sacrifice de la carte d'action 
	 */
	protected CapaciteSacrifice sacrifice;
	
	/**
     *  booleen permettant de savoir si la carte peut être sacrifiee
     */	
protected boolean peutEtreSacrifiee=true;

/**
 *  premier constructeur de la classe
 *  Permet d'avoir des cartes ne possedant ni dogemes, ni origine
 * @param id valeur à affecter à l'atttribut id
 */
	public CarteAction(int id){
		super(id);
	}
	
	/**
     * deuxième constructeur de la carte
     * Permet d'avoir des cartes ne possedant pas de dogmes
     * @param origine valeur à affecter à l'origine de la carte
     * @param id valeur à affecter l'identifiant de la carte
     * fait appel au constructeur de la classe dont elle herite
     */
	
	public CarteAction(TypeOrigine origine, CapaciteSacrifice capacite, int id) {
		super(origine,id);
		this.sacrifice=capacite;
	}
	
	
	
	
	/**
 * Ce constructeur permet de creer des cartes d'action sans dogme, ni origine
 * @param capacite represente le sacrifice de la carte 
 * @param id  valeur à affecter à l'identifiant de la carte
 * fait appel au constructeur de la classe dont elle herite
 */
	public CarteAction(CapaciteSacrifice capacite,int id) {
		super(id);
		this.sacrifice = capacite;
	}
	
	
	/**
	 * Ce constructeur permet de creer des cartes d'action qui n'on qu'un dogme et une capacite
	 * @param capacite represente le sacrifice de la carte 
	 * @param origine valeur à affecter à l'origine de la carte
	 * fait appel au constructeur de la classe dont elle herite
	 */
	public CarteAction(CapaciteSacrifice capacite, TypeOrigine origine,int id) {
		super(id);
		this.origines=origine;
		this.sacrifice = capacite;
	}
	
	
	/**
	 * Ce constructeur permet de creer des cartes avec une origine, deux dogmes et une capacite speciale de sacrifice
	 * @param origine valeur à affecter à l'origine de la carte
	 * @param dogme1 valeur du premier dogme de la carte
	 * @param dogme2  valeur du deuxième dogme de la carte
	 * @param capacite capacite represente le sacrifice de la carte 
	 * @param id valeur à affecter à l'identfiant de la carte
	 * fait appel au constructeur de la classe dont elle herite
	 */
	public CarteAction(TypeOrigine origine, TypeDogme dogme1, TypeDogme dogme2,CapaciteSacrifice capacite,int id){
		super(origine, dogme1,dogme2,id);
		this.sacrifice=capacite;
	}

	
	/**
	 * Ce constructeur permet de creer des cartes d'action avec une origine, trois dogmes, et une capacite speciale de sacrifice
	* @param origine valeur à affecter à l'origine de la carte
	 * @param dogme1 valeur du premier dogme de la carte
	 * @param dogme2 valeur du deuxième dogme de la carte
	 * @param dogme3 valeur du troisième dogme de la carte
	 * @param id valeur à affecter à l'identfiant de la carte
	 * @param capacite represente le sacrifice de la carte 
	 */
	public CarteAction(TypeOrigine origine, TypeDogme dogme1, TypeDogme dogme2,TypeDogme dogme3,CapaciteSacrifice capacite,int id){
		super(origine,dogme1,dogme2,dogme3,id);
		this.sacrifice=capacite;
	}
	
	
	/**
	 * permet d'affecter une valeur à l'attribut peutEtreSacrifiee
	 * @param booleen contient la valeur booleenne à affecter
	 */
	public void setPeutEtreSacrifiee(boolean bool){
		this.peutEtreSacrifiee=bool;
	}
	
	/**
	 * permet de recuperer la capacite de sacrifice associee a la carte
	 * @return la capacite en question
	 */
	public CapaciteSacrifice getStrategie(){
		return sacrifice;
	}
	
	
	/**
	 * Permet de recuperer la valeur de l'attribut peutEtreSacrifiee
	 * @return la valeur de l'attribut en question
	 */
public boolean getPeutEtreSacrifiee(){
	return this.peutEtreSacrifiee;
}

	/**
	 * La methode toString() permet d'afficher les attributs de la carte d'action
	 * Elle est redefinie dans les classes filles
	 */
	public  String toString(){
		
				StringBuffer sb= new StringBuffer();
				sb.append(super.toString());
		sb.append("\n"+"Capacite : "+sacrifice.toString()); 
		return sb.toString();
	}

}
