/**
 * 
 */
package elementsDeBase;

import java.util.*;
import interfaceGraphique.FenetreChoix;
import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageBox;
/**
* La classe Carte est une classe representant un joueur 
* @author Anne-Sophie Ndoumbe
* @author Stephane Deffon
* Elle herite de l'interface Observable
*/
public class Joueur extends Observable {
	/**
	 * nom de u joueur
	 */
	protected String nom;
	/**
	 * points neant du joueur
	 */
	protected int pointsNeant;
	/**
	 * points nuits du joueur
	 */
	protected int pointsNuit;
	/**
	 * points jour du joueur
	 */
	protected int pointsJour;
	/**
	 * points de priere du joueur 
	 */
	protected int pointsPriere;
	/***
	 * nombre de cartes dans la maisn du joueur
	 */
	protected int nombreCartesMain;
	/**
	 * collection de cartes dans la main du joueur
	 */
	protected LinkedList<CarteAction> main; 
	/**
	 * collection de guides spirituels du joueur ayant guide des croyants
	 */
	protected LinkedList<GuideSpirituel> cartesRecuperees;
	/**
	 * divinite du joueur
	 */
	protected Divinite divinite;
	/**
	 * numero du joueur
	 */
	static protected int numeroJoueur = 0;
	/**
	 * booleen permettant de savoir si le joueur a deje pioche
	 */
	protected boolean aPioche = false; 
	/**
	 * attribu contenant la fenetre de choix de l'interface graphique
	 */
	protected FenetreChoix fenetre;
	/**
	 * booleen contenant l'unique instance de la partie
	 */
	private Partie partie = Partie.getPartie();
	/**
	 * boooleen permettant de savoir si un joueur a deje joue
	 */
	protected boolean aDejaJoue = false; 

/**
 * constructeur de la classe
 */
	public Joueur() {
		numeroJoueur++;
		
		this.addObserver(partie);
		pointsJour = pointsNeant = pointsNuit = 0;
		main = new LinkedList<CarteAction>();
		cartesRecuperees = new LinkedList<GuideSpirituel>();
	
	}

