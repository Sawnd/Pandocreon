package capaciteDivinite;
import java.util.*;
import elementsDeBase.*;
import interfaceGraphique.MessageBox;

/**
 * La classe Capacité3 représente l'implémentation de la capacité spéciale des divivnités "Yarstur" et "Puis-Tara"
 * la classe implémente l'interface CapaciteDivinite
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Capacite3 implements CapaciteDivinite {
	
	/**
	 * l'attribut origine désigne l'origine de la carte qui bénéficie de la capacité spéciale
	 */
 private TypeOrigine origine;
 
 /**
  *  variable où sont stockées les cartes à enelver du centre de table
  */
 private LinkedList<Croyant> cartesARetirer =new LinkedList<Croyant>();
 
 /**
  * construteur de la classe
  * @param nom permet de modifier l'origine de la carte en fonction de la divinité à laquelle la capacité est associée
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
	 *  Cette méthode permet d'utiliser la capacité de la divinité au cours d'une partie
	 *  L'utilisation de la capacité détruit toutes les cartes présentes sur le centre de table et possedant la même origine que la classe
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
	MessageBox.getMessageBox().ajouterMessage("Les croyants "+origine+"  ont été détruits!");
	}
	
	/**
	 * Cette méthode permet un affichage dans la console du pouvoir de la divinité
	 */
		public String toString(){
		StringBuffer sb = new StringBuffer();
		switch(origine){
		case NEANT: sb.append("Peut détruire toutes les cartes Croyants au centre de la table d’Origine Néant");
		break;
		case JOUR : sb.append(" Peut détruire toutes les cartes Croyants au centre de la table d’Origine Jour");	
		break;
		default : sb.append(" Peut détruire toutes les cartes Croyants au centre de la table d’Origine Jour");}	
	return sb.toString();
		
	}
}
