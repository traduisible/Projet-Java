
public class Arête implements Comparable<Arête> {
	
	// Variables privées
	
	private Sommet départ;
	private Sommet arrivée;
	
	
	// Constructeur

	
	public Arête(Sommet départ, Sommet arrivée)
	{
		this.setDépart(départ);
		this.setArrivée(arrivée);
	}
	
	
	// Accesseurs

	public Sommet getDépart()
	{
		return départ;
	}
	
	public Sommet getArrivée()
	{
		return arrivée;
	}
	
	
	// Modifieurs

	public void setDépart(Sommet départ)
	{
		this.départ = départ;
	}
	
	public void setArrivée(Sommet arrivée)
	{
		this.arrivée = arrivée;
	}
	
	
	// Méthodes publiques
	
	@Override
	public int compareTo(Arête edge) {
		Boolean equals = (edge.getDépart() == départ && edge.getArrivée() == arrivée)
				|| (edge.getDépart() == arrivée && edge.getArrivée() == départ);
		return (equals? 0 : 1);
	}
	
	
	
	

}
