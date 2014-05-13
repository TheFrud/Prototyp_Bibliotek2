package se.prototyp.services;

import javax.naming.*;
import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class GetUserInfoService{

	private DataSource ds;
	Connection connection;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
	  public GetUserInfoService(){
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
			public ArrayList<String> getUserInfo(String anvandarnamn, String losenord){
				ArrayList<String> list = new ArrayList<String>();
				try{
					// H�mta all data ifr�n Person-tabellen och spara i en lista
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from Person WHERE Anv�ndarnamn = ? AND L�senord = ?");
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

					
					// H�mta data om personens roll och l�gg i slutet av lista
					preparedStatement = connection.prepareStatement("SELECT Roll FROM Rollinnehav WHERE Personnummer IN (SELECT Personnummer FROM Person WHERE Anv�ndarnamn = ?)");
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



		  
}


