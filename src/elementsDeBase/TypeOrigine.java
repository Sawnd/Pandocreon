package elementsDeBase;

/**
 * Enum�ration des differentes origines des cartes
 * @author Anne-Sophie Ndoumbe
 * @author Steohane Deffon
 *
 */
public enum TypeOrigine {
JOUR("Jour"),
NUIT("Nuit"),
NEANT("N�ant"),
AUBE("Aube"),
CREPUSCULE("Cr�puscule"),
NULL("");


/**
 * nom de l'�numeration
 */
private String nom="";

/**
 * Permet de modifier la valeur de m'attribut nom
 * @param nom , valeu r� affecter � l'attribut
 */
TypeOrigine(String nom){
	this.nom=nom;
}

/**
 * permet d'afficher l'attribut nom de l'�num�ration
 * @return le nom de l'�num�ration
 */
public String toString() {
	return nom;
}
}