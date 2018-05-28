/**
 * 
 */
package elementsDeBase;

/**
* La classe Carte est une classe abstraite que vont implémenter toutes les cartes du jeu
* @author Anne-Sophie Ndoumbe
* @author Stephane Deffon
*/
public abstract class Carte {
/**
 * origine de la carte
 */
	protected TypeOrigine origines = TypeOrigine.NULL;
	
	/**
	 * dogmes de la carte
	 */
	protected TypeDogme[] dogmes = { TypeDogme.NULL, TypeDogme.NULL, TypeDogme.NULL };
	
	/**
	 * Nombre de cartes créées
	 */
	public static int NOMBRE_DE_CARTES = 0; 
	
	/**
	 * entier permettant d'identifier la carte
	 */
    private int id =0;
    
    /**
     *  booléen permettant de savoir si la carte peut être jouée
     */
	protected boolean peutEtreJouee = true;

	/**
	 *  premier constructeur de la classe
	 *  Permet d'avoir des cartes ne possedant ni dogmes, ni origine
	 * @param id valeur à affecter à l'atttribut id
	 */
    public Carte(int id) {
		
		this.id=id;
	}

    /**
     * deuxième constructeur de la carte
     * Permet d'avoir des cartes ne possedant pas de dogmes
     * @param origine valeur à affecter à l'origine de la carte
     * @param id valeur à affecter l'identifiant de la carte
     */
	public Carte(TypeOrigine origine,int id) {
		NOMBRE_DE_CARTES++;
		this.origines = origine;
		this.id=id;
	}
	
	/**
	 * Troisième constructeur permettant d'avoir  des cartes possedant une origine et deux dogmes
	 * @param origine valeur à affecter à l'origine de la carte
	 * @param dogme1 valeur du premier dogme de la carte
	 * @param dogme2 valeur du deuxième dogme de la carte
	 * @param id valeur à affecter à l'identifiant de la carte
	 */

	public Carte(TypeOrigine origine, TypeDogme dogme1, TypeDogme dogme2,int id) {
		NOMBRE_DE_CARTES++;
		this.origines = origine;
		this.dogmes[0] = dogme1;
		this.dogmes[1] = dogme2;
       this.id=id;
	}

	
	/**
	 * quatrième constructeur de la classe
	 * Permettant d'avoir des cartes possedant une origine et trois dogmes
	 * @param origine valeur à affecter à l'origine de la carte
	 * @param dogme1 valeur du premier dogme de la carte
	 * @param dogme2 valeur du deuxième dogme de la carte
	 * @param dogme3 valeur du troisième dogme de la carte
	 * @param id valeur à affecter à l'identfiant de la carte
	 */
	public Carte(TypeOrigine origine, TypeDogme dogme1, TypeDogme dogme2, TypeDogme dogme3,int id) {
		NOMBRE_DE_CARTES++;
		this.origines = origine;
		this.dogmes[0] = dogme1;
		this.dogmes[1] = dogme2;
		this.dogmes[2] = dogme3;
		this.id=id;
	}

	
	/**
	 * Cette méthode permet un affichage des attributs de la carte
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(" Origine: " + this.origines);
		sb.append(" Dogmes: " + this.dogmes[0] + " " + this.dogmes[1] + " " + this.dogmes[2]);
		return sb.toString();
	}
 
	/**
	 * Méthode  permettant de jouer une carte
	 * Elle est implémentée dans chaque classe héritant de la classe carte
	 */
	public void jouerCarte() {
	}

	/**
	 * Permet de récupérer l'origine de la carte
	 * @return l'origine de la carte
	 */
	public TypeOrigine getorigine() {
		return this.origines;
	}
	
	/**
	 * Permet de récupérer le premier dogme de la carte
	 * @return le dogme en question
	 */
	public TypeDogme getdogme1() {
		return this.dogmes[0];
	}
	
	/**
	 * Permet de récupérer le deuxième dogme de la carte
	 * @return le dogme en question
	 */

	public TypeDogme getdogme2() {
		return this.dogmes[1];
	}

	/**
	 * Permet de récupérer le troisième dogme de la carte
	 * @return le dogme en question
	 */
	public TypeDogme getdogme3() {
		return this.dogmes[2];
	}

	/**
	 * permet d'affecter une valeur à l'attribut peutEtreJouee
	 * @param booleen contient la valeur booléenne à affecter
	 */
	public void setPeutEtreJouee(boolean booleen) {
		this.peutEtreJouee = booleen;
	}

	/**
	 * Permet de récupérer la valeur de l'attribut peutEtreJouee
	 * @return la valeur de l'attribut en question
	 */
	public boolean getPeutEtreJouee() {
		return this.peutEtreJouee;
	}
	
	
	/**
	 * Permet de retourner la valeur de l'identifiant de la carte
	 * @return la valeur en question
	 */
	public int getId(){
		return this.id;
	}
	
	
	/**
	 * Permet de determiner le dogme en communs entre deux cartes
	 * @param c est la carte à laquelle il faut comparre la carte dont la méthode a été appelée
	 * @return le nombre de dogmes en commun
	 */

	public int dogmesEnCommuns(Carte c) {
		int dogmesCommuns = 0;
		int i = 0;  
		/**
		 * on compare les dogmes des deux cartes un à un
		 * S'ils sont équivalents, on incrémente un compteur qu'on retpurne à la fin de la méthode
		 */
		while (this.dogmes[i] != null && i < 2) {
			if (this.dogmes[i] == c.getdogme1() || this.dogmes[i] == c.getdogme2() || this.dogmes[i] == c.getdogme3()) {
				dogmesCommuns++;
			}
			i++;
		}
		return dogmesCommuns;
	}
	
	
}


