/**
 * 
 */
package strategies;
import java.util.Iterator;
import java.util.LinkedList;
import elementsDeBase.*;
/**
 * La classe StrategieDifficile permet de definir le comportement d'un joeur virtuel de niveau difficile
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *
 */
public class StrategieDifficile implements Strategie{
	
	/**
	 * entier representant le nombres de fois que le joueur a joue
	 */
	private int compteur = 0;
	
	/**
	 * constructeur de la classe 
	 */
	public StrategieDifficile() {
		
	}

	/**
	 * Permet l'enchainement des methodes relatives au deroulement du tour d'un joueur de niveau facile
	 */
public void deployerStrategie(){
	compteur++;
	this.defausser(Partie.getjoueurEnCours().getmain());
	Partie.getjoueurEnCours().completerSaMain();
	this.mettreCroyantAuCentre(Partie.centreDeTable, (JoueurVirtuel) Partie.getjoueurEnCours());
	GuideSpirituel.guiderCroyant((JoueurVirtuel) Partie.getjoueurEnCours());
	Iterator<CarteAction> it = Partie.getjoueurEnCours().getmain().iterator();
	int apocalypsej = 0;
	while (it.hasNext() && apocalypsej == 0) {
		CarteAction apo = it.next();
		if (apo instanceof Apocalypse) {
			jouerUneApocalypse((Apocalypse) apo);
			apocalypsej++;
		}

	}
	if(compteur==4){
		Iterator<CarteAction> itd = Partie.getjoueurEnCours().getmain().iterator();
		int deusj = 0;
		while (itd.hasNext() && deusj == 0) {
			CarteAction deus = itd.next();
			if (deus instanceof DeusEx) {
				jouerUneDeusEx((DeusEx) deus);
				deusj++;
			}
		}
	}
}

/**
 * Cette Methode permet au joueur virtuel de se defausser de cartes contenues dans sa main
 * @param main designe la main du joueur virtuel
 */
public void defausser(LinkedList<CarteAction> main) {
	int cartedef = 0;
	int dogmesEnCommun = 0;
	int compatibilite = 0;
	Iterator<CarteAction> it = main.iterator();
	LinkedList<CarteAction> main2 = new LinkedList<CarteAction>();
	while (it.hasNext()) {
		/*
		 * le joueur se defausse dans un premier temps de cartes qui n'ont pas la meme origine que sa divinite
		 */
		CarteAction carte = (CarteAction) it.next();
		if (carte instanceof Croyant && carte.getorigine()!=Partie.getjoueurEnCours().getorigineDivinite() || carte instanceof GuideSpirituel
				&& carte.getorigine() != Partie.getjoueurEnCours().getorigineDivinite()) {
			main2.add(carte);
			cartedef++;
		}
		/**
		 * le joueur se defausse egalement de cryanstq ui n'ont pas de dogmes en commun avec les guides spirituels qu'il a en main
		 */
		if (carte instanceof Croyant) {
			for (Iterator<CarteAction> itg = Partie.getjoueurEnCours().getmain().iterator(); itg.hasNext();) {
				CarteAction guidesp = itg.next();
				if (guidesp instanceof GuideSpirituel) {
				dogmesEnCommun = guidesp.dogmesEnCommuns(carte);
					if (dogmesEnCommun > 2) {
						compatibilite++;
					}
				}

			}
			if (compatibilite == 0) {
				main2.add(carte);
				cartedef++;
			}
		}

	}
	Partie.getjoueurEnCours().getmain().removeAll(main2);
	
	/**
	 * affiche aux autres joueurs les actions du joueur virtuel
	 */
			if (cartedef == 0) {
		System.out.println(Partie.getjoueurEnCours().getNom() + " n'a pas souhaite se defausser de cartes");
	} else {
		System.out.println(Partie.getjoueurEnCours().getNom() + " s'est defausse de " + cartedef + " cartes");
	}
}

/**
 * Permet au joueur virtuel de mettre des croyants au centre
 * @param jv Joueur virtuel
 * @param centre centre de table
 */
public void mettreCroyantAuCentre(CentreTable centre, JoueurVirtuel jv) {
int croycentre=0;

/**
 * le joueur selectionne l'ensemble des cartes de sa main qu'il peut defausser
 */
	LinkedList<Croyant> tabcroy = new LinkedList<Croyant>();
	for (Iterator<CarteAction> it = Partie.getjoueurEnCours().getmain().iterator(); it.hasNext();) {
		CarteAction c1 = it.next();
		if (c1 instanceof Croyant) {
			tabcroy.add((Croyant) c1);

		}
	}
	
	/**
	 * Par la suite, le joueur se defausse d'un nombre aleatoire de croyants
	 * ce qui lui permet de ne pas perdre tous ses points en une fois
	 */
	for(int i =0; i<Math.round(Math.random()*tabcroy.size());i++){	
		int position = (int) Math.round(Math.random() * (tabcroy.size() - 1));
		if(jv.peutJouer(tabcroy.get(position))){
			Partie.getjoueurEnCours().getmain().remove(tabcroy.get(position));
			centre.ajouterCroyant(tabcroy.get(position));
			croycentre++;
		}
		
	
	}
	/**
	 * affiche aux autres joueurs les actions du joueur virtuel
	 */
	if (croycentre == 0) {
		System.out.println(Partie.getjoueurEnCours().getNom() + " n'a pas souhaite mettre de croyants au centre ");
	} else {
		System.out.println(Partie.getjoueurEnCours().getNom() + " a mis " + croycentre + " croyants au centre");
	}
	
}


/**
 * Permet au joueur virtuel de jouer une carte Apocalypse
 * @param apocalypse carte e jouer
 */
public void jouerUneApocalypse(Apocalypse apocalypse) {
	Joueur jsortant = Partie.getjoueurEnCours();
	Joueur j = Partie.getjoueurEnCours();
	int pointsp = jsortant.getPointsPriere();
	
	/**
	 * le joueur verifie les points de priere des autres joueurs 
	 * s'il a le plus petit nombre de points, il ne joue pas sa carte afin de ne pas d'eliminer de la partie
	 */
	for (Iterator<Joueur> itj = Partie.listeJoueurs.iterator(); itj.hasNext();) {
		if (itj.next().getPointsPriere() < pointsp) {
			jsortant = itj.next();
			pointsp = j.pointsDePriereTotal();
		}
	}
	if (jsortant != Partie.getjoueurEnCours()) {
		apocalypse.jouerCarte();
		
		/**
		 * affiche aux autres joueurs les actions du joueur virtuel
		 */
		System.out.println(Partie.getjoueurEnCours().getNom().toString()+" a joue une carte Apocalypse");
	}
}


/**
 * Permet au joueur Virtuel de jouer une deusEx
 * @param deus est la carte e jouer en question
 */
public void jouerUneDeusEx(DeusEx deus){
	if(Partie.getjoueurEnCours().peutJouer(deus)){
		deus.jouerCarte();
		
		/**
		 * affiche aux autres joueurs les actions du joueur virtuel
		 */
		System.out.println(Partie.getjoueurEnCours().getNom().toString()+" a joue une carte DeusEx");
		deus.toString();
	}
}



}

