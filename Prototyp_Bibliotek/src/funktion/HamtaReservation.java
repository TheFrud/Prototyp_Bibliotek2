package funktion;

import javax.naming.*;
import javax.sql.DataSource;

import modell.Lan;
import modell.Reservation;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class HamtaReservation{

	private DataSource ds;
	Connection connection;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
	  public HamtaReservation(){
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
			public ArrayList<Reservation> hamtaReservationer(String personnummer){
				ArrayList<Reservation> list = new ArrayList<Reservation>();
				HamtaAnvandare hamtaAnvandare = new HamtaAnvandare();
				SimpleDateFormat sdf = new SimpleDateFormat();
				HamtaDokument gts = new HamtaDokument();
				try{
					// Hämta alla reservationer med hjälp av personnummer.
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * FROM reservation WHERE Person_Personnummer = ?");
					preparedStatement.setString(1, personnummer);
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						Calendar calendarStartdatum = new GregorianCalendar();
						Calendar calendarSlutdatum = new GregorianCalendar();		
						
						// Hämta start och slut-datum och lägg in i strängar.
						String date1String = resultSet.getString(3);
						String date2String = resultSet.getString(4);
						
						// Splitta upp strängarna med delimiter (-) . Exempel: 2013-04-05 > "2013" "04" "05"
						String[] dateDelar = date1String.split("-");
						calendarStartdatum.set(Integer.parseInt(dateDelar[0]), Integer.parseInt(dateDelar[1]), Integer.parseInt(dateDelar[2]));
						dateDelar = date2String.split("-");
						calendarSlutdatum.set(Integer.parseInt(dateDelar[0]), Integer.parseInt(dateDelar[1]), Integer.parseInt(dateDelar[2]));
						
						// Skapa reservationsobjekt och lägg i lista.
						list.add(new Reservation(hamtaAnvandare.hamtaAnvandare(resultSet.getString(1)), gts.hamtaDokumentMedId(resultSet.getString(2)) , calendarStartdatum, calendarSlutdatum));

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
			public ArrayList<Reservation> hamtaReservationer(){
				ArrayList<Reservation> list = new ArrayList<Reservation>();
				HamtaAnvandare hamtaAnvandare = new HamtaAnvandare();
				SimpleDateFormat sdf = new SimpleDateFormat();
				HamtaDokument gts = new HamtaDokument();
				try{
					// Hämta alla reservationer
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * FROM reservation");
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						Calendar calendarStartdatum = new GregorianCalendar();
						Calendar calendarSlutdatum = new GregorianCalendar();		
						
						// Hämta start och slut-datum och lägg in i strängar.
						String date1String = resultSet.getString(3);
						String date2String = resultSet.getString(4);

						// Splitta upp strängarna med delimiter (-) . Exempel: 2013-04-05 > "2013" "04" "05"
						String[] dateDelar = date1String.split("-");
						calendarStartdatum.set(Integer.parseInt(dateDelar[0]), Integer.parseInt(dateDelar[1]), Integer.parseInt(dateDelar[2]));
						dateDelar = date2String.split("-");
						calendarSlutdatum.set(Integer.parseInt(dateDelar[0]), Integer.parseInt(dateDelar[1]), Integer.parseInt(dateDelar[2]));
						
						// Skapa reservationsobjekt och lägg i lista.
						list.add(new Reservation(hamtaAnvandare.hamtaAnvandare(resultSet.getString(1)), gts.hamtaDokumentMedId(resultSet.getString(2)) , calendarStartdatum, calendarSlutdatum));

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


