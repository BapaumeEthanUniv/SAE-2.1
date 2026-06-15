package ihm;

import controleur.Controleur;

import metier.Acteur;
import metier.Role;
import metier.Zone;
import metier.Chemin;

import java.io.File;

import java.util.ArrayList;

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
	private final JPanel[][] tabPnlCases;      // Permet de stocker les cases du plateau sous forme de JPanel
	
	private Controleur 	     ctrl;
	private FrameJeu 	     frame;
	private int		         indice;           // Permet de savoir l'indice dans lequel le Panel est rangé dans le tableau de JPanel de FrameJeu
	
	private int              nbLigne;          // Permet de stocker le nombre de lignes   du plateau
	private int              nbColonne;        // Permet de stocker le nombre de colonnes du plateau
	private int              tailleCase;       // Permet de stocker la taille des cases   du plateau

    private int              ligSelectionne;   // Permet de stocker la ligne   sélectionnée lors du jeu
    private int              colSelectionne;   // Permet de stocker la colonne sélectionnée lors du jeu
	
	private Image 		     imgFond;          // Permet de stocker une img de fond pour l'appliquer au panel
	
	private JPanel		     pnlPlateau;
	private JPanel           pnlCentre;

    private JLabel           lblManche;
    private JLabel           lblMancheCoul;
	
	private JButton		     btnScore;
	
	private Font             police;           // Permet d'appliquer une police spéciale aux JLabel
	
	public PanelJeu (Controleur ctrl, FrameJeu f, int indice)
	{
		
		this.setLayout(new BorderLayout());

        this.ctrl 	            = ctrl;
        this.frame 		        = f;
        this.indice 		    = indice;

        this.nbLigne            = this.ctrl.getNbLigne();
        this.nbColonne          = this.ctrl.getNbColonne();

        Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();          // Permet d'ajuster
        int espaceDispo = (int) (ecran.getHeight() * 0.55);                     // la taille des cases
                                                                                // par rapport à
        this.tailleCase = espaceDispo / Math.max(this.nbLigne, this.nbColonne); // la taille de l'écran

        this.ligSelectionne = -1;
        this.colSelectionne = -1;

        this.imgFond            = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");
		
		/*-------------------------------*/
		/*   Création des composants     */
		/*-------------------------------*/

        this.tabPnlCases        = new JPanel[nbLigne][nbColonne];

        // Panel qui contiendra pnlBandeau
		JPanel pnlHaut          = new JPanel();
		pnlHaut                 .setLayout(new BorderLayout());
		pnlHaut                 .setOpaque(false);

        // Panel qui contiendra pnlPlateau
		this.pnlCentre          = new JPanel();
		this.pnlCentre          .setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
		this.pnlCentre          .setOpaque(false);

        // Panel qui contiendra btnScore
		JPanel pnlBas           = new JPanel();
		pnlBas                  .setLayout(new FlowLayout(FlowLayout.RIGHT , 20, 20));
		pnlBas                  .setOpaque(false);

        // Panel qui affichera le Plateau
		this.pnlPlateau         = new JPanel();
		pnlPlateau              .setLayout(new GridLayout(this.nbLigne, this.nbColonne, 2, 2));
		pnlPlateau              .setBackground(new Color(60, 60, 75));
		pnlPlateau              .setSize(400, 400);

        // Panel qui contiendra les JLabel
		JPanel pnlBandeau       = new JPanel();
		pnlBandeau              .setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlBandeau              .setOpaque(false);

        this.lblManche          = new JLabel("Casting : " , JLabel.CENTER);

		this.lblMancheCoul      = new JLabel  (ctrl.getManche().toString(), JLabel.CENTER);                  // JLabel contenant le Casting actif
		this.lblMancheCoul      .setForeground(ctrl.getManche().getCouleur());                               // On change la couleur du JLabel selon le Casting

		// Crée une ligne séparatrice à l'aide de JSeparator
		JSeparator separateur   = new JSeparator(SwingConstants.HORIZONTAL);
		separateur              .setForeground(new Color(150, 150, 150));
		separateur              .setBackground(new Color(0, 0, 0, 0));
		
		// Essai pour initialiser les polices en prenant la police téléchargée dans le dossier polices/
		try
        {
		    File fichierTitre  = new File("./polices/TitreSaisieInfos/Shrikhand-Regular.ttf");
		    Font policeLbl     = Font.createFont(Font.TRUETYPE_FONT, fichierTitre);   // crée la police
		    
		    java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(policeLbl);   // enregistre la police dans le système Java

		    this.police        = policeLbl.deriveFont(Font.BOLD, 18f);                // police du bandeau 	modifié en Gras + taille 18

		    this.lblManche.setFont(police);
		    this.lblMancheCoul.setFont(police); // police du bandeau changé
		}
		// Si fichier non trouvé, la police est en SansSerif Gras par défaut
		catch (Exception e)
		{
		    this.lblManche.setFont(new Font("SansSerif", Font.BOLD, 18));
		    this.lblMancheCoul.setFont(new Font("SansSerif", Font.BOLD, 18));
		}
		
		// Parcours pour dessiner le plateau
		for (int lig = 0; lig < this.nbLigne; lig++)
		{
			for (int col = 0; col < this.nbColonne; col++)
            {
				JPanel pnlCellule = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
                pnlCellule        .setPreferredSize(new Dimension(this.tailleCase, this.tailleCase));                    // On donne une taille au pnlCellule avec l'attribut tailleCase

				Zone zoneCase     = this.ctrl.getTabZone()[lig][col];                                                // On récupère la Zone du tableau de Zone du métier au coordonées lig col
				if (zoneCase != null) { pnlCellule.setBackground(zoneCase.getCouleurAwt()); } 			         // Si la zone est coloriée, le pnlCellule prend la couleur de la Zone
				else                  { pnlCellule.setBackground(Color.WHITE); }                                 // Sinon, le pnlCellule prend la couleur par défaut (blanc)

				JLabel lblImage   = new JLabel();
				lblImage          .setPreferredSize(new Dimension(this.tailleCase - 6, this.tailleCase - 6)); // On ajuste la taille du lblImage en fonction de l'attribut tailleCase et ajustement personnel
				lblImage          .setOpaque(false);                                                                       // Transparent par défaut

				pnlCellule        .add(lblImage);
				
				this.tabPnlCases[lig][col] = pnlCellule;                                                         // On ajoute pnlCellule au tableau de JPanel
				pnlPlateau        .add(pnlCellule);                                                                      // Le panel qui affichera le plateau ajoute le pnlCellule

                pnlCellule        .addMouseListener(new GereSouris(lig, col));
            }
		}
		
		// On affiche les images de chaque acteur dans lstActeurs et leur casting s'il est un acteur principal
		if (this.ctrl.getLstActeurs() != null)
		{
			for (Acteur acteur : this.ctrl.getLstActeurs()) 
            {
                    int lig = acteur.getPosX();
                    int col = acteur.getPosY();

                    JPanel pnlCellule = this.tabPnlCases[lig][col];              // On récupère le JPanel dans le tableau de JPanel
                    JLabel lblImage   = (JLabel) pnlCellule.getComponent(0);     // On récupère le JLabel contenant l'image dans pnlCellule

                    ImageIcon imgRole = this.creerImgRole(acteur.getRole());     // On stocke l'image liée au Role de l'acteur grâce à la méthode creerImgRole()
                    lblImage.setIcon(imgRole);                                   // On change l'image par celle stockée au-dessus

                    // Si l'acteur est un acteur principal, on le rend transparent
                    if (acteur.estPrincipal())
                    {
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
		
		pnlHaut.add(pnlBandeau  , BorderLayout.CENTER);
		pnlHaut.add(separateur  , BorderLayout.SOUTH );
		
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
            this.ctrl .cacherFrameCarte();
            this.frame.creerPanelScore();
            this.frame.setPnl(this.frame.getPnl(this.indice + 1));
		}
	}

    /* --------------------------*/
    /*    Méthodes utilitaires   */
    /* --------------------------*/

    // Méthode permettant d'afficher le message de fin de partie et de déverrouiller le bouton des scores
    public void finDePartie()
    {
        this.lblManche    .setText("Fin de la Partie !");

        this.lblMancheCoul.setText("");
        this.btnScore     .setEnabled(true);
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
		Image imgRedimensionnee  = iconeOriginale.getImage().getScaledInstance(this.tailleCase - 6, this.tailleCase - 6, Image.SCALE_SMOOTH);
		return new ImageIcon(imgRedimensionnee);
	}
	
	// Méthode permettant de dessiner les contacts entre les acteurs
	protected void peindreContacts(Graphics g)
	{
		final int MARGE = this.tailleCase / 2 - 2;  // Pixels à laisser avant l'image
		
		int decalageX, decalageY;
		double centreX1, centreY1, centreX2, centreY2;

        double distance, distanceX, distanceY;
        double uX, uY;
        int x1, x2, y1, y2;
		
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
                    distanceX = centreX2 - centreX1;
                    distanceY = centreY2 - centreY1;
                    distance  = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

                    // Vecteur unitaire
                    uX = distanceX / distance;
                    uY = distanceY / distance;

                    // On recule le début et la fin de MARGE pixels
                    x1 = (int) (centreX1 + uX * MARGE);
                    y1 = (int) (centreY1 + uY * MARGE);
                    x2 = (int) (centreX2 - uX * MARGE);
                    y2 = (int) (centreY2 - uY * MARGE);

                    g2.drawLine(x1, y1, x2, y2);
                }
            }
	    }
	}

    // Méthode permettant de dessiner le chemin des différents castings au fur et à mesure
	protected void peindreChemin(Graphics g)
	{
		final int MARGE = this.tailleCase / 2 - 2;   // Pixels à laisser avant l'image

        int decalageX, decalageY;
        double centreX1, centreY1, centreX2, centreY2;

        double distance, distanceX, distanceY;
        double uX, uY;
        int x1, x2, y1, y2;
		
		Graphics2D g2;

		
		if (this.tabPnlCases == null || this.pnlPlateau == null) return;
        if (this.ctrl.getTabChemin() == null)                    return;
        if (this.ctrl.getTabChemin().length < 2)                 return;

        g2 = (Graphics2D) g;
        g2.setStroke(new java.awt.BasicStroke(4));

        decalageX = this.pnlCentre.getX() + this.pnlPlateau.getX();
        decalageY = this.pnlCentre.getY() + this.pnlPlateau.getY();

        Chemin[] tabChemin = this.ctrl.getTabChemin();

        for (int cptChemin = 0; cptChemin < tabChemin.length; cptChemin++)
        {
            if (tabChemin[cptChemin] != null)
            {
                g2.setColor(tabChemin[cptChemin].getCouleur().getCouleur());
                ArrayList<Acteur> chemin = tabChemin[cptChemin].getChemin();
		    		
                for (int cpt = 0; cpt < chemin.size() - 1; cpt++)
                {
					JPanel case1 = tabPnlCases[chemin.get(cpt)    .getPosX()][chemin.get(cpt)    .getPosY()];
					JPanel case2 = tabPnlCases[chemin.get(cpt + 1).getPosX()][chemin.get(cpt + 1).getPosY()];

					centreX1 = decalageX + case1.getX() + case1.getWidth()  / 2.0;
					centreY1 = decalageY + case1.getY() + case1.getHeight() / 2.0;
					centreX2 = decalageX + case2.getX() + case2.getWidth()  / 2.0;
                    centreY2 = decalageY + case2.getY() + case2.getHeight() / 2.0;

					distanceX = centreX2 - centreX1;
					distanceY = centreY2 - centreY1;
					distance  = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

					uX = distanceX / distance;
					uY = distanceY / distance;

					x1 = (int) (centreX1 + uX * MARGE);
					y1 = (int) (centreY1 + uY * MARGE);
					x2 = (int) (centreX2 - uX * MARGE);
					y2 = (int) (centreY2 - uY * MARGE);

					g2.drawLine(x1, y1, x2, y2);
                }
            }
        }
	}

    // Méthode permettant de peindre les cadres de tous les acteurs (noir ou de couleur du casting)
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

    // Méthode permettant de mettre à jour le JLabel hébergeant le Casting actif tout au long de la partie
	public void majLblCasting()
	{
        if (this.btnScore.isEnabled()) return;  // Si le bouton est déverrouillé, on ne change le texte

        this.lblMancheCoul.setText      (ctrl.getManche().toString()  );
		this.lblMancheCoul.setForeground(ctrl.getManche().getCouleur());
	}


    // Classe interne permettant de gérer le clic de la souris pendant le Jeu
    private class GereSouris extends MouseAdapter
    {
        private int ligMouse;
        private int colMouse;

        public GereSouris(int lig, int col)
        {
            this.ligMouse = lig;
            this.colMouse = col;
        }

        public void mouseClicked(MouseEvent e)
        {
            PanelJeu.this.ligSelectionne = this.ligMouse;
            PanelJeu.this.colSelectionne = this.colMouse;

            if (PanelJeu.this.ctrl.ajouterChemin(this.ligMouse, this.colMouse))
            {
                PanelJeu.this.ctrl.changerCarte();
                PanelJeu.this.ctrl.majLblCasting();
            }

            PanelJeu.this.repaint();
        }
    }
}

