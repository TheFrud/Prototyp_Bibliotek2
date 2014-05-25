package modell;

public class Roll {

	String roll;
	String beskrivning;
	
	
	
	public Roll(String roll, String beskrivning) {
		super();
		this.roll = roll;
		this.beskrivning = beskrivning;
	}
	
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public String getBeskrivning() {
		return beskrivning;
	}
	public void setBeskrivning(String beskrivning) {
		this.beskrivning = beskrivning;
	}
	
	
	
}
