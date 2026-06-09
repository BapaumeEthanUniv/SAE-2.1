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
		this.setTitle("Carte pioché");
		this.setSize(500, 700);
		this.setLocation(10, 320);

		this.panelCarte = new PanelCarte();

		this.add(this.panelCarte);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
}
