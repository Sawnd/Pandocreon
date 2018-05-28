package elementsDeBase;

/**
 * Enum�ration des differents dogmes des cartes
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
 * nom de l'�numeration
 */
private String nom="";

/**
 * Permet de modifier la valeur de m'attribut nom
 * @param nom , valeu r� affecter � l'attribut
 */
TypeDogme(String nom){
	this.nom=nom;
 }

/**
 * permet d'afficher l'attribut nom de l'�num�ration
 * @return le nom de l'�num�ration
 */
public String toString(){
	return nom;
}

}
