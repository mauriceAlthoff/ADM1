
public class Bogen {

	private int untereGrenze;
	private int kapazitaet;
	private int kosten;
	
	private Knoten ausgehend;
	private Knoten eingehend;
	
	public Bogen (int untereGrenze, int kapazitaet, int kosten, Knoten ausgehend, Knoten eingehend) {
		this.untereGrenze = untereGrenze;
		this.kapazitaet = kapazitaet;
		this.kosten = kosten;
		
		this.ausgehend = ausgehend;
		this.eingehend = eingehend;
	}
	
	public int getUntereGrenze () {
		return untereGrenze;
	}
	
	public int getKapazitaet () {
		return kapazitaet;
	}
	
	public int getKosten () {
		return kosten;
	}
	
	public Knoten getEingehendenKnoten () {
		return eingehend;
	}
	
	public Knoten getAusgehendenKnoten () {
		return ausgehend;
	}
	
	public void setUntereGrenze (int untereGrenze) {
		this.untereGrenze = untereGrenze;
	}
	
	public void setKapazitaet (int kapazitaet) {
		this.kapazitaet = kapazitaet;
	}
	
	public void setKosten (int kosten) {
		this.kosten = kosten;
	}
	
	public void setEingehendenKnoten (Knoten eingehend) {
		this.eingehend = eingehend;
	}
	
	public void setAusgehendenKnoten (Knoten ausgehend) {
		this.ausgehend = ausgehend;
	}
		
}
