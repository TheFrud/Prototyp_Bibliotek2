package se.prototyp.services;

import javax.naming.*;
import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class GetLiteratureService{

	private DataSource ds;
	Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
		
	  public GetLiteratureService(){
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
		  
			public ArrayList<String> getTitles(){
				ArrayList<String> list = new ArrayList<String>();
				String bookLine;
				try{
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from litterature");
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						bookLine = "";
						bookLine = bookLine + "ID: " +  String.valueOf(resultSet.getInt(1));
						bookLine = bookLine + "Titel: " + resultSet.getString(2);
						list.add(bookLine);
					}
					return list;
				}

				catch(SQLException sqle){
					System.out.println(sqle.getMessage());
					return list;
				}
				finally{
					if(connection != null){
						try{
							connection.close();
						}
						catch(SQLException sql){
							sql.printStackTrace();
						}
					}


				}
			}
			
			public ArrayList<String> getTitles(String title){
				ArrayList<String> list = new ArrayList<String>();
				String bookLine;
				try{
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from litterature WHERE Titel = ?");
					preparedStatement.setString(1, title);
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						bookLine = "";
						bookLine = bookLine + "ID: " +  String.valueOf(resultSet.getInt(1));
						bookLine = bookLine + "Titel: " + resultSet.getString(2);
						list.add(bookLine);
					}
					return list;
				}
				catch(SQLException sqle){
					System.out.println(sqle.getMessage());
					return list;
				}
				finally{
					if(connection != null){
						try{
							connection.close();
						}
						catch(SQLException sql){
							sql.printStackTrace();
						}
					}

				}

			}
			
			public int getNumberOfTitles(){
				int amount = 0;
				try{
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from litterature");
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						amount++;
					}
					return amount;
				}
				catch(SQLException sqle){
					System.out.println(sqle.getMessage());
					return amount;
				}
				finally{
					if(connection != null){
						try{
							connection.close();
						}
						catch(SQLException sql){
							sql.printStackTrace();
						}
					}


			}

		  
		  
}
}

