package ihm;

import controleur.Controleur;

import metier.Plateau;
import metier.Casting;
import metier.Role;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class PanelSaisie extends JPanel
{
	private Controleur 	ctrl;
	
	private JButton 	btnCreer;
	
	private JCheckBox[] 	tabCBCasting;
	private JCheckBox[] 	tabCBRole;
	
	// private JPanel	panelJoueur;
	
	private JTextField 	txtLigne;
	private JTextField 	txtColonne;
	private JTextField 	txtCasting;
	private JTextField 	txtRole;
	// private JTextField 	txtJoueur;
	
	public PanelSaisie (Controleur ctrl)
	{	
		this.setLayout (new GridLayout (7, 1));
		
		/*-------------------------------------------------*/
		/*   Création et initialisation des composants     */
		/*-------------------------------------------------*/
		this.ctrl = ctrl;
		
		JPanel	panelPlateau 	= new JPanel();
		panelPlateau.setLayout	(new FlowLayout(FlowLayout.LEFT));
		
		JPanel	panelCasting 	= new JPanel();
		panelCasting.setLayout	(new FlowLayout(FlowLayout.LEFT));
		
		JPanel 	panelLstCasting	= new JPanel();
		panelLstCasting.setLayout(new GridLayout(2, 3));
		
		JPanel	panelRole 	= new JPanel();
		panelRole   .setLayout	(new FlowLayout(FlowLayout.LEFT));
		
		JPanel 	panelLstRole	= new JPanel();
		panelLstRole.setLayout	(new GridLayout(2, 3));
		
		this.btnCreer		= new JButton("Créer Plateau");
		
		String[]  lstCasting	= new String[this.ctrl.getNbCasting()];
		System.out.println(lstCasting.length);
		
		for (int cpt = 0; cpt < lstCasting.length; cpt++)
		{
			lstCasting[cpt] = this.ctrl.getCasting(cpt);
		}
		
		this.tabCBCasting	= new JCheckBox[lstCasting.length];
		
		String[]  lstRole	= new String[this.ctrl.getNbRole()];
		
		for (int cpt = 0; cpt < lstRole.length; cpt++)
		{
			lstRole[cpt] = this.ctrl.getRole(cpt);
		}
		
		this.tabCBRole		= new JCheckBox[lstRole.length];
		
		this.txtLigne		= new JTextField("", 5);
		this.txtColonne		= new JTextField("", 5);
		this.txtCasting		= new JTextField("", 5);
		this.txtRole		= new JTextField("", 5);
		
		/*-------------------------------------------------*/
		/*         Positionnement des composants           */
		/*-------------------------------------------------*/
		// Partie Taille du Plateau
		this.add(new JLabel("Taille du plateau"));
		
		panelPlateau.add(new JLabel("Nombre de lignes : "));
		panelPlateau.add(this.txtLigne);
		
		panelPlateau.add(new JLabel("Nombre de colonnes : "));
		panelPlateau.add(this.txtColonne);
		
		this.add(panelPlateau);
		
		// Partie Casting
		this.add(new JLabel("Casting"));
		
		panelCasting.add(new JLabel("Nombre de castings : "));
		panelCasting.add(this.txtCasting);
		
		for (int cpt = 0; cpt < lstCasting.length; cpt++)
		{
			this.tabCBCasting[cpt] = new JCheckBox( "" + lstCasting[cpt], false);
			panelLstCasting.add(this.tabCBCasting[cpt]);
		}
		
		panelCasting.add(panelLstCasting);
		this.add(panelCasting);
		
		// Partie Role
		this.add(new JLabel("Rôles"));
		
		panelRole.add(new JLabel("Nombre de rôles : "));
		panelRole.add(this.txtRole);
		
		for (int cpt = 0; cpt < lstRole.length; cpt++)
		{
			this.tabCBRole[cpt] = new JCheckBox( "" + lstRole[cpt], false);
			panelLstRole.add(this.tabCBRole[cpt]);
		}
		
		panelRole.add(panelLstRole);
		this.add(panelRole);
		
		// Partie Joueur
			// A voir + tard
			
		// Partie Bouton
		this.add(btnCreer);
	}
}
