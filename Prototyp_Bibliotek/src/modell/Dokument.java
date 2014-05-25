package modell;

public class Dokument implements Comparable<Dokument>{

	private String isbn;
	private String titel;
	private String upplaga;
	private String forfattare;
	private String forlag;
	private String sidantal;
	private String sprak;
	private String nyckelord;
	private String beskrivning;
	
	public Dokument(){
		
	}
	
	public Dokument(String isbn, String titel, String upplaga,
			String forfattare, String forlag, String sidantal, String sprak,
			String nyckelord, String beskrivning) {
		super();
		this.isbn = isbn;
		this.titel = titel;
		this.upplaga = upplaga;
		this.forfattare = forfattare;
		this.forlag = forlag;
		this.sidantal = sidantal;
		this.sprak = sprak;
		this.nyckelord = nyckelord;
		this.beskrivning = beskrivning;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setiSBN(String isbn) {
		this.isbn = isbn;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getUpplaga() {
		return upplaga;
	}
	public void setUpplaga(String upplaga) {
		this.upplaga = upplaga;
	}
	public String getForfattare() {
		return forfattare;
	}
	public void setForfattare(String forfattare) {
		this.forfattare = forfattare;
	}
	public String getForlag() {
		return forlag;
	}
	public void setForlag(String forlag) {
		this.forlag = forlag;
	}
	public String getSidantal() {
		return sidantal;
	}
	public void setSidantal(String sidantal) {
		this.sidantal = sidantal;
	}
	public String getSprak() {
		return sprak;
	}
	public void setSprak(String sprak) {
		this.sprak = sprak;
	}
	public String getNyckelord() {
		return nyckelord;
	}
	public void setNyckelord(String nyckelord) {
		this.nyckelord = nyckelord;
	}
	public String getBeskrivning() {
		return beskrivning;
	}
	public void setBeskrivning(String beskrivning) {
		this.beskrivning = beskrivning;
	}
	public int compareTo(Dokument dok) {
		return this.getTitel().compareTo(dok.getTitel());
	}

	
}


