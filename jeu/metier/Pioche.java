package metier;

import controleur.Controleur;

import java.util.ArrayList;

public class Pioche
{
	private ArrayList<Role>     lstRoleCarte  ;
	private ArrayList<Carte>    lstCarte      ;
	private int                 nbCartesTotal ;
	private Carte               carteActive   ;
	public Controleur           ctrl          ;

	public Pioche(ArrayList<Role> lstRoles, Controleur ctrl)
	{
		this.lstRoleCarte = lstRoles;
		this.ctrl = ctrl;
		this.creerPioche();
		this.nbCartesTotal = lstCarte.size();
	}

	public void creerPioche()
	{
		boolean[] typeCarte = {false, true};
		this.lstCarte       = new ArrayList<Carte>();
		System.out.println(lstRoleCarte);
		for (boolean type : typeCarte)
		{
			for (Role role : this.lstRoleCarte)
			{
				this.lstCarte.add(new Carte(role, type));
			}
			
			this.lstCarte.add(new Carte(Role.JOKER, type));	// Carte Joker
		}
		
		this.piocherCarte();
	}

	public boolean piocherCarte()
	{
		int indice = (int)(Math.random()*getNbCarte()); //a modifier pour le debug si on veut set une seed pour un exemple particulier
		Carte tirer;
		for (Carte c : this.lstCarte)
		{
			System.out.print(c.getRole()+"|"+c.estFonce()+" ");
		}
		if (this.finManche())
		{
			System.out.println(" nouvelle manche ");
			this.creerPioche();
			this.nouvelleManche();
		}
		else
		{
			tirer = this.lstCarte.get(indice);
			this.lstCarte.remove(indice);
			this.carteActive = tirer;
		}
		return true;
	}

	public boolean finManche()
	{
		return (this.getNbCarteFonce()==0);
	}

	public void nouvelleManche()
		{

			this.ctrl.nouvelleManche();
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
