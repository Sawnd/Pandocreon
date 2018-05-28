/**
 * 
 */
package elementsDeBase;

/**
* La classe Carte est une classe abstraite que vont impl�menter toutes les cartes du jeu
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
	 * Nombre de cartes cr��es
	 */
	public static int NOMBRE_DE_CARTES = 0; 
	
	/**
	 * entier permettant d'identifier la carte
	 */
    private int id =0;
    
    /**
     *  bool�en permettant de savoir si la carte peut �tre jou�e
     */
	protected boolean peutEtreJouee = true;

	/**
	 *  premier constructeur de la classe
	 *  Permet d'avoir des cartes ne possedant ni dogmes, ni origine
	 * @param id valeur � affecter � l'atttribut id
	 */
    public Carte(int id) {
		
		this.id=id;
	}

    /**
     * deuxi�me constructeur de la carte
     * Permet d'avoir des cartes ne possedant pas de dogmes
     * @param origine valeur � affecter � l'origine de la carte
     * @param id valeur � affecter l'identifiant de la carte
     */
	public Carte(TypeOrigine origine,int id) {
		NOMBRE_DE_CARTES++;
		this.origines = origine;
		this.id=id;
	}
	
	/**
	 * Troisi�me constructeur permettant d'avoir  des cartes possedant une origine et deux dogmes
	 * @param origine valeur � affecter � l'origine de la carte
	 * @param dogme1 valeur du premier dogme de la carte
	 * @param dogme2 valeur du deuxi�me dogme de la carte
	 * @param id valeur � affecter � l'identifiant de la carte
	 */

	public Carte(TypeOrigine origine, TypeDogme dogme1, TypeDogme dogme2,int id) {
		NOMBRE_DE_CARTES++;
		this.origines = origine;
		this.dogmes[0] = dogme1;
		this.dogmes[1] = dogme2;
       this.id=id;
	}

	
	/**
	 * quatri�me constructeur de la classe
	 * Permettant d'avoir des cartes possedant une origine et trois dogmes
	 * @param origine valeur � affecter � l'origine de la carte
	 * @param dogme1 valeur du premier dogme de la carte
	 * @param dogme2 valeur du deuxi�me dogme de la carte
	 * @param dogme3 valeur du troisi�me dogme de la carte
	 * @param id valeur � affecter � l'identfiant de la carte
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
	 * Cette m�thode permet un affichage des attributs de la carte
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(" Origine: " + this.origines);
		sb.append(" Dogmes: " + this.dogmes[0] + " " + this.dogmes[1] + " " + this.dogmes[2]);
		return sb.toString();
	}
 
	/**
	 * M�thode  permettant de jouer une carte
	 * Elle est impl�ment�e dans chaque classe h�ritant de la classe carte
	 */
	public void jouerCarte() {
	}

	/**
	 * Permet de r�cup�rer l'origine de la carte
	 * @return l'origine de la carte
	 */
	public TypeOrigine getorigine() {
		return this.origines;
	}
	
	/**
	 * Permet de r�cup�rer le premier dogme de la carte
	 * @return le dogme en question
	 */
	public TypeDogme getdogme1() {
		return this.dogmes[0];
	}
	
	/**
	 * Permet de r�cup�rer le deuxi�me dogme de la carte
	 * @return le dogme en question
	 */

	public TypeDogme getdogme2() {
		return this.dogmes[1];
	}

	/**
	 * Permet de r�cup�rer le troisi�me dogme de la carte
	 * @return le dogme en question
	 */
	public TypeDogme getdogme3() {
		return this.dogmes[2];
	}

	/**
	 * permet d'affecter une valeur � l'attribut peutEtreJouee
	 * @param booleen contient la valeur bool�enne � affecter
	 */
	public void setPeutEtreJouee(boolean booleen) {
		this.peutEtreJouee = booleen;
	}

	/**
	 * Permet de r�cup�rer la valeur de l'attribut peutEtreJouee
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
	 * @param c est la carte � laquelle il faut comparre la carte dont la m�thode a �t� appel�e
	 * @return le nombre de dogmes en commun
	 */

	public int dogmesEnCommuns(Carte c) {
		int dogmesCommuns = 0;
		int i = 0;  
		/**
		 * on compare les dogmes des deux cartes un � un
		 * S'ils sont �quivalents, on incr�mente un compteur qu'on retpurne � la fin de la m�thode
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


