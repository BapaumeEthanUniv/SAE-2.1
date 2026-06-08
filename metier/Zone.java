package metier;

import java.awt.Color;
import java.awt.color.*;
import java.util.ArrayList;

public class Zone
{
	private  Couleur couleur;
	private static int compteur;
	private int numZone;

	public Zone(Couleur couleur)
	{
		this.numZone=++Zone.compteur;
		this.couleur=couleur;
	}
	
	public Couleur getCouleur() {return this.couleur;}
	public int   getNumZone() {return this.numZone;}

	public void setNumZone(int numZone) {this.numZone = numZone;}

	public String toString()
	{
		String Sret = String.format("%03d" , this.numZone);
		Sret+=        String.format("%03d" , this.couleur.getCouleur().getRed());
		Sret+=        String.format("%03d" , this.couleur.getCouleur().getGreen());
		Sret+=        String.format("%03d" , this.couleur.getCouleur().getBlue());
		return Sret;
	}

	public void setCouleur(Couleur couleur)
	{
		this.couleur = couleur;
	}

	public static void resetCompteur()
	{
		Zone.compteur = 0;
	}

}
