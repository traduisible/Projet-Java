/*
 * On étiquette chaque sommet de la manière suivante :
 * (x,P/I,y)
 * Cette étiquette signifie qu'un sommet donné est accessible par un chemin alternant
 * de longueur paire/impaire depuis le sommet libre x, et précédé immédiatement
 * dans ce chemin par y.
 */
public class Étiquette {
	
	//Variables privées
	
	private Sommet origine; //origine est supposée libre
	private boolean pair;
	private Sommet prédécesseur;
	
	
	//Constructeurs
	
	//Étiquette pour un sommet libre
	public Étiquette(Sommet origine)
	{
		this.origine = origine;
		this.pair = true;
		this.prédécesseur = null;
	}
	
	public Étiquette (Sommet origine, boolean pair, Sommet prédécesseur)
	{
		this.origine = origine;
		this.pair = pair;
		this.prédécesseur = prédécesseur;
	}
	
	
	//Modifieurs
	
	public void setOrigine(Sommet origine)
	{
		this.origine = origine;
	}
	
	public void setPair(boolean pair)
	{
		this.pair = pair;
	}
	
	public void setPrédécesseur(Sommet prédécesseur)
	{
		this.prédécesseur = prédécesseur;
	}
	
	//Accesseurs
	
	public Sommet getOrigine()
	{
		return origine;
	}
	
	public boolean isPair()
	{
		return pair;
	}

	public Sommet getPrédécesseur()
	{
		return prédécesseur;
	}


}
