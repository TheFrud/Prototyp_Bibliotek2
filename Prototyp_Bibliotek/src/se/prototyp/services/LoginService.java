package se.prototyp.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LoginService{
	private DataSource ds;
	private DataSource ds2;
	Connection connection;
	Connection connection2;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
	public LoginService(){
		  try {
		    // Look up the JNDI data source only once at init time
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
		    return ds2.getConnection();
	 }

	public boolean addUserFromExistingDB(String anvandarnamnIn, String losenordIn){
		ArrayList<String> dataFranPersondatabas = new ArrayList<String>();
		try{
			// Hämtar data om personen från persondatabasen.
			connection2 = getConnection2();
			preparedStatement = connection2.prepareStatement("SELECT * FROM person WHERE Användarnamn = ? AND Lösenord = ?");
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
			connection = getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO person (Personnummer, Användarnamn, Lösenord, Förnamn, Efternamn, Gatuadress, Stad, Postnummer, Telefon, Epost) VALUES (?,?,?,?,?,?,?,?,?,?)");
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
			return false;
			
		}
		catch(SQLException se){
			System.out.println(se.getMessage());
			// Om operationen inte lyckades returnerar vi falskt
			return false;
		}
        finally {
		      if (connection2 != null) 
		        try {connection2.close();} catch (SQLException e) {}
		      }
	}
	
	
}
