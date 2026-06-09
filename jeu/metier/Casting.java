package metier;

import java.awt.Color;

public enum Casting
{
	ROUGE 	("Rouge", Color.RED),
	VERT	("Vert" , Color.GREEN ),
	BLEU	("Bleu" , Color.BLUE ),
	MAGENTA	("Mauve", Color.MAGENTA),
	JAUNE	("Jaune", Color.YELLOW),
	CYAN	("Cyan" , Color.CYAN );
	
	//Attribut
	private String libelle;
	private Color  couleur;
	
	//Constructeur
	Casting (String libelle, Color couleur) { this.libelle = libelle; this.couleur = couleur;}

	//getters
	public        String getLibelle ()  { return libelle; }
	public        Color  getCouleur ()  {return couleur;}
	public static int    getNbCasting() {return Casting.values().length;}

	// autres méthodes
	public String toString   ()
	{
		return this.name();
	}
	
	public static Casting valueOf(int ordinal)
	{
		return Casting.values()[ordinal];
	}

	public static Casting getCasting(Color couleur)
	{
		for (Casting c : Casting.values())
		{
			if (c.getCouleur().equals(couleur))
				return c;
		}

		return null;
	}
}