	/**
	 * Permet de modifier la valeur de l'attribut divinite
	 * @param div valeur e affecter e l'attribut
	 */
	public void setDivinite(Divinite div) {
		this.divinite = div;
	}
/**
 * permet de modifier la valeur des points Jour 
 * @param point valeur e affecter
 */
	public void setpointsJour(int point) {
		pointsJour += point;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * permet de modifier la valeur des points Nuit 
	 * @param point valeur e affecter
	 */
		
	public void setpointsNuit(int point) {
		pointsNuit += point;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * permet de modifier la valeur des points Neant
	 * @param point valeur e affecter
	 */
		
	public void setpointsNeant(int point) {
		pointsNeant += point;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * permet d'ajouter une care e la main du joueur
	 * @param carte carte e ajouter
	 */
	public void ajouterCarte(CarteAction carte) {
		main.add(carte);
	}

	/**
	 * Cette Methode permet au joueur de deposer des croyants au centre de la
	 * table tout en les enlevant de la main du joueur
	 * @throws InputMismatchException
	 * @throws IndexOutOfBoundsException
	 */
	
	@SuppressWarnings("resource")
	public void sacrifierCartes() throws InputMismatchException, IndexOutOfBoundsException {

		GuideSpirituel g;
		int j;
		if (!this.cartesRecuperees.isEmpty()) {

			System.out.println("Quelle carte voulez-vous sacrifier? \n");
			this.afficherCartesRecuperees();
			Scanner sc3 = new Scanner(System.in);
			System.out.println("Selectionnez un Guide Spirituel :");
			j = sc3.nextInt() - 1;
			while (j > cartesRecuperees.size()) {
				System.out.println("Veuillez saisir un nombre valide");
				j = sc3.nextInt() - 1;
			}

			g = cartesRecuperees.remove(j);
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
				Partie.setcarteEnCours(g);
				this.fauxTour();
				g = (GuideSpirituel) Partie.getcarteEnCours();
				if (g.peutEtreSacrifiee) {
					g.jouerCarte();

					Partie.piocheCarteAction.listeCartesAction.add(g);
				} else {
					System.out.println("Cette carte ne peut pas etre sacrifie ce tour-ci");
				}
			} else {
				Croyant[] croyantRec = g.getcroyantsGuides();
				Croyant c1 = croyantRec[l];
				Partie.setcarteEnCours(c1);
				this.fauxTour();
				c1 = (Croyant) Partie.getcarteEnCours();
				if (c1.peutEtreSacrifiee) {
					c1.jouerCarte();
					croyantRec[l] = null;
					g.setcroyantsGuides(croyantRec);
					Partie.piocheCarteAction.listeCartesAction.add(c1);
					cartesRecuperees.add(g);
				} else {
					System.out.println("Cette carte ne peut pas etre sacrifie ce tour-ci");
				}
			}
		} else {
			System.out.println("Vous n'avez recupere aucune carte! ");

		}
		this.defausserGuideVide();
	}

	/**
	 * Methode permettant au joueur de sacrifier des cartes d'action
	 * @param c carte d'action e sacrifier
	 */
	public void sacrifierCartes(CarteAction c) {
		if (c instanceof Apocalypse) {
			if (Partie.compteurApo() > 1) {
				if (this.peutJouer(c)) {
					c.jouerCarte();
					if (this.main.contains(c)) {
						this.removeCarte(c);
					}
					if (this.getcartesRecuperees().contains(c)) {
						this.removeCarteRecuperee((GuideSpirituel) c);
					}
					Partie.piocheCarteAction.listeCartesAction.add(c);
					setChanged();
					notifyObservers(this);
				}
			} else {
				MessageBox.getMessageBox().ajouterMessage("Une carte Apocalypse a deje ete jouee au tour precedent");

			}
		} else if (c instanceof DeusEx) {
			if (this.peutJouer(c)) {
				c.jouerCarte();
				if (this.main.contains(c)) {
					this.removeCarte(c);
				}
				if (this.getcartesRecuperees().contains(c)) {
					this.removeCarteRecuperee((GuideSpirituel) c);
				}
				Partie.piocheCarteAction.listeCartesAction.add(c);
				setChanged();
				notifyObservers(this);
			}
		} else {

			if (c.peutEtreSacrifiee) {
				c.jouerCarte();
				// ajouter faux tour
				if (this.main.contains(c)) {
					this.removeCarte(c);
				}
				if (this.getcartesRecuperees().contains(c)) {
					this.removeCarteRecuperee((GuideSpirituel) c);
				}
				Partie.piocheCarteAction.listeCartesAction.add(c);
				setChanged();
				notifyObservers(this);
			} else {
				MessageBox.getMessageBox().ajouterMessage("Cette carte ne peut pas etre sacrifie ce tour-ci");
			}
			this.defausserGuideVide();
		}
	}
/**
 * Methode permettant de mettre un croyant au centre
 * @param centre centre de table
 * @throws InputMismatchException
 * @throws IndexOutOfBoundsException
 */
	@SuppressWarnings("resource")
	public void mettreCroyantAuCentre(CentreTable centre) throws InputMismatchException, IndexOutOfBoundsException {
		this.afficherCarte();
		System.out.println("Quel Croyant voulez vous mettre au centre?");
		Scanner sc = new Scanner(System.in);
		int position = sc.nextInt() - 1;
		while (position >= 0 && !main.isEmpty() && position < this.main.size()) {
			Croyant c;
			if (main.get(position) instanceof Croyant) {// new
				c = (Croyant) main.get(position);
				System.out.println(c);
				if (this.peutJouer(c)) {
					main.remove(position);
					centre.ajouterCroyant(c);
					this.afficherCarte();
					position = sc.nextInt() - 1;
				} else {
					position = sc.nextInt() - 1;
				}
			} else {
				System.out.println("Ce n'est pas une carte Croyant ! Veuillez reesayer");
				position = sc.nextInt() - 1;
			}
		}
	}

	/**
	 * Methode permettant de mettre un croyant au centre
	 * @param c croyant e mettre a centre
	 */
	public void mettreCroyantAuCentre(Croyant c) {
		if (this.peutJouer(c)) {
			main.remove(c);
			Partie.centreDeTable.ajouterCroyant(c);
		}
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Methode permettant de savoir si une carte peut etre jouee
	 * @param c carte pour laquelle il faut verifier 
	 * @returnbooleen permettant de savoir si la carte peut etre jouee
	 * @throws InputMismatchException
	 * @throws IndexOutOfBoundsException
	 */
	@SuppressWarnings("resource")
	public boolean peutJouer(Carte c) throws InputMismatchException, IndexOutOfBoundsException {
		boolean peutEtreJouee = true;
		if (c.getPeutEtreJouee()) {
			if (c.origines == TypeOrigine.JOUR && pointsJour >= 1) {
				setpointsJour(-1);
			} else if (c.origines == TypeOrigine.NULL) {

			} else if (c.origines == TypeOrigine.NUIT && pointsNuit >= 1) {

				setpointsNuit(-1);
			} else if (c.origines == TypeOrigine.NEANT && (pointsNuit >= 2 || pointsJour >= 2 || pointsNeant >= 1)) {
				if (pointsNeant >= 1) {
					setpointsNeant(-1);
				} else if (pointsNuit >= 2 && pointsJour >= 2) {
					System.out.println("Points Jour(1) ou Nuit(2)?");
					Scanner sc2 = new Scanner(System.in);
					int n = sc2.nextInt();
					switch (n) {
					case 1:
						setpointsJour(-2);
						break;
					case 2:
						setpointsNuit(-2);
						break;
					default:
						setpointsJour(-2);
					}
				} else if (pointsNuit >= 2) {
					setpointsNuit(-2);
				} else {
					setpointsJour(-2);
				}
			} else {
				MessageBox.getMessageBox()
						.ajouterMessage("Vous n'avez pas assez de points pour deposer cette carte au centre");
				peutEtreJouee = false;// new
			}
		} else

		{
			System.out.println("Cette carte a ete bloquee");
			peutEtreJouee = false;
		}
		return peutEtreJouee;
	}
	/**
	 * Methode permettant de recuperer des croyants
	 * @throws InputMismatchException
	 * @throws IndexOutOfBoundsException
	 */

	@SuppressWarnings("resource")
	public void recupererCroyants() throws InputMismatchException, IndexOutOfBoundsException { 
		
		boolean possedeGuide = false;
		ListIterator<CarteAction> it = main.listIterator();
		while (it.hasNext()) {
			CarteAction c = it.next();
			int n = it.nextIndex();
			if (c instanceof GuideSpirituel) {
				System.out.println("Carte " + "[" + n + "]" + " : " + c);
				possedeGuide = true;
			}
		}
		if (possedeGuide && !Partie.centreDeTable.getCroyantsCentre().isEmpty()) {
			System.out.println("Avec quel guide spirituel?");
			Scanner sc = new Scanner(System.in);
			int position;
			do {
				position = sc.nextInt() - 1;
			} while (position != -1 && position > main.size() - 1 && !(main.get(position) instanceof GuideSpirituel));
			if (position != -1) {
				GuideSpirituel guide = (GuideSpirituel) main.get(position);
				Partie.setcarteEnCours(guide);
				this.fauxTour();
				guide = (GuideSpirituel) Partie.getcarteEnCours();

				if (this.peutJouer(guide)) {
					if (guide.guiderCroyant()) {

						this.cartesRecuperees.add(guide);
						main.remove(position);
					}
				}
			}
		} else {
			System.out.println("Vous n'avez pas de guide ou le centre de table est vide");
		}
	}

	
	/**
	 * Methode permettant d'afficher les cartes contenues dans la main du joueur
	 */

	public void afficherCarte() {
		if (!Partie.getInterfaceGaphiqueActive()) {
			ListIterator<CarteAction> it = main.listIterator();
			while (it.hasNext()) {
				CarteAction c = it.next();
				int n = it.nextIndex();
				System.out.println("Carte " + "[" + n + "]" + " : " + c + "\n\r");
			}
		} else {
			setChanged();
			notifyObservers(main);
		}
	}
/**
 * Methode permettant de defausser les cartes d'un joueur
 * @throws InputMismatchException
 * @throws IndexOutOfBoundsException
 */
	public void defausser() throws InputMismatchException, IndexOutOfBoundsException {
		System.out.println(
				"De quelle(s) carte(s) voulez-vous vous defausser? (Saisir le numero. Saisir 0 pour terminer)");

		this.afficherCarte();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int position = sc.nextInt() - 1;
		while (position >= 0 && !main.isEmpty() && position < main.size()) {

			CarteAction c = main.remove(position);
			Partie.piocheCarteAction.listeCartesAction.add(c);
			this.afficherCarte();
			position = sc.nextInt() - 1;
		}

	}

	/**
	 * Methode permettant uttilisee par l'interface graphique pour se defausser de cartes
	 * @param c carte dont il faut se defausser
	 */
	public void defausser(CarteAction c) { // pour interface graphique
		main.remove(c);
		Partie.piocheCarteAction.listeCartesAction.add(c);
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Methode permettant d'afficher les oints du joueur 
	 */
	public void afficherLesPoints() {
		if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println(
					"Points Jour: " + pointsJour + " Points Nuit: " + pointsNuit + " Point Neant: " + pointsNeant);
		}
	}

	/**
	 * Methode permettant au joueur de completer sa main
	 */
	public void completerSaMain() {
		if (!aPioche) {
			while (main.size() != 7)// changer le nombre , represente le nombre
									// de cartes dans la main
			{
				main.add(Partie.piocheCarteAction.tirerCarteduDessus());
			}
			// this.afficherCarte();
			aPioche = true;
		} else {
			MessageBox.getMessageBox().ajouterMessage("Vous avez deje complete votre main!");
			
		}
		if (Partie.getInterfaceGaphiqueActive()) {
			setChanged();
			notifyObservers(this);
		}
	}

	/**
	 * Methode permettant d'afficher le centre de table
	 */
	public void afficherCentreDeTable() {
		System.out.println("Centre De Table");

		ListIterator<Croyant> it = Partie.centreDeTable.getCroyantsCentre().listIterator();
		while (it.hasNext()) {
			CarteAction c = it.next();
			int n = it.nextIndex();
			System.out.println("Carte " + "[" + n + "]" + " : " + c);
		}
	}

	/**
	 * Methode permettant d'afficher les guides spirituels recuperes
	 */
	public void afficherCartesRecuperees() {

		if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println("Cartes Recuperees");
		} else {
			MessageBox.getMessageBox().ajouterMessage("Cartes Recuperees");
		}
		ListIterator<GuideSpirituel> it = cartesRecuperees.listIterator();
		while (it.hasNext()) {
			GuideSpirituel c = it.next();
			int n = it.nextIndex();
			if (!Partie.getInterfaceGaphiqueActive()) {
				System.out.println("Carte " + "[" + n + "]" + " : " + c + "\n");
			} else {
				MessageBox.getMessageBox().ajouterMessage("Carte " + "[" + n + "]" + " : " + c + "\n");
			}
		}

	}

	/**
	 * Methode permettant d'afficher les croyants recuperes
	 */
	public void afficherCroyantsRecuperes() {
		ListIterator<GuideSpirituel> it = cartesRecuperees.listIterator();
		if (!cartesRecuperees.isEmpty()) {
			while (it.hasNext()) {
				GuideSpirituel c = it.next();
				c.afficherCroyantsRecuperees();
			}
		} else {
			MessageBox.getMessageBox().ajouterMessage("La liste des croyants recuperes est vide");
		}
	}

	/**
	 * Methode permettant de recuperer les croyants recuperer
	 * @return la lsite des croyants recuperes
	 */
	public LinkedList<Croyant> getCroyantsRecuperes() {
		ListIterator<GuideSpirituel> it = cartesRecuperees.listIterator();
		LinkedList<Croyant> TousLesCroyantsGuides = new LinkedList<Croyant>();
		if (!cartesRecuperees.isEmpty()) {
			while (it.hasNext()) {
				GuideSpirituel g = it.next();
				for (int i = 0; i < g.getcroyantsGuides().length; i++) {
					TousLesCroyantsGuides.add(g.getcroyantsGuides()[i]);
				}
			}
		} else {
			MessageBox.getMessageBox().ajouterMessage("La liste des croyants recuperes est vide");
		}

		return TousLesCroyantsGuides;
	}
	/**
	 * Methode permettant d'afficher tous les guides spirituel recuperes par tous les joueurs
	 */

	public void afficherCartesRecupereesTousLesJoueurs() {
		ListIterator<Joueur> it2 = Partie.listeJoueurs.listIterator();
		Joueur j;
		while (it2.hasNext()) {
			j = it2.next();
			System.out.println(j + " \r\n");
			j.afficherCartesRecuperees();

		}
	}

	/**
	 * Methode permettant d'afficher e l'utilisateur les options que le joueur a durant la partie
	 * @throws InputMismatchException
	 * @throws IndexOutOfBoundsException
	 */
	protected void choix() throws InputMismatchException, IndexOutOfBoundsException {
		int choix = -5;
		do {
			try { // chaque tour, e mettre dans la methode jouer
				System.out.println("Que voulez vous faire? Saisissez le numero");
				System.out.println("Se defausser [1]");
				System.out.println("Completer sa main [2]");
				System.out.println("Mettre des croyants au centre [3]");
				System.out.println("Consulter le centre de table [4]");
				System.out.println("Consulter les points [5]");
											
				System.out.println("Guider des croyants [6]");
				System.out.println("Consulter les cartes recuperees [7]");
				System.out.println("Utiliser la capacite de la divinite[8]");
				System.out.println("Sacrifier une carte[9]");
				System.out.println("Consulter les cartes recuperees de tous les joueurs[10]");
				System.out.println("Afficher les points de prieres [11]");
				System.out.println("Jouer un Deus Ex ou une Apocalypse[12]"); 
			
				System.out.println("Quitter [0]");
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				choix = sc.nextInt();
				
				if (choix > 12) {
					System.out.println("Veuillez reessayer"); 
					choix = sc.nextInt();
				}

				
				switch (choix) {
				case 1:
					this.defausser();
					break;
				case 2:
					this.completerSaMain();
					break;
				case 3:
					this.mettreCroyantAuCentre(Partie.centreDeTable);
					break;
				case 4:
					this.afficherCentreDeTable();
					break;
				case 5:
					this.afficherLesPoints();
					break;
				case 6:
					this.recupererCroyants();
					break;
				case 7:
					this.afficherCartesRecuperees();
					break;
				case 8:
					this.divinite.utiliserCapacite();
					break;
				case 9:
					this.sacrifierCartes();
					break;
				case 10:
					this.afficherCartesRecupereesTousLesJoueurs();
					break;
				case 11:
					this.afficherPointsDePrieres();
					break;
				case 12:
					this.jouerUneCarteAction();
				default:

					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Veuillez saisir un nombre");
			}
		} while (choix != 0 && choix < 12);

		if (choix == 0) {
			CentreTable.setCroyantsPeuventEtreGuides();
			this.setPeuventEtreJouees(true);
			this.setPeuventEtreSacrifiees(true);
			this.defausserGuideVide();
		}
	}

	/**
	 * Cette methode permet au joueur de faire toutes les actions requises, dans
	 * la mesure du possible, avec ses cartes
	 * @param partie renfermant l'instance la partie en cours
	 * @param i
	 * @return un entier j
	 */

	public int jouer(int i, Partie partie) {
		if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println("\n" + "C'est au tour de " + nom + " de jouer!" + "\n");
			System.out.println("Votre divinite est : " + this.divinite + "\n");
			System.out.println("Vous avez comme cartes :" + "\n");
			for (Iterator<CarteAction> it = this.main.iterator(); it.hasNext();) {
				CarteAction carte = it.next();
				System.out.println(carte);
			}

			System.out.println("Vous avez: " + "\n" + "- " + pointsJour + " points jour" + "\n" + "- " + pointsNuit
					+ " points nuit" + "\n" + "- " + pointsNeant + " points neant");
			this.setaPioche();
			this.choix();
		}
		int j = i + 1;
		return j;
	}



/**
 * Methode permettant de recuperer les points de priere d'un joueur
 * @return les points de priere
 */
	public int pointsDePriereTotal() {
		ListIterator<GuideSpirituel> it = this.cartesRecuperees.listIterator();
		CarteAction c;
		GuideSpirituel g;
		int i = 0;
		while (it.hasNext()) {
			c = it.next();
			if (c instanceof GuideSpirituel) {
				g = (GuideSpirituel) c;
				i += g.pointsDePrieres();
			}
		}
		setpointsPriere(i);
		return i;
	}

	/**
	 * Methode permettant de recuperer le nombre de croyants rattaches au total
	 * @return retourne la valeur recuperee
	 */
	public int nbCroyantsRattachesTotal() {
		ListIterator<CarteAction> it = main.listIterator();
		CarteAction c;
		GuideSpirituel g;
		int i = 0;
		while (it.hasNext()) {
			c = it.next();
			if (c instanceof GuideSpirituel) {
				g = (GuideSpirituel) c;
				i += g.nombreCroyantsRattaches();
			}
		}
		return i;
	}

	/**
	 * Methode permettant d'afficher d'afficher les points de priere d'un joueur
	 */
	public void afficherPointsDePrieres() {
		ListIterator<Joueur> it = Partie.listeJoueurs.listIterator();
		Joueur j;
		int i;
		while (it.hasNext()) {
			j = it.next();
			i = j.pointsDePriereTotal();
			System.out.println(j + " a " + i + " points de prieres.  \r\n");

		}
	}

	/**
	 * Methode permettant de savoir si un guide spirituel du joueur croit e un dogme
	 * @param dogme e verifier
	 * @return le booleen permettant de savoir si le guide croit au dogme passe en parametre
	 */
	public boolean croitAuDogme(TypeDogme dogme) {
		boolean possedeLeDogme = false;
		Iterator<GuideSpirituel> it = this.cartesRecuperees.iterator();
		GuideSpirituel g;
		while (it.hasNext()) {
			g = it.next();
			if (g.getdogme1() == dogme || g.getdogme2() == dogme || g.getdogme3() == dogme) {
				possedeLeDogme = true;
			}
		}
		return possedeLeDogme;
	}

	/**
	 * Methode de savoir si la divinite croet e un certain dogme
	 * @param dogme e verifier
	 * @return le booleen permettant de savoir si la divinite croet au dogme passe en parametres
	 */
	public boolean divCroitAuDogme(TypeDogme dogme) {
		boolean possedeLeDogme = false;
		Divinite div = this.divinite;
		TypeDogme[] dogmes = new TypeDogme[3];
		dogmes[0] = div.getdogme1();
		dogmes[1] = div.getdogme2();
		dogmes[2] = div.getdogme3();
		for (int i = 0; i < 3; i++) {
			if (dogmes[i] == dogme) {
				possedeLeDogme = true;
			}
		}
		return possedeLeDogme;

	}
/**
 * Methode permettant de choisir un joueur
 * @return joueur choisi
 * @throws InputMismatchException
 * @throws IndexOutOfBoundsException
 */
	public Joueur choisirUnJoueur() throws InputMismatchException, IndexOutOfBoundsException { // new
		if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println("Sur quel joueur voulez-vous utiliser votre capacite?(0 pour terminer)");
			Partie.afficherLesJoueurs();
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt() - 1;
			while (n >= Partie.listeJoueurs.size() - 1) {
				System.out.println("Veuillez saisir un nombre valide");
				n = sc.nextInt() - 1;
			}
			return Partie.listeJoueurs.get(n);
		} else {

			Joueur j = FenetrePrincipale.choisirUnJoueur();
			return j;// appelle une methode statique de la vue qui ouvre un
						// jdialog qui demande de chosiir un joueur et qui
						// retourne un joueur
		}
	}

	/**
	 * Methode permettant de choisir un guide spirituel
	 * @param cartesRec liste dans laquelle il faut choisir un guide spirituel
	 * @return le guide spirituel choisi
	 */
	@SuppressWarnings("resource")
	public GuideSpirituel choisirUnGuideSpirituel(LinkedList<GuideSpirituel> cartesRec) {
		GuideSpirituel g = null;
		if (Partie.getjoueurEnCours() instanceof JoueurPhysique) {
			if (!Partie.getInterfaceGaphiqueActive()) {
				Scanner sc = new Scanner(System.in);

				int k = sc.nextInt() - 1;
				while (k >= cartesRec.size() && !cartesRec.isEmpty()) {
					System.out.println("Veuillez saisir un nombre valide");
					k = sc.nextInt() - 1;
				}
				if (!cartesRec.isEmpty()) {
					g = cartesRec.remove(k);
				} else {
					System.out.println("Le Joueur n'a recupere aucune carte...");
				}
				return g;
			} else {
				LinkedList<CarteAction> liste = new LinkedList<CarteAction>();
				liste.addAll(cartesRec);
				if (!liste.isEmpty()) {
					g = FenetrePrincipale.choisirUnGuideSpirituel(liste);
				} else {
					MessageBox.getMessageBox().ajouterMessage("Le joueur n'a recupere aucune carte...");
				}
				return g;
			}
		} else {
			int i = (int) Math.round(Math.random()) * (cartesRec.size());
			g = cartesRec.get(i);
			MessageBox.getMessageBox().ajouterMessage(
					"Le joueur " + Partie.getjoueurEnCours() + "a choisi le guide " + g.getStrategie().getNom());
			return g;
		}

	}

	/**
	 * Permet d'afficher le nom du joueur 
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(nom);
		return sb.toString();
	}

	/**
	 * Methode permettant de jouer une carte d'action
	 * @throws InputMismatchException
	 * @throws IndexOutOfBoundsException
	 */
	@SuppressWarnings("resource")
	public void jouerUneCarteAction() throws InputMismatchException, IndexOutOfBoundsException {
		System.out.println("Quelle carte voulez vous jouer? (Saisir le numero. Saisir 0 pour terminer)");
		this.afficherCarte();
		Scanner sc = new Scanner(System.in);
		int position = sc.nextInt() - 1;
		if (position >= 0 && !main.isEmpty() && position < main.size() - 1) {

			CarteAction c = main.remove(position);

			if (!(c instanceof Croyant) && !(c instanceof GuideSpirituel)) {
				if (c instanceof Apocalypse) {
					if (Partie.compteurApo() > 1) {
						if (this.peutJouer(c)) {
							Partie.setcarteEnCours(c);
							this.fauxTour();
							if (Partie.compteurApo() > 1) {
								c.jouerCarte();
							}
							Partie.piocheCarteAction.listeCartesAction.add(c);
						}
					} else {
						System.out.println("Une carte Apocalypse a deje ete jouee au tour precedent");
						main.add(c);
					}
				} else {
					if (this.peutJouer(c)) {
						Partie.setcarteEnCours(c);
						this.fauxTour();
						c = (CarteAction) Partie.getcarteEnCours();
						c.jouerCarte();
						Partie.piocheCarteAction.listeCartesAction.add(c);
						this.afficherCarte();
					}
				}
			} else {
				System.out.println("Ce n'est ni un Deus Ex, ni une Apocalypse !");
				main.add(c);
			}
		}

	}
/**
 * Methode permettant de savoir si deux joueurs sont equivalents
 * @param j joueur e comparer au joueur dont la methode est appelee
 * @return le booleen qui  permet de savoir si les joueurs sont egaux
 */
	public boolean equals(Joueur j) {
		boolean estEgal = false;
		if (this.nom == j.nom) {
			estEgal = true;
		}
		return estEgal;

	}
	
	/**
	 * Methode permet de choisir un guide spirituel
	 * @param cartesRec collection dans laquelle il faut choisir le croyant
	 * @return le croyant choisi
	 */

	@SuppressWarnings("resource")
	public Croyant choisirUnCroyant(LinkedList<Croyant> cartesRec) {
		Croyant c = null;
		if (Partie.getjoueurEnCours() instanceof JoueurPhysique) {
			if (!Partie.getInterfaceGaphiqueActive()) {
				Scanner sc = new Scanner(System.in);

				;
				int k = sc.nextInt() - 1;
				while (k >= cartesRec.size() && !cartesRec.isEmpty()) {
					System.out.println("Veuillez saisir un nombre valide");
					k = sc.nextInt() - 1;
				}
				if (!cartesRec.isEmpty()) {
					c = cartesRec.remove(k);
				} else {
					System.out.println("Il n'y a pas de croyant rattaches e ce guide");
				}
			} else {
				LinkedList<CarteAction> liste = new LinkedList<CarteAction>();
				Iterator<Croyant> it = cartesRec.iterator();
				while (it.hasNext()) {
					liste.add(it.next());
				}
				c = FenetrePrincipale.choisirCroyant(liste);
			}
		} else {
			int i = (int) Math.round(Math.random() * (cartesRec.size()));
			c = cartesRec.get(i);
			if (!Partie.getInterfaceGaphiqueActive()) {
				System.out.println(
						"Le joueur" + Partie.getjoueurEnCours() + " a choisi le croyant  " + c.getStrategie().getNom());
			} else {
				MessageBox.getMessageBox().ajouterMessage("Le joueur" + Partie.getjoueurEnCours()
						+ " a choisi d'utliser sa capacite sur le joueur " + c.getStrategie().getNom());
			}

		}
		return c;
	}
/**
 * Methode permettant de creer un tour intermediaire pour savoir si des joueurs veulent joueur des deusEx
 * @return le booleen qui permet de savoir si le joueur veut jouer
 * @throws InputMismatchException
 * @throws IndexOutOfBoundsException
 */
	@SuppressWarnings("resource")
	public boolean fauxTour() throws InputMismatchException, IndexOutOfBoundsException {
		boolean aJoue = false;
		System.out.println(this + " vient de poser cette carte: \n " + Partie.getcarteEnCours()
				+ "\n Quelqu'un veut il jouer? (0 = Oui 1= Non)");
		Scanner sc = new Scanner(System.in);
		int k;

		k = sc.nextInt();
		if (k == 0) {
			Joueur j = this.choisirUnJoueur();
			aJoue = this.jouerCarteFauxTour(j);
		}
		return aJoue;
	}

	/**
	 * Methode permettant de jouer une carte pendant le faux tour
	 * @param j joueur jouant pendant le faux tour
	 * @return le booleen permettant de savoir si le joueur a joue
	 * @throws InputMismatchException
	 * @throws IndexOutOfBoundsException
	 */
	public boolean jouerCarteFauxTour(Joueur j) throws InputMismatchException, IndexOutOfBoundsException {
		LinkedList<CarteAction> main = j.getmain();
		ListIterator<CarteAction> it = main.listIterator();
		CarteAction c;
		int n;
		boolean possedeCarte = false;
		System.out.println("Saisissez la carte e jouer");
		while (it.hasNext()) {
			c = it.next();
			n = it.nextIndex();
			if (c.getorigine() == TypeOrigine.NULL) {
				System.out.println("Carte [" + n + "] :" + c);
				possedeCarte = true;
			}
		}
		if (!j.divinite.getCapaciteUtilisee()) {
			System.out.println(("\n [8] pour la capacite de la divinite ") + this.divinite);
			possedeCarte = true;
		}
		if (possedeCarte) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			int k;
			boolean divinite = false;
			do {
				k = sc.nextInt() - 1;
				if (k == 7) {
					divinite = true;
				}
			} while (k < -1 && k > main.size() && main.get(k).getorigine() != TypeOrigine.NULL && !divinite);
			if (divinite) {
				this.divinite.utiliserCapacite();
			} else {
				c = main.get(k);
				c.jouerCarte();
				main.remove(c);
				j.setmain(main);
				Partie.piocheCarteAction.listeCartesAction.add(c);
			}
		} else {
			System.out.println(j + " n'a finalement pas joue");
		}
		return possedeCarte;
	}

