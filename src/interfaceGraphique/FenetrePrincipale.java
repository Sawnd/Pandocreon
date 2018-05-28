package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;

import elementsDeBase.CarteAction;
import elementsDeBase.CentreTable;
import elementsDeBase.Croyant;
import elementsDeBase.GuideSpirituel;
import elementsDeBase.Joueur;
import elementsDeBase.JoueurPhysique;
import elementsDeBase.JoueurVirtuel;
import elementsDeBase.Partie;
import elementsDeBase.TypeOrigine;

/**
 * Classe permettant d'afficher le plateau de jeu
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *elle herite de la classe JFrame et implemente les interfaces Observer et ActionListener
 */
public class FenetrePrincipale extends JFrame implements ActionListener, Observer {
	/**
	 * gives the programmer control over which versions of a class are considered incompatible in regard to serialization
	 */
	private static final long serialVersionUID = 1L;
        public static  String imgDirPath="src\\images" ;
/**
 * attribut contennat un JFrame
 */
	private JFrame  frame2 = null;

	/**
	 * attribut contennat un JWindow
	 */
	private JWindow frame;
	/**
	 * attribut contenant un JLayeredPane
	 */
	private JLayeredPane pane;
	/**
	 * attributs contennat des JPanel
	 */
	private JPanel pane2, pane3, pane4;
	
	/**
	 * attribut contennat l'unique instance de la classe
	 */
	private static FenetrePrincipale fenetre = null;
	/**
	 * attributs contenant des boutins
	 */
	private JButton button1, button2, button3, button4, button5;
	private static JButton button6;
	/**
	 * attribut contenant l'unique instance de la partie
	 */
	private Partie partie = Partie.getPartie();
	
	/**
	 * attributs contennat les differentes vues
	 */
	VueMain vm = null;
	VueCarte divinite = null;
	VueCentreTable vueCentre = null;
	VueCartesRecuperees vcr = null;

	/**
	 * constructeur de la classe
	 */
	private FenetrePrincipale() {
		initialize();
		partie.addObserver(this);

	}
	
	
	/**
	 * methode prmettant de recuperer l'unique instance de la classe
	 * @return l'instance recuperee
	 */

	public static FenetrePrincipale getFenetrePrincipale() {
		if (fenetre == null) {
			JWindow window = new JWindow();
			JPanel pane = new JPanel();
			window.setContentPane(pane);
			window.setSize(1400, 728);
			ImageIcon img = null;
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) (dimension.getWidth());
			int y = (int) (dimension.getHeight());
			try {
				img = new ImageIcon(
						ImageIO.read(new File(imgDirPath+"\\fond3.jpg")).getScaledInstance(x, y, Image.SCALE_SMOOTH));
				JLabel pic = new JLabel(img);
				pane.add(pic);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			window.setVisible(true);

			fenetre = new FenetrePrincipale();
		}
		return fenetre;

	}

	/**
	 * Permet d'initialiser la fenetre
	 */
	public void initialize() {
		
		frame = new JWindow();
		frame.setLayout(new BorderLayout());
		frame.setAlwaysOnTop(true);
		pane = new JLayeredPane();
		frame.getContentPane().setBackground(new Color(240, 230, 140));
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		dimension.getHeight();
		frame.getHeight();
		frame.setBounds(x - (1024 / 2), 0, 1008, 503);
		JTextField pandocreon = new JTextField();
		pandocreon.setFont(new Font("Footlight MT Light", Font.BOLD, 34));
		pandocreon.setBackground(new Color(240, 230, 140));
		pandocreon.setText("PANDOCREON DIVINAE");
		pandocreon.setBounds(frame.getWidth() / 2 - 400 / 2, frame.getHeight() / 2 - 200, 392, 63);
		JLabel label = new JLabel();
		Image img = null;
		//String path = "img/fond.jpg"; 
                String path = FenetrePrincipale.imgDirPath + "\\fond.png";
		File file = new File(path);
		try {
			img = ImageIO.read(file);

		} catch (IOException e) {
			
			e.printStackTrace();
		}
		label.setIcon(new ImageIcon(img));
		label.setBounds(162, 161, 217, 97);
		frame.getContentPane().add(label);
		pandocreon.setColumns(10);
		frame.getContentPane().add(pandocreon);
		button1 = new JButton("Nouvelle Partie");
		button2 = new JButton("Commencer");
		button3 = new JButton("Quitter");
		button4 = new JButton("Regles du Jeu");
		pane.setBackground(Color.BLACK);
		// pane.setVisible(true);
		button1.setBounds(461, 192, 150, 22);
		button2.setBounds(461, 220, 150, 22);
		button3.setBounds(461, 248, 150, 22);
		button4.setBounds(461, 274, 150, 22);
		frame.add(pane, BorderLayout.CENTER);
		pane.add(button1);
		pane.add(button2);
		pane.add(button4);
		pane.add(button3);
		button2.setEnabled(false);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		frame.setVisible(true);

	}

