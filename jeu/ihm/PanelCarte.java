package ihm;

import controleur.Controleur;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import metier.Carte;
import metier.Role;

public class PanelCarte extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private ImageIcon  imgPersonnage;
    private JButton    btnChangerImage;
    private Image      imageRedimensionnee;
    private ImageIcon  iconeFinale;
    private JLabel     lblImage;
	private JLabel     lblScore;
	private JLabel     lblTour;
    	
    private Image 	   imgFond;
    	
    private int        nomImage;
    private int        largeurImage;
    private int        hauteurImage;

	private Font       policeBandeau;

    public PanelCarte(Controleur ctrl)
    {
		this.ctrl = ctrl;

		
		this.setLayout(new BorderLayout());

		/*-------------------------------*/
		/*   Création des composants     */
		/*-------------------------------*/
		
		JPanel panelAfficheCarte = new JPanel(new GridLayout());
		panelAfficheCarte.setOpaque(false);
		
		JPanel panelAfficheScore = new JPanel(new GridLayout(1, 2));
		panelAfficheScore.setOpaque(false);
		
		this.nomImage            = (int)(Math.random() * 10) + 1;
		this.largeurImage        = 250;
		this.hauteurImage        = 350;
		
		this.piocheAleatoire();
		this.lblImage = new JLabel(this.iconeFinale);
		
		this.imgFond             = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");

		this.btnChangerImage     = new JButton("Piocher Carte");

		this.lblTour = new JLabel("Tour : ");
		this.lblScore = new JLabel("Score joueur : ");

		try
        	{
		    File fichierTitre              = new File("./polices/TitreSaisieInfos/Shrikhand-Regular.ttf");
		    Font policeLbl                 = Font.createFont(Font.TRUETYPE_FONT, fichierTitre);   // crée la police
		    
		    java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(policeLbl);   // enregistre la police dans le système Java

		    this.policeBandeau            =  policeLbl.deriveFont(Font.BOLD, 18f);                // police du bandeau 	modifié en Gras + taille 18
 
			lblTour.setFont(policeBandeau);
			lblScore.setFont(policeBandeau);                                                    // police du bandeau changé
		}
		// Si fichier non trouvé, la police est en SansSerif Gras par défaut
		catch (Exception e)
		{
		    lblTour  .setFont(new Font("SansSerif", Font.BOLD, 18));
			lblScore .setFont(new Font("SansSerif", Font.BOLD, 18));
		}
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		panelAfficheCarte.add(this.lblImage);

		panelAfficheScore.add(this.lblTour);
		panelAfficheScore.add(this.lblScore);

		this.add(this.btnChangerImage, BorderLayout.NORTH);
		this.add(panelAfficheCarte   , BorderLayout.CENTER);
		this.add(panelAfficheScore   , BorderLayout.SOUTH);

		/* ----------------------------- */
		/* Activation des Composants     */
		/* ----------------------------- */

		this.btnChangerImage.addActionListener(this);
    	}

    	public void actionPerformed(ActionEvent e)
   	{
		if (e.getSource() == this.btnChangerImage)
		{	
			this.piocheAleatoire();
			this.lblImage.setIcon(this.iconeFinale);
		}
    	}
    	
    	// Méthode permettant de simuler une pioche aléatoire et d'afficher les cartes de façon optimisé
    	private void piocheAleatoire ()
    	{
			Carte carteAffiche = this.ctrl.getCartePioche();
			System.out.println(carteAffiche.getRole());
			if(!carteAffiche.estFonce())
			{
				switch (carteAffiche.getRole()) 
				{
					case Role.CASCADEUR   -> this.nomImage = 5;
					case Role.EMOTION     -> this.nomImage = 7;
					case Role.FIGURANT    -> this.nomImage = 9;
					case Role.ANTAGONISTE -> this.nomImage = 3;
					default               -> this.nomImage = 1;
				}
			}
			else
			{
				switch (carteAffiche.getRole()) 
				{
					case Role.CASCADEUR   -> this.nomImage = 6;
					case Role.EMOTION     -> this.nomImage = 8;
					case Role.FIGURANT    -> this.nomImage = 10;
					case Role.ANTAGONISTE -> this.nomImage = 4;
					default               -> this.nomImage = 2;
				}
			}

    		this.imgPersonnage       = new ImageIcon("./images/" + this.nomImage + ".png");
			this.imageRedimensionnee = this.imgPersonnage.getImage()
		        .getScaledInstance(this.largeurImage, this.hauteurImage, Image.SCALE_SMOOTH);    
			this.iconeFinale         = new ImageIcon(this.imageRedimensionnee);
    	}
    	
    	// Méthode permettant de changer le fond du panel par imgFond + dessiner les liens sur le plateau
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		if (this.imgFond != null) 
		{
			g.drawImage(this.imgFond, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}
