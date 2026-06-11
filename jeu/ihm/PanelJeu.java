package ihm;

import controleur.Controleur;

import metier.Acteur;
import metier.Role;
import metier.Zone;

import java.util.ArrayList;

import java.io.File;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
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
	private int              ligSelectionne;
	private int              colSelectionne;
	
	private Image 		 imgFond;
	
	private JPanel		 pnlPlateau;
	private JPanel           pnlCentre;
	
	private Graphics         g;
	
	private JButton		 btnScore;
	
	private Font             policeBandeau;
	
	public PanelJeu (Controleur ctrl, FrameJeu f, int indice)
	{
		
		this.setLayout(new BorderLayout());
		
		/*-------------------------------*/
		/*   Création des composants     */
		/*-------------------------------*/
		
		this.ctrl 	        = ctrl;
		this.frame 		= f;
		this.indice 		= indice;
		
		this.nbLigne            = this.ctrl.getNbLigne();
		this.nbColonne          = this.ctrl.getNbColonne();
		this.tailleCase         = 500 / Math.max(this.nbLigne, this.nbColonne);
		
		this.ligSelectionne     = -1;
		this.colSelectionne     = -1;
		
		this.imgFond            = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");
		
		this.tabPnlCases        = new JPanel[nbLigne][nbColonne];
		
		JPanel pnlHaut          = new JPanel();
		pnlHaut                 .setLayout(new BorderLayout());
		pnlHaut                 .setOpaque(false);
		
		this.pnlCentre          = new JPanel();
		this.pnlCentre          .setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
		this.pnlCentre          .setOpaque(false);
		
		JPanel pnlBas           = new JPanel();
		pnlBas                  .setLayout(new FlowLayout(FlowLayout.RIGHT , 50, 50));
		pnlBas                  .setOpaque(false);
		
		this.pnlPlateau         = new JPanel();
		pnlPlateau              .setLayout(new GridLayout(this.nbLigne, this.nbColonne, 2, 2));
		pnlPlateau              .setBackground(new Color(60, 60, 75));
		pnlPlateau              .setSize(400, 400);
		
		JPanel pnlBandeau       = new JPanel();
		pnlBandeau              .setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlBandeau              .setOpaque(false);
		
		JLabel lblMancheCoul    = new JLabel(this.ctrl.getManche().toString(), JLabel.CENTER);
		lblMancheCoul           .setForeground(this.ctrl.getManche().getCouleur());
		
		JLabel lblManche        = new JLabel("Manche : " , JLabel.CENTER);
		
		
		// Crée une ligne séparatrice à l'aide de JSeparator
		JSeparator separateur   = new JSeparator(SwingConstants.HORIZONTAL);
		separateur              .setForeground(new Color(150, 150, 150));
		separateur              .setBackground(new Color(0, 0, 0, 0));
		
		// Essai pour initialiser les polices en prenant la police téléchargée dans le dossier polices/
		try
        	{
		    File fichierTitre              = new File("./polices/TitreSaisieInfos/Shrikhand-Regular.ttf");
		    Font policeLbl                 = Font.createFont(Font.TRUETYPE_FONT, fichierTitre);   // crée la police
		    
		    java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(policeLbl);   // enregistre la police dans le système Java

		    this.policeBandeau             = policeLbl.deriveFont(Font.BOLD, 18f);                // police du bandeau 	modifié en Gras + taille 18

		    lblManche.setFont(policeBandeau);  
		    lblMancheCoul.setFont(policeBandeau); // police du bandeau changé
		}
		// Si fichier non trouvé, la police est en SansSerif Gras par défaut
		catch (Exception e)
		{
		    lblManche.setFont(new Font("SansSerif", Font.BOLD, 18));
		    lblMancheCoul.setFont(new Font("SansSerif", Font.BOLD, 18));
		}
		
		// Parcours pour dessiner le plateau
		for (int lig = 0; lig < this.nbLigne; lig++)
		{
			for (int col = 0; col < this.nbColonne; col++)
		    	{
				JPanel pnlCellule = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
				
				Zone zoneCase = this.ctrl.getTabZone()[lig][col];
				if (zoneCase != null) { pnlCellule.setBackground(zoneCase.getCouleurAwt()); } 			
				else                  { pnlCellule.setBackground(Color.WHITE); }

				JLabel lblImage = new JLabel();
				lblImage.setPreferredSize(new Dimension(this.tailleCase - 6, this.tailleCase - 6));
				lblImage.setOpaque(false); // Transparent par défaut
				
				final int finalLig = lig, finalCol= col;
				
				pnlCellule.addMouseListener(new MouseAdapter() 
				{
					public void mouseClicked(MouseEvent e)
					{
					        ligSelectionne = finalLig;
					        colSelectionne = finalCol;
					    	//System.out.println(ctrl.ajouterChemin(finalLig, finalCol));
					    
					        ligSelectionne = finalLig;
					        colSelectionne = finalCol;

					        if (ctrl.ajouterChemin(finalLig, finalCol)) {}
						       ctrl.changerCarte();

					        repaint();
					}
				});
				
				pnlCellule.add(lblImage);
				
				this.tabPnlCases[lig][col] = pnlCellule;
				pnlPlateau.add(pnlCellule);
		    	}
		}
		
		// On affiche les images de chaque acteur dans lstActeurs et leur casting si il est un acteur principal
		if (this.ctrl.getLstActeurs() != null)
		{
			for (Acteur acteur : this.ctrl.getLstActeurs()) 
		    	{
				int lig = acteur.getPosX();
				int col = acteur.getPosY();
				
				JPanel pnlCellule = this.tabPnlCases[lig][col];
				JLabel lblImage   = (JLabel) pnlCellule.getComponent(0); 
				
				ImageIcon imgRole = this.creerImgRole(acteur.getRole());
				lblImage.setIcon(imgRole);
				
				if (acteur.estPrincipal()) 
				{
				    //lblImage.setBackground(acteur.getCouleur());
				    lblImage.setOpaque(false);
				}
		    	}
		}
		
		this.btnScore = new JButton("Voir les Scores >>");
		this.btnScore.setOpaque(false);
		this.btnScore.setEnabled(false);
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		pnlBandeau.add(lblManche);
		pnlBandeau.add(lblMancheCoul);
		
		pnlHaut.add(pnlBandeau, BorderLayout.CENTER);
		pnlHaut.add(separateur, BorderLayout.SOUTH );
		
		pnlCentre.add(pnlPlateau, BorderLayout.CENTER);
		
		pnlBas.add(btnScore);
		
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
	
	// Méthode qui surcharge paint() pour dessiner après que les cases aient été posées
	public void paint(Graphics g) 
	{
		super.paint(g); 
		peindreContacts(g); 
		peindreChemin(g);
		peindreCadres(g);
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
	
	// Méthode permettant de créer les images selon le rôle de l'acteur de façon optimisé
	private ImageIcon creerImgRole(Role role)
	{
		String chemin = "";
		    
		switch (role.name()) 
		{
			case "CASCADEUR":   chemin = "./images/cascadeur.png";   break;
			case "EMOTION":     chemin = "./images/emotionnel.png";  break;
			case "ANTAGONISTE": chemin = "./images/antagoniste.png"; break;
			case "FIGURANT":    chemin = "./images/figurant.png";    break;
			default: return new ImageIcon(); 
		}
		    
		ImageIcon iconeOriginale = new ImageIcon(chemin);
		Image imgRedimensionnee = iconeOriginale.getImage().getScaledInstance(this.tailleCase - 6, this.tailleCase - 6, Image.SCALE_SMOOTH);
		return new ImageIcon(imgRedimensionnee);
	}
	
	// Méthode permettant de dessiner les contacts entre les acteurs
	protected void peindreContacts(Graphics g)
	{
		final int MARGE = this.tailleCase / 2 - 2; // pixels à laisser avant l'image
		
		int decalageX, decalageY;
		double centreX1, centreY1, centreX2, centreY2;
		
		if (this.tabPnlCases == null || this.pnlPlateau == null) return;
	    	if (this.ctrl.getLstActeurs() == null) return;

	    	Graphics2D g2 = (Graphics2D) g;
	    	g2.setStroke(new java.awt.BasicStroke(1));
	    	g2.setColor(new Color (90, 94, 107));

	    	decalageX = this.pnlCentre.getX() + this.pnlPlateau.getX();
	    	decalageY = this.pnlCentre.getY() + this.pnlPlateau.getY();

	    	for (Acteur acteur : this.ctrl.getLstActeurs()) 
	    	{
			if (acteur.getVoisins() != null) 
			{
		    		for (Acteur voisin : acteur.getVoisins()) 
		    		{
		        		JPanel case1 = tabPnlCases[acteur.getPosX()][acteur.getPosY()];
		        		JPanel case2 = tabPnlCases[voisin.getPosX()][voisin.getPosY()];

		        		centreX1 = decalageX + case1.getX() + case1.getWidth()  / 2.0;
					centreY1 = decalageY + case1.getY() + case1.getHeight() / 2.0;
					centreX2 = decalageX + case2.getX() + case2.getWidth()  / 2.0;
					centreY2 = decalageY + case2.getY() + case2.getHeight() / 2.0;

					// Vecteur entre les deux centres
					double distanceX = centreX2 - centreX1;
					double distanceY = centreY2 - centreY1;
					double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

					// Vecteur unitaire
					double ux = distanceX / distance;
					double uy = distanceY / distance;

					// On recule le début et la fin de MARGE pixels
					int x1 = (int) (centreX1 + ux * MARGE);
					int y1 = (int) (centreY1 + uy * MARGE);
					int x2 = (int) (centreX2 - ux * MARGE);
					int y2 = (int) (centreY2 - uy * MARGE);

					g2.drawLine(x1, y1, x2, y2);
		    		}
			}
	    	}
	}
	
	protected void peindreChemin(Graphics g)
	{
		if (this.tabPnlCases == null || this.pnlPlateau == null) return;
	    	if (this.ctrl.getCheminActif() == null)                  return;
	    	if (this.ctrl.getCheminActif().getChemin().size() < 2)   return;

	    	final int MARGE = this.tailleCase / 2 - 2;

	    	Graphics2D g2 = (Graphics2D) g;
	    	g2.setStroke(new java.awt.BasicStroke(4)); 

	    	int decalageX = this.pnlCentre.getX() + this.pnlPlateau.getX();
	   	int decalageY = this.pnlCentre.getY() + this.pnlPlateau.getY();

	    	g2.setColor(this.ctrl.getCheminActif().getCouleur().getCouleur());

	    	ArrayList<Acteur> chemin = this.ctrl.getCheminActif().getChemin();

	    	for (int cpt = 0; cpt < chemin.size() - 1; cpt++)
	    	{
			JPanel case1 = tabPnlCases[chemin.get(cpt)    .getPosX()][chemin.get(cpt)    .getPosY()];
			JPanel case2 = tabPnlCases[chemin.get(cpt + 1).getPosX()][chemin.get(cpt + 1).getPosY()];

			double centreX1 = decalageX + case1.getX() + case1.getWidth()  / 2.0;
			double centreY1 = decalageY + case1.getY() + case1.getHeight() / 2.0;
			double centreX2 = decalageX + case2.getX() + case2.getWidth()  / 2.0;
			double centreY2 = decalageY + case2.getY() + case2.getHeight() / 2.0;

			double distanceX = centreX2 - centreX1;
			double distanceY = centreY2 - centreY1;
			double distance  = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

			double ux = distanceX / distance;
			double uy = distanceY / distance;

			int x1 = (int) (centreX1 + ux * MARGE);
			int y1 = (int) (centreY1 + uy * MARGE);
			int x2 = (int) (centreX2 - ux * MARGE);
			int y2 = (int) (centreY2 - uy * MARGE);

			g2.drawLine(x1, y1, x2, y2);
	    	}
	}
	
	

	private void peindreCadres(Graphics g)
	{
		final int EPAISSEUR = 3;
		
		if (this.tabPnlCases == null || this.pnlPlateau == null) return;
	    	if (this.ctrl.getLstActeurs() == null) return;

	    	Graphics2D g2 = (Graphics2D) g;
	    	g2.setStroke(new java.awt.BasicStroke(EPAISSEUR));

	    	int decalageX = this.pnlCentre.getX() + this.pnlPlateau.getX();
	    	int decalageY = this.pnlCentre.getY() + this.pnlPlateau.getY();

	    	for (Acteur acteur : this.ctrl.getLstActeurs())
	    	{	
			JPanel pnlCellule = this.tabPnlCases[acteur.getPosX()][acteur.getPosY()];
				
			g2.setColor(Color.BLACK);

			int x = decalageX + pnlCellule.getX() + EPAISSEUR;
			int y = decalageY + pnlCellule.getY() + EPAISSEUR;
			int w = pnlCellule.getWidth()  - EPAISSEUR * 2;
			int h = pnlCellule.getHeight() - EPAISSEUR * 2;

			if (acteur.estPrincipal()) 
			{ 
				g2.setColor(acteur.getCouleur());
				
			}
			
			g2.drawRect(x, y, w, h);
	    	}
	}
}

