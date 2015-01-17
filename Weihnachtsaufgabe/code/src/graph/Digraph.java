package graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Digraph {

	private Knoten[] knoten;
	private Bogen[] boegen;
	
	public Digraph (String dateiname) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dateiname),"UTF-8"));		
			ArrayList<Knoten> k = new ArrayList<Knoten>();
			ArrayList<Bogen>  b = new ArrayList<Bogen>();
			
			String line = reader.readLine();
			while (line != null) {
				String[] token = line.split("\t");
				k.add(new Knoten(token[0]));
				line = reader.readLine();
			}
			reader.close();
			
			
			knoten = new Knoten[k.size()];
			k.toArray(knoten);
			
			for (int i = 0; i < knoten.length; i++) {
				for (int j = i; j < knoten.length; j++) {
					if (!knoten[i].getNomen().equals(knoten[j].getNomen())) {
						boolean match = false;
						if (knoten[i].getNomen().charAt(0) == knoten[j].getNomen().charAt(0)
								&& knoten[i].getNomen().charAt(1) == knoten[j].getNomen().charAt(1)
								&& knoten[i].getNomen().charAt(2) == knoten[j].getNomen().charAt(2)) {
							match = true;
						}
						if (knoten[i].getNomen().charAt(0) == knoten[j].getNomen().charAt(0)
								&& knoten[i].getNomen().charAt(1) == knoten[j].getNomen().charAt(1)
								&& knoten[i].getNomen().charAt(3) == knoten[j].getNomen().charAt(3)) {
							match = true;
						}
						if (knoten[i].getNomen().charAt(0) == knoten[j].getNomen().charAt(0)
								&& knoten[i].getNomen().charAt(2) == knoten[j].getNomen().charAt(2)
								&& knoten[i].getNomen().charAt(3) == knoten[j].getNomen().charAt(3)) {
							match = true;
						}
						if (knoten[i].getNomen().charAt(1) == knoten[j].getNomen().charAt(1)
								&& knoten[i].getNomen().charAt(2) == knoten[j].getNomen().charAt(2)
								&& knoten[i].getNomen().charAt(3) == knoten[j].getNomen().charAt(3)) {
							match = true;
						}
						
						if (match) {
							Bogen bogen = new Bogen(knoten[i],knoten[j]);
							knoten[i].addBogen(bogen);
							knoten[j].addBogen(bogen);
							b.add(bogen);
						}
					}
				}
			}
			
			boegen = new Bogen[b.size()];
			b.toArray(boegen);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void groeßterGrad () {
		int max = 0;
		for (int i = 0; i < knoten.length; i++) {
			if (knoten[i].getBoegen().size() > max) {
				max = knoten[i].getBoegen().size();
			}
		}
		
		System.out.println("Größter Grad eines Knotens ist " + max);
		for (int i = 0; i < knoten.length; i++) {
			if (knoten[i].getBoegen().size() == max) {
				System.out.println("Knoten \"" + knoten[i].getNomen() + "\" ist ein Beispiel.");
			}
		}
	}
	
	public void zusammenhaengend () {
		int komponente = 0;
		HashMap<Knoten, Integer> cycle = new HashMap<Knoten, Integer>();
		for (int i = 0; i < knoten.length; i++) {
			if (!cycle.containsKey(knoten[i])) {
				komponente += 1;
				int anzahlKnoten = breitensuche(knoten[i], cycle);
//				System.out.println("Zusammenhangskomponente " + komponente + " besitzt " + anzahlKnoten + " Knoten");
				if (anzahlKnoten >= 20) {
					System.out.println("Zusammenhangskomponente " + komponente + " besitzt min. 20 Knoten");			
				}
				if (anzahlKnoten == 1) {
					System.out.println("Knoten \"" + knoten[i].getNomen() + "\" ist ein isolierter Knoten");
				}
			}
		}
		System.out.println("D besitzt " + komponente + " Zusammenhangskomponenten");
	}
	
	public int breitensuche (Knoten wurzel, HashMap<Knoten, Integer> cycle) {
		Queue<Knoten> pruefen = new LinkedList<Knoten>();

		int anzahlKnoten = 0;
		pruefen.add(wurzel);
		
		while (pruefen.peek() != null) {
			Knoten k = pruefen.poll();
			anzahlKnoten += 1;
			cycle.put(k, 0);
			
			ArrayList<Bogen> boegen = k.getBoegen();
			for (int i = 0; i < boegen.size(); i++) {
				Bogen b = boegen.get(i);
				Knoten k1 = b.getKnoten()[0];
				Knoten k2 = b.getKnoten()[1];
				if (cycle.get(k1) == null && !pruefen.contains(k1)) {
					pruefen.add(b.getKnoten()[0]);
				}
				if (cycle.get(k2) == null && !pruefen.contains(k2)) {
					pruefen.add(b.getKnoten()[1]);
				}
			}
		}
		
		return anzahlKnoten;
	}
}
