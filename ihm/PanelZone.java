package ihm;

import controleur.Controleur;

import metier.Zone;
import metier.Couleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

public class PanelZone extends JPanel implements ActionListener
{
	private final JPanel[][] 	cases;
	private Controleur 		    ctrl;
	private FrameCreation 		frameCreation;

    private Image 		        imgFond;

	private JButton     		btnSuivant;
	private JButton     		btnPrecedent;
	private JButton     		btnZonePrecedent;
	private JButton     		btnZoneSuivant;
	private JLabel      		labelCouleur;
	
	private JComboBox<Couleur>  	jcbCouleur; 

	private Color       		couleurActuelle;
	
	private int         		indice;
	private int         		tailleLargeur;
	private int         		tailleHauteur;
	private int         		nbZone;
	private boolean     		modeDessin = false;

	public PanelZone(Controleur ctrl, FrameCreation f, int indice)
	{
		this.ctrl = ctrl;
		this.frameCreation = f;
		this.indice = indice;

        this.imgFond    = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");

		this.tailleLargeur = this.ctrl.getNbColonne();
		this.tailleHauteur = this.ctrl.getNbLigne();
		this.nbZone = 0;
		this.couleurActuelle = Couleur.SAUMON.getCouleur();

		this.cases = new JPanel[this.tailleLargeur][this.tailleHauteur];
		this.setLayout(new BorderLayout());

		/* ------------------------------ */
		/* Création des composants        */
		/* ------------------------------ */
		JPanel pnlBtnSuite = new JPanel(new GridLayout(2,2));

		JPanel bandeau = new JPanel(new GridLayout(2,1));
		bandeau.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

		JPanel wrapper = new JPanel(new GridBagLayout());
		wrapper.setBorder(BorderFactory.createEmptyBorder(16, 16, 8, 16));

		JLabel titre = new JLabel("Plateau " + tailleLargeur + "×" + tailleHauteur);
		titre.setFont(new Font("Monospaced", Font.BOLD, 18));

		JLabel lblNbZone = new JLabel("Zone Numéro : " + this.nbZone);

		JButton btnEffacer = new JButton("Tout effacer");
		btnEffacer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnEffacer.setBackground(new Color(60, 60, 75));
		btnEffacer.setForeground(new Color(200, 200, 220));
		btnEffacer.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(90, 90, 110), 1),
				BorderFactory.createEmptyBorder(5, 12, 5, 12)));
		
		JPanel grille = new JPanel(new GridLayout(this.tailleLargeur, this.tailleHauteur, 1, 1));
		grille.setBackground(new Color(60, 60, 75));

		// int tailleCase;

		
		// tailleCase = Math.max(24, Math.min(64, 560 / this.tailleHauteur));

		for (int i = 0; i < this.tailleLargeur; i++) 
		{
			for (int j = 0; j < this.tailleHauteur; j++) 
			{
				JPanel cellule = new JPanel();
				cellule.setBackground(Color.WHITE);
				cellule.setPreferredSize(new Dimension(50, 50));
				cellule.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 210), 0));
				cellule.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

				final int fi = i, fj = j;

				cellule.addMouseListener(new MouseAdapter() 
				{
					public void mousePressed(MouseEvent e)
					{
						modeDessin = true;
						colorierCase(fi, fj);
					}
					
					public void mouseReleased(MouseEvent e)
					{
						modeDessin = false;
					}
					
					public void mouseEntered(MouseEvent e)
					{
						if (modeDessin) colorierCase(fi, fj);
						if (!cases[fi][fj].getBackground().equals(couleurActuelle))
						{
							cases[fi][fj].setBorder(BorderFactory.createLineBorder(couleurActuelle.darker(), 2));
						}
					}
					
					public void mouseExited(MouseEvent e)
					{
						cases[fi][fj].setBorder(BorderFactory.createLineBorder(new Color(200, 200, 210), 0));
					}
				});

				cases[i][j] = cellule;
				grille.add(cellule);
			}
		}

		JLabel lblChoix = new JLabel("Couleur :");
		lblChoix.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblChoix.setForeground(new Color(170, 170, 200));

		this.jcbCouleur = new JComboBox<>(Couleur.values());
		this.jcbCouleur.setSelectedIndex(12); // Saumon (couleur par défaut)
		this.jcbCouleur.setFont(new Font("SansSerif", Font.PLAIN, 13));
		this.jcbCouleur.setBackground(new Color(40, 40, 55));
		this.jcbCouleur.setForeground(new Color(220, 220, 240));
		this.jcbCouleur.setFocusable(false);
		this.jcbCouleur.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.jcbCouleur.setPreferredSize(new Dimension(160, 30));

		// Aperçu de la couleur choisie
		labelCouleur = new JLabel();
		labelCouleur.setOpaque(true);
		labelCouleur.setBackground(couleurActuelle);
		labelCouleur.setPreferredSize(new Dimension(28, 28));
		labelCouleur.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 130), 2));

		this.btnSuivant       = new JButton("Suivant >>");
		this.btnPrecedent     = new JButton("<< Précédent");
		this.btnZonePrecedent = new JButton("Zone précédente");
		this.btnZoneSuivant   = new JButton("Nouvelle Zone");

		/* ------------------------------ */
		/* Positionnement des Composants  */
		/* ------------------------------ */
		bandeau.add(titre);
		bandeau.add(this.jcbCouleur); 

		wrapper.add(grille);

		pnlBtnSuite.add(this.btnZonePrecedent);
		pnlBtnSuite.add(this.btnZoneSuivant);
		pnlBtnSuite.add(this.btnPrecedent);
		pnlBtnSuite.add(this.btnSuivant);

		this.add(bandeau, BorderLayout.NORTH);
		this.add(wrapper, BorderLayout.CENTER);
		this.add(pnlBtnSuite, BorderLayout.SOUTH);
		
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
				this.labelCouleur.setBackground(this.couleurActuelle);
			}
		}
	}

	private void colorierCase(int i, int j)
	{
		cases[i][j].setBackground(couleurActuelle);
		cases[i][j].repaint();
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
