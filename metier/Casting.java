package metier;

import java.awt.Color;

public enum Casting
{
	ROUGE 	("Rouge", Color.RED),
	VERT	("Vert", Color.GREEN ),
	BLEU	("Bleu", Color.BLUE ),
	MAUVE	("Mauve", Color.MAGENTA),
	JAUNE	("Jaune", Color.YELLOW),
	CYAN	("Cyan", Color.CYAN );
	
	private String libelle;
	private Color  couleur;
	
	Casting (String libelle, Color couleur) { this.libelle = libelle; this.couleur = couleur;}
	
	public String getLibelle() { return libelle; }

	public Color getCouleur() {return couleur;}
	
	public String toString()
	{
		return this.name().toLowerCase();
	}
	
	public static int getNbCasting() 
	{
		return Casting.values().length;
	}
	
	public static Casting valueOf(int ordinal)
	{
		return Casting.values()[ordinal];
	}
}
