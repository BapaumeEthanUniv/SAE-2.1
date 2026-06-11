package metier;

import java.util.ArrayList;

public class Pioche
{
	private ArrayList<Role>     lstRoleCarte  ;
	private ArrayList<Carte>    lstCarte      ;
	private int                 nbCartesTotal ;
	private Carte               carteActive   ;

	public Pioche(ArrayList<Role> lstRoles)
	{
		this.lstRoleCarte = lstRoles;
		this.creerPioche();
		this.nbCartesTotal = lstCarte.size();
	}

	public void creerPioche()
	{
		boolean[] typeCarte = {false, true};
		this.lstCarte       = new ArrayList<Carte>();

		for (boolean type : typeCarte)
		{
			for (Role role : this.lstRoleCarte)
			{
				this.lstCarte.add(new Carte(role, type));
			}
			
			this.lstCarte.add(new Carte(Role.JOKER, type));	// Carte Joker
		}
		
		int indice = (int)(Math.random()*getNbCarte());
		this.carteActive = this.lstCarte.get(indice);
	}

	public boolean piocherCarte()
	{
		int indice = (int)(Math.random()*getNbCarte());
		Carte tirer;
		if (this.finManche())
		{
			tirer = this.lstCarte.get(indice);
			this.lstCarte.remove(indice);
			this.carteActive = tirer;
			return true;
		}
		return false;
	}

	public boolean finManche()
	{
		return (this.getNbCarteFonce()==0);
	}

	public int getNbCarteTotal(){ return this.nbCartesTotal;  }

	public int getNbCarte()     { return this.lstCarte.size();}

	public Carte getCarte()     { return this.carteActive;    }

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
