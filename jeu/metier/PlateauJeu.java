/*-----------------------------------------*/
/* Classe métier principale du Jeu         */
/*-----------------------------------------*/

package metier;

import controleur.Controleur;

import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Scanner;

import java.awt.Color;

public class PlateauJeu 
{
	//Attributs
	private int                nbLigne;       //Nombre de lignes du plateau
	private int                nbColonne;     //Nombre de colonnes du plateau
	
	private ArrayList<Casting> lstCasting;    //Liste des castings (manches) de la partie

	private int                idManche;      //Index de la manche en cours

	private ArrayList<Role>    lstRole;       //Liste des rôles (type de symboles) présents sur le plateau

	private Casting            manche;        //Couleur de la manche actuelle

	private Chemin[]           tabChemin;     //Ensemble des chemins sur le plateau

	private Zone[][]           tabZone;       //Ensemble des zones du plateau

	private boolean[][]        tabArete;      //True si une arête déjà traversé est sur la coordonnée
	
	private boolean[][]        tabAretePoint; //Même chose que tabArete mais pour les points de la grille

	private int                tailleCase;    //Taille des case du plateau

	private ArrayList<Acteur>  lstActeurs;    //Liste acteurs (symbole) placés sur le plateau

	private int[]              scores;        //Score de chaque manche

	private Controleur         ctrl;          //Controleur

	//Constructeur
	public PlateauJeu(File filePlateau, Controleur ctrl)
	{
		this.ctrl = ctrl;

		//Importe les attributs du plateau en utilisant les données sauvegardées
		this.importActeur (filePlateau);
		this.importCasting(filePlateau);
		this.importPlateau(filePlateau);
		this.importZone   (filePlateau);

		this.tabArete      = new boolean[this.nbLigne]    [this.nbColonne];
		this.tabAretePoint = new boolean[this.nbLigne - 1][this.nbColonne - 1];
		this.scores        = new int    [this.lstCasting.size()];
		this.tabChemin     = new Chemin [this.lstCasting.size()];

		this.majVoisin();      //Permet l'affichage des arrêtes
		this.nouvelleManche(); //Commence la première manche
	}

	//Getter
	public int                getNbLigne()        {return this.nbLigne;}
	public int                getNbColonne()      {return this.nbColonne;}
	public int                getTailleCase()     {return this.tailleCase;}
	public ArrayList<Casting> getLstCasting()     {return this.lstCasting;}
	public Zone[][]           getTabZone()        {return this.tabZone;}
	public boolean[][]        getTabArete()       {return this.tabArete;}
	public boolean[][]        getTabAretePoint()  {return this.tabAretePoint;}
	public ArrayList<Acteur>  getLstActeurs()     {return this.lstActeurs;}
	public Casting            getManche()         {return this.manche;}
	public ArrayList<Role>    getLstRole()        {return this.lstRole;}
	public int[]              getScores()         {return this.scores;}

	// Retourne le score final
	public int                getScoreFinal()
	{
		int somme = 0;

		for (int i : scores)
			somme += i;

		return somme;
	}

	public int                getIdManche()       {return this.idManche;}
	public Chemin[]           getTabChemin()      {return this.tabChemin;}

	// Retourne un acteur en prenant sa position sur la plateau
	public Acteur             getActeur(int posX, int posY)
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

	// Retourne l'acteur principal pour la couleur donnée
	public Acteur            getPrincipal(Casting casting)
	{
		for (Acteur acteur : lstActeurs)
		{
			if (acteur.estPrincipal() && acteur.getCouleur().equals(casting.getCouleur()))
				return acteur;
		}
		return null;
	}

	// Définit un acteur comme acteur principal (point de départ)
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

	/*---------------------------*/
	/* Méthodes d'import         */
	/*---------------------------*/

	// Ajoute les acteurs sur le plateau
	public boolean importActeur(File filePlateau)
	{
		FileReader fr;
		Role       role;
		int        posX;
		int        posY;
		String     ligne;

		this.lstActeurs = new ArrayList<Acteur>();
		this.lstRole    = new ArrayList<Role>();

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

				if (! this.lstRole.contains(role))
					this.lstRole.add(role);
			}

