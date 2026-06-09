package ihm;

import controleur.Controleur;

import java.io.File;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

public class PanelJeu extends JPanel implements ActionListener
{
	private Controleur 	ctrl;
	private FrameJeu 	frame;
	private int		indice;
	
	private Image 		imgFond;
	
	private JButton		btnScore;
	
	public PanelJeu (Controleur ctrl, FrameJeu f, int indice)
	{
		this.ctrl 		= ctrl;
		this.frame 		= f;
		this.indice 		= indice;
	
		this.setLayout(new BorderLayout());
		
		this.imgFond      = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");
		
		JPanel pnlPrincipal = new JPanel();
		pnlPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlPrincipal.setOpaque(false);
		
		this.btnCharger = new JButton("Charger Niveau");
		this.btnCharger.setOpaque(false);
		
		pnlPrincipal.add(this.btnCharger);
		this.add(pnlPrincipal);
		
		this.btnCharger.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public void actionPerformed ( ActionEvent e )
	{
		if ( e.getSource() == this.btnCharger )
		{
			JFileChooser chooser = new JFileChooser("."); 
			
			int res = chooser.showOpenDialog(this); 
			
			if (res == JFileChooser.APPROVE_OPTION) 
			{
				File fichier = chooser.getSelectedFile();
				this.frame.setPnl(this.frame.getPnl(indice + 1));
			}
		}
	}
	
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		if (this.imgFond != null) 
		{
			g.drawImage(this.imgFond, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}

