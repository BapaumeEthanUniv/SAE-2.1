package controleur;

import java.io.File;
import java.util.ArrayList;

import ihm.FrameJeu;
import ihm.FrameCarte;

import java.awt.Dimension;
import metier.Acteur;
import metier.Casting;
import metier.PlateauJeu;
import metier.Zone;

//import frameJeu.PlateauJeu;

public class Controleur
{
	private FrameJeu        frameJeu;
	private FrameCarte	frameCarte;
	private PlateauJeu      metier;
	
	Dimension tailleEcran;
	int l, lJeu, lCarte;
	int h;
	int xCarte, yCarte;
	int xPlateau, yPlateau;
	
	public Controleur()
	{
		this.frameJeu   = new FrameJeu  (this);
		
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

	public void initPlateau(File filePlateau) { metier = new PlateauJeu(filePlateau);}
	
	public void creerFrameCarte ()            { frameCarte = new FrameCarte(this); this.frameCarte.setSize(lCarte, h); this.frameCarte.setLocation(xCarte,yCarte); }

	//getters
	public int                getNbLigne()    {return this.metier.getNbLigne();}
	public int                getNbColonne()  {return this.metier.getNbColonne();}
	public int                getTailleCase() {return this.metier.getTailleCase();}
	public ArrayList<Casting> getLstCasting() {return this.metier.getLstCasting();}
	public Zone[][]           getTabZone()    {return this.metier.getTabZone();}
	public Casting[][]        getTabArrete()  {return this.metier.getTabArrete();}
	public ArrayList<Acteur>  getLstActeurs() {return this.metier.getLstActeurs();}
	
	public static void main (String[] args) { new Controleur(); } //Démarrage de l'application
}
