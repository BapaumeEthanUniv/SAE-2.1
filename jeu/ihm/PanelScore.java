package ihm;

import controleur.Controleur;

import java.io.File;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PanelScore extends JPanel implements ActionListener
{
    private Controleur 	ctrl;
    private FrameJeu 	frame;
    private int		indice;

    private Image       imgFond;     // Permet de stocker une img de fond pour l'appliquer au panel

    private JLabel      lblScore;

    private Font        police;      // Permet d'appliquer une police spéciale aux JLabel

    private JButton	btnRetour;
    private JButton	btnRejouer;
    private JButton	btnQuitter;

    // Constructeur
    public PanelScore (Controleur ctrl, FrameJeu f, int indice)
    {
        this.ctrl 		     = ctrl;
        this.frame 		     = f;
        this.indice 		 = indice;

        this.setLayout(new BorderLayout());

        this.imgFond         = Toolkit.getDefaultToolkit().getImage("./images/img-score.png");

        /*-------------------------------*/
        /*   Création des composants     */
        /*-------------------------------*/

        // Panel qui contiendra le JLabel du score
        JPanel pnlScore      = new JPanel();
        pnlScore             .setLayout(new GridLayout(2, 1, 30, 30));
        pnlScore             .setOpaque(false);

        // Panel qui contiendra les deux boutons
        JPanel pnlBouton     = new JPanel();
        pnlBouton            .setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
        pnlBouton            .setOpaque(false);

        this.lblScore        = new JLabel("Bravo ! Vous avez marqué " + this.ctrl.getScoreFinal() + "points !", JLabel.CENTER);

        // Essai pour initialiser les polices en prenant la police téléchargée dans le dossier polices/
        try
        {
            File fichierTitre = new File("./polices/TitreSaisieInfos/Shrikhand-Regular.ttf");
            Font policeLbl    = Font.createFont(Font.TRUETYPE_FONT, fichierTitre);   // crée la police

            java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(policeLbl);   // enregistre la police dans le système Java

            this.police       = policeLbl.deriveFont(Font.BOLD, 18f);                // police du bandeau 	modifié en Gras + taille 18

            this.lblScore.setFont(this.police); // police changé
        }
        // Si fichier non trouvé, la police est en SansSerif Gras par défaut
        catch (Exception e)
        {
            this.lblScore.setFont(new Font("SansSerif", Font.BOLD, 18));
        }

        this.btnRetour       = new JButton("<< Retour à l'accueil");
        this.btnRetour       .setOpaque(false);

        this.btnRejouer      = new JButton("Rejouer !");
        this.btnRejouer      .setOpaque(false);
        
        this.btnQuitter      = new JButton("Quitter le jeu");
        this.btnQuitter      .setOpaque(false);

        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
        pnlScore .add(this.lblScore);

        pnlBouton.add(this.btnRetour);
        pnlBouton.add(new JLabel("       "));
        pnlBouton.add(this.btnRejouer  );
        pnlBouton.add(new JLabel("       "));
        pnlBouton.add(this.btnQuitter  );

        this.add(pnlScore , BorderLayout.CENTER);
        this.add(pnlBouton, BorderLayout.SOUTH );

        /* ----------------------------- */
        /* Activation des Composants     */
        /* ----------------------------- */

        this.btnRetour .addActionListener(this);
        this.btnRejouer.addActionListener(this);
        this.btnQuitter.addActionListener(this);

        this.setVisible(true);
    }

    public void actionPerformed ( ActionEvent e )
    {
        if ( e.getSource() == this.btnRetour    )
        {
            this.frame.setPnl(this.frame.getPnl(0));                // On retourne au premier panel (PanelAccueilJeu)
        }

        if ( e.getSource() == this.btnRejouer   )
        {
            this.ctrl .initPlateau(this.frame.getFichierPlateau());       // On réinitialise le plateau

            this.frame.creerPanelJeu();                                   // On recrée le panel du Jeu
            this.ctrl .creerFrameCarte();                                 // On recrée le panel de Pioche

            this.frame.setPnl(this.frame.getPnl(this.indice - 1));  // On revient au panel précédent (PanelJeu)
        }
        
        if ( e.getSource() == this.btnQuitter   )
        {
        	System.exit(0);
        }
    }


    /* --------------------------*/
    /*    Méthodes utilitaires   */
    /* --------------------------*/

    // Méthode permettant de dessiner l'image de fond du panel
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (this.imgFond != null)
        {
            g.drawImage(this.imgFond, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}


