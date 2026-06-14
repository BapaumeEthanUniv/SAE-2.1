/*-------------------------------------------------------*/
/* Classe métier principale du Créateur de plateau       */
/*-------------------------------------------------------*/

package metier;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;

import java.util.ArrayList;

import java.awt.Color;

public class CreateurPlateau 
{
	//Attribut
	private String            nomPlateau; //Nom du plateau

	private int               nbLigne;    //Nombre de ligne
	private int               nbColonne;  //Nombre de colonne

	private Role[]            tabRole;    //Contient les rôles qui vont être utilisé dans ce plateau
	private Casting[]         tabCasting; //Contient les castings qui vont être utilisé dans ce plateau

	private Zone[][]          tabZone;    //Contient la position des zones
	private Zone              zoneActive; //Zone en train d'être placée

	private int               tailleCase; //Taille des cases du plateau

	private ArrayList<Acteur> lstActeurs; //Liste des acteurs placés
	private ArrayList<Zone>   lstZones;   //Liste des zones placés

	//Constructeur
	public CreateurPlateau(String nomPlateau, int nbLigne, int nbColonne, int tailleCase, Role[] tabRole, Casting[] tabCasting)
	{
		this.nomPlateau = nomPlateau;
		this.nbLigne    = nbLigne;
		this.nbColonne  = nbColonne;
		this.tailleCase = tailleCase;
		this.tabRole    = tabRole;
		this.tabCasting = tabCasting;
		this.tabZone    = new Zone[this.nbLigne][this.nbColonne];
		this.lstZones   = new ArrayList<Zone>();
		this.lstActeurs = new ArrayList<Acteur>();
		Zone.resetCompteur();
		this.nouvelleZone();
	}

	//Getter
	public String            getNomPlateau() {return this.nomPlateau; }
	public int               getNbLigne()    {return nbLigne;}
	public int               getNbColonne()  {return nbColonne;}
	public int               getTailleCase() {return tailleCase;}
	public Role[]            getTabRole()    {return tabRole;}
	public Casting[]         getTabCasting() {return tabCasting;}
	public Zone              getZoneActive() {return this.zoneActive;}
	public Zone[][]          getTabZone()    {return tabZone;}
	public ArrayList<Zone>   getLstZones()   {return lstZones;}
	public ArrayList<Acteur> getLstActeurs() {return lstActeurs;}

	//Setter
	public void setZoneActive(Zone zone) {this.zoneActive = zone;}

	//Place une zone dans le plateau
	public boolean modifierZone(int lig, int col, Zone zone)
	{
		boolean estPremier   = true;  //La zone est la première placée
		boolean adjacent     = false; //La zone est ajacente à une autre case avec la même zone
		boolean coulAdjacent = false; //La zone est adjacente à une autre zone avec une couleur différente

		//verif que la zone est la première placée
		for(int l = 0; l < this.tabZone.length; l++)
		{
			for(int c = 0; c < this.tabZone[l].length; c++)
			{
				if (zone == tabZone[l][c])
					estPremier = false;
			}
		}

		//Double boucle pour traverser les cases adjacentes
		for(int l = lig - 1; l <= lig + 1; l++)
		{
			for(int c = col - 1; c <= col + 1; c++)
			{
				if (l < this.nbLigne && l >= 0 &&
					c < this.nbColonne && c >= 0 &&
				    !(c == col && l == lig)&&
				    !(c == col - 1 && l == lig - 1)&&
				    !(c == col + 1 && l == lig - 1)&&
				    !(c == col - 1 && l == lig + 1)&&
				    !(c == col + 1 && l == lig + 1))
				{
					//verif si il y a une zone posée avant de lire
					if (tabZone[l][c] != null)
					{
						//verif si il y a une même zone adjacente
						if (tabZone[l][c] == zone)
							adjacent = true;
						//verif si une zone adjacente a la même couleur
						if (zone.getCouleur() == tabZone[l][c].getCouleur() && tabZone[l][c] != zone)
							coulAdjacent = true;
					}
				}
			}
		}

		//verif qu'on ne place pas en dehors du plateau et qu'une autre zone n'occupe pas l'espace
		if (lig >= this.nbLigne   || lig < 0 ||
			col >= this.nbColonne || col < 0 ||
			tabZone[lig][col] != null ||
			(! estPremier && ! adjacent) || coulAdjacent)
			return false;

		this.tabZone[lig][col]=zone;
		return true;
	}

