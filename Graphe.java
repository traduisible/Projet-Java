import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


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
	
	
	// Remonte le sommet v en fonction de sa parité
	public void remonter(Sommet v, Sommet s, boolean pair, Couplage couplage)
	{
		Sommet p = v.getÉtiquette().getPrédécesseur();
		if (p.compareTo(s) == 0)
		{
			couplage.addArêteSansModif(v, p);
			couplage.addCouplé(s);
		}
		if (pair)
		{
			couplage.removeArêteSansModif(v);
			remonter(p, s, false, couplage);
		}
			else
			{
				couplage.addArêteSansModif(v, p);
				remonter(p, s, true, couplage);
			}
	}
	
	/* 
	 * Effectue un parcours en profondeur du graphe en marquant les sommets au fur et à mesure, à partir du sommet u,
	 * où u est à distance paire du sommet s.
	 * Si un chemin augmentant est trouvé, dfs augmente le chemin et renvoie 1.
	 * Si une fleur est trouvée, dfs compresse la fleur et renvoie 2.
	 * Sinon, dfs renvoie 0.
	 */
	public int dfs(Sommet u, Sommet s, Set<Sommet> libresNonÉtiquetés, Hashtable<Sommet,Sommet> arêtes, Couplage couplage)
	{
		for (Sommet v : u.getVoisins())
		{
			if (libresNonÉtiquetés.contains(v))
				// Fantastique, on a trouvé un chemin augmentant. On l'augmente et on renvoie fièrement 1.
			{
				libresNonÉtiquetés.remove(v); // On va coupler v, il n'est donc plus libre.
				remonter (v, s, false, couplage);
				return 1;
			}
			else
			{
				if (!v.isÉtiqueté())
					// Ici, rien d'impressionnant. On étiquette v et on continue notre exploration.
				{
					v.setÉtiquette(s, false, u);
					arêtes.get(v).setÉtiquette(s, true, v);
					return dfs(arêtes.get(v), s, libresNonÉtiquetés, arêtes, couplage);		
				}
				else
				{
					Étiquette étiquette = v.getÉtiquette();
					if (!étiquette.isPair())
						// v est à distance impaire, donc on poursuit notre exploration sur ses voisins.
					{
						for (Sommet w : v.getVoisins())
						{
							int x = dfs(w, s, libresNonÉtiquetés, arêtes, couplage);
							if (x != 0)
								// Dès qu'il se passe quelque chose, on s'arrête et on renvoie la valeur correspondante, sans quoi l'algorithme pourrait faire n'importe quoi.
								{
								return x;
								}
						}
					}
					if (étiquette.getOrigine() != s)
						// On est ici "au milieu" d'un chemin augmentant. On le remonte correctement et on renvoie, tout heureux, 1.
					{
						remonter(v, étiquette.getOrigine(), false, couplage);
						v.setÉtiquette(s, true, u);
						remonter(v, s, false, couplage);
						return 1;
					}
					else
						// On a trouvé une fleur, que l'on va compresser.
					{
						// Compresser la fleur
						return 2;
					}
				}
			}
		}
		return 0;
	}
	
	
	public void cheminAugmentant()
	{
		Couplage couplage = couplageMaximal();
		Set<Sommet> libresNonÉtiquetés = new HashSet<Sommet>();
		libresNonÉtiquetés.addAll(sommets);
		libresNonÉtiquetés.removeAll(couplage.getCouplés()); // On stocke les sommets libres et non étiquetés pour y accéder rapidement
		for (Sommet s : libresNonÉtiquetés)
		{
			dfs(s, s, libresNonÉtiquetés, couplage.getArêtes(), couplage);
		}
		
		
		
		
	}

}
