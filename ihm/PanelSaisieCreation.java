package ihm;

import controleur.Controleur;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import metier.Casting;
import metier.Role;

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
	
	private JButton 	btnSuivant;
	
	private JCheckBox[] 	tabCBCasting;
	private JCheckBox[] 	tabCBRole;
	
	private Casting[]	tabCasting;
	private Casting[]	tabCastingActif;
	
	private Role[]		tabRole;
	private Role[]		tabRoleActif;
	
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
		this.ctrl 	= ctrl	;
		this.frame      = f ;
		this.indice 	= indice;
		
		this.imgFond      = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");
		
		JPanel	panelNomPlateau		= new JPanel();
		panelNomPlateau.setLayout	(new FlowLayout(FlowLayout.CENTER));
		panelNomPlateau.setOpaque	(false);
		
		JPanel	panelTaillePlateau 	= new JPanel();
		panelTaillePlateau.setLayout	(new FlowLayout(FlowLayout.CENTER));
		panelTaillePlateau.setOpaque	(false);
		
		JPanel	panelCasting 		= new JPanel();
		panelCasting.setLayout		(new FlowLayout(FlowLayout.CENTER));
		panelCasting.setOpaque		(false);
		
		JPanel 	panelLstCasting		= new JPanel();
		panelLstCasting.setLayout	(new GridLayout(2, 3));
		panelLstCasting.setOpaque	(false);
		
		JPanel	panelRole 		= new JPanel();
		panelRole.setLayout		(new FlowLayout(FlowLayout.CENTER));
		panelRole.setOpaque		(false);
		
		JPanel 	panelLstRole		= new JPanel();
		panelLstRole.setLayout		(new GridLayout(2, 3));
		panelLstRole.setOpaque		(false);
		
		this.btnSuivant			= new JButton("Suivant");
		
		
		String[]  lstCasting	= new String[this.ctrl.getNbCasting()];
		
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
		lblTitre.setFont(new Font("SansSerif", Font.BOLD, 25));
		
		this.add(lblTitre);
		this.add(new JLabel(""));
		
		// Partie Plateau
		this.add(new JLabel("<html><u>Nom du Plateau</u></html>", JLabel.CENTER));
		
			// Partie Nom du Plateau
			panelNomPlateau.add(new JLabel ("Nom du plateau : ", 10));
			panelNomPlateau.add(this.txtNomPlateau);
		
			this.add(panelNomPlateau);
			
			// Partie Taille du Plateau
			this.add(new JLabel("<html><u>Taille du Plateau</u></html>", JLabel.CENTER));
		
			panelTaillePlateau.add(new JLabel("Nombre de lignes : ", 10));
			panelTaillePlateau.add(this.txtLigne);
			
			panelTaillePlateau.add(new JLabel("Nombre de colonnes : ", 10));
			panelTaillePlateau.add(this.txtColonne);
			
			this.add(panelTaillePlateau);
		
		// Partie Casting
		this.add(new JLabel("<html><u>Casting</u></html>", JLabel.CENTER));
		
		panelCasting.add(new JLabel("Nombre de castings : "));
		
		for (int cpt = 0; cpt < lstCasting.length; cpt++)
		{
			this.tabCBCasting[cpt]  = new JCheckBox( "" + lstCasting[cpt], false);
			this.tabCBCasting[cpt].setOpaque(false);
			panelLstCasting.add(this.tabCBCasting[cpt]);
		}
		
		panelCasting.add(panelLstCasting);
		this.add(panelCasting);
		
		// Partie Role
		this.add(new JLabel("<html><u>Rôles</u></html>", JLabel.CENTER));
		
		panelRole.add(new JLabel("Nombre de rôles : "));
		
		for (int cpt = 0; cpt < lstRole.length; cpt++)
		{
			this.tabCBRole[cpt] = new JCheckBox( "" + lstRole[cpt], false);
			this.tabCBRole[cpt].setOpaque(false);
			panelLstRole.add(this.tabCBRole[cpt]);
		}
		
		panelRole.add(panelLstRole);
		this.add(panelRole);
		
		// Partie Joueur
			// A voir + tard
		
		this.add(new JLabel(""));
			
		// Partie Bouton
		this.add(btnSuivant);
		
		/* ----------------------------- */
		/* Activation des Composants     */
		/* ----------------------------- */
		
		this.btnSuivant.addActionListener(this);
	}
	
	public void actionPerformed (ActionEvent e)
	{
		int nbLigne 	= 0;
		int nbColonne 	= 0;
		
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
						this.tabCastingActif[cpt] = tabCasting[cpt];
						//cptCasting++;					
					}
				}
				
				// Parcours pour compter nombre roles sélectionnés
				for (int cpt = 0; cpt < this.tabCBRole.length; cpt++)
				{
					if (this.tabCBRole[cpt].isSelected())
					{
						this.tabRoleActif[cpt] = tabRole[cpt];
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
							this.tabRoleActif,
							this.tabCastingActif );

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
