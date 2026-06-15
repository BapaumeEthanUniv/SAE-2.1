package ihm;

import controleur.Controleur;

import java.awt.event.ComponentAdapter;

import javax.swing.JFrame;

public class FrameCarte extends JFrame
{
	public  boolean    updating = false;  // Permet le déplacement fluide et correct des fenêtres;

    private Controleur ctrl;
	private PanelCarte panelCarte;

    // Contructeur
	public FrameCarte(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setTitle("Pioche");

		this.panelCarte = new PanelCarte(ctrl);

		this.add(this.panelCarte);

        this.addComponentListener( new GereDeplacerFrame() );

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

    /* --------------------------*/
    /*    Méthodes utilitaires   */
    /* --------------------------*/

    // Méthode permettant de changer de Carte en appelant la méthode changerCarte() de PanelCarte
	public void changerCarte()
	{
		this.panelCarte.changerCarte();
	}

    // Méthode permettant de signaler au PanelCarte que la partie est terminée
    public void finDePartie()  { this.panelCarte.finDePartie();}


    // Classe interne permettant de gérer le déplacement des fenêtres
    private class GereDeplacerFrame extends ComponentAdapter
    {
        public void componentMoved(java.awt.event.ComponentEvent e)
        {
            if ( !updating && FrameCarte.this.isFocused())
            {
                ctrl.moveFrame("Carte"); // On prévient le contrôleur que la pioche bouge !
            }
        }
    }
}
