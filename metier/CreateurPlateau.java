package metier;

import java.io.PrintWriter;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.File;


import java.util.ArrayList;
import java.util.Arrays;

public class CreateurPlateau 
{

	private String    nomPlateau;

	private int       nbLigne;
	private int       nbColonne;

	private Role[]    tabRole;
	private Casting[] tabCasting;

	private Zone[][]  tabZone;
	private Zone      zoneActive;

	private int       tailleCase;

	private ArrayList<Acteur> lstActeurs;
	private ArrayList<Zone>   lstZones;

	public CreateurPlateau(String nomPlateau, int nbLigne, int nbColonne, int tailleCase, Role[] tabRole, Casting[] tabCasting)
	{
		this.nomPlateau      = nomPlateau;
		this.nbLigne         = nbLigne;
		this.nbColonne       = nbColonne;
		this.tailleCase      = tailleCase;
		this.tabRole         = tabRole;
		this.tabCasting      = tabCasting;
		this.tabZone = new Zone[this.nbLigne][this.nbColonne];
		this.lstZones = new ArrayList<Zone>();
		Zone.resetCompteur();
		this.lstActeurs = new ArrayList<Acteur>();

        this.nouvelleZone();
	}

    public String getNomPlateau() { return this.nomPlateau; }

	public int getNbLigne() {return nbLigne;}

	public int getNbColonne() {return nbColonne;}

	public int getTailleCase() {return tailleCase;}

	public Role[] getTabRole() {return tabRole;}

	public Casting[] getTabCasting() {return tabCasting;}

	public Zone getZoneActive() {return this.zoneActive;}

	public Zone[][] getTabZone() {return tabZone;}

	public ArrayList<Zone> getLstZones() {return lstZones;}

	public void setZoneActive(Zone zone) {this.zoneActive = zone;}

	public ArrayList<Acteur> getLstActeurs() {return lstActeurs;}


	public boolean modifierZone(int lig, int col, Zone zone)
	{
		boolean estPremier   = true;
		boolean adjacent     = false;
		boolean coulAdjacent = false;

		//verif que la zone est la première placée
		for(int l = 0; l < this.tabZone.length; l++)
		{
			for(int c = 0; c < this.tabZone[l].length; c++)
			{
				if (zone == tabZone[l][c])
					estPremier = false;
			}
		}

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

		//verif qu'on ne place pas en dehors du plateau
		if (lig >= this.nbLigne || lig < 0 ||
			col >= this.nbColonne || col < 0 ||
			(! estPremier && ! adjacent) || coulAdjacent)
			return false;

		this.tabZone[lig][col]=zone;
		return true;
	}

    public void effacerZone(int lig, int col)
    {
        this.tabZone[lig][col] = null;
    }

