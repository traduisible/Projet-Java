import java.util.*;


public class Couplage {
	
	// Variables privées
	
	private Set<Arête> arêtes;
	private Set<Sommet> couplés;
	
	
	// Constructeur
	
	public Couplage()
	{
		arêtes = new HashSet<Arête>();
		couplés = new HashSet<Sommet>();
	}
	
	public Couplage(Arête arête)
	{
		arêtes = new HashSet<Arête>();
		arêtes.add(arête);
		couplés = new HashSet<Sommet>();
		couplés.add(arête.getDépart());
		couplés.add(arête.getArrivée());
	}
	
	public Couplage(Set<Arête> arêtes, Set<Sommet> couplés)
	{
		this.arêtes = arêtes;
		this.couplés = couplés;
	}
	
	// Accesseurs
	
	public Set<Arête> getArêtes()
	{
		return arêtes;
	}
	
	public Set<Sommet> getCouplés()
	{
		return couplés;
	}
	
	
	// Modifieurs
	
	public void setArêtes(Set<Arête> arêtes)
	{
		this.arêtes = arêtes;
	}
	
	public void addArête(Arête arête)
	{
		arêtes.add(arête);
		couplés.add(arête.getDépart());
		couplés.add(arête.getArrivée());
	}
	
	public void setCouplés(Set<Sommet> couplés)
	{
		this.couplés = couplés;
	}
	
	
	// Méthodes publiques
	
	public void différenceSymétrique(Couplage P)
	{
		Set <Arête> intersection = new HashSet<Arête>(arêtes);
		intersection.retainAll(P.getArêtes());
		arêtes.addAll(P.getArêtes());
		arêtes.removeAll(intersection);
		couplés.addAll(P.getCouplés());
	}
	
	/*public Couplage différenceSymétriqueHorsPlace(Couplage P)
	{
		Set<Arête> union = new HashSet<Arête>(arêtes);
		union.addAll(P.getArêtes());
		Set<Arête> intersection = new HashSet<Arête>(arêtes);
		intersection.retainAll(P.getArêtes());
		union.removeAll(intersection);
		return (new Couplage(union));
	}*/
	


}
