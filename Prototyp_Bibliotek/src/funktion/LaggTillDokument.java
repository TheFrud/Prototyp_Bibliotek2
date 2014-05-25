package funktion;

import javax.naming.*;
import javax.sql.DataSource;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LaggTillDokument{

	private DataSource ds;
	Connection connection;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
	  public LaggTillDokument(){
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
		  
		  public int laggTillDokument (String isbn, String titel, String upplaga, String forfattare, String forlag, String sidantal, 
				  String sprak, String bindningstyp, String nyckelord, String beskrivning, String dokumenttyp, String hylla, String hyllplan) {
			  Calendar c = new GregorianCalendar();
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
			  Konsistenskontroll konsistensKontroll = new Konsistenskontroll();
			  int change = 0;
			  int change2 = 0;
			  try {
				  // Vi tittar om dokumentet redan existerar i Dokumenttabellen.
				if(!konsistensKontroll.hittaDokument(isbn)){
					// Om inte så försöker vi lägga till dokumentet.
					connection = getConnection();
					preparedStatement = connection.prepareStatement("INSERT INTO dokument (ISBN, Titel, Upplaga, Författare, Förlag, Sidantal, Språk, Bindningstyp, Nyckelord, Beskrivning, Dokumenttyp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					preparedStatement.setString(1, isbn);
					preparedStatement.setString(2, titel);
					preparedStatement.setString(3, upplaga);
					preparedStatement.setString(4, forfattare);
					preparedStatement.setString(5, forlag);
					preparedStatement.setString(6, sidantal);
					preparedStatement.setString(7, sprak);
					preparedStatement.setString(8, bindningstyp);
					preparedStatement.setString(9, nyckelord);
					preparedStatement.setString(10, beskrivning);
					preparedStatement.setString(11, dokumenttyp);
					
					change2 = preparedStatement.executeUpdate();
					if(change2 < 1){
						return 0;
					}
				}
				
				// Vi försöker lägga till dokumentet i lagret.
				connection = getConnection();
				preparedStatement = connection.prepareStatement("INSERT INTO lager (Hylla, Hyllplan, Tillgänglig, ISBN, Tillagd) VALUES (? ,?, ?, ?, ?)");
				preparedStatement.setString(1, hylla);
				preparedStatement.setString(2, hyllplan);
				preparedStatement.setInt(3, 1);
				preparedStatement.setString(4, isbn);
				preparedStatement.setString(5, sdf.format(c.getTime()));
				
				// Har rader lagts till i databasen?
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


