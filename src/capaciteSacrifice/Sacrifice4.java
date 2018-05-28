package capaciteSacrifice;

import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import interfaceGraphique.MessageBox;

/**
 * La classe sacrifice4 repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice de cartes Croyant
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice4 implements CapaciteSacrifice {

	/**
	 * nom de la  capacit� sp�ciale 
	 */
	private String nom;
	
	/**
	 * cha�ne de caract�res repr�sentant la classe sur laquelle utiliser la sacrifice
	 */
	private String classe;
/**
 * constructeur de la classe
 * @param nom repr�sentant la valeur � affecter � l'attribut nom et determinant la valeur de l'attribut classe
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
		case "Int�gristes":
			this.classe = "Guide Spirituel";
			break;
		case "R�volutionnaires":
			this.classe = "Croyant";
		}
	}
	
	/**
	 * Cette m�thode impose � un joueur de sacrifier d'une carte appartement � la classe enregistr�e dans l'attribut classe
	 *  La capacit� sp�ciale du sacrifice est jou�e
	 * impl�mentation de la m�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
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
		sb.append("Impose le sacrifice d�un " + classe
				+ " d�un autre joueur, qui choisit la carte. La capacit� sp�ciale du sacrifice est jou�e");
		return sb.toString();
	}
}
