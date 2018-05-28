package capaciteSacrifice;

import java.util.LinkedList;
import java.util.ListIterator;

import elementsDeBase.CarteAction;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import interfaceGraphique.MessageBox;


/**
 * La classe DeusEx5 repr�sente l'impl�mentation de la capacit� sp�ciale de sacrifice de la carte DeusEx " Diversion"
 * la classe impl�mente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx5 implements CapaciteSacrifice{
	
	/**
	 * nom de la  capacit� sp�ciale 
	 */
	private String nom="Diversion";
	
	/**
	 * contructeur de la classe
	 */
	public DeusEx5(){
		
	}
	
	/**
	 * Cette m�thode permet au joueur en cours de prendre 3 cartes dans la main d�un autre joueur et les inclure  � sa main
	 * impl�mentation de l am�thode seSacriifer() d�finie dans l'interface capaciteSacrifice
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
		sb.append(
				"Prenez 3 cartes dans la main d�un autre joueur et incluez les � votre main" );
		return sb.toString();
	}
}
