import java.util.ArrayList;

public class Knoten {

	private int flow;
	
	private ArrayList<Bogen> ausgehend = new ArrayList<Bogen>();
	private ArrayList<Bogen> eingehend = new ArrayList<Bogen>();
	
	public Knoten (int flow) {
		this.flow = flow;
	}
	
	public int getFlow () {
		return flow;
	}
	
	public ArrayList<Bogen> getAusgehendeBoegen () {
		return ausgehend;
	}
	
	public ArrayList<Bogen> getEingehendeBoegen () {
		return eingehend;
	}
	
	public void setFlow (int flow) {
		this.flow = flow;
	}
	
	public void addAusgehendenBogen (Bogen bogen) {
		this.ausgehend.add(bogen);
	}
	
	public void addEingehendenBogen (Bogen bogen) {
		this.eingehend.add(bogen);
	}
}
