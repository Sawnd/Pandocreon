/**
 * 
 */
package elementsDeBase;

import java.util.*;
import elementsDeBase.PiocheDivinite;
import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageBox;
import strategies.Strategie;
import strategies.StrategieDifficile;
import strategies.StrategieFacile;
import strategies.StrategieMoyen;
import elementsDeBase.PiocheCarteAction;

/**
 * @author Anne-Sophie
 *
 */
public class Partie extends Observable implements Observer {
	private static Partie partie = null;
	public int NOMBREJOUEURS;
	public static ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>();
	public static CentreTable centreDeTable;
	public PiocheDivinite piocheDivinite;
	public static PiocheCarteAction piocheCarteAction;
	public final static TypeOrigine[] De = { TypeOrigine.JOUR, TypeOrigine.NEANT, TypeOrigine.NUIT };
	private static TypeOrigine origineDuTour;
	private static Joueur joueurEnCours;
	private static int nbDeTours = 0;
	private static int compteurApo = 0;
	private static CarteAction carteEncours;
	private int numeroJoueur = 100;// new
	private static boolean interfaceGraphiqueActive = true;
	private Scanner sc = new Scanner(System.in);
	/**
		 * 
		 */
	private Partie() {
		// NOMBREJOUEURS = definirNombreDeJoueurs();
		piocheDivinite = new PiocheDivinite();
		piocheCarteAction = new PiocheCarteAction();
		centreDeTable = new CentreTable();

	}

	public static Partie getPartie() {
		if (partie == null) {
			partie = new Partie();
		} else {
		}
		return partie;
	}

	public void definirNombreDeJoueurs(ArrayList<String> jr, ArrayList<String> jv)
			throws InputMismatchException, IndexOutOfBoundsException { // Permet
		centreDeTable.addObserver(this);
		setChanged();
		notifyObservers(centreDeTable);
		int nbj = 0;
		Iterator<String> it = jr.iterator();
		while (it.hasNext()) {
			listeJoueurs.add(new JoueurPhysique(it.next()));
			nbj++;
		}
		it = jv.iterator();
		while (it.hasNext()) {
			listeJoueurs.add(new JoueurVirtuel(it.next(), new StrategieFacile()));
			nbj++;
		}
		Collections.shuffle(listeJoueurs);
		this.NOMBREJOUEURS = nbj;
		this.distribuerCartes(listeJoueurs);
		this.distribuerDivinites(listeJoueurs);
		// MessageBox.getMessageBox().ajouterMessage("La partie aura " +
		// NOMBREJOUEURS + " joueurs");

		MessageBox.getMessageBox().ajouterMessage("La partie aura " + NOMBREJOUEURS + " joueurs");
	}

	public int definirNombreDeJoueurs() throws InputMismatchException, IndexOutOfBoundsException { // Permet
		// de
		// saisir
		// au
		// clavier
		// le
		// char carac = 'a';
		int nbJ = 0;
		int j = 7, l = 0, choice = 0;
		Strategie strategie;
		// while (carac !='O'){
		do {
			try {
				System.out.println("Le nombre de joueurs ne peut pas depasser 6, il faut au moins un joueur Reel");
				System.out.println("Nombre de joueurs reels?");
				
				j = sc.nextInt();
				System.out.println("Nombre de joueurs virtuels?");
				l = sc.nextInt();
				strategie = null;
				if (l != 0) {
					System.out.println("Choisissez le niveau des joueurs virtuels?");
					System.out.println("Facile [1]");
					System.out.println("Moyen[2]");
					System.out.println("Difficile [3]");
					choice = sc.nextInt();
				}
			} catch (InputMismatchException e) {
				System.out.println("Veuillez ne saisir que des chiffres ");
			} catch (IndexOutOfBoundsException e2) {
				System.out.println("Veuillez saisir une position valide");
			}
		} while (j + l > 6 || choice > 3 || j < 1 || j + l < 2);

		switch (choice) {
		case 1:
			StrategieFacile stratf = new StrategieFacile();
			strategie = stratf;
			break;
		case 2:
			StrategieMoyen stratm = new StrategieMoyen();
			strategie = stratm;
			break;
		case 3:
			StrategieDifficile stratd = new StrategieDifficile();
			strategie = stratd;
			break;
		default:
			StrategieFacile stratf1 = new StrategieFacile();
			strategie = stratf1;
			break;
		}

		nbJ = j + l;
		if (j != 0) {
			int i = 0;
			while (i < j) {
				JoueurPhysique joueur = new JoueurPhysique();
				listeJoueurs.add(joueur);
				i++;
			}
		}
		if (l != 0) {
			int i = 0;
			while (i < l) {
				JoueurVirtuel joueur = new JoueurVirtuel(strategie);
				listeJoueurs.add(joueur);
				i++;
			}
		}
		return nbJ;
	}

