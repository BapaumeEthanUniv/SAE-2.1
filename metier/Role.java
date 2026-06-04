package metier;

public enum Role
{
	CASCADEUR 	("Cascadeur  "),
	EMOTION		("Emotion    "),
	ANTAGONISTE	("Antagoniste"),
	FIGURANT	("Figurant"   );
	
	private String libelle;
	
	Role (String libelle) { this.libelle = libelle; }
	
	public String getLibelle() { return libelle; }
	
	public String toString()
	{
		return this.name().substring(0, 2);
	}
	
	public static int getNbRole() 
	{
		return Role.values().length;
	}
	
	public static Role valueOf(int ordinal)
	{
		return Role.values()[ordinal];
	}
}
