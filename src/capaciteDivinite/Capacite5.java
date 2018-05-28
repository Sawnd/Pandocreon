package capaciteDivinite;

import elementsDeBase.Partie;
import elementsDeBase.Joueur;
import elementsDeBase.CarteAction;
import java.util.*;
import elementsDeBase.GuideSpirituel;

/**
 * La classe Capacité5 représente l'implémentation de la capacité spéciale d'une divivnité 
 * la classe implémente l'interface CapaciteDivinite
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Capacite5 implements CapaciteDivinite {
	
	/**
	 * consructeur  de la classe
	 */
	public Capacite5(){
		
	}
	
	public void utiliserCapacite(){
		Joueur j= Partie.getjoueurEnCours();
		LinkedList<CarteAction> main=j.getmain();
		LinkedList<GuideSpirituel> cartesRec=j.getcartesRecuperees();
		ListIterator<CarteAction> it1=main.listIterator();
		ListIterator<GuideSpirituel> it2 =cartesRec.listIterator();
		int compteur=0;
	
		while (it1.hasNext()){
			CarteAction carte1=it1.next();
			if (carte1 instanceof GuideSpirituel){
				compteur++;
			}
		}
		while(it2.hasNext()){
			CarteAction carte2=it2.next();
			if(carte2 instanceof GuideSpirituel){
				compteur++;
			}			
		}
		System.out.println("Vous avez " + compteur + " Guides Spirituels.");
		j.setpointsNeant(compteur);
	}

	public String toString(){
		StringBuffer sb=new StringBuffer();
		sb.append("Récupère autant de points d’Action supplémentaires d’Origine Néant que le nombre de Guides Spirituels que la divinité possède.");
        return sb.toString();	
	}
}
