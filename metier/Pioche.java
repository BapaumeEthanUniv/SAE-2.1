import java.util.ArrayList;

public class Pioche
{
	private String[]            lstRoleCarte;
	private ArrayList<Carte>    lstCarte    ;
	private int                 nbCartes    ;
	private int                 cptCarte    ;

	public Pioche(String[] roleDispo)
	{
		this.lstRoleCarte = new String[roleDispo.length];
		this.lstRoleCarte = roleDispo;
	}

	public void creerPioche(int nbCartes)
	{
		String[] typeCarte = new String[]{"Clair", "Foncé"};
		this.nbCartes = nbCartes;

		for( String type : typeCarte)
		{
			for(String role : this.lstRoleCarte)
			{
				this.lstCarte.add(new Carte(role, type));
			}
		}
	}

	public Carte getCarte(int indice)
	{
		Carte tirer;
		tirer = this.lstCarte.get(indice);
		this.lstCarte.remove(indice);
		return tirer;
	}

	public int getNbCarte(){return this.nbCartes;}
}
