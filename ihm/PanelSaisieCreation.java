package ihm;

import controleur.Controleur;
import metier.Casting;
import metier.Role;

import java.io.File;

import java.awt.BorderLayout;
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
	/* -- Constantes -- */
	
	public 	static 	int  NB_CASTING_MIN  = 4;
	public 	static 	int  NB_ROLE_MIN     = 2;
	public  static  int  TAILLE_MIN	     = 6;
	public  static  int  TAILLE_MAX      = 10;
   	public  static  int  TAILLE_CASE_MAX = 35;

	/* -- Attributs -- */
	
	private Controleur      ctrl;
	private FrameCreation   frame;
	private int             indice;           // permet le changement des panels
	
	private Image           imgFond;
	
	private JButton         btnSuivant;
    	private JButton         btnPrecedent;

	private JCheckBox[]     tabCBCasting;     // tableau de CheckBox pour choisir les castings
	private JCheckBox[]     tabCBRole;        // tableau de CheckBox pour choisir les rôles
	
	private Casting[]       tabCasting;       // tableau de Casting  pour récupérer sous forme de Casting les castings sélectionnés
	private Role[]          tabRole;          // tableau de Role     pour récupérer sous forme de Role    les rôles    sélectionnés
	
	private JTextField      txtNomPlateau;
	private JTextField      txtLigne;
	private JTextField      txtColonne;

    	private Font            policeSousTitre;  // permet la personnalisation des sous-titres
    	private Font            policeLabel;	  // permet la personnalisation des labels
	
	public PanelSaisieCreation (Controleur ctrl, FrameCreation f, int indice)
	{	
		this.setLayout (new GridLayout (6, 1, 0, 10));
		
		
		/*-------------------------------*/
		/*   Création des composants     */
		/*-------------------------------*/
		
		this.ctrl                          = ctrl;
		this.frame                         = f ;
		this.indice                        = indice;
		
		this.imgFond                       = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");
		
		// Partie Plateau
			JPanel  pnlNomPlateau      = new JPanel();
			pnlNomPlateau              .setLayout(new BorderLayout(0, 15));
			pnlNomPlateau              .setOpaque(false);

			JPanel	pnlSaisieNom       = new JPanel();
			pnlSaisieNom               .setLayout (new FlowLayout(FlowLayout.CENTER));
			pnlSaisieNom               .setOpaque(false);
			
			JPanel	pnlTaillePlateau   = new JPanel();
			pnlTaillePlateau           .setLayout(new BorderLayout(0, 15));
			pnlTaillePlateau           .setOpaque(false);

			JPanel  pnlSaisieTaille    = new JPanel();
			pnlSaisieTaille            .setLayout(new FlowLayout(FlowLayout.CENTER));
			pnlSaisieTaille            .setOpaque(false);

		// Partie Casting
			JPanel	pnlCasting         = new JPanel();
			pnlCasting.setLayout       (new BorderLayout(0, 10));
			pnlCasting.setOpaque       (false);
			
			JPanel  pnlSaisieCasting   = new JPanel();
			pnlSaisieCasting           .setLayout(new FlowLayout(FlowLayout.CENTER));
			pnlSaisieCasting           .setOpaque(false);

			JPanel 	pnlLstCasting      = new JPanel();
			pnlLstCasting.setLayout    (new GridLayout(2, 3));
			pnlLstCasting.setOpaque    (false);

		// Partie Rôle
			JPanel	pnlRole            = new JPanel();
			pnlRole.setLayout          (new BorderLayout(0, 10));
			pnlRole.setOpaque          (false);

			JPanel  pnlSaisieRole      = new JPanel();
			pnlSaisieRole              .setLayout(new FlowLayout(FlowLayout.CENTER));
			pnlSaisieRole              .setOpaque(false);

			JPanel 	pnlLstRole         = new JPanel();
			pnlLstRole.setLayout       (new GridLayout(2, 3));
			pnlLstRole.setOpaque       (false);

		// Partie Bouton
			JPanel  pnlBouton          = new JPanel();
			pnlBouton.setLayout        (new FlowLayout(FlowLayout.CENTER, 50, 20));
			pnlBouton.setOpaque        (false);

			this.btnPrecedent          = new JButton("<< Précédent" );
			this.btnSuivant            = new JButton("Suivant >>");
		
		// permet de donner une limite plus propre plutôt qu'en dur lors des parcours
		String[]  lstCasting               = new String[this.ctrl.getNbCasting()];	
		
		for (int cpt = 0; cpt < lstCasting.length; cpt++)
			lstCasting[cpt]            = this.ctrl.getCasting(cpt);
		
		this.tabCBCasting                  = new JCheckBox[lstCasting.length];
		this.tabCasting                    = new Casting  [lstCasting.length];

		for (int cpt = 0; cpt < lstCasting.length; cpt++)
		    	this.tabCasting[cpt]       = Casting.valueOf(cpt);
		
		
		// permet de donner une limite plus propre plutôt qu'en dur lors des parcours
		String[]  lstRole                  = new String[this.ctrl.getNbRole()];
		for (int cpt = 0; cpt < lstRole.length; cpt++)
			lstRole[cpt]               = this.ctrl.getRole(cpt);
		
		this.tabCBRole                     = new JCheckBox[lstRole.length];
		this.tabRole                       = new Role     [lstRole.length];

		for (int cpt = 0; cpt < lstRole.length; cpt++)
		    	this.tabRole[cpt]          = Role.valueOf(cpt);

        	this.txtNomPlateau                 = new JTextField("", 15);
		this.txtLigne                      = new JTextField("", 5);
		this.txtColonne                    = new JTextField("", 5);

        	JLabel lblTitre                    = new JLabel ("<html><u>Saisie des Informations</u></html>", JLabel.CENTER);

		// Essai pour initialiser les polices en prenant la police téléchargée dans le dossier polices/
        	try
        	{
		    File fichierTitre              = new File("./polices/TitreSaisieInfos/Shrikhand-Regular.ttf");
		    Font policeLbl                 = Font.createFont(Font.TRUETYPE_FONT, fichierTitre);   // crée la police
		    
		    java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(policeLbl);   // enregistre la police dans le système Java

		    Font policeTitre               =  policeLbl.deriveFont(Font.BOLD, 22f);               // police du titre 		modifié en Gras + taille 22
		    this.policeSousTitre           =  policeLbl.deriveFont(Font.PLAIN, 16f);              // police du sous-titre 	modifié en Gras + taille 16
		    this.policeLabel               =  policeLbl.deriveFont(Font.PLAIN, 12f);              // police des autres labels 	modifié en Gras + taille 12

		    lblTitre.setFont(policeTitre);                                                        // police du label du titre changé
		}
		// Si fichier non trouvé, la police du titre est en SansSerif Gras par défaut
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
		    	pnlNomPlateau.add(this.creerSousTitre("Nom du Plateau : "), BorderLayout.NORTH);

		    	pnlSaisieNom.add(this.txtNomPlateau);

			pnlNomPlateau.add(pnlSaisieNom, BorderLayout.CENTER);
		    	pnlNomPlateau.add(this.creerSeparation(), BorderLayout.SOUTH);

		    	this.add(pnlNomPlateau);
				
			// Partie Taille du Plateau
		    	pnlTaillePlateau.add(this.creerSousTitre("Taille du Plateau"), BorderLayout.NORTH);

		    	pnlSaisieTaille.add(this.creerJLabel("Lignes : "));
		    	pnlSaisieTaille.add(this.txtLigne);
		    	pnlSaisieTaille.add(this.creerJLabel("Colonnes : "));
		    	pnlSaisieTaille.add(this.txtColonne);

		    	pnlTaillePlateau.add(pnlSaisieTaille, BorderLayout.CENTER);
		    	pnlTaillePlateau.add(this.creerSeparation(), BorderLayout.SOUTH);
		    	
		    	this.add(pnlTaillePlateau);
			
			
		// Partie Casting
		pnlCasting.add(this.creerSousTitre("Castings"), BorderLayout.NORTH);

		pnlSaisieCasting.add(this.creerJLabel("Castings séléctionnés : "));

		// Parcours permettant de construire le tableau de CheckBox des Castings et les rendre ergonomique, et les ajouter au panel
		for (int cpt = 0; cpt < lstCasting.length; cpt++)
		{
			this.tabCBCasting[cpt]  = new JCheckBox( "" + lstCasting[cpt], false);
			this.tabCBCasting[cpt].setOpaque(false);
		    	this.tabCBCasting[cpt].setFont(new Font("SansSerif", Font.ITALIC, 12));

		   	pnlLstCasting.add(this.tabCBCasting[cpt]);
		}

		pnlSaisieCasting.add(pnlLstCasting);

		pnlCasting.add(pnlSaisieCasting, BorderLayout.CENTER);
		pnlCasting.add(this.creerSeparation(), BorderLayout.SOUTH);

		this.add(pnlCasting);
			
			
		// Partie Role
		pnlRole.add(this.creerSousTitre("Rôles"), BorderLayout.NORTH);

		pnlSaisieRole.add(this.creerJLabel("Rôles séléctionnés : "));
			
		// Parcours permettant de construire le tableau de CheckBox des Roles et les rendre ergonomique, et les ajouter au panel
		for (int cpt = 0; cpt < lstRole.length; cpt++)
		{
			this.tabCBRole[cpt] = new JCheckBox( "" + lstRole[cpt], false);
			this.tabCBRole[cpt].setOpaque(false);
		    	this.tabCBRole[cpt].setFont(new Font("SansSerif", Font.ITALIC, 12));

			pnlLstRole.add(this.tabCBRole[cpt]);
		}
			
		pnlSaisieRole.add(pnlLstRole);

		pnlRole.add(pnlSaisieRole, BorderLayout.CENTER);
		pnlRole.add(this.creerSeparation(), BorderLayout.SOUTH);

		this.add(pnlRole);
				
				
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
		Casting[] 	tabCastingActif = new Casting[this.tabCBCasting.length];   // tableau de Casting --> sera en attribut de initCreateur
                Role[] 		tabRoleActif 	= new Role   [this.tabCBRole   .length];   // tableau de Casting --> sera en attribut de initCreateur
		
		int cptLigne   = 0;                                                        // compteur de lignes   saisies par le joueur --> sera en attribut de initCreateur
		int cptColonne = 0;                                                        // compteur de colonnes saisies par le joueur --> sera en attribut de initCreateur
		
		if (e.getSource() == this.btnSuivant)
		{	
			if (this.verifier())
			{
				// Parcours pour ajouter les castings sélectionnés dans le tableau tabCastingActif
				for (int cpt = 0; cpt < this.tabCBCasting.length; cpt++)
				{
					if (this.tabCBCasting[cpt].isSelected())
					{
						tabCastingActif[cpt] = tabCasting[cpt];
					}
				}
				
				// Parcours pour ajouter les rôles sélectionnés dans le tableau tabRoleActif
				for (int cpt = 0; cpt < this.tabCBRole.length; cpt++)
				{
					if (this.tabCBRole[cpt].isSelected())
					{
						tabRoleActif[cpt]   = tabRole[cpt];
					}
				}
				
				cptLigne        = Integer.parseInt(this.txtLigne.getText());
				cptColonne      = Integer.parseInt(this.txtColonne.getText());
				
				this.ctrl.initCreateur(	this.txtNomPlateau.getText(),
							cptLigne,
							cptColonne,
							TAILLE_CASE_MAX,
							tabRoleActif,
							tabCastingActif );	

				this.frame.creerPanelZone(this.indice + 1);                // crée le PanelZone uniquement quand le plateau est initialisé
						
				this.frame.setPnl(this.frame.getPnl(this.indice+1));       // changement de panel --> PanelZone
			}
			else
			{
				System.out.println("Erreur : valeurs erronées.");
			}
		}

        	if (e.getSource() == this.btnPrecedent)
        	{
            		this.frame.setPnl(this.frame.getPnl(this.indice-1));               // revient au PanelAccueilCreation
        	}
	}
	
	// Méthode permettant de vérifier la saisie du joueur : retourne true si les valeurs sont valides, false sinon.
	public boolean verifier()
	{	
		int cptLigne    = 0;
		int cptColonne  = 0;
		
		int cptCasting  = 0;
		int cptRole     = 0;
		
		// Vérification si les JTextField ne sont pas vides
		if (this.txtNomPlateau  .equals("") || this.txtLigne        .equals("") ||
		    this.txtColonne     .equals("")	    )
		{
			return false;
		}
		else
		{
			// Essaie de convertir le texte des TextField txtLigne et txtColonne dans un entier
			try
			{
				cptLigne     = Integer.parseInt(this.txtLigne  .getText());
				cptColonne   = Integer.parseInt(this.txtColonne.getText());
			}
			catch (Exception e)
			{
				System.out.println ("Erreur : la taille du plateau saisie n'est pas correcte. Réessayer.");
				return false;
			}
			
			// Vérification si le nombre de lignes/colonnes sont valides
			if (cptLigne   < TAILLE_MIN || cptLigne   > TAILLE_MAX ||
			    cptColonne < TAILLE_MIN || cptColonne > TAILLE_MAX)

			{
				System.out.println ("Erreur : la saisie de la taille du plateau est erronée. Réessayer.");
				return false;
			}
			
			// Boucle permettant de compter le nombre de castings sélectionnés
			for (int cpt = 0; cpt < this.tabCBCasting.length; cpt++)
			{
				if (this.tabCBCasting[cpt].isSelected())
				{
					cptCasting++;					
				}
			}
			
			if (cptCasting < NB_CASTING_MIN) { return false; } 
			
			// Boucle permettant de compter le nombre de rôles sélectionnés
			for (int cpt = 0; cpt < this.tabCBRole.length; cpt++)
			{
				if (this.tabCBRole[cpt].isSelected())
				{
					cptRole++;					
				}
			}
			
			if (cptRole < NB_ROLE_MIN)       { return false; } 
		}
		
		return true;
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

	// Méthode permettant de créer des JLabel soulignés et avec la police définie pour les sous-titres   de façon optimisé 
	private JLabel creerSousTitre(String texte)
	{
        	JLabel lbl = new JLabel("<html><u>" + texte + "</u></html>", JLabel.CENTER);
        	lbl.setFont(this.policeSousTitre);

        	return lbl;
	}

	// Méthode permettant de créer des JLabel soulignés et avec la police définie pour les autres labels de façon optimisé 
	private JLabel creerJLabel(String texte)
	{
        	JLabel lbl = new JLabel(texte, 10);
        	lbl.setFont(this.policeLabel);

        	return lbl;
	}

	// Méthode permettant de créer des séparations entre les blocs avec JSeparator de façon optimisé
	private JSeparator creerSeparation()
    	{
		JSeparator separateur = new JSeparator(SwingConstants.HORIZONTAL);
        	separateur.setForeground(new Color(150, 150, 150));
        	separateur.setBackground(new Color(0, 0, 0, 0));

        	return separateur;
    	}
}
