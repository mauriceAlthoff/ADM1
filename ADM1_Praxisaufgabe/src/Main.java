public class Main {
	public static void main(String[] args) {
		Knoten[] knoten = new Knoten[3];
		Bogen[] boegen = new Bogen[4];
		
		knoten[0] = new Knoten(1, -7);
		knoten[1] = new Knoten(2, 5);
		knoten[2] = new Knoten(3, 2);
		
		boegen[0] = new Bogen(0, 4, 3, knoten[0], knoten[1]);
		knoten[0].addAusgehendenBogen(boegen[0]);
		knoten[1].addEingehendenBogen(boegen[0]);
		
		boegen[1] = new Bogen(0, 10, 1, knoten[0], knoten[2]);
		knoten[0].addAusgehendenBogen(boegen[1]);
		knoten[2].addEingehendenBogen(boegen[1]);
		
		boegen[2] = new Bogen(0, 3, 1, knoten[1], knoten[2]);
		knoten[1].addAusgehendenBogen(boegen[2]);
		knoten[2].addEingehendenBogen(boegen[2]);
		
		boegen[3] = new Bogen(0, 5, 8, knoten[2], knoten[1]);
		knoten[2].addAusgehendenBogen(boegen[3]);
		knoten[1].addEingehendenBogen(boegen[3]);
		
		Digraph graph = new Digraph(knoten, boegen);
		new NetzwerkSimplex().berechne(graph);
		
		Digraph graph2 = new Digraph("testset2014/stndrd1.net"); //cap1.net
		new NetzwerkSimplex().berechne(graph2);
		
		Digraph graph1 = new Digraph("testset2014/chvatal1.net");
		new NetzwerkSimplex().berechne(graph1);
		
		Digraph graph3 = new Digraph("testset2014/cap1.net");
		new NetzwerkSimplex().berechne(graph3);
	}
}
