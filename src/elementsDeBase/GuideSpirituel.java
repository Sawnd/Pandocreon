/**
 * 
 */
 package elementsDeBase;

import capaciteSacrifice.*;
import java.util.*;

/**
 * La classe GuideSpirituel represente un des types de cartes du jeu
 * Elle herite  de la classe CarteAction et implemente ses methodes {@link: elementsDeBase.CarteAction}
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 */
public class GuideSpirituel extends CarteAction {
	
	/**
	 * entier representant le nombre de croyants guides par la carte
	 */
	private int nombreCartesCroyants = 0;
	
	/**
	 * entier representant le nombre maximal de croyants pouvant etre rattaches e la carte
	 */
	public final int NB_CARTES_RATTACHEES_MAX;
	
	/**
	 * tableau contenant l'ensemble des croyants guides par la carte
	 */
	private Croyant[] croyantsGuides;
	
	/**
	 * booleen permettant de savoir si le guide est en etat de stase
	 */
	private boolean stase = false;

	/**
	 * Ce constructeur permet de creer des cartes avec une origine, deux dogmes et une capacite speciale de sacrifice
	 * @param origine valeur e affecter e l'origine de la carte
	 * @param dogme1 valeur du premier dogme de la carte
	 * @param dogme2  valeur du deuxieme dogme de la carte
	 * @param capacite capacite represente le sacrifice de la carte 
	 * @param id valeur e affecter e l'identfiant de la carte
	 * @param nbCartesMax valeur e affecter au nombre maximal de croyants pouvant etre guides par la carte
	 * fait appel au constructeur de la classe dont elle herite
	 */
	public GuideSpirituel(TypeOrigine origine, TypeDogme dogme1, TypeDogme dogme2, int nbCartesMax,
			CapaciteSacrifice capacite,int id) { 
		super(origine, dogme1, dogme2, capacite,id);
		this.NB_CARTES_RATTACHEES_MAX = nbCartesMax;
		croyantsGuides = new Croyant[NB_CARTES_RATTACHEES_MAX];
		this.setPeutEtreJouee(true);
	}

	
	/**
	 * Methode permettant e un joueur reel de guider des croyants
	 * @return un booleen permettant de savoir si des croyants ont etet rattaches au guide
	 */
	public boolean guiderCroyant() { 
		boolean aGuide = true; 
		
		/**
		 * on commence par afficher au joueur la lsite des cartes qu'il peut guider 
		 */
		System.out.println("Vous pouvez guider:  ");
		ListIterator<Croyant> it = Partie.centreDeTable.getCroyantsCentre().listIterator();
		while (it.hasNext()) {
			Croyant c = it.next();
			int dogmesEnCommun = 0;
			dogmesEnCommun = this.dogmesEnCommuns(c);
			
			if (dogmesEnCommun > 0 && c.getpeutEtreGuidee()) {
				System.out.println("[" + it.nextIndex() + "]" + c);
			}
		}
		
		/**
		 * le joueur choisit les cartes une par une en saisissant au clavier un entier correspondant e la carte voulue
		 * on rajoute chque carte e la collection de cartes e guider tant que le nombre maximal de croyants pouvant etre guides par le guide spirituel n'est pas excede
		 */
		System.out.println("Quels Croyants voulez vous guider?");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt() - 1;
		int k = 0; 
		
		LinkedList<Croyant> cartesAGuider = new LinkedList<Croyant>();
		while (k < this.nombreDeCroyantsRattachesMax() && n != -1) {

			if (croyantsGuides[k] == null) {
				cartesAGuider.add(Partie.centreDeTable.getCroyantsCentre().get(n));
				k++;
			} else if (k >= NB_CARTES_RATTACHEES_MAX) {
				System.out
						.println("Vous ne pouvez pas rattacher de Croyants supplemetaires!" + NB_CARTES_RATTACHEES_MAX);
			}
			n = sc.nextInt() - 1;
		}
		if (cartesAGuider.isEmpty()) {
			aGuide = false;
		}
		/**
		 * on rajoute les cartes guidees e la liste des croyants du guide spirituel
		 */
		Partie.centreDeTable.enleverCroyant(cartesAGuider);
		for (int l = 0; l < this.NB_CARTES_RATTACHEES_MAX; l++) {
			if (croyantsGuides[l] == null && !cartesAGuider.isEmpty()) {
				croyantsGuides[l] = cartesAGuider.pop();
			}
		}
		return aGuide;
	}

	
	/**
	 * Methode permettant e un joueur virtuel de guider des croyants
	 * @return un booleen permettant de savoir si des croyants ont etet rattaches au guide
	 */
	public static void guiderCroyant(JoueurVirtuel jv) { 
		int croyrpz = 0;
		int dogmesEnCommun = 0;
		int cartesg = 0;
		Croyant guide = null;
		LinkedList<GuideSpirituel> cartesARet=new LinkedList<GuideSpirituel>();
		LinkedList<GuideSpirituel> cartesRec=jv.getcartesRecuperees();
		
		/**
		 * on parcourt la liste des cartes du joueur pour recuperer un guide spirituel
		 * si le joueur a assez de points pour le jouer, on parcourt le centre de table s'il n'est pas vide
		 * on cherche alors des cartes ayant au minimum un dogme en commun avec le guide et on le rajoute a la liste des croyants guides
		 * le nombre de croyants guides n'excede pas le nombre maximal de croyants pouvant etre rattaches au guide
		 */
		for (Iterator<CarteAction> it = jv.main.iterator(); it.hasNext();) {
			CarteAction carte = it.next();
			if (carte instanceof GuideSpirituel && jv.peutJouer(carte)) {
				GuideSpirituel g = (GuideSpirituel) carte;
				
				for (int i = 0; i <= ((GuideSpirituel) carte).NB_CARTES_RATTACHEES_MAX-1; i++) {
					if (Partie.centreDeTable.getCroyantsCentre().isEmpty()) {

					} else {
						for (Iterator<Croyant> itc = Partie.centreDeTable.getCroyantsCentre().iterator(); itc.hasNext();) {
							Croyant croy = itc.next();
                                
							dogmesEnCommun=g.dogmesEnCommuns(croy);
							
							if (dogmesEnCommun >= 1 && croy.NBCROYANTS > croyrpz) {
								guide = croy;
								croyrpz = croy.NBCROYANTS;
							}
						}
						Partie.centreDeTable.enleverCroyant(guide);
						g.croyantsGuides[i] = guide;
						guide=null;
						
						
						
						cartesg++;
					}
					
					
				}
				cartesARet.add(g);
				cartesRec.add(g);
			}
		}
		
		jv.main.removeAll(cartesARet);
		
		/**
		 * on affiche aux autres joueurs les actions menees par le joueur virtuel
		 */
		if (cartesg != 0) {
			System.out.println(jv.getNom().toString() + " a guide " + cartesg + " croyants");
		} else {
			System.out.println(jv.getNom().toString() + " n'a pas souhaite guider de croyants" + "\n");
		}

	}
	
