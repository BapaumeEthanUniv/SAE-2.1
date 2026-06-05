package controleur;

import ihm.FrameCreation;

import metier.PlateauJeu;
import metier.Casting;
import metier.CreateurPlateau;
import metier.Role;
import metier.Zone;

public class Controleur
{
	private FrameCreation 	ihm;
	private CreateurPlateau metier;
	
	private Casting 		casting;
	private Role			role;
	
	public Controleur()
	{
		this.ihm = new FrameCreation(this);
	}
	
	public int getNbCasting () { return Casting.getNbCasting(); }
	public int getNbRole    () { return Role.getNbRole   (); }

	public Zone[][] getTabZone() {return metier.getTabZone();}
	public Zone     getZoneActive() {return metier.getZoneActive();}
	
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
