package metier;

public class Carte
{
	private Role   roleActeur;
	private String typeCarte;

	public Carte(Role role, String type)
	{
		this.roleActeur = role;
		this.typeCarte  = type;
	}

	public Role    getRole() {return this.roleActeur;}
	public String  getType() {return this.typeCarte ;}
}