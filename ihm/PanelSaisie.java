package ihm;

import controleur.Controleur;

import metier.CreateurPlateau;
import metier.Casting;
import metier.Role;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;

import java.awt.event.*;

import javax.swing.*;

public class PanelSaisie extends JPanel implements ActionListener
{
	public 	static 	int 	NB_CASTING_MIN 	= 4;
	public 	static 	int 	NB_ROLE_MIN 	= 2;
	
	private Controleur 	ctrl;
	private FrameSaisie 	frame;
	private int 		indice;
	
	private JButton 	btnSuivant;
	
	private JCheckBox[] 	tabCBCasting;
	private JCheckBox[] 	tabCBRole;
	
	// private JPanel	panelJoueur;
	
	private JTextField	txtNomPlateau;
	private JTextField 	txtLigne;
	private JTextField 	txtColonne;
	// private JTextField 	txtJoueur;
	
	public PanelSaisie (Controleur ctrl, FrameSaisie f, int indice)
	{	
		this.setLayout (new GridLayout (13, 1));
		
		/*-------------------------------------------------*/
		/*   Création et initialisation des composants     */
		/*-------------------------------------------------*/
		this.ctrl 	= ctrl	;
		this.frame      = f ;
		this.indice 	= indice;
		
		JPanel	panelNomPlateau		= new JPanel();
		panelNomPlateau.setLayout	(new FlowLayout(FlowLayout.CENTER));
		
		JPanel	panelTaillePlateau 	= new JPanel();
		panelTaillePlateau.setLayout	(new FlowLayout(FlowLayout.CENTER));
		
		JPanel	panelCasting 		= new JPanel();
		panelCasting.setLayout		(new FlowLayout(FlowLayout.CENTER));
		
		JPanel 	panelLstCasting		= new JPanel();
		panelLstCasting.setLayout	(new GridLayout(2, 3));
		
		JPanel	panelRole 		= new JPanel();
		panelRole   .setLayout		(new FlowLayout(FlowLayout.CENTER));
		
		JPanel 	panelLstRole		= new JPanel();
		panelLstRole.setLayout		(new GridLayout(2, 3));
		
		this.btnSuivant			= new JButton("Suivant");
		
		
		String[]  lstCasting	= new String[this.ctrl.getNbCasting()];
		
		for (int cpt = 0; cpt < lstCasting.length; cpt++)
		{
			lstCasting[cpt] = this.ctrl.getCasting(cpt);
		}
		
		this.tabCBCasting	= new JCheckBox[lstCasting.length];
		
		String[]  lstRole	= new String[this.ctrl.getNbRole()];
		
		for (int cpt = 0; cpt < lstRole.length; cpt++)
		{
			lstRole[cpt] 	= this.ctrl.getRole(cpt);
		}
		
		this.tabCBRole		= new JCheckBox[lstRole.length];
		
		this.txtLigne		= new JTextField("", 5);
		this.txtColonne		= new JTextField("", 5);
		this.txtNomPlateau	= new JTextField("", 15);
		
		/*-------------------------------------------------*/
		/*         Positionnement des composants           */
		/*-------------------------------------------------*/
		// Partie Plateau
		this.add(new JLabel("<html><u>Nom du Plateau</u></html>", JLabel.CENTER));
		
			// Partie Nom du Plateau
			panelNomPlateau.add(new JLabel ("Nom du plateau : ", 10));
			panelNomPlateau.add(this.txtNomPlateau);
		
			this.add(panelNomPlateau);
			
			this.add(new JLabel(""));
			
			// Partie Taille du Plateau
			this.add(new JLabel("<html><u>Taille du Plateau</u></html>", JLabel.CENTER));
		
			panelTaillePlateau.add(new JLabel("Nombre de lignes : ", 10));
			panelTaillePlateau.add(this.txtLigne);
			
			panelTaillePlateau.add(new JLabel("Nombre de colonnes : ", 10));
			panelTaillePlateau.add(this.txtColonne);
			
			this.add(panelTaillePlateau);
			
			this.add(new JLabel(""));
		
		// Partie Casting
		this.add(new JLabel("<html><u>Casting</u></html>", JLabel.CENTER));
		
		panelCasting.add(new JLabel("Nombre de castings : "));
		
		for (int cpt = 0; cpt < lstCasting.length; cpt++)
		{
			this.tabCBCasting[cpt] = new JCheckBox( "" + lstCasting[cpt], false);
			panelLstCasting.add(this.tabCBCasting[cpt]);
		}
		
		panelCasting.add(panelLstCasting);
		this.add(panelCasting);
		
		this.add(new JLabel(""));
		
		// Partie Role
		this.add(new JLabel("<html><u>Rôles</u></html>", JLabel.CENTER));
		
		panelRole.add(new JLabel("Nombre de rôles : "));
		
		for (int cpt = 0; cpt < lstRole.length; cpt++)
		{
			this.tabCBRole[cpt] = new JCheckBox( "" + lstRole[cpt], false);
			panelLstRole.add(this.tabCBRole[cpt]);
		}
		
		panelRole.add(panelLstRole);
		this.add(panelRole);
		
		// Partie Joueur
			// A voir + tard
		
		this.add(new JLabel(""));
			
		// Partie Bouton
		this.add(btnSuivant);
		
		this.btnSuivant.addActionListener(this);
	}
	
	public void actionPerformed (ActionEvent e)
	{
		int cptCasting 	= 0;
		int cptRole 	= 0;
		
		if (e.getSource() == this.btnSuivant)
		{
			this.frame.setPnl(this.frame.getPnl(this.indice+1));
			
			if (this.verifier())
			{
				// Parcours pour compter nombre castings sélectionnés
				for (int cpt = 0; cpt < this.tabCBCasting.length; cpt++)
				{
					if (this.tabCBCasting[cpt].isSelected())
					{
						cptCasting++;					
					}
				}
				
				// Parcours pour compter nombre roles sélectionnés
				for (int cpt = 0; cpt < this.tabCBRole.length; cpt++)
				{
					if (this.tabCBRole[cpt].isSelected())
					{
						cptRole++;					
					}
				}
				
				// Affichage test
				System.out.println ( "Nom du Plateau : " +
				this.txtNomPlateau.getText());
				
				System.out.println ( "Taille : " + 
				this.txtLigne.getText() + this.txtColonne.getText());
				
				System.out.println ( "Nombre de casting : " + cptCasting);
				
				System.out.println ( "Nombre de rôles   : " + cptRole);
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
}
