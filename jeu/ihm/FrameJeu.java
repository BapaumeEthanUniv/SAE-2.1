package ihm;

import controleur.Controleur;

import java.io.File;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;

import javax.swing.*;


public class FrameJeu extends JFrame
{
    public  boolean    updating = false;  // Permet le déplacement fluide et correct des frames

    private Controleur ctrl;
	private JPanel[]   tabPanel;          // Contient les différents panels du Jeu (Accueil + Jeu + Score)

	private JPanel     panelActif;        // Permet de contenir le panel affiché et de changer avec la méthode setPnl()
	
	public FrameJeu (Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setTitle("Acte de Présence");
		this.setSize(600, 700);
		this.setLayout(new BorderLayout());

		this.tabPanel	 = new JPanel[3];
		
		this.tabPanel[0] = new PanelAccueilJeu(ctrl, this, 0);

		this.panelActif = this.tabPanel[0];
		
		this.add(this.panelActif, BorderLayout.CENTER);

        this.addComponentListener( new GereDeplacerFrame() );
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}

    // Méthode permettant de changer de panel sur l'écran
	public void setPnl(JPanel pnl)
	{
		this.remove(this.panelActif);  // On enlève le panel de la Frame
		this.panelActif = pnl;         // Le panelActif devient le nouveau panel passé en paramètre
		this.add(this.panelActif);     // Le nouveau panelActif est ajouté à la Frame

        // On actualise
		this.repaint();
		this.revalidate();
	}

    // Méthodes permettant de créer le PanelJeu/PanelScore et le contenir dans le tableau de JPanel
	public void creerPanelJeu()   { this.tabPanel[1] = new PanelJeu   (ctrl, this, 1); }
    public void creerPanelScore()
    {
        this.tabPanel[2] = new PanelScore (ctrl, this, 2);
    }

    // Getters
	public JPanel getPnl(int indice) { return this.tabPanel[indice]; }
    public File   getFichierPlateau(){ return ((PanelAccueilJeu) this.tabPanel[0]).getFichierPlateau(); }

    // Méthode permettant de mettre à jour le JLabel du Casting actif en appelant la méthode majLblCasting() de PanelJeu
	public void majLblCasting() { ((PanelJeu) this.tabPanel[1]).majLblCasting(); }

    // Méthode permettant de signaler au PanelJeu que la partie est terminée
    public void finDePartie() { ((PanelJeu) this.tabPanel[1]).finDePartie(); }

    // Classe interne permettant de gérer le déplacement des fenêtres
    private class GereDeplacerFrame extends ComponentAdapter
    {
        public void componentMoved(java.awt.event.ComponentEvent e)
        {
            if ( !updating && FrameJeu.this.isFocused() )
            {
                ctrl.moveFrame("Jeu"); // On prévient le contrôleur que le jeu bouge
            }
        }
    }
}
