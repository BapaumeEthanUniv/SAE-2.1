package ihm;

import controleur.Controleur;

import java.io.File;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

public class PanelAccueilJeu extends JPanel implements ActionListener
{
	private Controleur 	ctrl;
	private FrameJeu 	frame;
	private int		indice;
	
	private Image 		imgFond;
	
	private JButton		btnCharger;
	
	public PanelAccueilJeu (Controleur ctrl, FrameJeu f, int indice)
	{
		this.ctrl 		= ctrl;
		this.frame 		= f;
		this.indice 		= indice;
	
		this.setLayout(new GridLayout(10,1));
		
		this.imgFond      = Toolkit.getDefaultToolkit().getImage("./images/img-accueil-jeu.png");
		
		JPanel pnlPrincipal = new JPanel();
		pnlPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlPrincipal.setOpaque(false);
		
		this.btnCharger = new JButton("Charger Niveau");
		this.btnCharger.setOpaque(false);
		
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		pnlPrincipal.add(this.btnCharger);
		this.add(pnlPrincipal);
		
		this.btnCharger.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public void actionPerformed ( ActionEvent e )
	{
		if ( e.getSource() == this.btnCharger )
		{
			JFileChooser fcRepertoire = new JFileChooser("../creation/Plateau/"); 
			fcRepertoire.setFileSelectionMode(fcRepertoire.DIRECTORIES_ONLY);
			
			int res = fcRepertoire.showOpenDialog(this); 
			
			if (res == JFileChooser.APPROVE_OPTION) 
			{
				File fichier = fcRepertoire.getSelectedFile();
				
				this.ctrl.initPlateau(fichier);
				
				this.ctrl.creerFrameCarte();
				
				this.frame.creerPanelJeu();
				this.frame.setPnl(this.frame.getPnl(this.indice + 1));
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

