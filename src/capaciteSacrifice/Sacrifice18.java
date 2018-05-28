package capaciteSacrifice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import elementsDeBase.CarteAction;
import elementsDeBase.Croyant;
import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import elementsDeBase.TypeDogme;
import interfaceGraphique.FenetrePrincipale;
/**
 * La classe sacrifice18 represente l'implementation de la capacite speciale de sacrifice de cartes Croyant
 * la classe implemente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice18 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacite speciale 
	 */
	private String nom="Ascete";
	
	
	/**
	 * contructeur de la classe
	 */
	
	public Sacrifice18(){
		
	}
	
	/**
	 * Cette methode demande au joueur qui la possede de sacrifier 2 cartes Croyants deune Divinite ayant le Dogme Humain ou Symboles
	 * Les capacites speciales sont jouees normalement.
	 * implementation de la methode seSacriifer() definie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier(){
		LinkedList<Croyant> cartesARetirer=new LinkedList<Croyant>();
	System.out.println("Sur quel joueur voulez-vous utiliser votre capacite?(0 pour terminer)");
	Partie.afficherLesJoueurs();
	@SuppressWarnings("resource")
	Scanner sc= new Scanner(System.in);
	 int n=sc.nextInt()-1;
	while (n>=Partie.listeJoueurs.size()-1){			
		System.out.println("Veuillez saisir un nombre valide");
		n=sc.nextInt()-1;	
	}
	while(!Partie.listeJoueurs.get(n).divCroitAuDogme(TypeDogme.HUMAIN) && !Partie.listeJoueurs.get(n).divCroitAuDogme(TypeDogme.SYMBOLE)){
		System.out.println("Veuillez saisir un nombre valide, ce joueur ne possede ni le dogme Humain ni le dogme Symboles");
		n=sc.nextInt()-1;	
	}
	Joueur j=Partie.listeJoueurs.get(n);
	LinkedList<GuideSpirituel> cartesRec=j.getcartesRecuperees();
	GuideSpirituel g;
	Croyant c;
	int k=0;
	Croyant[] croyantsGuides;
	for (int i=0;i<2;i++){
               j.afficherCartesRecuperees();
               if (!Partie.getInterfaceGaphiqueActive()) {
               System.out.println("Choisissez le Guide Spirituel");
               k=sc.nextInt()-1;
                   while(k>cartesRec.size()-1){
                	System.out.println("Veuillez saisir un nombre valide"); 
                	k=sc.nextInt()-1;
                   }
                   g=cartesRec.remove(k);
                              }else{
            	   LinkedList<CarteAction> liste = new LinkedList<CarteAction>();
            	   Iterator<CarteAction> it = liste.iterator();
            	   while(it.hasNext()){
            		   liste.add(it.next());
            	   }
            	   g=FenetrePrincipale.choisirUnGuideSpirituel(liste);
            	   cartesRec.remove(g);
               }
               croyantsGuides=g.getcroyantsGuides();
               if (!Partie.getInterfaceGaphiqueActive()) {
               System.out.println("Choisissez le croyant");
               int l=sc.nextInt()-1;
               g.afficherCroyantsRecuperees();
               
               while(l>g.nombreDeCroyantsRattachesMax()-1){
            	System.out.println("Veuillez saisir un nombre valide"); 
            	l=sc.nextInt()-1;
               }
               c=croyantsGuides[l];
               croyantsGuides[l]=null;
               }else{
            	   LinkedList<CarteAction> liste = new LinkedList<CarteAction>();
            	   for(int m=0; m<croyantsGuides.length;m++){
            		  liste.addLast(croyantsGuides[i]); 
            	   }
            	   c=FenetrePrincipale.choisirCroyant(liste);
            	   croyantsGuides[liste.indexOf(c)]=null;
               }
               c.jouerCarte();
               g.setcroyantsGuides(croyantsGuides);
               cartesRec.add(g);
               cartesARetirer.add(c);
               j.setcartesRecuperees(cartesRec);
	}
	Partie.centreDeTable.ajouterCroyant(cartesARetirer);
	Partie.listeJoueurs.get(n).setcartesRecuperees(j.getcartesRecuperees());
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
			sb.append(
					"Sacrifie 2 cartes Croyants deune Divinite ayant le Dogme Humain ou Symboles. Les capacites Speciales sont jouees normalement.");
			return sb.toString();
		
	}

}
