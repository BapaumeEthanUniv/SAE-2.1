package ihm;

import controleur.Controleur;
import metier.Casting;
import metier.Role;

import java.io.File;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

public class PanelSaisieCreation extends JPanel implements ActionListener
{
	public 	static 	int 	NB_CASTING_MIN 	= 4;
	public 	static 	int 	NB_ROLE_MIN 	= 2;
	public  static  int	    TAILLE_MIN	= 6;
	public  static  int	    TAILLE_MAX	= 10;
	public  static  int	    TAILLE_CASE_MIN	= 20;
    public  static  int     TAILLE_CASE_MAX = 45;

	private Controleur 	ctrl;
	private FrameCreation 	frame;
	private int 		indice;
	
	private Image 		imgFond;
	
	private JButton 	btnSuivant;
    private JButton     btnPrecedent;

	private JCheckBox[] 	tabCBCasting;
	private JCheckBox[] 	tabCBRole;
	
	private Casting[]	tabCasting;
	
	private Role[]		tabRole;
	
	// private JPanel	panelJoueur;
	
	private JTextField	txtNomPlateau;
	private JTextField 	txtLigne;
	private JTextField 	txtColonne;
    private JTextField  txtTailleCase;
	// private JTextField 	txtJoueur;

    private Font policeSousTitre;
    private Font policeLabel;
	
