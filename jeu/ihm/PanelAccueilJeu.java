package ihm;

import controleur.Controleur;

import java.io.File;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

public class PanelAccueilJeu extends JPanel implements ActionListener
{
	private Controleur 	ctrl;
	private FrameJeu 	frame;
	private int		    indice;          // Permet de savoir l'indice dans lequel le Panel est rangé dans le tableau de JPanel de FrameJeu
	
	private Image 		imgFond;         // Permet de stocker une img de fond pour l'appliquer au panel
    private File        fichierPlateau;  // Permet d'avoir en main le répertoire du plateau
	
	private JButton		btnCharger;
	private JButton		btnJouer;
	private JButton		btnQuitter;
	
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

        // Panel qui contiendra le bouton Charger Niveau
		JPanel pnlBtnCharger = new JPanel();
		pnlBtnCharger        .setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		pnlBtnCharger        .setOpaque(false);

        // Panel qui contiendra le bouton Jouer
		JPanel pnlBtnJouer   = new JPanel();
		pnlBtnJouer          .setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		pnlBtnJouer          .setOpaque(false);
		
		JPanel pnlBtnQuitter = new JPanel();
		pnlBtnQuitter        .setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		pnlBtnQuitter        .setOpaque(false);
		
		
		this.btnCharger = new JButton("Charger Niveau");
		this.btnCharger .setOpaque(false);
		
		this.btnJouer   = new JButton("Jouer");
		this.btnJouer   .setOpaque(false);
		this.btnJouer   .setEnabled(false);
		
		this.btnQuitter = new JButton("Quitter le Jeu");
		this.btnQuitter .setOpaque(false);
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		this.add(new JLabel(""));  // Permet de
		this.add(new JLabel(""));  // centrer
		this.add(new JLabel(""));  // les boutons
		this.add(new JLabel(""));  // par rapport à l'image
		this.add(new JLabel(""));  // et à la fenêtre

		pnlBtnCharger.add(this.btnCharger);
		pnlBtnJouer  .add(this.btnJouer  );
		pnlBtnQuitter.add(this.btnQuitter);

		this.add(pnlBtnCharger);
		this.add(pnlBtnJouer  );
		this.add(pnlBtnQuitter);
		
		/* ----------------------------- */
		/* Activation des Composants     */
		/* ----------------------------- */
		
		this.btnCharger.addActionListener(this);
		this.btnJouer  .addActionListener(this);
		this.btnQuitter.addActionListener(this);
		
		this.setVisible(true);
	}

	public void actionPerformed ( ActionEvent e )
	{
		if ( e.getSource() == this.btnCharger )
		{
			JFileChooser fcRepertoire = new JFileChooser("../creation/Plateau/"); 
			fcRepertoire.setFileSelectionMode(fcRepertoire.DIRECTORIES_ONLY); // Création du sélectionneur de dossier
			
			int res = fcRepertoire.showOpenDialog(this);

            // Si on appuie sur Ouvrir en ayant choisi son répertoire
			if (res == JFileChooser.APPROVE_OPTION)
			{
				this.fichierPlateau = fcRepertoire.getSelectedFile();    // L'attribut stocke le répertoire
				
				this.ctrl.initPlateau(fichierPlateau);                   // On initialise le plateau
				
				this.frame.creerPanelJeu();                              // On crée le PanelJeu
				
				this.btnJouer.setEnabled(true);                          // On active le bouton pour lancer le Jeu
			}
		}
		
		if ( e.getSource() == this.btnJouer   )
		{
			this.ctrl.creerFrameCarte();                                  // On crée le PanelCarte
			this.frame.setPnl(this.frame.getPnl(this.indice + 1));        // On change le panelActif pour afficher PanelJeu
            this.btnJouer.setEnabled(false);                              // On désactive le bouton Jouer après avoir appuyé
		}
		
		if ( e.getSource() == this.btnQuitter )
		{
			System.exit(0);
		}
	}

    // Getter permettant au métier de récupérer le fichier séléctionné
    public File getFichierPlateau() {return this.fichierPlateau; }

    /* --------------------------*/
    /*    Méthodes utilitaires   */
    /* --------------------------*/

	// Méthode permettant de dessiner l'image de l'accueil
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		if (this.imgFond != null) 
		{
			g.drawImage(this.imgFond, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}

