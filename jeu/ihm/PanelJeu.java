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
		
		this.setLayout(new BorderLayout());
		
		/*-------------------------------*/
		/*   Création des composants     */
		/*-------------------------------*/
		
		this.ctrl 		= ctrl;
		this.frame 		= f;
		this.indice 		= indice;
		
		this.imgFond            = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");
		
		JPanel pnlHaut          = new JPanel();
		pnlHaut                 .setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlHaut                 .setOpaque(false);
		
		JPanel pnlPrincipal     = new JPanel();
		pnlPrincipal            .setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlPrincipal.setOpaque(false);
		
		JPanel pnlPlateau       = new JPanel();
		//pnlPlateau              .setLayout(new GridLayout(/* nbLigne, nbColonne, gapX, gapY */));
		pnlPlateau              .setOpaque(false);
		
		this.btnScore = new JButton("Voir les Scores >>");
		this.btnScore.setOpaque(false);
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		/*
		pnlPrincipal.add(this.btnScore);
		this.add(pnlPrincipal); */
		
		/* ----------------------------- */
		/* Activation des Composants     */
		/* ----------------------------- */
		this.btnScore.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public void actionPerformed ( ActionEvent e )
	{
		if ( e.getSource() == this.btnScore )
		{
			this.frame.setPnl(this.frame.getPnl(indice + 1));
		}
	}
	
	// Méthode permettant de changer le fond du panel par imgFond + dessiner les liens sur le plateau
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		if (this.imgFond != null) 
		{
			g.drawImage(this.imgFond, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}

