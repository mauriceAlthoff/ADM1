import java.util.ArrayList;


public class Knoten {
	
	private int id;

	private int bedarf;
	private Double knotenpreis;
	
	private ArrayList<Bogen> ausgehend = new ArrayList<Bogen>();
	private ArrayList<Bogen> eingehend = new ArrayList<Bogen>();
	
	public Knoten (int id, int bedarf) {
		this.id = id;
		this.bedarf = bedarf;
	}
	
	public int getID () {
		return id;
	}
	
	public int getBedarf () {
		return bedarf;
	}
	
	public Double getKnotenpreis () {
		return knotenpreis;
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
	
	public void setKnotenpreis (Double knotenpreis) {
		this.knotenpreis = knotenpreis;
	}
	
	public void addAusgehendenBogen (Bogen bogen) {
		this.ausgehend.add(bogen);
	}
	
	public void addEingehendenBogen (Bogen bogen) {
		this.eingehend.add(bogen);
	}
}
