package metier;

import metier.Casting;

import java.awt.Color;

public enum Couleur
{
	GOMME		( "Gomme"	, Color.WHITE		) ,
	ROSE		( "Rose"	, new Color (230,100,160)),
	ROUGE		( "Rouge"	, Casting.ROUGE		) ,
	BORDEAUX	( "Bordeaux"	, new Color (109,7  ,26 )),
	ORANGE 		( "Orange"  	, new Color (255,149,9  )),
	JAUNE		( "Jaune"	, Color.YELLOW		) ,
	VERT_CITRON 	( "Vert Citron"	, new Color (140,210,40 )),
	VERT		( "Vert"	, Color.GREEN		) ,
	CYAN		( "Cyan"	, Color.CYAN		) ,
	MAGENTA		( "Magenta"	, Color.MAGENTA		) ,
	VIOLET		( "Violet"	, new Color (140,60 ,200)),
	BLEU		( "Bleu"	, new Color (40 ,110,220)),
	SAUMON 		( "Saumon"	, new Color (240,150,120)),
	MARRON 		( "Marron"	, new Color (140,80 ,30 )),
	GRIS   		( "Gris"	, new Color (150,150,150));
	 
	private String couleur;
	 
	public Couleur (String couleur) { this.couleur = couleur; }
	 
	public String toString()
	{
		return this.name();
	}
	
	public static int getNbCouleur() 
	{
		return Couleur.values().length;
	}
	
	public static Couleur valueOf(int ordinal)
	{
		return Couleur.values()[ordinal];
	}
}
