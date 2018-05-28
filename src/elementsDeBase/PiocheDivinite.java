/**
 * 
 */
package elementsDeBase;
import java.util.*;

import capaciteDivinite.*;
/**
 * @author Anne-Sophie
 *
 */
public class PiocheDivinite extends Pioche{
	
	/**
	 * collection contenant les cartes de la pioche
	 */	
public LinkedList<Divinite> listeDivinites;

	/**
	 * constructeur de la classe
	 */
	public PiocheDivinite() {
		super();
	 listeDivinites = new LinkedList<Divinite>();
		creerCartesDivinite();
	}
	
	
	/**
	 * methode pour tirer la carte du dessus de la pioche
	 * @return la carte tiree
	 * 
	 */
	public Divinite tirerCarteDuDessus(LinkedList<Divinite> listeD){
		return listeD.pop();
			}
	
	
	/**
	 * permet de creer les cartes Divinite
	 */
	public void creerCartesDivinite(){
		
    	Capacite4 capacite1= new Capacite4();//yes
    	Divinite c1 = new Divinite("Llewella",capacite1, TypeOrigine.NUIT, TypeDogme.CHAOS, TypeDogme.MYSTIQUE, TypeDogme.NATURE,101);
    	listeDivinites.add(c1);
    	Capacite3 capacite2 = new Capacite3("Pui-Tara");// yes
    	Divinite c2 = new Divinite("Pui-Tara",capacite2, TypeOrigine.NUIT, TypeDogme.SYMBOLE, TypeDogme.MYSTIQUE, TypeDogme.NATURE,102);
    	listeDivinites.add(c2);
    	Capacite5 capacite3 = new Capacite5();//yes
    	Divinite c3 = new Divinite("Gwenghelen",capacite3, TypeOrigine.AUBE, TypeDogme.HUMAIN, TypeDogme.MYSTIQUE, TypeDogme.SYMBOLE,103);
    	listeDivinites.add(c3);
    	Capacite2 capacite4 = new Capacite2();
    	Divinite c4 = new Divinite("Brewalen",capacite4, TypeOrigine.JOUR, TypeDogme.SYMBOLE, TypeDogme.HUMAIN, TypeDogme.NATURE,104);
    	listeDivinites.add(c4);
    	Capacite8 capacite5 = new Capacite8();
    	Divinite c5 = new Divinite("Shingva",capacite5, TypeOrigine.AUBE, TypeDogme.SYMBOLE, TypeDogme.MYSTIQUE, TypeDogme.HUMAIN,105);
    	listeDivinites.add(c5);
    	Capacite1 capacite6 = new Capacite1();
    	Divinite c6 = new Divinite("Drinded",capacite6, TypeOrigine.NUIT, TypeDogme.SYMBOLE, TypeDogme.HUMAIN, TypeDogme.NATURE,106);
    	listeDivinites.add(c6);
    	Capacite6 capacite7 = new Capacite6();//yes
    	Divinite c7 = new Divinite("Gorpa",capacite7, TypeOrigine.CREPUSCULE, TypeDogme.CHAOS, TypeDogme.HUMAIN, TypeDogme.SYMBOLE,107);
    	listeDivinites.add(c7);
    	Capacite3 capacite8 = new Capacite3("Yarstur");//yes
    	Divinite c8 = new Divinite("Yarstur",capacite8, TypeOrigine.JOUR, TypeDogme.CHAOS, TypeDogme.MYSTIQUE, TypeDogme.SYMBOLE,108);
    	listeDivinites.add(c8);
    	Capacite7 capacite9 = new Capacite7();
    	Divinite c9 = new Divinite("Romtec", capacite9, TypeOrigine.CREPUSCULE, TypeDogme.CHAOS, TypeDogme.MYSTIQUE, TypeDogme.NATURE,109);
    	listeDivinites.add(c9);
    	Divinite c10 = new Divinite("Killinstred",capacite1, TypeOrigine.NUIT, TypeDogme.CHAOS, TypeDogme.MYSTIQUE, TypeDogme.NATURE,110);
    	listeDivinites.add(c10);
    	/**
    	 * permet de melanger la collection
    	 */
    	Collections.shuffle(listeDivinites);
    	
    	}

}
