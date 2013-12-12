import java.util.ArrayList;
import java.util.Iterator;
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
	
	public void addSommet(Sommet sommet)
	{
		sommets.add(sommet);
	}
	
	public void addArête(Sommet départ, Sommet arrivée)
	{
		départ.addVoisin(arrivée);
		arrivée.addVoisin(départ);		
	}
	
	public void addArête(Arête arête)
	{
		addArête(arête.getDépart(), arête.getArrivée());
	}
	
	
	//Accesseurs
	
	public List<Sommet> getSommets()
	{
		return this.sommets;
	}
	
	
	// Méthodes publiques
	
	// Renvoie un couplage maximal trouvé de manière gloutonne
	public Couplage couplageMaximal()
	{
		Couplage output = new Couplage();
		for (Sommet s : sommets)
		{
			if (!s.isÉtiqueté())
			{
				Iterator<Sommet> it = s.getVoisins().iterator();
				Boolean found = false;
				Sommet t = new Sommet();
				while (it.hasNext() && !found)
				{
					t = it.next();
					found = !t.isÉtiqueté();
				}
				if (found)
				{
					output.addArête(new Arête(s,t));
					s.setÉtiqueté(true);
					t.setÉtiqueté(true);
				}
			}
		}
		for (Sommet s : sommets)
		{
			s.setÉtiqueté(false);
		}
		return output;
	}

}
