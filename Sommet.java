import java.util.*;

public class Sommet implements Comparable<Sommet>{
	
	//Variables privées
	
	// Les noms des sommets permettront de les comparer facilement
	private String nom;
	// On garde le nombre de sommets déjà créés pour pouvoir garantir l'unicité du nom lorque l'on en crée un nouveau
	private static int compteur;
	
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
    
    /*
     * Si l'utilisateur ne spécifie pas de nom, on utilise le compteur pour en créer un nouveau.
     * Les nouveaux sommets ainsi créés sont numérotés Ni, où i est le compteur actuel
     */
	public Sommet()
	{
		nom = new String("N"+Integer.toString(compteur));
		setCompteur(compteur+1);
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
	
	public void addVoisins(Set<Sommet> voisins)
	{
		this.voisins.addAll(voisins);
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
		étiqueté = true;
	}
	public void setÉtiquette(Sommet origine, boolean pair, Sommet Prédécesseur)
	{
		étiquette = new Étiquette(origine, pair, Prédécesseur);
		étiqueté = true;
	}
	
	public void setHistorique(Stack<État> historique)
	{
		this.historique = historique;
	}
	
	public void setCompteur(int compteur)
	{
		Sommet.compteur = compteur;
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
	
	public boolean isLibreNonÉtiqueté(Couplage couplage)
	{
		return (!couplage.getCouplés().contains(this) && !étiqueté);
	}

	public Étiquette getÉtiquette()
	{
		return étiquette;
	}
	
	public boolean hasPrédécesseur()
	{
		return(étiqueté && étiquette.getPrédécesseur() != null);
	}
	
	public Sommet getPrédécesseur()
	{
		if (étiqueté)
		{
			return étiquette.getPrédécesseur();
		}
		else
		{
			return null;
		}
	}

	public Stack<État> getHistorique()
	{
		return historique;
	}
	
	public int getCompteur()
	{
		return compteur;
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

	@Override
	public int compareTo(Sommet s) {
		return nom.compareTo(s.getNom());
	}
	
}
