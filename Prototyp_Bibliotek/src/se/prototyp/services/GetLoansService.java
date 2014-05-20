package se.prototyp.services;

import javax.naming.*;
import javax.sql.DataSource;

import model.Lan;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GetLoansService{

	private DataSource ds;
	Connection connection;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
	  public GetLoansService(){
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
			public ArrayList<Lan> getLoans(String personnummer){
				ArrayList<Lan> list = new ArrayList<Lan>();
				GetUserInfoService getUserInfoService = new GetUserInfoService();
				SimpleDateFormat sdf = new SimpleDateFormat();
				try{
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * FROM lån WHERE Person_Personnummer = ?");
					preparedStatement.setString(1, personnummer);
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						Calendar calendarStartdatum = new GregorianCalendar();
						Calendar calendarSlutdatum = new GregorianCalendar();		
						
						String date1String = resultSet.getString(2);
						String date2String = resultSet.getString(3);

						String[] dateDelar = date1String.split("-");
						calendarStartdatum.set(Integer.parseInt(dateDelar[0]), Integer.parseInt(dateDelar[1]), Integer.parseInt(dateDelar[2]));
						dateDelar = date2String.split("-");
						calendarSlutdatum.set(Integer.parseInt(dateDelar[0]), Integer.parseInt(dateDelar[1]), Integer.parseInt(dateDelar[2]));
						
						
						
						list.add(new Lan(resultSet.getString(1), calendarStartdatum , calendarSlutdatum , resultSet.getString(4), getUserInfoService.hamtaAnvandare(personnummer)));
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


