package ihm;

import controleur.Controleur;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class FrameSaisie extends JFrame
{
	private PanelSaisie panelSaisie;
	
	public FrameSaisie (Controleur ctrl)
	{
		this.setTitle("Saisie des informations");
		this.setSize(500, 400); 
		this.setLayout(new BorderLayout());
		
		this.panelSaisie = new PanelSaisie(ctrl);
		
		this.add(this.panelSaisie, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	this.setVisible(true); 
	}
}

