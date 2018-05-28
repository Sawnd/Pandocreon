package capaciteDivinite;
import java.util.*;
import elementsDeBase.*;
import interfaceGraphique.MessageBox;

/**
 * La classe Capacit�3 repr�sente l'impl�mentation de la capacit� sp�ciale des divivnit�s "Yarstur" et "Puis-Tara"
 * la classe impl�mente l'interface CapaciteDivinite
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Capacite3 implements CapaciteDivinite {
	
	/**
	 * l'attribut origine d�signe l'origine de la carte qui b�n�ficie de la capacit� sp�ciale
	 */
 private TypeOrigine origine;
 
 /**
  *  variable o� sont stock�es les cartes � enelver du centre de table
  */
 private LinkedList<Croyant> cartesARetirer =new LinkedList<Croyant>();
 
 /**
  * construteur de la classe
  * @param nom permet de modifier l'origine de la carte en fonction de la divinit� � laquelle la capacit� est associ�e
  */
	public Capacite3(String nom){
		
		switch(nom){
		case ("Yarstur"): origine= TypeOrigine.NEANT;
		break;
		case ("Pui-Tara"): origine=TypeOrigine.JOUR;	
		break;
		default :origine=TypeOrigine.JOUR;}
		
	}
	
	/**
	 *  Cette m�thode permet d'utiliser la capacit� de la divinit� au cours d'une partie
	 *  L'utilisation de la capacit� d�truit toutes les cartes pr�sentes sur le centre de table et possedant la m�me origine que la classe
	 */
	public void utiliserCapacite(){
		ListIterator<Croyant> it=Partie.centreDeTable.getCroyantsCentre().listIterator();
	   
	    Croyant c;
		while (it.hasNext()){
			
			c=it.next();
			if( c.getorigines()==origine){
				cartesARetirer.add(c);
			}
			
		}
		Partie.piocheCarteAction.listeCartesAction.addAll(cartesARetirer);
		Partie.centreDeTable.enleverCroyant(cartesARetirer);	
	MessageBox.getMessageBox().ajouterMessage("Les croyants "+origine+"  ont �t� d�truits!");
	}
	
	/**
	 * Cette m�thode permet un affichage dans la console du pouvoir de la divinit�
	 */
		public String toString(){
		StringBuffer sb = new StringBuffer();
		switch(origine){
		case NEANT: sb.append("Peut d�truire toutes les cartes Croyants au centre de la table d�Origine N�ant");
		break;
		case JOUR : sb.append(" Peut d�truire toutes les cartes Croyants au centre de la table d�Origine Jour");	
		break;
		default : sb.append(" Peut d�truire toutes les cartes Croyants au centre de la table d�Origine Jour");}	
	return sb.toString();
		
	}
}
