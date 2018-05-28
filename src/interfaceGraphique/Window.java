package interfaceGraphique;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;

import elementsDeBase.CarteAction;
/**
 * Classe représentant une fenêtre affiche à l'utilisateur
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *
 */
public class Window {

	/**
	 * attribut contenant un JFrame
	 */
	private JFrame frame;
	/**
	 * attribut contenant la main du joueur
	 */
	private LinkedList<CarteAction> main;

	/**
	 * Create the application
	 */
	public Window(int a) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		VueCarte vc;
	
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setLayout(new GridLayout(1, this.main.size()));

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		frame.setBounds(x-520, 550, this.main.size() * 150, 220);
		frame.setResizable(false);
		Iterator<CarteAction> it = this.main.iterator();
		while (it.hasNext()) {
			vc = new VueCarte(it.next());
			frame.add(vc);
		}
		frame.setAlwaysOnTop(true);
	}

}
