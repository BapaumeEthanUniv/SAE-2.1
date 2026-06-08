package metier;

import java.awt.Color;

public enum Couleur
{
	ROSE		( "Rose"	, new Color (230,100,160)),
	ROUGE 		( "Rouge"	, new Color (255,64 , 64)),
	BORDEAUX	( "Bordeaux"	, new Color (109,  7, 26)),
	ORANGE 		( "Orange"  	, new Color (255,149,  9)),
	JAUNE		( "Jaune"	, new Color (255,228, 54)),
	VERT_CITRON 	( "Vert Citron"	, new Color (140,210, 40)),
	VERT		( "Vert"	, new Color ( 58,242, 75)),
	TURQUOISE	( "Turquoise"	, new Color ( 37,253,233)),
	BLEU		( "Bleu"	, new Color ( 40,110,220)),
	MAUVE		( "Mauve"	, new Color (212,115,212)),
	VIOLET		( "Violet"	, new Color (140, 60,200)),
	SAUMON 		( "Saumon"	, new Color (240,150,120)),
	MARRON 		( "Marron"	, new Color (140, 80, 30)),
	GRIS   		( "Gris"	, new Color (150,150,150));
	
	private String lib;
	private Color couleur;
	 
	Couleur (String lib, Color couleur) { this.lib = lib; this.couleur = couleur; }
	 
	public Color getCouleur() {return couleur;}

	public String getLib() {return lib;}

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
