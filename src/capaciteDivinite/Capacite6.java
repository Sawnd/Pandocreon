package capaciteDivinite;

import java.util.Scanner;
import elementsDeBase.TypeOrigine;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;

/**
 * La classe Capacite6 represente l'implementation de la capacite speciale d'une divivnite 
 * la classe implemente l'interface CapaciteDivinite
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */

public class Capacite6 implements CapaciteDivinite {
	
	/**
	 * constructeur de la classe 
	 */
	public Capacite6(){
	
}

/**
 *  Cette methode permet d'utiliser la capacite de la divinite au cours d'une partie
 * Il est demande au joueur en cours de choisir un adversaire dont il recupere les points d'action
 */
public void utiliserCapacite(){
	System.out.println("Sur quel joueur voulez-vous utiliser votre capacite?");
	Partie.afficherLesJoueurs();
	@SuppressWarnings("resource")
	Scanner sc= new Scanner(System.in);
	int n=sc.nextInt()-1;
	while (n-1>=Partie.listeJoueurs.size()){
		System.out.println("Veuillez saisir un nombre valide");
		n=sc.nextInt()-1;
	}
	Joueur j=Partie.listeJoueurs.get(n);
	Joueur joueurEnCours=Partie.getjoueurEnCours();
	TypeOrigine origine =Partie.getOrigineDuTour();
	
	System.out.println(j);
	j.afficherLesPoints();
	System.out.println(joueurEnCours);
	joueurEnCours.afficherLesPoints();
	
	if (j.getorigineDivinite() == origine && origine == TypeOrigine.JOUR){
		j.setpointsJour(-2);
		joueurEnCours.setpointsJour(2);
	}else if(j.getorigineDivinite() == TypeOrigine.AUBE && origine == TypeOrigine.JOUR){
		j.setpointsJour(-1);
		joueurEnCours.setpointsJour(1);
	}else if (j.getorigineDivinite() == origine && origine == TypeOrigine.NUIT){
		j.setpointsNuit(-2);
		joueurEnCours.setpointsNuit(2);
	}else if(j.getorigineDivinite() == TypeOrigine.CREPUSCULE && origine == TypeOrigine.NUIT){
		j.setpointsNuit(-1);
		joueurEnCours.setpointsNuit(1);
	}else if (j.getorigineDivinite() == TypeOrigine.CREPUSCULE && origine == TypeOrigine.NEANT){
			j.setpointsNeant(-1);
			joueurEnCours.setpointsNeant(1);
    }else if(j.getorigineDivinite() == TypeOrigine.AUBE && origine == TypeOrigine.NEANT){
			j.setpointsNeant(-1);
			joueurEnCours.setpointsNeant(1);
		}
	System.out.println("Voici la nouvelle repartition des points : ");
	System.out.println(j);
	j.afficherLesPoints();
	System.out.println(joueurEnCours);
	joueurEnCours.afficherLesPoints();
}

/**
 * Cette methode permet un affichage dans la console du pouvoir de la divinite
 */
public String toString(){
	StringBuffer sb = new StringBuffer();
	
	sb.append(": Peut recuperer les points deActions deune autre Divinite en plus des siens. Leautre Divinite ne reeoit aucun point deAction ce tour-ci.");
	return sb.toString();
}
}
