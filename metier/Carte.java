public class Carte
{
	private String roleActeur;
	private String typeCarte;

	public Carte(String role, String type)
	{
		this.roleActeur = role;
		this.typeCarte  = type;
	}

	public String getRole() {return this.roleActeur;}
	public String getType() {return this.typeCarte ;}
}