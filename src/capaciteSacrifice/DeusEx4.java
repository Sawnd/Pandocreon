package capaciteSacrifice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import elementsDeBase.CarteAction;
import elementsDeBase.Croyant;
import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import interfaceGraphique.FenetrePrincipale;


/**
 * La classe DeusEx4 represente l'implementation de la capacite speciale de sacrifice de la carte DeusEx " Fourberie"
 * la classe implemente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx4 implements CapaciteSacrifice{
	
	/**
	 * nom de la  capacite speciale 
	 */
	private String nom="Fourberie";
	
	/**
	 * contructeur de la classe
	 */
	public DeusEx4(){
		
	}
	
	/**
	 * Cette methode permet au joueur en cours de sacrifier une carte Croyant deune autre Divinite. Les Capacites Speciales ne sont pas jouees
	 * implementation de l amethode seSacriifer() definie dans l'interface capaciteSacrifice
	 * On distingue le cas de la methode lorque l'interface graphique est active du cas oe le joueur joue en console
 	 */
	public void seSacrifier(){
		if (!Partie.getInterfaceGaphiqueActive()) {
			utiliserCapacite1();
		}else{
			utiliserCapacite2();
		}
	} 
	
	/**
	 * Methode utilisee lorsque le joueur joue en console
	 */
	@SuppressWarnings("resource")
	public void utiliserCapacite1(){
		Croyant[] croyantsGuides;
		Croyant c;
		int l;
		Joueur j= Partie.getjoueurEnCours();
		Joueur jAdverse=j.choisirUnJoueur();
		jAdverse.afficherCartesRecuperees();
		LinkedList<GuideSpirituel>cartesRec=j.getcartesRecuperees();
		GuideSpirituel g=j.choisirUnGuideSpirituel(cartesRec);
		
		g.afficherCroyantsRecuperees();
		  System.out.println("Choisissez le croyant");
		  
		  Scanner sc=new Scanner(System.in);
           l=sc.nextInt()-1;
          
          croyantsGuides=g.getcroyantsGuides();
          while(l>g.nombreDeCroyantsRattachesMax()-1){
       	System.out.println("Veuillez saisir un nombre valide"); 
       	l=sc.nextInt()-1;
          }
          c=croyantsGuides[l];
          croyantsGuides[l]=null;
          g.setcroyantsGuides(croyantsGuides);
          cartesRec.add(g);
          jAdverse.setcartesRecuperees(cartesRec);
          Partie.centreDeTable.ajouterCroyant(c);
          Iterator<Joueur> it=Partie.listeJoueurs.iterator();
          Joueur j1;
          while(it.hasNext()){
        	  j1=it.next();
        	  if(j1==j){
        		  j1.setcartesRecuperees(j.getcartesRecuperees());
        	  }
}
		
	}
	
	/**
	 * Methode utilisee lorsque l'interace graphique est active
	 */
	public void utiliserCapacite2(){
	    Croyant c;
		Joueur j= Partie.getjoueurEnCours();
		Joueur jAdverse=j.choisirUnJoueur();
		jAdverse.afficherCartesRecuperees();
		LinkedList<GuideSpirituel>cartesRec=j.getcartesRecuperees();
		GuideSpirituel g=j.choisirUnGuideSpirituel(cartesRec);
		LinkedList<CarteAction> listeCroyantsGuides = new LinkedList<CarteAction>();
		Croyant[] croyantsGuides = g.getcroyantsGuides();
		for(int i=0; i<croyantsGuides.length;i++){
			listeCroyantsGuides.add(croyantsGuides[i]);
		}
		c=FenetrePrincipale.choisirCroyant(listeCroyantsGuides);
		 croyantsGuides[listeCroyantsGuides.indexOf(c)]=null;
         g.setcroyantsGuides(croyantsGuides);
        // cartesRec.add(g);
         jAdverse.setcartesRecuperees(cartesRec);
         Partie.centreDeTable.ajouterCroyant(c);
         Iterator<Joueur> it=Partie.listeJoueurs.iterator();
         Joueur j1;
         while(it.hasNext()){
       	  j1=it.next();
       	  if(j1==j){
       		  j1.setcartesRecuperees(j.getcartesRecuperees());
       	  }
}
	}
	
	/**
	 * Cette methode permet de recuperer l'attribut nom de la classe
	 * @return le nom de la carte d'action associee e la capacite de sacrifice
	 * implementation de la methode getNom definie dans l'interface CapaciteSacrifice
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
			sb.append(
					"Sacrifiez une carte Croyant deune autre Divinite. Les Capacites Speciales ne sont pas jouees");
			return sb.toString();
		
	}
	
	
	

}