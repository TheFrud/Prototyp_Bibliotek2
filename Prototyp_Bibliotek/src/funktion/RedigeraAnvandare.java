package funktion;

import javax.naming.*;
import javax.sql.DataSource;

import java.sql.*;

public class RedigeraAnvandare{

	private DataSource ds;
	Connection connection;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
	  public RedigeraAnvandare(){
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
		  
			public boolean redigeraAnvandare(String personnummer, String anvandarnamn, String losenord, String fornamn, String efternamn, 
					String gatuadress, String stad, String postnummer, String telefon, String epost){
				boolean edited = false;
				try{
					connection = getConnection();
					preparedStatement = connection.prepareStatement("UPDATE person SET Användarnamn = ?, Lösenord = ?, Förnamn = ?, Efternamn = ?, Gatuadress = ?, Stad = ?, Postnummer = ?, Telefon = ?, Epost = ? WHERE Personnummer = ?");
					preparedStatement.setString(1, anvandarnamn);
					preparedStatement.setString(2, losenord);
					preparedStatement.setString(3, fornamn);
					preparedStatement.setString(4, efternamn);
					preparedStatement.setString(5, gatuadress);
					preparedStatement.setString(6, stad);
					preparedStatement.setString(7, postnummer);
					preparedStatement.setString(8, telefon);
					preparedStatement.setString(9, epost);
					preparedStatement.setString(10, personnummer);
					int numOfRowsEdited = preparedStatement.executeUpdate();
					if(numOfRowsEdited>0){
						edited = true;
					}
					return edited;
				}
				catch(SQLException sqle){
					System.out.println(sqle.getMessage());
					return edited;
				}
			    finally {
				      if (connection != null) 
				        try {connection.close();} catch (SQLException e) {}
				      }


			}


		   
		  

		  
}


