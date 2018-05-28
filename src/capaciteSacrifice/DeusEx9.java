package capaciteSacrifice;

import elementsDeBase.*;
import interfaceGraphique.MessageBox;

/**
 * La classe DeusEx9 repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice de la carte DeusEx " Transe"
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx9 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacit� sp�ciale 
	 */	
private String nom="Transe";

/**
 * contructeur de la classe
 */
 public DeusEx9(){
	 
 }
 
 /**
	 * Cette m�thode permet au joueur en cours de r�cup�rer les effets b�n�fiques d�une carte d�Action pos�e par une autre Divinit�. 
	 * S�il s�agit d�une carte Croyants ou Guide Spirituel , le joueur en cours r�cup�re la carte
	 * impl�mentation de la m�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
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
				 MessageBox.getMessageBox().ajouterMessage("Vous avez ajout� la carte "+ c.getStrategie().getNom()+" � votre main");
			 }
		 }else{
			 c.jouerCarte();
			 Partie.getcarteEnCours().setPeutEtreJouee(false);
		 }
	 
 }else{
	 System.out.println("Cette carte n'a pas d'effet");
 }}
 
 /**
	 * Cette m�thode permet un affichage de la capacit� de sacrifice de la carte d'action
	 * impl�mentation de la m�thode  toString() d�finie dans l'interface CapaciteSacrifice
	 */
 public String toString(){
		StringBuffer sb=new StringBuffer(nom+"\n");
		sb.append(
				"Permet de r�cup�rer les effets b�n�fiques d�une carte d�Action pos�e par une autre Divinit�. S�il s�agit d�une carte Croyants ou Guide Spirituel , vous posez la carte devant vous" );
		return sb.toString();
	}
 
 /**
	 * Cette m�thode permet de r�cup�rer l'attribut nom de la classe
	 * @return le nom de la carte d'action associ�e � la capacit� de sacrifice
	 * impl�mentation de la m�thode getNom d�finie dans l'interface CapaciteSacrifice
	 */
 public String getNom() {
		return nom;
	}
}
