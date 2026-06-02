package metier;

public class Acteur
{
	private int posX;
	private int posY;
	
	private String type;
	private boolean estPrincipal;
	
	private Acteur[] lstVoisins;
	
	// Constructeur
	public Acteur (int posX, int posY, String type)
	{
		this.posX = posX;
		this.posY = posY;
		this.type = type;
		
		this.lstVoisins = new Acteur[8];   // 8 = vertic. + horiz. + diagonales
		this.estPrincipal = false;
	}
	
	// Getters
	public String   getType    () 		 { return this.type;       	  }
	
	public int      getPosX    () 		 { return this.posX;       	  }
	public int      getPoxY    () 		 { return this.posY;       	  }
	
	public Acteur[] getVoisins () 		 { return this.lstVoisins; 	  }
	public Acteur   getVoisin  (int indice)  { return this.lstVoisins[indice]; }
	
	// Setters
	public void   setType    (String type) { this.type = type; }
	
	public void   setPrincipal ()
	{
		if (this.estPrincipal) 	{ this.estPrincipal = false; }
		else			{ this.estPrincipal = true ; }
	}
	
	// Méthodes utilitaires	
	public boolean estVoisin (Acteur voisin)
	{
		for (int cpt = 0; cpt < lstVoisins.length; cpt++)
		{
			if (lstVoisins[cpt].equals(voisin))
			{
				return true;
			}
		}
		
		return false;
	}
}
