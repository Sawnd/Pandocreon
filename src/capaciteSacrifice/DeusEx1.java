package capaciteSacrifice;

import java.util.Iterator;
import java.util.LinkedList;

import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import elementsDeBase.TypeOrigine;
import interfaceGraphique.MessageBox;

/**
 * La classe DeusEx1 représente l'implémentation de la capacité spéciale de sacrifice d'une carte DeusEx 
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx1 implements CapaciteSacrifice {
	
	/**
	 * nom de la capacité spéciale 
	 */
	private String nom="Colère Divine";
	
	/**
	 * première origine possible de la carte sur laquelle utiliser la capacité de sacfifice
	 */
TypeOrigine origine1 = TypeOrigine.NEANT;

/**
 * deuxième origine possible de la carte sur laquelle utiliser la capacité de sacfifice
 */
TypeOrigine origine2;

/**
 * contructeur de la classe
 * @param origine est la valeur à associer à l'attribut origine2
 */
	public DeusEx1(TypeOrigine origine) {
origine2=origine;
		
	}
	
	/**
	 *  Cette méthode permet de détruire une carte Guide Spirituel d’Origine " +origine2+" ou Néant dont la capacité spéciale n’a pas effet. 
	 *  Les Croyants attachés reviennent au centre de la table
	 * Le sacrifice de la carte est équivalent à la pose d'une carte Apocalypse
	 * implémenttaion de l améthode seSacriifer() définie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier(){
		Joueur j=Partie.getjoueurEnCours();
		Joueur jAdverse=j.choisirUnJoueur();
		LinkedList<GuideSpirituel> cartesRecAdversaire=jAdverse.getcartesRecuperees();
		while(jAdverse.getcartesRecuperees().isEmpty()){
			if (!Partie.getInterfaceGaphiqueActive()) {
				System.out.println("Ce joueur n'a pas encore récupéré de croyants, choisissez un autre joueur");
				}else{
					MessageBox.getMessageBox().ajouterMessage("Ce joueur n'a pas encore récupéré de croyants, choisissez un autre joueur");
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
				System.out.println("Cette carte est bloquée");
				}else {
					MessageBox.getMessageBox().ajouterMessage("Cette carte est bloquée");
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
				" Détruit une carte Guide Spirituel d’Origine " +origine2+" ou Néant dont la capacité spéciale n’a pas effet. Les Croyants attachés reviennent au centre de la table" );
		return sb.toString();
	}
	
}
