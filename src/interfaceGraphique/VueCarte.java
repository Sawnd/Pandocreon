package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JWindow;

import elementsDeBase.Carte;
import elementsDeBase.CarteAction;
import elementsDeBase.Croyant;
import elementsDeBase.Divinite;
import elementsDeBase.GuideSpirituel;
import elementsDeBase.Partie;
/**
 * Classe permettant l'affichage d'une carte
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 * ele implemente l'interface ActionListener
 *
 */
public class VueCarte extends JButton implements ActionListener {
	/**
	 * gives the programmer control over which versions of a class are considered incompatible in regard to serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * element permettant l'affichage de la fenetre
	 */
	private JWindow frame;
	/**
	 *  attribut contenant une carte
	 */
	private Carte carte;
/**
 * attribut contennat la vue de la pioche
 */
	private static VueCarte pioche;
	/**
	 *  attribut contenant un JLabel
	 */
	private JLabel pic;
	/**
	 * attribut contenant une image
	 */
	private Image img;
	/**
	 * attributs representants des boutons
	 */
	private JButton bouton1, bouton2, bouton3, bouton4, bouton5, bouton6, bouton7;
	/**
	 * booleen permettant de savoir s'il s'agit du deuxieme clic
	 */
	private boolean deuxiemeClic = false;
	/**
	 * booleen permettant de savoir si l'objet est la pioche
	 */
	private boolean estLaPioche = false;
	/**
	 * attribut permettant de savoir si l'objet a ete guide 
	 */
	private boolean estGuide = false;
	/**
	 * attribut permettant de savoir si l'element a ete guide
	 */
	private boolean estAuCentre = false;
	private ControleurJoueur ctrl = new ControleurJoueur(Partie.getjoueurEnCours());

	
	/**
	 * Constructeur de la classe
	 * @param vc vue de la carte
	 */
	protected VueCarte(VueCarte vc) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					VueCarte window = new VueCarte(vc.carte);
					if (vc.estAuCentre) {
						window.setEstAuCentre(true);
					}
					if (vc.estGuide) {
						window.setEstGuide(true);
					}
					window.frame.setVisible(true);
					window.afficherOptionsPossibles();
					window.deuxiemeClic = true;
					window.frame.setAlwaysOnTop(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Constructeur de la classe
	 * @param c carte e afficher
	 */
	public VueCarte(Carte c) {
		super();
		this.carte = c;
		frame = new JWindow();
		initialize();
		this.addActionListener(this);
	}
	/**
	 * Methode permettant d'afficher la vue de la pioche
	 * @return la vue de la pioche
	 */
	public static VueCarte getPioche() {
		if (pioche == null) {
			pioche = vuePioche();
		}
		return pioche;
	}

	/**
	 * Permet de gerer le vue d'une carte divinite
	 */
	public void carteDivinite() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		dimension.getWidth();
		frame.getWidth();
		int y = (int) ((dimension.getHeight() - frame.getHeight()));
		frame.setBounds(0, y, 150, 220);
        frame.setAlwaysOnTop(true);
		frame.setVisible(true);

	}

	
/**
 * Permet de recuperer l'identifiant de la carte
 * @return la valeur recuperee
 */
	public int getId() {
		return this.carte.getId();
	}

	/**
	 * Permet de modifier la valeur de l'attribut carte
	 * @param carte valeur  affecter
	 */
	public void setCarte(Carte carte) {
		this.carte = carte;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		img = null;
		//String path = "img/" + getId() + ".png";
                String path = FenetrePrincipale.imgDirPath + "\\"+getId() + ".png";
		File file = new File(path);
		try {
			img = ImageIO.read(file);

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	    frame.add(this);
		frame.setSize(150, 220);
	}

	/**
	 * Permet d'afficher la pioche
	 * @return la vue de la pioche
	 */
	private static VueCarte vuePioche() {
		VueCarte window = new VueCarte(new Divinite(0));
		window.frame.setVisible(true);
		window.estLaPioche = true;
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - window.frame.getWidth()));
		int y = (int) ((dimension.getHeight() - window.frame.getHeight()));
		window.frame.setBounds(x, y, 150, 220);
		
window.frame.setAlwaysOnTop(true);
		window.addActionListener(pioche);
		return window;
	}
/**
 * permet d'afficher un elemnet graphique
 * @param g parametre e peindre
 */
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
		g2d.setPaint(gp);
		g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		g2d.setColor(Color.black);
	}

	/**
	 * permet d recuperer la vie de la carte
	 * @return la vue recuperee
	 */
	public JLabel getVueCarte() {
		return this.pic;
	}

	/**
	 * permet d'excecuter des actions en fonction du bouton sur lequel l'utilisteur a clique
	 *
	 */
	class ClicListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == bouton2) {
				CarteAction c = (CarteAction) carte;
				frame.dispose();
				ctrl.defausserUneCarte(c);
			}
			if (source == bouton1) {
				Croyant c = (Croyant) carte;
				frame.dispose();
				ctrl.mettreCroyantAuCentre(c);
			}
			if (source == bouton3) {
				GuideSpirituel g = (GuideSpirituel) carte;
				frame.dispose();
				ctrl.guiderCroyant(g);
				FenetrePrincipale.EnableButtonFini(true);
			}
			if (source == bouton7) {
				Croyant c = (Croyant) carte;
				frame.dispose();
				ctrl.RattacherCroyant(c);
			}
			if (source == bouton5) {
				CarteAction c = (CarteAction) carte;
				frame.dispose();
				ctrl.sacrifierCarte(c);
			}
			if (source == bouton6){
				frame.dispose();
				ctrl.utiliserCapaciteDivinite();
			}
		}
	}
