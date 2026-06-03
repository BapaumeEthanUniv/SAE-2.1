import java.util.ArrayList;

public class Pioche
{
	private Controleur          ctrl        ;
	private String              lstRoleCarte;
	private ArrayList<Carte>    lstCarte    ;
	private int                 nbCartes    ;
	private int                 cptCarte    ;

	public Pioche(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.lstRoleCarte = this.ctrl.getRoleActif();
	}

	public void creerPioche(int nbCartes)
	{
		String[] typeCarte = new String[]{"Clair", "Foncé"};
		this.nbCartes = nbCartes;

		for( String tmp : typeCarte)
		{
			for(String tmp2 : this.lstRoleCarte)
			{
				this.lstCarte.add(new Carte(tmp2, tmp));
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
