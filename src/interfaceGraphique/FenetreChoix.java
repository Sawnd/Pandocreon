package interfaceGraphique;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * La classe FenetreChoix de g�rer les choix de la partie
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *
 */
public class FenetreChoix {
	/**
	 * attribut permettant l'affichage des �l�ments graphiques
	 */
	private JFrame frame;
	
	/**
	 * bool�en permettant de savoir si la fen�tre est visible
	 */
	private static boolean visible = true;
	
	/**
	 * table contennat les actions que peut effectuer un joueur � partir de cette fen�tre
	 */
	private String[] tab_string = { "Se defausser", "Completer sa main", "Mettre des Croyants au centre",
			"Consulter le Centre", "Consulter les points", "Guider des croyants", "Consulter les cartes r�cup�r�es",
			"Utiliser la capacit� de la divinit�", "Sacrifier une carte",
			"Consulter les cartes r�cup�r�es de tous les joueurs", "Afficher les points de pri�res",
			"Joueur un Deus Ex/une Apocalypse", "Quitter" };
	JButton[] tab_button = new JButton[tab_string.length];
	
	/**
	 * constructeur public de la classe
	 */
	public FenetreChoix(){
		this.setVisible(true);
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try {
					FenetreChoix fenetre=new FenetreChoix(0);
					fenetre.frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * constructeur priv� de la classe
	 * @param a permet de faire la difference avec le constructeur public
	 */
	private FenetreChoix(int a) {
		initialize();

	}

	/**
	 * Initialiser le contenu de la fen�tre
	 */
	public void initialize() {
		
		frame = new JFrame();
		JButton bouton;
		frame.setLayout(new GridLayout(7, 2));
		for (int i = 0; i < tab_string.length - 1; i++) {
			bouton = new JButton(tab_string[i]);
			bouton.addActionListener(new choixListener());
			frame.getContentPane().add(bouton);
		}
		bouton = new JButton("Quitter");
		bouton.addActionListener(new quitterListener());
		frame.getContentPane().add(bouton);
		frame.setSize(800, 400);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Que voulez vous faire?");
		
	}

	/**
	 * permet de changer la visibilit� de la fen�tre
	 * @param bool
	 */
	public void setVisible(boolean bool) {
		FenetreChoix.visible = bool;
		if (!bool) {
			frame.dispose();
		}
	}

	/**
	 * permet de savoir si la fen�re est visible ou pas
	 * @return la valeur de l'attribut visible
	 */
	public boolean getVisible() {
		return FenetreChoix.visible;
	}

	/**
	 * classe interne permettant d'effectuer des t�ches lorsqu'on clique sur un bouton
	 *
	 */
	class choixListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			((JButton) e.getSource()).getText();
		}
	}

	/**
	 * classe interne permettant de quitter une fen�tre
	 *
	 */
	class quitterListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			setVisible(false);
			frame.dispose();
						
		}
	}

}