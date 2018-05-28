package capaciteSacrifice;

import java.util.Iterator;

import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import elementsDeBase.TypeDogme;
import interfaceGraphique.MessageBox;

/**
 * La classe sacrifice2 représente l'implémentation de la capacité spéciale de sacrifice de cartes Croyant
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice2 implements CapaciteSacrifice {
	/**
	 * nom de la  capacité spéciale 
	 */
	private String nom;
	
	/**
	 * dogmes de la carte
	 */
	private TypeDogme dogme1, dogme2;

	public Sacrifice2(String nom, TypeDogme dogme1, TypeDogme dogme2) {
		this.nom = nom;
		this.dogme1 = dogme1;
		this.dogme2 = dogme2;
	}


	/**
	 * Cette méthode empêche une divinité possedan les même dogmes que la capacité de sacrifier une de ses cartes croyants durant ce tour
	 * Les capacités spéciales sont jouées normalement.
	 * implémentation de la méthode seSacriifer() définie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {
		Joueur j = Partie.getjoueurEnCours();
		int compteur = 3;
		Joueur jAdverse;
		do {
			jAdverse = j.choisirUnJoueur();
			if (compteur > 3) {
				if (!Partie.getInterfaceGaphiqueActive()) {
				System.out.println("Choisissez une divinté posédant les dogmes " + dogme1 + " ou " + dogme2);
				}else{
					MessageBox.getMessageBox().ajouterMessage("Choisissez une divinté posédant les dogmes " + dogme1 + " ou " + dogme2);
				}
			}
			compteur--;
		} while (!jAdverse.croitAuDogme(dogme1) && jAdverse.divCroitAuDogme(dogme2) && compteur > 0);
		Iterator<Joueur> it =Partie.listeJoueurs.iterator();
		Joueur j1;
		while(it.hasNext()){
			j1=it.next();
			if(j1==jAdverse){
				j1.setPeuventEtreSacrifiees(false);
			}
		}

	}
	
	/**
	 * Cette méthode permet de récupérer l'attribut nom de la classe
	 * @return le nom de la carte d'action associée à la capacité de sacrifice
	 * implémentation de la méthode getNom() définie dans l'interface CapaciteSacrifice
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette méthode permet un affichage de la capacité de sacrifice de la carte d'action
	 * implémentation de la méthode  toString() définie dans l'interface CapaciteSacrifice
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(nom + "\n");
		sb.append(": Empêche une divinité possédant le Dogme " + dogme1 + " ou " + dogme2
				+ "+ de sacrifier une de ses cartes de Croyants durant ce tour ");
		return sb.toString();
	}

}
