package modell;

public class Pappersdokument extends Dokument {

	public Pappersdokument(String isbn, String titel, String upplaga,
			String forfattare, String forlag, String sidantal, String sprak,
			String nyckelord, String beskrivning) {
		super(isbn, titel, upplaga, forfattare, forlag, sidantal, sprak, nyckelord,
				beskrivning);
		// TODO Auto-generated constructor stub
	}

	String bindningstyp;

	public String getBindningstyp() {
		return bindningstyp;
	}

	public void setBindningstyp(String bindningstyp) {
		this.bindningstyp = bindningstyp;
	}
	
	
}
