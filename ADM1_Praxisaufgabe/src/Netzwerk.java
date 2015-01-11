import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Netzwerk {
	
	private Knoten[] knoten = null;
	private Bogen[]  boegen = null;
	
	public Netzwerk (String dateiname) {
		try {
			RandomAccessFile raf = new RandomAccessFile(dateiname, "r");
			int bogenIdx = 0;
			
			String zeile = raf.readLine();
			while (zeile != null) {
				String[] token = zeile.split(" ");
				if (token[0].equals("p")) {
					knoten = new Knoten[Integer.getInteger(token[3])];
					boegen = new Bogen[Integer.getInteger(token[4])];
				}
				if (token[0].equals("n")) {
					knoten[Integer.getInteger(token[1])-1] = new Knoten(Integer.getInteger(token[2])); 
				}
				if (token[0].equals("a")) {
					Bogen bogen = new Bogen(Integer.getInteger(token[4]), Integer.getInteger(token[5])
							, Integer.getInteger(token[6]), knoten[Integer.getInteger(token[1])]
							, knoten[Integer.getInteger(token[2])]);
					boegen[bogenIdx] = bogen;
					knoten[Integer.getInteger(token[1])].addAusgehendenBogen(bogen);
					knoten[Integer.getInteger(token[2])].addEingehendenBogen(bogen);
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
}
