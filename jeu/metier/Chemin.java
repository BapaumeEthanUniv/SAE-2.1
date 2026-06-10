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
		if (this.chemin.getFirst().getVoisins().contains(acteurAjouté))
		{
			this.chemin.addLast(acteurAjouté);
			return true;
		}

		if (this.chemin.getLast().getVoisins().contains(acteurAjouté))
		{
			this.chemin.addFirst(acteurAjouté);
			return true;
		}

		return false;
	}
}