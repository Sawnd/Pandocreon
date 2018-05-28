package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import elementsDeBase.Joueur;
import elementsDeBase.JoueurPhysique;
import elementsDeBase.Partie;
/**
 * La classe FenetreChoixJoueur permet au joueur de selectioner les parametres de la partie
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *
 */
public class FenetreChoixJoueur implements Observer,ActionListener,ItemListener {
	
	/**
	 * Attribut qui contient l'unique instance de la classe
	 */
	private static FenetreChoixJoueur fenetre = null;
	
	/**
	 * attribut permettant l'affichage des elements graphiques
	 */
	private JFrame frame;
	
	/**
	 * attributs contenant des titres
	 */
	private JLabel title1, title2, title3;
	
	/**
	 * attributs contenant des containeurs
	 */
	private Container container1, container2, container3;

	/**
	 * attribut contenant un champ de texte
	 */
	private JTextField text;
	
	/**
	 * attributs representants des boutons 
	 */
	private JButton bouton1 = new JButton("Valider");
	private JButton bouton2 = new JButton("Confirmer");
	
	/**
	 * attribut contenant l'unique instance du controleur de la partie
	 */
	private ControleurPartie ctrlPartie = ControleurPartie.getControleurPartie();
	
	/**
	 * attribut contenant un separateur de fenetre
	 */
	JSeparator separateur = new JSeparator(JSeparator.VERTICAL);
	
	/**
	 * Collection contenant les joueurs reels de la partie
	 */
	private ArrayList<String> JoueursReels = new ArrayList<String>();
	
	/**
	 * collection contenant les joueurs virtuels de la partie
	 */
	private ArrayList<String> JoueursVirtuels = new ArrayList<String>();
	
	/**
	 * attribut contenant l'unique instance de la partie
	 */
	private Partie partie = Partie.getPartie();
	
	/**
	 * attributs representants des listes deroulantes
	 */
	private Choice choix = new Choice();
	private Choice choix2 = new Choice();
	private Choice choix3 = new Choice();

	/**
	 * methode permettant de recuperer l'unique instance de la classe
	 * si elle n'existe pas, elle est creee
	 * @return
	 */
	public static FenetreChoixJoueur getFenetreChoixJoueur() {
		if (fenetre == null) {
			fenetre = new FenetreChoixJoueur();
		}
		return fenetre;
	}

	/**
	 * constructeur de la classe
	 */
	private FenetreChoixJoueur() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreChoixJoueur window = new FenetreChoixJoueur(0);
					window.frame.setVisible(true);
					window.frame.requestFocusInWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * constructeur de la classe
	 * @param a permettant de le differencier du premier constructeur
	 */
	protected FenetreChoixJoueur(int a) {
	
		title1 = new JLabel("Nombre de Joueurs reels");
		title2 = new JLabel("Nombre de Joueurs virtuels");
		title3 = new JLabel("Niveau des JOUEURS virtuels");
		frame = new JFrame("Choix des Joueurs");
		container1 = frame.getContentPane();
		container2 = new Container();
		container3 = new Container();

		container1.setLayout(new BorderLayout());
		container2.setLayout(new BoxLayout(container2, BoxLayout.Y_AXIS));
		container3.setLayout(new BoxLayout(container3, BoxLayout.Y_AXIS));
		initialize();
		}
	
	
	/**
	 * Initialise le contenu de la fenetre
	 */
	
	public void initialize() {
		partie.addObserver(this);
		/*
		 * parametres de la fenetre
		 */
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setBounds(x - 200, y - 200, 400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 350);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);

		/*
		 * ajouter des options au choix
		 */
		choix.add("1");
		choix.add("2");
		choix.add("3");
		choix.add("4");
		choix.add("5");
		choix.add("6");
		choix.addItemListener(this);
		
		/*
		 * ajouter des elements e a la fenetre
		 */
		container2.add(Box.createHorizontalGlue(), BoxLayout.X_AXIS);
		container2.add(title1);
		container2.add(choix);
		container3.add(title2);
		container3.add(choix2);
		container3.add(title3);
		container1.add(container2, BorderLayout.WEST);
		container1.add(separateur, BorderLayout.CENTER);
		container1.add(container3, BorderLayout.EAST);
		container1.add(bouton1, BorderLayout.SOUTH);
		container3.add(choix3);
		bouton1.addActionListener(new NombreListener());
		
