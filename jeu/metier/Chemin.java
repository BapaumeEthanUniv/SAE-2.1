package metier;

import java.util.ArrayList;

public class Chemin 
{
	private ArrayList<Acteur> chemin;
	private Casting          couleur;
	private PlateauJeu       plateau;

	public Chemin(Casting couleur, PlateauJeu plateau)
	{
		this.chemin  = new ArrayList<Acteur>();
		this.couleur = couleur;
		this.plateau = plateau;
		this.chemin.add(this.plateau.getPrincipal(couleur));
	}

	public boolean ajouterChemin(Acteur acteurAjouté, ArrayList<Acteur> lstActeurs)
	{
		if (this.estAdjacentQueue(acteurAjouté))
		{
			this.chemin.addLast(acteurAjouté);
			return true;
		}

		if (this.estAdjacentTete(acteurAjouté))
		{
			this.chemin.addFirst(acteurAjouté);
			return true;
		}

		return false;
	}

	public boolean estAdjacentTete(Acteur acteur)
	{
		int ligActeur = acteur.getPosX();
		int colActeur = acteur.getPosY();

		int ligTete   = this.chemin.getFirst().getPosX();
		int colTete   = this.chemin.getFirst().getPosY();

		if ( Math.abs(ligActeur - ligTete) <= 1 &&
		     Math.abs(colActeur - colTete) <= 1)
			 return true;

		return false;
	}

	public boolean estAdjacentQueue(Acteur acteur)
	{
		int ligActeur = acteur.getPosX();
		int colActeur = acteur.getPosY();

		int ligQueue  = this.chemin.getLast().getPosX();
		int colQueue  = this.chemin.getLast().getPosY();

		if ( Math.abs(ligActeur - ligQueue) <= 1 &&
		     Math.abs(colActeur - colQueue) <= 1)
			 return true;

		return false;
	}
}
