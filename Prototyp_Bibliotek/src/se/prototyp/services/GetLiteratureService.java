package se.prototyp.services;

import java.util.ArrayList;

import se.prototyp.database.DBOperations;

public class GetLiteratureService extends se.prototyp.database.DBConnection {


	public ArrayList<String> getBooks() {
		return super.getBooks();
	}
	
	public ArrayList<String> getBooks(String title) {
		return super.getBooks(title);
	}
	
	public int getNumberOfTitles() {
		return super.getNumberOfTitles();
	}
	


}
