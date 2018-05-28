package capaciteSacrifice;

import java.util.LinkedList;

import elementsDeBase.*;
import elementsDeBase.Partie;
import elementsDeBase.TypeOrigine;
import interfaceGraphique.MessageBox;

/**
 * La classe DeusEx12 represente l'implementation de la capacite speciale de sacrifice de la carte DeusEx " Inquisition"
 * la classe implemente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx12 implements CapaciteSacrifice{
	
	/**
	 * nom de la  capacite speciale 
	 */
	private String nom="Inquisition";
	
	/**
	 * contructeur de la classe
	 */
	public DeusEx12(){
		
	}
	
	/**
	 * Cette methode permet au joueur possedant la carte de choisir un des Guides Spirituels deun autre joueur et leun des siens.
	 * Le joueur lance alors le de de cosmogonie. 
	 * Sur Jour, le Guide adverse est sacrifie, sur Nuit le sien est sacrifie, sur Neant rien ne se passe.
	 * Le sacrifice de la carte est equivalent e la pose d'une carte Apocalypse
	 * implementtaion de l amethode seSacriifer() definie dans l'interface capaciteSacrifice
	 */
		public void seSacrifier(){ 
			
			/**
			 * on recupere  le joueur en cours qui choisit un adversaire 
			 */
		Joueur j=Partie.getjoueurEnCours();
		Joueur jAdverse=j.choisirUnJoueur();
		
		/**
		 * on recupere dans une variable les cartes rcuperees du joueur precedemmet choisi
		 * le joueur choisit un des guides spirituels recuperes par son adversaire et relance le de
		 */
		GuideSpirituel g;
		LinkedList<GuideSpirituel> cartesRec;
		TypeOrigine origine =Partie.lancerDe();
		switch(origine){
		case JOUR: cartesRec=jAdverse.getcartesRecuperees();
		g=j.choisirUnGuideSpirituel(cartesRec);
		g.jouerCarte();
		Partie.piocheCarteAction.listeCartesAction.add(g);
		if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println("La face du de est Jour, le guide spirituel de votre adversaire est sacrifie");
		} else{
			MessageBox.getMessageBox().ajouterMessage("La face du de est Jour, le guide spirituel de votre adversaire est sacrifie");
		}
		break;
		case NUIT:cartesRec=j.getcartesRecuperees();
		g=j.choisirUnGuideSpirituel(cartesRec);
		g.jouerCarte();
		Partie.piocheCarteAction.listeCartesAction.add(g);
		if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println("La face du de est Nuit, votre guide spirituel  est sacrifie");
		} else{
			MessageBox.getMessageBox().ajouterMessage("La face du de est Nuit, votre guide spirituel est sacrifie");
		}
		break;
		case NEANT:if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println("La face du de est Neant, rien ne se passe...");
		} else{
			MessageBox.getMessageBox().ajouterMessage("La face du de est Neant, rien ne se passe...");
		}
		break;
		default:break;
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
				"Choisissez un des Guides Spirituels deun autre joueur et leun des votres. Lancez le de de cosmogonie. Sur Jour, le Guide adverse est sacrifie, sur Nuit le votre est sacrifie, sur Neant rien ne se passe." );
		return sb.toString();
	}

}