	public boolean changerCouleurZone(Couleur couleur, Zone zone)
	{
		for (int lig = 0; lig < tabZone.length; lig ++)
			for (int col = 0; col < tabZone[lig].length; col ++)
				if (tabZone[lig][col] == zone)
				{
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
								if (tabZone[l][c] != null && couleur.getCouleur() == tabZone[l][c].getCouleur() && tabZone[l][c] != zone)
									return false;
						}
					}
				}

		zone.setCouleur(couleur.getCouleur());
		return true;
	}

	public void nouvelleZone()
	{
		Zone zone = new Zone(Couleur.SAUMON.getCouleur());
		this.lstZones.add(zone);
		this.setZoneActive(zone);
	}

	public void zonePrecedente()
	{
		if (this.lstZones.indexOf(this.zoneActive) > 0)
			this.zoneActive = this.lstZones.get(this.lstZones.indexOf(this.zoneActive) - 1);
	}

	public void zoneSuivante()
	{
		if (this.lstZones.indexOf(this.zoneActive) < this.lstZones.size() - 1)
			this.zoneActive = this.lstZones.get(this.lstZones.indexOf(this.zoneActive) + 1);
	}

	public boolean ajouterActeur  (Role role, int posX, int posY) 
	{
		boolean placeLibre = true;

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

	public void   setPrincipal (Color c, Acteur a) {a.setPrincipal(c);}

	public void CreerPlateau()
	{
		File filePrincipal = new File("Plateau");
        File filePlateau = new File("Plateau/" + this.nomPlateau);

		filePrincipal.mkdir();
        filePlateau  .mkdir();

		this.exportZone();
		this.exportActeur();
		this.exportCasting();
		this.exportPlateau();
	}

	public void exportZone()
	{
		String outputAtributZone = "";
		String outputAtributZonePlateau = "";
		String sRet;
		ArrayList<Zone> ZonesDistinctes;

		ZonesDistinctes = new ArrayList<Zone>();
		for (int y=0; y<this.getNbColonne(); y++)
		{
			for (int x=0; x<this.getNbLigne(); x++)
			{
				outputAtributZonePlateau+=String.format("%03d",this.tabZone[x][y].getNumZone());
				if (!ZonesDistinctes.contains(this.tabZone[x][y]))
				{
					ZonesDistinctes.add(tabZone[x][y]);
				}
			}
			outputAtributZonePlateau+="\n";
		}
		for (Zone zone : ZonesDistinctes)
		{
			outputAtributZone+=zone+"\n";
		}

		sRet = outputAtributZone+"Plateau :\n"+outputAtributZonePlateau;

		try
		{
			PrintWriter sortie = new PrintWriter( new FileOutputStream("Plateau/" + nomPlateau + "/Zone.data") );
			sortie.print(sRet);
			sortie.close();
		}catch (Exception e){ e.printStackTrace(); }
	}

	public void exportPlateau()
	{
		try
		{
			PrintWriter sortie = new PrintWriter( new FileOutputStream("Plateau/" + nomPlateau + "/Plateau.data") );
			sortie.print(String.format("%03d", nbLigne) + String.format("%03d", nbLigne) + String.format("%03d", tailleCase));
			sortie.close();
		}catch (Exception e){ e.printStackTrace(); }
	}

	public void exportCasting()
	{
		String sRet = "";

		for (Acteur acteur : lstActeurs)
		{
			if (acteur.estPrincipal())
				sRet += String.format("%03d", acteur.getCouleur().getRed()) + 
			            String.format("%03d", acteur.getCouleur().getRed()) + 
						String.format("%03d", acteur.getCouleur().getRed()) + 
						String.format("%03d", acteur.getPosX()) + 
						String.format("%03d", acteur.getPosY()) + "\n";
		}

		try
		{
			PrintWriter sortie = new PrintWriter( new FileOutputStream("Plateau/" + nomPlateau + "/Casting.data") );
			sortie.print(sRet);
			sortie.close();
		}catch (Exception e){ e.printStackTrace(); }
	}

	public void exportActeur()
	{
		String sRet = "";

		for (Acteur acteur : lstActeurs)
		{
			sRet += acteur.getRole() + 
			        String.format("%03d", acteur.getPosX()) + 
			        String.format("%03d", acteur.getPosY()) + "\n";
		}

		try
		{
			PrintWriter sortie = new PrintWriter( new FileOutputStream("Plateau/" + nomPlateau + "/Acteur.data") );
			sortie.print(sRet);
			sortie.close();
		}catch (Exception e){ e.printStackTrace(); }
	}




	/*main de test
	public static void main(String[] args) 
	{
		 
		CreateurPlateau plato;
		Zone zone1;
		Zone zone2;
		Zone zone3;
		plato = new CreateurPlateau("Bah non frère c'est vraiment pas drôle", 3, 3, 5, null, null);
		zone1 = new Zone(Color.BLUE);
		zone2 = new Zone(Color.RED);
		zone3 = new Zone(Color.GREEN);
		plato.modifierZone(1-1, 1-1, zone1);
		plato.modifierZone(1-1, 2-1, zone1);
		plato.modifierZone(2-1, 2-1, zone1);
		plato.modifierZone(2-1, 1-1, zone2);
		plato.modifierZone(3-1, 1-1, zone2);
		plato.modifierZone(3-1, 2-1, zone2);
		plato.modifierZone(1-1, 3-1, zone3);
		plato.modifierZone(2-1, 3-1, zone3);
		plato.modifierZone(3-1, 3-1, zone3);

		plato.ajouterActeur(Role.ANTAGONISTE, 0, 0);
		plato.ajouterActeur(Role.FIGURANT, 1, 0);
		plato.lstActeurs.get(0).setPrincipal(Color.RED);
		plato.lstActeurs.get(1).setPrincipal(Color.BLUE);

		plato.CreerPlateau();
	}*/
}
