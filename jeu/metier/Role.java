package metier;

public enum Role
{
	CASCADEUR 	("Cascadeur  "),
	EMOTION		("Émotion    "),
	ANTAGONISTE	("Antagoniste"),
	FIGURANT	("Figurant"   );
	
	//Attribut
	private String libelle;
	
	//Constructeur
	Role (String libelle) { this.libelle = libelle; }
	
	//Getter
	public        String getLibelle() {return libelle;}
	public static int    getNbRole()  {return Role.values().length;}
	
	//Autres méthodes

	public String toString()
	{
		return this.name().substring(0,3);
	}
	
	public static Role valueOf(int ordinal)
	{
		return Role.values()[ordinal];
	}

	public static Role getRole(String role)
	{
		for (Role r : Role.values())
		{
			if (r.toString().equals(role))
				return r;
		}

		return null;
	}
}
