import java.util.*;

public class Sommet {
	
	//Variables privées
	
	private String nom;
	
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
    private Stack<État> historique;
	
    
	//Constructeurs
    
	public Sommet()
	{
		nom = null;
		voisins = new HashSet<Sommet>();
		contracté = new Boolean(false);
		métasommet = new Boolean(false);
		étiqueté = new Boolean(false);
		étiquette = null;
		historique = new Stack<État>();
	}
    
	public Sommet(String nom)
	{
		this.nom = nom;
		voisins = new HashSet<Sommet>();
		contracté = new Boolean(false);
		métasommet = new Boolean(false);
		étiqueté = new Boolean(false);
		étiquette = null;
		historique = new Stack<État>();
	}
	
	public Sommet(String nom, Set<Sommet> voisins)
	{
		this.nom = nom;
		this.voisins = voisins;
		contracté = new Boolean(false);
		métasommet = new Boolean(false);
		étiqueté = new Boolean(false);
		étiquette = null;
		historique = new Stack<État>();
	}
	
	
	//Modifieurs
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public void setVoisins(Set<Sommet> voisins)
	{
		this.voisins = voisins;
	}
	
	public void addVoisin(Sommet voisin)
	{
		voisins.add(voisin);
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
	
	public void setHistorique(Stack<État> historique)
	{
		this.historique = historique;
	}
	
	
	//Accesseurs
	
	public String getNom()
	{
		return nom;
	}
	
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

	public Stack<État> getHistorique()
	{
		return historique;
	}
	
	
	//Méthodes publiques
	
	public État getLastState()
	{
		return historique.pop();
	}
	
	public void addState(État state)
	{
		historique.add(state);
	}
	
	public void addState(Set<Sommet> voisins, Set<Sommet> contractés)
	{
		historique.add(new État(voisins, contractés));
	}
	
}
