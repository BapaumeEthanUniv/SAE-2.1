package ihm;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.*;

import controleur.Controleur;

public class PanelSymbole extends JPanel implements ActionListener
{
	private Controleur    ctrl;
	private FrameCreation frame;
	private int           indice;

    private Image 		        imgFond;

	private JPanel pnlHaut;
	private JPanel pnlCombo;

	private JComboBox<String> comboRole;
	private JComboBox<String> comboCasting;
	private JLabel            lblMessage;

	private JPanel grilleCentre; //a modifier si besoin, je sais pas comment la grille a été faite

	private JPanel        pnlBouton;
	private JButton       btnPrecedent;
	private JButton       btnConfirmer;
	public PanelSymbole(Controleur ctrl, FrameCreation f, int indice)
	{
        this.setLayout( new BorderLayout());

        this.ctrl 	= ctrl	;
        this.frame      = f ;
        this.indice 	= indice;
        this.imgFond    = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");
        /*-------------------------------------------------*/
		/*   Création et initialisation des composants     */
		/*-------------------------------------------------*/

		this.pnlHaut = new JPanel(new GridLayout(2,1));
		this.pnlCombo = new JPanel();

		this.comboCasting = new JComboBox<String>();
		this.comboRole    = new JComboBox<String>();

		this.lblMessage = new JLabel("bha j'ai pas fait les action encore",JLabel.CENTER);

		this.grilleCentre = new JPanel(); // pareil bah c'est la place de la grille donc a modifier si besoin

		this.pnlBouton = new JPanel();
        this.pnlBouton.setLayout(new FlowLayout());

		this.btnPrecedent = new JButton("<< Précédent");
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

		this.pnlBouton.add(this.btnPrecedent, FlowLayout.LEFT);
		this.pnlBouton.add(this.btnConfirmer, FlowLayout.CENTER);
		this.add(this.pnlBouton, BorderLayout.SOUTH);
	


		/* ----------------------------- */
		/* Activation des Composants     */
		/* ----------------------------- */
		this.btnPrecedent.addActionListener(this);

        this.setVisible(true);
	}

    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource() == this.btnPrecedent)
        {
            this.frame.setPnl(this.frame.getPnl(indice-1));
        }
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
