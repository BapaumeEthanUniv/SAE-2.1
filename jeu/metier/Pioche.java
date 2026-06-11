package metier;

import java.util.ArrayList;

public class Pioche
{
	private ArrayList<Role>     lstRoleCarte  ;
	private ArrayList<Carte>    lstCarte      ;
	private int                 nbCartesTotal ;

	public Pioche(ArrayList<Role> lstRoles)
	{
		this.lstRoleCarte = lstRoles;
		this.creerPioche();
		this.nbCartesTotal = lstCarte.size();
	}

	public void creerPioche()
	{
		boolean[] typeCarte = {false, true};

		for    (boolean type : typeCarte)
		{
			for(Role    role : this.lstRoleCarte)
			{
				this.lstCarte.add(new Carte(role, type));
			}
			
			this.lstCarte.add(new Carte(null, type));	// Carte Joker
		}
	}

	public Carte getCarte()
	{
		int indice = (int)Math.random()*getNbCarte();
		Carte tirer;
		tirer = this.lstCarte.get(indice);
		this.lstCarte.remove(indice);
		return tirer;
	}

	public boolean finManche()
	{
		return (this.getNbCarteFonce()==0);
	}

	public int getNbCarteTotal(){return this.nbCartesTotal;}

	public int getNbCarte() { return this.lstCarte.size();}

	public int getNbCarteFonce()
	{
		int nbCarteFonce = 0;
		for (Carte carte : this.lstCarte)
		{
			if (carte.estFonce())
				nbCarteFonce++;
		}
		return nbCarteFonce;
	}
}
