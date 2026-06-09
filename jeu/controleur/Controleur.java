package controleur;

import ihm.FrameJeu;

import metier.PlateauJeu;

public class Controleur
{
	private FrameJeu        ihm;
	//private PlateauJeu      metier;
	
	public Controleur()
	{
		this.ihm = new FrameJeu(this);
	}

	//getters
	
	public static void main (String[] args) { new Controleur(); } //Démarage de l'application
}