	/**
	 * Methode permettant d'executer des actions en fonction du bouton sur lequel l'utilisateur a clique
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == button1) {

			MessageBox.getMessageBox().ajouterMessage("Nouvelle Partie");
			FenetreChoixJoueur.getFenetreChoixJoueur();
			button1.setEnabled(false);
			button2.setEnabled(true);
		}
		if (source == button2 || source == button5) {
			VueCarte.getPioche();

			ControleurPartie.getControleurPartie().passerAuTourSuivant();
			if (source == button2) {
				initialize3();
			}

		}

	}

	/**
	 * permet d'initialiser la fenetre
	 */
	private void initialize3() {
		frame.setBackground(new Color(0, 0, 0, 0));
		frame.getContentPane().removeAll();
		pane.removeAll();
		frame.setLayout(new BorderLayout());
		button5 = new JButton("Fin du tour");
		button5.addActionListener(this);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth()));
		int y = (int) ((dimension.getHeight()));

		pane2 = new JPanel() {
			
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				if (g instanceof Graphics2D) {
					final int R = 240;
					final int G = 240;
					final int B = 240;
					Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 150), 0.0f, getHeight(),
							new Color(R, G, B, 0), true);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setPaint(p);
					g2d.fillRect(0, 0, getWidth(), getHeight());
				}
			}
		};
		pane2.setLayout(new BorderLayout());
		frame.setSize(pane2.getPreferredSize());
		frame.setBounds(150, 0, x - 300, y);
		
		frame.add(pane2, BorderLayout.NORTH);
	
		pane2.add(new InformationsJoueur(), BorderLayout.NORTH);
		pane2.revalidate();
		pane2.repaint();
		frame.revalidate();
		frame.repaint();
		button6 = new JButton("Fini");
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControleurJoueur.guiderCroyantTermine();

			}
		});
		
		JPanel listeButtons = new JPanel() {
			
			private static final long serialVersionUID = 1L;

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
		};
		listeButtons.setLayout(new BoxLayout(listeButtons, BoxLayout.X_AXIS));
		listeButtons.add(button5);
		listeButtons.add(button6);
		listeButtons.add(button3);
		
		pane2.add(listeButtons, BorderLayout.SOUTH);
		button6.setEnabled(false);
		pane2.revalidate();
		
	}

	
	/**
	 * permet d'initialiser la fenetre
	 */
	public void initialize2() {
		frame2 = new JFrame();
		frame2.setLayout(new BorderLayout());
		pane2 = new JPanel();
		
		pane2.setBackground(Color.BLACK);
		pane2.setLayout(new BorderLayout());
		frame2.getContentPane().add(pane2, BorderLayout.CENTER);
		frame2.setVisible(true);

	}

	/**
	 * Methode permettant d'actualiser la classe en fonction de l'objet modifie et de l'observable
	 * @param o l'objet observe
	 * @param args objet dont la valeur a change
	 */
	public void update(Observable o, Object args) {

		if (args instanceof JoueurPhysique) {
			if (vm != null) {
				divinite.mettreAJourLaDivinite(((JoueurPhysique) args).getDivinite());
				vm.MettreAjourLaMain(((JoueurPhysique) args).getmain());
				pane3.removeAll();
				frame.remove(pane3);

			} else {
				divinite = new VueCarte(((JoueurPhysique) args).getDivinite());
				divinite.carteDivinite();

			}
			if (vcr != null) {
				vcr.MettreAjourLesCartesRec(((JoueurPhysique) args).getcartesRecuperees());
			} else {
				vcr = new VueCartesRecuperees(((JoueurPhysique) args).getcartesRecuperees());
			}
			vm = new VueMain(((JoueurPhysique) args).getmain());
			pane3 = new JPanel() {
				
				private static final long serialVersionUID = 1L;

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
			};

			

			pane3.add(vm);

			frame.add(pane3, BorderLayout.SOUTH);
			frame.revalidate();
			frame.repaint();
			
		}
		if (args instanceof JoueurVirtuel) {
			if (pane3 != null) {
				pane3.removeAll();
			}
			frame.revalidate();
			frame.repaint();
		}
		if (args instanceof CentreTable) {
			if (vueCentre != null) {
				vueCentre.mettreAJourLeCentreDeTable((CentreTable) args);
				pane4.removeAll();
				frame.remove(pane4);
			}
			vueCentre = new VueCentreTable((CentreTable) args);
			pane4 = new JPanel() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

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
			};
			pane4.add(vueCentre);

			frame.add(pane4, BorderLayout.CENTER);
			frame.revalidate();
			frame.repaint();

		}

	}

	/**
	 * Permettre ou non l'utilisation d bouton 6
	 * @param bool booleen permettant de permetre ou pas l'utilisation du bouton
	 */
	public static void EnableButtonFini(boolean bool) {
		button6.setEnabled(bool);
	}
	
	/**
	 * Methode permettant de choisir un joueur
	 * @return joueur choisi
	 */

	public static Joueur choisirUnJoueur() {
		Iterator<Joueur> it = Partie.listeJoueurs.iterator();
		Joueur[] options = new Joueur[Partie.listeJoueurs.size()];
		int i = 0;
		while (it.hasNext()) {
			Joueur j1 = it.next();
			if (j1 != Partie.getjoueurEnCours()) {
				options[i] = j1;
			}
		}
		Joueur j = (Joueur) JOptionPane.showInputDialog(null, "Sur quel Joueur voulez vous utiliser votre capacite?",
				"Veuillez choisir un Joueur", JOptionPane.PLAIN_MESSAGE, null, options, Partie.listeJoueurs.get(i));
		MessageBox.getMessageBox().ajouterMessage("Vous avez choisi " + j);

		return j;
	}

	/**
	 * Methode permettant de choisir une cosmogonie
	 * @return cosmogonie choisie
	 */
	public static TypeOrigine choisirUneCosmogonie() {
		TypeOrigine[] options = { TypeOrigine.JOUR, TypeOrigine.NEANT, TypeOrigine.NUIT };
		TypeOrigine origine = (TypeOrigine) JOptionPane.showInputDialog(null, "Quel cosmogonie choisissez vous?",
				"Veuillez choisir une cosmogonie", JOptionPane.PLAIN_MESSAGE, null, options, TypeOrigine.JOUR);
		return origine;
	}

	/**
	 * Methode permettant de choisir un guide spirituel
	 * @param liste collection dans laquelle choisir le guide
	 * @return guide spirituel choisi
	 */
	public static GuideSpirituel choisirUnGuideSpirituel(LinkedList<CarteAction> liste) {
		Iterator<CarteAction> it = liste.iterator();
		String[] options = new String[liste.size()];
		int i = 0;
		while (it.hasNext()) {
			CarteAction carte = it.next();
			if (carte instanceof GuideSpirituel) {
				options[i] = "" + i;
				MessageBox.getMessageBox().ajouterMessage(carte.toString() + "[" + i + "]");
			}
		}
		String str = (String) JOptionPane.showInputDialog(null, "Quel guide spirituel choisissez vous?",
				"Veuillez choisir un guide spirituel", JOptionPane.PLAIN_MESSAGE, null, options, liste.get(i));
		CarteAction g = liste.get(Integer.parseInt(options[Integer.parseInt(str)]));
		MessageBox.getMessageBox().ajouterMessage("Vous avez choisi " + g);
		return (GuideSpirituel) g;

	}
