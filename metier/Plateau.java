package metier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class Plateau 
{
	public static final Color[]  TAB_CASTING = new Color[]  {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.YELLOW, Color.CYAN};
	public static final String[] TAB_ROLE    = new String[] {"Cascadeur", "Emotion", "Antagoniste", "Figurant"};

	private int      nbLigne;
	private int      nbColonne;
	
	private Color[]  tabCastingActif;

	private String[] tabRoleActif;

	private int[][]  tabZone;

	private int      tailleCase;

	private ArrayList<Acteur> lstActeurs;

	public Plateau(int nbLigne, int nbColonne, int nbCasting, int nbRole, int tailleCase, Color[] tabCastingActif, String[] tabRoleActif)
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

	public String[] getTabRoleActif() {return tabRoleActif;}

	public Color[]  getTabCastingActif() {return tabCastingActif;}

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

		if (! Arrays.asList(TAB_ROLE).contains(type) || posX > this.nbLigne || posX < 0 || posY > this.nbColonne || posY < this.nbColonne)
			return false;

		lstActeurs.add(acteur);
		acteur.majVoisin(lstActeurs);
		return true;
	}
	
	public boolean supprimerActeur(int posX, int posY)
	{
		for (Acteur acteur : lstActeurs)
		{
			if (posX == acteur.getPosX() && posY == acteur.getPoxY() )
			{
				lstActeurs.remove(acteur);
				acteur.supprimerVoisin();
				return true;
			}
		}

		return false;
	}
}
