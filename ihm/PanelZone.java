package ihm;

import controleur.Controleur;
import metier.Zone;
import metier.Couleur;

import java.io.File;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.event.*;
import javax.swing.*;

public class PanelZone extends JPanel implements ActionListener
{
    private final JPanel[][] 	tabPanelCases;
    private Controleur 		    ctrl;
    private FrameCreation 		frameCreation;

    private Image 		        imgFond;

    private JButton     		btnSuivant;
    private JButton     		btnPrecedent;
    private JButton     		btnZonePrecedent;
    private JButton     		btnZoneSuivant;
    private JLabel      		labelCouleur;

    private JComboBox<Couleur>  jcbCouleur;

    private Color       		couleurActuelle;

    private int         		indice;
    private int         		tailleLargeur;
    private int         		tailleHauteur;
    private int                 tailleCase;

    private String              nomPlateau;
    private int         		nbZone;
    private boolean     		modeDessin = false;

    private Font                policeBandeau;

    public PanelZone(Controleur ctrl, FrameCreation f, int indice)
    {
        this.ctrl = ctrl;
        this.frameCreation = f;
        this.indice = indice;

        this.imgFond = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");

        this.tailleLargeur  = this.ctrl.getNbColonne();
        this.tailleHauteur  = this.ctrl.getNbLigne();
        this.tailleCase     = this.ctrl.getTailleCase();
        this.nomPlateau     = this.ctrl.getNomPlateau();

        this.nbZone = 0;
        this.couleurActuelle = Couleur.SAUMON.getCouleur();

        this.tabPanelCases = new JPanel[this.tailleLargeur][this.tailleHauteur];

        // On ajoute un espacement vertical de 20px pour aérer sans utiliser de BorderFactory
        this.setLayout(new BorderLayout(0, 10));

        try
        {
            File fichierTitre   = new File("./polices/TitreSaisieInfos/Shrikhand-Regular.ttf");
            Font fontBase       = Font.createFont(Font.TRUETYPE_FONT, fichierTitre);
            java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(fontBase);
            this.policeBandeau  =  fontBase.deriveFont(Font.PLAIN, 16f);
        }
        catch (Exception e) {}

        /* ------------------------------ */
        /* Création des composants        */
        /* ------------------------------ */
        JPanel pnlBtnSuite      = new JPanel(new GridLayout(2,2, 5, 5)); // 5px d'espace entre les boutons
        pnlBtnSuite             .setOpaque(false);

        JPanel pnlBandeau       = new JPanel(new GridLayout(2,1, 5, 5));
        pnlBandeau              .setOpaque(false);

        JPanel pnlTitre         = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlTitre                .setOpaque(false);

        JPanel pnlChoixCouleur  = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlChoixCouleur         .setOpaque(false);

        JPanel pnlConteneur     = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
        pnlConteneur            .setOpaque(false);

        JLabel lblTitre         = new JLabel("Plateau  " + this.tailleLargeur + " × " + this.tailleHauteur + "  :  " + this.nomPlateau);
        lblTitre                .setFont(this.policeBandeau);
        lblTitre                .setOpaque(false);

        JPanel pnlGrille        = new JPanel(new GridLayout(this.tailleLargeur, this.tailleHauteur, 2, 2));
        pnlGrille               .setBackground(new Color(60, 60, 75));

        for (int lig = 0; lig < this.tailleLargeur; lig++)
        {
            for (int col = 0; col < this.tailleHauteur; col++)
            {
                JPanel pnlCellule = new JPanel();
                pnlCellule.setBackground(Color.WHITE);
                pnlCellule.setPreferredSize(new Dimension(this.tailleCase, this.tailleCase));
                pnlCellule.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                final int finalLig = lig, finalCol= col;

                pnlCellule.addMouseListener(new MouseAdapter()
                {
                    public void mousePressed(MouseEvent e)
                    {
                        modeDessin = true;
                        colorierCase(finalLig, finalCol);
                    }

                    public void mouseReleased(MouseEvent e)
                    {
                        modeDessin = false;
                    }

                    public void mouseEntered(MouseEvent e)
                    {
                        if (modeDessin) colorierCase(finalLig, finalCol);

                        if (tabPanelCases[finalLig][finalCol].getBackground().equals(Color.WHITE))
                        {
                            tabPanelCases[finalLig][finalCol].setBackground(Color.LIGHT_GRAY);
                        }
                    }

                    public void mouseExited(MouseEvent e)
                    {
                        if (tabPanelCases[finalLig][finalCol].getBackground().equals(Color.LIGHT_GRAY))
                        {
                            tabPanelCases[finalLig][finalCol].setBackground(Color.WHITE);
                        }
                    }
                });

                this.tabPanelCases[lig][col] = pnlCellule;
                pnlGrille.add(pnlCellule);
            }
        }

        JLabel lblChoix = new JLabel("Couleur :");
        lblChoix.setFont(this.policeBandeau);

        this.jcbCouleur = new JComboBox<>(Couleur.values());
        this.jcbCouleur.setSelectedIndex(12); // Saumon (couleur par défaut)
        this.jcbCouleur.setFont(new Font("SansSerif", Font.PLAIN, 13));
        this.jcbCouleur.setFocusable(false);
        this.jcbCouleur.setOpaque(false);
        this.jcbCouleur.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.jcbCouleur.setPreferredSize(new Dimension(450, 30));

        this.btnSuivant       = new JButton("Suivant >>");
        this.btnPrecedent     = new JButton("<< Précédent");
        this.btnZonePrecedent = new JButton("Zone précédente");
        this.btnZoneSuivant   = new JButton("Nouvelle Zone");

        /* ------------------------------ */
        /* Positionnement des Composants  */
        /* ------------------------------ */
        pnlTitre.add(lblTitre);
        pnlChoixCouleur.add(lblChoix);
        pnlChoixCouleur.add(this.jcbCouleur);

        pnlBandeau.add(pnlTitre);
        pnlBandeau.add(pnlChoixCouleur);

        pnlConteneur.add(pnlGrille);

        pnlBtnSuite.add(this.btnZonePrecedent);
        pnlBtnSuite.add(this.btnZoneSuivant);
        pnlBtnSuite.add(this.btnPrecedent);
        pnlBtnSuite.add(this.btnSuivant);

        this.add(pnlBandeau, BorderLayout.NORTH);
        this.add(pnlConteneur   , BorderLayout.CENTER);
        this.add(pnlBtnSuite    , BorderLayout.SOUTH);

        this.btnSuivant.addActionListener(this);
        this.btnPrecedent.addActionListener(this);
        this.btnZonePrecedent.addActionListener(this);
        this.btnZoneSuivant.addActionListener(this);
        this.jcbCouleur.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent a)
    {
        if (a.getSource() == this.btnSuivant)
        {
            this.frameCreation.setPnl(this.frameCreation.getPnl(this.indice+1));
        }

        if (a.getSource() == this.btnPrecedent)
        {
            this.frameCreation.setPnl(this.frameCreation.getPnl(this.indice-1));
        }

        if (a.getSource() == this.btnZonePrecedent)
        {
            this.ctrl.zonePrecedente();
        }

        if (a.getSource() == this.btnZoneSuivant)
        {
            this.ctrl.nouvelleZone();

            for (Zone zone : this.ctrl.getLstZones())
            {
                System.out.println(zone);
            }
        }

        if (a.getSource() == this.jcbCouleur)
        {
            Couleur choix = (Couleur) this.jcbCouleur.getSelectedItem();
            if (choix != null)
            {
                this.couleurActuelle = choix.getCouleur();
            }
        }
    }

    private void colorierCase(int lig, int col)
    {
        tabPanelCases[lig][col].setBackground(couleurActuelle);
        tabPanelCases[lig][col].repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (this.imgFond != null) {
            g.drawImage(this.imgFond, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}