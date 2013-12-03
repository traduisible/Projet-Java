import java.util.ArrayList;
import java.util.List;


public class Graphe {
	
	//Variables privées
	
	List<Sommet> sommets;
	
	
	//Constructeurs
	
	public Graphe()
	{
		sommets = new ArrayList<Sommet>();
	}
	
	public Graphe(List<Sommet> sommets)
	{
		this.sommets = sommets;
	}
	
	
	//Modifieurs
	
	public void setSommets(List<Sommet> sommets)
	{
		this.sommets = sommets;
	}
	
	
	//Accesseurs
	
	public List<Sommet> getSommets()
	{
		return this.sommets;
	}

}
