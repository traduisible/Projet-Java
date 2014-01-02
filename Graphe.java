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
	 * Trouve une corolle contenant u et v en remontant les Prédécesseurs de chacun.
	 * Le comportement de l'algorithme n'est pas garanti si u et v ne sont pas adjacents (les sommets entre u et v seront alors oubliés),
	 * ou s'ils ne sont pas dans la même corolle.
	 */
	
	public Set<Sommet> corolle(Sommet u, Sommet v)
	{
		Set<Sommet> corolle = new HashSet<Sommet>();
		corolle.add(u);
		corolle.add(v);
		Sommet predU = u.getPrédécesseur();
		Sommet predV = v.getPrédécesseur();
		while (!corolle.contains(predU) && !corolle.contains(predV) && predU.hasPrédécesseur() && predV.hasPrédécesseur())
		{
			corolle.add(predU);
			corolle.add(predV);
			predU = predU.getPrédécesseur();
			predV = predV.getPrédécesseur();
		}
		Sommet w = predU;
		if (corolle.contains(predV))
			// w est la base de la corolle, que l'on va utiliser pour enlever les éléments que l'on a ajouté en trop.
		{
		w = predV;
		}
		corolle.add(w); // On ajoute w, car dans le cas où u et v étaient équidistants à la corolle, il n'a pas été ajouté.
		w = w.getPrédécesseur();
		while (corolle.contains(w))
			// On enlève les éléments de la tige.
		{
			corolle.remove(w);
			w = w.getPrédécesseur();
		}
		return corolle;
	}
	
	/*
	 * Transforme le graphe en un graphe H = G/S où S est réduit à un seul métasommet.
	 * Le comportement de l'algorithme n'est pas garanti si S n'est pas connexe ou si S contient des sommets contractés, mais on l'utilisera uniquement sur des corolles.
	 */
	public void compresser(Set<Sommet> S)
	{
		Iterator<Sommet> it = S.iterator();
		// On choisit un sommet s pour représenter S
		Sommet s = it.next();
		// s est désormais un métasommet
		s.setMétasommet(true);
		Set<Sommet> voisins = new HashSet<Sommet>(s.getVoisins());
		// On ajoute tous les voisins de chaque sommet au sommet s
		while (it.hasNext())
		{
			Sommet x = it.next();
			x.setContracté(true);
			s.addVoisins(x.getVoisins());			
		}
		s.addState(voisins, S); // enlever s de S ?
	}
	
	public void compresser(Fleur fleur)
	{
		Set<Sommet> corolle = corolle(fleur.getPétaleA(), fleur.getPétaleB());
		compresser(corolle);
	}
	
	/* 
	 * Effectue un parcours en profondeur du graphe en marquant les sommets au fur et à mesure, à partir du sommet u,
	 * où u est à distance paire du sommet s.
	 * Si un chemin augmentant est trouvé, renvoie une exception CheminAugmentant contenant les renseignements nécessaires pour reconstituer le chemin.
	 * Si une fleur est trouvée, renvoie une exception Fleur contenant les renseignements nécessaires pour reconstituer la fleur.
	 * Sinon, dfs ne renvoie rien.
	 */
	public void dfs(Sommet u, Sommet s, Hashtable<Sommet,Sommet> arêtes, Couplage couplage) throws Fleur, CheminAugmentant
	{
		System.out.println(u.getNom());
		for (Sommet v : u.getVoisins())
		{
			if (v.isLibreNonÉtiqueté(couplage))
				// Fantastique, on a trouvé un chemin augmentant !
			{
				System.out.println("Chemin augmentant à "+v.getNom());
				throw new CheminAugmentant(s,v);
				/*remonter (v, s, false, couplage);
				couplage.addCouplé(v);*/
			}
			else
			{
				if (!v.isÉtiqueté())
					// Ici, rien d'impressionnant. On étiquette v et on continue notre exploration.
				{
					System.out.println("Rien à "+v.getNom());
					v.setÉtiquette(s, false, u);
					arêtes.get(v).setÉtiquette(s, true, v);
					dfs(arêtes.get(v), s, arêtes, couplage);		
				}
				else
				{
					Étiquette étiquette = v.getÉtiquette();
					if (!étiquette.isPair())
						// v est à distance impaire, donc on poursuit notre exploration sur ses voisins.
					{
						System.out.println(v.getNom()+" est à distance impaire");
						for (Sommet w : v.getVoisins())
						{
							if (!w.isÉtiqueté())
							{
								w.setÉtiquette(s, true, v);
								dfs(w, s, arêtes, couplage);
							}
						}
					}
					if (étiquette.getOrigine() != s)
						// On est ici "au milieu" d'un chemin augmentant.
					{
						System.out.println("On a trouvé le milieu d'un chemin en "+v.getNom());
						throw new CheminAugmentant(v,étiquette.getOrigine(),s);
						/*remonter(v, étiquette.getOrigine(), false, couplage);
						v.setÉtiquette(s, true, u);
						remonter(v, s, false, couplage);*/
					}
					else
						// On a trouvé une fleur.
					{
						System.out.println("On a trouvé une fleur en "+v.getNom());
						throw new Fleur(u,v);
					}
				}
			}
		}
	}
	
	// Renvoie un chemin augmentant s'il en existe un, retourne null sinon.
	public CheminAugmentant cheminAugmentant(Couplage couplage)
	{
		try
		{
			for (Sommet s : sommets)
			{
				if (s.isLibreNonÉtiqueté(couplage))
				{
					s.setÉtiquette(s, true, null);
					dfs(s, s, couplage.getArêtes(), couplage);
				}
			}
		}
		catch (Fleur fleur) {
			compresser(fleur);
			CheminAugmentant cheminCompressé = cheminAugmentant(couplage);
			if (cheminCompressé == null)
			{
				return null;
			}
			else
			{
				return cheminCompressé; // Do something here !
			}
		}
		catch (CheminAugmentant chemin) {
			return chemin;
		}
		return null;
	}
	
	public Couplage couplageMaximum()
	{
		Couplage couplage = couplageMaximal();
		int x = 0;
		do
			// On parcourt le graphe à la recherche de chemins augmentants ou de fleurs jusqu'à ce qu'il ne reste rien.
		{
			x = 0;
			for (Sommet s : sommets)
			{
				if (s.isLibreNonÉtiqueté(couplage))
				{
					System.out.println("On va appeler une DFS à "+s.getNom());
					s.setÉtiquette(s, true, null);

				}
			}
		}
		while (x != 0);
		return couplage;
	}

}