	/**
	 * Permet de modifier la valeur de l'attribut peutEtreJouee d'un guide spirituel et des croyants
	 * @param bool valeur e affecter
	 */
	public void setPeuventEtreJouees(boolean bool) {
		Iterator<CarteAction> it = this.main.iterator();
		Iterator<GuideSpirituel> it2 = this.cartesRecuperees.iterator();
		CarteAction c;
		GuideSpirituel g;
		while (it.hasNext()) {
			c = it.next();
			c.setPeutEtreJouee(bool);
		}
		;
		while (it2.hasNext()) {
			g = it2.next();
			g.setPeutEtreJouee(bool);
		}
	}

	/**
	 * Permet de modifier la valeur de l'attribut " peutEtreSacrifiee
	 * @param bool vauelur e affecter e la variable
	 */
	public void setPeuventEtreSacrifiees(boolean bool) {
		Iterator<CarteAction> it = this.main.iterator();
		Iterator<GuideSpirituel> it2 = this.cartesRecuperees.iterator();
		CarteAction c;
		GuideSpirituel g;
		while (it.hasNext()) {
			c = it.next();
			c.setPeutEtreSacrifiee(bool);
		}

		while (it2.hasNext()) {
			g = it2.next();
			g.setPeutEtreSacrifiee(bool);
			Croyant[] croyantsGuides = g.getcroyantsGuides();
			for (int i = 0; i < g.NB_CARTES_RATTACHEES_MAX - 1; i++) {
				if (croyantsGuides[i] != null) {
					croyantsGuides[i].setPeutEtreSacrifiee(bool);
				}

			}
			g.setcroyantsGuides(croyantsGuides);

		}
	}

