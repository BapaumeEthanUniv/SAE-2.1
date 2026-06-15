package controleur;

import metier.Acteur;
import metier.Carte;
import metier.Casting;
import metier.Chemin;
import metier.Pioche;
import metier.PlateauJeu;
import metier.Role;
import metier.Zone;

import ihm.FrameCarte;
import ihm.FrameJeu;

import java.io.File;

import java.util.ArrayList;

import java.awt.Dimension;
import java.awt.Point;

public class Controleur
{
	private FrameJeu      frameJeu;
	private FrameCarte	frameCarte;
	private PlateauJeu      metier;
	private Pioche          pioche;
	
	Dimension tailleEcran;   // Variables permettant 
	int l, lJeu, lCarte;     // de gérer 
	int h;                   // la position 
	int xCarte, yCarte;      // et la taille 
	int xPlateau, yPlateau;  // des fenêtres
	
	public Controleur()
	{	
		this.frameJeu   = new FrameJeu (this);
		
		tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

		h = (int) tailleEcran.getHeight() - 100 - 100;
		l = (int) tailleEcran.getWidth () - 335 - 50 - 335;
		
		lJeu   = (int) (l * 0.60);  // Largeur fenêtre jeu
		lCarte = (int) (l * 0.40);  // Largeur fenêtre pioche
		
		this.frameJeu.setSize(lJeu  , h);
		
		xCarte   = 335;
		xPlateau = xCarte + lCarte + 50;
		
		yCarte   = yPlateau = 100;
		
		this.frameJeu  .setLocation(xPlateau, yPlateau);
	}

	//Getters
	public int                getNbLigne     () { return this.metier.getNbLigne();      }
	public int                getNbColonne   () { return this.metier.getNbColonne();    }
	public int                getTailleCase  () { return this.metier.getTailleCase();   }
	public ArrayList<Casting> getLstCasting  () { return this.metier.getLstCasting();   }
	public Zone[][]           getTabZone     () { return this.metier.getTabZone();      }
	public ArrayList<Acteur>  getLstActeurs  () { return this.metier.getLstActeurs();   }
	public ArrayList<Role>    getLstRole     () { return this.metier.getLstRole();      }
	public Carte              getCartePioche () { return this.pioche.getCarte();        }
	public Chemin[]           getTabChemin   () { return this.metier.getTabChemin();    }
	public Casting            getManche      () { return this.metier.getManche();       }
	public int                getIdManche    () { return this.metier.getIdManche();     }
	public int                getNbCarteFonce() { return this.pioche.getNbCarteFonce(); }
	public int[]              getScores      () { return this.metier.getScores();       }
	public int                getScoreFinal  () { return this.metier.getScoreFinal();   }


    /* --------------------------*/
    /*    Méthodes utilitaires   */
    /* --------------------------*/

	// Méthode permettant de changer la Carte affichée
    public void    changerCarte()               { this.frameCarte.changerCarte();       }
    
    // Méthode permettant de bouger les fenêtres l'une à côté de l'autre avec un espace de 50px
    public void    moveFrame(String orig)
    {
        Point p;

        // Si le joueur déplace le jeu, la frameCarte suit
        if (orig.equals("Jeu") && this.frameJeu != null && this.frameCarte != null && this.frameCarte.isVisible())
        {
            p = this.frameJeu.getLocation();

            this.frameCarte.updating = true;
            this.frameCarte.setLocation(p.x - 50 - this.lCarte, p.y); // On place à gauche (x - 20 - largeur)
            this.frameCarte.updating = false;
        }

        // Si le joueur déplace la frameCarte, le jeu suit
        if (orig.equals("Carte") && this.frameJeu != null && this.frameCarte != null && this.frameCarte.isVisible())
        {
            p = this.frameCarte.getLocation();

            this.frameJeu.updating = true;
            this.frameJeu.setLocation(p.x + this.lCarte + 50, p.y); // On place à droite (x + largeur + 20)
            this.frameJeu.updating = false;
        }
    }

    // Méthode permettant d'initialiser le plateau, en prenant en paramètre le répertoire du plateau
    public void    initPlateau(File filePlateau, boolean estDebug)
    {
        this.metier = new PlateauJeu(filePlateau, this);
        this.pioche = new Pioche    (this, estDebug);
    }

    // Méthode permettant de créer la FrameCarte lorsqu'on a appuyé sur le bouton Jouer
    public void    creerFrameCarte ()
    {
        frameCarte = new FrameCarte(this);
        this.frameCarte.setSize(lCarte, h);
        this.frameCarte.setLocation(xCarte,yCarte);
    }

    // Méthode permettant de signaler à l'IHM que le jeu est terminé en appelant les méthodes finDePartie() des frames
	public void    finDePartie()
	{
	    this.frameJeu.finDePartie();
        this.frameCarte.finDePartie();
	}

    // Méthode permettant de piocher une carte en appelant la méthode piocherCarte() de la classe Pioche
	public void    piocherCarte()                    { this.pioche.piocherCarte(); }

    // Méthode permettant d'ajouter un chemin en appelant la méthode ajouterChemin() du Métier
	public boolean ajouterChemin(int posX, int posY) { return this.metier.ajouterChemin(posX, posY, this.getCartePioche().getRole()); }

    // Méthode permettant de signaler à l'IHM qu'une nouvelle manche commence en appelant la méthode nouvelleManche() du Métier
	public void    nouvelleManche()                  { this.metier.nouvelleManche(); }

    // Méthode permettant de mettre à jour le JLabel affichant le Casting actif
	public void    majLblCasting()                   { this.frameJeu.majLblCasting(); }

    // Méthode permettant de cacher la frameCarte lorsqu'on se trouve sur le panel des scores
    public void    cacherFrameCarte()
    {
        if (this.frameCarte != null)
        {
            this.frameCarte.setVisible(false);
        }
    }


	public static void main (String[] args) { new Controleur(); } // Démarrage de l'application
}
