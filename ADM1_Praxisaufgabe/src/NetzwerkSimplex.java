import java.util.ArrayList;
import java.util.HashMap;


public class NetzwerkSimplex {

	private Knoten[] knoten;
	private Bogen[]  boegen;
	
	private Knoten[] initKnoten;
	private Bogen[]  initBoegen;
	
	private HashMap<Knoten,Knoten> p;
	
	private ArrayList<Bogen> T = new ArrayList<Bogen>();
	private ArrayList<Bogen> L = new ArrayList<Bogen>();
	private ArrayList<Bogen> U = new ArrayList<Bogen>();
	
	public NetzwerkSimplex () {}
	
	public void berechne (Digraph graph) {
		knoten = graph.getKnoten();
		boegen = graph.getBoegen();
		
		initialisierung();
		while (true) {
			berechneKnotenpreise();
			Bogen e = pricing();
			if (e == null) {
				break;				
			}
			update(e);
		}
		
		for (int i = boegen.length; i < initBoegen.length; i++) {
			if (initBoegen[i].getFluss() > 0) {
				System.out.println("Es existiert kein zulässiger Fluss für diesen Digraphen");
				return;
			}
		}
		System.out.println("Der optimale Fluss ist:");
		for (int i = 0; i < boegen.length; i++) {
			Bogen b = boegen[i];
			System.out.println("(" + b.getAusgehendenKnoten().getID() + "," + b.getEingehendenKnoten().getID()
					+ ") = " + b.getFluss());
			
		}
		
	}
	
