package ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

public class PanelCarte extends JPanel implements ActionListener
{
	private ImageIcon imgPersonnage;
    	private JButton   btnChangerImage;
    	private Image     imageRedimensionnee;
    	private ImageIcon iconeFinale;
    	private JLabel    lblImage;
    	
    	private int       nomImage;
    	private int       largeurImage;
    	private int       hauteurImage;

    	public PanelCarte()
    	{
		this.setLayout(new BorderLayout());

		JPanel panelAfficheCarte = new JPanel(new GridLayout());
		JPanel panelAfficheScore = new JPanel(new GridLayout(2, 2));
		
		this.nomImage            = (int)(Math.random() * 10) + 1;
		this.largeurImage        = 250;
		this.hauteurImage        = 350;

		this.piocheAleatoire();
		this.lblImage = new JLabel(this.iconeFinale);

		this.btnChangerImage = new JButton("Changer l'image");

		panelAfficheCarte.setOpaque(false);
		panelAfficheCarte.add(this.lblImage);

		panelAfficheScore.add(new JLabel("Manche : "));
		panelAfficheScore.add(new JLabel("Tour : "));
		panelAfficheScore.add(new JLabel("Score joueur : "));

		this.add(this.btnChangerImage, BorderLayout.NORTH);
		this.add(panelAfficheCarte   , BorderLayout.CENTER);
		this.add(panelAfficheScore   , BorderLayout.SOUTH);

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
    	
    	private void piocheAleatoire ()
    	{
    		this.imgPersonnage = new ImageIcon("./images/" + this.nomImage + ".png");
		this.imageRedimensionnee = this.imgPersonnage.getImage()
		        .getScaledInstance(this.largeurImage, this.hauteurImage, Image.SCALE_SMOOTH);
		this.iconeFinale = new ImageIcon(this.imageRedimensionnee);
    	}
}
