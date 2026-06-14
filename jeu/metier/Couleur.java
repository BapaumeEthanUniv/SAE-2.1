package metier;

import java.awt.Color;

public enum Couleur
{

	ROSE        ( "Rose"        , new Color (230,100,160)),
	ROUGE	    ( "Rouge"       , new Color (255,64 ,64 )),
	ORANGE      ( "Orange"      , new Color (255,149,9  )),
	JAUNE       ( "Jaune"       , new Color (252,220,18 )),
	VERT_CITRON ( "Vert Citron" , new Color (140,210,40 )),
	VERT_FONCE  ( "Vert Foncé"  , new Color (0  ,128,0  )),
	VIOLET      ( "Violet"      , new Color (140,60 ,200)),
	BLEU        ( "Bleu"        , new Color (40 ,110,220)),
	SAUMON      ( "Saumon"      , new Color (240,150,120)),
	MARRON      ( "Marron"      , new Color (140,80 ,30 )),
	VIOLET_PALE ( "Violet Pâle" , new Color (230,180,255)),
	BLEU_PALE   ( "Bleu Pâle"   , new Color (175,221,255)),
	VERT_PALE   ( "Vert Pâle"   , new Color (175,255,190)),
	ROSE_PALE   ( "Rose Pâle"   , new Color (255,180,240)),
	BEIGE       ( "Beige"       , new Color (255,215,175)),
	TURQUOISE   ( "Turquoise"   , new Color (110,255,255)),
	GRIS_CLAIR  ( "Gris clair"  , new Color (200,200,200)),
	GRIS        ( "Gris"        , new Color (150,150,150));
	
	// Attributs
	private String lib;
	private Color couleur;
	
	Couleur (String lib, Color couleur) { this.lib = lib; this.couleur = couleur; }

	// Getters
	public        Color      getCouleur()   {return this.couleur           ;}
	public        String     getLib()       {return this.lib               ;}
	public static int        getNbCouleur() {return Couleur.values().length;}


    /* --------------------------*/
    /*    Méthodes utilitaires   */
    /* --------------------------*/

	public String toString()
	{
		return this.lib;
	}
	
	public static Couleur valueOf(int ordinal)
	{
		return Couleur.values()[ordinal];
	}
	
	public static Couleur couleurCorrespondante(Color color)
	{
		
		Couleur[] lstCouleurs = Couleur.values();
		for (Couleur c : lstCouleurs)
		{
			if (c.getCouleur().equals(color))
			{
				return c;
			}
		}
		return null;
	}
}
