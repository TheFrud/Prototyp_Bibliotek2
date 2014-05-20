package se.prototyp.services;

import javax.naming.*;
import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class DBConsistencyService{

	private DataSource ds;
	private DataSource ds2;
	Connection connection = null;
	Connection connection2 = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
		
    public DBConsistencyService(){
	    try {
		      Context ctx = new InitialContext();
			  ds = (DataSource) ctx.lookup("java:comp/env/jdbc/prototyp_bibliotek");
			  ds2 = (DataSource) ctx.lookup("java:comp/env/jdbc/prototyp_bibliotek2");
			  
	    }
	    catch (NamingException e) {
	      e.printStackTrace();
	    }
	  }
  
	  private Connection getConnection() throws SQLException {
	    return ds.getConnection();
	  }
	  private Connection getConnection2() throws SQLException {
		    return ds.getConnection();
		  }
		  
	    // Kollar om personen finns i persondatabasen på skolan
		public boolean checkUserExistanceAndRole(String anvandarnamn, String losenord){
			try{
	    		// Returnera 1 om personen är både student och anställd
				connection2 = getConnection2();
	    		preparedStatement = connection2.prepareStatement("SELECT * FROM person WHERE Användarnamn = ? AND Lösenord = ? AND Personnummer IN (SELECT Personnummer FROM Rollinnehav)");
	    		preparedStatement.setString(1, anvandarnamn);
	    		preparedStatement.setString(2, losenord);
	    		resultSet = preparedStatement.executeQuery();
	    		if(resultSet.next()){
	    			return true;
	    		}
		    	return false;

	    	}
	    	catch(SQLException se){
	    		System.out.println(se.getMessage());
		    	return false;
	    	}
	        finally {
			      if (connection2 != null) 
			        try {connection2.close();} catch (SQLException e) {}
			      }
	    }
		
		// Kollar om det lösenord personen skrivit in stämmer överrens med det redan lagrade i persondatabasen
		public boolean checkIfUserExistsInSchoolDB(String personnummer, String losenord){
			boolean exists = false;
			try{
				connection2 = getConnection2();
				preparedStatement = connection2.prepareStatement("SELECT * FROM person WHERE Lösenord = ? AND Personnummer = ?");
				preparedStatement.setString(1, losenord);
				preparedStatement.setString(2, personnummer);			
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()){
					exists = true;
				}
				return exists;
			}
			catch(SQLException sqle){
				System.out.println(sqle.getMessage());
				return exists;
			}
	        finally {
			      if (connection2 != null) 
			        try {connection2.close();} catch (SQLException e) {}
			      }
	
		}
		
		
		public boolean autentisera(String anvandarnamn, String losenord){
	    	boolean memberExist = false;
	    	try{
	    		connection = getConnection();
	    		preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE Användarnamn = ? AND Lösenord = ?");
	    		preparedStatement.setString(1, anvandarnamn);
	    		preparedStatement.setString(2, losenord);
	    		resultSet = preparedStatement.executeQuery();
	    		if(resultSet.next()){
	    			memberExist = true;
	    		}
		    	return memberExist;
	    	}
	    	catch(SQLException se){
	    		System.out.println(se.getMessage());
		    	return memberExist;
	    	}
	        finally {
			      if (connection != null) 
			        try {connection.close();} catch (SQLException e) {}
			      }

	    }
		public boolean checkIfPasswordExists(String losenord){
			boolean exists = false;
			try{
				connection = getConnection();
				preparedStatement = connection.prepareStatement("SELECT * from person WHERE Lösenord = ?");
				preparedStatement.setString(1, losenord);
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()){
					exists = true;
				}
				return exists;
			}
			catch(SQLException sqle){
				System.out.println(sqle.getMessage());
				return exists;
			}
	        finally {
			      if (connection != null) 
			        try {connection.close();} catch (SQLException e) {}
			      }

		}
		public boolean checkIfUserNameExists(String anvandarnamn){
			boolean exists = false;
			try{
				connection = getConnection();
				preparedStatement = connection.prepareStatement("SELECT * from person WHERE Användarnamn = ?");
				preparedStatement.setString(1, anvandarnamn);
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()){
					exists = true;
				}
				return exists;
			}
			catch(SQLException sqle){
				System.out.println(sqle.getMessage());
				return exists;
			}
	        finally {
			      if (connection != null) 
			        try {connection.close();} catch (SQLException e) {}
			      }

		}

		  
		 
}

