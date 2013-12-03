import java.util.*;

public class Sommet {
	
	//Variables privées
	
	private Set<Sommet> voisins;
	
	private boolean contracté;
	private boolean métasommet;
	
    private boolean étiqueté;
    private Étiquette étiquette;
    
    /*
     * On conserve un historique des listes d'adjacences précédant les contractions
     * successives sous forme de pile, pour le cas où on trouverait une fleur
     * sur un métasommet.
     * De même, on conserve un historique des sommets successivement contractés.
     */
    private Stack<Set<Sommet>> historiqueVoisins;
    private Stack<Set<Sommet>> historiqueContractés;
	
    
	//Constructeurs
    
	public Sommet()
	{
		voisins = new TreeSet<Sommet>();
		contracté = new Boolean(false);
		métasommet = new Boolean(false);
		étiqueté = new Boolean(false);
		étiquette = null;
		historiqueVoisins = new Stack<Set<Sommet>>();
		historiqueContractés = new Stack<Set<Sommet>>();
	}
	
	public Sommet(Set<Sommet> voisins)
	{
		this.voisins = voisins;
		contracté = new Boolean(false);
		métasommet = new Boolean(false);
		étiqueté = new Boolean(false);
		étiquette = null;
		historiqueVoisins = new Stack<Set<Sommet>>();
		historiqueContractés = new Stack<Set<Sommet>>();
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
	
	public void setÉtiquette(Étiquette étiquette)
	{
		this.étiquette = étiquette;
	}
	
	public void setHistoriqueVoisins(Stack<Set<Sommet>> historiqueVoisins)
	{
		this.historiqueVoisins = historiqueVoisins;
	}
	
	public void setHistoriqueContractés(Stack<Set<Sommet>> historiqueContractés)
	{
		this.historiqueContractés = historiqueContractés;
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

	public Étiquette getÉtiquette()
	{
		return étiquette;
	}

	public Stack<Set<Sommet>> getHistoriqueVoisins()
	{
		return historiqueVoisins;
	}

	public Stack<Set<Sommet>> getHistoriqueContractés()
	{
		return historiqueContractés;
	}
	
}
