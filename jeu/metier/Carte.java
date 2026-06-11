package metier;

public class Carte
{
	private Role    roleActeur;
	private boolean fonce;

	public Carte(Role role, Boolean fonce)
	{
		this.roleActeur = role;
		this.fonce      = fonce;
	}

	public Role    getRole()   {return this.roleActeur;}
	public boolean  estFonce() {return this.fonce ;}
}