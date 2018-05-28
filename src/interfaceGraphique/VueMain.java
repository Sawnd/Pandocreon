package interfaceGraphique;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;

import elementsDeBase.CarteAction;
/**
 * Classe permettant d'afficher la main du joueur en cours
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *enne herite de JPanel
 */
public class VueMain extends JPanel {

	/**
	 * gives the programmer control over which versions of a class are considered incompatible in regard to serialization
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * liste de cartes d'action representant la main
	 */
	private LinkedList<CarteAction> main;
	
	/**
	 * permet l'ajout des elements graphiques
	 */
	private JFrame frame2;
	
/***
 * Constructeur de la classe
 * @param m liste de cartes e affecter e l'attribut main
 * @param a permettant de faire la difference entre les constructeurs
 */
	public VueMain(LinkedList<CarteAction> m, int a) {
		super();
		this.main = m;
		initialize2();
	    frame2.setVisible(true);
	}

	/**
	 * 	constructeur de la classe
	 * @param m liste de carte a affecter e l'attribut main
	 */
	public VueMain(LinkedList<CarteAction> m) {
		this.main = m;
		initialize();
		

	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		VueCarte vc;
		
	setLayout(new GridLayout(1, this.main.size()));

		Toolkit.getDefaultToolkit().getScreenSize();
		Iterator<CarteAction> it = this.main.iterator();
		while (it.hasNext()) {
			vc = new VueCarte(it.next());
			add(vc);
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize2() {
		VueCarte vc;
		frame2 = new JFrame();
		frame2.setAlwaysOnTop(true);
		 new JWindow();
		frame2.setLayout(new GridLayout(1, this.main.size()));
		frame2.setTitle("Croyants rattaches");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame2.getWidth()) / 2);
		frame2.setBounds(x / 6, 0, this.main.size() * 90, 140);
		frame2.setResizable(false);
		Iterator<CarteAction> it = this.main.iterator();
		while (it.hasNext()) {
			vc = new VueCarte(it.next());
			vc.setEstGuide(true);

			frame2.add(vc);
		}
	
	}

	/**
	 * Methode permettant de mettre e jouer la vue de la main
	 * @param m
	 */
	public void MettreAjourLaMain(LinkedList<CarteAction> m) {
		this.main = m;
		removeAll();
	   revalidate();
	
	}

	/**
	 * permet de retourner les dimensions de la main
	 * @return la valeur de la dimension
	 */
	public Dimension getPreferredSize() {
		return new Dimension(this.main.size() * 90, 140);
	}
}