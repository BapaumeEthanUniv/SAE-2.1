package metier;

import java.util.ArrayList;
public class Acteur
{
	private int posX;
	private int posY;
	
	private String type;
	private boolean estPrincipal;
	
	private ArrayList<Acteur> lstVoisins;
	
	// Constructeur
	public Acteur (int posX, int posY, String type)
	{
		this.posX = posX;
		this.posY = posY;
		this.type = type;
		
		this.lstVoisins = new ArrayList<Acteur>();   // 8 = vertic. + horiz. + diagonales
		this.estPrincipal = false;
	}
	
	// Getters
	public String   getType    () 		 { return this.type;       	  }
	
	public int      getPosX    () 		 { return this.posX;       	  }
	public int      getPoxY    () 		 { return this.posY;       	  }
	
	public ArrayList<Acteur> getVoisins ()            { return this.lstVoisins; 	  }
	public Acteur            getVoisin  (int indice)  { return this.lstVoisins.get(indice); }
	
	// Setters
	public void   setType    (String type) { this.type = type; }
	
	public void   setPrincipal ()
	{
		if (this.estPrincipal) 	{ this.estPrincipal = false; }
		else			{ this.estPrincipal = true ; }
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
}
