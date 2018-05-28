/**
 * 
 */
package elementsDeBase;

// new
import java.util.Collections;
import java.util.LinkedList;

import capaciteSacrifice.CapaciteApocalypse;
import capaciteSacrifice.DeusEx1;
import capaciteSacrifice.DeusEx10;
import capaciteSacrifice.DeusEx12;
import capaciteSacrifice.DeusEx2;
import capaciteSacrifice.DeusEx3;
import capaciteSacrifice.DeusEx4;
import capaciteSacrifice.DeusEx5;
import capaciteSacrifice.DeusEx6;
import capaciteSacrifice.DeusEx7;
import capaciteSacrifice.DeusEx8;
import capaciteSacrifice.DeusEx9;
import capaciteSacrifice.Sacrifice1;
import capaciteSacrifice.Sacrifice10;
import capaciteSacrifice.Sacrifice11;
import capaciteSacrifice.Sacrifice12;
import capaciteSacrifice.Sacrifice14;
import capaciteSacrifice.Sacrifice15;
import capaciteSacrifice.Sacrifice16;
import capaciteSacrifice.Sacrifice17;
import capaciteSacrifice.Sacrifice18;
import capaciteSacrifice.Sacrifice19;
import capaciteSacrifice.Sacrifice2;
import capaciteSacrifice.Sacrifice20;
import capaciteSacrifice.Sacrifice21;
import capaciteSacrifice.Sacrifice3;
import capaciteSacrifice.Sacrifice4;
import capaciteSacrifice.Sacrifice5;
import capaciteSacrifice.Sacrifice6;
import capaciteSacrifice.Sacrifice7;
import capaciteSacrifice.Sacrifice8;
import capaciteSacrifice.Sacrifice9;

/**
 * @author Anne-Sophie
 *
 */
public class PiocheCarteAction extends Pioche {
	
/**
 * collection contenant les cartes de la pioche
 */
	public LinkedList<CarteAction> listeCartesAction;

	/**
	 *  constructeur de la classe 
	 */
	public PiocheCarteAction(){
		listeCartesAction = new LinkedList<CarteAction>();
		creerCartesAction();
	}

