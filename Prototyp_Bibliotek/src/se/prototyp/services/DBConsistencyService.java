package se.prototyp.services;

public class DBConsistencyService extends se.prototyp.database.DBConnection{


	public boolean checkUserExistanceAndRole(String anvandarnamn, String losenord) {
		return super.checkUserExistanceAndRole(anvandarnamn, losenord);
	}
	public boolean checkIfUserNameExists(String userName) {
		return super.checkIfUserNameExists(userName);
	}
	public boolean checkIfPasswordExists(String password) {
		return super.checkIfPasswordExists(password);
	}
	public boolean checkIfPasswordExistsInSchoolDatabase(String personnummer, String losenord) {
		return super.checkIfPasswordExistsInSchoolDatabase(personnummer, losenord);
	}
	public boolean addUserFromExistingDatabase(String anvandarnamnIn,
			String losenordIn) {
		return super.addUserFromExistingDatabase(anvandarnamnIn, losenordIn);
	}
	
}
