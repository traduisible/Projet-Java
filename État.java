import java.util.Set;

public class État {
	
	// Variables privées
	
	private Set<Sommet> voisins;
	private Set<Sommet> contractés;
	
	
	// Constructeur
	
	public État(Set<Sommet> voisins, Set<Sommet> contractés)
	{
		this.voisins = voisins;
		this.contractés = contractés;
	}
	
	// Accesseurs
	
	public Set<Sommet> getVoisins()
	{
		return voisins;
	}
	
	public Set<Sommet> getContractés()
	{
		return contractés;
	}
	
	// Modifieurs

	public void setVoisins(Set<Sommet> voisins)
	{
		this.voisins = voisins;
	}

	public void setContractés(Set<Sommet> contractés)
	{
		this.contractés = contractés;
	}
	
	

}