package controleur;

import ihm.FrameCarte;
import ihm.FrameJeu;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import metier.Acteur;
import metier.Carte;
import metier.Casting;
import metier.Chemin;
import metier.Pioche;
import metier.PlateauJeu;
import metier.Role;
import metier.Zone;

public class Controleur
{
	private FrameJeu      frameJeu;
	private FrameCarte	frameCarte;
	private PlateauJeu      metier;
	private Pioche          pioche;
	
	Dimension tailleEcran;
	int l, lJeu, lCarte;
	int h;
	int xCarte, yCarte;
	int xPlateau, yPlateau;
	
	public Controleur()
	{
		this.frameJeu   = new FrameJeu (this);
		
		tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

		h = (int) tailleEcran.getHeight() - 100 - 100;
		l = (int) tailleEcran.getWidth () - 335 - 20 - 335;
		
		lJeu   = (int) (l * 0.60);
		lCarte = (int) (l * 0.40);
		
		this.frameJeu  .setSize(lJeu  , h);
		
		xCarte   = 335;
		xPlateau = xCarte + lCarte + 20;
		
		yCarte   = yPlateau = 100;
		
		this.frameJeu  .setLocation(xPlateau, yPlateau);
		
	}

	public void initPlateau(File filePlateau) 
	{ 
		this.metier = new PlateauJeu(filePlateau);
		this.pioche = new Pioche    (this.getLstRole());	
	}
	
	public void creerFrameCarte ()            { frameCarte = new FrameCarte(this); this.frameCarte.setSize(lCarte, h); this.frameCarte.setLocation(xCarte,yCarte); }

	//getters
	public int                getNbLigne()    {return this.metier.getNbLigne();}
	public int                getNbColonne()  {return this.metier.getNbColonne();}
	public int                getTailleCase() {return this.metier.getTailleCase();}
	public ArrayList<Casting> getLstCasting() {return this.metier.getLstCasting();}
	public Zone[][]           getTabZone()    {return this.metier.getTabZone();}
	//public Casting[][]        getTabArete()  {return this.metier.getTabArrete();}
	public ArrayList<Acteur>  getLstActeurs() {return this.metier.getLstActeurs();}
	public ArrayList<Role>    getLstRole()    {return this.metier.getLstRole();}
	public Carte              getCartePioche(){return this.pioche.getCarte();}
	public Chemin[]           getTabChemin(){return this.metier.getTabChemin();}
	public Casting            getManche()     {return this.metier.getManche();}
	public void               changerCarte()   {this.frameCarte.changerCarte();}
	public int                getNbCarteFonce(){return this.pioche.getNbCarteFonce();}


	public boolean piocherCarte()             { return this.pioche.piocherCarte();}
	public boolean ajouterChemin(int posX, int posY) {return this.metier.ajouterChemin(posX, posY, this.getCartePioche().getRole());}
	public boolean nouvelleManche()                  {return this.metier.nouvelleManche();}
	
	public static void main (String[] args) { new Controleur(); } //Démarrage de l'application
}
