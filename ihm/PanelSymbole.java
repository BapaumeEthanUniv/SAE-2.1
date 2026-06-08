package ihm;

import metier.Acteur;
import metier.Casting;
import metier.Role;
import metier.Zone;
import controleur.Controleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

public class PanelSymbole extends JPanel implements ActionListener
{
	private Controleur          ctrl;
	private FrameCreation       frame;
	private int                 indice;

	private Image 		imgFond;

	private JPanel[][]          tabPnlCases;

    	private JComboBox<Role>     jcbRole;
    	private JComboBox<Casting>  jcbCasting;

	private JRadioButton        rbRole;
	private JRadioButton        rbCasting;

	private JButton             btnPrecedent;
	private JButton             btnConfirmer;

	public PanelSymbole(Controleur ctrl, FrameCreation f, int indice)
	{
		this.ctrl       = ctrl;
		this.frame      = f;
		this.indice     = indice;

		this.imgFond    = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");

		this.setLayout(new BorderLayout(0, 10));

		/*-------------------------------------------------*/
		/* Création et initialisation des composants       */
		/*-------------------------------------------------*/

		JPanel pnlHaut = new JPanel(new BorderLayout());
		pnlHaut.setOpaque(false);

		JPanel pnlOutils = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		pnlOutils.setOpaque(false);

		this.jcbRole    = new JComboBox<>(this.ctrl.getTabRole());
		this.jcbCasting = new JComboBox<>(this.ctrl.getTabCasting());

		this.rbRole     = new JRadioButton("Placer un Rôle :", true);
		this.rbCasting  = new JRadioButton("Assigner un Casting :", false);
		this.rbRole.setOpaque(false);
		this.rbCasting.setOpaque(false);

		ButtonGroup bgMode = new ButtonGroup();
		bgMode.add(this.rbRole);
		bgMode.add(this.rbCasting);

		JPanel pnlConteneurGrille = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
		pnlConteneurGrille.setOpaque(false);

		int nbLigne = this.ctrl.getNbLigne();
		int nbCol   = this.ctrl.getNbColonne();
		int taille  = this.ctrl.getTailleCase();

		JPanel pnlGrille = new JPanel(new GridLayout(nbLigne, nbCol, 2, 2));
		pnlGrille.setBackground(new Color(60, 60, 75));

		this.tabPnlCases = new JPanel[nbLigne][nbCol];

		for (int lig = 0; lig < nbLigne; lig++)
		{
		    for (int col = 0; col < nbCol; col++)
		    {
			JPanel pnlCellule = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
			
			Zone zoneCase = this.ctrl.getTabZone()[lig][col];
			
			if (zoneCase != null) 	{ pnlCellule.setBackground(zoneCase.getCouleurAwt()); }
			else			{ pnlCellule.setBackground( Color.WHITE ); }

			JLabel lblImage = new JLabel();
			lblImage.setPreferredSize(new Dimension(taille - 6, taille - 6));
			lblImage.setOpaque(false); 
			
			pnlCellule.add(lblImage);

			final int finalLig = lig, finalCol = col;

			MouseAdapter clicSouris = new MouseAdapter()
			{
			    public void mousePressed(MouseEvent e)
			    {
				if (SwingUtilities.isLeftMouseButton(e))
				{
				    if (rbRole.isSelected())
				    {
				        Role roleChoisi = (Role) jcbRole.getSelectedItem();
				        ImageIcon imgRole = creerImgRole(roleChoisi); 

				        boolean placementPossible = ctrl.ajouterActeur(roleChoisi, finalLig, finalCol);
				        
				        if (placementPossible) 
				        {
				            lblImage.setIcon(imgRole);
				            lblImage.setOpaque(false); 
				            pnlCellule.repaint();
				        }
				    }
				    else if (rbCasting.isSelected())
				    {
					    Casting castingChoisi = (Casting) jcbCasting.getSelectedItem();

					    if (lblImage.getIcon() != null) 
					    {
						Color couleurCadre = castingChoisi.getCouleur(); 
						
						if (ctrl.estCastingUtilise(couleurCadre))
						{
						    Acteur acteurActuel = ctrl.getActeur(finalLig, finalCol);
						    
						    if (acteurActuel != null && acteurActuel.estPrincipal() && acteurActuel.getCouleur().equals(couleurCadre)) 
						    {
							return; 
						    }
							
						    return; 
						}
						
						lblImage.setBackground(couleurCadre);
						lblImage.setOpaque(true);
						pnlCellule.repaint();
						
						metier.Acteur acteurCible = ctrl.getActeur(finalLig, finalCol);
						if (acteurCible != null) 
						{
						    ctrl.setPrincipal(couleurCadre, acteurCible);
						}
					    }
					}
				}
				else if (SwingUtilities.isRightMouseButton(e))
				{
				    ctrl.supprimerActeur(finalLig, finalCol);
				    
				    lblImage.setIcon(null);
				    lblImage.setOpaque(false); 
				    pnlCellule.repaint();
				}
			    }
			};

			pnlCellule.addMouseListener(clicSouris);
			lblImage.addMouseListener(clicSouris);

			this.tabPnlCases[lig][col] = pnlCellule;
			pnlGrille.add(pnlCellule);
		    }
		}
		
		JPanel pnlBouton = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
		pnlBouton.setOpaque(false);

		this.btnPrecedent = new JButton("<< Précédent");
		this.btnConfirmer = new JButton("Créer le Plateau");

		/*------------------------------------------*/
		/* Positionnement des composants           */
		/*------------------------------------------*/

		pnlOutils.add(this.rbRole);
		pnlOutils.add(this.jcbRole);
		pnlOutils.add(new JLabel("      "));
		pnlOutils.add(this.rbCasting);
		pnlOutils.add(this.jcbCasting);

		pnlHaut.add(pnlOutils, BorderLayout.CENTER);

		JSeparator separateur = new JSeparator(SwingConstants.HORIZONTAL);
		separateur.setForeground(new Color(150, 150, 150));
		pnlHaut.add(separateur, BorderLayout.SOUTH);

		pnlConteneurGrille.add(pnlGrille);

		pnlBouton.add(this.btnPrecedent);
		pnlBouton.add(this.btnConfirmer);

		this.add(pnlHaut, BorderLayout.NORTH);
		this.add(pnlConteneurGrille, BorderLayout.CENTER);
		this.add(pnlBouton, BorderLayout.SOUTH);

		/* ----------------------------- */
		/* Activation des Composants     */
		/* ----------------------------- */
		this.btnPrecedent.addActionListener(this);
		this.btnConfirmer.addActionListener(this);
	    }

	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == this.btnPrecedent)
		{
		    this.frame.setPnl(this.frame.getPnl(indice - 1));
		}

		if (e.getSource() == this.btnConfirmer)
		{
		    this.ctrl.creerPlateau();
		    System.out.println("Plateau généré avec succès !");

		    this.frame.setPnl(this.frame.getPnl(0));
		}
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
	    
	private ImageIcon creerImgRole(Role role)
	{
		    String chemin = "";
		    int tailleCase;
		    
		    switch (role.name()) 
		    {
			case "CASCADEUR":   
			    chemin = "./images/cascadeur.png"; 
			    break;
			    
			case "EMOTION": 
			    chemin = "./images/emotionnel.png"; 
			    break;
			    
			case "ANTAGONISTE": 
			    chemin = "./images/antagoniste.png"; 
			    break;
			    
			case "FIGURANT":    
			    chemin = "./images/figurant.png"; 
			    break;
			    
			default: 
			    return new ImageIcon(); 
		    }
		    
		    ImageIcon iconeOriginale = new ImageIcon(chemin);

		    tailleCase = this.ctrl.getTailleCase();
		    Image imgRedimensionnee = iconeOriginale.getImage().getScaledInstance(tailleCase-5, tailleCase-5, Image.SCALE_SMOOTH);
		   
		    return new ImageIcon(imgRedimensionnee);
	}
	
	public void majZone()
	{
	    	for (int lig = 0; lig < tabPnlCases.length; lig ++)
			for (int col = 0; col < tabPnlCases[lig].length; col ++)
		        {
		            if (this.ctrl.getTabZone()[lig][col] != null)
		            {
		                tabPnlCases[lig][col].setBackground(this.ctrl.getTabZone()[lig][col].getCouleurAwt());
		        	tabPnlCases[lig][col].repaint();
		            }
		            else
		            {
		                tabPnlCases[lig][col].setBackground(Color.WHITE);
		                tabPnlCases[lig][col].repaint();
		            }
		        }
    	}
}
