
package elementsDeBase;

import java.util.Scanner;

import capaciteDivinite.*;
/**
 * La classe Divinite represente un des types de cartes du jeu
 * Elle herite  de la classe Carte et implemente ses methodes {@link: elementsDeBase.Carte}
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Divinite extends Carte {
	
/**
 * nom de la divinite
 */
	private String nom;
	
	/**
	 * Capacité speciale de sacrifice implementée par l'interface CapaciteDivinite
	 */
	private CapaciteDivinite capaciteDivinite; 
	private boolean capaciteUtilisee = false;

	/**
	 *  premier constructeur de la classe
	 *  Permet d'avoir des cartes ne possedant ni dogmes, ni origine
	 * @param id valeur à affecter à l'atttribut id
	 * Elle fait appel au constructeur de la classe dont elle hérite
	 */
	public Divinite(int id){
		super(id);
	}
	
	
	/**
	 * quatrième constructeur de la classe
	 * Permettant d'avoir des cartes possedant une origine et trois dogmes
	 * @param origine valeur à affecter à l'origine de la carte
	 * @param dogme1 valeur du premier dogme de la carte
	 * @param dogme2 valeur du deuxième dogme de la carte
	 * @param dogme3 valeur du troisième dogme de la carte
	 * @param id valeur à affecter à l'identfiant de la carte
	 * Fait appel au constructeur de la classe dont elle hérite
	 */
	public Divinite(String name, CapaciteDivinite capacite, TypeOrigine origine, TypeDogme dogme1, TypeDogme dogme2,
			TypeDogme dogme3,int id) {
		super(origine, dogme1, dogme2, dogme3,id); 
		this.nom = name;
		this.capaciteDivinite = capacite;
	}
 
	
	/**
	 * Methode permettant d'utiliser la capacité spéciale de sacrifice de la divinité si elle n'a pas encore été utilisée
	 */
	public void utiliserCapacite() {
		System.out.println(this.toString());
		System.out.println("Voulez-vous utiliser la capacite? (0 pour valider)"  );
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		if (n==0){
		if (!capaciteUtilisee) {
			this.capaciteDivinite.utiliserCapacite();
			capaciteUtilisee=true;
		} 
		else {
			System.out.println("La capacite a dejà ete utilise!!");
		}}

	}
	
	
	/**
	 * La methode toString() // Permet d'afficher le nom de la divinite, son origine, ses dogmes et sa capacite
	 * 
	 */
	public String toString() { 
		StringBuffer sb = new StringBuffer();
	   sb.append(this.nom + "\n"+"Origine: " + this.origines );
		sb.append( "\n"+"Dogmes: " + this.dogmes[0] + " " + this.dogmes[1] + " "
				+ this.dogmes[2]);
		sb.append("\n"+"Capacite: "+this.capaciteDivinite.toString());
		return sb.toString();
	}	
	
	/**
	 * Permet de récupérer la valeur de l'attribut capaciteUtilisee
	 * @return la valeur en question
	 */
public boolean getCapaciteUtilisee(){
	return this.capaciteUtilisee;
}

}
