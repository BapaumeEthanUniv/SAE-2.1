package metier;

import java.awt.Color;

public enum Couleur
{

	ROSE        ( "Rose"        , new Color (230,100,160)),
	ROUGE       ( "Rouge"       , Color.RED) ,
	ROUGE_CLAIR ( "Rouge Clair" , new Color (255,64 ,64 )),
	BORDEAUX    ( "Bordeaux"    , new Color (109,7  ,26 )),
	ORANGE      ( "Orange"      , new Color (255,149,9  )),
	JAUNE       ( "Jaune"       , Color.YELLOW),
	VERT_CITRON ( "Vert Citron" , new Color (140,210,40 )),
	VERT        ( "Vert"        , Color.GREEN),
	VERT_FONCE  ( "Vert Foncé"  , new Color (0  ,128,0  )),
	CYAN        ( "Cyan"        , Color.CYAN) ,
	MAGENTA     ( "Magenta"     , Color.MAGENTA) ,
	VIOLET      ( "Violet"      , new Color (140,60 ,200)),
	BLEU        ( "Bleu"        , new Color (40 ,110,220)),
	BLEU_FONCE  ( "Bleu Foncé"  , new Color (0  ,0  ,255)),
	SAUMON      ( "Saumon"      , new Color (240,150,120)),
	MARRON      ( "Marron"      , new Color (140,80 ,30 )),
	VIOLET_PALE ( "Violet Pâle" , new Color (230,180,255)),
	BLEU_PALE   ( "Bleu Pâle"   , new Color (175,221,255)),
	VERT_PALE   ( "Vert Pâle"   , new Color (175,255,190)),
	ROSE_PALE   ( "Rose pale"   , new Color (255,180,240)),
	BEIGE       ( "Beige"       , new Color (255,215,175)),
	TURQUOISE   ( "turquoise"   , new Color (110,255,255)),
	GRIS_CLAIR  ( "Gris clair"  , new Color (200,200,200)),
	GRIS        ( "Gris"        , new Color (150,150,150));
	
	//Attribut
	private String lib;
	private Color couleur;
	
	Couleur (String lib, Color couleur) { this.lib = lib; this.couleur = couleur; }
	
	/*----------*/
	/* Méthodes */
	/*----------*/
	
	//Getters :
	public        Color      getCouleur()   {return couleur;}
	public        String     getLib()       {return lib;}
	public static int        getNbCouleur() {return Couleur.values().length;}

	// autres méthodes :
	public String toString()
	{
		return this.name();
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
