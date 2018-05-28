package elementsDeBase;
import java.util.LinkedList;
import java.util.Scanner;

import strategies.Strategie;
/**
* La classe Carte est une classe representant un joueur  virtuel
* @author Anne-Sophie Ndoumbe
* @author Stephane Deffon
* Elle herite de le la classe joueur
*/
public class JoueurVirtuel extends Joueur {
	
	/**
	 * Methode contenant la strategie du joueur
	 */
private Strategie strategie;

/**
 * Constructeur de la classe
 * @param strategie permettant d'attribuer une strageie à un joueur
 */
	public JoueurVirtuel(Strategie strategie) {
		super();
		System.out.println("Nom du joueur "+numeroJoueur+" ?");
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		String str=sc.nextLine();
		this.nom =str;
	    this.strategie = strategie;
	}
	
	/**
	 * Constructeur de la classe
	 * @param strategie permettant d'attribuer une strategie e un joueur 
	 * @param nom permettant d'attribuer un nom au joueur
	 */
	public JoueurVirtuel(String nom ,Strategie strategie){
		super();
		this.nom=nom;
		this.strategie=strategie;
	}
	
	/**
	 * Permet de modifier la valeur de l'attribut divinite
	 * @param div valeur e affecter e l'attribut
	 */
	public void setDivinite(Divinite div){
	super.setDivinite(div);
	}

	/**
	 * permet de modifier la valeur des points Jour 
	 * @param point valeur e affecter
	 */
	public void setpointsJour(int point){
	super.setpointsJour(point);
	}

	/**
	 * permet de modifier la valeur des points Nuit 
	 * @param point valeur e affecter
	 */
		
	public void setpointsNuit(int point){
		super.setpointsNuit(point);
	}

	/**
	 * permet de modifier la valeur des points Neant
	 * @param point valeur e affecter
	 */
		
	public  void setpointsNeant(int point){
	super.setpointsNeant(point);
	}

	/**
	 * permet de modifier la valeur des points de priere
	 * @param point valeur e affecter
	 */
		
	public  void setpointsPriere(int point){
		super.setpointsPriere(point);
		}

