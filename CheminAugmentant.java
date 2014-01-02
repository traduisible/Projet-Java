
public class CheminAugmentant extends Exception {
	private Sommet borne;
	private Sommet origine;
	private Sommet extrémité;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Constructeurs
	
	public CheminAugmentant(Sommet borne, Sommet origine, Sommet extrémité)
	{
		this.borne = borne;
		this.origine = origine;
		this.extrémité = extrémité;
	}
	
	public CheminAugmentant(Sommet origine, Sommet extrémité)
	{
		this.borne = extrémité;
		this.origine = origine;
		this.extrémité = extrémité;
	}

	public Sommet getBorne() {
		return borne;
	}

	public void setBorne(Sommet borne) {
		this.borne = borne;
	}

	public Sommet getOrigine() {
		return origine;
	}

	public void setOrigine(Sommet origine) {
		this.origine = origine;
	}

	public Sommet getExtrémité() {
		return extrémité;
	}

	public void setExtrémité(Sommet extrémité) {
		this.extrémité = extrémité;
	}

}
