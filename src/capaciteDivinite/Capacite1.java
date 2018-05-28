package capaciteDivinite;

import java.util.Iterator;
import java.util.LinkedList;

import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
/**
 * La classe Capacite1 represente l'impl�mentation de la capacit� sp�ciale de la divivnit� "Drinded"
 * la classe impl�mente l'interface CapaciteDivinite
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Capacite1 implements CapaciteDivinite{
	
	/**
	 * nom de la divinit� qui b�n�ficie de cette capacit� sp�ciale 
	 */
	private String nom="Drinded";
	
	/**
	 * contructeur de la classe
	 */
	public Capacite1(){
		
	}
	
	/**
	 *  Cette m�thode permet d'utiliser la capacit� de la divinit� au cours d'une partie
	 * Il est demand� au joueur en cours de choisir un adversaire � qui il emp�chera le sacrifice d'un de ses guides spirituels
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
	 * Cette m�thode permet un affichage  du pouvoir de la divinit�
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer(" Peut emp�cher le sacrifice d�un des Guides Spirituels de n�importe quel joueur.");
		return sb.toString();
	}
	
	/**
	 * Cette m�thode permet de r�cup�rer l'attribut nom de la classe
	 * @return le nom de la divinit� associ�e � la capacit�
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette methode permet de modifier l'attribut nom de la classe
	 * @param nom est la valeur qu'on souhaie attribuer � l'attribut nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

}
