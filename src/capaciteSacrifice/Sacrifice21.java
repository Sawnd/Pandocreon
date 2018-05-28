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
 * La classe sacrifice21 repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice de cartes Croyant
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */

public class Sacrifice21 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacit� sp�ciale 
	 */
	private String nom = "Sorcier";

	/**
	 * contructeur de la classe
	 */
	public Sacrifice21() {

	}

	/**
	 * Cette m�thode prmet au joueur en cours de permuter un de ses guides spirituels avec celui d'un adversaire. 
	 * impl�mentation de la m�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
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
			System.out.println("Quel Guide Spirituel voulez vous r�cup�rer?");
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
			System.out.println("Les joueurs n'ont pas de Guide Spirituel. La capacit� est gach�e");
			}else{
				MessageBox.getMessageBox().ajouterMessage("Les joueurs n'ont pas de Guide Spirituel. La capacit� est gach�e");
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
		StringBuffer sb=new StringBuffer(nom+"\n");
		sb.append(
				"Echangez l�un de vos Guildes Spirituels avec celui d�une autre Divinit�. Vous choisissez les deux guides Spirituels en question. Les Croyants restent attach�s aux m�mes cartes.  ");
		return sb.toString();

	}
}
