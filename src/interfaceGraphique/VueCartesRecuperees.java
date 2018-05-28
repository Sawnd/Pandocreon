package interfaceGraphique;

import java.awt.GridLayout;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import elementsDeBase.GuideSpirituel;

/**
 * Classe permettant d'afficher les cartes recuperees par un guide spirituel
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *elle herite de la classe JLabel
 */
public class VueCartesRecuperees extends JLabel {
	/**
	 * gives the programmer control over which versions of a class are considered incompatible in regard to serialization
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * collection contenna tles cartes recuperees
	 */
	private LinkedList<GuideSpirituel> cartesRec;
	/**
	 * attribut contenant un JFrame
	 */
	private JFrame frame;

	/**
	 * Constructeur de la classe
	 * @param cr cartes recuperees par le guide
	 */
	public VueCartesRecuperees(LinkedList<GuideSpirituel> cr) {
		this.cartesRec = cr;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		VueCarte vc;
		frame = new JFrame();
		frame.setUndecorated(true);
		int size = cartesRec.size();
		int size2 = 1;
		if (size > 3) {
			size -= 2;
			size2++;
		}
		if (size > 6) {
			size -= 4;
			size2++;
		}
		frame.setLayout(new GridLayout(size, 5));
		frame.setBounds(0, 0, 90 * size2, 140 * size);
		frame.setResizable(false);
		Iterator<GuideSpirituel> it = this.cartesRec.iterator();
		while (it.hasNext()) {
			vc = new VueCarte(it.next());
			vc.setEstGuide(true);
			frame.add(vc);
		}
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
	}

	/**
	 * Mettre e jouer les cartes recuperees par un guide spirituel
	 * @param cr nouvelle valeur de la liste
	 */
	public void MettreAjourLesCartesRec(LinkedList<GuideSpirituel> cr) {
		this.cartesRec = cr;
		frame.dispose();
		initialize();
	}

	
}