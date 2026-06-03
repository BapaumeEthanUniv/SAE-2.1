package metier;

import java.util.ArrayList;

public class Plateau 
{
	private int      nbLigne;
	private int      nbColonne;
	
	private Casting[] tabCastingActif;

	private Role[]    tabRoleActif;

	private int[][]   tabZone;

	private int       tailleCase;

	private ArrayList<Acteur> lstActeurs;

	public Plateau(int nbLigne, int nbColonne, int tailleCase, Casting[] tabCastingActif, Role[] tabRoleActif)
	{
		this.nbLigne         = nbLigne;
		this.nbColonne       = nbColonne;
		this.tailleCase      = tailleCase;
		this.tabCastingActif = tabCastingActif;
		this.tabRoleActif    = tabRoleActif;
	}

	public int getNbLigne() {return nbLigne;}

	public int getNbColonne() {return nbColonne;}

	public int getTailleCase() {return tailleCase;}

	public Role[] getTabRoleActif() {return tabRoleActif;}

	public Casting[] getTabCastingActif() {return tabCastingActif;}

	public boolean modifierZone(int lig, int col, int nZone)
	{
		if (lig > this.nbLigne || lig < 0 || col > this.nbColonne || col < this.nbColonne)
			return false;

		this.tabZone[lig][col] = nZone;
		return true;
	}

	public boolean ajouterActeur  (String type, int posX, int posY) 
	{
		Acteur acteur = new Acteur(posX, posY, type);

		if (posX > this.nbLigne || posX < 0 || posY > this.nbColonne || posY < this.nbColonne)
			return false;

		lstActeurs.add(acteur);
		acteur.majVoisins(lstActeurs);
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
}
