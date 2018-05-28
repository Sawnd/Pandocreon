package capaciteSacrifice;

import java.util.Iterator;
import java.util.LinkedList;
import elementsDeBase.CarteAction;
import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageBox;
/**
 * La classe sacrifice21 représente l'implémentation de la capacité spéciale de sacrifice de cartes Croyant
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */

public class Sacrifice21 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacité spéciale 
	 */
	private String nom = "Sorcier";

	/**
	 * contructeur de la classe
	 */
	public Sacrifice21() {

	}

	/**
	 * Cette méthode prmet au joueur en cours de permuter un de ses guides spirituels avec celui d'un adversaire. 
	 * implémentation de la méthode seSacriifer() définie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier() {
		Joueur j=Partie.getjoueurEnCours();
		Joueur jAdverse=j.choisirUnJoueur();
		GuideSpirituel g1, g2;
	
		LinkedList<GuideSpirituel> cartesRecAdversaire = jAdverse.getcartesRecuperees();
		LinkedList<GuideSpirituel> cartesRec = j.getcartesRecuperees();
		Iterator<Joueur> it = Partie.listeJoueurs.iterator();
		if (!cartesRecAdversaire.isEmpty() && !cartesRec.isEmpty()) {
			jAdverse.afficherCartesRecuperees();
			if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println("Quel Guide Spirituel voulez vous récupérer?");
			g1=j.choisirUnGuideSpirituel(cartesRecAdversaire);
			j.afficherCartesRecuperees();
			g2=j.choisirUnGuideSpirituel(cartesRec);
			}else{
				Iterator<GuideSpirituel> itg= cartesRecAdversaire.iterator();
				LinkedList<CarteAction> liste = new LinkedList<CarteAction>();
				while(itg.hasNext()){
					liste.add(itg.next());
				}
				g1=FenetrePrincipale.choisirUnGuideSpirituel(liste);
				liste.remove(g1);
				g2=FenetrePrincipale.choisirUnGuideSpirituel(liste);
				
			}
			
			cartesRec.add(g1);
			cartesRecAdversaire.add(g2);
			j.setcartesRecuperees(cartesRec);
			jAdverse.setcartesRecuperees(cartesRecAdversaire);
			Joueur j1;
			while (it.hasNext()) {
				j1 = it.next();
				if (Partie.getjoueurEnCours() == j1) {
					j1.setcartesRecuperees(j.getcartesRecuperees());
				}
				if(j1==jAdverse){
					j1.setcartesRecuperees(jAdverse.getcartesRecuperees());
				}
			}
		}else{
			if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println("Les joueurs n'ont pas de Guide Spirituel. La capacité est gachée");
			}else{
				MessageBox.getMessageBox().ajouterMessage("Les joueurs n'ont pas de Guide Spirituel. La capacité est gachée");
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
		StringBuffer sb=new StringBuffer(nom+"\n");
		sb.append(
				"Echangez l’un de vos Guildes Spirituels avec celui d’une autre Divinité. Vous choisissez les deux guides Spirituels en question. Les Croyants restent attachés aux mêmes cartes.  ");
		return sb.toString();

	}
}
