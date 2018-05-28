package capaciteDivinite;

import java.util.*;

import elementsDeBase.CarteAction;
import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.Partie;
import elementsDeBase.TypeDogme;

/**
 * La classe Capacité8 représente l'implémentation de la capacité spéciale de la divivnité "Drinded"
 * la classe implémente l'interface CapaciteDivinite
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class Capacite8 implements CapaciteDivinite {
	
	/*
	 * Scanner qui permet de récuprer les valeurs saisies au claviers par un utilisateur
	 */
 private Scanner sc = new Scanner(System.in);
 
    /**
	 * constructeur de la classe 
	 */
	public Capacite8() {

	}

	
	/**
	 *  Cette méthode permet d'utiliser la capacité de la divinité au cours d'une partie
	 * Il est demandé au joueur en cours de choisir un adversaire à qui im impose le sacrifice d’un Guide Spirituel ayant le Dogme Symboles ou Nature
	 */
	public void utiliserCapacite() {
		System.out.println("Sur quel joueur voulez-vous utiliser votre capacité?");
		Partie.afficherLesJoueurs();
		int n = sc.nextInt() - 1;
		while (n - 1 >= Partie.listeJoueurs.size()) {
			System.out.println("Veuillez saisir un nombre valide");
			n = sc.nextInt() - 1;
		}
		Joueur j = Partie.listeJoueurs.get(n);
		LinkedList<CarteAction> main = j.getmain();
		LinkedList<GuideSpirituel> cartesRec = j.getcartesRecuperees();
		ListIterator<CarteAction> it1 = main.listIterator();
		ListIterator<GuideSpirituel> it2 = cartesRec.listIterator();

		System.out.println("Guides Spirituels dans la main de " + j);
		while (it1.hasNext()) {
			CarteAction carte1 = it1.next();
			int position1 = it1.nextIndex();
			if (carte1 instanceof GuideSpirituel
					&& ((carte1.getdogme1() == TypeDogme.NATURE || carte1.getdogme1() == TypeDogme.SYMBOLE)
							|| (carte1.getdogme2() == TypeDogme.NATURE || carte1.getdogme2() == TypeDogme.SYMBOLE))) {
				System.out.println("Carte [" + position1 + "] :" + carte1);
			}

		}
		System.out.println("Guides Spirituels dans les cartes récupérées de " + j);
		while (it2.hasNext()) {
			CarteAction carte2 = it2.next();
			int position2 = it2.nextIndex();
			if (carte2 instanceof GuideSpirituel
					&& ((carte2.getdogme1() == TypeDogme.NATURE || carte2.getdogme1() == TypeDogme.SYMBOLE)
							|| (carte2.getdogme2() == TypeDogme.NATURE || carte2.getdogme2() == TypeDogme.SYMBOLE))) {
				System.out.println("Carte [" + position2 + "] :" + carte2);
			}
		}
		System.out.println("Voulez-vous choisir un Guide Spirituel dans la main(1) ou dans les cartes récupérées(2) de "
				+ j + " ?");
		int i = sc.nextInt();
		while (i < 1 || i > 2) {
			System.out.println("Veuillez entrer un nombre valide");
			i = sc.nextInt();
		}
		
		int k;
		GuideSpirituel guide;
		switch (i) {
		case 1:
			System.out.println("Main de " + j);
			System.out.println("Saisissez le numero de la carte dont vous voulez utiliser la capacité: ");
			k = sc.nextInt() - 1;
			while ((main.get(k) instanceof GuideSpirituel) == false) {
				System.out.println("Veuillez entrer un nombre valide");
				k = sc.nextInt();
			}
			guide = (GuideSpirituel) main.remove(k);
			guide.utiliserSacrifice();
			j.setmain(main);
			break;
		case 2:
			System.out.println("Cartes récupérées de " + j);
			System.out.println("Saisissez le numero de la carte dont vous voulez utiliser la capacité: ");
			k = sc.nextInt() - 1;
			while ((cartesRec.get(k) instanceof GuideSpirituel) == false) {
				System.out.println("Veuillez entrer un nombre valide");
				k = sc.nextInt();
			}
			guide = (GuideSpirituel) cartesRec.remove(k);
			guide.utiliserSacrifice();
			j.setcartesRecuperees(cartesRec);
			break;
		default:
			break;
		}

	}
	
	/**
	 * Cette méthode permet un affichage dans la console du pouvoir de la divinité
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Peut imposer le sacrifice d’un Guide Spirituel ayant le Dogme Symboles ou Nature.");
		return sb.toString();
	}
}
