package se.prototyp.database;

import java.sql.*;
import java.util.ArrayList;


public class DBConnection {
    Connection connection = null;
    Connection connection2 = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    
    // CONNECTION 1
    public Connection createConnection() {
	Connection localConn = null;

	try { 
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException cnfe) {
	    System.err.println("Couldn't find driver class:");
	    cnfe.printStackTrace();
	    System.exit(1);
	}
//	System.out.println("Everything seems ok!");
  
	try {
//		localConn = DriverManager.getConnection("jdbc:mysql://46.239.118.12:3306/prototyp_bibliotek","frud", "ultrajacka112");
	    localConn = DriverManager.getConnection("jdbc:mysql://192.168.1.15:3306/prototyp_bibliotek","frud", "ultrajacka112");
//		localConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/prototyp_bibliotek","root", "chocs");
//	    localConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prototyp_bibliotek","root", "");
	    
	} catch (SQLException se) {
	    System.out.println("Couldn't connect: print out a stack trace and exit.");
	    se.printStackTrace();
	    System.exit(1);
	}
  
	if (localConn != null)
	    System.out.println("");
		//System.out.println("Congratulations! You are now connected to the database!");
	else
	    System.out.println("We should never get here.");
	System.out.println("Connection 1");
	return localConn;
    }

    
    
    // CONNECTION 2
    public Connection createConnection2() {
	Connection localConn = null;

	try { 
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException cnfe) {
	    System.err.println("Couldn't find driver class:");
	    cnfe.printStackTrace();
	    System.exit(1);
	}
//	System.out.println("Everything seems ok!");
  
	try {
//		localConn = DriverManager.getConnection("jdbc:mysql://46.239.118.12:3306/prototyp_bibliotek","frud", "ultrajacka112");
	    localConn = DriverManager.getConnection("jdbc:mysql://192.168.1.15:3306/personregister","frud", "ultrajacka112");
//		localConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/prototyp_bibliotek","root", "chocs");
//	    localConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prototyp_bibliotek","root", "");
	    
	} catch (SQLException se) {
	    System.out.println("Couldn't connect: print out a stack trace and exit.");
	    se.printStackTrace();
	    System.exit(1);
	}
  
	if (localConn != null)
	    System.out.println("");
		//System.out.println("Congratulations! You are now connected to the database!");
	else
	    System.out.println("We should never get here.");
	System.out.println("Connection 2");
	return localConn;
    }
    
    
    
    
    
    public void runDB(){
    	connection = createConnection();
    	connection2 = createConnection2();
    }
    
