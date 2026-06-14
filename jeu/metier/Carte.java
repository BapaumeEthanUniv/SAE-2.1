package metier;

public class Carte
{
	private Role    roleActeur;
	private boolean fonce;

    // Constructeur
	public Carte(Role role, Boolean fonce)
	{
		this.roleActeur = role;
		this.fonce      = fonce;
	}

    // Getters
	public Role     getRole()   {return this.roleActeur;}
	public boolean  estFonce() {return this.fonce ;}
}