	/**
	 * Methode permettant de de recuperer les points jour
	 * @return la valeur recuperee
	 */
	public int getPointsJour() {
		return pointsJour;
	}

	/**
	 * Methode permettant de  recuperer les points nuit
	 * @return la valeur recuperee
	 */
	public int getPointsNuit() {
		return pointsNuit;
	}

	/**
	 * Methode permettant de  recuperer les points de priere
	 * @return la valeur recuperee
	 */
	public int getPointsPriere() {
		return pointsPriere;
	}

	/**
	 * Methode permettant de  recuperer les points neant
	 * @return la valeur recuperee
	 */
	public int getPointsNeant() {
		return pointsNeant;
	}

	/**
	 * Methode permettant de  recuperer le nom du joueur
	 * @return la valeur recuperee
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Methode permettant de recuperer la divinite du joueur
	 * @return la valeur recuperee
	 */
	public Divinite getDivinite() {
		return this.divinite;
	}

	/**
	 * Methode permettant de modifier  les points points de priere du joueur
	 *@param point points e affecter
	 *notifier les observateurs
	 */
	public void setpointsPriere(int point) {
		pointsPriere = point;
		setChanged();
		notifyObservers();
	}

	/**
	 * Methode permettant de defausser un guide spirituel vide
	 */
	public void defausserGuideVide() {
		LinkedList<GuideSpirituel> cartesRec = this.getcartesRecuperees();
		Iterator<GuideSpirituel> it = cartesRec.iterator();
		GuideSpirituel g;
		LinkedList<GuideSpirituel> guidesARet = new LinkedList<GuideSpirituel>();
		while (it.hasNext()) {
			g = it.next();
			if (g.isEmpty()) {
				guidesARet.add(g);
			}
		}
		cartesRec.removeAll(guidesARet);
		this.setcartesRecuperees(cartesRec);
		Partie.piocheCarteAction.listeCartesAction.addAll(guidesARet);
	}

