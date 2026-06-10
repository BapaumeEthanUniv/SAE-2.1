package metier;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.util.Scanner;
import java.io.FileReader;

public class PlateauJeu 
{
	private int                nbLigne;
	private int                nbColonne;
	
	private ArrayList<Casting> lstCasting;

	private Zone[][]           tabZone;

	private boolean[][]        tabArrete;

	private int                tailleCase;

	private ArrayList<Acteur> lstActeurs;

	public PlateauJeu(File filePlateau)
	{
		this.importActeur (filePlateau);
		this.importCasting(filePlateau);
		this.importPlateau(filePlateau);
		this.importZone   (filePlateau);
		
		this.majVoisin();
	}

	public int                getNbLigne()    {return nbLigne;}

	public int                getNbColonne()  {return nbColonne;}

	public int                getTailleCase() {return tailleCase;}

	public ArrayList<Casting> getLstCasting() {return lstCasting;}

	public Zone[][]           getTabZone()    {return tabZone;}

	public boolean[][]        getTabArrete()  {return tabArrete;}

	public ArrayList<Acteur>  getLstActeurs() {return lstActeurs;}

	public Acteur getActeur(int posX, int posY)
	{
		for (Acteur acteur : this.lstActeurs)
		{
			if (acteur.getPosX() == posX && acteur.getPosY() == posY)
			{
				return acteur; // On a trouvé l'acteur sur lequel on a cliqué !
			}
		}
		return null; // Il n'y a pas d'acteur ici
	}

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

	public boolean importActeur(File filePlateau)
	{
		FileReader fr;
		Role       role;
		int        posX;
		int        posY;
		String ligne;
		this.lstActeurs = new ArrayList<Acteur>();

		try
		{
			fr = new FileReader ( filePlateau.getPath() + File.separator + "Acteur.data" );
			Scanner sc = new Scanner ( fr );

			while ( sc.hasNextLine() )
			{
				ligne = sc.nextLine();
				role = Role   .getRole (ligne.substring(0, 3));
				posX = Integer.parseInt(ligne.substring(3, 6));
				posY = Integer.parseInt(ligne.substring(6));

				this.lstActeurs.add(new Acteur(posX, posY, role));
			}

			fr.close();
			sc.close();
		}
		catch (Exception e){ return false; }

		return true;
	}

	public boolean importCasting(File filePlateau)
	{
		FileReader fr;
		Color      couleur;
		int        posX;
		int        posY;

		this.lstCasting = new ArrayList<Casting>();
		try
		{
			fr = new FileReader ( filePlateau.getPath() + File.separator + "Casting.data" );
			Scanner sc = new Scanner ( fr );
			String Ligne;

			while ( sc.hasNextLine() )
			{
				Ligne = sc.nextLine();
				couleur = new Color(Integer.parseInt(Ligne.substring(0, 3)),
				                    Integer.parseInt(Ligne.substring(3, 6)),
									Integer.parseInt(Ligne.substring(6, 9)));
				posX = Integer.parseInt(Ligne.substring(9, 12));
				posY = Integer.parseInt(Ligne.substring(12, 15));

				this.lstCasting.add(Casting.getCasting(couleur));
				this.setPrincipal(couleur, posX, posY);
			}

			fr.close();
			sc.close();
		}
		catch (Exception e){ return false; }

		return true;
	}

