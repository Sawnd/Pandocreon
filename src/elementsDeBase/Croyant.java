/**
 * 
 */
package elementsDeBase;

import capaciteSacrifice.CapaciteSacrifice;
/**
 * La classe Croyant represente un des types de cartes du jeu
 * Elle herite  de la classe CarteAction et implemente ses methodes {@link: elementsDeBase.CarteAction}
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Croyant extends CarteAction {
	
	/**
	 *  Indique le nombre de croyants que cette carte rapporte
	 */
public final int NBCROYANTS; 

/**
 *  Indique le guide spirituel qui guide cette carte
 */
private GuideSpirituel guide=null;  

/**
 *  booleen permettant de savoir si la carte peut etre guidee
 */
private boolean peutEtreGuidee=false;

/**
 * Le constructeur permet ici de creer des cartes croyants en passant en parametres tous les attributs
 * Elle fait appel e un des constructeurs de sa classe-mere CarteAction
 * @param origine  valeur e affecter e l'origine de la carte
 * @param dogme1 valeur du premier dogme de la carte
 * @param dogme2 valeur du deuxieme dogme de la carte
 * @param dogme3 valeur du troisieme dogme de la carte
 * @param nbCroyants valeur de l'attribut NBCROYANTS
 * @param capacite
 */
public Croyant(TypeOrigine origine, TypeDogme dogme1, TypeDogme dogme2,TypeDogme dogme3,int  nbCroyants, CapaciteSacrifice capacite,int id) {
		super(origine,dogme1,dogme2,dogme3,capacite,id);
		this.NBCROYANTS=nbCroyants;
	}


/**
 * La methode toString() permet d'afficher au joueur les caracteristiques de ses cartes Croyant
 * Cette methode fait appel e la methode toString() definie dans la classe CarteAction et rajoute l'affichage des attrinuts propres e une carte Croyant
 */
public String toString(){
	StringBuffer sb = new StringBuffer();
	sb.append("\n"+"Croyant \n");
	sb.append(super.toString());
	sb.append("\n"+"Nombre de croyants :"+NBCROYANTS+"");
	if (guide!=null)
		sb.append("\n"+"Le croyant est guide par: "+guide+"\n");
			return sb.toString();


	}

/**
 * Permet de recuperer l'origine de la carte
 * @return l'origine de la carte
 */
public TypeOrigine getorigines(){
	return this.origines;
}

/**
 * permet d'affecter une valeur e l'attribut peutEtreGuidee
 * @param booleen contient la valeur booleenne e affecter
 */
 public void setCroyantPeutGuide(){
	 this.peutEtreGuidee=true;
 }
 
 /**
	 * Permet de recuperer la valeur de l'attribut peutEtreGuidee
	 * @return la valeur de l'attribut en question
	 */
public boolean getpeutEtreGuidee(){
	return this.peutEtreGuidee;
}


/**
 * Methode  permettant de jouer une carte
  */
public void jouerCarte(){ 
	/**
	 * appelle la methode seSacrifier() du sacrifice de la carte
	 */
	this.sacrifice.seSacrifier();
}
}