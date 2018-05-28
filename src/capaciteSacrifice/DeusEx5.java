package capaciteSacrifice;

import java.util.LinkedList;
import java.util.ListIterator;

import elementsDeBase.CarteAction;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import interfaceGraphique.MessageBox;


/**
 * La classe DeusEx5 représente l'implémentation de la capacité spéciale de sacrifice de la carte DeusEx " Diversion"
 * la classe implémente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx5 implements CapaciteSacrifice{
	
	/**
	 * nom de la  capacité spéciale 
	 */
	private String nom="Diversion";
	
	/**
	 * contructeur de la classe
	 */
	public DeusEx5(){
		
	}
	
	/**
	 * Cette méthode permet au joueur en cours de prendre 3 cartes dans la main d’un autre joueur et les inclure  à sa main
	 * implémentation de l améthode seSacriifer() définie dans l'interface capaciteSacrifice
	 */
	public void seSacrifier(){
		Joueur j1=Partie.getjoueurEnCours();
		CarteAction c1;
		LinkedList<CarteAction> mainJ;
		LinkedList<CarteAction> main;
		Joueur j=j1.choisirUnJoueur();
		main=j1.getmain();
		mainJ=j.getmain();
		for (int i=0; i<3; i++){
			if(mainJ.isEmpty()){
				break;
			}
			int num= (int) Math.round(Math.random()*mainJ.size());
			if(!mainJ.isEmpty()){
		c1=mainJ.remove(num-1);
		main.add(c1);		}else {
			if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println("La main du joueur est vide");
			}else{
				MessageBox.getMessageBox().ajouterMessage("La main du joueur est vide");
			}
		}
	}
		j.setmain(mainJ);
		ListIterator<Joueur> it= Partie.listeJoueurs.listIterator();
		Joueur j2;
		while (it.hasNext()){
			j2=it.next();
			if(j2==j1){
				j2.setmain(j1.getmain());
			}
			if(j2==j){
				j2.setmain(j.getmain());
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
				"Prenez 3 cartes dans la main d’un autre joueur et incluez les à votre main" );
		return sb.toString();
	}
}
