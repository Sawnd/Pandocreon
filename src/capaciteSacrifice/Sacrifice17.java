package capaciteSacrifice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import elementsDeBase.Croyant;
import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import elementsDeBase.TypeDogme;
import elementsDeBase.TypeOrigine;
import interfaceGraphique.MessageBox;
/**
 * La classe sacrifice17 represente l'implementation de la capacite speciale de sacrifice de cartes Croyant
 * la classe implemente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Sacrifice17 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacite speciale 
	 */
	private String nom = "Paladin";
	
	/**
	 * dogme de la carte possedant le sacrifice
	 */
	private TypeDogme dogme = TypeDogme.NATURE;
	
	/**
	 * origine de la carte possedant le sacrifice
	 */
	private TypeOrigine origine = TypeOrigine.JOUR;
	
	/**
	 * collection contenant les croyants e defausser
	 */
	private LinkedList<Croyant> cartesARetirer = new LinkedList<Croyant>();
	
	/**
	 * contructeur de la classe
	 */
	
public Sacrifice17(){
	
}

/**
 * Cette methode sacrifie tous les Croyants deOrigine Nuit ou Neant et ayant le Dogme Nature, actuellement sur la table sont defausses.
 *  Les capacites speciales ne sont pas jouees.");
 * implementation de la methode seSacriifer() definie dans l'interface capaciteSacrifice
 */
	public void seSacrifier() {
		ListIterator<Croyant> it = Partie.centreDeTable.getCroyantsCentre().listIterator();
		Croyant c;
		while (it.hasNext()) {
			c = it.next();
			if (c.getorigines() != origine) {
				if (c.getdogme1() == dogme || c.getdogme2() == dogme || c.getdogme3() == dogme) {
					cartesARetirer.add(c);
				}
			}
		}
		Partie.centreDeTable.enleverCroyant(cartesARetirer);
		Partie.piocheCarteAction.listeCartesAction.addAll(cartesARetirer);
		cartesARetirer.clear();
		Iterator<Joueur> it2 = Partie.listeJoueurs.iterator();

		Joueur j;
		GuideSpirituel g;
		LinkedList<GuideSpirituel> cartesRec;
		Croyant[] croyantsRec;
		Croyant c1;
		while (it2.hasNext()) {
			j = it2.next();
			cartesRec = j.getcartesRecuperees();
			Iterator<GuideSpirituel> it3 = cartesRec.iterator();
			while (it3.hasNext()) {
				g = it3.next();
				croyantsRec = g.getcroyantsGuides();
				for (int i = 0; i < g.nombreDeCroyantsRattachesMax() - 1; i++) {
					if (croyantsRec[i].getorigines() != origine) {
						c1 = croyantsRec[i];
						if (c1.getdogme1() == dogme || c1.getdogme2() == dogme || c1.getdogme3() == dogme) {

							cartesARetirer.add(c1);
							croyantsRec[i] = null;
						}
					}
				}
				g.setcroyantsGuides(croyantsRec);
			}
			j.setcartesRecuperees(cartesRec);

		}
		if (!Partie.getInterfaceGaphiqueActive()) {
		System.out.println("Les Croyants ont ete defausses!");
		}else{
			MessageBox.getMessageBox().ajouterMessage("Tous les Croyants deOrigine Nuit ou Neant et ayant le Dogme Nature, actuellement sur la table sont defausses. Les capacites speciales ne sont pas jouees");
		}
	}
	

	/**
	 * Cette methode permet de recuperer l'attribut nom de la classe
	 * @return le nom de la carte d'action associee e la capacite de sacrifice
	 * implementation de la methode getNom() definie dans l'interface CapaciteSacrifice
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Cette methode permet un affichage de la capacite de sacrifice de la carte d'action
	 * implementation de la methode  toString() definie dans l'interface CapaciteSacrifice
	 */
	public String toString() {
		StringBuffer sb=new StringBuffer(nom+"\n");
		sb.append(
				" Tous les Croyants deOrigine Nuit ou Neant et ayant le Dogme Nature, actuellement sur la table sont defausses. Les capacites speciales ne sont pas jouees.");
		return sb.toString();

	}

}
