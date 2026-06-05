package ihm;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import controleur.Controleur;

public class PanelSymbole extends JPanel
{
	private Controleur    ctrl;
	private FrameCreation frame;
	private int           indice;

	private JPanel pnlHaut;
	private JPanel pnlCombo;

	private JComboBox<String> comboRole;
	private JComboBox<String> comboCasting;
	private JLabel            lblMessage;

	private JPanel grilleCentre; //a modifier si besoin, je sais pas comment la grille a été faite

	private JPanel pnlBas;
	private JButton       btnPrecedent;
	private JButton       btnConfirmer;
	public PanelSymbole(Controleur ctrl, FrameCreation f, int indice)
	{

		/*-------------------------------------------------*/
		/*   Création et initialisation des composants     */
		/*-------------------------------------------------*/
		this.ctrl 	= ctrl	;
		this.frame      = f ;
		this.indice 	= indice;
		this.setLayout( new BorderLayout());

		this.pnlHaut = new JPanel(new GridLayout(2,1));
		this.pnlCombo = new JPanel();

		this.comboCasting = new JComboBox<String>();
		this.comboRole    = new JComboBox<String>();

		this.lblMessage = new JLabel("bha j'ai pas fait les action encore",JLabel.CENTER);

		this.grilleCentre = new JPanel(); // pareil bah c'est la place de la grille donc a modifier si besoin

		this.pnlBas = new JPanel();

		this.btnPrecedent = new JButton("Précédent");
		this.btnConfirmer = new JButton("Confirmer");

		/*------------------------------------------*/
		/*  Positionnement des composants           */
		/*------------------------------------------*/
		this.pnlCombo.add(new JLabel("Role : "));
		this.pnlCombo.add(this.comboRole);
		this.pnlCombo.add(new JLabel("Casting : "));
		this.pnlCombo.add(this.comboCasting);

		this.pnlHaut.add(this.pnlCombo);
		this.pnlHaut.add(this.lblMessage);

		this.add(this.pnlHaut,BorderLayout.NORTH);
		this.add(this.grilleCentre); //toujours pareil HIHI j'aime le jafun

		this.pnlBas.setLayout(new GridLayout(1,2));
		this.pnlBas.add(this.btnPrecedent);
		this.pnlBas.add(this.btnConfirmer);
		this.add(pnlBas, BorderLayout.SOUTH);
	


		/* ----------------------------- */
		/* Activation des Composants     */
		/* ----------------------------- */
		this.setVisible(true);
	}
}
