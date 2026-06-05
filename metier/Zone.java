package metier;

import java.awt.Color;
import java.awt.color.*;
import java.util.ArrayList;

public class Zone
{
	private  Color couleur;
	private static int compteur;
	private int numZone;

	public Zone(Color couleur)
	{
		this.numZone=++Zone.compteur;
		this.couleur=couleur;
	}
	
	public Color getCouleur() {return this.couleur;}
	public int   getNumZone() {return this.numZone;}

	public void setNumZone(int numZone) {this.numZone = numZone;}

	public String toString()
	{
		String Sret = String.format("%03d" , this.numZone);
		Sret+=        String.format("%03d" , this.couleur.getRed());
		Sret+=        String.format("%03d" , this.couleur.getGreen());
		Sret+=        String.format("%03d" , this.couleur.getBlue());
		return Sret;
	}

	public void setCouleur(Color couleur)
	{
		this.couleur = couleur;
	}
	
	public static void main(String[] args)
	{
		Zone zone1 = new Zone(Color.RED);
		Zone zone2 = new Zone(Color.GREEN);
		Zone zone3 = new Zone(Color.BLUE);
		System.out.println(zone1);
		System.out.println(zone2);
		System.out.println(zone3);
	}

}
