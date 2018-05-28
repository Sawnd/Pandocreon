package capaciteSacrifice;

import java.util.*;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageBox;
import elementsDeBase.CarteAction;

/**
 * La classe sacrifice3 représente l'implémentation de la capacité spéciale de sacrifice de cartes Croyant
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice3 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacité spéciale 
	 */
	private String nom;

	/**
	 * contructeur de la classe
	 * @param nom représente la valeur à affecter à l'attribut nom
	 */
	public Sacrifice3(String nom) {
		this.nom = nom;
	}

	/**
	 * Cette méthode permet au joueur en cours de choisir un adversaire et de piocher de cartes au hasard dans sa main
	 * implémentation de la méthode seSacriifer() définie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {

		Joueur j1 = Partie.getjoueurEnCours();
		CarteAction c1;
		LinkedList<CarteAction> mainJ;
		LinkedList<CarteAction> main;
		Joueur j;
		if (!Partie.getInterfaceGaphiqueActive()) {
		 j = j1.choisirUnJoueur();
		}else{
			j = FenetrePrincipale.choisirUnJoueur();
		}
		main = j1.getmain();
		mainJ = j.getmain();
		for (int i = 0; i < 2; i++) {
			if (mainJ.isEmpty()) {
				break;
			}
			int num = (int) Math.round(Math.random() * mainJ.size());
			if (!mainJ.isEmpty()) {
				c1 = mainJ.remove(num - 1);
				main.add(c1);
			}else{
				if (!Partie.getInterfaceGaphiqueActive()) {
				System.out.println("La main du joueur est vide");
				}else{
					MessageBox.getMessageBox().ajouterMessage("La main du joueur est vide");
				}
			}
		}
		j.setmain(mainJ);
		ListIterator<Joueur> it = Partie.listeJoueurs.listIterator();
		Joueur j2;
		while (it.hasNext()) {
			j2 = it.next();
			if (j2 == j1) {
				j2.setmain(j1.getmain());
			}
			if (j2 == j) {
				j2.setmain(j.getmain());
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
		sb.append(" Vous piochez deux cartes au hasard dans la main d'une autre Divinité");
		return sb.toString();
	}

}
