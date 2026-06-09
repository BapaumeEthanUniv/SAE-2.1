package controleur;

import ihm.FrameJeu;
import ihm.FrameCarte;

import java.awt.Dimension;

//import metier.PlateauJeu;

public class Controleur
{
	private FrameJeu        frameJeu;
	private FrameCarte	frameCarte;
	//private PlateauJeu      metier;
	
	int l, lJeu, lCarte;
	int h;
	int xCarte, yCarte;
	int xPlateau, yPlateau;
	
	public Controleur()
	{
		this.frameJeu   = new FrameJeu(this);
		this.frameCarte = new FrameCarte(this);
		
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(tailleEcran);

		h = (int) tailleEcran.getHeight() - 100 - 100;
		l = (int) tailleEcran.getWidth () - 335 - 20 - 335;
		
		lJeu   = (int) (l * 0.60);
		lCarte = (int) (l * 0.40);
		
		this.frameJeu  .setSize(lJeu  , h);
		this.frameCarte.setSize(lCarte, h);
		
		xCarte   = 335;
		xPlateau = xCarte + lCarte + 20;
		
		yCarte   = yPlateau = 100;
		
		this.frameJeu  .setLocation(xPlateau, yPlateau);
		this.frameCarte.setLocation(xCarte  , yCarte  );
	}

	//getters
	
	public static void main (String[] args) { new Controleur(); } //Démarrage de l'application
}
