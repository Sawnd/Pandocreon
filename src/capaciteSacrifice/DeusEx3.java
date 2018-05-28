package capaciteSacrifice;

import java.util.Iterator;
import java.util.LinkedList;

import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import interfaceGraphique.MessageBox;

/**
 * La classe DeusEx3 représente l'implémentation de la capacité spéciale de sacrifice d'une carte DeusEx 
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx3 implements CapaciteSacrifice{
	
	/**
	 * nom de la  capacité spéciale 
	 */
	private String nom;
	
	/**
	 * contructeur de la classe
	 * @param nom est la valeur à associer à l'attribut nom
	 */
	public DeusEx3(String nom){
		this.nom=nom;
	}
	
	/**
	 * Cette méthode permet au joueur en cours de récupérer un des Guides Spirituels posés devant une autre Divinité et de la placez devant lui avec les Croyants qui y sont attachés
	 * implémenttaion de l améthode seSacriifer() définie dans l'interface capaciteSacrifice
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
		MessageBox.getMessageBox().ajouterMessage("Ce joueur n'a récupéré aucune cartes!!");
	}
	}
	
	/**
	 * Cette méthode permet de récupérer l'attribut nom de la classe
	 * @return le nom de la carte d'action associée à la capacité de sacrifice
	 * implémentation de la méthode getNom définie dans l'interface CapaciteSacrifice
	 */
	public String getNom() {
		return nom;
	}
	
	
	/**
	 * Cette méthode permet un affichage de la capacité de sacrifice de la carte d'action
	 * implémentation de la méthode  toString() définie dans l'interface CapaciteSacrifice
	 */
	public String toString(){
		StringBuffer sb=new StringBuffer(nom+"\n");
		sb.append(
				" Vous récupérez un des Guides Spirituels posés devant une autre Divinité et la placez devant vous avec les Croyants qui y sont attachés" );
		return sb.toString();
	}

}
