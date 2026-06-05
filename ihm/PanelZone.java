package ihm;

import controleur.Controleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.w3c.dom.events.MouseEvent;

public class PanelZone extends JPanel implements ActionListener
{
	private final JPanel[][] cases;

	private Controleur 	ctrl;
	
	private FrameSaisie frameSaisie;

	private JButton     btnSuivant;
	private JButton     btnPrecedent ;
	private JButton     btnZonePrecedent;
	private JButton     btnZoneSuivant;
	private JLabel      labelCouleur;

	private Color       couleurActuelle = new Color(109, 7 ,  26);

	private int         indice;
	private int         tailleLargeur;
	private int         tailleHauteur;
	private boolean     modeDessin = false;

	private static final Map<String, Color> PALETTE = new LinkedHashMap<>();
    static {
        PALETTE.put("Effacer",      Color.WHITE);
        PALETTE.put("Rouge",        new Color(109, 7 ,  26));
        PALETTE.put("Rouge Colore", Color.RED);
        PALETTE.put("Orange",       new Color(235, 130, 30));
        PALETTE.put("Jaune",        new Color(240, 200, 20));
        PALETTE.put("Vert",         new Color(50,  180, 80));
        PALETTE.put("Bleu",         new Color(40,  110, 220));
        PALETTE.put("Violet",       new Color(140, 60,  200));
        PALETTE.put("Mauve",        Color.MAGENTA);
        PALETTE.put("Rose",         new Color(230, 100, 160));
        PALETTE.put("Marron",       new Color(140, 80,  30));
        PALETTE.put("Noir",         Color.BLACK);
        PALETTE.put("Gris",         new Color(150, 150, 150));
        PALETTE.put("Cyan",         new Color(30,  200, 220));
        PALETTE.put("Saumon",       new Color(240, 150, 120));
        PALETTE.put("Vert citron",  new Color(140, 210, 40));
    }
	
	public PanelZone(Controleur ctrl, FrameSaisie f, int indice, int tailleLargeur, int tailleHauteur)
	{
		this.ctrl = ctrl;
		this.frameSaisie = f;
		this.indice = indice;
		this.tailleLargeur = tailleLargeur;
		this.tailleHauteur = tailleHauteur;

		this.cases = new JPanel[this.tailleLargeur][this.tailleHauteur];

		this.setLayout(new BorderLayout());

		/* ------------------------------ */
		/* Création des composants        */
		/* ------------------------------ */

		JPanel bandeau = new JPanel(new BorderLayout());
        bandeau.setBackground(new Color(20, 20, 28));
        bandeau.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

		JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(28, 28, 35));
        wrapper.setBorder(BorderFactory.createEmptyBorder(16, 16, 8, 16));

		JLabel titre = new JLabel("✦ GRILLE " + tailleLargeur + "×" + tailleHauteur);
        titre.setFont(new Font("Monospaced", Font.BOLD, 18));
        titre.setForeground(new Color(210, 210, 240));

        JButton btnEffacer = new JButton("Tout effacer");
        btnEffacer.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btnEffacer.setBackground(new Color(60, 60, 75));
        btnEffacer.setForeground(new Color(200, 200, 220));
        btnEffacer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(90, 90, 110), 1),
                BorderFactory.createEmptyBorder(5, 12, 5, 12)));
		
		JPanel grille = new JPanel(new GridLayout(this.tailleLargeur, this.tailleHauteur, 1, 1));
        grille.setBackground(new Color(60, 60, 75)); // couleur des séparateurs

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

                // Clic simple
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
                        // Survol : légère teinte
                        if (!cases[fi][fj].getBackground().equals(couleurActuelle))
						{
                            cases[fi][fj].setBorder(BorderFactory.createLineBorder(
                                    couleurActuelle.darker(), 2));
                        }
                    }
                    public void mouseExited(MouseEvent e)
					{
                        cases[fi][fj].setBorder(BorderFactory.createLineBorder(
                                new Color(200, 200, 210), 0));
                    }
                });

				cases[i][j] = cellule;
                grille.add(cellule);
            }
		}

		JLabel lblChoix = new JLabel("Couleur :");
        lblChoix.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblChoix.setForeground(new Color(170, 170, 200));

        // Menu déroulant
        String[] noms = PALETTE.keySet().toArray(new String[0]);
        JComboBox<String> combo = new JComboBox<>(noms);
        combo.setSelectedIndex(1); // Rouge par défaut
        combo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        combo.setBackground(new Color(40, 40, 55));
        combo.setForeground(new Color(220, 220, 240));
        combo.setFocusable(false);
        combo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        combo.setPreferredSize(new Dimension(160, 30));

        // Aperçu de la couleur choisie
        labelCouleur = new JLabel();
        labelCouleur.setOpaque(true);
        labelCouleur.setBackground(couleurActuelle);
        labelCouleur.setPreferredSize(new Dimension(28, 28));
        labelCouleur.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 130), 2));

        // Listener combo
        combo.addActionListener(e -> {
            String choix = (String) combo.getSelectedItem();
            if (choix != null) {
                couleurActuelle = PALETTE.get(choix);
                labelCouleur.setBackground(couleurActuelle);
            }
        });

		this.btnSuivant       = new JButton("Suivant"        );
		this.btnPrecedent     = new JButton("Précédent "     );
		this.btnZonePrecedent = new JButton("Zone précédente");
		this.btnZoneSuivant   = new JButton("Zone suivante"  );

		/* ------------------------------ */
		/* Positionnement des Composants  */
		/* ------------------------------ */

		
		bandeau.add(titre);
		bandeau.add(combo);

		wrapper.add(grille);

		this.add(bandeau, BorderLayout.NORTH);
		this.add(wrapper, BorderLayout.CENTER);
		this.add(this.btnSuivant, BorderLayout.SOUTH);
		this.add(this.btnPrecedent, BorderLayout.SOUTH);
		this.add(this.btnZonePrecedent, BorderLayout.SOUTH);
		this.add(this.btnZoneSuivant, BorderLayout.SOUTH);
		this.btnSuivant.addActionListener(this);
		this.btnPrecedent .addActionListener(this);
		this.btnZonePrecedent.addActionListener(this);
		this.btnZoneSuivant.addActionListener(this);
	}

	public void actionPerformed(ActionEvent a)
	{
		if (a.getSource() == this.btnSuivant)
		{
			this.frameSaisie.setPnl(this.frameSaisie.getPnl(this.indice+1));
		}

		if (a.getSource() == this.btnPrecedent)
		{
			this.frameSaisie.setPnl(this.frameSaisie.getPnl(this.indice-1));
		}
	}

	private void colorierCase(int i, int j)
    {
        cases[i][j].setBackground(couleurActuelle);
        cases[i][j].repaint();
    }
}
