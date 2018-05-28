package interfaceGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import elementsDeBase.Partie;
/**
 * Classe permettant d'afficher les informations sur le joueur en cours
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 * Implemente l'interface Observer
 *
 */
public class InformationsJoueur extends JPanel implements Observer {
	/**
	 * gives the programmer control over which versions of a class are considered incompatible in regard to serialization
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * varaibales contenant du texte
	 */
	private JTextArea lblCosmogonie, lblNomDuJoueur, lblPointsJour, lblPointsNuit, lblPointsNeant, lblApocalypse,
			lblPointsDePrieres;

	/**
	 * Create the panel.
	 */
	public InformationsJoueur() {
		Partie.getPartie().addObserver(this);
		setBackground(new Color(0, 0, 0, 255));
		setLayout(new GridLayout(1, 7));
		setPreferredSize(new Dimension(600, 50));

		lblCosmogonie = new JTextArea("Cosmogonie :");
		add(lblCosmogonie);
		lblCosmogonie.setBackground(Color.DARK_GRAY);
		lblCosmogonie.setForeground(Color.WHITE);
		lblNomDuJoueur = new JTextArea("Nom du Joueur");
		add(lblNomDuJoueur);
		lblNomDuJoueur.setBackground(Color.DARK_GRAY);
		lblNomDuJoueur.setForeground(Color.WHITE);
		lblPointsJour = new JTextArea("Points Jour :");
		add(lblPointsJour);
		lblPointsJour.setBackground(Color.DARK_GRAY);
		lblPointsJour.setForeground(Color.WHITE);
		lblPointsNuit = new JTextArea("Points Nuit : ");
		add(lblPointsNuit);
		lblPointsNuit.setBackground(Color.DARK_GRAY);
		lblPointsNuit.setForeground(Color.WHITE);
		lblPointsNeant = new JTextArea("Points Néant : ");
		add(lblPointsNeant);
		lblPointsNeant.setBackground(Color.DARK_GRAY);
		lblPointsNeant.setForeground(Color.WHITE);
		lblPointsDePrieres = new JTextArea("Points de Prières :");
		add(lblPointsDePrieres);
		lblPointsDePrieres.setBackground(Color.DARK_GRAY);
		lblPointsDePrieres.setForeground(Color.WHITE);
		lblApocalypse = new JTextArea("Tour depuis Apocalypse :");
		add(lblApocalypse);
		lblApocalypse.setBackground(Color.DARK_GRAY);
		lblApocalypse.setForeground(Color.WHITE);
	}

	/**
	 * Peindre un élement graphique
	 * @param g l'élément à peindre
	 */
	protected void paintComponent(Graphics g) {
		if (g instanceof Graphics2D) {
			final int R = 240;
			final int G = 240;
			final int B = 240;
			Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 50), 0.0f, getHeight(), new Color(R, G, B, 50),
					true);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(p);
			g2d.fillRect(0, 0, getWidth(), getHeight());
		}
	}

	/**
	 * Permet de mettre la classe à jour quand elle est notifiée par une classe qu'elle observe
	 * @param arg1 l'obejt qui a subit une modification
	 * @param arg0 classe qui notifie
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		lblPointsJour.setText("Points Jour : " + Partie.getjoueurEnCours().getPointsJour());
		lblPointsNuit.setText("Points Nuit : " + Partie.getjoueurEnCours().getPointsNuit());
		lblPointsNeant.setText("Points Neant : " + Partie.getjoueurEnCours().getPointsNeant());
		lblPointsDePrieres.setText("Points de Prières : " + Partie.getjoueurEnCours().pointsDePriereTotal());
		lblNomDuJoueur.setText(Partie.getjoueurEnCours().getNom());
		lblCosmogonie.setText("Cosmogonie : " + Partie.getOrigineDuTour());
        lblApocalypse.setText("Tour depuis Apocalypse : "+Partie.compteurApo());
		this.revalidate();
	}

}
