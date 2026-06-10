package ihm;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

public class PanelCarte extends JPanel implements ActionListener
{
	private ImageIcon imgPersonnage;
    	private JButton   btnChangerImage;
    	private Image     imageRedimensionnee;
    	private ImageIcon iconeFinale;
    	private JLabel    lblImage;
    	
    	private Image 	  imgFond;
    	
    	private int       nomImage;
    	private int       largeurImage;
    	private int       hauteurImage;

    	public PanelCarte()
    	{
		this.setLayout(new BorderLayout());

		/*-------------------------------*/
		/*   Création des composants     */
		/*-------------------------------*/
		
		JPanel panelAfficheCarte = new JPanel(new GridLayout());
		panelAfficheCarte.setOpaque(false);
		
		JPanel panelAfficheScore = new JPanel(new GridLayout(2, 2));
		panelAfficheScore.setOpaque(false);
		
		this.nomImage            = (int)(Math.random() * 10) + 1;
		this.largeurImage        = 250;
		this.hauteurImage        = 350;
		
		this.piocheAleatoire();
		this.lblImage = new JLabel(this.iconeFinale);
		
		this.imgFond             = Toolkit.getDefaultToolkit().getImage("./images/img-saisie.png");

		this.btnChangerImage     = new JButton("Piocher Carte");
		
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		panelAfficheCarte.add(this.lblImage);

		panelAfficheScore.add(new JLabel("Manche : "));
		panelAfficheScore.add(new JLabel("Tour : "));
		panelAfficheScore.add(new JLabel("Score joueur : "));

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
		this.nomImage            = (int)(Math.random() * 10) + 1;
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
