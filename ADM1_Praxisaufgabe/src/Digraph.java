import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Digraph {
	
	private Knoten[] knoten = null;
	private Bogen[]  boegen = null;
	
	public Digraph (Knoten[] knoten, Bogen[] boegen) {
		this.knoten = knoten;
		this.boegen = boegen;
	}
	
	public Digraph (String dateiname) {
		try {
			RandomAccessFile raf = new RandomAccessFile(dateiname, "r");
			int bogenIdx = 0;
			
			String zeile = raf.readLine();
			while (zeile != null) {
				String[] token = zeile.split(" ");
				if (token[0].equals("p")) {
					int k = Integer.parseInt(token[2]); 
					int b = Integer.parseInt(token[3]);
					System.out.printf("Dimensions %d %d\n", k,b);
					knoten = new Knoten[k];
					boegen = new Bogen[b];
				}
				if (token[0].equals("n")) {
					knoten[Integer.parseInt(token[1])-1] = new Knoten(Integer.parseInt(token[1]), Integer.parseInt(token[2])); 
				}
				if (token[0].equals("a")) {
					Bogen bogen = new Bogen(Integer.parseInt(token[3]), Integer.parseInt(token[4])
							, Integer.parseInt(token[5]), knoten[Integer.parseInt(token[1])-1]
							, knoten[Integer.parseInt(token[2])-1]);
					boegen[bogenIdx] = bogen;
					knoten[Integer.parseInt(token[1])-1].addAusgehendenBogen(bogen);
					knoten[Integer.parseInt(token[2])-1].addEingehendenBogen(bogen);
				}
				
				zeile = raf.readLine();
			}
			raf.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setKnoten (Knoten[] knoten) {
		this.knoten = knoten;
	}
	
	public void setBoegen (Bogen[] boegen) {
		this.boegen = boegen;
	}
	
	public Knoten[] getKnoten () {
		return knoten;
	}
	
	public Bogen[] getBoegen () {
		return boegen;
	}
}
