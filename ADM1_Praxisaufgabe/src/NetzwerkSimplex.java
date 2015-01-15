import java.util.ArrayList;


public class NetzwerkSimplex {

	private static Knoten[] knoten;
	private static Bogen[]  boegen;
	
	private static ArrayList<Knoten> initKnoten;
	private static ArrayList<Bogen>  initBoegen;
	
	private static ArrayList<Bogen> T;
	private static ArrayList<Bogen> L;
	private static ArrayList<Bogen> U;
	
	public void initialisierung (Digraph graph) {
		initKnoten = new ArrayList<Knoten>();
		initBoegen = new ArrayList<Bogen>();
		
		knoten = graph.getKnoten();
		boegen = graph.getBoegen();
		
		for (int i = 0; i < knoten.length -1; i++) {
			initKnoten.add(knoten[i]);
		}
		initKnoten.add( new Knoten(0));
		
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
		initBoegen.clear();
		for (int i = 0; i < boegen.length - 1; i++) {
			if (boegen[i] != null) {
				initBoegen.add(boegen[i]);
				if (Math.abs(boegen[i].getKosten()) > maxKosten) {
					maxKosten = Math.abs(boegen[i].getKosten());
				}
			}
		}
		
		int M = 1 + 1/2 * knoten.length * maxKosten;
		for (int i = 0; i < V_plus.size(); i++) {
			Bogen bogen = new Bogen(0, -1, M, V_plus.get(i), initKnoten.get(initKnoten.size() - 1));
			V_plus.get(i).addAusgehendenBogen(bogen);
			initKnoten.get(initKnoten.size()- 1).addEingehendenBogen(bogen);
			initBoegen.add(bogen);
		}
		for (int i = 0; i < V_minus.size(); i++) {
			Bogen bogen = new Bogen(0, -1, M, initKnoten.get(initKnoten.size() - 1), V_minus.get(i));
			initKnoten.get(initKnoten.size() - 1).addAusgehendenBogen(bogen);
			V_minus.get(i).addEingehendenBogen(bogen);
			//initBoegen.add(boegen.length + V_plus.size() + i, bogen);
			initBoegen.add(bogen);
		}

		L = new ArrayList<Bogen>();
		T = new ArrayList<Bogen>();
		U = new ArrayList<Bogen>();
		
		for (int i = 0; i < boegen.length; i++) {
			L.add(initBoegen.get(i));
		}
		for (int i = boegen.length; i < initBoegen.size(); i++) {
			T.add(initBoegen.get(i));
		}
	}
	
	public void berechnungKnotenpreise () {
		
	}
	
	public Boolean optimalitaetstest () {
		// dummy
		return true;
	}
	
	public void pricing () {
		
	}
	
	public void augmentierung () {
		
	}
	
	public void update () {
		
	}
	
	public void printSolution() {
		System.out.printf("This is the end my friend");		
	}
}
