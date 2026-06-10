package metier;

import java.util.ArrayList;

public class Pioche
{
	//private Controleur          ctrl        ;
	private PlateauJeu		        metier      ;

	private String[]            lstRoleCarte;
	private ArrayList<Carte>    lstCarte    ;
	private int                 nbCartes    ;
	private int                 cptCarte    ;
	private int                 nbCarteGrise;
	private int                 nbCarteGriseTotale;

	public Pioche(String[] roleDispo)
	{
		this.lstRoleCarte = new String[roleDispo.length];
		this.lstRoleCarte = roleDispo;
	}

	public void creerPioche(int nbCartes)
	{
		String[] typeCarte = new String[] {"Clair", "Foncé"};
		this.nbCartes = nbCartes;
		this.nbCarteGrise = 0;
		this.nbCarteGriseTotale = this.lstRoleCarte.length + 1;

		for( String type : typeCarte)
		{
			for(String role : this.lstRoleCarte)
			{
				this.lstCarte.add(new Carte(role, type));
			}
		}
	}

	public Carte getCarte(int indice)
	{
		Carte tirer;
		tirer = this.lstCarte.get(indice);
		this.lstCarte.remove(indice);
		if(tirer.getType().equals("Foncé"))
			this.nbCarteGrise++;
		return tirer;
	}

	public boolean finManche()
	{
		if(this.nbCarteGrise == this.nbCarteGriseTotale)
		{
			this.nbCarteGrise = 0;
			return true;
		}
		return false;
	}

	public int getNbCarte(){return this.nbCartes;}
}
