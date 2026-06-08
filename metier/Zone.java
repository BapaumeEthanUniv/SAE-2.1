package metier;

import java.awt.Color;

public class Zone
{
	//Attribut
	private        Couleur couleur;
	private static int     compteur;
	private        int     numZone;  //Numéro auto-incrémenté

	//Constructeur
	public Zone(Couleur couleur)
	{
		this.numZone = ++Zone.compteur; //Incrémentation du nombre de Zone + attribution du numéro de zone
		this.couleur = couleur;
	}

	//Getters
	public Couleur getCouleur ()    {return this.couleur;}                   //Retourne la couleur de l'énum Couleur.java
	public Color   getCouleurAwt () {return this.getCouleur().getCouleur();} //Retourne la couleur en objet awt.Color
	public int     getNumZone ()    {return this.numZone;}

	//Setters
	public void setNumZone(int numZone)     { this.numZone = numZone;}
	public void setCouleur(Couleur couleur) { this.couleur = couleur;}

	//Autres méthodes
	public String toString()
	{
		String sRet = String.format("%03d" , this.numZone);
		sRet+=        String.format("%03d" , this.couleur.getCouleur().getRed());
		sRet+=        String.format("%03d" , this.couleur.getCouleur().getGreen());
		sRet+=        String.format("%03d" , this.couleur.getCouleur().getBlue());
		return sRet;
	}
	
	public static void resetCompteur() // permet de reset le nombre de zones lors ce que la création du tableau est fini
	{
		Zone.compteur = 0;
	}

}
