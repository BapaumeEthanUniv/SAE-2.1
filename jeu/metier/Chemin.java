package metier;

import java.util.ArrayList;

public class Chemin 
{
	private ArrayList<Acteur> chemin;
	private Casting           couleur;
	private PlateauJeu        plateau;

	public Chemin(Casting couleur, PlateauJeu plateau)
	{
		this.chemin  = new ArrayList<Acteur>();
		this.couleur = couleur;
		this.plateau = plateau;
		this.chemin.add(this.plateau.getPrincipal(couleur));
	}

	public ArrayList<Acteur> getChemin() {return chemin;}

	public boolean ajouterChemin(Acteur acteurAjouté, ArrayList<Acteur> lstActeurs, Role rolePioche)
	{
		if (acteurAjouté != null && acteurAjouté.getRole().equals(rolePioche)) 
		{
			if (this.chemin.getLast().getVoisins().contains(acteurAjouté) &&
	            this.plateau.entreDeux(this.chemin.getLast(), acteurAjouté))
			{
				this.chemin.addLast(acteurAjouté);
				this.plateau.ajouterArete(this.chemin.getLast(), acteurAjouté);
				return true;
			}

			if (this.chemin.getFirst().getVoisins().contains(acteurAjouté) && 
			    this.plateau.entreDeux(this.chemin.getFirst(), acteurAjouté))
			{
				this.chemin.addFirst(acteurAjouté);
				this.plateau.ajouterArete(this.chemin.getFirst(), acteurAjouté);
				return true;
			}
		}

		return false;
	}
}
