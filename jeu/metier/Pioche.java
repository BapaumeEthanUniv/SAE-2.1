package metier;

import controleur.Controleur;

import java.util.ArrayList;

public class Pioche
{
	// Attributs
    private ArrayList<Role>     lstRoleCarte  ;
	private ArrayList<Carte>    lstCarte      ;
	private Carte               carteActive   ;
	public Controleur           ctrl          ;

    // Constructeur
	public Pioche(ArrayList<Role> lstRoles, Controleur ctrl)
	{
		this.lstRoleCarte = lstRoles;
		this.ctrl = ctrl;
		this.creerPioche();
	}

    // Getters
    public int   getNbCarte     () { return this.lstCarte.size();}
    public Carte getCarte       () { return this.carteActive;    }

    public int   getNbCarteFonce() // retourne le nombre de cartes foncées encore présentes dans la pioche
    {
        int nbCarteFonce = 0;
        for (Carte carte : this.lstCarte)
        {
            if (carte.estFonce())
                nbCarteFonce++;
        }
        return nbCarteFonce;
    }


    /* --------------------------*/
    /*    Méthodes utilitaires   */
    /* --------------------------*/

	public void creerPioche() // crée une pioche stocké dans la liste lstCarte.
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
		
		this.piocherCarte();
	}

	public void piocherCarte() // pioche une carte dans la liste lstCarte, la retirant et la stockant dans carteActive
	{
		int indice = (int)(Math.random()*getNbCarte()); //a modifier pour le debug si on veut set une seed pour un exemple particulier
		Carte tirer;
		if (this.finManche())
		{
			this.creerPioche();
			this.nouvelleManche();
		}
		else
		{
			tirer = this.lstCarte.get(indice);
			this.lstCarte.remove(indice);
			this.carteActive = tirer;
		}
	}

	public boolean finManche() //return true si la manche est fini en regardant si le nombre de carte foncé est de 0
	{
		return (this.getNbCarteFonce()==0);
	}

	public void nouvelleManche() // initialise une nouvelle manche
    {
        this.ctrl.nouvelleManche();
    }
}
