package metier;

import java.util.ArrayList;
import java.awt.Color;

public class Acteur
{
	private int posX;
	private int posY;
	
	private Role role;
	private boolean estPrincipal;
	private Color   couleur;
	
	private ArrayList<Acteur> lstVoisins;
	
	// Constructeur
	public Acteur (int posX, int posY, Role role)
	{
		this.posX = posX;
		this.posY = posY;
		this.role = role;
		
		this.lstVoisins = new ArrayList<Acteur>();   // 8 = vertic. + horiz. + diagonales
		this.estPrincipal = false;
	}
	
	// Getters
	public Role   getRole    () 		 { return this.role;       	  }
	
	public int      getPosX    () 		 { return this.posX;       	  }
	public int      getPosY    () 		 { return this.posY;       	  }

	public boolean estPrincipal() {return estPrincipal;}
	public Color getCouleur() {return couleur;}
	
	public ArrayList<Acteur> getVoisins ()            { return this.lstVoisins; 	  }
	public Acteur            getVoisin  (int indice)  { return this.lstVoisins.get(indice); }
	
	// Setters
	public void   setRole    (Role role) { this.role = role; }
	
	public void   setPrincipal (Color c)
	{
		if (this.estPrincipal) { this.estPrincipal = false; }
		else 
		{ 
			this.estPrincipal = true ; 
			couleur = c;
		}
	}
	
	// MÃ©thodes utilitaires	
	public boolean estVoisin (Acteur voisin)
	{
		return this.lstVoisins.contains(voisin);
	}

	public boolean addVoisin(Acteur voisin)
	{
		if (!this.estVoisin(voisin))
		{
			this.lstVoisins.add(voisin);
			voisin.getVoisins().add(this);
			return true;
		}
		return false;
	}

	private boolean entreDeux(Acteur a, ArrayList<Acteur> lstActeurs)
	{
		int deltaY = Integer.compare(a.getPosY(), this.getPosY());
		int deltaX = Integer.compare(a.getPosX(), this.getPosX());
		
		int y = this.getPosY()   + deltaY;
		int x = this.getPosX() + deltaX;

		while ( y != a.getPosY() || x != a.getPosX() )
		{
			for (Acteur acteur : lstActeurs )
				if (acteur.getPosY() == y && acteur.getPosX() == x)
					return false;
			
			y += deltaY;
			System.out.println(y + " " + a.getPosY());
			x += deltaX;
		}
		
		return true;
	}

	public void majVoisins(ArrayList<Acteur> lstActeurs)
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

	public void supprimerVoisin()
	{
		for (Acteur voisin : lstVoisins)
			voisin.lstVoisins.remove(this);
	}

	public String toString()
	{
		return "" + this.role + String.format("%03d", this.posX) + String.format("%03d", this.posY);
	}
}
