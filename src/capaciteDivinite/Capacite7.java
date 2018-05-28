package capaciteDivinite;

import java.util.Iterator;
import java.util.LinkedList;

import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;

/**
 * La classe Capacit�7 repr�sente l'impl�mentation de la capacit� sp�ciale de la divivnit� "Romtec"
 * la classe impl�mente l'interface CapaciteDivinite
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Capacite7 implements CapaciteDivinite {
	/**
	 * nom de l divinit� qui b�n�ficie de cette capacit� sp�ciale 
	 */
	private String nom="Romtec";
	
	/**
	 * contructeur de la classe
	 */
	public Capacite7(){
		
	}
	
	/**
	 *  Cette m�thode permet d'utiliser la capacit� de la divinit� au cours d'une partie
	 * Il est demand� au joueur en cours de choisir un adversaire � qui il emp�chera de cr�er un guide spirituel
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
	 * Cette m�thode permet un affichage dans la console du pouvoir de la divinit�
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer(" Peut emp�cher un joueur de cr�er un Guide Spirituel. La carte est d�fauss�e.");
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
