package funktion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import modell.Forslag;
import modell.Reservation;

public class HamtaForslag {

	private DataSource ds;
	Connection connection;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
	  public HamtaForslag(){
		    try {
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
			public ArrayList<Forslag> hamtaForslag(){
				ArrayList<Forslag> list = new ArrayList<Forslag>();
				HamtaAnvandare hamtaAnvandare = new HamtaAnvandare();
				try{
					// Hämta alla förslag.
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * FROM inköpsförslag");
					resultSet = preparedStatement.executeQuery();
					// Skapa förslagobjekt och lägg i lista.
					while(resultSet.next()){
						list.add(new Forslag(hamtaAnvandare.hamtaAnvandare(resultSet.getString(2)), resultSet.getString(3)));
					}
					return list;
				}
				
				catch(SQLException sqle){
					System.out.println(sqle.getMessage());
					return list;
				}
			    finally {
				      if (connection != null) 
				        try {connection.close();} catch (SQLException e) {}
				      }

			}
}
