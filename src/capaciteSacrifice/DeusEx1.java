package capaciteSacrifice;

import java.util.Iterator;
import java.util.LinkedList;

import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import elementsDeBase.TypeOrigine;
import interfaceGraphique.MessageBox;

/**
 * La classe DeusEx1 repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice d'une carte DeusEx 
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx1 implements CapaciteSacrifice {
	
	/**
	 * nom de la capacit� sp�ciale 
	 */
	private String nom="Col�re Divine";
	
	/**
	 * premi�re origine possible de la carte sur laquelle utiliser la capacit� de sacfifice
	 */
TypeOrigine origine1 = TypeOrigine.NEANT;

/**
 * deuxi�me origine possible de la carte sur laquelle utiliser la capacit� de sacfifice
 */
TypeOrigine origine2;

/**
 * contructeur de la classe
 * @param origine est la valeur � associer � l'attribut origine2
 */
	public DeusEx1(TypeOrigine origine) {
origine2=origine;
		
	}
	
	/**
	 *  Cette m�thode permet de d�truire une carte Guide Spirituel d�Origine " +origine2+" ou N�ant dont la capacit� sp�ciale n�a pas effet. 
	 *  Les Croyants attach�s reviennent au centre de la table
	 * Le sacrifice de la carte est �quivalent � la pose d'une carte Apocalypse
	 * impl�menttaion de l am�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier(){
		Joueur j=Partie.getjoueurEnCours();
		Joueur jAdverse=j.choisirUnJoueur();
		LinkedList<GuideSpirituel> cartesRecAdversaire=jAdverse.getcartesRecuperees();
		while(jAdverse.getcartesRecuperees().isEmpty()){
			if (!Partie.getInterfaceGaphiqueActive()) {
				System.out.println("Ce joueur n'a pas encore r�cup�r� de croyants, choisissez un autre joueur");
				}else{
					MessageBox.getMessageBox().ajouterMessage("Ce joueur n'a pas encore r�cup�r� de croyants, choisissez un autre joueur");
				}	
			 jAdverse=j.choisirUnJoueur();
			 cartesRecAdversaire=jAdverse.getcartesRecuperees();
		}
		jAdverse.afficherCartesRecuperees();
		int compteur =3;
		
		GuideSpirituel g1=null;
		do {
			if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println("Choisissez un Guide Spirituel Jour ou Nuit");
			}else{
				MessageBox.getMessageBox().ajouterMessage("Choisissez un Guide Spirituel Jour ou Nuit");
			}
			g1=j.choisirUnGuideSpirituel(cartesRecAdversaire);
			if(!g1.getPeutEtreJouee()){
				if (!Partie.getInterfaceGaphiqueActive()) {
				System.out.println("Cette carte est bloqu�e");
				}else {
					MessageBox.getMessageBox().ajouterMessage("Cette carte est bloqu�e");
				}
				cartesRecAdversaire.add(g1);
			}
		} while (g1.getorigine()!=origine1 &&g1.getorigine()!=origine2 &&!g1.getPeutEtreJouee() && compteur>3);
		 if(g1!=null){
			 jAdverse.setcartesRecuperees(cartesRecAdversaire);
		g1.defausserLesCroyants();
		Partie.piocheCarteAction.listeCartesAction.add(g1);
		Iterator<Joueur> it=Partie.listeJoueurs.iterator();
		Joueur j1;
		while(it.hasNext()){
			j1=it.next();
			if(j1==jAdverse){
				j1.setcartesRecuperees(jAdverse.getcartesRecuperees());
			}
			}
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
				" D�truit une carte Guide Spirituel d�Origine " +origine2+" ou N�ant dont la capacit� sp�ciale n�a pas effet. Les Croyants attach�s reviennent au centre de la table" );
		return sb.toString();
	}
	
}