	/**
	 * Methode permettant de  recuperer la main du joueur
	 * @return la valeur recuperee
	 */
	public LinkedList<CarteAction> getmain() {
		return this.main;
	}

	/**
	 * Methode permettant de  modifier la main du joueur
	 * @param main main e affecter
	 */
	public void setmain(LinkedList<CarteAction> main) {
		this.main = main;
	}

	/**
	 * Methode permettant de  recuperer les cartes recuperees du joueur
	 * @return la valeur recuperee
	 */
	public LinkedList<GuideSpirituel> getcartesRecuperees() {
		return this.cartesRecuperees;
	}
/**
 * Methode de modifier les cartes recuperees du joueur
 * @param cartesRec valeurs e affecter 
 */
	public void setcartesRecuperees(LinkedList<GuideSpirituel> cartesRec) {

		this.cartesRecuperees = cartesRec;
	}

	/**
	 * 	Methode permettant de recuperer l'origine de la divinite
	 * @return origine recuperee
	 */
	public TypeOrigine getorigineDivinite() { 
		return this.divinite.origines;
	}

	/**
	 * 	Methode permettant de recuperer un dogme d'une divinite
	 * @param indice du dogme e recuperer
	 * @return dogme recupere
	 */
	public TypeDogme getDogmeDivinite(int i) {
		return this.divinite.dogmes[i - 1];
	}
/**
 * Methode permettant de modifier la valeur de l'attribut aPioche
 */
	public void setaPioche() {
		this.aPioche = false;
	}
	
