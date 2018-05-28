package capaciteSacrifice;

import java.util.ListIterator;

import elementsDeBase.Croyant;
import elementsDeBase.Partie;
import elementsDeBase.TypeDogme;
import interfaceGraphique.MessageBox;

import java.util.*;

/**
 * La classe sacrifice12 repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice de cartes Croyant
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice12 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacit� sp�ciale 
	 */
	private String nom="Tyran";
	
	/**
	 * origine de la carte
	 */
	private TypeDogme dogme = TypeDogme.MYSTIQUE;
	
	/**
	 * collection contenant l'ensemble des cartes � retirer
	 */
	private LinkedList<Croyant> cartesARetirer=new LinkedList<Croyant>();
	
	/**
	 * contructeur de la classe
	 */
	public Sacrifice12(){
		}
	
	/**
	 * Cette m�thode de d�fausser tous les Croyants ayant le Dogme Mystique actuellement au centre de la Table
	 * impl�mentation de la m�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
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
		MessageBox.getMessageBox().ajouterMessage("Tous les Croyants ayant le dogme Mystique ont �t� d�fauss�! \n");
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
		sb.append(" D�fausse tous les Croyants ayant le Dogme Mystique actuellement au centre de la Table");
		return sb.toString();
		
		
	}

}
