package metier;

public enum Casting
{
	ROUGE 	("Rouge"),
	VERT	("Vert" ),
	BLEU	("Bleu" ),
	MAUVE	("Mauve"),
	JAUNE	("Jaune"),
	CYAN	("Cyan" );
	
	private String libelle;
	
	Casting (String libelle) { this.libelle = libelle; }
	
	public String getLibelle() { return libelle; }
	
	public String toString()
	{
		return this.name().toLowerCase();
	}
	
	public static int getNbCasting() 
	{
		return Casting.values().length;
	}
	
	public static Casting valueOf(int ordinal)
	{
		return Casting.values()[ordinal];
	}
}