	public void initialisierung () {
		initKnoten = new Knoten[knoten.length + 1];
		for (int i = 0; i < knoten.length; i++) {
			initKnoten[i] = knoten[i];
		}	
		initKnoten[initKnoten.length - 1] = new Knoten(0,0);
		initKnoten[initKnoten.length - 1].setKnotenpreis(new Double(0));
		
		p = new HashMap<Knoten,Knoten>();
		for (int i = 0; i < initKnoten.length - 1; i++) {
			p.put(initKnoten[i], initKnoten[initKnoten.length - 1]);
		}
		
		ArrayList<Knoten> V_plus = new ArrayList<Knoten>();
		ArrayList<Knoten> V_minus = new ArrayList<Knoten>();		
		for (int i = 0; i < knoten.length; i++) {
			int nettobedarf = knoten[i].getBedarf();
			
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
		
		int M = (int) (1 + 0.5 * knoten.length * maxKosten);
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
		
		for (int i = 0; i < boegen.length; i++) {
			L.add(initBoegen[i]);
		}
		for (int i = boegen.length; i < initBoegen.length; i++) {
			T.add(initBoegen[i]);
		}
		
		//Fluss berechnen
		for (int i = 0; i < initBoegen.length; i++) {
			if (L.contains(initBoegen[i])) {
				initBoegen[i].setFluss(initBoegen[i].getUntereGrenze());
			} else {
				if (initKnoten[initKnoten.length - 1].getAusgehendeBoegen().contains(initBoegen[i])) {
					initBoegen[i].setFluss(initBoegen[i].getEingehendenKnoten().getBedarf());
				} 
				if (initKnoten[initKnoten.length - 1].getEingehendeBoegen().contains(initBoegen[i])) {
					initBoegen[i].setFluss(-initBoegen[i].getAusgehendenKnoten().getBedarf());
				}
			}
		}
	}
	
	public void berechneKnotenpreise () {	
		boolean fertig = false;
		while (!fertig) {
			fertig = true;
			for (int i = 0; i < T.size(); i++) {
				Bogen bogen = T.get(i);
				Knoten ausgehend = bogen.getAusgehendenKnoten();
				Knoten eingehend = bogen.getEingehendenKnoten();
				if (ausgehend.getKnotenpreis() == null && eingehend.getKnotenpreis() != null) {
					ausgehend.setKnotenpreis(new Double(eingehend.getKnotenpreis().doubleValue() - bogen.getKosten()));
					continue;
				}
				if (ausgehend.getKnotenpreis() != null && eingehend.getKnotenpreis() == null) {
					eingehend.setKnotenpreis(new Double(ausgehend.getKnotenpreis().doubleValue() + bogen.getKosten()));
					continue;
				}
				if (ausgehend.getKnotenpreis() == null && eingehend.getKnotenpreis() == null) {
					fertig = false;
				}
			}
			
		}
		
		for (int i = 0; i < boegen.length; i++) {
			Bogen b = boegen[i];
			boegen[i].setReduzierteKosten(b.getKosten() 
					+ b.getAusgehendenKnoten().getKnotenpreis()
					- b.getEingehendenKnoten().getKnotenpreis());
		}
	}
	
	public Bogen pricing () {
		Bogen best = null;
		double min = Double.MAX_VALUE;
		for (int i = 0; i < L.size(); i++) {
			if (L.get(i).getReduzierteKosten() < 0) {
				if (L.get(i).getReduzierteKosten() < min) {
					min = L.get(i).getReduzierteKosten();
					best = L.get(i);
				}
			}
		}
		
		if (best == null) {
			double max = Double.MIN_VALUE;
			for (int i = 0; i < U.size(); i++) {
				if (U.get(i).getReduzierteKosten() > 0) {
					if (U.get(i).getReduzierteKosten() > max) {
						max = U.get(i).getReduzierteKosten();
						best = U.get(i);
					}
				}
			}

		}
		
		return best;
	}
	
	public void update (Bogen e) {
		T.add(e);
		
		ArrayList<Bogen> F = new ArrayList<Bogen>();
		ArrayList<Bogen> B = new ArrayList<Bogen>();
		
		F.add(e);
		
		//Kreis finden
		Knoten k = e.getAusgehendenKnoten();
		Knoten pre = p.get(k);
		while(pre != null) {
			for (int i = 0; i < T.size(); i++) {
				Bogen b = T.get(i);
				if (b.getAusgehendenKnoten().equals(pre) && b.getEingehendenKnoten().equals(k)) {
					F.add(b);
				}
				if (b.getAusgehendenKnoten().equals(k) && b.getEingehendenKnoten().equals(pre)) {
					B.add(b);
				}
			}
			
			k = pre;
			pre = p.get(k);
		}
		
		k = e.getEingehendenKnoten();
		pre = p.get(k);
		while(pre != null) {
			for (int i = 0; i < T.size(); i++) {
				Bogen b = T.get(i);
				if (b.getAusgehendenKnoten().equals(pre) && b.getEingehendenKnoten().equals(k)) {
					B.add(b);
				}
				if (b.getAusgehendenKnoten().equals(k) && b.getEingehendenKnoten().equals(pre)) {
					F.add(b);
				}
			}
			
			k = pre;
			pre = p.get(k);
		}
		
		//Flussänderung berechnen
		int eps = Integer.MAX_VALUE;	
		for (int i = 0; i < B.size(); i++) {
			int wert = B.get(i).getFluss() - B.get(i).getUntereGrenze();
			if (wert < eps) {
				eps = wert;
			}
		}
		
		for (int i = 0; i < F.size(); i++) {
			int wert = F.get(i).getObereGrenze() - F.get(i).getFluss();
			if (wert < eps) {
				eps = wert;
			}
		}
		
		//Fluss neu berechnen und f wählen
		Bogen f = null;
		for (int i = 0; i < B.size(); i++) {
			Bogen b = B.get(i);
			b.setFluss(B.get(i).getFluss() - eps);
			if (b.getFluss() == b.getUntereGrenze()) {
				f = b;
			}
		}
		for (int i = 0; i < F.size(); i++) {			
			Bogen b = F.get(i);
			b.setFluss(b.getFluss() + eps);
			if (b.getFluss() == b.getObereGrenze()) {
				f = b;
			}
		}
		
		//update
		T.remove(f);
		if (L.contains(e)) {
			L.remove(e);
		} else {
			U.remove(e);
		}
		
		if (f.getFluss() == f.getUntereGrenze()) {
			L.add(f);
		} else {
			U.add(f);
		}
		
		if (!f.equals(e)) {
			p.put(e.getEingehendenKnoten(), e.getAusgehendenKnoten());
			e.getEingehendenKnoten().setKnotenpreis(null);
		}
	}
}
