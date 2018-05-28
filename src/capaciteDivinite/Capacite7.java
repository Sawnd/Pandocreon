package capaciteDivinite;

import java.util.Iterator;
import java.util.LinkedList;

import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;

/**
 * La classe Capacité7 représente l'implémentation de la capacité spéciale de la divivnité "Romtec"
 * la classe implémente l'interface CapaciteDivinite
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Capacite7 implements CapaciteDivinite {
	/**
	 * nom de l divinité qui bénéficie de cette capacité spéciale 
	 */
	private String nom="Romtec";
	
	/**
	 * contructeur de la classe
	 */
	public Capacite7(){
		
	}
	
	/**
	 *  Cette méthode permet d'utiliser la capacité de la divinité au cours d'une partie
	 * Il est demandé au joueur en cours de choisir un adversaire à qui il empêchera de créer un guide spirituel
	 */
	public void utiliserCapacite(){
		Joueur j =Partie.getjoueurEnCours();
		GuideSpirituel g;
		Joueur jAdverse =j.choisirUnJoueur();
		LinkedList<GuideSpirituel> cartesRec =jAdverse.getcartesRecuperees();
		g=j.choisirUnGuideSpirituel(cartesRec);
		g.setPeutEtreJouee(false);
		cartesRec.add(g);
		jAdverse.setcartesRecuperees(cartesRec);
	 Iterator<Joueur> it =Partie.listeJoueurs.iterator();
	 Joueur j1;
	 while(it.hasNext()){
		 j1=it.next();
		 if(j1==jAdverse){
			 j1.setcartesRecuperees(jAdverse.getcartesRecuperees());
		 }
	 }
	}
	
	/**
	 * Cette méthode permet un affichage dans la console du pouvoir de la divinité
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer(" Peut empêcher un joueur de créer un Guide Spirituel. La carte est défaussée.");
		return sb.toString();
	}
	
	/**
	 * Cette méthode permet de récupérer l'attribut nom de la classe
	 * @return le nom de la divinité associée à la capacité
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette methode permet de modifier l'attribut nom de la classe
	 * @param nom est la valeur qu'on souhaie attribuer à l'attribut nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


}
