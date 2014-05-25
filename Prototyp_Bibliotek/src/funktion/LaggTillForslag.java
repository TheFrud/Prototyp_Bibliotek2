package funktion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LaggTillForslag {

	private DataSource ds;
	Connection connection;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
	  public LaggTillForslag(){
		    try {
		      // Look up the JNDI data source only once at init time
		      Context ctx = new InitialContext();
			  ds = (DataSource) ctx.lookup("java:comp/env/jdbc/prototyp_bibliotek");
		    }
		    catch (NamingException e) {
		      e.printStackTrace();
		    }
		  }
	  
		  private Connection getConnection() throws SQLException {
		    return ds.getConnection();
		  }
		  
		  public boolean laggTillForslag (String personnummer, String forslag) {
			  int change = 0;
			  try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement("INSERT INTO ink�psf�rslag VALUES (null, ?, ?)");
				preparedStatement.setString(1, personnummer);
				preparedStatement.setString(2, forslag);
				change = preparedStatement.executeUpdate();
				if(change > 0){
					return true;
				}
				return false;
			}
		    catch (SQLException sqlException) {
		      sqlException.printStackTrace();
				return false;
		    }

		    finally {
		      if (connection != null) 
		        try {connection.close();} catch (SQLException e) {}
		      }
		    }
}
