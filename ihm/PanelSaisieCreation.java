package ihm;

import controleur.Controleur;
import metier.Casting;
import metier.Role;

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
	public  static  int	TAILLE_MIN	= 6;
	public  static  int	TAILLE_MAX	= 10;
	public  static  int	TAILLE_CASE	= 50;
	
	private Controleur 	ctrl;
	private FrameCreation 	frame;
	private int 		indice;
	
	private Image 		imgFond;
	
	private JButton 	btnCreer;
	
	private JCheckBox[] 	tabCBCasting;
	private JCheckBox[] 	tabCBRole;
	
	private Casting[]	tabCasting;
	
	private Role[]		tabRole;
	
	// private JPanel	panelJoueur;
	
	private JTextField	txtNomPlateau;
	private JTextField 	txtLigne;
	private JTextField 	txtColonne;
	// private JTextField 	txtJoueur;
	
	public PanelSaisieCreation (Controleur ctrl, FrameCreation f, int indice)
	{	
		this.setLayout (new GridLayout (13, 1));
		
		/*-------------------------------------------------*/
		/*   Création et initialisation des composants     */
		/*-------------------------------------------------*/
		this.ctrl 	    = ctrl	;
		this.frame      = f ;
		this.indice 	= indice;
		
		this.imgFond    = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");
		
		JPanel	pnlNomPlateau		= new JPanel();
		pnlNomPlateau.setLayout	(new FlowLayout(FlowLayout.CENTER));
		pnlNomPlateau.setOpaque	(false);
		
		JPanel	pnlTaillePlateau 	= new JPanel();
		pnlTaillePlateau.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlTaillePlateau.setOpaque(false);
		
		JPanel	pnlCasting 		= new JPanel();
		pnlCasting.setLayout		(new FlowLayout(FlowLayout.CENTER));
		pnlCasting.setOpaque		(false);
		
		JPanel 	pnlLstCasting		= new JPanel();
		pnlLstCasting.setLayout	(new GridLayout(2, 3));
		pnlLstCasting.setOpaque	(false);
		
		JPanel	pnlRole 		    = new JPanel();
		pnlRole.setLayout		    (new FlowLayout(FlowLayout.CENTER));
		pnlRole.setOpaque		    (false);
		
		JPanel 	pnlLstRole	    = new JPanel();
		pnlLstRole.setLayout	    (new GridLayout(2, 3));
		pnlLstRole.setOpaque	    (false);

        JPanel  pnlBouton         = new JPanel();
        pnlBouton.setLayout       (new FlowLayout(FlowLayout.RIGHT, 20, 0));
        pnlBouton.setOpaque	      (false);

		this.btnCreer			    = new JButton("Suivant >>");
		
		
		String[]  lstCasting	    = new String[this.ctrl.getNbCasting()];
		
		for (int cpt = 0; cpt < lstCasting.length; cpt++)
		{
			lstCasting[cpt] = this.ctrl.getCasting(cpt);
		}
		
		this.tabCBCasting	= new JCheckBox[lstCasting.length];
		this.tabCasting		= new Casting  [lstCasting.length];
		
		String[]  lstRole	= new String[this.ctrl.getNbRole()];
		
		for (int cpt = 0; cpt < lstRole.length; cpt++)
		{
			lstRole[cpt] 	= this.ctrl.getRole(cpt);
		}
		
		this.tabCBRole		= new JCheckBox[lstRole.length];
		this.tabRole		= new Role     [lstRole.length];
		
		this.txtLigne		= new JTextField("", 5);
		this.txtColonne		= new JTextField("", 5);
		this.txtNomPlateau	= new JTextField("", 15);
		
		/*------------------------------------------*/
		/*  Positionnement des composants           */
		/*------------------------------------------*/
		
		this.add(new JLabel(""));
		
		JLabel lblTitre = new JLabel ("<html><u>Saisie des Informations</u></html>", JLabel.CENTER);
		lblTitre.setFont(new Font("SansSerif", Font.BOLD, 22));
		
		this.add(lblTitre);
		this.add(new JLabel(""));
		
		// Partie Plateau
		JLabel lblNomPlateau = new JLabel ("<html><u>Nom du Plateau</u></html>", JLabel.CENTER);
        lblNomPlateau.setFont(new Font("SansSerif", Font.BOLD, 12));
        this.add(lblNomPlateau);
		
			// Partie Nom du Plateau
            pnlNomPlateau.add(new JLabel ("Nom du plateau : ", 10));
            pnlNomPlateau.add(this.txtNomPlateau);
		
			this.add(pnlNomPlateau);
			
			// Partie Taille du Plateau
			this.add(new JLabel("<html><u>Taille du Plateau</u></html>", JLabel.CENTER));

            pnlTaillePlateau.add(new JLabel("Nombre de lignes : ", 10));
            pnlTaillePlateau.add(this.txtLigne);

            pnlTaillePlateau.add(new JLabel("Nombre de colonnes : ", 10));
            pnlTaillePlateau.add(this.txtColonne);
			
			this.add(pnlTaillePlateau);
		
		// Partie Casting
		this.add(new JLabel("<html><u>Casting</u></html>", JLabel.CENTER));
		
		pnlCasting.add(new JLabel("Nombre de castings : "));
		
		for (int cpt = 0; cpt < lstCasting.length; cpt++)
		{
			this.tabCBCasting[cpt]  = new JCheckBox( "" + lstCasting[cpt], false);
			this.tabCBCasting[cpt].setOpaque(false);
			pnlLstCasting.add(this.tabCBCasting[cpt]);
		}
		
		pnlCasting.add(pnlLstCasting);
		this.add(pnlCasting);
		
		// Partie Role
		this.add(new JLabel("<html><u>Rôles</u></html>", JLabel.CENTER));
		
		pnlRole.add(new JLabel("Nombre de rôles : "));
		
		for (int cpt = 0; cpt < lstRole.length; cpt++)
		{
			this.tabCBRole[cpt] = new JCheckBox( "" + lstRole[cpt], false);
			this.tabCBRole[cpt].setOpaque(false);
			pnlLstRole.add(this.tabCBRole[cpt]);
		}
		
		pnlRole.add(pnlLstRole);
		this.add(pnlRole);
		
		// Partie Joueur
			// A voir + tard
		
		this.add(new JLabel(""));
			
		// Partie Bouton
        pnlBouton.add(this.btnCreer);
		this.add(pnlBouton);
		
		/* ----------------------------- */
		/* Activation des Composants     */
		/* ----------------------------- */
		
		this.btnCreer.addActionListener(this);
	}
	
	public void actionPerformed (ActionEvent e)
	{
		Casting[] 	tabCastingActif = new Casting[this.tabCBCasting.length];
		Role[] 		tabRoleActif 	= new Role   [this.tabCBRole   .length];
		
		int nbLigne 	= 0;
		int nbColonne 	= 0;
		
		int cptCasting 	= 0;
		int cptRole 	= 0;
		
		if (e.getSource() == this.btnCreer)
		{	
			if (this.verifier())
			{
				// Parcours pour compter nombre castings sélectionnés
				for (int cpt = 0; cpt < this.tabCBCasting.length; cpt++)
				{
					if (this.tabCBCasting[cpt].isSelected())
					{
						tabCastingActif[cpt] = tabCasting[cpt];
						//cptCasting++;					
					}
				}
				
				// Parcours pour compter nombre roles sélectionnés
				for (int cpt = 0; cpt < this.tabCBRole.length; cpt++)
				{
					if (this.tabCBRole[cpt].isSelected())
					{
						tabRoleActif[cpt] = tabRole[cpt];
						//cptRole++;					
					}
				}
				
				nbLigne 	= Integer.parseInt(this.txtLigne.getText());
				nbColonne 	= Integer.parseInt(this.txtColonne.getText());
				
				/*
				// Affichage test
				System.out.println ( "Nom du Plateau : " +
				this.txtNomPlateau.getText());
				
				System.out.println ( "Taille : " + 
				this.txtLigne.getText() + this.txtColonne.getText());
				
				System.out.println ( "Nombre de casting : " + cptCasting);
				
				System.out.println ( "Nombre de rôles   : " + cptRole);
				*/
				
				this.ctrl.initCreateur(	this.txtNomPlateau.getText(),
							nbLigne,
							nbColonne,
							TAILLE_CASE,
							tabRoleActif,
							tabCastingActif );	

				this.frame.CreerPanelZone();
						
				this.frame.setPnl(this.frame.getPnl(this.indice+1));
			}
			else
			{
				System.out.println("Erreur : valeurs erronées.");
			}
		}
	}
	
	public boolean verifier()
	{	
		int nbLigne 	= 0;
		int nbColonne 	= 0;
		
		int cptCasting 	= 0;
		int cptRole 	= 0;
		
		if (this.txtNomPlateau.equals("") || this.txtLigne.equals("") || 
		    this.txtColonne.equals("")				)
		{
			return false;
		}
		else
		{
			try
			{
				nbLigne   = Integer.parseInt(this.txtLigne  .getText());
				nbColonne = Integer.parseInt(this.txtColonne.getText());
			}
			catch (Exception e)
			{
				System.out.println ("Erreur : la taille du plateau saisie n'est pas un entier. Réessayer.");
				return false;
			}
			
			if (nbLigne   < TAILLE_MIN || nbLigne   > TAILLE_MAX ||
			    nbColonne < TAILLE_MIN || nbColonne > TAILLE_MAX)
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
}
