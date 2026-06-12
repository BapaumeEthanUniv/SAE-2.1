package metier;

import java.util.ArrayList;

public class Chemin 
{
	private ArrayList<Acteur> chemin;
	private Casting           couleur;
	private PlateauJeu        plateau;

	//constructeur
	public Chemin(Casting couleur, PlateauJeu plateau)
	{
		this.chemin  = new ArrayList<Acteur>();
		this.couleur = couleur;
		this.plateau = plateau;
		this.chemin.add(this.plateau.getPrincipal(couleur));
	}

	// getter
	public ArrayList<Acteur> getChemin()  {return chemin;}
	public Casting           getCouleur() {return couleur;}

	// Permet de rajouté des chemin entre les sommets (acteur)
	public boolean ajouterChemin(Acteur acteurAjoute, ArrayList<Acteur> lstActeurs, Role rolePioche)
	{
		//System.out.println("Acteur : " + acteurAjoute.getRole().getLibelle() + " Pioche : " + rolePioche.getLibelle());
		if (acteurAjoute != null && (acteurAjoute.getRole().equals(rolePioche) || rolePioche.equals(Role.JOKER)) && ! this.chemin.contains(acteurAjoute))
		{
			//System.out.println("A côté de la queue : " + this.chemin.getLast().getVoisins().contains(acteurAjoute));
			//System.out.println("A côté de la tête : " + this.chemin.getFirst().getVoisins().contains(acteurAjoute));
			if (this.chemin.getLast().getVoisins().contains(acteurAjoute) &&
	            ! this.plateau.entreDeux(this.chemin.getLast(), acteurAjoute))
			// vérifie si le chemin passe bien un ligne, colonne, diago et ne passe pas par dessu un autre chemin sur le dernier acteur de la liste
			{
				this.plateau.ajouterArete(this.chemin.getLast(), acteurAjoute);
				this.chemin.addLast(acteurAjoute);
				return true;
			}
			else if (this.chemin.getFirst().getVoisins().contains(acteurAjoute) && 
			         ! this.plateau.entreDeux(this.chemin.getFirst(), acteurAjoute))
			 // vérifie si le chemin passe bien sur une ligne, colonne ou diagonal et pas par dessu un autre acteur sur le premier acteur de la liste
			{
				this.plateau.ajouterArete(this.chemin.getFirst(), acteurAjoute);
				this.chemin.addFirst(acteurAjoute);
				return true;
			}
		}

		return false;
	}
}
