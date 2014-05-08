package se.prototyp.services;

import java.util.ArrayList;

public class GetUserInfoService extends se.prototyp.database.DBConnection{

	public ArrayList<String> getUserInfo(String userName, String password) {
		return super.getUserInfo(userName, password);
	}

}