    public DBConnection(){
    	runDB();
    }
    
    
	public boolean addUserFromExistingDB(String anvandarnamnIn, String losenordIn){
		ArrayList<String> dataFranPersondatabas = new ArrayList<String>();
		try{
			// Hämtar data om personen från persondatabasen.
			preparedStatement = connection2.prepareStatement("SELECT * FROM Person WHERE Användarnamn = ? AND Lösenord = ?");
			preparedStatement.setString(1, anvandarnamnIn);
			preparedStatement.setString(2, losenordIn);
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){
				return false;
			}
			// Sparar resultatet i en lista
			dataFranPersondatabas.add(resultSet.getString(1));
			dataFranPersondatabas.add(resultSet.getString(2));
			dataFranPersondatabas.add(resultSet.getString(3));
			dataFranPersondatabas.add(resultSet.getString(4));
			dataFranPersondatabas.add(resultSet.getString(5));
			dataFranPersondatabas.add(resultSet.getString(6));
			dataFranPersondatabas.add(resultSet.getString(7));
			dataFranPersondatabas.add(resultSet.getString(8));
			dataFranPersondatabas.add(resultSet.getString(9));
			dataFranPersondatabas.add(resultSet.getString(10));
			
			// Sparar datan från listan i strängar
			String personnummer = dataFranPersondatabas.get(0);
			String anvandarnamn = dataFranPersondatabas.get(1);
			String losenord = dataFranPersondatabas.get(2);
			String fornamn = dataFranPersondatabas.get(3);
			String efternamn = dataFranPersondatabas.get(4);
			String gatuadress = dataFranPersondatabas.get(5);
			String stad = dataFranPersondatabas.get(6);
			String postnummer = dataFranPersondatabas.get(7);
			String telefon = dataFranPersondatabas.get(8);
			String epost = dataFranPersondatabas.get(9);
			// Lägger in datan i bibliotek informatikas databas
			preparedStatement = connection.prepareStatement("INSERT INTO Person (Personnummer, Användarnamn, Lösenord, Förnamn, Efternamn, Gatuadress, Stad, Postnummer, Telefon, Epost) VALUES (?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, personnummer);
			preparedStatement.setString(2, anvandarnamn);
			preparedStatement.setString(3, losenord);
			preparedStatement.setString(4, fornamn);
			preparedStatement.setString(5, efternamn);
			preparedStatement.setString(6, gatuadress);
			preparedStatement.setString(7, stad);
			preparedStatement.setString(8, postnummer);
			preparedStatement.setString(9, telefon);
			preparedStatement.setString(10, epost);
			int forandring = preparedStatement.executeUpdate();
			// Om operationen lyckades så returnerar vi sant
			if(forandring > 0){
				return true;
			}
			
		}
		catch(SQLException se){
			System.out.println(se.getMessage());
		}
		// Om operationen inte lyckades returnerar vi falskt
		return false;
	}
    
    
    // Kollar om personen finns i persondatabasen på skolan
	public boolean checkUserExistanceAndRole(String anvandarnamn, String losenord){
		try{
    		// Returnera 1 om personen är både student och anställd
    		preparedStatement = connection2.prepareStatement("SELECT * FROM Person WHERE Användarnamn = ? AND Lösenord = ? AND Personnummer IN (SELECT Personnummer FROM Rollinnehav)");
    		preparedStatement.setString(1, anvandarnamn);
    		preparedStatement.setString(2, losenord);
    		resultSet = preparedStatement.executeQuery();
    		if(resultSet.next()){
    			return true;
    		}

    	}
    	catch(SQLException se){
    		System.out.println(se.getMessage());
    	}
    	// Returnera 0 om personen inte existerar i databasen
    	return false;
    }
	// Kollar om det lösenord personen skrivit in stämmer överrens med det redan lagrade i persondatabasen
	public boolean checkIfUserExistsInSchoolDB(String personnummer, String losenord){
		boolean exists = false;
		try{
			preparedStatement = connection2.prepareStatement("SELECT * FROM Person WHERE Lösenord = ? AND Personnummer = ?");
			preparedStatement.setString(1, losenord);
			preparedStatement.setString(2, personnummer);			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				exists = true;
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return exists;
	}
	
	
	public boolean checkUserExistance(String anvandarnamn, String losenord){
    	boolean memberExist = false;
    	try{
    		preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE Användarnamn = ? AND Lösenord = ?");
    		preparedStatement.setString(1, anvandarnamn);
    		preparedStatement.setString(2, losenord);
    		resultSet = preparedStatement.executeQuery();
    		if(resultSet.next()){
    			memberExist = true;
    		}
    	}
    	catch(SQLException se){
    		System.out.println(se.getMessage());
    	}
    	return memberExist;
    }
	
	public boolean addUser(String anvandarnamn, String losenord, String personnummer, String fornamn, 
			String efternamn, String gatuadress, String stad, String postnummer, String telefon, String epost){
		boolean userAdded = false;
		try{
			preparedStatement = connection.prepareStatement("INSERT INTO Person (Användarnamn, Lösenord, Personnummer, Förnamn, Efternamn, Gatuadress, Stad, Postnummer, Telefon, Epost) VALUES(?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, anvandarnamn);
			preparedStatement.setString(2, losenord);
			preparedStatement.setString(3, personnummer);
			preparedStatement.setString(4, fornamn);
			preparedStatement.setString(5, efternamn);
			preparedStatement.setString(6, gatuadress);
			preparedStatement.setString(7, stad);
			preparedStatement.setString(8, postnummer);
			preparedStatement.setString(9, telefon);
			preparedStatement.setString(10, epost);
			int change = preparedStatement.executeUpdate();
			if(change > 0){
				userAdded = true;
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return userAdded;
	}
	public ArrayList<String> getTitles(){
		ArrayList<String> list = new ArrayList<String>();
		String bookLine;
		try{
			preparedStatement = connection.prepareStatement("SELECT * from litterature");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				bookLine = "";
				bookLine = bookLine + "ID: " +  String.valueOf(resultSet.getInt(1));
				bookLine = bookLine + "Titel: " + resultSet.getString(2);
				list.add(bookLine);
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return list;
	}
	public ArrayList<String> getTitles(String title){
		ArrayList<String> list = new ArrayList<String>();
		String bookLine;
		try{
			preparedStatement = connection.prepareStatement("SELECT * from litterature WHERE Titel = ?");
			preparedStatement.setString(1, title);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				bookLine = "";
				bookLine = bookLine + "ID: " +  String.valueOf(resultSet.getInt(1));
				bookLine = bookLine + "Titel: " + resultSet.getString(2);
				list.add(bookLine);
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return list;
	}
	public int addLiterature(String title){
		int change = 0;
		try{
			preparedStatement = connection.prepareStatement("INSERT INTO litterature (Titel) VALUES (?)");
			preparedStatement.setString(1, title);
			change = preparedStatement.executeUpdate();
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return change;
		
	}
	public int getNumberOfTitles(){
		int amount = 0;
		try{
			preparedStatement = connection.prepareStatement("SELECT * from litterature");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				amount++;
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return amount;
		
	}
	public ArrayList<String> getLoans(){
		ArrayList<String> list = new ArrayList<String>();
		String loanLine;
		try{
			//SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate
			//FROM Orders
			//INNER JOIN Customers
			//ON Orders.CustomerID=Customers.CustomerID;
			//preparedStatement = dbConnect.connection.prepareStatement("SELECT UserName FROM user_lantagare WHERE ID IN (SELECT User FROM loans)");
			preparedStatement = connection.prepareStatement("SELECT user_lantagare.UserName, litterature.Titel FROM loans INNER JOIN user_lantagare ON user_lantagare.ID = loans.User INNER JOIN litterature ON litterature.ID = loans.Litterature");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				loanLine = "";
				loanLine = loanLine + "Låntagare: " + resultSet.getString(1);
				loanLine = loanLine + "| Lånat verk: " + resultSet.getString(2);
				list.add(loanLine);
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		
		return list;
	}
	public ArrayList<String> getUserInfo(String anvandarnamn, String losenord){
		ArrayList<String> list = new ArrayList<String>();
		try{
			// Hämta all data ifrån Person-tabellen och spara i en lista
			preparedStatement = connection.prepareStatement("SELECT * from Person WHERE Användarnamn = ? AND Lösenord = ?");
			preparedStatement.setString(1, anvandarnamn);
			preparedStatement.setString(2, losenord);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				list.add(resultSet.getString(1));
				list.add(resultSet.getString(2));
				list.add(resultSet.getString(3));
				list.add(resultSet.getString(4));
				list.add(resultSet.getString(5));
				list.add(resultSet.getString(6));
				list.add(resultSet.getString(7));
				list.add(resultSet.getString(8));
				list.add(resultSet.getString(9));
				list.add(resultSet.getString(10));
			}

			
			// Hämta data om personens roll och lägg i slutet av lista
			preparedStatement = connection.prepareStatement("SELECT Roll FROM Rollinnehav WHERE Personnummer IN (SELECT Personnummer FROM Person WHERE Användarnamn = ?)");
			preparedStatement.setString(1, anvandarnamn);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				list.add(resultSet.getString(1));
			}

			

		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return list;
	}

	public boolean editUser(int id, String userName, String firstName, String familyName, String password){
		boolean edited = false;
		try{
			preparedStatement = connection.prepareStatement("UPDATE user SET UserName = ?, FirstName = ?, SecondName = ?, Password = ? WHERE ID = ?");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, familyName);
			preparedStatement.setString(4, password);
			preparedStatement.setInt(5,id);
			int numOfRowsEdited = preparedStatement.executeUpdate();
			if(numOfRowsEdited>0){
				edited = true;
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return edited;

	}
	public boolean checkIfPasswordExists(String losenord){
		boolean exists = false;
		try{
			preparedStatement = connection.prepareStatement("SELECT * from Person WHERE Lösenord = ?");
			preparedStatement.setString(1, losenord);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				exists = true;
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return exists;
	}
	public boolean checkIfUserNameExists(String anvandarnamn){
		boolean exists = false;
		try{
			preparedStatement = connection.prepareStatement("SELECT * from Person WHERE Användarnamn = ?");
			preparedStatement.setString(1, anvandarnamn);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				exists = true;
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return exists;
	}
}

