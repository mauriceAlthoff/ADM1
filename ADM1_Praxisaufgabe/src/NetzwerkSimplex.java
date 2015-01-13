import java.util.ArrayList;


public class NetzwerkSimplex {

	private static Knoten[] knoten;
	private static Bogen[]  boegen;
	
	private static Knoten[] initKnoten;
	private static Bogen[]  initBoegen;
	
	public static void initialisierung (Digraph graph) {
		knoten = graph.getKnoten();
		boegen = graph.getBoegen();
		
		initKnoten = new Knoten[knoten.length + 1];
		for (int i = 0; i < knoten.length; i++) {
			initKnoten[i] = knoten[i];
		}	
		initKnoten[knoten.length - 1] = new Knoten(0);
		
		ArrayList<Knoten> V_plus = new ArrayList<Knoten>();
		ArrayList<Knoten> V_minus = new ArrayList<Knoten>();		
		for (int i = 0; i < knoten.length; i++) {
			int nettobedarf = 0;
			nettobedarf += knoten[i].getBedarf();
			
			ArrayList<Bogen> außengrad = knoten[i].getAusgehendeBoegen();
			for (int j = 0; j < außengrad.size(); j++) {
				nettobedarf += außengrad.get(j).getUntereGrenze();
			}
			ArrayList<Bogen> innengrad = knoten[i].getEingehendeBoegen();
			for (int j = 0; j < innengrad.size(); j++) {
				nettobedarf -= innengrad.get(j).getUntereGrenze();
			}
			
			if (nettobedarf < 0) {
				V_plus.add(knoten[i]);
			} else {
				V_minus.add(knoten[i]);
			}
		}
		
		int maxKosten = 0;
		initBoegen = new Bogen[boegen.length + knoten.length];
		for (int i = 0; i < boegen.length; i++) {
			initBoegen[i] = boegen[i];
			if (Math.abs(boegen[i].getKosten()) > maxKosten) {
				maxKosten = Math.abs(boegen[i].getKosten());
			}
		}
		
		int M = 1 + 1/2 * knoten.length * maxKosten;
		for (int i = 0; i < V_plus.size(); i++) {
			Bogen bogen = new Bogen(0, -1, M, V_plus.get(i), initKnoten[initKnoten.length - 1]);
			V_plus.get(i).addAusgehendenBogen(bogen);
			initKnoten[initKnoten.length - 1].addEingehendenBogen(bogen);
			initBoegen[boegen.length + i] = bogen;
		}
		for (int i = 0; i < V_minus.size(); i++) {
			Bogen bogen = new Bogen(0, -1, M, initKnoten[initKnoten.length - 1], V_minus.get(i));
			initKnoten[initKnoten.length - 1].addAusgehendenBogen(bogen);
			V_minus.get(i).addEingehendenBogen(bogen);
			initBoegen[boegen.length + V_plus.size() + i] = bogen;
		}
	}
	
	public static void berechnungKnotenpreise () {
		
	}
	
	public static void optimalitaetstest () {
		
	}
	
	public static void pricing () {
		
	}
	
	public static void augmentierung () {
		
	}
	
	public static void update () {
		
	}
}
