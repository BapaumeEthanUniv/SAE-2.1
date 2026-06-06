package ihm;

import controleur.Controleur;
import java.awt.BorderLayout;
import javax.swing.*;

public class FrameCreation extends JFrame
{
	private Controleur ctrl;
	private JPanel[] tabPanel;

	private JPanel panelActif;
	
	public FrameCreation (Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setTitle("Création/Modification des plateaux");
		this.setSize(600, 700);
		this.setLayout(new BorderLayout());
		
		this.tabPanel	 = new JPanel[4];
		
		this.tabPanel[0] = new PanelAccueilCreation(ctrl, this, 0);
		this.tabPanel[1] = new PanelSaisieCreation (ctrl, this, 1);
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

	public void creerPanelZone()
	{
		this.tabPanel[2] = new PanelZone(this.ctrl, this, 2);
		this.tabPanel[3] = new PanelSymbole(this.ctrl, this, 3);
	}
	
	public JPanel getPnl(int i) { return this.tabPanel[i]; }
}

