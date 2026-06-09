package metier;

import java.util.ArrayList;
import java.awt.Color;

public class PlateauJeu 
{
	private int      		nbLigne;
	private int      		nbColonne;
	
	private Casting[] 		tabCastingActif;

	private Role[]    		tabRoleActif;

	private int[][]   		tabZone;

	private Casting[][] 		tabArrete;

	private int       		tailleCase;

	private ArrayList<Acteur> 	lstActeurs;

	public PlateauJeu()//a compléter ya absolument rien la dans le constructeur
	{

	}

	public int getNbLigne() {return nbLigne;}

	public int getNbColonne() {return nbColonne;}

	public int getTailleCase() {return tailleCase;}

	public Role[] getTabRoleActif() {return tabRoleActif;}

	public Casting[] getTabCastingActif() {return tabCastingActif;}

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
