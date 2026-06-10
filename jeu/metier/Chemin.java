package metier;

import java.util.ArrayList;
import java.awt.Point;

public class Chemin 
{
	private ArrayList<Point> chemin;
	private Casting          couleur;
	private PlateauJeu       plateau;

	public Chemin(Casting couleur, PlateauJeu plateau)
	{
		this.chemin  = new ArrayList<Point>();
		this.couleur = couleur;
		this.plateau = plateau;
		this.chemin.add(new Point(this.plateau.getPrincipal(this.couleur).getPosX(), this.plateau.getPrincipal(this.couleur).getPosY()));
	}

	public boolean ajouterChemin(Acteur acteurAjouté, ArrayList<Acteur> lstActeurs)
	{
		if (this.estAdjacentQueue(acteurAjouté))
		{
			this.chemin.addLast(new Point(acteurAjouté.getPosX(), acteurAjouté.getPosY()));
			return true;
		}

		if (this.estAdjacentTete(acteurAjouté))
		{
			this.chemin.addFirst(new Point(acteurAjouté.getPosX(), acteurAjouté.getPosY()));
			return true;
		}

		return false;
	}

	public boolean estAdjacentTete(Acteur acteur)
	{
		int ligActeur = acteur.getPosX();
		int colActeur = acteur.getPosY();

		int ligTete   = (int) this.chemin.getFirst().getX();
		int colTete   = (int) this.chemin.getFirst().getY();

		if ( Math.abs(ligActeur - ligTete) <= 1 &&
		     Math.abs(colActeur - colTete) <= 1)
			 return true;

		return false;
	}

	public boolean estAdjacentQueue(Acteur acteur)
	{
		int ligActeur = acteur.getPosX();
		int colActeur = acteur.getPosY();

		int ligQueue  = (int) this.chemin.getLast().getX();
		int colQueue  = (int) this.chemin.getLast().getY();

		if ( Math.abs(ligActeur - ligQueue) <= 1 &&
		     Math.abs(colActeur - colQueue) <= 1)
			 return true;

		return false;
	}
}
