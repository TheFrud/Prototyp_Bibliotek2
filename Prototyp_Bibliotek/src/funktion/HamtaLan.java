package funktion;

import javax.naming.*;
import javax.sql.DataSource;

import modell.Lan;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HamtaLan{

	private DataSource ds;
	Connection connection;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
	  public HamtaLan(){
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
			public ArrayList<Lan> hamtaLan(String personnummer){
				ArrayList<Lan> list = new ArrayList<Lan>();
				HamtaAnvandare hamtaAnvandare = new HamtaAnvandare();
				HamtaDokument hamtaDokument = new HamtaDokument();
				try{
					// H�mta alla l�n med hj�lp av personnummer.
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * FROM lån WHERE Person_Personnummer = ?");
					preparedStatement.setString(1, personnummer);
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						Calendar calendarStartdatum = new GregorianCalendar();
						Calendar calendarSlutdatum = new GregorianCalendar();		
						
						// H�mta start och slut-datum och l�gg in i str�ngar.
						String date1String = resultSet.getString(2);
						String date2String = resultSet.getString(3);

						// Splitta upp str�ngarna med delimiter (-) . Exempel: 2013-04-05 > "2013" "04" "05"
						String[] dateDelar = date1String.split("-");
						calendarStartdatum.set(Integer.parseInt(dateDelar[0]), Integer.parseInt(dateDelar[1]), Integer.parseInt(dateDelar[2]));
						dateDelar = date2String.split("-");
						calendarSlutdatum.set(Integer.parseInt(dateDelar[0]), Integer.parseInt(dateDelar[1]), Integer.parseInt(dateDelar[2]));
						
						// Skapa l�nobjekt och l�gg i lista.
						list.add(new Lan(resultSet.getString(1), calendarStartdatum , calendarSlutdatum , hamtaDokument.hamtaDokumentMedId(resultSet.getString(4)), hamtaAnvandare.hamtaAnvandare(personnummer)));
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
			public ArrayList<Lan> hamtaLan(){
				ArrayList<Lan> list = new ArrayList<Lan>();
				HamtaAnvandare hamtaAnvandare = new HamtaAnvandare();
				HamtaDokument hamtaDokument = new HamtaDokument();
				try{
					// H�mta alla l�n.
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * FROM lån");
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						Calendar calendarStartdatum = new GregorianCalendar();
						Calendar calendarSlutdatum = new GregorianCalendar();		
						
						// H�mta start och slut-datum och l�gg in i str�ngar.
						String date1String = resultSet.getString(2);
						String date2String = resultSet.getString(3);

						// Splitta upp str�ngarna med delimiter (-) . Exempel: 2013-04-05 > "2013" "04" "05"
						String[] dateDelar = date1String.split("-");
						calendarStartdatum.set(Integer.parseInt(dateDelar[0]), Integer.parseInt(dateDelar[1]), Integer.parseInt(dateDelar[2]));
						dateDelar = date2String.split("-");
						calendarSlutdatum.set(Integer.parseInt(dateDelar[0]), Integer.parseInt(dateDelar[1]), Integer.parseInt(dateDelar[2]));
						
						// Skapa l�nobjekt och l�gg i lista.
						list.add(new Lan(resultSet.getString(1), calendarStartdatum , calendarSlutdatum , hamtaDokument.hamtaDokumentMedId(resultSet.getString(4)), hamtaAnvandare.hamtaAnvandare(resultSet.getString(5))));
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


