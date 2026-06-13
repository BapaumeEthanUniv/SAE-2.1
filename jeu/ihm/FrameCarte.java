package ihm;

import controleur.Controleur;
import javax.swing.JFrame;

public class FrameCarte extends JFrame
{
	private Controleur   ctrl;
	private PanelCarte panelCarte;

	public FrameCarte(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setTitle("Pioche");

		this.panelCarte = new PanelCarte(ctrl);

		this.add(this.panelCarte);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}

	public void changerCarte()
	{
		this.panelCarte.changerCarte();
	}

    public void finDePartie()  { this.panelCarte.finDePartie();}
}