/**
 * Permet d'excecuter des actions en fonction de la source 
 */
	public void actionPerformed(ActionEvent e) {
		if (!estLaPioche) {
			if (deuxiemeClic) {

			} else {

				new VueCarte(this);
				if (this.carte instanceof GuideSpirituel) {
					afficherCroyantsRattaches();
				}
			}
		} else if (estLaPioche) {
			ctrl = new ControleurJoueur(Partie.getjoueurEnCours());
			ctrl.completerSamain();
		}
	}
	
	
	/**
	 * Mettre e jour la divinite
	 * @param div valeur e afecter e la divinite
	 */

	public void mettreAJourLaDivinite(Divinite div) {
		this.carte = div;
		frame.dispose();
		initialize();
		carteDivinite();
	}

	/**
	 * Permet d'afficher les differentes actions que le joueur peut effectuer sur une carte
	 */
	private void afficherOptionsPossibles() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		

		Container cont = new Container();

		cont.setLayout(new BoxLayout(cont, BoxLayout.X_AXIS));

		bouton1 = new JButton("Mettre au centre");
		Dimension d = new Dimension(100, 10);
		bouton1.setPreferredSize(d);
		bouton1.addActionListener(new ClicListener());

		bouton2 = new JButton("Defausser");
		bouton2.setMinimumSize(d);
		bouton2.addActionListener(new ClicListener());
		bouton3 = new JButton("Guider Croyants");
		bouton3.addActionListener(new ClicListener());
		bouton3.setSize(d);
		bouton4 = new JButton("Annuler");
		
		bouton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}
		});
		bouton5 = new JButton("Sacrifier");
		bouton5.addActionListener(new ClicListener());
		bouton5.setSize(d);
		bouton6 = new JButton("Utiliser la capacite");
		bouton6.addActionListener(new ClicListener());
		bouton7 = new JButton("Guider ce croyant");
		bouton7.addActionListener(new ClicListener());
		if (!(this.carte instanceof Divinite)) {
			if (this.carte instanceof Croyant) {
				if (this.estAuCentre) {
					cont.add(bouton7);
				}
				if (this.estGuide) {
					cont.add(bouton5);
				}
				if (!this.estAuCentre && !this.estGuide) {
					cont.add(bouton1);
				}
			} else if (this.carte instanceof GuideSpirituel){
				if (this.estGuide) {
					cont.add(bouton5);
				}else{
					cont.add(bouton3);
				}
			} else {
				cont.add(bouton5);
			}
			if (!this.estGuide && !this.estAuCentre) {
				cont.add(bouton2);
			}

		} else {

			cont.add(bouton6);
		}
		cont.add(bouton4);
		cont.setBackground(Color.BLACK);
		frame.setBounds(x, y, 300, 400);
		
		frame.add(cont, BorderLayout.SOUTH);

	}
/**
 * permet de modifier la valeur de l'attribut estGuide
 * @param bool valeur e affecter
 */
	public void setEstGuide(boolean bool) {
		this.estGuide = bool;
	}
/**
 * permet de modifier la valeur de l'attribut estAuCentre
 * @param bool valeur e affecter e la variable
 */
	public void setEstAuCentre(boolean bool) {
		this.estAuCentre = bool;
	}
/**
 * Permet de recuperer la valeur de m'attribut estAuCentre
 * @return la valeur recyperee
 */
	public boolean getEstAuCentre() {
		return this.estAuCentre;
	}

	/**
	 * permet de recuperer les dimensions voulues
	 * @return les dimensions
	 */
	public Dimension getPreferredSize() {
		return new Dimension(90, 140);
	}

	/**
	 * Permet d'afficher les cartes recuperees par un guide spirituel
	 */
	public void afficherCroyantsRattaches() {
		if (this.carte instanceof GuideSpirituel) {
			if (!((GuideSpirituel) carte).isEmpty()) {
				LinkedList<CarteAction> croyGuides = new LinkedList<CarteAction>();

				for (int i = 0; i < ((GuideSpirituel) carte).NB_CARTES_RATTACHEES_MAX; i++) {
					if (((GuideSpirituel) carte).getcroyantsGuides()[i] != null) {
						croyGuides.add(((GuideSpirituel) carte).getcroyantsGuides()[i]);
					}

				}
			new VueMain(croyGuides, 0);
			}
		}
	}

}