	/**
	 * Cette methode permet de distibuer aux joueurs les points d'action en
	 * fonction de la cosmogonie du tour et de l'origine de leur divinite
	 * 
	 * @param listeJoueurs
	 */
	public void distribuerPoints(ArrayList<Joueur> listeJoueurs) { // new
		Iterator<Joueur> it = listeJoueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (j.divinite.origines == origineDuTour && origineDuTour == TypeOrigine.JOUR) {
				j.setpointsJour(2);
			} else if (j.divinite.origines == TypeOrigine.AUBE && origineDuTour == TypeOrigine.JOUR) {
				j.setpointsJour(1);
			} else if (j.divinite.origines == origineDuTour && origineDuTour == TypeOrigine.NUIT) {
				j.setpointsNuit(2);
			} else if (j.divinite.origines == TypeOrigine.CREPUSCULE && origineDuTour == TypeOrigine.NUIT) {
				j.setpointsNuit(1);
			} else if (j.divinite.origines == TypeOrigine.CREPUSCULE && origineDuTour == TypeOrigine.NEANT) {
				j.setpointsNeant(1);
			} else if (j.divinite.origines == TypeOrigine.AUBE && origineDuTour == TypeOrigine.NEANT) {
				j.setpointsNeant(1);
			}
		}
	}

	public void distribuerDivinites(ArrayList<Joueur> listeJoueurs) {
		Iterator<Joueur> it = listeJoueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			Divinite div = piocheDivinite.tirerCarteDuDessus(piocheDivinite.listeDivinites);
			j.setDivinite(div);
		}

	}

	/**
	 * Cette methode methode permet de modifier l'attribut origineDuTour de la
	 * partie
	 * 
	 * @param origine
	 */

	public static void setOrigineDuTour(TypeOrigine origine) {
		Partie.origineDuTour = origine;
		partie.setChanged();
		partie.notifyObservers(origine);

	}

	public static TypeOrigine getOrigineDuTour() {
		return Partie.origineDuTour;
	}

	/**
	 * Cette methode choisit au hasard une face du de et la renvoie
	 * 
	 * @return
	 */

	public static TypeOrigine lancerDe() { // Choisit au hasard une face du de
											// et la renvoie
		int position = (int) Math.round(Math.random() * (De.length - 1));
		MessageBox.getMessageBox().ajouterMessage("La cosmogonie est " + De[position]);
		// MessageBox.getMessageBox().ajouterMessage("La cosmogonie est " +
		// De[position]);
		setOrigineDuTour(De[position]);
		return De[position];

	}

	/**
	 * Cette methode permet de distribuer ds cartes d'action e tous les joueurs
	 * de la partie
	 * 
	 * @param listeJoueurs
	 *            permet de recuprer la liste de joueurs
	 */

	public void distribuerCartes(ArrayList<Joueur> listeJoueurs) {
		Iterator<Joueur> it = listeJoueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			for (int i = 0; i < 7; i++) { // i=nbdeCartes e piocher
				CarteAction carte = piocheCarteAction.tirerCarteduDessus();
				j.ajouterCarte(carte);
			}
		}

		/**
		 * Cette methode permet de distribuer des divinites e tous les joueurs
		 * de la partie
		 * 
		 * @param listeJoueurs
		 *            permet de recuperer la liste de joueurs
		 */
	}

	// new
	public static void afficherLesJoueurs() {
		ListIterator<Joueur> it = Partie.listeJoueurs.listIterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			int n = it.nextIndex();
			partie.setChanged();
			partie.notifyObservers(j);
			partie.setChanged();
			partie.notifyObservers("Joueur " + "[" + n + "]" + " : " + j);

		}
	}

	public void setjoueurEnCours(Joueur j) {
		Partie.joueurEnCours = j;
		setChanged();
		notifyObservers(j);
	}

	public static Joueur getjoueurEnCours() {
		return Partie.joueurEnCours;
	}

	public static CarteAction getcarteEnCours() {
		return Partie.carteEncours;
	}

	public static void setcarteEnCours(CarteAction c) {
		Partie.carteEncours = c;
	}

	public static void resetCompteurApo() {
		Partie.compteurApo = 0;
	}

	public static int compteurApo() {
		return Partie.compteurApo;
	}

	public static int getnbDeTours() {
		return Partie.nbDeTours;
	}

	public void update(Observable o, Object args) {
		if (args instanceof JoueurPhysique || args instanceof String || args instanceof CentreTable) {
			setChanged();
			notifyObservers(args);
		}
	}

	public void gererPartie() {

		if (numeroJoueur < Partie.listeJoueurs.size()) {

			partie.setjoueurEnCours(Partie.listeJoueurs.get(numeroJoueur));
			Partie.getjoueurEnCours().setpointsJour(100);
			Partie.getjoueurEnCours().setpointsNeant(100);
			Partie.getjoueurEnCours().setpointsNuit(100);
			Partie.listeJoueurs.get(numeroJoueur).jouer(numeroJoueur, partie);

			numeroJoueur++;
		} else {
			numeroJoueur = 0;
			nbDeTours++;
			Partie.compteurApo++;
			Partie.lancerDe();
			partie.distribuerPoints(listeJoueurs);
			Joueur j1 = Partie.listeJoueurs.remove(0);
			Partie.listeJoueurs.add(j1);
		}

	}
	public static void setStrategie(Strategie strategie) {
	}
	
	

	public static boolean getInterfaceGaphiqueActive() {
		return interfaceGraphiqueActive;
	}

	public static void main(String[] args) {

		int a=10;
		Scanner sc;
		do {
			System.out.println("Interface Graphique ou ligne de commande? (0 : Interface Graphique 1:Console)");
			try {
				sc = new Scanner(System.in);

				a = sc.nextInt();
			
			} catch (InputMismatchException e) {
				System.out.println("Ligne de commande");
			}
		} while (a<0 && a>1);
		if (a == 0) {
			Partie.interfaceGraphiqueActive = true;
		}else{
			Partie.interfaceGraphiqueActive=false;
		}

		Partie partie = Partie.getPartie();
	
		if (!Partie.getInterfaceGaphiqueActive()) {
			partie.NOMBREJOUEURS = partie.definirNombreDeJoueurs();

			partie.distribuerDivinites(Partie.listeJoueurs);
			partie.distribuerCartes(Partie.listeJoueurs);
			int nbj = Partie.listeJoueurs.size();
			int k = 0;
			while (k <= nbj - 1) {
				nbDeTours++;
				Partie.compteurApo++;
				Partie.lancerDe();
				partie.distribuerPoints(listeJoueurs);
				int i = 0;
				while (i <= Partie.listeJoueurs.size() - 1) {
					partie.setjoueurEnCours(Partie.listeJoueurs.get(i));// new
					// Partie.getjoueurEnCours().addObserver(ctrl);
					int j = Partie.listeJoueurs.get(i).jouer(i, partie);
					i = j;

				}
				Joueur j1 = Partie.listeJoueurs.remove(0);
				Partie.listeJoueurs.add(j1);

			}
			k++;
		} else {
			FenetrePrincipale.getFenetrePrincipale();
		}
	}
}