	/**
	 * methode pour tirer la carte du dessus de la pioche
	 * @return la carte tiree
	 * 
	 */
	public CarteAction tirerCarteduDessus() { 
		return listeCartesAction.pop();
	}
	

	
	/**
	 * permet de creer les cartes d'action de la partie
	 */
	public void creerCartesAction() {

		Sacrifice1 Moines = new Sacrifice1("Moines");
		Croyant c1 = new Croyant(TypeOrigine.JOUR, TypeDogme.NATURE, TypeDogme.HUMAIN, TypeDogme.MYSTIQUE, 2, Moines,1);
		listeCartesAction.add(c1);
		Croyant c2 = new Croyant(TypeOrigine.JOUR, TypeDogme.SYMBOLE, TypeDogme.CHAOS, TypeDogme.MYSTIQUE, 2, Moines,2);
		listeCartesAction.add(c2);
		Croyant c3 = new Croyant(TypeOrigine.JOUR, TypeDogme.CHAOS, TypeDogme.HUMAIN, TypeDogme.MYSTIQUE, 2, Moines,3);
		listeCartesAction.add(c3);
		Croyant c4 = new Croyant(TypeOrigine.JOUR, TypeDogme.CHAOS, TypeDogme.NATURE, TypeDogme.MYSTIQUE, 2, Moines,4);
		listeCartesAction.add(c4);
		Croyant c5 = new Croyant(TypeOrigine.JOUR, TypeDogme.NATURE, TypeDogme.SYMBOLE, TypeDogme.MYSTIQUE, 2, Moines,5);
		listeCartesAction.add(c5);
		Sacrifice1 Demons = new Sacrifice1("Demons");
		Croyant c6 = new Croyant(TypeOrigine.NUIT, TypeDogme.NATURE, TypeDogme.HUMAIN, TypeDogme.MYSTIQUE, 2, Demons,6);
		listeCartesAction.add(c6);
		Croyant c7 = new Croyant(TypeOrigine.NUIT, TypeDogme.SYMBOLE, TypeDogme.CHAOS, TypeDogme.MYSTIQUE, 2, Demons,7);
		listeCartesAction.add(c7);
		Croyant c8 = new Croyant(TypeOrigine.NUIT, TypeDogme.CHAOS, TypeDogme.HUMAIN, TypeDogme.MYSTIQUE, 2, Demons,8);
		listeCartesAction.add(c8);
		Croyant c9 = new Croyant(TypeOrigine.NUIT, TypeDogme.CHAOS, TypeDogme.NATURE, TypeDogme.MYSTIQUE, 2, Demons,9);
		listeCartesAction.add(c9);
		Croyant c10 = new Croyant(TypeOrigine.NUIT, TypeDogme.NATURE, TypeDogme.SYMBOLE, TypeDogme.MYSTIQUE, 2, Demons,10);
		listeCartesAction.add(c10);
		Sacrifice1 Esprits = new Sacrifice1("Esprits");
		Croyant c11 = new Croyant(TypeOrigine.NEANT, TypeDogme.NATURE, TypeDogme.HUMAIN, TypeDogme.MYSTIQUE, 2, Esprits,11);
		listeCartesAction.add(c11);
		Croyant c12 = new Croyant(TypeOrigine.NEANT, TypeDogme.SYMBOLE, TypeDogme.CHAOS, TypeDogme.MYSTIQUE, 2, Esprits,12);
		listeCartesAction.add(c12);
		Croyant c13 = new Croyant(TypeOrigine.NEANT, TypeDogme.CHAOS, TypeDogme.HUMAIN, TypeDogme.MYSTIQUE, 2, Esprits,13);
		listeCartesAction.add(c13);
		Croyant c14 = new Croyant(TypeOrigine.NEANT, TypeDogme.CHAOS, TypeDogme.NATURE, TypeDogme.MYSTIQUE, 2, Esprits,14);
		listeCartesAction.add(c14);
		Croyant c15 = new Croyant(TypeOrigine.NEANT, TypeDogme.NATURE, TypeDogme.SYMBOLE, TypeDogme.MYSTIQUE, 2,
				Esprits,15);
		listeCartesAction.add(c15);
		Sacrifice3 Travailleurs3 = new Sacrifice3("Travailleurs");
		Croyant c16 = new Croyant(TypeOrigine.JOUR, TypeDogme.HUMAIN, TypeDogme.MYSTIQUE, TypeDogme.CHAOS, 2,
				Travailleurs3,16);
		listeCartesAction.add(c16);
		Sacrifice3 Alienes3 = new Sacrifice3("Alienes");
		Croyant c17 = new Croyant(TypeOrigine.NEANT, TypeDogme.CHAOS, TypeDogme.MYSTIQUE, TypeDogme.HUMAIN, 2,
				Alienes3,17);
		listeCartesAction.add(c17);
		Sacrifice3 Alchimistes3 = new Sacrifice3("Alchimistes");
		Croyant c18 = new Croyant(TypeOrigine.NEANT, TypeDogme.CHAOS, TypeDogme.MYSTIQUE, TypeDogme.HUMAIN, 2,
				Alchimistes3,18);
		listeCartesAction.add(c18);
		Sacrifice4 Vampires = new Sacrifice4("Vampires");
		Croyant c19 = new Croyant(TypeOrigine.NUIT, TypeDogme.NATURE, TypeDogme.SYMBOLE, TypeDogme.HUMAIN, 2, Vampires,19);
		listeCartesAction.add(c19);
		Croyant c20 = new Croyant(TypeOrigine.NUIT, TypeDogme.CHAOS, TypeDogme.MYSTIQUE, TypeDogme.HUMAIN, 2, Vampires,20);
		listeCartesAction.add(c20);
		Sacrifice4 Integristes = new Sacrifice4("Integristes");
		Croyant c21 = new Croyant(TypeOrigine.JOUR, TypeDogme.NATURE, TypeDogme.CHAOS, TypeDogme.HUMAIN, 1,
				Integristes,21);
		listeCartesAction.add(c21);
		Sacrifice4 Ermite = new Sacrifice4("Ermite");
		Croyant c22 = new Croyant(TypeOrigine.JOUR, TypeDogme.NATURE, TypeDogme.CHAOS, TypeDogme.MYSTIQUE, 1, Ermite,22);
		listeCartesAction.add(c22);
		Croyant c23 = new Croyant(TypeOrigine.JOUR, TypeDogme.NATURE, TypeDogme.SYMBOLE, TypeDogme.MYSTIQUE, 1, Ermite,23);
		listeCartesAction.add(c23);
		Sacrifice4 Revolutionnaires = new Sacrifice4("Revolutionnaires");
		Croyant c24 = new Croyant(TypeOrigine.NEANT, TypeDogme.HUMAIN, TypeDogme.CHAOS, TypeDogme.SYMBOLE, 2,
				Revolutionnaires,24);
		listeCartesAction.add(c24);
		Sacrifice10 Revenant = new Sacrifice10("Revenant");
		Croyant c25 = new Croyant(TypeOrigine.NEANT, TypeDogme.HUMAIN, TypeDogme.MYSTIQUE, TypeDogme.NATURE, 1,
				Revenant,25);
		listeCartesAction.add(c25);
		Sacrifice10 Diplomates = new Sacrifice10("Diplomates");
		Croyant c26 = new Croyant(TypeOrigine.JOUR, TypeDogme.CHAOS, TypeDogme.HUMAIN, TypeDogme.SYMBOLE, 4,
				Diplomates,26);
		listeCartesAction.add(c26);
		Sacrifice6 Nihilistes = new Sacrifice6();
		Croyant c27 = new Croyant(TypeOrigine.NEANT, TypeDogme.CHAOS, TypeDogme.MYSTIQUE, TypeDogme.SYMBOLE, 4,
				Nihilistes,27);
		listeCartesAction.add(c27);
		Sacrifice5 GuerriersSaints = new Sacrifice5();
		Croyant c28 = new Croyant(TypeOrigine.JOUR, TypeDogme.NATURE, TypeDogme.MYSTIQUE, TypeDogme.SYMBOLE, 4,
				GuerriersSaints,28);
		listeCartesAction.add(c28);
		Sacrifice7 Lycanthropes = new Sacrifice7();
		Croyant c29 = new Croyant(TypeOrigine.NUIT, TypeDogme.NATURE, TypeDogme.CHAOS, TypeDogme.HUMAIN, 4,
				Lycanthropes,29);
		listeCartesAction.add(c29);
		Sacrifice8 Pillards = new Sacrifice8();
		Croyant c30 = new Croyant(TypeOrigine.NUIT, TypeDogme.SYMBOLE, TypeDogme.NATURE, TypeDogme.MYSTIQUE, 4,
				Pillards,30);
		listeCartesAction.add(c30);

		Sacrifice9 Illusionnistes = new Sacrifice9();
		Croyant c31 = new Croyant(TypeOrigine.NUIT, TypeDogme.CHAOS, TypeDogme.HUMAIN, TypeDogme.SYMBOLE, 4,
				Illusionnistes,31);
		listeCartesAction.add(c31);

		Sacrifice11 Messie = new Sacrifice11();
		GuideSpirituel c32 = new GuideSpirituel(TypeOrigine.JOUR, TypeDogme.MYSTIQUE, TypeDogme.HUMAIN, 3, Messie,32);
		listeCartesAction.add(c32);
		Sacrifice12 Tyran = new Sacrifice12();
		GuideSpirituel c33 = new GuideSpirituel(TypeOrigine.NEANT, TypeDogme.CHAOS, TypeDogme.SYMBOLE, 3, Tyran,33);
		listeCartesAction.add(c33);
		Sacrifice14 Clerc = new Sacrifice14();
		GuideSpirituel c34 = new GuideSpirituel(TypeOrigine.JOUR, TypeDogme.HUMAIN, TypeDogme.CHAOS, 2, Clerc,34);
		listeCartesAction.add(c34);
		GuideSpirituel c35 = new GuideSpirituel(TypeOrigine.NUIT, TypeDogme.NATURE, TypeDogme.SYMBOLE, 2, Clerc,35);
		listeCartesAction.add(c35);
		GuideSpirituel c36 = new GuideSpirituel(TypeOrigine.NEANT, TypeDogme.MYSTIQUE, TypeDogme.NATURE, 2, Clerc,36);
		listeCartesAction.add(c36);
		GuideSpirituel c37 = new GuideSpirituel(TypeOrigine.JOUR, TypeDogme.CHAOS, TypeDogme.NATURE, 2, Clerc,37);
		listeCartesAction.add(c37);
		GuideSpirituel c38 = new GuideSpirituel(TypeOrigine.NUIT, TypeDogme.SYMBOLE, TypeDogme.MYSTIQUE, 2, Clerc,38);
		listeCartesAction.add(c38);
		GuideSpirituel c39 = new GuideSpirituel(TypeOrigine.NEANT, TypeDogme.CHAOS, TypeDogme.SYMBOLE, 2, Clerc,39);
		listeCartesAction.add(c39);
		GuideSpirituel c40 = new GuideSpirituel(TypeOrigine.JOUR, TypeDogme.CHAOS, TypeDogme.MYSTIQUE, 2, Clerc,40);
		listeCartesAction.add(c40);
		GuideSpirituel c41 = new GuideSpirituel(TypeOrigine.NUIT, TypeDogme.HUMAIN, TypeDogme.NATURE, 2, Clerc,41);
		listeCartesAction.add(c41);
		Sacrifice15 Shaman = new Sacrifice15();
		GuideSpirituel c42 = new GuideSpirituel(TypeOrigine.NUIT, TypeDogme.SYMBOLE, TypeDogme.NATURE, 3, Shaman,42);
		listeCartesAction.add(c42);
		Sacrifice17 Paladin = new Sacrifice17();
		GuideSpirituel c43 = new GuideSpirituel(TypeOrigine.JOUR, TypeDogme.MYSTIQUE, TypeDogme.HUMAIN, 3, Paladin,43);
		listeCartesAction.add(c43);
		Sacrifice16 Anarchiste = new Sacrifice16();
		GuideSpirituel c44 = new GuideSpirituel(TypeOrigine.NEANT, TypeDogme.CHAOS, TypeDogme.HUMAIN, 3, Anarchiste,44);
		listeCartesAction.add(c44);
		Sacrifice18 Ascete = new Sacrifice18();
		GuideSpirituel c45 = new GuideSpirituel(TypeOrigine.NUIT, TypeDogme.SYMBOLE, TypeDogme.HUMAIN, 1, Ascete,45);
		listeCartesAction.add(c45);
		Sacrifice19 Devin = new Sacrifice19();
		GuideSpirituel c46 = new GuideSpirituel(TypeOrigine.NEANT, TypeDogme.MYSTIQUE, TypeDogme.NATURE, 1, Devin,46);
		listeCartesAction.add(c46);
		Sacrifice20 Exorcistes = new Sacrifice20();
		GuideSpirituel c47 = new GuideSpirituel(TypeOrigine.JOUR, TypeDogme.CHAOS, TypeDogme.MYSTIQUE, 1, Exorcistes,47);
		listeCartesAction.add(c47);
		Sacrifice21 Sorcier = new Sacrifice21();
		GuideSpirituel c48 = new GuideSpirituel(TypeOrigine.NUIT, TypeDogme.SYMBOLE, TypeDogme.MYSTIQUE, 3, Sorcier,48);
		listeCartesAction.add(c48);
		DeusEx1 ColereDivine1 = new DeusEx1(TypeOrigine.NUIT);
		DeusEx c49 = new DeusEx(TypeOrigine.JOUR, ColereDivine1,49);
		listeCartesAction.add(c49);
		DeusEx1 ColereDivine2 = new DeusEx1(TypeOrigine.JOUR);
		DeusEx c50 = new DeusEx(TypeOrigine.JOUR, ColereDivine2,50);
		listeCartesAction.add(c50);
		CapaciteApocalypse Apocalypse = new CapaciteApocalypse("");
		Apocalypse c51 = new Apocalypse(TypeOrigine.JOUR, Apocalypse,51);
		listeCartesAction.add(c51);
		Apocalypse c52 = new Apocalypse(TypeOrigine.NUIT, Apocalypse,52);
		listeCartesAction.add(c52);
		Apocalypse c53 = new Apocalypse(TypeOrigine.NEANT, Apocalypse,53);
		listeCartesAction.add(c53);
		Apocalypse c54 = new Apocalypse(Apocalypse,55);
		listeCartesAction.add(c54);
		Apocalypse c55 = new Apocalypse(Apocalypse,55);
		listeCartesAction.add(c55);
		DeusEx3 OrdreCeleste = new DeusEx3("Ordre Celeste");
		DeusEx c56 = new DeusEx(TypeOrigine.JOUR, OrdreCeleste,56);
		listeCartesAction.add(c56);
		DeusEx3 Concentration = new DeusEx3("Concentration");
		DeusEx c57 = new DeusEx(TypeOrigine.NEANT, Concentration,57);
		listeCartesAction.add(c57);
		DeusEx12 Inquisition = new DeusEx12();
		DeusEx c58 = new DeusEx(Inquisition,58);
		listeCartesAction.add(c58);
		DeusEx5 Diversion = new DeusEx5();
		DeusEx c59 = new DeusEx(TypeOrigine.NUIT, Diversion,59);
		listeCartesAction.add(c59);
		DeusEx6 TrouNoir = new DeusEx6();
		DeusEx c60 = new DeusEx(TypeOrigine.NEANT, TrouNoir,60);
		listeCartesAction.add(c60);
		Sacrifice10 Bouleversement = new Sacrifice10("Bouleversement");
		DeusEx c61 = new DeusEx( Bouleversement,61);
		listeCartesAction.add(c61);
		DeusEx7 Phoenix = new DeusEx7();
		DeusEx c62 = new DeusEx(TypeOrigine.NEANT, Phoenix,62);
		listeCartesAction.add(c62);
		CapaciteApocalypse Martyr = new CapaciteApocalypse("Martyr");
		GuideSpirituel c63 = new GuideSpirituel(TypeOrigine.JOUR, TypeDogme.HUMAIN, TypeDogme.NATURE, 2, Martyr,63);
		listeCartesAction.add(c63);
		GuideSpirituel c64 = new GuideSpirituel(TypeOrigine.NUIT, TypeDogme.SYMBOLE, TypeDogme.HUMAIN, 2, Martyr,64);
		listeCartesAction.add(c64);
		GuideSpirituel c65 = new GuideSpirituel(TypeOrigine.NEANT, TypeDogme.CHAOS, TypeDogme.NATURE, 2, Martyr,65);
		listeCartesAction.add(c65);
		DeusEx4 Fourberie = new DeusEx4();
		DeusEx c66 = new DeusEx(TypeOrigine.NUIT, Fourberie,66);
		listeCartesAction.add(c66);
		DeusEx2 Stase = new DeusEx2();
		DeusEx c67 = new DeusEx(TypeOrigine.JOUR, Stase,67);
		listeCartesAction.add(c67);
		DeusEx8 InfluenceJour = new DeusEx8(TypeOrigine.JOUR);
		DeusEx8 InfluenceNuit = new DeusEx8(TypeOrigine.NUIT);
		DeusEx8 InfluenceNeant = new DeusEx8(TypeOrigine.NEANT);
		DeusEx8 InfluenceNulle = new DeusEx8();
		DeusEx c68 = new DeusEx(InfluenceJour,68);
		listeCartesAction.add(c68);
		DeusEx c69 = new DeusEx(InfluenceNuit,69);
		listeCartesAction.add(c69);
		DeusEx c70 = new DeusEx(InfluenceNeant,70);
		listeCartesAction.add(c70);
		DeusEx c71 = new DeusEx(InfluenceNulle,71);
		listeCartesAction.add(c71);
		DeusEx c72 = new DeusEx(InfluenceNulle,72);
		listeCartesAction.add(c72);
		Sacrifice2 Travailleurs1 = new Sacrifice2("Travailleurs", TypeDogme.NATURE, TypeDogme.MYSTIQUE);
		Croyant c73 = new Croyant(TypeOrigine.JOUR, TypeDogme.CHAOS, TypeDogme.SYMBOLE, TypeDogme.HUMAIN, 2,
				Travailleurs1,73);
		listeCartesAction.add(c73);
		Sacrifice2 Travailleurs2 = new Sacrifice2("Travailleurs", TypeDogme.CHAOS, TypeDogme.MYSTIQUE);
		Croyant c74 = new Croyant(TypeOrigine.JOUR, TypeDogme.HUMAIN, TypeDogme.SYMBOLE, TypeDogme.NATURE, 2,
				Travailleurs2,74);
		listeCartesAction.add(c74);
		Sacrifice2 Alchimistes1 = new Sacrifice2("Alchimistes", TypeDogme.HUMAIN, TypeDogme.MYSTIQUE);
		Croyant c75 = new Croyant(TypeOrigine.NUIT, TypeDogme.CHAOS, TypeDogme.SYMBOLE, TypeDogme.NATURE, 2,
				Alchimistes1,75);
		listeCartesAction.add(c75);
		Sacrifice2 Alchimistes2 = new Sacrifice2("Alchimistes", TypeDogme.HUMAIN, TypeDogme.SYMBOLE);
		Croyant c76 = new Croyant(TypeOrigine.NUIT, TypeDogme.CHAOS, TypeDogme.MYSTIQUE, TypeDogme.NATURE, 2,
				Alchimistes2,76);
		listeCartesAction.add(c76);
		Sacrifice2 Alienes1 = new Sacrifice2("Alienes", TypeDogme.NATURE, TypeDogme.MYSTIQUE);
		Croyant c77 = new Croyant(TypeOrigine.NEANT, TypeDogme.CHAOS, TypeDogme.SYMBOLE, TypeDogme.HUMAIN, 2,
				Alienes1,77);
		listeCartesAction.add(c77);
		Sacrifice2 Alienes2 = new Sacrifice2("Alienes", TypeDogme.MYSTIQUE, TypeDogme.CHAOS);
		Croyant c78 = new Croyant(TypeOrigine.NEANT, TypeDogme.HUMAIN, TypeDogme.SYMBOLE, TypeDogme.NATURE, 2,
				Alienes2,78);
		listeCartesAction.add(c78);
		DeusEx9 Transe=new DeusEx9();
		DeusEx c79 = new DeusEx(Transe,79);
		listeCartesAction.add(c79);
		DeusEx10 Miroir = new DeusEx10();
		DeusEx c80 = new DeusEx(Miroir,80);
		listeCartesAction.add(c80);
		/**
		 * permet de melanger la collection
		 */
		Collections.shuffle(listeCartesAction);
		
	}

}
