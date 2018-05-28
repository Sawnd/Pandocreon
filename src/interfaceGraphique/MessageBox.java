package interfaceGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;

import elementsDeBase.Partie;
/**
 * Classe permettant d'afficher des messages à l'utilisateur, elle implemente l'interface observer
 * @author Anne-Sophie Ndoumbe
 * @author Stephane Deffon
 *
 */
public class MessageBox extends JLabel implements Observer {
	/**
	 * gives the programmer control over which versions of a class are considered incompatible in regard to serialization
	 */
	private static final long serialVersionUID = 1L;
	private static MessageBox msgBox = null;
	private JFrame frame = new JFrame();
	private TextArea text = new TextArea("", 1000, 1, Scrollbar.VERTICAL);
	private Partie partie = Partie.getPartie();
	private static String message = "La partie va commencer";
	public static MessageBox getMessageBox() {
		if (msgBox == null) {
			msgBox = new MessageBox();

		}
		return msgBox;
	}
	
	/**
	 * Create the application
	 */
	protected MessageBox() {
		frame.setTitle("Déroulement de la partie");
		text.setText(message);
text.setEditable(false);
		text.setBackground(Color.WHITE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) dim.getWidth();
		int y = (int) dim.getHeight();
		frame.setBounds(x - 300, y - 400, 300, 200);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.add(text);
		partie.addObserver(this);
 	    frame.setUndecorated(true);
		frame.setVisible(true);
	
	}
	/**
	 * Ajouter un message à la fenêtre
	 * @param str message à ajouter
	 */
	public void ajouterMessage(String str) {
		this.setMessage(str);
		this.text.setText(message);
		text.setCaretPosition(text.getText().length());

		if (!Partie.getInterfaceGaphiqueActive()) {
			System.out.println(str);
			frame.setVisible(false);
		}}
		
/**
 * modifier la valeur de l'attribut message
 * @param str valeur à affecter à l'attribut
 */
	public void setMessage(String str) {

		MessageBox.message += "\n" + str;
	}

	/**
	 * récupérer la valeur de l'attribut message
	 * @return la valeur récupérée
	 */
	public String getMessage() {
		return MessageBox.message;
	}

	/**
	 * Permet de mettre la classe à jour quand elle est notifiée par une classe qu'elle observe
	 * @param str l'obejt qui a subit une modification
	 * @param o classe qui notifie
	 */
	public void update(Observable o, Object str) {
		if (str instanceof String) {
			ajouterMessage((String) str);
		}
	}
	
}
