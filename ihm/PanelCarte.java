
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCarte extends JPanel
{
	// private Controleur     ctrl;
	private ImageIcon imgPersonnage;


	public PanelCarte()
	{
		JPanel panelAfficheCarte;
		JPanel panelAfficheScore;

		int hauteur = 500;
		int largeur = 350;
		int image;

		// this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		image = (int)(Math.random() * 10) + 1;

		panelAfficheCarte = new JPanel(new GridLayout());
		panelAfficheScore = new JPanel(new GridLayout(2,2));

		this.imgPersonnage = new ImageIcon( "../images/" + image + ".png" );

		Image imageRedimensionnee = this.imgPersonnage.getImage().getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH);

		ImageIcon iconeFinale = new ImageIcon(imageRedimensionnee);

		JLabel label = new JLabel(iconeFinale);

		panelAfficheCarte.setOpaque(false);

		panelAfficheCarte.add(label);

		panelAfficheScore.add(new JLabel("manche : "));
		panelAfficheScore.add(new JLabel("tour : "));
		panelAfficheScore.add(new JLabel("score joueur : "));

		this.add(panelAfficheCarte, BorderLayout.CENTER);
		this.add(panelAfficheScore, BorderLayout.SOUTH);
	}
}