package modell;

import java.util.Date;

public class Forslag {

	Anvandare anvandare;
	String inkopsforslag;
	
	public Forslag(Anvandare anvandare, String inkopsforslag) {
		super();
		this.anvandare = anvandare;
		this.inkopsforslag = inkopsforslag;
	}
	public Anvandare getAnvandare() {
		return anvandare;
	}
	public void setAnvandare(Anvandare anvandare) {
		this.anvandare = anvandare;
	}
	public String getInkopsforslag() {
		return inkopsforslag;
	}
	public void setInkopsforslag(String inkopsforslag) {
		this.inkopsforslag = inkopsforslag;
	}
	
	
}
