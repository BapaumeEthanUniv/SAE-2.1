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

	private ImageIcon  imgPersonnage;       // Image de la Carte
   	private JButton    btnPasser;
    private Image      imgRedimensionnee;   // Permet de redimensionner la Carte avant de l'afficher
   	private ImageIcon  iconeFinale;         // Permet de stocker l'image de la Carte finale
    private JLabel     lblImage;            // Permet d'afficher iconeFinale (image de la carte)
    	
    private Image 	   imgFond;             // Image de fond
    	
   	private int        nomImage;            // Stocke le nom de l'image de la Carte (1 à 10)
    private int        largeurImage;        // Stocke la largeur de l'image
    private int        hauteurImage;        // Stocke la hauteur de l'image

	private Font       police;              // Permet d'appliquer une police spéciale aux JLabel

    // Constructeur
    public PanelCarte(Controleur ctrl)
    {
		this.ctrl = ctrl;
		
		this.setLayout(new BorderLayout());

		/*-------------------------------*/
		/*   Création des composants     */
		/*-------------------------------*/

        // Panel qui contiendra l'image de la carte (dans lblImage)
		JPanel panelAfficheCarte = new JPanel(new GridLayout());
		panelAfficheCarte.setOpaque(false);
		
		this.nomImage            = (int)(Math.random() * 10) + 1;
		this.largeurImage        = 250;
		this.hauteurImage        = 350;

        this.imgFond             = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");

        this.btnPasser           = new JButton("Passer le Tour");

        this.lblImage            = new JLabel("", JLabel.CENTER);
		this.changerCarte();

        // Essai pour initialiser les polices en prenant la police téléchargée dans le dossier polices/
		try
        {
            File fichierTitre   = new File("./polices/TitreSaisieInfos/Shrikhand-Regular.ttf");
            Font policeLbl      = Font.createFont(Font.TRUETYPE_FONT, fichierTitre);              // Crée la police
		    
            java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(policeLbl);   // Enregistre la police dans le système Java

            this.police         =  policeLbl.deriveFont(Font.BOLD, 18f);                     // Police du bandeau modifié en Gras + taille 18

            this.lblImage.setFont(police);  // Police du JLabel changé
		}
		// Si fichier non trouvé, la police est en SansSerif Gras par défaut
		catch (Exception e)
		{
            this.lblImage.setFont(new Font("SansSerif", Font.BOLD, 18));
		}
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		panelAfficheCarte.add(this.lblImage);

		this.add(this.btnPasser   , BorderLayout.NORTH);
		this.add(panelAfficheCarte, BorderLayout.CENTER);

		/* ----------------------------- */
		/* Activation des Composants     */
		/* ----------------------------- */

		this.btnPasser.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
   	{
		if (e.getSource() == this.btnPasser)
		{	
			this.changerCarte();
			this.ctrl.majLblCasting();
		}
    }

    /* --------------------------*/
    /*    Méthodes utilitaires   */
    /* --------------------------*/
    	
    // Méthode permettant de simuler une pioche aléatoire et d'afficher les cartes de façon optimisé
    public void changerCarte ()
    {
		this.ctrl.piocherCarte();

        // Si le bouton Passer est verrouillé, on ne pioche plus
        if (!this.btnPasser.isEnabled()) return;
		
		Carte carteAffiche = this.ctrl.getCartePioche();

        // Condition pour savoir l'image de la carte à afficher selon le Role de la Carte pioché
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

        // Image stockée dans attribut imgPersonnage
    	this.imgPersonnage       = new ImageIcon("./images/" + this.nomImage + ".png");

        // Redimension de l'image selon les attributs largeurImage et hauteurImage --> nouvelle Carte stockée dans l'attribut
		this.imgRedimensionnee = this.imgPersonnage.getImage()
		    .getScaledInstance(this.largeurImage, this.hauteurImage, Image.SCALE_SMOOTH);

        // Image finale stockée dans l'attribut iconeFinale
		this.iconeFinale         = new ImageIcon(this.imgRedimensionnee);

        // Le JLabel ajoute iconeFinale
		this.lblImage.setIcon(this.iconeFinale);
    }

    // Méthode permettant d'annoncer la fin de la partie
    public void finDePartie()
    {
        this.btnPasser.setEnabled(false);             // Bouton Passer verrouillé
        this.lblImage.setIcon(null);                  // Image du JLabel effacé
        this.lblImage.setText("Fin de la Partie !");  // Affichage du message dans JLabel
    }
    	
    // Méthode permettant de dessiner le fond du panel
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		if (this.imgFond != null) 
		{
			g.drawImage(this.imgFond, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}