	public PanelSaisieCreation (Controleur ctrl, FrameCreation f, int indice)
	{	
		this.setLayout (new GridLayout (6, 1, 0, 10));
		
		/*-------------------------------------------------*/
		/*   Création et initialisation des composants     */
		/*-------------------------------------------------*/
		this.ctrl 	                = ctrl;
		this.frame                  = f ;
		this.indice 	            = indice;
		
		this.imgFond                = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");
		
		// Partie Plateau
        JPanel  pnlPlateau          = new JPanel();
        pnlPlateau                  .setLayout(new BorderLayout());
        pnlPlateau                  .setOpaque(false);

        JPanel	pnlNomPlateau		= new JPanel();
		pnlNomPlateau.setLayout	    (new FlowLayout(FlowLayout.CENTER));
		pnlNomPlateau.setOpaque	    (false);
		
		JPanel	pnlTaillePlateau 	= new JPanel();
		pnlTaillePlateau            .setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlTaillePlateau            .setOpaque(false);

        // Partie Casting
		JPanel	pnlCasting 		    = new JPanel();
		pnlCasting.setLayout		(new FlowLayout(FlowLayout.CENTER));
		pnlCasting.setOpaque		(false);
		
		JPanel 	pnlLstCasting		= new JPanel();
		pnlLstCasting.setLayout	    (new GridLayout(2, 3));
		pnlLstCasting.setOpaque	    (false);

        // Partie Rôle
		JPanel	pnlRole 		    = new JPanel();
		pnlRole.setLayout		    (new FlowLayout(FlowLayout.CENTER));
		pnlRole.setOpaque		    (false);
		
		JPanel 	pnlLstRole	        = new JPanel();
		pnlLstRole.setLayout	    (new GridLayout(2, 3));
		pnlLstRole.setOpaque	    (false);

        // Partie Bouton
        JPanel  pnlBouton           = new JPanel();
        pnlBouton.setLayout         (new GridLayout(1,2));
        pnlBouton.setOpaque	        (false);

        this.btnPrecedent			= new JButton("<< Précédent" );
		this.btnSuivant			    = new JButton("Suivant >>");
		
		String[]  lstCasting	    = new String[this.ctrl.getNbCasting()];
		
		for (int cpt = 0; cpt < lstCasting.length; cpt++)
		{
			lstCasting[cpt]         = this.ctrl.getCasting(cpt);
		}
		
		this.tabCBCasting	        = new JCheckBox[lstCasting.length];
		this.tabCasting		        = new Casting  [lstCasting.length];

        for (int cpt = 0; cpt < lstCasting.length; cpt++)
        {
            this.tabCasting[cpt]    = Casting.valueOf(cpt);
        }
		
		String[]  lstRole	        = new String[this.ctrl.getNbRole()];
		
		for (int cpt = 0; cpt < lstRole.length; cpt++)
		{
			lstRole[cpt] 	        = this.ctrl.getRole(cpt);
		}
		
		this.tabCBRole		        = new JCheckBox[lstRole.length];
		this.tabRole		        = new Role     [lstRole.length];

        for (int cpt = 0; cpt < lstRole.length; cpt++)
        {
            this.tabRole[cpt]       = Role.valueOf(cpt);
        }

        this.txtNomPlateau	        = new JTextField("", 15);
		this.txtLigne		        = new JTextField("", 5);
		this.txtColonne		        = new JTextField("", 5);
        this.txtTailleCase          = new JTextField("", 5);

        JLabel lblTitre             = new JLabel ("<html><u>Saisie des Informations</u></html>", JLabel.CENTER);

        try
        {
            File fichierTitre       = new File("./polices/TitreSaisieInfos/Shrikhand-Regular.ttf");
            Font fontTitreBase      = Font.createFont(Font.TRUETYPE_FONT, fichierTitre);
            Font fontBase  = Font.createFont(Font.TRUETYPE_FONT, fichierTitre);

            java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(fontTitreBase);
            java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(fontBase);

            Font fontTitreFinal     =  fontTitreBase.deriveFont(Font.BOLD, 22f);
            this.policeSousTitre    =  fontBase.deriveFont(Font.PLAIN, 16f);
            this.policeLabel        =  fontBase.deriveFont(Font.PLAIN, 12f);

            lblTitre.setFont(fontTitreFinal);
        }
        catch (Exception e)
        {
            lblTitre.setFont(new Font("SansSerif", Font.BOLD, 22));
        }
		
		/*------------------------------------------*/
		/*  Positionnement des composants           */
		/*------------------------------------------*/

		this.add(lblTitre);
		
		// Partie Plateau
            // Partie Nom du Plateau
            pnlNomPlateau.add(this.creerSousTitre("Nom du Plateau : "));
            pnlNomPlateau.add(this.txtNomPlateau);
		
			this.add(pnlNomPlateau);
			
			// Partie Taille du Plateau
			this.add(this.creerSeparation());

            this.add(this.creerSousTitre("Taille du Plateau"));

            pnlTaillePlateau.add(this.creerJLabel("Lignes : "));
            pnlTaillePlateau.add(this.txtLigne);

            pnlTaillePlateau.add(this.creerJLabel("Colonnes : "));
            pnlTaillePlateau.add(this.txtColonne);

            pnlTaillePlateau.add(this.creerJLabel("Taille Cases : "));
            pnlTaillePlateau.add(this.txtTailleCase);
			
			this.add(pnlTaillePlateau);
		
		// Partie Casting
        this.add(this.creerSeparation());

		this.add(this.creerSousTitre("Castings"));
		
		pnlCasting.add(this.creerJLabel("Nombre de Castings : "));
		
		for (int cpt = 0; cpt < lstCasting.length; cpt++)
		{
			this.tabCBCasting[cpt]  = new JCheckBox( "" + lstCasting[cpt], false);
			this.tabCBCasting[cpt].setOpaque(false);
            this.tabCBCasting[cpt].setFont(new Font("SansSerif", Font.ITALIC, 12));

            pnlLstCasting.add(this.tabCBCasting[cpt]);
		}
		
		pnlCasting.add(pnlLstCasting);
		this.add(pnlCasting);
		
		// Partie Role
        this.add(this.creerSeparation());

		this.add(this.creerSousTitre("Rôles"));
		
		pnlRole.add(this.creerJLabel("Nombre de Rôles : "));
		
		for (int cpt = 0; cpt < lstRole.length; cpt++)
		{
			this.tabCBRole[cpt] = new JCheckBox( "" + lstRole[cpt], false);
			this.tabCBRole[cpt].setOpaque(false);
            this.tabCBRole[cpt].setFont(new Font("SansSerif", Font.ITALIC, 12));

			pnlLstRole.add(this.tabCBRole[cpt]);
		}
		
		pnlRole.add(pnlLstRole);
		this.add(pnlRole);
		
		// Partie Joueur
			// A voir + tard
		
		this.add(this.creerSeparation());
			
		// Partie Bouton
        pnlBouton.add(this.btnPrecedent);
        pnlBouton.add(this.btnSuivant);

		this.add(pnlBouton);
		
		/* ----------------------------- */
		/* Activation des Composants     */
		/* ----------------------------- */
		
		this.btnSuivant.addActionListener(this);
        this.btnPrecedent.addActionListener(this);
	}
	
