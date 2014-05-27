package funktion;

import javax.naming.*;
import javax.sql.DataSource;

import modell.Anvandare;
import modell.Roll;

import java.sql.*;
import java.util.ArrayList;

public class HamtaAnvandare{

	private DataSource ds;
	Connection connection;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
	  public HamtaAnvandare(){
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
			public ArrayList<String> hamtaAnvandarInfo(String anvandarnamn, String losenord){
				ArrayList<String> list = new ArrayList<String>();
				try{
					// Hämta data ifrån person-tabellen och spara i en lista
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from person WHERE Användarnamn = ? AND Lösenord = ?");
					preparedStatement.setString(1, anvandarnamn);
					preparedStatement.setString(2, losenord);
					resultSet = preparedStatement.executeQuery();
					
					while(resultSet.next()){
						list.add(resultSet.getString(1));
						list.add(resultSet.getString(2));
						list.add(resultSet.getString(3));
						list.add(resultSet.getString(4));
						list.add(resultSet.getString(5));
						list.add(resultSet.getString(6));
						list.add(resultSet.getString(7));
						list.add(resultSet.getString(8));
						list.add(resultSet.getString(9));
						list.add(resultSet.getString(10));
					}

					
					// Hämta data om personens roll och lägg i slutet av listan
					preparedStatement = connection.prepareStatement("SELECT Roll FROM rollinnehav WHERE Personnummer IN (SELECT Personnummer FROM person WHERE Användarnamn = ?)");
					preparedStatement.setString(1, anvandarnamn);
					resultSet = preparedStatement.executeQuery();
					if(resultSet.next()){
						list.add(resultSet.getString(1));
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
			public Anvandare hamtaAnvandare(String personnummer){
				Anvandare anvandare = new Anvandare();
				try{
					// H�mta data ifr�n Person-tabellen
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from person WHERE Personnummer = ?");
					preparedStatement.setString(1, personnummer);
					resultSet = preparedStatement.executeQuery();
					
					// Skapa ett nytt objekt av typen Anvandare med hj�lp av datan.
					while(resultSet.next()){
						anvandare = new Anvandare(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3) 
								, resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)
								,resultSet.getString(8), resultSet.getString(9), resultSet.getString(10));
					}
					// H�mta personens roll.
					preparedStatement = connection.prepareStatement("SELECT * FROM roll WHERE Roll IN (SELECT Roll FROM rollinnehav WHERE Personnummer = ?)");
					preparedStatement.setString(1, personnummer);
					resultSet = preparedStatement.executeQuery();
					// Skapa ett nytt objekt av typen Roll med hj�lp av datan.
					if(resultSet.next()){
						anvandare.setRoll(new Roll(resultSet.getString(1), resultSet.getString(2)));
					}
					return anvandare;
				}
				catch(SQLException sqle){
					System.out.println(sqle.getMessage());
					return anvandare;
				}
			    finally {
				      if (connection != null) 
				        try {connection.close();} catch (SQLException e) {}
				      }

			}
			public String hamtaRoll(String personnummer){
				String roll = "";
				try{
					// H�mta data om en persons roll.
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT Roll from rollinnehav WHERE Personnummer = ?");
					preparedStatement.setString(1, personnummer);
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						roll = resultSet.getString(1);
					}
					// Kolla vilken roll personen har. Beroende p� svaret s� skickar vi tillbaka 
					// data till kontrollern om vilket gr�nssnitt som anv�ndaren borde skickas till.
					if(roll.equals("L�ntagare")){
						return "lantagare.jsp";
					}
					if(roll.equals("Bibliotekarie")){
						return "bibliotekarie.jsp";
					}
					if(roll.equals("Administrat�r")){
						return "administrator.jsp";
					}
					return roll;
				}
				catch(SQLException sqle){
					System.out.println(sqle.getMessage());
					return roll;
				}
			    finally {
				      if (connection != null) 
				        try {connection.close();} catch (SQLException e) {}
				      }

			}



		  
}


