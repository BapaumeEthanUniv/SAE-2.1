package controleur;

import ihm.FrameCreation;

import metier.PlateauJeu;
import metier.Casting;
import metier.Role;

public class Controleur
{
	private PlateauJeu 		metier;
	private FrameCreation 	ihm;
	
	private Casting 		casting;
	private Role			role;
	
	public Controleur()
	{
		this.ihm = new FrameCreation(this);
	}
	
	public int getNbCasting () { return this.casting.getNbCasting(); }
	public int getNbRole    () { return this.role   .getNbRole   (); }
	
	public String getCasting (int indice)
	{ 
		return Casting.valueOf ( indice ).getLibelle(); 
	}
	
	public String getRole (int indice)
	{
		return Role.valueOf    ( indice ).getLibelle();
	}
	
	public static void main (String[] args) { new Controleur(); }
}
