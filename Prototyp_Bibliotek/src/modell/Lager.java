package modell;

public class Lager implements Comparable<Lager>{

	int dokumentID;
	Dokument dokument;
	String hylla;
	String hyllplan;
	byte tillganglig;
	String tillagd;
	
	public Lager(int dokumentID, Dokument dokument, String hylla, String hyllplan
			, byte tillganglig, String tillagd ) {
		super();
		this.dokumentID = dokumentID;
		this.dokument = dokument;
		this.hylla = hylla;
		this.hyllplan = hyllplan;
		this.tillganglig = tillganglig;
		this.tillagd = tillagd;
	}
	
	public String tillagdNar(){
		return String.format("%s", tillagd);
	}
	
	public String toString(){
		return String.format("%s", dokument.getTitel());
	}
	public String getTillagd() {
		return tillagd;
	}
	public void setTillagd(String tillagd) {
		this.tillagd = tillagd;
	}
	public int getDokumentID() {
		return dokumentID;
	}
	public void setDokumentID(int dokumentID) {
		this.dokumentID = dokumentID;
	}
	public Dokument getDokument() {
		return dokument;
	}
	public void setDokument(Dokument dokument) {
		this.dokument = dokument;
	}
	public String getHylla() {
		return hylla;
	}
	public void setHylla(String hylla) {
		this.hylla = hylla;
	}
	public String getHyllplan() {
		return hyllplan;
	}
	public void setHyllplan(String hyllplan) {
		this.hyllplan = hyllplan;
	}
	public byte getTillganglig() {
		return tillganglig;
	}
	public void setTillganglig(byte tillganglig) {
		this.tillganglig = tillganglig;
	}


	public int compareTo(Lager l) {
		return -this.tillagd.compareTo(l.tillagd);
	}
	
	
	
}
