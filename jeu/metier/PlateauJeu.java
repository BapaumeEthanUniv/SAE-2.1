package metier;

import java.util.ArrayList;
import java.awt.Color;
import java.io.File;

public class PlateauJeu 
{
	private int                nbLigne;
	private int                nbColonne;
	
	private ArrayList<Casting> lstCasting;

	private Zone[][]           tabZone;

	private Casting[][]        tabArrete;

	private int                tailleCase;

	private Zone[][]           tabZones;

	private ArrayList<Acteur> lstActeurs;

	public PlateauJeu(File filePlateau)
	{

	}

	public int                getNbLigne()    {return nbLigne;}

	public int                getNbColonne()  {return nbColonne;}

	public int                getTailleCase() {return tailleCase;}

	public ArrayList<Casting> getLstCasting() {return lstCasting;}

	public Zone[][]           getTabZone()    {return tabZone;}

	public Casting[][]        getTabArrete()  {return tabArrete;}

	public ArrayList<Acteur>  getLstActeurs() {return lstActeurs;}

	public void majVoisin()
	{
		for (Acteur acteur : lstActeurs)
			acteur.majVoisins(lstActeurs);
	}

	public boolean setPrincipal(Color c, int posX, int posY)
	{
		for (Acteur acteur : lstActeurs)
		{
			if (posX == acteur.getPosX() && posY == acteur.getPosY() )
			{
				acteur.setPrincipal(c);
				return true;
			}
		}

		return false;
	}


}
