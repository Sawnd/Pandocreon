/**
 * 
 */
package elementsDeBase;
import java.util.*;

import interfaceGraphique.MessageBox;

/**
* La classe Carte est une classe representant un joueur  physique
* @author Anne-Sophie Ndoumbe
* @author Stephane Deffon
* Elle herite de le la classe joueur
*/
public class JoueurPhysique extends Joueur {

	/**
	 * Constructeur de la classe
	 */
public JoueurPhysique() {
	super();
	System.out.println("Nom du joueur "+numeroJoueur+" ?");
	@SuppressWarnings("resource")
	Scanner sc=new Scanner(System.in);
	String str=sc.nextLine();
	this.nom =str;
	
}
/**
 * constructeur de la classe
 * @param nom permettant de modifier le nom du joueur
 */
public JoueurPhysique(String nom){
	super();
	this.nom=nom;
}

/**
 * Permet de modifier la valeur de l'attribut divinite
 * @param div valeur e affecter e l'attribut
 */
public void setDivinite(Divinite div){
super.setDivinite(div);
}

/**
 * permet de modifier la valeur des points Jour 
 * @param point valeur e affecter
 */
public void setpointsJour(int point){
super.setpointsJour(point);
}

/**
 * permet de modifier la valeur des points Nuit 
 * @param point valeur e affecter
 */
	
public void setpointsNuit(int point){
	super.setpointsNuit(point);
}

/**
 * permet de modifier la valeur des points Neant
 * @param point valeur e affecter
 */
	
public  void setpointsNeant(int point){
super.setpointsNeant(point);
}

/**
 * permet de modifier la valeur des points de priere
 * @param point valeur e affecter
 */
	
public  void setpointsPriere(int point){
	super.setpointsPriere(point);
	}

/**
 * permet d'ajouter une care e la main du joueur
 * @param carte carte e ajouter
 */
public void ajouterCarte(CarteAction carte){
	super.ajouterCarte(carte);
}
 
/**
 * Methode permettant d'afficher les guides spirituels recuperes
 */
public void afficherCartesRecuperees(){
	super.afficherCartesRecuperees();
}
/**
 * Methode permettant de recuperre la divinite du joueur
 * @return la divinite
 */
public Divinite getDivinite(){
	return super.getDivinite();
}

/**
 * Methode permettant de recuperer les points jour du joueur
 * @return les points jour
 */
public int getPointsJour() {
return super.getPointsJour();
}

/**
 * Methode permettant de recuperer les points nuit  du joueur
 * @return les points nuit
 */
public int getPointsNuit() {
return super.getPointsNuit();
}

/**
 * Methode permettant de recuperer les points de priere du joueur
 * @return les points de priere
 */
public int getPointsPriere() {
	return super.getPointsPriere();
	}
/**
 * Methode permettant de recuperer les points neant du joueur
 * @return les points neant
 */
public int getPointsNeant() {
return super.getPointsNeant();
}

/**
 * Methode permettant de recuperer le nom du joueur
 * @return le nom du joueur
 */
public String getNom() {
return super.getNom();
}

/**
 * Methode permettant de recuperer la main du joueur
 * @return la main du joueur
 */
public LinkedList<CarteAction> getmain() {
return super.getmain();
}

/**
 * Methode permettant de recuperer un dogme de la divinie du joueur
 * @param indice du dogme 
 * @return le dogme recupere
 */
public TypeDogme getDogmeDivinite(int i){
return super.getDogmeDivinite(i);
}

/**
 * Methode permettant de modifier la main du joueur
 * @param main valeur e affecter e la main
 */

public void setmain(LinkedList<CarteAction> main) {
super.setmain(main);
}

/**
 * Methode permettant de recuperer les guides spirituels 
 * @return les guides recuperes
 */
public LinkedList<GuideSpirituel> getcartesRecuperees() {
return super.getcartesRecuperees();
}

/**
 * Permet de modifier la valeur des cartes recuperees
  *@param cartesRec valeur e affecter
 */
public void setcartesRecuperees(LinkedList<GuideSpirituel> cartesRec) {
super.setcartesRecuperees(cartesRec);
}

/**
 * 	m	ethode permettant de modifier l'attribut aPioche
 */
public void setaPioche() {
super.setaPioche();
}
/**
 * Permet de recuperer les points de prieres total du joueur
 * @return les points de priere
 */
public int pointsDePriereTotal() {
	return super.pointsDePriereTotal();
}
/**
 * Methode permettant de recuperre le nombre dr croyants rattaches e un guide
 * @return le nombre recupere
 */
public int nbCroyantsRattachesTotal(){
	return super.nbCroyantsRattachesTotal();
}

/**
 * Permet d'afficher les points de priere du joueur
 */
public void afficherPointsDePrieres() {
	super.afficherPointsDePrieres();
}

/**
 * Methode permettant de savoir si un guide spirituel croit e un dogme
 * @param dogme e verifier
 * @return booleen permettant de savoir si le guide croit au dogme ou pas
 */
public boolean croitAuDogme(TypeDogme dogme){
	return super.croitAuDogme(dogme);
}
/**
 * Methode permettant de savoir si une divinite croit e un dogme
 * @param dogme e verifier
 * @return booleen permettant de savoir si la divinite croit au dogme ou pas
 */
public boolean divCroitAuDogme(TypeDogme dogme){
	return super.divCroitAuDogme(dogme);
}

/**
 * 	Methode permettant au joueur de jouer 
 * @param i entier permettant de compter le nombre de tours
 * @param partie contenant l'instance unique de la partie
 */
public int jouer(int i, Partie partie){

	MessageBox.getMessageBox().ajouterMessage("\n"+"C'est au tour de "+nom+" de jouer!"+"\n");
    this.afficherLesPoints();

	this.setaPioche();
	return super.jouer(i, partie);
	
}

/**
 * Permet d'afficher le nom du joueur
 */
public String toString() {
		return super.toString();
}
	
}
