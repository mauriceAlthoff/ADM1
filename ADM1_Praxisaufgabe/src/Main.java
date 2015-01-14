public class Main {
	public static void main(String[] args) {
		Digraph netzwerk = new Digraph("testset2014/chvatal1.net");
		NetzwerkSimplex sim = new NetzwerkSimplex();
		sim.initialisierung(netzwerk);
		sim.berechnungKnotenpreise();
		while (sim.optimalitaetstest()) {
			sim.pricing();
			sim.augmentierung();
			sim.update();
		}
		sim.printSolution();
	}
}
