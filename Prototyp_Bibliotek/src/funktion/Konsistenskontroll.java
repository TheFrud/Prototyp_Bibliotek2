package funktion;

import javax.naming.*;
import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class Konsistenskontroll{

	private DataSource ds;
	private DataSource ds2;
	Connection connection = null;
	Connection connection2 = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
		
    public Konsistenskontroll(){
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
		  
	    // Kollar om personen finns i persondatabasen p� skolan. Notera att connection2 anv�nds.
		public boolean hittaAnvandareOchRollSkolDB(String anvandarnamn, String losenord){
			try{
	    		// Returnera 1 om personen �r b�de student och anst�lld
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
		
		// Kollar om det l�senord personen skrivit in st�mmer �verrens med det redan lagrade i persondatabasen. Notera att connection2 anv�nds.
		public boolean hittaAnvandareSkolDB(String personnummer, String losenord){
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
	    		// Vi tittar om anv�ndarnamn och l�senord matchar det i databasen.
	    		connection = getConnection();
	    		preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE Användarnamn = ? AND Lösenord = ?");
	    		preparedStatement.setString(1, anvandarnamn);
	    		preparedStatement.setString(2, losenord);
	    		resultSet = preparedStatement.executeQuery();
	    		// Om rader har returnerats s� s�tter vi variablen till sant.
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
		public boolean hittaLosenord(String losenord){
			boolean exists = false;
			try{
				// Vi tittar om l�senordet finns i databasen.
				connection = getConnection();
				preparedStatement = connection.prepareStatement("SELECT * from person WHERE Lösenord = ?");
				preparedStatement.setString(1, losenord);
				resultSet = preparedStatement.executeQuery();
				// Om rader har returnerats s� s�tter vi variablen till sant.
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
		public boolean hittaAnvandarnamn(String anvandarnamn){
			boolean exists = false;
			try{
				// Vi tittar om anv�ndarnamnet finns i databasen.
				connection = getConnection();
				preparedStatement = connection.prepareStatement("SELECT * from person WHERE Användarnamn = ?");
				preparedStatement.setString(1, anvandarnamn);
				resultSet = preparedStatement.executeQuery();
				// Om rader har returnerats s� s�tter vi variabeln till sant.
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
		public boolean hittaDokument(String isbn){
			boolean exists = false;
			try{
				// Vi tittar om ett dokument redan existerar i databasen.
				connection = getConnection();
				preparedStatement = connection.prepareStatement("SELECT * from dokument WHERE ISBN = ?");
				preparedStatement.setString(1, isbn);
				resultSet = preparedStatement.executeQuery();
				// Om rader har returnerats s� s�tter vi variablen till sant.
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