		/*
		 * ajouter des options au choix choix3
		 */
		choix3.add("Facile");
		choix3.add("Moyen");
		choix3.add("Difficile");
	
	}

	/**
	 * Methode permettant de saisir les noms des joueurs de la partie
	 * @param reel nombre de joueurs reels
	 * @param virtuel nombre de joueurs virtuels
	 */
	public void saisirNomsJoueurs(int reel, int virtuel) {
		container1.removeAll();
		container1.setLayout(new BoxLayout(container1, BoxLayout.Y_AXIS));
		frame.setSize(200, 400);
		frame.setResizable(false);
		frame.setTitle("Saisir le nom des Joueurs");
		for (int i = 0; i < (reel + virtuel) ; i++) {
			if (i < reel ) {

				text = new JTextField("Joueur reel " + (i + 1));

			} else {
				text = new JTextField("Joueur virtuel " + (i + 1));

			}
			text.setMaximumSize(new Dimension(200, 50));
			container1.add(text);
		}
		container1.add(bouton2);
		bouton2.addActionListener(new NombreListener());

	}
	
	/**
	 * Methode permettant de creer les joueurs reeels
	 * @param jr nombre de joueurs reels
	 * @return la liste de joueurs reels
	 */

	public ArrayList<String> JoueursReels(int jr) {
		Component[] tab_cont = container1.getComponents();
		ArrayList<String> tab_jr = new ArrayList<String>();
		int i = 0, k = 0;
		while (i < tab_cont.length && k < jr ) {
			if (tab_cont[i] instanceof JTextField) {
				tab_jr.add(((JTextField) tab_cont[i]).getText());
				k++;
			}
			i++;
		}
		return tab_jr;
	}

	/**
	 * Methode permettant de creer les joueurs virtuels
	 * @param jr nombre de joueurs reels
	 * @param jv nombre de joueurs virtuels
	 * @return la lsite de joueurs virtuels
	 */
	public ArrayList<String> JoueursVirtuels(int jr, int jv) {
		Component[] tab_cont = container1.getComponents();
		ArrayList<String> tab_jv = new ArrayList<String>();
		int i = 0, k = 0;
		while (i < tab_cont.length && k < (jv + jr + 1)) {
			if (tab_cont[i] instanceof JTextField) {
				if (k > jr-1) {
					tab_jv.add(((JTextField) tab_cont[i]).getText());
				}
				k++;
			}
			i++;
		}
		return tab_jv;
	}

	/**
	 * Methode permettant d'aficher les points de priere de tous les joueurs de la partie
	 */
	public void ListeJoueurs() {
		container1.removeAll();
		Iterator<String> it = JoueursReels.iterator();
		Iterator<Joueur> it2 = Partie.listeJoueurs.iterator();
		JLabel label, titre1, titre2;
		container1.setLayout(new BorderLayout());
		container2.removeAll();
		container3.removeAll();
		container2.setLayout(new BoxLayout(container2, BoxLayout.Y_AXIS));
		container3.setLayout(new BoxLayout(container3, BoxLayout.Y_AXIS));
		titre1 = new JLabel("Joueurs Reels : ");
		titre2 = new JLabel("Joueurs Virtuels : ");
		container2.add(titre1);
		container3.add(titre2);
		while (it.hasNext()) {
			String j = it.next();
			int i = 0;
			while (it2.hasNext()) {
				Joueur j1 = it2.next();
				if (j1.getNom() == j) {
					j1.pointsDePriereTotal();
					i = j1.getPointsPriere();
				}

			}
			label = new JLabel(j + " : " + i);
			container2.add(label);
		}
		it = JoueursVirtuels.iterator();
		while (it.hasNext()) {
			String j = it.next();
			int i = 0;
			while (it2.hasNext()) {
				Joueur j1 = it2.next();
				if (j1.getNom() == j) {
					j1.pointsDePriereTotal();
					i = j1.getPointsPriere();
				}

			}
			label = new JLabel(j + " : " + i);
			container3.add(label);
		}

		container1.add(container2, BorderLayout.WEST);
		container1.add(separateur);
		container1.add(container3, BorderLayout.EAST);
		frame.setTitle("Points de Prieres");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		dimension.getHeight();
		frame.getHeight();
		frame.setBounds(x + 585, 0, 200, 100);
				frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		

	}


	/**
	 * classe interne permettant d'effectuer des teches lorsqu'on clique sur un bouton
	 *
	 */
	class NombreListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			int jr, jv;
			jr = Integer.parseInt(choix.getSelectedItem());
			jv = Integer.parseInt(choix2.getSelectedItem());
			
			/*
			 * actions efectuees lorsqu'on clique sur le bouton 1
			 */
			if (source == bouton1) {
				int a = (Integer.parseInt(choix.getSelectedItem()) + Integer.parseInt(choix2.getSelectedItem()));
				if (a >= 1 && a <= 6) {
					saisirNomsJoueurs(jr, jv);
				} 
				if(jv !=0){
					 ControleurPartie.setStrategieJoueursVirtuels(choix3.getSelectedItem());
			            
			        MessageBox.getMessageBox().ajouterMessage("Les joueurs virtuels sont de niveau "+choix3.getSelectedItem());	
				}
				
           
			}
			/*
			 * actions effectuees lorsqu'on clique sur le bouton2
			 */
			if (source == bouton2) {
				JoueursReels = (JoueursReels(jr));
				JoueursVirtuels = (JoueursVirtuels(jr, jv));
				ctrlPartie.ajouterJoueur(JoueursReels, JoueursVirtuels);
				ListeJoueurs();
			}

		}
	}

	/**
	 * Methode permettant de mettre e jour la classe lorsqu'elle est notifiee par un objet qu'elle observe
	 *@param o l'observable
	 *@param l'objet d'ou vient la notification
	 */
	public void update(Observable o, Object str) {
		if (str instanceof String) {
			if (JoueursReels.contains(str) || JoueursVirtuels.contains(str)) {
				this.JoueursReels.remove(str);
				this.JoueursVirtuels.remove(str);
				MessageBox.getMessageBox().ajouterMessage("Le joueur " + str + " est elimine");
				ListeJoueurs();
			}
			
		}
		if (str instanceof JoueurPhysique) {
			frame.dispose();
			ListeJoueurs();
		}
	}

	

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		}
	
/**
 * Methode permettant d'actualiser le nombre de joueurq viruels que le joueur peut choisir en fonction du nombre de joueurs reels qu'il a selectionnes
 */
	@Override
	public void itemStateChanged(ItemEvent arg0) {
	
		choix2.removeAll();
		if(Integer.parseInt(choix.getSelectedItem())==1){
		
		for (int i=1;i<=6-Integer.parseInt(choix.getSelectedItem());i++)
		{
			choix2.add(""+i);
		}	
		}else{
		for (int i=0;i<=6-Integer.parseInt(choix.getSelectedItem());i++)
		{
			choix2.add(""+i);
		}
		
	}
}
}
