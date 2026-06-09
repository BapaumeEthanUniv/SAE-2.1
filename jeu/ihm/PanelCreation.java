package ihm;

import controleur.Controleur;
import javax.swing.*;

public class PanelCreation extends JPanel //implements ActionListener
{
	private Controleur 	ctrl;
	private FrameCreation 	frame;
	private int		indice;
	
	public PanelCreation(Controleur ctrl, FrameCreation f, int indice)
	{
		this.ctrl 		= ctrl;
		this.frame 	= f;
		this.indice 		= indice;
	}
}

