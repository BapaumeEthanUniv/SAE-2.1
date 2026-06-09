package controleur;

import java.io.File;
import java.util.ArrayList;

import ihm.FrameJeu;
import metier.Acteur;
import metier.Casting;
import metier.PlateauJeu;
import metier.Zone;

//import metier.PlateauJeu;

public class Controleur
{
	private FrameJeu        ihm;
	private PlateauJeu      metier;
	
	public Controleur()
	{
		this.ihm = new FrameJeu(this);
	}

	public void initPlateau(File filePlateau) { metier = new PlateauJeu(filePlateau);}

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
