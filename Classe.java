import java.util.Set;
import java.util.TreeSet;


public class Classe<E> {
	//Variables privées	
	Set<E> equivalents;
		
	//Constructeurs	
	public Classe()
	{
		equivalents = new TreeSet<E>();
	}
	public Classe(Set<E> equivalents)
	{
		this.equivalents = equivalents;
	}
	
	
	//Méthodes publiques	
	public void merge(Classe<E> classe1, Classe<E> classe2)
	{
		Set<E> union = new TreeSet<E>();
		union.addAll(classe1.equivalents);
		union.addAll(classe2.equivalents);
		classe1.equivalents = union;
		classe2.equivalents = union;
	}
	
	

}
