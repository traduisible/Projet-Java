import java.util.Set;
import java.util.HashSet;
import java.util.Hashtable;


public class Couplage {
	
	// Variables privées
	
	private Hashtable<Sommet,Sommet> arêtes; // Ici, une arête uv est représentée par (clé : u, objet : v) et (clé : v, objet : u). On perd en compacité, mais l'on gagne en rapidité.
	private Set<Sommet> couplés;
	
	
	// Constructeur
	
	public Couplage()
	{
		arêtes = new Hashtable<Sommet,Sommet>();
		couplés = new HashSet<Sommet>();
	}
	
	public Couplage(Arête arête)
	{
		arêtes = new Hashtable<Sommet,Sommet>();
		arêtes.put(arête.getDépart(), arête.getArrivée());
		arêtes.put(arête.getArrivée(), arête.getDépart());
		couplés = new HashSet<Sommet>();
		couplés.add(arête.getDépart());
		couplés.add(arête.getArrivée());
	}
	
	public Couplage(Sommet départ, Sommet arrivée)
	{
		arêtes = new Hashtable<Sommet,Sommet>();
		arêtes.put(départ, arrivée);
		arêtes.put(arrivée, départ);
		couplés = new HashSet<Sommet>();
		couplés.add(départ);
		couplés.add(arrivée);
	}
	
	public Couplage(Hashtable<Sommet,Sommet> arêtes, Set<Sommet> couplés)
	{
		this.arêtes = arêtes;
		this.couplés = couplés;
	}
	
	// Accesseurs
	
	public Hashtable<Sommet,Sommet> getArêtes()
	{
		return arêtes;
	}
	
	public Set<Sommet> getCouplés()
	{
		return couplés;
	}
	
	
	// Modifieurs
	
	public void setArêtes(Hashtable<Sommet,Sommet> arêtes)
	{
		this.arêtes = arêtes;
	}
	
	public void addArête(Arête arête)
	{
		arêtes.put(arête.getDépart(),arête.getArrivée());
		arêtes.put(arête.getArrivée(),arête.getDépart());
		couplés.add(arête.getDépart());
		couplés.add(arête.getArrivée());
	}
	
	/*
	 * Ajoute une arête en supposant qu'aucun nouveau sommet n'est ajouté au couplage
	 * (Cela permet de ne pas effectuer la coûteuse mise à jour des sommets couplés lorsque l'on remonte le chemin depuis v)
	 */
	public void addArêteSansModif(Arête arête)
	{
		arêtes.put(arête.getDépart(),arête.getArrivée());
		arêtes.put(arête.getArrivée(),arête.getDépart());		
	}
	
	public void addArête(Sommet départ, Sommet arrivée)
	{
		arêtes.put(départ, arrivée);
		arêtes.put(arrivée, départ);
		couplés.add(départ);
		couplés.add(arrivée);
	}
	
	// Idem	
	public void addArêteSansModif(Sommet départ, Sommet arrivée)
	{
		arêtes.put(départ, arrivée);
		arêtes.put(arrivée, départ);
	}
	
	public void removeArête(Sommet départ)
	{
		Sommet arrivée = arêtes.get(départ);
		arêtes.remove(départ);
		couplés.remove(départ);
		couplés.remove(arrivée);
	}
	
	// Même idée que pour l'ajout
	public void removeArêteSansModif(Sommet départ)
	{
		arêtes.remove(départ);
	}
	
	public void setCouplés(Set<Sommet> couplés)
	{
		this.couplés = couplés;
	}
	
	/*
	 * Permet d'ajouter un sommet aux sommets couplés, indépendamment de ce qui se passe pour les arêtes.
	 * (Cela est utile encore une fois pour réduire le nombre d'opérations inutiles)
	 */
	public void addCouplé(Sommet s)
	{
		couplés.add(s);
	}
	
	
	// Méthodes publiques
	
	/*public void différenceSymétrique(Couplage P)
	//Attention, la caractéristique de "couplés" n'est pas préservée en général, seulement dans le cas où P est un chemin augmentant.
	{
		Set <Arête> intersection = new HashSet<Arête>(arêtes);
		intersection.retainAll(P.getArêtes());
		arêtes.addAll(P.getArêtes());
		arêtes.removeAll(intersection);
		couplés.addAll(P.getCouplés());
	}*/
	
	/*public Couplage différenceSymétriqueHorsPlace(Couplage P)
	{
		Set<Arête> union = new HashSet<Arête>(arêtes);
		union.addAll(P.getArêtes());
		Set<Arête> intersection = new HashSet<Arête>(arêtes);
		intersection.retainAll(P.getArêtes());
		union.removeAll(intersection);
		return (new Couplage(union));
	}*/
	
	public void print()
	{
		HashSet<Sommet> printed = new HashSet<Sommet>();
		for (Sommet s : couplés)
		{
			if (!printed.contains(s))
			{
				System.out.println("("+s.getNom()+","+arêtes.get(s).getNom()+")");
				printed.add(s);
				printed.add(arêtes.get(s));
			}
		}
		
		
	}


}
