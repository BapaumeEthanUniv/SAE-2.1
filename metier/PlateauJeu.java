package metier;

import java.util.ArrayList;

public class PlateauJeu 
{
	private int      nbLigne;
	private int      nbColonne;
	
	private Casting[] tabCastingActif;

	private Role[]    tabRoleActif;

	private int[][]   tabZone;

	private Casting[][] tabArrete;

	private int       tailleCase;

	private ArrayList<Acteur> lstActeurs;

	public PlateauJeu()//a compléter ya absolument rien la dans le constructeur
	{

	}

	public int getNbLigne() {return nbLigne;}

	public int getNbColonne() {return nbColonne;}

	public int getTailleCase() {return tailleCase;}

	public Role[] getTabRoleActif() {return tabRoleActif;}

	public Casting[] getTabCastingActif() {return tabCastingActif;}

}
