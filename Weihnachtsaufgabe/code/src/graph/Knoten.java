package graph;

import java.util.ArrayList;

public class Knoten {

	private String nomen;
	
	private ArrayList<Bogen> boegen = new ArrayList<Bogen>();
	
	public Knoten (String nomen) {
		this.nomen = nomen;
	}
	
	public String getNomen () {
		return nomen;
	}
	
	public ArrayList<Bogen> getBoegen () {
		return boegen;
	}
	
	public void addBogen (Bogen bogen) {
		this.boegen.add(bogen);
	}

}
