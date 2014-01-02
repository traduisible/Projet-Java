
public class Fleur extends Exception {
	private Sommet pétaleA;
	
	private Sommet pétaleB;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructeur
	
	public Fleur(Sommet pétaleA, Sommet pétaleB)
	{
		this.pétaleA = pétaleA;
		this.pétaleB = pétaleB;
	}

	public Sommet getPétaleA() {
		return pétaleA;
	}


	public void setPétaleA(Sommet pétaleA) {
		this.pétaleA = pétaleA;
	}


	public Sommet getPétaleB() {
		return pétaleB;
	}


	public void setPétaleB(Sommet pétaleB) {
		this.pétaleB = pétaleB;
	}
	

}
