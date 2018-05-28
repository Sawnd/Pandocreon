package capaciteSacrifice;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import elementsDeBase.*;

/**
 * La classe sacrifice9 represente l'implementation de la capacite speciale de sacrifice de cartes Croyant
 * la classe implemente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice9 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacite speciale 
	 */
	private String nom= "Illusionnistes";
	
	/**
	 * contructeur de la classe
	 */	
public Sacrifice9(){
	
}


/**
 * Cette methode permet au joueur en cours de beneficier de la capacite de sacrifice d'une carte Croyant d'un de ses adversaires
 * La carte en question reste en jeu
 * On distingue le cas oe l'interface graphique est active du cas oe elle ne l'est pas
 * implementation de la methode seSacriifer() definie dans l'interface capaciteSacrifice
 */
public void seSacrifier(){
	if (!Partie.getInterfaceGaphiqueActive()) {
		utiliserCapacite1();
	}else{
		utiliserCapacite2();
	}
}
/**
 * Methode utilisee lorsque le jeu est joue en console
 */
	public void utiliserCapacite1(){
	  Joueur jEnCours=Partie.getjoueurEnCours();
			Joueur j1=jEnCours.choisirUnJoueur();
			
			GuideSpirituel g;
			if (!j1.getcartesRecuperees().isEmpty()) {
				System.out.println("De quelle carte voulez-vous recopier la capacite? \n");
				j1.afficherCartesRecuperees();
				LinkedList<GuideSpirituel> cartesRec=j1.getcartesRecuperees();
				
				g =jEnCours.choisirUnGuideSpirituel(cartesRec);
				System.out.println("Selectionnez une carte (0 pour le guide spirituel)");
				g.afficherCroyantsRecuperees();
				int l;
				@SuppressWarnings("resource")
				Scanner sc4 = new Scanner(System.in);
				l = sc4.nextInt() - 1;
				while (l < -1 || l > g.nombreDeCroyantsRattachesMax()-1) {
					System.out.println("Veuillez saisir un nombre valide");
					l = sc4.nextInt() - 1;
				}

				if (l == -1) {
					g.jouerCarte();
				} else {
					Croyant[] croyantRec = g.getcroyantsGuides();
					Croyant c1 = croyantRec[l];
					c1.jouerCarte();
				}

			} else {
				System.out.println(j1+" n'a recupere aucune carte! ");

			}
			
		}
	
	/**
	 * Methode utilisee lorsque l'interface graphique est active
	 */
	public void utiliserCapacite2(){
		Joueur jEnCours=Partie.getjoueurEnCours();
		Joueur j1=jEnCours.choisirUnJoueur();
		if (!j1.getcartesRecuperees().isEmpty()) {
			System.out.println("De quelle carte voulez-vous recopier la capacite? \n");
			j1.afficherCroyantsRecuperes();
			ListIterator<GuideSpirituel> it = j1.getcartesRecuperees().listIterator();
			LinkedList<Croyant> liste = new LinkedList<Croyant>();
			while (it.hasNext()) {
				GuideSpirituel c = it.next();
				for( int i =0; i< c.getcroyantsGuides().length;i++){
					liste.add(c.getcroyantsGuides()[i]);
				}
			}
			Croyant croyant = jEnCours.choisirUnCroyant(liste);
			croyant.jouerCarte();
		}
		else {
			System.out.println(j1+" n'a recupere aucune carte! ");
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
		sb.append("Vous beneficiez de la capacite speciale de sacrifice deune carte de Croyants appartenant e une autre Divinite. La carte en question reste en jeu.");
	return sb.toString();
	}
}
