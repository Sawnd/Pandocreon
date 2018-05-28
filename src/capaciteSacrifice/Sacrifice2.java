package capaciteSacrifice;

import java.util.Iterator;

import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import elementsDeBase.TypeDogme;
import interfaceGraphique.MessageBox;

/**
 * La classe sacrifice2 repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice de cartes Croyant
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice2 implements CapaciteSacrifice {
	/**
	 * nom de la  capacit� sp�ciale 
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
	 * Cette m�thode emp�che une divinit� possedan les m�me dogmes que la capacit� de sacrifier une de ses cartes croyants durant ce tour
	 * Les capacit�s sp�ciales sont jou�es normalement.
	 * impl�mentation de la m�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {
		Joueur j = Partie.getjoueurEnCours();
		int compteur = 3;
		Joueur jAdverse;
		do {
			jAdverse = j.choisirUnJoueur();
			if (compteur > 3) {
				if (!Partie.getInterfaceGaphiqueActive()) {
				System.out.println("Choisissez une divint� pos�dant les dogmes " + dogme1 + " ou " + dogme2);
				}else{
					MessageBox.getMessageBox().ajouterMessage("Choisissez une divint� pos�dant les dogmes " + dogme1 + " ou " + dogme2);
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
	 * Cette m�thode permet de r�cup�rer l'attribut nom de la classe
	 * @return le nom de la carte d'action associ�e � la capacit� de sacrifice
	 * impl�mentation de la m�thode getNom() d�finie dans l'interface CapaciteSacrifice
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette m�thode permet un affichage de la capacit� de sacrifice de la carte d'action
	 * impl�mentation de la m�thode  toString() d�finie dans l'interface CapaciteSacrifice
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(nom + "\n");
		sb.append(": Emp�che une divinit� poss�dant le Dogme " + dogme1 + " ou " + dogme2
				+ "+ de sacrifier une de ses cartes de Croyants durant ce tour ");
		return sb.toString();
	}

}
