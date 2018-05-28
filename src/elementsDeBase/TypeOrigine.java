package elementsDeBase;

/**
 * Enumération des differentes origines des cartes
 * @author Anne-Sophie Ndoumbe
 * @author Steohane Deffon
 *
 */
public enum TypeOrigine {
JOUR("Jour"),
NUIT("Nuit"),
NEANT("Néant"),
AUBE("Aube"),
CREPUSCULE("Crépuscule"),
NULL("");


/**
 * nom de l'énumeration
 */
private String nom="";

/**
 * Permet de modifier la valeur de m'attribut nom
 * @param nom , valeu rà affecter à l'attribut
 */
TypeOrigine(String nom){
	this.nom=nom;
}

/**
 * permet d'afficher l'attribut nom de l'énumération
 * @return le nom de l'énumération
 */
public String toString() {
	return nom;
}
}