	public void actionPerformed (ActionEvent e)
	{
		Casting[] 	tabCastingActif = new Casting[this.tabCBCasting.length];
        String      sCasting = "";

		Role[] 		tabRoleActif 	= new Role   [this.tabCBRole   .length];
        String      sRole = "";
		
		int nbLigne 	= 0;
		int nbColonne 	= 0;
        int tailleCase  = 0;
		
		int cptCasting 	= 0;
		int cptRole 	= 0;
		
		if (e.getSource() == this.btnSuivant)
		{	
			if (this.verifier())
			{
				// Parcours pour compter nombre castings sélectionnés
				for (int cpt = 0; cpt < this.tabCBCasting.length; cpt++)
				{
					if (this.tabCBCasting[cpt].isSelected())
					{
						tabCastingActif[cpt] = tabCasting[cpt];
                        sCasting += tabCastingActif[cpt] + " ";
					}
				}
				
				// Parcours pour compter nombre roles sélectionnés
				for (int cpt = 0; cpt < this.tabCBRole.length; cpt++)
				{
					if (this.tabCBRole[cpt].isSelected())
					{
						tabRoleActif[cpt] = tabRole[cpt];
						sRole += tabRoleActif[cpt] + " ";
					}
				}
				
				nbLigne 	= Integer.parseInt(this.txtLigne.getText());
				nbColonne 	= Integer.parseInt(this.txtColonne.getText());
                tailleCase  = Integer.parseInt(this.txtTailleCase.getText());

				// Affichage test
				System.out.println ( "Nom du Plateau : " +
				this.txtNomPlateau.getText());
				
				System.out.println ( "Taille : " + 
				this.txtLigne.getText() + " " + this.txtColonne.getText() + " " + this.txtTailleCase.getText());
				
				System.out.println ( "Casting : " + sCasting);
				
				System.out.println ( "Nombre de rôles   : " + sRole);
				
				this.ctrl.initCreateur(	this.txtNomPlateau.getText(),
							nbLigne,
							nbColonne,
							tailleCase,
							tabRoleActif,
							tabCastingActif );	

				this.frame.creerPanelZone();
						
				this.frame.setPnl(this.frame.getPnl(this.indice+1));
			}
			else
			{
				System.out.println("Erreur : valeurs erronées.");
			}
		}

        if (e.getSource() == this.btnPrecedent)
        {
            this.frame.setPnl(this.frame.getPnl(this.indice-1));
        }
	}
	
	public boolean verifier()
	{	
		int nbLigne 	= 0;
		int nbColonne 	= 0;
        int tailleCase  = 0;
		
		int cptCasting 	= 0;
		int cptRole 	= 0;
		
		if (this.txtNomPlateau  .equals("") || this.txtLigne        .equals("") ||
		    this.txtColonne     .equals("")	|| this.txtTailleCase   .equals("")	    )
		{
			return false;
		}
		else
		{
			try
			{
				nbLigne     = Integer.parseInt(this.txtLigne        .getText());
				nbColonne   = Integer.parseInt(this.txtColonne      .getText());
                tailleCase  = Integer.parseInt(this.txtTailleCase   .getText());
			}
			catch (Exception e)
			{
				System.out.println ("Erreur : la taille du plateau saisie n'est pas correcte. Réessayer.");
				return false;
			}
			
			if (nbLigne     < TAILLE_MIN        || nbLigne      > TAILLE_MAX ||
			    nbColonne   < TAILLE_MIN        || nbColonne    > TAILLE_MAX ||
                tailleCase  < TAILLE_CASE_MIN   || tailleCase   > TAILLE_CASE_MAX)
			{
				System.out.println ("Erreur : la saisie de la taille du plateau est erronée. Réessayer.");
				return false;
			}
			
			for (int cpt = 0; cpt < this.tabCBCasting.length; cpt++)
			{
				if (this.tabCBCasting[cpt].isSelected())
				{
					cptCasting++;					
				}
			}
			
			if (cptCasting < NB_CASTING_MIN) { return false; } 
			
			for (int cpt = 0; cpt < this.tabCBRole.length; cpt++)
			{
				if (this.tabCBRole[cpt].isSelected())
				{
					cptRole++;					
				}
			}
			
			if (cptRole < NB_ROLE_MIN) 	 { return false; } 
		}
		
		return true;
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

    private JLabel creerSousTitre(String texte)
    {
        JLabel lbl = new JLabel("<html><u>" + texte + "</u></html>", JLabel.CENTER);
        lbl.setFont(this.policeSousTitre);

        return lbl;
    }

    private JLabel creerJLabel(String texte)
    {
        JLabel lbl = new JLabel(texte, 10);
        lbl.setFont(this.policeLabel);

        return lbl;
    }

    private JSeparator creerSeparation()
    {
        JSeparator separateur = new JSeparator(SwingConstants.HORIZONTAL);
        separateur.setForeground(new Color(150, 150, 150));
        separateur.setBackground(new Color(0, 0, 0, 0));

        return separateur;
    }
}
