package capaciteDivinite;

import java.util.Iterator;
import java.util.LinkedList;

import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
/**
 * La classe Capacite1 represente l'implémentation de la capacité spéciale de la divivnité "Drinded"
 * la classe implémente l'interface CapaciteDivinite
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Capacite1 implements CapaciteDivinite{
	
	/**
	 * nom de la divinité qui bénéficie de cette capacité spéciale 
	 */
	private String nom="Drinded";
	
	/**
	 * contructeur de la classe
	 */
	public Capacite1(){
		
	}
	
	/**
	 *  Cette méthode permet d'utiliser la capacité de la divinité au cours d'une partie
	 * Il est demandé au joueur en cours de choisir un adversaire à qui il empêchera le sacrifice d'un de ses guides spirituels
	 */
	
	public void utiliserCapacite(){
		Joueur j =Partie.getjoueurEnCours();
		GuideSpirituel g;
		Joueur jAdverse =j.choisirUnJoueur();
		LinkedList<GuideSpirituel> cartesRec =jAdverse.getcartesRecuperees();
		g=j.choisirUnGuideSpirituel(cartesRec);
		g.setPeutEtreSacrifiee(false);
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
	 * Cette méthode permet un affichage  du pouvoir de la divinité
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer(" Peut empêcher le sacrifice d’un des Guides Spirituels de n’importe quel joueur.");
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
