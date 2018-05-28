package capaciteSacrifice;

import java.util.*;

import elementsDeBase.*;
import elementsDeBase.TypeOrigine;
import interfaceGraphique.MessageBox;
/**
 * La classe sacrifice15 represente l'implementation de la capacite speciale de sacrifice de cartes Croyant
 * la classe implemente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice15 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacite speciale 
	 */
	private String nom="Shaman";
	

	/**
	 * contructeur de la classe
	 */
		public Sacrifice15(){
		
	}
	
	/**
	 * Cette methode sacrifie tous les Croyants deOrigine Neant deune Divinite ayant le Dogme Humain. 
	 * Les capacites speciales sont jouees normalement.
	 * implementation de la methode seSacriifer() definie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier(){
	
		LinkedList<Croyant> cartesARetirer=new LinkedList<Croyant>();
		TypeDogme dogme1,dogme2,dogme3;
		Joueur joueurChoisi;
		
		/**
		 * le joueur en cours a la possibilite de choisir un adversaire tant que l'dversaire precedemment choisi ne possede pas le dogme '=" humain"
		 */
do{
		 joueurChoisi =Partie.getjoueurEnCours().choisirUnJoueur();
		
		  dogme1=joueurChoisi.getDogmeDivinite(1);
		  dogme2=joueurChoisi.getDogmeDivinite(2);
		  dogme3=joueurChoisi.getDogmeDivinite(3);}
		while (dogme1==TypeDogme.HUMAIN||dogme2==TypeDogme.HUMAIN||dogme3==TypeDogme.HUMAIN);
		
	/**
	 * On parcourt les croyants recuperes par chaque guidela divinite
	 * les croyants d'origine neant son ajoutes e une collection
	 * Les croyants ajoutes e cette collection sera par la suite detachees de leurs guides spirituels
	 */
		LinkedList<GuideSpirituel>cartesRec=joueurChoisi.getcartesRecuperees();
		Iterator<GuideSpirituel> it = cartesRec.iterator();
		GuideSpirituel g;
		Croyant c;
		Croyant[] croyantsRec;
		while (it.hasNext()){
			g=it.next();
			croyantsRec=g.getcroyantsGuides();
			for (int i=0;i<g.nombreDeCroyantsRattachesMax()-1;i++){
				if(croyantsRec[i].getorigines()==TypeOrigine.NEANT){
					c=croyantsRec[i];
					c.jouerCarte();
					cartesARetirer.add(c);
					croyantsRec[i]=null;
				}
			}
			g.setcroyantsGuides(croyantsRec);
		}
		joueurChoisi.setcartesRecuperees(cartesRec);
		Partie.piocheCarteAction.listeCartesAction.addAll(cartesARetirer);
		MessageBox.getMessageBox().ajouterMessage("Les croyants Neant de "+joueurChoisi+" ont ete detruits!");
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
 public String toString(){
	 StringBuffer sb=new StringBuffer(nom+"\n");
		sb.append("Sacrifie tous les Croyants deOrigine Neant deune Divinite ayant le Dogme Humain. Les capacites speciales sont jouees normalement.");
	return sb.toString();
 }
}