	/**
	 * Methode permettant d'ajouter une carte d'action e la maisn du joueur
	 * @param c carte e ajouter
	 * 
	 */
	public void addCarte(CarteAction c) {
		this.main.add(c);
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Methode permettant d'enlever une carte d'action de la main du joueur
	 * @param c carte e enlever
	 */
	public void removeCarte(CarteAction c) {
		this.main.remove(c);
		setChanged();
		notifyObservers(this);
	}

	
	/**
	 * Methode permettant d'ajouter un guide spirituel e la lsite des cartes recuperees
	 * @param g guide e ajouter
	 */
	public void addCarteRecuperee(GuideSpirituel g) {
		this.cartesRecuperees.add(g);
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Methode permettant d'enlever une carte de la main du joueur
	 * @param g caret e enlever
	 */
	public void removeCarteRecuperee(GuideSpirituel g) {
		this.cartesRecuperees.remove(g);
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Methode qui permet de modifier la valeur de l'atttribut aPioche
	 * @param bool valeur e attribuer e l'attribut
	 */
	public void setaDejaJoue(boolean bool) {
		this.aPioche = bool;
	}
/**
 * Methode permettant de recuperer la valeur de l'attribut aDejaJoue
 * @return la valeur de l'attri
 */
	public boolean getaDejaJoue() {
		return this.aDejaJoue;
	}
}