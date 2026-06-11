package metier;

import java.util.ArrayList;

import metier.PlateauJeu;

import java.awt.Color;

public class Acteur
{
	// Attribut
	private int     posX; //ligne
	private int     posY; //colone
	
	private Role    role;
	private boolean estPrincipal;
	private Color   couleur;
	
	private ArrayList<Acteur> lstVoisins;
	
	// Constructeur
	public Acteur (int posX, int posY, Role role)
	{
		this.posX = posX;
		this.posY = posY;
		this.role = role;
		
		this.lstVoisins   = new ArrayList<Acteur>();
		this.estPrincipal = false;
	}
	
	// Getters
	public Role getRole () { return this.role; }
	public int  getPosX () { return this.posX; }
	public int  getPosY () { return this.posY; }

	public boolean estPrincipal () {return estPrincipal;} // retourne true si l'acteur est l'acteur principal de son casting
	public Color   getCouleur   () {return couleur;}      // retourne la couleur du casting de l'acteur
	
	public ArrayList<Acteur> getVoisins ()            { return this.lstVoisins;}              // retourne la liste de tout les voisins de cet acteur
	public Acteur            getVoisin  (int indice)  { return this.lstVoisins.get(indice); } // retourne le voisin d'indice entré en paramètre
	
	// Setters
	public void setRole      (Role role) { this.role = role; } // Définis le rôle de l'acteur par le rôle entré en paramètre
	public void setPrincipal (Color c)                         // Définis cet acteur comme acteur principal du casting à la couleur entré en paramètre
	{
		if (this.estPrincipal) { this.estPrincipal = false; }
		else 
		{ 
			this.estPrincipal = true ; 
			couleur = c;
		}
	}
	
	// Méthodes utilitaires	
	public boolean estVoisin (Acteur voisin) // permet de savoir si l'acteur est voisin avec celui entré en paramètre
	{
		return this.lstVoisins.contains(voisin);
	}

	public boolean addVoisin(Acteur voisin) // ajoute l'acteur en paramètre dans la liste des voisins, retourne false si déja présent
	{
		if (!this.estVoisin(voisin))
		{
			this.lstVoisins.add(voisin);
			voisin.getVoisins().add(this);
			return true;
		}
		return false;
	}

	private boolean entreDeux(Acteur a, ArrayList<Acteur> lstActeurs) // méthode permettant de savoir si l'acteur est en ligne directe avec l'acteur entré en 1er paramètre
	{
		int deltaY = Integer.compare(a.getPosY(), this.getPosY());
		int deltaX = Integer.compare(a.getPosX(), this.getPosX());
		
		int y = this.getPosY() + deltaY;
		int x = this.getPosX() + deltaX;

		while ( y != a.getPosY() || x != a.getPosX() )
		{
			for (Acteur acteur : lstActeurs )
				if (acteur.getPosY() == y && acteur.getPosX() == x)
					return true;
			
			y += deltaY;
			x += deltaX;
		}
		
		return false;
	}

	public void majVoisins(ArrayList<Acteur> lstActeurs) // met a jour la liste des voisins de l'acteur
	{
		for (Acteur a : lstActeurs)
		{
			if (((Math.abs(a.getPosX() - this.getPosX()) == Math.abs(a.getPosY() - this.getPosY())) 
			 || (a.getPosX() == this.getPosX() && a.getPosY() != this.getPosY()
			 ||  a.getPosY() == this.getPosY() && a.getPosX() != this.getPosX()))&& !this.entreDeux(a, lstActeurs))
			{
				this.addVoisin(a);
			}
		}
	}

	public void supprimerVoisin(Acteur voisin) //supprime le voisin de la liste
	{
		this.lstVoisins.remove(voisin);
		
		try
		{
			voisin.supprimerVoisin(this);
		}
		catch(Exception e){}
	}


	public String toString()
	{
		return "" + this.role + String.format("%03d", this.posX) + String.format("%03d", this.posY);
	}

	public void ajouterArete(PlateauJeu plateau, Acteur voisin)
	{
		plateau.ajouterArete(this, voisin);
	}
} 
