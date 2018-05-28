package capaciteSacrifice;

import java.util.LinkedList;
import java.util.Scanner;

import elementsDeBase.CarteAction;
import elementsDeBase.Croyant;
import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import elementsDeBase.TypeDogme;
import elementsDeBase.TypeOrigine;
import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageBox;
/**
 * La classe sacrifice20 represente l'implementation de la capacite speciale de sacrifice de cartes Croyant
 * la classe implemente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice20 implements CapaciteSacrifice{
	
	/**
	 * nom de la  capacite speciale 
	 */
	private String nom="Exorciste";
	
	/**
	 * contructeur de la classe
	 */
	public Sacrifice20(){
		
	}
	
	/**
	 * Cette methode perme au joueurs en cours de choisir une divinite deOrigine Nuit ou ayant les Dogmes Mystique et Chaos qui reprend dans sa main leun de ses Guides Spirituels. 
	 * Les Croyants qui y etaient attaches sont defausses
	 * Les capacites speciales sont jouees normalement.
	 * implementation de la methode seSacriifer() definie dans l'interface capaciteSacrifice
	 */
		public void seSacrifier(){
		Joueur j;
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		LinkedList<Croyant> cartesARetirer=new LinkedList<Croyant>();
		if (!Partie.getInterfaceGaphiqueActive()) {
		System.out.println("Sur quel joueur voulez-vous utiliser votre capacite?(0 pour terminer)");
		Partie.afficherLesJoueurs();
		
		 int n=sc.nextInt()-1;
		while (n>=Partie.listeJoueurs.size()-1){			
			System.out.println("Veuillez saisir un nombre valide");
			n=sc.nextInt()-1;	
		}
		while(!Partie.listeJoueurs.get(n).divCroitAuDogme(TypeDogme.MYSTIQUE) && !Partie.listeJoueurs.get(n).divCroitAuDogme(TypeDogme.CHAOS) && !(Partie.listeJoueurs.get(n).getorigineDivinite()==TypeOrigine.NUIT)){
			System.out.println("Veuillez saisir un nombre valide, ce joueur ne possede ni le dogme Humain ni le dogme Symboles");
			n=sc.nextInt()-1;	
		}
		j=Partie.listeJoueurs.get(n);
		}else{
			 j =FenetrePrincipale.choisirUnJoueur();
			while(!j.divCroitAuDogme(TypeDogme.MYSTIQUE) && !j.divCroitAuDogme(TypeDogme.CHAOS) && !(j.getorigineDivinite()==TypeOrigine.NUIT)){
				MessageBox.getMessageBox().ajouterMessage("Veuillez saisir un nombre valide, ce joueur ne possede ni le dogme Humain ni le dogme Symboles");
					
			}
		}
		
	
		GuideSpirituel g = null;
		LinkedList<GuideSpirituel> cartesRec=j.getcartesRecuperees();
		LinkedList<CarteAction> main=j.getmain();
		        j.afficherCartesRecuperees();
		        if(!cartesRec.isEmpty()){
		        	if (!Partie.getInterfaceGaphiqueActive()) {
		        System.out.println("Quel Guide Spirituel retournera dans sa main?");
		        int k=sc.nextInt()-1;
		        while(k>cartesRec.size()-1){
                	System.out.println("Veuillez saisir un nombre valide"); 
                	k=sc.nextInt()-1;
                   }
               g=cartesRec.remove(k);
		        	}else{
		        		LinkedList<CarteAction> liste = new LinkedList<CarteAction>();
		        		java.util.Iterator<GuideSpirituel> itc = cartesRec.iterator();
		        		while(itc.hasNext()){
		        			liste.add(itc.next());
		        			g = FenetrePrincipale.choisirUnGuideSpirituel(liste);
		        			cartesRec.remove(g);
		        		}
		        	}
               g.defausserLesCroyants();
               main.add(g);
Partie.piocheCarteAction.listeCartesAction.addAll(cartesARetirer);
j.setmain(main);
j.setcartesRecuperees(cartesRec);

		        }else{
		        	if (!Partie.getInterfaceGaphiqueActive()) {
		        	System.out.println("Le joueur n'a recupere aucune carte, la capacite est gechee... :(");
		        	}else{
		        		MessageBox.getMessageBox().ajouterMessage("Le joueur n'a recupere aucune carte, la capacite est gechee... :(");
		        	}
		        }
		        
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
					"Une Divinite deOrigine Nuit ou ayant les Dogmes Mystique et Chaos reprend dans sa main leun de ses Guides Spirituels. Les Croyants qui y etaient attaches sont defausses." );
			return sb.toString();
	

}
}