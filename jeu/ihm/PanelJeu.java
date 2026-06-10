package ihm;

import controleur.Controleur;

import java.io.File;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

public class PanelJeu extends JPanel implements ActionListener
{
	private final JPanel[][] tabPnlCases;
	
	private Controleur 	 ctrl;
	private FrameJeu 	 frame;
	private int		 indice;
	
	private int              nbLigne;
	private int              nbColonne;
	private int              tailleCase;
	
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
		
		this.nbLigne            = this.ctrl.getNbLigne();
		this.nbColonne          = this.ctrl.getNbColonne();
		this.tailleCase         = this.ctrl.getTailleCase();
		
		this.imgFond            = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");
		
		this.tabPnlCases        = new JPanel[nbLigne][nbColonne];
		
		JPanel pnlHaut          = new JPanel();
		pnlHaut                 .setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlHaut                 .setOpaque(false);
		
		JPanel pnlCentre        = new JPanel();
		pnlCentre               .setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
		pnlCentre               .setOpaque(false);
		
		JPanel pnlBas           = new JPanel();
		pnlBas                  .setLayout(new FlowLayout(FlowLayout.RIGHT ));
		pnlBas                  .setOpaque(false);
		
		this.pnlPlateau         = new JPanel();
		pnlPlateau              .setLayout(new GridLayout(this.nbLigne, this.nbColonne, 2, 2));
		pnlPlateau              .setBackground(new Color(60, 60, 75));
		
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
		
		for (int lig = 0; lig < this.nbLigne; lig++)
		{
		    	for (int col = 0; col < this.nbColonne; col++)
		    	{
		        	JPanel pnlCellule = new JPanel();
		        	pnlCellule.setBackground(Color.WHITE);
		        	pnlCellule.setPreferredSize(new Dimension(this.tailleCase, this.tailleCase));
		        
		        	this.tabPnlCases[lig][col] = pnlCellule;
		        	pnlPlateau.add(pnlCellule);
		        }
		}
		
		this.btnScore = new JButton("Voir les Scores >>");
		this.btnScore.setOpaque(false);
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		pnlHaut.add(lblBandeau);
		
		pnlCentre.add(pnlPlateau, BorderLayout.CENTER);
		
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
	protected void peindreContacts(Graphics g)
	{
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		
		//if (this.tabPnlCases == null || this.pnlPlateau == null) return;
		// Dessiner les lignes 
		/* for (Acteur acteur : ctrl.getLstActeurs()) 
		
			for (Acteur voisin : acteur.getVoisins()) 
			{
			    JPanel case1 = tabPnlCases[acteur.getPosX()][acteur.getPosY()];
			    JPanel case2 = tabPnlCases[voisin.getPosX()][voisin.getPosY()];
			    
			    int centreX1 = this.pnlGrille.getX() + case1.getX() + (case1.getWidth() / 2);
			    int centreY1 = this.pnlGrille.getY() + case1.getY() + (case1.getHeight() / 2);

			    int centreX2 = this.pnlGrille.getX() + case2.getX() + (case2.getWidth() / 2);
			    int centreY2 = this.pnlGrille.getY() + case2.getY() + (case2.getHeight() / 2);

			    // On trace la ligne entre les deux !
			    g2.drawLine(centreX1, centreY1, centreX2, centreY2);
			}
    		*/
		
	}
}