			fr.close();
			sc.close();
		}
		catch (Exception e){ return false; }

		return true;
	}

	// Définit la liste des manches et la position des points de départ
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

	// Définit la taille du plateau et des cases
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

	// Définit la liste des zones, leur couleur et leur position
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
					if (numZone !=-1)
						this.tabZone[numLig][numCol] = lstZones.get(numZone);

				}
				numLig++;
			}
			sc.close();
		}
		catch (Exception e){return false;}
		return true;
	}


    /* --------------------------*/
    /*    Méthodes utilitaires   */
    /* --------------------------*/

    // Passe à la manche suivante
    public void nouvelleManche()
    {
        if (this.idManche > 0)
            this.calculerScore(); //Calcule le score après chaque fin de manche

        if (this.idManche < this.lstCasting.size())
        {
            this.manche                   = this.lstCasting.get(this.idManche);
            this.tabChemin[this.idManche] = new Chemin(manche, this);
            this.idManche++;
        }
        else
            this.ctrl.finDePartie(); //Fini la partie si toutes les manches ont été faites
    }

    // Permet à chaque acteur d'identifier ses voisins
    public void majVoisin()
    {
        for (Acteur acteur : lstActeurs)
            acteur.majVoisins(lstActeurs);
    }

	// Ajoute les arrêtes dans tabArrete quand un chemin est dessiné
	public void ajouterArete(Acteur acteurDepart, Acteur acteurArrive)
	{
		int deltaY = Integer.compare(acteurArrive.getPosY(), acteurDepart.getPosY());
		int deltaX = Integer.compare(acteurArrive.getPosX(), acteurDepart.getPosX());
		
		int x = acteurDepart.getPosX();
		int y = acteurDepart.getPosY();

		while (x != acteurArrive.getPosX() || y != acteurArrive.getPosY())
		{
			if (deltaX < 0 && deltaY < 0)
				this.tabAretePoint[x - 1][y - 1] = true;

			if (deltaX > 0 && deltaY > 0)
				this.tabAretePoint[x][y]         = true;

			if (deltaX > 0 && deltaY < 0)
				this.tabAretePoint[x][y - 1]     = true;

			if (deltaX < 0 && deltaY > 0)
				this.tabAretePoint[x - 1][y]     = true;

			x += deltaX;
			y += deltaY;

			if (x != acteurArrive.getPosX() || y != acteurArrive.getPosY())
				this.tabArete[x][y] = true;
		}
		acteurDepart.supprimerVoisin(acteurArrive); //Lorsqu'une arête est dessiner entre deux symboles, ils ne sont plus considérés comme voisin
	}

	// Vérifie si des arrêtes déjà remplis se trouve entre deux acteurs
	public boolean entreDeux(Acteur acteurDepart, Acteur acteurArrive)
	{
		int deltaY = Integer.compare(acteurArrive.getPosY(), acteurDepart.getPosY());
		int deltaX = Integer.compare(acteurArrive.getPosX(), acteurDepart.getPosX());
		
		int y = acteurDepart.getPosY();
		int x = acteurDepart.getPosX();

		while ( y != acteurArrive.getPosY() || x != acteurArrive.getPosX() )
		{
			if (deltaX < 0 && deltaY < 0 && this.tabAretePoint[x - 1][y - 1])
				return true;

			if (deltaX > 0 && deltaY > 0 && this.tabAretePoint[x][y])
				return true;

			if (deltaX > 0 && deltaY < 0 && this.tabAretePoint[x][y - 1])
				return true;

			if (deltaX < 0 && deltaY > 0 && this.tabAretePoint[x - 1][y])
				return true;

			y += deltaY;
			x += deltaX;

			if (this.tabArete[x][y] && (x != acteurArrive.getPosX() || y != acteurArrive.getPosY()))
				return true;
		}
		
		return false;
	}

	// Appel de la méthode ajouterChemin du chemin actif
	public boolean ajouterChemin(int posX, int posY, Role rolePioche) 
	{
		return this.tabChemin[this.idManche - 1].ajouterChemin(this.getActeur(posX, posY), this.lstActeurs, rolePioche);
	}

	// Calcul des scores de la manche actuelle
	public void calculerScore()
	{
		ArrayList<Integer> nbActeurs     = new ArrayList<Integer>(); //Nombre d'acteurs dans chaque zone
		int                nbActeursMax  = 0;                        //Nombre d'acteurs dans la zone avec le plus d'acteurs
		ArrayList<Zone>    zonesParcouru = new ArrayList<Zone>();    //Liste des zones parcourues
		Zone               zoneActeur;                               //Zone de l'acteur traité
		int                indexZone;                                //Index de la zone traité

		for (Acteur acteurChemin : this.tabChemin[idManche - 1].getChemin())
		{
			zoneActeur = this.tabZone[acteurChemin.getPosX()][acteurChemin.getPosY()];

			if (! zonesParcouru.contains(zoneActeur))
			{
				zonesParcouru.add(zoneActeur);
				nbActeurs    .add(1);
			}
			else
			{
				indexZone = zonesParcouru.indexOf(zoneActeur);

				nbActeurs.set(indexZone, nbActeurs.get(indexZone) + 1);
			}
		}

		for (Integer i : nbActeurs)
			if (i > nbActeursMax)
				nbActeursMax = i;

		this.scores[idManche - 1] = nbActeursMax * zonesParcouru.size();
	}
}