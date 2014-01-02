public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sommet A = new Sommet("A");
		Sommet B = new Sommet("B");
		Sommet C = new Sommet("C");
		Sommet D = new Sommet("D");
		Sommet E = new Sommet("E");
		Sommet F = new Sommet("F");
		Sommet H = new Sommet("H");
		Graphe G = new Graphe();
		G.addSommet(A);
		G.addSommet(B);
		G.addSommet(C);
		G.addSommet(D);
		G.addSommet(E);
		G.addSommet(F);
		G.addSommet(H);
		G.addArête(A, B);
		G.addArête(A, D);
		G.addArête(B, D);
		G.addArête(C, D);
		G.addArête(D, E);
		G.addArête(E, F);
		G.addArête(F,H);
		Couplage M = G.couplageMaximal();
		M.print();
		System.out.println();
		Couplage Z = G.couplageMaximum();
		Z.print();
	}

}
