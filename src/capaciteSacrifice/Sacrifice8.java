package capaciteSacrifice;

import java.util.ListIterator;
import java.util.Scanner;

import elementsDeBase.*;
import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageBox;

/**
 * La classe sacrifice8 represente l'implementation de la capacite speciale de sacrifice de cartes Croyant
 * la classe implemente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice8 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacite speciale 
	 */
	private String nom = "Pillards";

	/**
	 * contructeur de la classe
	 */
	public Sacrifice8() {

	}

	/**
	 * Cette methode permet au joueur en cours de choisir un adversaire et de recuperer ses points d'action
	 * La divinite perd ses points
	 * implementation de la methode seSacriifer() definie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {
		if (!Partie.getInterfaceGaphiqueActive()) {
		System.out.println("Sur quel joueur voulez-vous utiliser votre capacite?");
		}else{
			MessageBox.getMessageBox().ajouterMessage("Sur quel joueur voulez-vous utiliser votre capacite?");
		}
		ListIterator<Joueur> it = Partie.listeJoueurs.listIterator();
		boolean aDejaJoue = true;
		Joueur j1 = Partie.getjoueurEnCours();
		int n;
		int compteur = 0;
		while (it.hasNext()) {
			Joueur j = it.next();
			n = it.nextIndex();
			if (!aDejaJoue) {
				compteur++;
				System.out.println("[" + n + "] " + j);
			}
			if (j == j1) {
				aDejaJoue = false;
			}
		}
		Joueur joueur = null;
		int m;
		if (aDejaJoue) {
			if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println("Tous les joueurs ont deje joue!");
			}else{
				MessageBox.getMessageBox().ajouterMessage("Tous les joueurs ont deje joue");
			}
		} else {
			if (!Partie.getInterfaceGaphiqueActive()) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			 m = sc.nextInt();
			while (m <= (Partie.listeJoueurs.size() - compteur) || m > Partie.listeJoueurs.size()) {
				System.out.println("Veuillez saisir un nombre valide");
				m = sc.nextInt();
				joueur = Partie.listeJoueurs.get(m - 1);
			}
			}else{
				joueur = FenetrePrincipale.choisirUnJoueur();
				if(joueur == Partie.getjoueurEnCours()){
					joueur = FenetrePrincipale.choisirUnJoueur();
					MessageBox.getMessageBox().ajouterMessage("Vous souhaitez appliquer l'effet de la carte sur votre joueur. Veuillez choisisr un autre joueur");
			}
			}
			 
			TypeOrigine origine = Partie.getOrigineDuTour();
			ListIterator<Joueur> it2 = Partie.listeJoueurs.listIterator();
			Joueur j3;
			while (it2.hasNext()) {
				j3 = it2.next();
				if (j3 == joueur) {

					if (j3.getorigineDivinite() == origine && origine == TypeOrigine.JOUR) {
						j3.setpointsJour(-2);
					} else if (j3.getorigineDivinite() == TypeOrigine.AUBE && origine == TypeOrigine.JOUR) {
						j3.setpointsJour(-1);
					} else if (j3.getorigineDivinite() == origine && origine == TypeOrigine.NUIT) {
						j3.setpointsNuit(-2);
					} else if (j3.getorigineDivinite() == TypeOrigine.CREPUSCULE && origine == TypeOrigine.NUIT) {
						j3.setpointsNuit(-1);

					} else if (j3.getorigineDivinite() == TypeOrigine.CREPUSCULE && origine == TypeOrigine.NEANT) {
						j3.setpointsNeant(-1);
					} else if (j3.getorigineDivinite() == TypeOrigine.AUBE && origine == TypeOrigine.NEANT) {
						j3.setpointsNeant(-1);
					}
				}
				if (j3 == j1) {
					if (j3.getorigineDivinite() == origine && origine == TypeOrigine.JOUR) {
						j3.setpointsJour(2);
					} else if (j3.getorigineDivinite() == TypeOrigine.AUBE && origine == TypeOrigine.JOUR) {
						j3.setpointsJour(1);
					} else if (j3.getorigineDivinite() == origine && origine == TypeOrigine.NUIT) {
						j3.setpointsNuit(2);
					} else if (j3.getorigineDivinite() == TypeOrigine.CREPUSCULE && origine == TypeOrigine.NUIT) {

						j3.setpointsNuit(1);
					} else if (j3.getorigineDivinite() == TypeOrigine.CREPUSCULE && origine == TypeOrigine.NEANT) {

						j3.setpointsNeant(1);
					} else if (j3.getorigineDivinite() == TypeOrigine.AUBE && origine == TypeOrigine.NEANT) {

						j3.setpointsNeant(1);
					}
				}

			}
		}
	}
	
	/**
	 * Cette methode permet de recuperer l'attribut nom de la classe
	 * @return le nom de la carte d'action associee e la capacite de sacrifice
	 * implementation de la methode getNom() definie dans l'interface CapaciteSacrifice
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Cette methode permet un affichage de la capacite de sacrifice de la carte d'action
	 * implementation de la methode  toString() definie dans l'interface CapaciteSacrifice
	 */
	public String toString() {
		StringBuffer sb=new StringBuffer(nom+"\n");
		sb.append(
				"Recuperez les points deAction deune Divinite neayant pas encore joue durant ce tour. Les points deAction gardent leur Origine. La Divinite perd ses points.");
		return sb.toString();
	}

}
