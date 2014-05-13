package se.prototyp.services;

import javax.naming.*;
import javax.sql.DataSource;

import java.sql.*;

public class EditUserService{

	private DataSource ds;
	Connection connection;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
	  public EditUserService(){
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
		  
			public boolean editUser(int id, String userName, String firstName, String familyName, String password){
				boolean edited = false;
				try{
					connection = getConnection();
					preparedStatement = connection.prepareStatement("UPDATE user SET UserName = ?, FirstName = ?, SecondName = ?, Password = ? WHERE ID = ?");
					preparedStatement.setString(1, userName);
					preparedStatement.setString(2, firstName);
					preparedStatement.setString(3, familyName);
					preparedStatement.setString(4, password);
					preparedStatement.setInt(5,id);
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