	/**
	 * permet d'ajouter une care e la main du joueur
	 * @param carte carte e ajouter
	 */
	public void ajouterCarte(CarteAction carte){
		super.ajouterCarte(carte);
	}
	/**
	 * Permet d'afficher le centre de table
	 */
	public void afficherCentreDeTable(){
		super.afficherCentreDeTable();
	}
	/**
	 * Permet au joueur de completer sa main
	 */
	public void completerSaMain() {
		if (!aPioche) {
			while (main.size() != 7)
			{
				main.add(Partie.piocheCarteAction.tirerCarteduDessus());
				if(main.size()==7){
					System.out.println(nom+" a complete sa main!");
				}
			}
			
			aPioche = true;
		}
		
	}
	/**
	 * Methode qui permet au joueur de joueur
	 * @param i permettant de recuperer le nombre de tours
	 * @param partie permettant de recuperer l'unique instance de la partie
	 */
	public int jouer(int i, Partie partie){
		System.out.println("\n"+"C'est au tour de "+nom+" de jouer!"+"\n");
		this.setaPioche();
	   strategie.deployerStrategie();
		return super.jouer(i, partie);
	}
	/**
	 * Permet de recuperer la valeur de la strategie du joueur
	 * @return la valeur recuperee
	 */
	public Strategie getStrategie() {
		return strategie;
	}
	/**
	 * Permet de modifier la strategie du joueur
	 * @param strategie strategie e attribuer au joueur
	 */
	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}
	/**
	 * Methode permettant d'afficher les cartes recuperees par tous les joueurs
	 */
		public void afficherCartesRecupereesTousLesJoueurs() {
		super.afficherCartesRecupereesTousLesJoueurs();
}
	
		/**
		 * Methode permettant d'afficher les guides spirituels recuperes
		 */
		public void afficherCartesRecuperees(){
			super.afficherCartesRecuperees();
		}
		/**
		 * Methode permettant de recuperre la divinite du joueur
		 * @return la divinite
		 */
		public Divinite getDivinite(){
			return super.getDivinite();
		}

		/**
		 * Methode permettant de recuperer les points jour du joueur
		 * @return les points jour
		 */
		public int getPointsJour() {
		return super.getPointsJour();
		}

		/**
		 * Methode permettant de recuperer les points nuit  du joueur
		 * @return les points nuit
		 */
		public int getPointsNuit() {
		return super.getPointsNuit();
		}

		/**
		 * Methode permettant de recuperer les points de priere du joueur
		 * @return les points de priere
		 */
		public int getPointsPriere() {
			return super.getPointsPriere();
			}
		/**
		 * Methode permettant de recuperer les points neant du joueur
		 * @return les points neant
		 */
		public int getPointsNeant() {
		return super.getPointsNeant();
		}

		/**
		 * Methode permettant de recuperer le nom du joueur
		 * @return le nom du joueur
		 */
		public String getNom() {
		return super.getNom();
		}

		/**
		 * Methode permettant de recuperer la main du joueur
		 * @return la main du joueur
		 */
		public LinkedList<CarteAction> getmain() {
		return super.getmain();
		}
		

			/**
			 * Methode permettant de recuperer un dogme de la divinie du joueur
			 * @param indice du dogme 
			 * @return le dogme recupere
			 */
			public TypeDogme getDogmeDivinite(int i){
			return super.getDogmeDivinite(i);
			}

			/**
			 * Methode permettant de modifier la main du joueur
			 * @param main valeur e affecter e la main
			 */

			public void setmain(LinkedList<CarteAction> main) {
			super.setmain(main);
			}

			/**
			 * Methode permettant de recuperer les guides spirituels 
			 * @return les guides recuperes
			 */
			public LinkedList<GuideSpirituel> getcartesRecuperees() {
			return super.getcartesRecuperees();
			}

			/**
			 * Permet de modifier la valeur des cartes recuperees
			  *@param cartesRec valeur e affecter
			 */
			public void setcartesRecuperees(LinkedList<GuideSpirituel> cartesRec) {
			super.setcartesRecuperees(cartesRec);
			}

			/**
			 * 	m	ethode permettant de modifier l'attribut aPioche
			 */
			public void setaPioche() {
			super.setaPioche();
			}
			/**
			 * Permet de recuperer les points de prieres total du joueur
			 * @return les points de priere
			 */
			public int pointsDePriereTotal() {
				return super.pointsDePriereTotal();
			}
			/**
			 * Methode permettant de recuperre le nombre dr croyants rattaches e un guide
			 * @return le nombre recupere
			 */
			public int nbCroyantsRattachesTotal(){
				return super.nbCroyantsRattachesTotal();
			}

			/**
			 * Permet d'afficher les points de priere du joueur
			 */
			public void afficherPointsDePrieres() {
				super.afficherPointsDePrieres();
			}

			/**
			 * Methode permettant de savoir si un guide spirituel croit e un dogme
			 * @param dogme e verifier
			 * @return booleen permettant de savoir si le guide croit au dogme ou pas
			 */
			public boolean croitAuDogme(TypeDogme dogme){
				return super.croitAuDogme(dogme);
			}
			/**
			 * Methode permettant de savoir si une divinite croit e un dogme
			 * @param dogme e verifier
			 * @return booleen permettant de savoir si la divinite croit au dogme ou pas
			 */
			public boolean divCroitAuDogme(TypeDogme dogme){
				return super.divCroitAuDogme(dogme);
			}

		/**
		 * Permet d'afficher le nom du joueur
		 */
		public String toString() {
				return super.toString();
		}
		public boolean peutJouer(Carte c) {
			boolean peutEtreJouee = true;
			if (c.peutEtreJouee) {
				if (c.origines == TypeOrigine.JOUR && pointsJour >= 1) {
					setpointsJour(-1);
				} else if (c.origines == TypeOrigine.NULL) {

				} else if (c.origines == TypeOrigine.NUIT && pointsNuit >= 1) {

					setpointsNuit(-1);
				} else if (c.origines == TypeOrigine.NEANT && (pointsNuit >= 2 || pointsJour >= 2 || pointsNeant >= 1)) {
					if (pointsNeant >= 1) {
						setpointsNeant(-1);
					} else if (pointsNuit >= 2 && pointsJour >= 2) {
						
							setpointsJour(-2);
					} else if (pointsNuit >= 2) {
						setpointsNuit(-2);
					} else {
						setpointsJour(-2);
					}
				} else {
					
					peutEtreJouee = false;// new
				}
			} else

			{
				
				peutEtreJouee = false;
			}
			return peutEtreJouee;
		}
		  
}
