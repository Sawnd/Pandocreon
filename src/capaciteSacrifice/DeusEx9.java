package capaciteSacrifice;

import elementsDeBase.*;
import interfaceGraphique.MessageBox;

/**
 * La classe DeusEx9 représente l'implémentation de la capacité spéciale de sacrifice de la carte DeusEx " Transe"
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx9 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacité spéciale 
	 */	
private String nom="Transe";

/**
 * contructeur de la classe
 */
 public DeusEx9(){
	 
 }
 
 /**
	 * Cette méthode permet au joueur en cours de récupérer les effets bénéfiques d’une carte d’Action posée par une autre Divinité. 
	 * S’il s’agit d’une carte Croyants ou Guide Spirituel , le joueur en cours récupère la carte
	 * implémentation de la méthode seSacriifer() définie dans l'interface capaciteSacrifice
	 */
 public void seSacrifier(){
	 CarteAction c=null;
	 c=(CarteAction) Partie.getcarteEnCours();
	 if(c!=null){
		 if (c instanceof GuideSpirituel || c instanceof Croyant){
			 
			 Partie.getjoueurEnCours().getmain().remove(c);
			 Partie.getcarteEnCours().setPeutEtreJouee(false);
			 System.out.println("Quel est le joueur qui a mis cette carte?");
			 Joueur j=Partie.getjoueurEnCours().choisirUnJoueur();
			 j.getmain().add(c);
			 if (!Partie.getInterfaceGaphiqueActive()) {
				 MessageBox.getMessageBox().ajouterMessage("Vous avez ajouté la carte "+ c.getStrategie().getNom()+" à votre main");
			 }
		 }else{
			 c.jouerCarte();
			 Partie.getcarteEnCours().setPeutEtreJouee(false);
		 }
	 
 }else{
	 System.out.println("Cette carte n'a pas d'effet");
 }}
 
 /**
	 * Cette méthode permet un affichage de la capacité de sacrifice de la carte d'action
	 * implémentation de la méthode  toString() définie dans l'interface CapaciteSacrifice
	 */
 public String toString(){
		StringBuffer sb=new StringBuffer(nom+"\n");
		sb.append(
				"Permet de récupérer les effets bénéfiques d’une carte d’Action posée par une autre Divinité. S’il s’agit d’une carte Croyants ou Guide Spirituel , vous posez la carte devant vous" );
		return sb.toString();
	}
 
 /**
	 * Cette méthode permet de récupérer l'attribut nom de la classe
	 * @return le nom de la carte d'action associée à la capacité de sacrifice
	 * implémentation de la méthode getNom définie dans l'interface CapaciteSacrifice
	 */
 public String getNom() {
		return nom;
	}
}
