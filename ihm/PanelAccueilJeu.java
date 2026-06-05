package ihm;

import controleur.Controleur;

import metier.CreateurPlateau;
import metier.Casting;
import metier.Role;


import java.io.File;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.*;

import javax.swing.*;

public class PanelAccueilJeu extends JPanel implements ActionListener
{
	private Controleur 	ctrl;
	private FrameSaisie 	frameSaisie;
	private int		indice;
	
	private Image 		imgFond;
	
	private JButton		btnCharger;
	
	public PanelAccueilJeu (Controleur ctrl, FrameSaisie f, int indice)
	{
		this.ctrl 		= ctrl;
		this.frameSaisie 	= f;
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
			JFileChooser chooser = new JFileChooser("."); 
			
			int res = chooser.showOpenDialog(this); 
			
			if (res == JFileChooser.APPROVE_OPTION) 
			{
				File fichier = chooser.getSelectedFile();
				this.frameSaisie.setPnl(this.frameSaisie.getPnl(indice + 1));
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		if (this.imgFond != null) 
		{
			g.drawImage(this.imgFond, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}

