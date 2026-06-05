package ihm;

import controleur.Controleur;
import metier.Couleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;

public class PanelZone extends JPanel implements ActionListener
{
	private final JPanel[][] 	cases;
	private Controleur 		ctrl;
	private FrameCreation 		frameCreation;

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
		this.tailleLargeur = this.ctrl.getNbColonne();
		this.tailleHauteur = this.ctrl.getNbLigne();
		this.nbZone = 0;
		this.couleurActuelle = Couleur.BORDEAUX.getCouleur(); 

		this.cases = new JPanel[this.tailleLargeur][this.tailleHauteur];
		this.setLayout(new BorderLayout());

		/* ------------------------------ */
		/* Création des composants        */
		/* ------------------------------ */
		JPanel pnlBtnSuite = new JPanel(new FlowLayout());

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

		int tailleCase = Math.max(24, Math.min(64, 560 / ((this.tailleLargeur*this.tailleHauteur)/2)));

		for (int i = 0; i < this.tailleLargeur; i++) 
		{
			for (int j = 0; j < this.tailleLargeur; j++) 
			{
				JPanel cellule = new JPanel();
				cellule.setBackground(Color.WHITE);
				cellule.setPreferredSize(new Dimension(tailleCase, tailleCase));
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
		this.jcbCouleur.setSelectedIndex(3); // index 3 = Bordeaux (couleur par défaut)
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

		this.btnSuivant       = new JButton("Suivant");
		this.btnPrecedent     = new JButton("Précédent");
		this.btnZonePrecedent = new JButton("Zone précédente");
		this.btnZoneSuivant   = new JButton("Nouvelle Zone");

		/* ------------------------------ */
		/* Positionnement des Composants  */
		/* ------------------------------ */
		bandeau.add(titre);
		bandeau.add(this.jcbCouleur); 

		wrapper.add(grille);

		pnlBtnSuite.add(this.btnSuivant);
		pnlBtnSuite.add(this.btnPrecedent);
		pnlBtnSuite.add(this.btnZonePrecedent);
		pnlBtnSuite.add(this.btnZoneSuivant);

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
}
