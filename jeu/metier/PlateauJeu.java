package metier;

import java.util.ArrayList;
import java.awt.Color;
import java.io.File;
import java.util.Scanner;
import java.io.FileReader;

public class PlateauJeu 
{
	private int                nbLigne;
	private int                nbColonne;
	
	private ArrayList<Casting> lstCasting;

	private Zone[][]           tabZone;

	private Casting[][]        tabArrete;

	private int                tailleCase;

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

	public void importActeur()
	{
		FileReader fr;

		try
		{
			fr = new FileReader ( "paroles.data" );
			Scanner sc = new Scanner ( fr );

			while ( sc.hasNextLine() )
				System.out.println ( sc.nextLine() );

			fr.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}
	public boolean importZone(File filePlateau)
	{
		FileReader fr;
		String Ligne;
		ArrayList<Zone> lstZones;
		int numZone;
		Color colorZone;
		int numLig;
		int numCol;
		lstZones = new ArrayList<Zone>();
		try
		{
			fr = new FileReader ( filePlateau.getPath() + File.separator + "Plateau.data" );
			Scanner sc = new Scanner ( fr );
			Ligne = sc.nextLine();
			while (!Ligne.equals("Plateau :"))
			{
				if (!Ligne.equals("null"))
				{
					numZone = Integer.parseInt(Ligne.substring(0,3));
					colorZone = new Color(Integer.parseInt(Ligne.substring(3,6)),
					                      Integer.parseInt(Ligne.substring(6,9)),
					                      Integer.parseInt(Ligne.substring(9,12)));
					
					lstZones.add(new Zone(numZone, colorZone));
				}
				Ligne = sc.nextLine();
			}
			numLig = 0;
			while (sc.hasNextLine())
			{
				for (int i=0; i<Ligne.length(); i+=3)
				{
					numCol = i/3;
					numZone = Integer.parseInt(Ligne.substring(i,i+3))-1;
					this.tabZone[numLig][numCol] = lstZones.get(numZone);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
