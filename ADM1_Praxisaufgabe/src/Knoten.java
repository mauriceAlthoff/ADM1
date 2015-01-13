import java.util.ArrayList;

public class Knoten {

	private int bedarf;
	
	private ArrayList<Bogen> ausgehend = new ArrayList<Bogen>();
	private ArrayList<Bogen> eingehend = new ArrayList<Bogen>();
	
	public Knoten (int flow) {
		this.bedarf = flow;
	}
	
	public int getBedarf () {
		return bedarf;
	}
	
	public ArrayList<Bogen> getAusgehendeBoegen () {
		return ausgehend;
	}
	
	public ArrayList<Bogen> getEingehendeBoegen () {
		return eingehend;
	}
	
	public void setBedarf (int bedarf) {
		this.bedarf = bedarf;
	}
	
	public void addAusgehendenBogen (Bogen bogen) {
		this.ausgehend.add(bogen);
	}
	
	public void addEingehendenBogen (Bogen bogen) {
		this.eingehend.add(bogen);
	}
}
