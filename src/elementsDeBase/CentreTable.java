/**
 * 
 */
package elementsDeBase;
import java.util.*;
/**
* La classe CentreTable represente le centre de table du jeu 
* Elle herite de la classe observable
* @author Anne-Sophie Ndoumbe
* @author Stephane Deffon
*/
public class CentreTable extends Observable{
	
	/**
	 * collection contennat les croyants mis au centre de la table
	 */
private ArrayList<Croyant> croyantsCentre ;


	/**
	 * Le constructuteur du centre de la table permet de creer une collection qui permettra de gerer plus facilement les cartes au centre de la table
	 */
	public CentreTable() {
		croyantsCentre =new ArrayList<Croyant>();
		
	}
	
	/**
	 * Cette Methode permet au joueur de deposer des croyants au centre de la table 
	 * @param croy permet de determiner de quelle carte croyant il s'agit
	 * Les observateurs de la classe sont egalement notifies du changement
		 */
	public void ajouterCroyant(Croyant croy){ 
	this.croyantsCentre.add(croy);
	setChanged();
	notifyObservers(this);
	}  
	
	/**
	 * Permet d'enlever un croynat du centre de la table
	 * @param croy est la carte e enlever
	 * Les observateurs de la classe sont egalement notifies du changement
	 * 
	 */
	public void enleverCroyant(Croyant croy){ 
		this.croyantsCentre.remove(croy);
		setChanged();
		notifyObservers(this);
		}
	
	/**
	 * permet de modifier l'attribut des peutEtreGuidee de toutes les cartes croyant du centre de table
	 */
	public static void setCroyantsPeuventEtreGuides(){
		Iterator<Croyant> it=Partie.centreDeTable.croyantsCentre.iterator();
		Croyant c;
		while(it.hasNext()){
			c=it.next();
			c.setCroyantPeutGuide();
		}
	}
	
	
	/**
	 * permet d'ajouter une collection de croyants au centre de table
	 * @param croy est la collection e rajouter
	 * les observateurs de la classe sont notifies du changement
	 */
	public void ajouterCroyant(Collection<Croyant> croy){
		this.croyantsCentre.addAll(croy);
		setChanged();
		notifyObservers(this);
	} 
	
	
	/**
	 * permet d'enlever une collection du centre de table
	 * @param croy est la collection e nelever en question
	 * les observayeurs de la classe sont notifies du changement
	 */
	public void enleverCroyant(Collection<Croyant> croy){
		this.croyantsCentre.addAll(croy);
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * permet de recuperer la collection de croyants au centre de la table
	 * @return la collection en question
	 */
	public ArrayList<Croyant> getCroyantsCentre(){
		return this.croyantsCentre;
	}

}
