package metier;

import java.io.PrintWriter;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.File;


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
		this.nomPlateau      = nomPlateau;
		this.nbLigne         = nbLigne;
		this.nbColonne       = nbColonne;
		this.tailleCase      = tailleCase;
		this.tabRole         = tabRole;
		this.tabCasting      = tabCasting;
		this.tabZone = new Zone[this.nbLigne][this.nbColonne];
	}

	public int getNbLigne() {return nbLigne;}

	public int getNbColonne() {return nbColonne;}

	public int getTailleCase() {return tailleCase;}

	public Role[] gettabRole() {return tabRole;}

	public boolean modifierZone(int lig, int col, Zone zone)
	{
		if (lig > this.nbLigne || lig < 0 || col > this.nbColonne || col < 0)
			return false;

		this.tabZone[lig][col]=zone;
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
		File filePrincipal = new File("Plateau");
        File filePlateau = new File("Plateau/" + this.nomPlateau);

		filePrincipal.mkdir();
        filePlateau  .mkdir();
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
				System.out.println(y+" "+x+" "+this.tabZone[x][y]+this.tabZone[1][1]);
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

		sRet = outputAtributZone+"_\n"+outputAtributZonePlateau;

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

	public static void main(String[] args) 
	{
		 
		CreateurPlateau plato;
		Zone zone1;
		Zone zone2;
		Zone zone3;
		plato = new CreateurPlateau("squeezie", 3, 3, 5, null, null);
		zone1 = new Zone(Color.BLUE);
		zone2 = new Zone(Color.RED);
		zone3 = new Zone(Color.GREEN);
		System.out.println(plato.modifierZone(1-1, 1-1, zone1));
		
		plato.modifierZone(1-1, 2-1, zone1);
		plato.modifierZone(2-1, 2-1, zone1);
		plato.modifierZone(2-1, 1-1, zone2);
		plato.modifierZone(3-1, 1-1, zone2);
		plato.modifierZone(3-1, 2-1, zone2);
		plato.modifierZone(1-1, 3-1, zone3);
		plato.modifierZone(2-1, 3-1, zone3);
		plato.modifierZone(3-1, 3-1, zone3);
		System.out.println(plato.getNbLigne());
		System.out.println(plato.getNbColonne());
		plato.CreerPlateau();
		plato.exportZone();
	}
}
