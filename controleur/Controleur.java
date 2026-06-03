package controleur;

import ihm.FrameSaisie;

import metier.Plateau;
import metier.Casting;
import metier.Role;

public class Controleur
{
	private Plateau 	metier;
	private FrameSaisie 	ihm;
	
	private Casting 	casting;
	private Role		role;
	
	public Controleur()
	{
		this.ihm = new FrameSaisie(this);
	}
	
	public int getNbCasting () { return this.casting.getNbCasting(); }
	public int getNbRole    () { return this.role   .getNbRole   (); }
	
	public String getCasting (int indice)
	{ 
		return Casting.valueOf ( indice ).toString(); 
	}
	
	public String getRole (int indice)
	{
		return Role.valueOf    ( indice ).toString();
	}
	
	public static void main (String[] args) { new Controleur(); }
}
