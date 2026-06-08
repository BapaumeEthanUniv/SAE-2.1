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

public class PanelAccueilCreation extends JPanel implements ActionListener
{
	private Controleur 		ctrl;		
	private FrameCreation 		frameSaisie;	
	private int		    	indice; 	// permet le changement des panels
	
	private Image 			imgFond;
	
	private JButton			btnCreer;
	
	/* -- Pas eu le temps de concevoir les parties MODIFIER et COPIER... -- */
	//private JButton		btnModifier;
	//private JButton		btnCopier;
	
	public PanelAccueilCreation (Controleur ctrl, FrameCreation f, int indice)
	{
		
		/*-------------------------------*/
		/*   Création des composants     */
		/*-------------------------------*/
		
		this.ctrl 		= ctrl;
		this.frameSaisie 	= f;
		this.indice 		= indice;
	
		this.setLayout(new GridLayout(14,1)); 	// nombre de lignes conséquent --> permet de baisser le bouton pas rapport au titre
		
		this.imgFond      = Toolkit.getDefaultToolkit().getImage("./images/img-accueil-creation.png"); // charge l'image du fond
		
		// Partie Bouton Creer
		JPanel pnlCreer 	= new JPanel();
		pnlCreer		.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlCreer		.setOpaque(false);
		
		/*
		JPanel pnlModifier 	= new JPanel();
		pnlModifier		.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlModifier		.setOpaque(false);
		
		JPanel pnlCopier 	= new JPanel();
		pnlCopier		.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlCopier		.setOpaque(false);*/
		
		this.btnCreer 		= new JButton("Créer un plateau");
		this.btnCreer		.setOpaque(false);			
		
		//this.btnModifier 	= new JButton("Modifier un plateau");
		//this.btnModifier	.setOpaque(false);
		
		//this.btnCopier 		= new JButton("Copier un plateau existant");
		//this.btnCopier		.setOpaque(false);
		
		pnlCreer.add	(this.btnCreer	 );
		//pnlModifier.add	(this.btnModifier);
		//pnlCopier.add	(this.btnCopier	 );
		
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		
		this.add(pnlCreer);
		//this.add(pnlModifier);
		//this.add(pnlCopier);
		
		this.btnCreer.addActionListener(this);
		//this.btnCopier.addActionListener(this);
		//this.btnModifier.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public void actionPerformed ( ActionEvent e )
	{
		if ( e.getSource() == this.btnCreer )
		{
			this.frameSaisie.creerPanelSaisie(this.indice + 1);
			this.frameSaisie.setPnl(this.frameSaisie.getPnl(indice + 1));
		}
		
		/*if ( e.getSource() == this.btnModifier )
		{
			JFileChooser chooser = new JFileChooser("./Plateau"); 
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			int res = chooser.showOpenDialog(this); 
			
			if (res == JFileChooser.APPROVE_OPTION) 
			{
				File fichier = chooser.getSelectedFile();
				this.frameSaisie.creerPanelZone(this.indice + 1);
				this.frameSaisie.setPnl(this.frameSaisie.getPnl(indice + 1));
			}
		}*/
		
		/*if ( e.getSource() == this.btnCopier )
		{
			JFileChooser chooser = new JFileChooser("./Plateau");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			int res = chooser.showOpenDialog(this); 
			
			if (res == JFileChooser.APPROVE_OPTION) 
			{
				File fichier = chooser.getSelectedFile();
				this.frameSaisie.setPnl(this.frameSaisie.getPnl(indice + 1));
			}
		}*/
	}
	
	// Méthode permettant de changer le fond du panel par imgFond
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		if (this.imgFond != null) 
		{
			g.drawImage(this.imgFond, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}

