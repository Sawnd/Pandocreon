package elementsDeBase;

/**
 * Enumération des differents dogmes des cartes
 * @author Anne-Sophie Ndoumbe
 * @author Steohane Deffon
 *
 */
public enum TypeDogme {
MYSTIQUE("Mystique"),
CHAOS("Chaos"),
HUMAIN("Humain"),
SYMBOLE("Symbole"),
NATURE("Nature"),
NULL("");

/**
 * nom de l'énumeration
 */
private String nom="";

/**
 * Permet de modifier la valeur de m'attribut nom
 * @param nom , valeu rà affecter à l'attribut
 */
TypeDogme(String nom){
	this.nom=nom;
 }

/**
 * permet d'afficher l'attribut nom de l'énumération
 * @return le nom de l'énumération
 */
public String toString(){
	return nom;
}

}
