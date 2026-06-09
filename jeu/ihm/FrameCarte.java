package ihm;

import javax.swing.JFrame;

public class FrameCarte extends JFrame
{
	// private Controleur   ctrl;
	private PanelCarte panelCarte;

	public FrameCarte()
	{
		// this.ctrl = ctrl;
		this.setTitle("Carte pioché");
		this.setSize(500, 600);
		this.setLocation(10, 320);

		this.panelCarte = new PanelCarte();

		this.add(this.panelCarte);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}

	public static void main(String[] args)
	{
		new FrameCarte();
	}
}
