package modell;

import java.util.Calendar;

public class Reservation {

	Anvandare anvandare;
	Dokument dokument;
	Calendar startdatum;
	Calendar slutdatum;
	
	public Reservation(Anvandare anvandare, Dokument dokument, Calendar startdatum,
			Calendar slutdatum) {
		super();
		this.anvandare = anvandare;
		this.dokument = dokument;
		this.startdatum = startdatum;
		this.slutdatum = slutdatum;
	}
	
	public Anvandare getAnvandare() {
		return anvandare;
	}
	public void setAnvandare(Anvandare anvandare) {
		this.anvandare = anvandare;
	}
	public Dokument getDokument() {
		return dokument;
	}
	public void setDokument(Dokument dokument) {
		this.dokument = dokument;
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
	
	
	
	
	
}
