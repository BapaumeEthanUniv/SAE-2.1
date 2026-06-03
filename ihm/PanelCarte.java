
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCarte extends JPanel
{
	// private Controleur     ctrl;
	private Image imgPersonnage;


	public PanelCarte()
	{
		JPanel panelAfficheCarte;
		JPanel panelAfficheScore;
		// this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		panelAfficheCarte = new JPanel(new GridLayout(2,1));
		panelAfficheScore = new JPanel(new GridLayout(2,2));

		panelAfficheCarte.add(new JLabel("La Carte pioché est : "));

		this.imgPersonnage = getToolkit().getImage ( "obelix.gif" );

		panelAfficheCarte.add(new JLabel(this.imgPersonnage));

		panelAfficheScore.add(new JLabel("manche : "));
		panelAfficheScore.add(new JLabel("tour : "));
		panelAfficheScore.add(new JLabel("score joueur : "));

		this.add(panelAfficheCarte, BorderLayout.CENTER);
		this.add(panelAfficheScore, BorderLayout.SOUTH);
	}

    @Override
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);

		g.drawImage( imgPersonnage, 25 ,25 , this);

		g.drawLine (   20, 470 , 370, 470 );
		g.drawLine (   20, 490 , 370, 490 );
    }
}