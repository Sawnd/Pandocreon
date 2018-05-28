package capaciteDivinite;

import elementsDeBase.*;
import java.util.*;
/**
 * La classe Capacit�4 repr�sente l'impl�mentation de la capacit� sp�ciale d'une divinite
 * la classe impl�mente l'interface CapaciteDivinite
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Capacite4 implements CapaciteDivinite {
	
	/**
	 * constructeur de la classe
	 */
	
    public Capacite4(){
		
	}
	
	/**
	 *  Cette m�thode permet d'utiliser la capacit� de la divinit� au cours d'une partie
	 * Il est demand� au joueur en cours de choisir un adversaire qu'il oblige � jouer une carte Apocalypse
	 */
		public void utiliserCapacite(){
		System.out.println("Sur quel joueur voulez-vous utiliser votre capacit�?");
		Partie.afficherLesJoueurs();
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		int n=sc.nextInt()-1;
		while (n-1>=Partie.listeJoueurs.size()){
			System.out.println("Veuillez saisir un nombre valide");
			n=sc.nextInt()-1;
		}
		Joueur j=Partie.listeJoueurs.get(n);
		LinkedList<CarteAction>main=j.getmain();
		ListIterator<CarteAction> it=main.listIterator();
		CarteAction c;
		boolean trouveApocalypse=false;
		while (it.hasNext() && !trouveApocalypse){
		  c = it.next();
		  if ( c instanceof Apocalypse){
			  ((Apocalypse) c).jouerCarte();
			  trouveApocalypse=true;
			  j.setmain(main);
		  }
		}
		if (!trouveApocalypse){
			System.out.println("Le joueur n'a pas de carte Apocalypse");
		}
	}
		
		/**
		 * Cette m�thode permet un affichage dans la console du pouvoir de la divinit�
		 */	
  public String toString(){
	  StringBuffer sb = new StringBuffer();
	  sb.append("Peut obliger un joueur � poser une carte Apocalypse s�il en poss�de une.");
  return sb.toString();
  }
}
