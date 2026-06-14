package metier;

import java.util.ArrayList;

public class Chemin 
{
	// Attributs
	private ArrayList<Acteur> chemin;
	private Casting           couleur;
	private PlateauJeu        plateau;

	// Constructeur
	public Chemin(Casting couleur, PlateauJeu plateau)
	{
		this.chemin  = new ArrayList<Acteur>();
		this.couleur = couleur;
		this.plateau = plateau;
		this.chemin.add(this.plateau.getPrincipal(couleur));
	}

	// Getters
	public ArrayList<Acteur> getChemin()  {return chemin;}
	public Casting           getCouleur() {return couleur;}


    /* --------------------------*/
    /*    Méthodes utilitaires   */
    /* --------------------------*/

	// Ajoute l'acteur en paramètre dans le chemin
	public boolean ajouterChemin(Acteur acteurAjoute, ArrayList<Acteur> lstActeurs, Role rolePioche)
	{
		if (acteurAjoute != null && (acteurAjoute.getRole().equals(rolePioche) || rolePioche.equals(Role.JOKER)) && ! this.chemin.contains(acteurAjoute)) //Vérification que la carte pioché permet le placement
		{
			if (this.chemin.getLast().getVoisins().contains(acteurAjoute) &&
			    ! this.plateau.entreDeux(this.chemin.getLast(), acteurAjoute)) //Vérification que l'acteur ajouté est voisin avec la queue du chemin et que l'ajout ne fasse pas croiser les arrêtes
			{
				this.plateau.ajouterArete(this.chemin.getLast(), acteurAjoute);
				this.chemin.addLast(acteurAjoute);
				return true;
			}
			else if (this.chemin.getFirst().getVoisins().contains(acteurAjoute) && 
			         ! this.plateau.entreDeux(this.chemin.getFirst(), acteurAjoute)) //Vérification que l'acteur ajouté est voisin avec la tête du chemin et que l'ajout ne fasse pas croiser les arrêtes
			{
				this.plateau.ajouterArete(this.chemin.getFirst(), acteurAjoute);
				this.chemin.addFirst(acteurAjoute);
				return true;
			}
		}

		return false;
	}
}