/**
 * Methode permettant de choisir un croyant
 * @param liste collection dans laquelle l faut choisir le croyant
 * @return le croyant choisi
 */
	public static Croyant choisirCroyant(LinkedList<CarteAction> liste) {
		Iterator<CarteAction> it = liste.iterator();
		String[] options = new String[liste.size()];
		int i = 0;
		while (it.hasNext()) {
			CarteAction carte = it.next();
			if (carte instanceof Croyant) {
				options[i] = "" + i;
			}
		}
		String str = (String) JOptionPane.showInputDialog(null, "Quel guide spirituel choisissez vous?",
				"Veuillez choisir un guide spirituel", JOptionPane.PLAIN_MESSAGE, null, options, liste.get(i));
		CarteAction c = liste.get(Integer.parseInt(options[Integer.parseInt(str)]));
		MessageBox.getMessageBox().ajouterMessage("Vous avez choisi " + c);
		return (Croyant) c;

	}

	/**
	 * Terminer la partie
	 * @param j joueur ayant remporte la partie
	 */
	public static void finirLaPartie(Joueur j) {
		if (fenetre!=null){
			fenetre.frame.dispose();
		}
		JDialog fenetre = new JDialog();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (dimension.getWidth());
		int y = (int) (dimension.getHeight());
		fenetre.setSize(x, y);

		ImageIcon img = null;
		JPanel pane = new JPanel();
		JLabel label = new JLabel(j + " a gagne!");
		JPanel pane2= new JPanel();
		pane2.setLayout(new BoxLayout(pane2,BoxLayout.X_AXIS));
		pane2.add(label);
		try {
			img = new ImageIcon(ImageIO.read(new File("img/fin.png")).getScaledInstance(x, y-100, Image.SCALE_SMOOTH));
			JLabel pic = new JLabel(img);
			pane.add(pic);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JButton btnQuitter = new JButton("Quitter");
		pane2.add(btnQuitter);
		pane.add(pane2,BorderLayout.NORTH);
		
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fenetre.setContentPane(pane);
		fenetre.setModal(true);
		fenetre.setVisible(true);
		fenetre.setAlwaysOnTop(true);

	}

	
}