	//Supprime le contenu d'une zone
	public void effacerZone(Zone zone)
	{
		for (int lig = 0; lig < tabZone.length; lig ++)
			for (int col = 0; col < tabZone[lig].length; col ++)
				if (this.tabZone[lig][col] == zone)
					this.tabZone[lig][col] = null;
	}

	//Change la couleur d'une zone
	public boolean changerCouleurZone(Couleur couleur, Zone zone)
	{
		//Vérifie que il n'y a pas de zone adjacente de la même couleur que la zone vers laquelle on change
		for (int lig = 0; lig < tabZone.length; lig ++)
			for (int col = 0; col < tabZone[lig].length; col ++)
				if (tabZone[lig][col] == zone)
				{
					for(int l = lig - 1; l <= lig + 1; l++)
					{
						for(int c = col - 1; c <= col + 1; c++)
						{
							if (l < this.nbLigne   && l >= 0   &&
								c < this.nbColonne && c >= 0   &&
								!(c == col     && l == lig)    &&
								!(c == col - 1 && l == lig - 1)&&
								!(c == col + 1 && l == lig - 1)&&
								!(c == col - 1 && l == lig + 1)&&
								!(c == col + 1 && l == lig + 1))
								if (tabZone[l][c] != null && couleur == tabZone[l][c].getCouleur() && tabZone[l][c] != zone)
									return false;
						}
					}
				}

		zone.setCouleur(couleur);
		return true;
	}

	//Crée une nouvelle zone
	public void nouvelleZone()
	{
		Zone zone = new Zone(Couleur.VIOLET);
		this.lstZones.add(zone);
		this.setZoneActive(zone);
	}

	//Passe zoneActive à la zone precedente dans la liste
	public void zonePrecedente()
	{
		if (this.lstZones.indexOf(this.zoneActive) > 0)
			this.zoneActive = this.lstZones.get(this.lstZones.indexOf(this.zoneActive) - 1);
	}

	//Passe zoneActive à la zone suivante dans la liste
	public void zoneSuivante()
	{
		if (this.lstZones.indexOf(this.zoneActive) < this.lstZones.size() - 1)
			this.zoneActive = this.lstZones.get(this.lstZones.indexOf(this.zoneActive) + 1);
	}

	//Place un acteur dans le plateau
	public boolean ajouterActeur  (Role role, int posX, int posY) 
	{
		boolean placeLibre = true; //Il n'y a pas d'acteur dans la zone

		for (Acteur aExistant : lstActeurs)
			if (aExistant.getPosX() == posX && aExistant.getPosY() == posY)
				placeLibre = false;

		if (posX > this.nbLigne || posX < 0 || 
			posY > this.nbColonne || posY < 0 ||
		    ! placeLibre)
			return false;

		Acteur acteur = new Acteur(posX, posY, role);
		lstActeurs.add(acteur);
		return true;
	}
	
	//Supprime un acteur du plateau
	public boolean supprimerActeur(int posX, int posY)
	{
		for (Acteur acteur : lstActeurs)
		{
			if (posX == acteur.getPosX() && posY == acteur.getPosY() )
			{
				lstActeurs.remove(acteur);
				return true;
			}
		}

		return false;
	}

	//Rend un acteur principal et lui donne une couleur
	public void setPrincipal (Color c, Acteur a) {a.setPrincipal(c);}

	//Vérifie qu'il y a au moins un acteur principal
	public boolean principalPresent()
	{
		for (Acteur aExistant : this.lstActeurs)
			if (aExistant.estPrincipal())
				return true;

		return false;
	}
	
	// Méthode pour vérifier si une couleur de casting est déjà sur le plateau
	public boolean estCastingUtilise(Color couleurAVerifier)
	{
		for (Acteur acteur : this.lstActeurs)
		{
		// S'il est principal et qu'il a déjà cette couleur --> le casting est pris
		if (acteur.estPrincipal() && acteur.getCouleur().equals(couleurAVerifier))
		{
			return true;
		}
		}
		return false; // On a fini de chercher, la couleur est libre
	}

