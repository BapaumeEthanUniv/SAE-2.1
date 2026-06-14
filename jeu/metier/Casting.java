package metier;

import java.awt.Color;

public enum Casting
{
	ROUGE 	("Rouge", Color.RED),
	VERT	("Vert" , Color.GREEN ),
	BLEU	("Bleu" , Color.BLUE ),
	MAGENTA	("Mauve", Color.MAGENTA),
	JAUNE	("Jaune", new Color(253, 238, 0)),
	CYAN	("Cyan" , Color.CYAN );
	
	// Attributs
	private String libelle;
	private Color  couleur;
	
	// Constructeur
	Casting (String libelle, Color couleur) { this.libelle = libelle; this.couleur = couleur;}

	// Getters
	public        String getLibelle ()  { return libelle; }
	public        Color  getCouleur ()  {return couleur;}
	public static int    getNbCasting() {return Casting.values().length;}

    public static Casting getCasting(Color couleur)
    {
        for (Casting c : Casting.values())
        {
            if (c.getCouleur().equals(couleur))
                return c;
        }

        return null;
    }

    /* --------------------------*/
    /*    Méthodes utilitaires   */
    /* --------------------------*/

	public String toString   ()
	{
		return this.name();
	}
	
	public static Casting valueOf(int ordinal)
	{
		return Casting.values()[ordinal];
	}
}