	public boolean importPlateau(File filePlateau)
	{
		FileReader fr;
		String Ligne;

		try
		{
			fr = new FileReader ( filePlateau.getPath() + File.separator + "Plateau.data" );
			Scanner sc = new Scanner ( fr );
			Ligne = sc.nextLine();
			this.nbLigne    = Integer.parseInt(Ligne.substring(0, 3));
			this.nbColonne  = Integer.parseInt(Ligne.substring(3, 6));
			this.tailleCase = Integer.parseInt(Ligne.substring(6, 9));
			fr.close();
			sc.close();
		}
		catch (Exception e){ return false; }

		return true;
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

		this.tabZone = new Zone[this.nbLigne][this.nbColonne];
		lstZones = new ArrayList<Zone>();
		try
		{
			fr = new FileReader ( filePlateau.getPath() + File.separator + "Zone.data" );
			Scanner sc = new Scanner ( fr );
			Ligne = sc.nextLine();
			while (!Ligne.equals("Plateau :"))
			{
				if (!Ligne.equals("null"))
				{
					numZone = Integer.parseInt(Ligne.substring(0,3));
					colorZone = new Color(Integer.parseInt(Ligne.substring(3,6)),
					                      Integer.parseInt(Ligne.substring(6,9)),
					                      Integer.parseInt(Ligne.substring(9)));
					
					lstZones.add(new Zone(numZone, colorZone));
				}
				Ligne = sc.nextLine();
			}
			numLig = 0;
			while (sc.hasNextLine())
			{
				Ligne = sc.nextLine();
				for (int i=0; i<Ligne.length(); i+=3)
				{
					numCol = i/3;

					numZone = Integer.parseInt(Ligne.substring(i,i+3))-1;
					this.tabZone[numLig][numCol] = lstZones.get(numZone);
				}
				numLig++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void ajouterArette(Acteur voisin1, Acteur voisin2)
	{
		int x;
		int y;
		x = voisin1.getPosX();
		y = voisin1.getPosY();
		while (x!= voisin2.getPosX())
		{
			x+=Integer.compare(voisin1.getPosX(), voisin2.getPosX());
			y+=Integer.compare(voisin2.getPosX(), voisin2.getPosX());
			if (x!=voisin1.getPosX() && y!=voisin1.getPosY() &&
			    x!=voisin2.getPosX() && y!=voisin2.getPosY() )
			{
				this.tabArrete[x][y]=true;
			}
		}
		voisin1.supprimerVoisin(voisin2);
	}

	//Vérifie si des arrêtes déjà remplis se trouve entre deux acteurs
	public boolean entreDeux(Acteur acteurDepart, Acteur acteurArrive)
	{
		int deltaY = Integer.compare(acteurArrive.getPosY(), acteurDepart.getPosY());
		int deltaX = Integer.compare(acteurArrive.getPosX(), acteurDepart.getPosX());
		
		int y = acteurDepart.getPosY() + deltaY;
		int x = acteurDepart.getPosX() + deltaX;

		while ( y != acteurArrive.getPosY() || x != acteurArrive.getPosX() )
		{
			if (tabArrete[x][y])
				return true;
			
			y += deltaY;
			x += deltaX;
		}
		
		return false;
	}

	public Acteur getPrincipal(Casting casting)
	{
		for (Acteur acteur : lstActeurs)
		{
			if (acteur.estPrincipal() && acteur.getCouleur().equals(casting.getCouleur()))
				return acteur;
		}
		return null;
	}

	//main de test
	// public static void main(String[] args) 
	// {
	// 	PlateauJeu p = new PlateauJeu(new File("../creation/Plateau/plateauTest"));

	// 	System.out.println("Plateau : " + p.getNbLigne() + " " + p.getNbColonne() + " " + p.getTailleCase());
		
	// 	for (Casting c : p.getLstCasting())
	// 		System.out.println("Casting : " + c.getLibelle());

	// 	for (Acteur a : p.getLstActeurs())
	// 		System.out.println("Acteur : " + a.getRole().getLibelle() + " " + a.getPosX() + " " + a.getPosY() + " " + a.getCouleur());

	// 	System.out.println("Zone : ");
	// 	for (int lig = 0; lig < p.getTabZone().length; lig ++)
	// 	{
	// 		for (int col = 0; col < p.getTabZone()[lig].length; col ++)
	// 		{
	// 			if (p.getTabZone()[lig][col] != null)
	// 				System.out.print(String.format("%3d", p.getTabZone()[lig][col].getNumZone()));
	// 			else
	// 				System.out.print("  0");
	// 		}
	// 		System.out.println();
	// 	}
	
	// }
}
