package ihm;

import controleur.Controleur;

import java.awt.BorderLayout;

import javax.swing.*;

public class FrameSaisie extends JFrame
{
	private JPanel[] tabPanel;

	private JPanel panelActif;
	
	public FrameSaisie (Controleur ctrl)
	{
		this.setTitle("Saisie des informations");
		this.setSize(600, 650); 
		this.setLayout(new BorderLayout());
		
		this.tabPanel	 = new JPanel[4];
		
		this.tabPanel[0] = new PanelAccueil(ctrl, this, 0);
		this.tabPanel[1] = new PanelSaisie (ctrl, this, 1);
		this.tabPanel[2] = new PanelZone   (ctrl, this, 2);
		//this.tabPanel[2] = new PanelSymbole(this, 2);

		this.panelActif = this.tabPanel[0];
		
		this.add(this.panelActif, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	this.setVisible(true); 
	}
	
	public void setPnl(JPanel pnl)
	{
		this.remove(this.panelActif);
		this.panelActif = pnl;
		this.add(this.panelActif);
		
		this.repaint();
		this.revalidate();
	}
	
	public JPanel getPnl(int i) { return this.tabPanel[i]; }
}

