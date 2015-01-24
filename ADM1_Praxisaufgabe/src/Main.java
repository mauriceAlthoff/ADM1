import java.io.File;
import java.util.ArrayList;

public class Main {
	private static ArrayList<String> testFiles = new ArrayList<String>();
	
	public static void printFnames(String sDir) {
		File[] faFiles = new File(sDir).listFiles();
		for (File file : faFiles) {
			if (file.getName().matches("([^\\s]+(\\.(?i)(\\d+|net))$)")) {
				testFiles.add(file.getAbsolutePath());
			}
			if (file.isDirectory()) {
				printFnames(file.getAbsolutePath());
			}
		}
	}
	
	public static void main(String[] args) {
//		Knoten[] knoten = new Knoten[3];
//		Bogen[] boegen = new Bogen[4];
//		
//		knoten[0] = new Knoten(1, -7);
//		knoten[1] = new Knoten(2, 5);
//		knoten[2] = new Knoten(3, 2);
//		
//		boegen[0] = new Bogen(0, 4, 3, knoten[0], knoten[1]);
//		knoten[0].addAusgehendenBogen(boegen[0]);
//		knoten[1].addEingehendenBogen(boegen[0]);
//		
//		boegen[1] = new Bogen(0, 10, 1, knoten[0], knoten[2]);
//		knoten[0].addAusgehendenBogen(boegen[1]);
//		knoten[2].addEingehendenBogen(boegen[1]);
//		
//		boegen[2] = new Bogen(0, 3, 1, knoten[1], knoten[2]);
//		knoten[1].addAusgehendenBogen(boegen[2]);
//		knoten[2].addEingehendenBogen(boegen[2]);
//		
//		boegen[3] = new Bogen(0, 5, 8, knoten[2], knoten[1]);
//		knoten[2].addAusgehendenBogen(boegen[3]);
//		knoten[1].addEingehendenBogen(boegen[3]);
//		
//		Digraph graph = new Digraph(knoten, boegen);
//		new NetzwerkSimplex().berechne(graph);

		printFnames("testset2014/");

		for(String file:testFiles){
			System.out.printf("File: %s\n", file);
			Digraph graph = new Digraph(file);
			new NetzwerkSimplex().berechne(graph);
		}
	}	
}
