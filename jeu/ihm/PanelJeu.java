package ihm;

import controleur.Controleur;

import java.io.File;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

public class PanelJeu extends JPanel implements ActionListener
{
	//private final JPanel[][] tabPnlCases;
	
	private Controleur 	 ctrl;
	private FrameJeu 	 frame;
	private int		 indice;
	
	private int              nbLigne;
	private int              nbColonne;
	
	private Image 		 imgFond;
	
	private JPanel		 pnlPlateau;
	
	private JButton		 btnScore;
	
	private Font             policeBandeau;
	
	public PanelJeu (Controleur ctrl, FrameJeu f, int indice)
	{
		
		this.setLayout(new BorderLayout());
		
		/*-------------------------------*/
		/*   Création des composants     */
		/*-------------------------------*/
		
		this.ctrl 		= ctrl;
		this.frame 		= f;
		this.indice 		= indice;
		
		//this.nbLigne            = this.ctrl.getNbLigne();
		//this.nbColonne          = this.ctrl.getNbColonne();
		
		this.imgFond            = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");
		
		JPanel pnlHaut          = new JPanel();
		pnlHaut                 .setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlHaut                 .setOpaque(false);
		
		JPanel pnlCentre        = new JPanel();
		pnlCentre               .setLayout(new BorderLayout(0, 30));
		pnlCentre               .setOpaque(false);
		
		JPanel pnlBas           = new JPanel();
		pnlBas                  .setLayout(new FlowLayout(FlowLayout.RIGHT ));
		pnlBas                  .setOpaque(false);
		
		this.pnlPlateau         = new JPanel();
		//pnlPlateau              .setLayout(new GridLayout(/* nbLigne, nbColonne, gapX, gapY */));
		pnlPlateau              .setOpaque(false);
		
		
		
		
		JLabel lblBandeau       = new JLabel("Bandeau", JLabel.CENTER);
		JLabel lblPlateau       = new JLabel("Plateau", JLabel.CENTER);
		JLabel lblBouton        = new JLabel("Bouton ", JLabel.RIGHT );
		
		// Essai pour initialiser les polices en prenant la police téléchargée dans le dossier polices/
		try
        	{
		    File fichierTitre              = new File("./polices/TitreSaisieInfos/Shrikhand-Regular.ttf");
		    Font policeLbl                 = Font.createFont(Font.TRUETYPE_FONT, fichierTitre);   // crée la police
		    
		    java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(policeLbl);   // enregistre la police dans le système Java

		    this.policeBandeau            =  policeLbl.deriveFont(Font.BOLD, 18f);                // police du bandeau 	modifié en Gras + taille 18

		    lblBandeau.setFont(policeBandeau);                                                    // police du bandeau changé
		}
		// Si fichier non trouvé, la police est en SansSerif Gras par défaut
		catch (Exception e)
		{
		    lblBandeau.setFont(new Font("SansSerif", Font.BOLD, 18));
		    lblPlateau.setFont(new Font("SansSerif", Font.BOLD, 18));
		    lblBouton .setFont(new Font("SansSerif", Font.BOLD, 18));
		}
		
		this.btnScore = new JButton("Voir les Scores >>");
		this.btnScore.setOpaque(false);
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		pnlHaut.add(lblBandeau);
		
		pnlCentre.add(lblPlateau, BorderLayout.CENTER);
		
		pnlBas.add(lblBouton);
		
		this.add(pnlHaut     , BorderLayout.NORTH);
		this.add(pnlCentre   , BorderLayout.CENTER);
		this.add(pnlBas      , BorderLayout.SOUTH); 
		
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
	
	// Méthode permettant de changer le fond du panel par imgFond
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		if (this.imgFond != null) 
		{
			g.drawImage(this.imgFond, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
	
	// Méthode permettant de dessiner les contacts entre les acteurs
	protected void paint        (Graphics2D g2)
	{
		super.paint(g2);
		
		//if (this.tabPnlCases == null || this.pnlPlateau == null) return;
		// Dessiner les lignes 
		
	}
}

