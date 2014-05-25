package modell;

public class EDokument extends Dokument{

	public EDokument(String isbn, String titel, String upplaga,
			String forfattare, String forlag, String sidantal, String sprak,
			String nyckelord, String beskrivning) {
		super(isbn, titel, upplaga, forfattare, forlag, sidantal, sprak, nyckelord,
				beskrivning);
		// TODO Auto-generated constructor stub
	}

	String filformat;

	public String getFilformat() {
		return filformat;
	}

	public void setFilformat(String filformat) {
		this.filformat = filformat;
	}
	
	
	
}