	/**
	 * Permet de savoir si un croyant peut rattache e un guide
	 * @param c croyant en question
	 * @return le boolean permettant de savoir si le croyant peut etre guide ou pas
	 */
	public boolean guiderCroyant(Croyant c){
		boolean peutGuide = false;
		int dogmesEnCommun = this.dogmesEnCommuns(c);
		if (dogmesEnCommun>0 && !this.isFull()){
		peutGuide=true;}
		return peutGuide;
	}

/**
 * Permet d'afficher les croyants recuperes par le guide spirituel 
 */
	public void afficherCroyantsRecuperees() {
		for (int i = 0; i < NB_CARTES_RATTACHEES_MAX; i++) {
			if (croyantsGuides[i] != null) {
				System.out.println("(" + (i + 1) + ")" + croyantsGuides[i]);
			}
		}
	}

	
	/**
	 * La methode toString() permet d'afficher les attributs de la carte d'action
	 */
	public String toString() {// new
		StringBuffer sb = new StringBuffer();
		sb.append("\n Guide Spirituel \n");
		sb.append(super.toString());
		sb.append(" Croyants guides :");
		for (int i = 0; i < NB_CARTES_RATTACHEES_MAX; i++) {
			if (croyantsGuides[i] != null) {
				sb.append("(" + (i + 1) + ")" + croyantsGuides[i]);
			}
		}
		return sb.toString();
	}

	
	/**
	 * Permet de recuperer le nombre de croyants max pouvant etre rattches e un guide spirituel
	 * @return la valeur en question
	 */
	public int nombreDeCroyantsRattachesMax() {
		return this.NB_CARTES_RATTACHEES_MAX;
	}

	/**
	 * Permet de recuperer le nombre de croyanst rattaches e un guide
	 * @return la dimenssion du tableau 
	 */
	public int nombreCroyantsRattaches() {

		int j = 0;

		for (int i = 0; i < NB_CARTES_RATTACHEES_MAX; i++) {
			if (croyantsGuides[i] != null) {
				j++;
			}
		}
		return j;
	}

