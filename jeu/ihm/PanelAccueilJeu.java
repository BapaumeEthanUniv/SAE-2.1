package ihm;

import controleur.Controleur;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class PanelAccueilJeu extends JPanel implements ActionListener
{
	private Controleur 	ctrl;
	private FrameJeu 	frame;
	private int		    indice;
	
	private Image 		imgFond;
    private File        fichierPlateau;
	
	private JButton		btnCharger;
	private JButton		btnJouer;
	
	// Constructeur
	public PanelAccueilJeu (Controleur ctrl, FrameJeu f, int indice)
	{
		this.ctrl 		     = ctrl;
		this.frame 		     = f;
		this.indice 		 = indice;
	
		this.setLayout(new GridLayout(12,1));
		
		this.imgFond         = Toolkit.getDefaultToolkit().getImage("./images/img-accueil-jeu.png");
		this.fichierPlateau  = new File("../creation/Plateau/");

		/*-------------------------------*/
		/*   Création des composants     */
		/*-------------------------------*/
		
		JPanel pnlBtnCharger = new JPanel();
		pnlBtnCharger        .setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		pnlBtnCharger        .setOpaque(false);
		
		JPanel pnlBtnJouer   = new JPanel();
		pnlBtnJouer          .setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		pnlBtnJouer          .setOpaque(false);
		
		this.btnCharger = new JButton("Charger Niveau");
		this.btnCharger .setOpaque(false);
		
		this.btnJouer   = new JButton("Jouer !");
		this.btnJouer   .setOpaque(false);
		this.btnJouer   .setEnabled(false);
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		pnlBtnCharger.add(this.btnCharger);
		pnlBtnJouer  .add(this.btnJouer  );
		this.add(pnlBtnCharger);
		this.add(pnlBtnJouer  );
		
		/* ----------------------------- */
		/* Activation des Composants     */
		/* ----------------------------- */
		
		this.btnCharger.addActionListener(this);
		this.btnJouer  .addActionListener(this);
		
		this.setVisible(true);
	}

	public void actionPerformed ( ActionEvent e )
	{
		if ( e.getSource() == this.btnCharger )
		{
			JFileChooser fcRepertoire = new JFileChooser("../creation/Plateau/"); 
			fcRepertoire.setFileSelectionMode(fcRepertoire.DIRECTORIES_ONLY); // création du selectionneur de dossier
			
			int res = fcRepertoire.showOpenDialog(this); 
			
			if (res == JFileChooser.APPROVE_OPTION) // lecture des fichiers du dossier choisie
			{
				this.fichierPlateau = fcRepertoire.getSelectedFile();
				
				this.ctrl.initPlateau(fichierPlateau);
				
				this.frame.creerPanelJeu();
				
				this.btnJouer.setEnabled(true); // activation du bouton jouer
			}
		}
		
		if ( e.getSource() == this.btnJouer   )
		{
			this.ctrl.creerFrameCarte();
			this.frame.setPnl(this.frame.getPnl(this.indice + 1));
            this.btnJouer.setEnabled(false);
		}
	}

    public File getFichierPlateau() {return this.fichierPlateau; }
	
	// Chargement de l'image de l'accueil
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		if (this.imgFond != null) 
		{
			g.drawImage(this.imgFond, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}

