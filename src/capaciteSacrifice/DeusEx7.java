package capaciteSacrifice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import elementsDeBase.*;
import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageBox;
/**
 * La classe DeusEx7 represente l'implementation de la capacite speciale de sacrifice de la carte DeusEx " Phoenix"
 * la classe implemente l'interface CapaciteSacrifice
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class DeusEx7 implements CapaciteSacrifice {
	
	/**
	 * nom de la  capacite speciale 
	 */
	private String nom="Phoenix";
	
	/**
	 * contructeur de la classe
	 */	
public DeusEx7(){ 
	
}

/**
 * Cette methode permet au joueur en cours de beneficier de la capacite speciale de leun de ses Croyants ou Guides Spirituels sans sacrifier la carte 
 * Le joueur choisit parmi ses cartes recuperees la carte dont il souhaite beneficier de la capacite
 * implementation de l amethode seSacriifer() definie dans l'interface capaciteSacrifice
 * On distingue le cas de la methode lorque l'interface graphique est active du cas oe le joueur joue en console
	 */
public void seSacrifier(){
	if (!Partie.getInterfaceGaphiqueActive()) {
		utiliserCapacite1();
	}else{
		utiliserCapacite2();
	}
}

/**
 * Methode utilisee lorsque le joueur joue en console
 */
@SuppressWarnings("resource")
public void utiliserCapacite1(){

	Joueur j1=Partie.getjoueurEnCours();
	GuideSpirituel g;
LinkedList<GuideSpirituel> cartesRec=j1.getcartesRecuperees();
	if (!cartesRec.isEmpty()) {
		System.out.println("De quelle carte voulez-vous copier la capacite? \n");
		j1.afficherCartesRecuperees();
		g=j1.choisirUnGuideSpirituel(cartesRec);
		System.out.println("Selectionnez une carte (0 pour le guide spirituel)");
		g.afficherCroyantsRecuperees();
		int l;
		Scanner sc4 = new Scanner(System.in);
		l = sc4.nextInt() - 1;
		while (l < -1 || l > g.nombreCroyantsRattaches()) {
			System.out.println("Veuillez saisir un nombre valide");
			l = sc4.nextInt() - 1;
		}

		if (l == -1) {
			g.jouerCarte();
		} else {
			Croyant[] croyantRec = g.getcroyantsGuides();
			Croyant c1 = croyantRec[l];
			c1.jouerCarte();
			
		}
	}
}

/**
 * Methode utilisee lorsque l'interace graphique est active
 */
	public void utiliserCapacite2(){
		Joueur j1=Partie.getjoueurEnCours();
		GuideSpirituel g;
		Croyant c;
	LinkedList<CarteAction> liste = new LinkedList<CarteAction>();
	LinkedList<GuideSpirituel> cartesRec=j1.getcartesRecuperees();
		if (!cartesRec.isEmpty()) {
			j1.afficherCartesRecuperees();
	Iterator<GuideSpirituel> it = cartesRec.iterator();
	while(it.hasNext()){
		CarteAction carte = it.next();
		liste.add(carte);
		((GuideSpirituel) carte).afficherCroyantsRecuperees();
	}
	String[] options = new String[2];
	options[0] = "Croyant";
	options[1] = "Guide Spirituel";
	String str = (String) JOptionPane.showInputDialog(null, "Quel guide spirituel choisissez vous?",
			 "Veuillez choisir un guide spirituel", JOptionPane.PLAIN_MESSAGE, null, options,
			options[0]);
	switch(str){
	case "Croyant": Partie.getjoueurEnCours().afficherCartesRecuperees();
	c = Partie.getjoueurEnCours().choisirUnCroyant(Partie.getjoueurEnCours().getCroyantsRecuperes());
	c.jouerCarte();
	break;
	
	case "Guide Spirituel":g= FenetrePrincipale.choisirUnGuideSpirituel(liste);
	g.jouerCarte();
	break;
	
	default : break;
	}
		}else{
			MessageBox.getMessageBox().ajouterMessage("Vous ne pouvez pas utiliser cette carte car vous n'avez pas encore guide de croyants");
			Partie.getjoueurEnCours().getmain().add((CarteAction) Partie.getcarteEnCours());
		}
}
	
	/**
	 * Cette methode permet de recuperer l'attribut nom de la classe
	 * @return le nom de la carte d'action associee e la capacite de sacrifice
	 * implementation de la methode getNom definie dans l'interface CapaciteSacrifice
	 */
public String getNom() {
	return nom;
}

/**
 * Cette methode permet un affichage de la capacite de sacrifice de la carte d'action
 * implementation de la methode  toString() definie dans l'interface CapaciteSacrifice
 */
public String toString(){
	StringBuffer sb=new StringBuffer(nom+"\n");
	sb.append("Permet de beneficier de la capacite speciale de leun de vos Croyants ou Guides Spirituels sans sacrifier la carte ");
	return sb.toString();
}
}
