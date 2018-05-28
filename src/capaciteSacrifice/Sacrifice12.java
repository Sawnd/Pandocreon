package capaciteSacrifice;

import java.util.ListIterator;

import elementsDeBase.Croyant;
import elementsDeBase.Partie;
import elementsDeBase.TypeDogme;
import interfaceGraphique.MessageBox;

import java.util.*;

/**
 * La classe sacrifice12 représente l'implémentation de la capacité spéciale de sacrifice de cartes Croyant
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice12 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacité spéciale 
	 */
	private String nom="Tyran";
	
	/**
	 * origine de la carte
	 */
	private TypeDogme dogme = TypeDogme.MYSTIQUE;
	
	/**
	 * collection contenant l'ensemble des cartes à retirer
	 */
	private LinkedList<Croyant> cartesARetirer=new LinkedList<Croyant>();
	
	/**
	 * contructeur de la classe
	 */
	public Sacrifice12(){
		}
	
	/**
	 * Cette méthode de défausser tous les Croyants ayant le Dogme Mystique actuellement au centre de la Table
	 * implémentation de la méthode seSacriifer() définie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier(){
		ListIterator<Croyant> it=Partie.centreDeTable.getCroyantsCentre().listIterator();
	    Croyant c ;
		while (it.hasNext()){
			
			c=it.next();			

			if( c.getdogme1()== dogme||c.getdogme2()==dogme||c.getdogme3()==dogme){
				cartesARetirer.add(c);
			}				
		}
		Partie.centreDeTable.enleverCroyant(cartesARetirer);
		Partie.piocheCarteAction.listeCartesAction.addAll(cartesARetirer);
		MessageBox.getMessageBox().ajouterMessage("Tous les Croyants ayant le dogme Mystique ont été défaussé! \n");
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
		sb.append(" Défausse tous les Croyants ayant le Dogme Mystique actuellement au centre de la Table");
		return sb.toString();
		
		
	}

}
