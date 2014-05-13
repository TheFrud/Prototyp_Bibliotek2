package se.prototyp.services;

import javax.naming.*;
import javax.sql.DataSource;

import java.sql.*;

public class AddLiteratureService{

	private DataSource ds;
	Connection connection;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
	  public AddLiteratureService(){
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
		  
		  public int addLiterature (String title) {
			  int change = 0;
			  try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement("INSERT INTO litterature (Titel) VALUES (?)");
				preparedStatement.setString(1, title);
				change = preparedStatement.executeUpdate();
				return change;
			}
		    catch (SQLException sqlException) {
		      sqlException.printStackTrace();
				return change;
		    }

		    finally {
		      if (connection != null) 
		        try {connection.close();} catch (SQLException e) {}
		      }
		    }
		  

		  
}


