package modell;

import java.util.Calendar;

public class Lan {

	String lanID;
	Calendar startdatum;
	Calendar slutdatum;
	Dokument dokument;
	Anvandare anvandare;
	
	public Lan(String lanID, Calendar startdatum, Calendar slutdatum,
			Dokument dokument, Anvandare anvandare) {
		super();
		this.lanID = lanID;
		this.startdatum = startdatum;
		this.slutdatum = slutdatum;
		this.dokument = dokument;
		this.anvandare = anvandare;
	}
	
	public String listaLan(){
		return String.format("Låntagare: %25s Lånat dokument: %25s Tillbaka: %s", anvandare.getAnvandarnamn(), dokument.getTitel(), slutdatum.getTime());
	}
	public String getLanID() {
		return lanID;
	}
	public void setLanID(String lanID) {
		this.lanID = lanID;
	}
	public Calendar getStartdatum() {
		return startdatum;
	}
	public void setStartdatum(Calendar startdatum) {
		this.startdatum = startdatum;
	}
	public Calendar getSlutdatum() {
		return slutdatum;
	}
	public void setSlutdatum(Calendar slutdatum) {
		this.slutdatum = slutdatum;
	}
	public Dokument getDokument() {
		return dokument;
	}
	public void setDokument(Dokument dokument) {
		this.dokument = dokument;
	}
	public Anvandare getAnvandare() {
		return anvandare;
	}
	public void setAnvandare(Anvandare anvandare) {
		this.anvandare = anvandare;
	}

	

	
	
}
