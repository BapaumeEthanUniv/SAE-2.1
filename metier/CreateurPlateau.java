package metier;

import java.io.PrintWriter;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateurPlateau 
{
	private String   nomPlateau;

	private int      nbLigne;
	private int      nbColonne;

	private Role[]    tabRole;
	private Casting[] tabCasting;

	private Zone[][]   tabZone;

	private int       tailleCase;

	private ArrayList<Acteur> lstActeurs;

	public CreateurPlateau(String nomPlateau, int nbLigne, int nbColonne, int tailleCase, Role[] tabRole, Casting[] tabCasting)
	{
		this.nbLigne         = nbLigne;
		this.nbColonne       = nbColonne;
		this.tailleCase      = tailleCase;
		this.tabRole         = tabRole;
		this.tabCasting      = tabCasting;
	}

	public int getNbLigne() {return nbLigne;}

	public int getNbColonne() {return nbColonne;}

	public int getTailleCase() {return tailleCase;}

	public Role[] gettabRole() {return tabRole;}

	public boolean modifierZone(int lig, int col, int nZone)
	{
		if (lig > this.nbLigne || lig < 0 || col > this.nbColonne || col < this.nbColonne)
			return false;

		this.tabZone[lig][col].setNumZone(nZone);
		return true;
	}

	public boolean ajouterActeur  (Role role, int posX, int posY) 
	{
		Acteur acteur = new Acteur(posX, posY, role);

		if (! Arrays.asList(Role.values()).contains(role) || posX > this.nbLigne || posX < 0 || posY > this.nbColonne || posY < this.nbColonne)
			return false;

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
				acteur.supprimerVoisin();
				return true;
			}
		}

		return false;
	}
	public void CreerPlateau()
	{
		//appel de toute les fonctions d'export
	}

	public void exportZone()
	{
		String outputAtributZone = "";
		String outputAtributZonePlateau = "";
		String sRet;
		ArrayList<Zone> ZonesDistinctes;

		ZonesDistinctes = new ArrayList<Zone>();
		for (int y=0; y<this.tabZone.length; y++)
		{
			for (int x=0; x<this.tabZone[0].length; x++)
			{
				outputAtributZonePlateau+=String.format("%03d",tabZone[y][x].getNumZone());
				if (!ZonesDistinctes.contains(this.tabZone[y][x]))
				{
					ZonesDistinctes.add(tabZone[y][x]);
				}
			}
			outputAtributZonePlateau+="\n";
		}
		for (Zone zone : ZonesDistinctes)
		{
			outputAtributZone+=zone+"\n";
		}

		sRet = outputAtributZone+"_\n"+outputAtributZonePlateau;

		try
		{
			PrintWriter sortie = new PrintWriter( new FileOutputStream("../Plateau/" + nomPlateau + "Zone.data") );
			sortie.print(sRet);
			sortie.close();
		}catch (Exception e){ e.printStackTrace(); }
	}

	public void exportPlateau()
	{
		try
		{
			PrintWriter sortie = new PrintWriter( new FileOutputStream("../Plateau/" + nomPlateau + "/Plateau.data") );
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
			PrintWriter sortie = new PrintWriter( new FileOutputStream("../Plateau/" + nomPlateau + "/Casting.data") );
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
			PrintWriter sortie = new PrintWriter( new FileOutputStream("../Plateau/" + nomPlateau + "/Acteur.data") );
			sortie.print(sRet);
			sortie.close();
		}catch (Exception e){ e.printStackTrace(); }
	}
}
