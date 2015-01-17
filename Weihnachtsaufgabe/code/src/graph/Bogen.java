package graph;

public class Bogen {

	private Knoten[] knoten = new Knoten[2];
	
	public Bogen (Knoten k1, Knoten k2) {
		this.knoten[0] = k1;
		this.knoten[1] = k2;
	}
	
	public Knoten[] getKnoten () {
		return knoten;
	}
	
}
