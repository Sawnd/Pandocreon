package interfaceGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Toolkit;
import java.util.Iterator;

import javax.swing.JPanel;

import elementsDeBase.CentreTable;
import elementsDeBase.Croyant;
/**
 * Classe permettant d'afficher le centre de table
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *elle hérite de la classe JLabel
 */
public class VueCentreTable extends JPanel {
	/**
	 * gives the programmer control over which versions of a class are considered incompatible in regard to serialization
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Attribut contenant le centre de table
	 */
	private CentreTable centre;

	/**
	 * constructeur de la classe
	 * @param ct centre de table à affecter à l'attribut centre
	 */
	public VueCentreTable(CentreTable ct) {
		
		this.centre = ct;
		initialize();
		if (centre.getCroyantsCentre().isEmpty()) {
			MessageBox.getMessageBox().ajouterMessage("Le centre de la table est vide");
			this.setVisible(false);
		} else {
			this.setVisible(true);
			
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		setBackground(new Color(0, 0, 0, 0));
		setSize(new Dimension(300, 200));
		setLayout(new CircleLayout());
		VueCarte vc;
		Iterator<Croyant> it = this.centre.getCroyantsCentre().iterator();
		while (it.hasNext()) {
			vc = new VueCarte(it.next());
			vc.setEstAuCentre(true);
			this.add(vc);
		}
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);

		this.setBounds(x / 2, y / 2 - 150, 500, 500);
	}
	
	/**
	 * Permet de peindrre un élémnet graphqiue
	 * @param g élémént à peindre
	 */
	protected void paintComponent(Graphics g) {
		if (g instanceof Graphics2D) {
			final int R = 240;
			final int G = 240;
			final int B = 240;
			Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0), 0.0f, getHeight(),
					new Color(R, G, B, 0), true);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(p);
			g2d.fillRect(0, 0, getWidth(), getHeight());
		}
	}
	/**
	 * Permet de mettre à jour le centre de table
	 * @param ct valeur à affecter à l'attribut centre
	 */
	public void mettreAJourLeCentreDeTable(CentreTable ct) {
		this.centre = ct;
		removeAll();
		   revalidate();
		
		if (centre.getCroyantsCentre().isEmpty()) {
			MessageBox.getMessageBox().ajouterMessage("Le centre de la table est vide");
		} else {
			initialize();
		}
	}


}
