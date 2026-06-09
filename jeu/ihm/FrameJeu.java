package ihm;

import controleur.Controleur;

import java.awt.BorderLayout;

import javax.swing.*;


public class FrameJeu extends JFrame
{
	private Controleur ctrl;
	private JPanel[] tabPanel;

	private JPanel panelActif;
	
	public FrameJeu (Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setTitle("Acte de Présence");
		this.setSize(600, 700);
		this.setLayout(new BorderLayout());
		
		this.tabPanel	 = new JPanel[4];
		
		this.tabPanel[0] = new PanelAccueilJeu(ctrl, this, 0);
		this.tabPanel[1] = new PanelJeu       (ctrl, this, 1);

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
