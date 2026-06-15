package metier;

import java.util.Collections;

import controleur.Controleur;

import java.util.ArrayList;

public class Pioche
{
	// Attributs
	private ArrayList<Carte>    lstCarte         ; //liste contenant les cartes encore présentes dans la Pioche 
	private Carte               carteActive      ; //contient la carte Active (présente sur la frameCarte)
	private Controleur          ctrl             ; //contien le controleur
	private boolean             PremiereCreation ; //booléen permettant de ne pas piocher une carte dès la première création de la pioche
	
	// Constructeur
	public Pioche(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.PremiereCreation = true;
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
			for (Role role : ctrl.getLstRole())
			{
				this.lstCarte.add(new Carte(role, type));
			}
			
			this.lstCarte.add(new Carte(Role.JOKER, type));	// Carte Joker
		}
		Collections.shuffle(this.lstCarte);

		if (this.PremiereCreation) { this.PremiereCreation = false; }

		else { this.piocherCarte(); }
	}

	public void piocherCarte() // pioche une carte dans la liste lstCarte, la retirant et la stockant dans carteActive
	{
		Carte tirer;
		if (this.finManche())
		{
			this.creerPioche();
			this.nouvelleManche();
		}
		else
		{
			tirer = this.lstCarte.get(0);
			this.lstCarte.remove(0);
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
