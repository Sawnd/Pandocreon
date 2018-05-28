package capaciteSacrifice;

import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import interfaceGraphique.MessageBox;

/**
 * La classe sacrifice4 représente l'implémentation de la capacité spéciale de sacrifice de cartes Croyant
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice4 implements CapaciteSacrifice {

	/**
	 * nom de la  capacité spéciale 
	 */
	private String nom;
	
	/**
	 * chaîne de caractères représentant la classe sur laquelle utiliser la sacrifice
	 */
	private String classe;
/**
 * constructeur de la classe
 * @param nom représentant la valeur à affecter à l'attribut nom et determinant la valeur de l'attribut classe
 */
	public Sacrifice4(String nom) {
		this.nom = nom;
		switch (nom) {
		case "Ermite":
			this.classe = "Croyant";
			break;
		case "Vampires":
			this.classe = "Croyant";
			break;
		case "Intégristes":
			this.classe = "Guide Spirituel";
			break;
		case "Révolutionnaires":
			this.classe = "Croyant";
		}
	}
	
	/**
	 * Cette méthode impose à un joueur de sacrifier d'une carte appartement à la classe enregistrée dans l'attribut classe
	 *  La capacité spéciale du sacrifice est jouée
	 * implémentation de la méthode seSacriifer() définie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {
		Joueur j, jAdverse;
		int n = 0;
		int compteur = Partie.listeJoueurs.size() - 1;
		while (n != -1 && compteur > 0) {
			j = Partie.getjoueurEnCours();
			jAdverse = j.choisirUnJoueur();
			if (!Partie.getInterfaceGaphiqueActive()) {
				System.out.println(jAdverse + ", quelle carte " + classe + " voulez-vous sacrifier?");
			} else {
				MessageBox.getMessageBox()
						.ajouterMessage(jAdverse + ", quelle carte " + classe + " voulez-vous sacrifier?");
			}
			jAdverse.sacrifierCartes();
			compteur--;
		}
		j = Partie.getjoueurEnCours();
		jAdverse = j.choisirUnJoueur();
		if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println(jAdverse + ", quelle carte " + classe + " voulez-vous sacrifier?");
		} else {
			MessageBox.getMessageBox()
					.ajouterMessage(jAdverse + ", quelle carte " + classe + " voulez-vous sacrifier?");
		}
		j.sacrifierCartes();
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
		sb.append("Impose le sacrifice d’un " + classe
				+ " d’un autre joueur, qui choisit la carte. La capacité spéciale du sacrifice est jouée");
		return sb.toString();
	}
}
