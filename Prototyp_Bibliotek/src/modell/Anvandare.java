package modell;

public class Anvandare {

	String personnummer;
	String anvandarnamn;
	String losenord;
	String fornamn;
	String efternamn;
	String gatuadress;
	String stad;
	String postnummer;
	String telefon;
	String epost;
	Roll roll;
	
	public Anvandare(){
		
	}
	
	public Anvandare(String personnummer, String anvandarnamn, String losenord,
			String fornamn, String efternamn, String gatuadress, String stad,
			String postnummer, String telefon, String epost) {
		super();
		this.personnummer = personnummer;
		this.anvandarnamn = anvandarnamn;
		this.losenord = losenord;
		this.fornamn = fornamn;
		this.efternamn = efternamn;
		this.gatuadress = gatuadress;
		this.stad = stad;
		this.postnummer = postnummer;
		this.telefon = telefon;
		this.epost = epost;
	}
	
	public String getPersonnummer() {
		return personnummer;
	}
	public void setPersonnummer(String personnummer) {
		this.personnummer = personnummer;
	}
	public String getAnvandarnamn() {
		return anvandarnamn;
	}
	public void setAnvandarnamn(String anvandarnamn) {
		this.anvandarnamn = anvandarnamn;
	}
	public String getLosenord() {
		return losenord;
	}
	public void setLosenord(String losenord) {
		this.losenord = losenord;
	}
	public String getFornamn() {
		return fornamn;
	}
	public void setFornamn(String fornamn) {
		this.fornamn = fornamn;
	}
	public String getEfternamn() {
		return efternamn;
	}
	public void setEfternamn(String efternamn) {
		this.efternamn = efternamn;
	}
	public String getGatuadress() {
		return gatuadress;
	}
	public void setGatuadress(String gatuadress) {
		this.gatuadress = gatuadress;
	}
	public String getStad() {
		return stad;
	}
	public void setStad(String stad) {
		this.stad = stad;
	}
	public String getPostnummer() {
		return postnummer;
	}
	public void setPostnummer(String postnummer) {
		this.postnummer = postnummer;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEpost() {
		return epost;
	}
	public void setEpost(String epost) {
		this.epost = epost;
	}

	public Roll getRoll() {
		return roll;
	}

	public void setRoll(Roll roll) {
		this.roll = roll;
	}
	
	
	
}