	/**
	 * Permet de recuperer les croynats rattaches e un guide spirituel
	 * @return le tableau contennat les croynats rattaches au guide
	 */
	public Croyant[] getcroyantsGuides() {
		return this.croyantsGuides;
	}
	
	/**
	 * Permet de modifier la valeur de l'attribut croyantsGuides
	 * @param croyantRec est la valeur e affecter e l'attribut
	 */

	public void setcroyantsGuides(Croyant[] croyantRec) {

		this.croyantsGuides = croyantRec;
	}

	
	/**
	 * Permet de jouer une carte
	 */
	public void jouerCarte() {
		this.utiliserSacrifice();
		this.defausserLesCroyants();
	}
	
	/**
	 * permet d'utiliser le sacrifice de la carte sans la defausser
	 */
	public void utiliserSacrifice(){
		/**
		 * appelle la methode seSacrifier() du sacrifice de la carte
		 * defausse les croyants rattaches au guide
		 */
		this.sacrifice.seSacrifier();
	}

	
	/**
	 * Permet de recuperer le nombre de points de priere d'un guide spirituel
	 * @return l'entier recupere
	 */
	public int pointsDePrieres() {
		int i = 0;
		for (int k = 0; k < this.NB_CARTES_RATTACHEES_MAX; k++) {
			if (this.croyantsGuides[k] != null) {
				i += this.croyantsGuides[k].NBCROYANTS;
			}
		}
		return i;
	}

	
	/**
	 * Permet de defausser les croyants rattaches e un guide spirituel
	 * La methode remet les cartes defaussees au centre de la table
	 */
	public void defausserLesCroyants() {
		Croyant c;
		LinkedHashSet<Croyant> croyants = new LinkedHashSet<Croyant>();
		for (int i = 0; i < this.NB_CARTES_RATTACHEES_MAX ; i++) { 
			if (this.croyantsGuides[i] != null) {
				c = this.croyantsGuides[i];
				croyants.add(c);
				this.croyantsGuides[i] = null;
			}
			Partie.centreDeTable.ajouterCroyant(croyants);
		}
	}
	
	
	/**
	 * Permet de savoir si le tableau des croyants recuperes par le guide est vide
	 * @return un booleen indiquant si le tableau est vide ou pas
	 */
	public boolean isEmpty() {
		boolean isEmpty=true;
		for (int i = 0; i < this.NB_CARTES_RATTACHEES_MAX ; i++) {
			if (this.croyantsGuides[i] != null) {
				isEmpty = false;
			}
		}
		return isEmpty;
	}
	
	/**
	 * Permet de savoir si le tableau des croyants recuperes est plein
	 * @return un boleen indiquant si le tableau est vide ou pas
	 */
	public boolean isFull() {
		boolean isFull=true;
		for (int i = 0; i < this.NB_CARTES_RATTACHEES_MAX ; i++) {
			if (this.croyantsGuides[i] == null) {
				isFull = false;
			}
		}
		return isFull;
	}
	
	/**
	 * Permet de recuperer la valeur de l'attribut stase
	 * @return la valeur recuperee
	 */
	public boolean getStase() {
		return this.stase;
	}
	
/**
 * permet de modifier la valeur de l'attribut stase
 * @param stase est la valeu re afecter e l'attribut
 */
	public void setStase(boolean stase) {
		this.stase = stase;
		if (this.stase == true) {
			this.peutEtreJouee = false;
		}
	}

	/**
	 * permet de modifier la valeur de l'attribut peutEtreJouee
	 * @param est la valeur e affecter e l'attribut
	 */
	public void setPeutEtreJouee(boolean bool) {
		if (stase) {
			this.peutEtreJouee = false;
		} else {
			this.peutEtreJouee = bool;
		}
	}

	/**
	 * Permet de recuperer les dogmes du guide spirituel
	 * @return letableau contenant les dogmes
	 */
	public TypeDogme[] getdogmes() {
		return this.dogmes;
	}

	/**
	 * permet de recuperer le nombre de croyants rattaches e un guide spirituel
	 * @return le nombre recupere
	 */
	public int getNombreCartesCroyants() {
		return nombreCartesCroyants;
	}

	/**
	 * permet de modifier la valeur de l'attribut nombreCartesCroyants
	 * @param nombreCartesCroyants est la valeur e affecter e l'attribut
	 */
	public void setNombreCartesCroyants(int nombreCartesCroyants) {
		this.nombreCartesCroyants = nombreCartesCroyants;
	}
}
