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

	public ArrayList<Acteur> getChemin()  {return chemin;}
	public Casting           getCouleur() {return couleur;}

	public boolean ajouterChemin(Acteur acteurAjoute, ArrayList<Acteur> lstActeurs, Role rolePioche)
	{

		if (acteurAjoute != null && (acteurAjoute.getRole().equals(rolePioche) || rolePioche.equals(Role.JOKER))) 
		{
			if (this.chemin.getLast().getVoisins().contains(acteurAjoute) &&
	            this.plateau.entreDeux(this.chemin.getLast(), acteurAjoute))
			{
				this.chemin.addLast(acteurAjoute);
				this.plateau.ajouterArete(this.chemin.getLast(), acteurAjoute);
				return true;
			}

			if (this.chemin.getFirst().getVoisins().contains(acteurAjoute) && 
			    this.plateau.entreDeux(this.chemin.getFirst(), acteurAjoute))
			{
				this.chemin.addFirst(acteurAjoute);
				this.plateau.ajouterArete(this.chemin.getFirst(), acteurAjoute);
				return true;
			}
		}

		return false;
	}
}