	// Méthode pour trouver quel acteur est à une position précise (X, Y)
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

	//Méthode finale qui va créer les fichiers .data
	public void CreerPlateau()
	{
		File filePrincipal = new File("Plateau");           //Crée le dossier Plateau
		File filePlateau   = new File("Plateau/" + this.nomPlateau); //Crée le dossier du plateau créé dans le dossier Plateau

		filePrincipal.mkdir();
		filePlateau  .mkdir();

		this.exportZone();
		this.exportActeur();
		this.exportCasting();
		this.exportPlateau();
	}

	/*----------------------------------------------*/
	/* Méthodes utilisées par CreerPlateau          */
	/*----------------------------------------------*/

	//Crée Zone.data
	public void exportZone()
	{
		String outputAtributZone = "";   //Stocke les attributs des zones
		String outputZonePlateau = "";   //Stocke les positions des zones
		String sRet;                     //Chaîne finale stockée

		for (int x = 0; x < this.nbLigne; x++)
		{
			for (int y = 0; y < this.nbColonne; y++)
			{
				if (this.tabZone[x][y] != null)
					outputZonePlateau += String.format("%03d",this.tabZone[x][y].getNumZone());
				else
					outputZonePlateau += "000";

				if (! this.lstZones.contains(this.tabZone[x][y]))
				{
					this.lstZones.add(tabZone[x][y]);
				}
			}
			outputZonePlateau += "\n";        //Chaque case du plateau contient un numéro
		}

		for (Zone zone : this.lstZones)
		{
			outputAtributZone += zone + "\n"; //Les attributs sont sous la forme : numéro, CouleurR, CouleurG, CouleurB
		}

		sRet = outputAtributZone + "Plateau :\n" + outputZonePlateau;

		try
		{
			PrintWriter sortie = new PrintWriter( new FileOutputStream("Plateau/" + nomPlateau + "/Zone.data") );
			sortie.print(sRet);
			sortie.close();
		} catch (Exception e) { e.printStackTrace(); }
	}

	//Crée Plateau.data
	public void exportPlateau()
	{
		try
		{
			PrintWriter sortie = new PrintWriter( new FileOutputStream("Plateau/" + nomPlateau + "/Plateau.data") );
			sortie.print(String.format("%03d", nbLigne)   + 
			             String.format("%03d", nbColonne) + 
						 String.format("%03d", tailleCase)); //Les attributs sont sous la forme : NbLigne, nBColonne, tailleCase
			sortie.close();
		} catch (Exception e) { e.printStackTrace(); }
	}

	//Crée Casting.data
	public void exportCasting()
	{
		String sRet = "";

		for (Acteur acteur : lstActeurs)
		{
			if (acteur.estPrincipal())
				sRet += String.format("%03d", acteur.getCouleur().getRed())   + 
			            String.format("%03d", acteur.getCouleur().getGreen()) + 
						String.format("%03d", acteur.getCouleur().getBlue())  + 
						String.format("%03d", acteur.getPosX()) + 
						String.format("%03d", acteur.getPosY()) + "\n"; //Les attributs sont sous la forme : CouleurR, CouleurG, CouleurB, PosX et PosY de l'acteur principal
		}

		try
		{
			PrintWriter sortie = new PrintWriter( new FileOutputStream("Plateau/" + nomPlateau + "/Casting.data") );
			sortie.print(sRet);
			sortie.close();
		} catch (Exception e) { e.printStackTrace(); }
	}

	//Crée Acteur.data
	public void exportActeur()
	{
		String sRet = "";

		for (Acteur acteur : lstActeurs)
		{
			sRet += acteur.getRole() +
			        String.format("%03d", acteur.getPosX()) +
			        String.format("%03d", acteur.getPosY()) + "\n"; //Les attributs sont sous la forme : Role, PosX, PosY
		}

		try
		{
			PrintWriter sortie = new PrintWriter( new FileOutputStream("Plateau/" + nomPlateau + "/Acteur.data") );
			sortie.print(sRet);
			sortie.close();
		} catch (Exception e) { e.printStackTrace(); }
	}
}