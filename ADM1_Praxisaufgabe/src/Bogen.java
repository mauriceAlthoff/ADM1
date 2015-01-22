
public class Bogen {

	private double fluss;
	
	private int untereGrenze;
	private int obereGrenze;
	private int kosten;
	
	
	private Knoten ausgehend;
	private Knoten eingehend;
	
	private double reduzierteKosten;
	
	public Bogen (int untereGrenze, int obereGrenze, int kosten, Knoten ausgehend, Knoten eingehend) {
		this.untereGrenze = untereGrenze;
		this.obereGrenze = obereGrenze;
		this.kosten = kosten;
		
		this.ausgehend = ausgehend;
		this.eingehend = eingehend;
	}
	
	public double getFluss() {
		return fluss;
	}
	
	public int getUntereGrenze () {
		return untereGrenze;
	}
	
	public int getObereGrenze () {
		return obereGrenze;
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
	
	public double getReduzierteKosten() {
		return reduzierteKosten;
	}
	
	public void setFluss (double fluss) {
		this.fluss = fluss;
	}
	
	public void setUntereGrenze (int untereGrenze) {
		this.untereGrenze = untereGrenze;
	}
	
	public void setObereGrenze (int obereGrenze) {
		this.obereGrenze = obereGrenze;
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
	
	public void setReduzierteKosten (double reduzierteKosten) {
		this.reduzierteKosten = reduzierteKosten;
	}
		
}
