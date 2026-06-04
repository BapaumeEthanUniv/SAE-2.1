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
    private JLabel    label;

    public PanelCarte()
    {
        JPanel panelAfficheCarte;
        JPanel panelAfficheScore;
        int hauteur = 500;
        int largeur = 350;

        this.setLayout(new BorderLayout());

        int image = (int)(Math.random() * 10) + 1;

        panelAfficheCarte = new JPanel(new GridLayout());
        panelAfficheScore = new JPanel(new GridLayout(2, 2));

        this.imgPersonnage = new ImageIcon("../images/" + image + ".png");
        this.imageRedimensionnee = this.imgPersonnage.getImage()
                .getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH);
        this.iconeFinale = new ImageIcon(this.imageRedimensionnee);
        this.label = new JLabel(this.iconeFinale);


        this.btnChangerImage = new JButton("Changer l'image");

        panelAfficheCarte.setOpaque(false);
        panelAfficheCarte.add(this.label);

        panelAfficheScore.add(new JLabel("manche : "));
        panelAfficheScore.add(new JLabel("tour : "));
        panelAfficheScore.add(new JLabel("score joueur : "));

        this.add(panelAfficheCarte, BorderLayout.CENTER);
        this.add(panelAfficheScore, BorderLayout.SOUTH);
        this.add(this.btnChangerImage, BorderLayout.NORTH);

        this.btnChangerImage.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        int image = ((int)(Math.random() * 10) + 1);
        this.imgPersonnage = new ImageIcon("../images/" + image + ".png");
        this.imageRedimensionnee = this.imgPersonnage.getImage()
                .getScaledInstance(350, 500, Image.SCALE_SMOOTH);
        this.iconeFinale = new ImageIcon(this.imageRedimensionnee);
        this.label.setIcon(this.iconeFinale);
    }
}
