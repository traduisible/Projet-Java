import java.util.Set;
import java.util.TreeSet;

public class Sommet {
	
	//Variables privées
	
	private Set<Sommet> voisins;
	
	private boolean contracté;
	private boolean métasommet;
	
    private boolean étiqueté;
    private boolean pair;
	
    
	//Constructeurs
    
	public Sommet()
	{
		voisins = new TreeSet<Sommet>();
		setContracté(new Boolean(false));
		setMétasommet(new Boolean(false));
		setÉtiqueté(new Boolean(false));
		setPair(new Boolean(false));
	}
	
	public Sommet(Set<Sommet> voisins)
	{
		this.voisins = voisins;
		setContracté(new Boolean(false));
		setMétasommet(new Boolean(false));
		setÉtiqueté(new Boolean(false));
		setPair(new Boolean(false));
	}
	
	
	//Modifieurs
	
	public void setVoisins(Set<Sommet> voisins)
	{
		this.voisins = voisins;
	}
	
	public void setContracté(boolean contracté)
	{
		this.contracté = contracté;
	}
	
	public void setMétasommet(boolean métasommet)
	{
		this.métasommet = métasommet;
	}
	
	public void setÉtiqueté(boolean étiqueté)
	{
		this.étiqueté = étiqueté;
	}
	
	public void setPair(boolean pair)
	{
		this.pair = pair;
	}
	
	
	//Accesseurs
	
	public Set<Sommet> getVoisins()
	{
		return this.voisins;
	}

	boolean isContracté()
    {
		return contracté;
	}

	public boolean isMétasommet()
	{
		return métasommet;
	}

	public boolean isÉtiqueté()
	{
		return étiqueté;
	}

	public boolean isPair()
	{
		return pair;
	}







	

}
