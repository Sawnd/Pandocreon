package capaciteSacrifice;

import java.util.Iterator;
import java.util.LinkedList;

import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import interfaceGraphique.MessageBox;

/**
 * La classe DeusEx3 repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice d'une carte DeusEx 
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx3 implements CapaciteSacrifice{
	
	/**
	 * nom de la  capacit� sp�ciale 
	 */
	private String nom;
	
	/**
	 * contructeur de la classe
	 * @param nom est la valeur � associer � l'attribut nom
	 */
	public DeusEx3(String nom){
		this.nom=nom;
	}
	
	/**
	 * Cette m�thode permet au joueur en cours de r�cup�rer un des Guides Spirituels pos�s devant une autre Divinit� et de la placez devant lui avec les Croyants qui y sont attach�s
	 * impl�menttaion de l am�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier(){
	Joueur j=Partie.getjoueurEnCours();
	Joueur jAdverse=j.choisirUnJoueur();
	
	LinkedList<GuideSpirituel> cartesRecAdversaire=jAdverse.getcartesRecuperees();
	LinkedList<GuideSpirituel> cartesRec=j.getcartesRecuperees();
	GuideSpirituel g;
	
	jAdverse.afficherCartesRecuperees();
	if ( !cartesRecAdversaire.isEmpty()){
	g=j.choisirUnGuideSpirituel(cartesRecAdversaire);
	jAdverse.setcartesRecuperees(cartesRecAdversaire);
	cartesRec.add(g);
	j.setcartesRecuperees(cartesRec);
	Iterator<Joueur> it=Partie.listeJoueurs.iterator();
	Joueur j2;
	while(it.hasNext()){
		j2=it.next();
	     if(j2==j){
	    	 j2.setcartesRecuperees(j.getcartesRecuperees());
	     }
	     if(j2==jAdverse){
	    	 j2.setcartesRecuperees(jAdverse.getcartesRecuperees());
	     }
	}}else{
		MessageBox.getMessageBox().ajouterMessage("Ce joueur n'a r�cup�r� aucune cartes!!");
	}
	}
	
	/**
	 * Cette m�thode permet de r�cup�rer l'attribut nom de la classe
	 * @return le nom de la carte d'action associ�e � la capacit� de sacrifice
	 * impl�mentation de la m�thode getNom d�finie dans l'interface CapaciteSacrifice
	 */
	public String getNom() {
		return nom;
	}
	
	
	/**
	 * Cette m�thode permet un affichage de la capacit� de sacrifice de la carte d'action
	 * impl�mentation de la m�thode  toString() d�finie dans l'interface CapaciteSacrifice
	 */
	public String toString(){
		StringBuffer sb=new StringBuffer(nom+"\n");
		sb.append(
				" Vous r�cup�rez un des Guides Spirituels pos�s devant une autre Divinit� et la placez devant vous avec les Croyants qui y sont attach�s" );
		return sb.toString();
	}

}
