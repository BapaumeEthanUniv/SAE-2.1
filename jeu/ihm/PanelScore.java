package ihm;

import controleur.Controleur;

import java.io.File;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

public class PanelScore extends JPanel implements ActionListener
{
    private Controleur 	ctrl;
    private FrameJeu 	frame;
    private int		    indice;

    private Image 		imgFond;

    private JLabel      lblScore;

    private JButton		btnRetour;
    private JButton		btnRejouer;

    // Constructeur
    public PanelScore (Controleur ctrl, FrameJeu f, int indice)
    {
        this.ctrl 		     = ctrl;
        this.frame 		     = f;
        this.indice 		 = indice;

        this.setLayout(new BorderLayout());

        this.imgFond         = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");

        /*-------------------------------*/
        /*   Création des composants     */
        /*-------------------------------*/

        JPanel pnlScore      = new JPanel();
        pnlScore             .setLayout(new GridLayout(2, 1, 10, 10));
        pnlScore             .setOpaque(false);

        JPanel pnlBouton     = new JPanel();
        pnlBouton             .setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pnlBouton             .setOpaque(false);

        this.lblScore        = new JLabel("Bravo ! Vous avez marqué " + this.ctrl.getScoreFinal() + "points !");

        this.btnRetour       = new JButton("<< Retour à l'accueil");
        this.btnRetour       .setOpaque(false);

        this.btnRejouer      = new JButton("Rejouer !");
        this.btnRejouer      .setOpaque(false);

        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
        pnlScore .add(this.lblScore);

        pnlBouton.add(this.btnRetour);
        pnlBouton.add(new JLabel("       "));
        pnlBouton.add(this.btnRejouer  );

        this.add(pnlScore , BorderLayout.CENTER);
        this.add(pnlBouton, BorderLayout.SOUTH );

        /* ----------------------------- */
        /* Activation des Composants     */
        /* ----------------------------- */

        this.btnRetour .addActionListener(this);
        this.btnRejouer.addActionListener(this);

        this.setVisible(true);
    }

    public void actionPerformed ( ActionEvent e )
    {
        if ( e.getSource() == this.btnRetour )
        {
            this.frame.setPnl(this.frame.getPnl(0));
        }

        if ( e.getSource() == this.btnRejouer   )
        {
            this.ctrl.initPlateau(this.frame.getFichierPlateau());

            this.frame.creerPanelJeu();
            this.ctrl.creerFrameCarte();

            this.frame.setPnl(this.frame.getPnl(this.indice - 1));
        }
    }

    // Chargement de l'image de fin
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (this.imgFond != null)
        {
            g.drawImage(this.imgFond, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}


