package controleur;

import ihm.FrameCreation;

import metier.PlateauJeu;
import metier.Acteur;
import metier.Casting;
import metier.Couleur;
import metier.CreateurPlateau;
import metier.Role;
import metier.Zone;
import java.awt.Color;

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

	//page 1
	public void initCreateur(String nomPlateau, int nbLigne, int nbColonne, int tailleCase, Role[] tabRole, Casting[] tabCasting)
	{
		this.metier = new CreateurPlateau(nomPlateau, nbLigne, nbColonne, tailleCase, tabRole, tabCasting);
	}

	//page 2
	public boolean modifierZone(int lig, int col, Zone zone) {return this.metier.modifierZone(lig, col, zone);}
	
	public boolean changerCouleurZone(Couleur couleur, Zone zone) {return this.metier.changerCouleurZone(couleur, zone);}

	public void zonePrecedente() {this.metier.zonePrecedente();}

	public void nouvelleZone() {this.metier.nouvelleZone();}

	//page 3
	public void CreerPlateau() {this.metier.CreerPlateau();}

	public boolean ajouterActeur  (Role role, int posX, int posY) {return this.metier.ajouterActeur(role, posX, posY);}

	public boolean supprimerActeur(int posX, int posY) {return supprimerActeur(posX, posY);}

	public void   setPrincipal (Color c, Acteur a) {this.metier.setPrincipal(c, a);}

	public static void main (String[] args) { new Controleur(); }
}
