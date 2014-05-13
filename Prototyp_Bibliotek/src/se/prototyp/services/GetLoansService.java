package se.prototyp.services;

import javax.naming.*;
import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;

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
			public ArrayList<String> getLoans(){
				ArrayList<String> list = new ArrayList<String>();
				String loanLine;
				try{
					//SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate
					//FROM Orders
					//INNER JOIN Customers
					//ON Orders.CustomerID=Customers.CustomerID;
					//preparedStatement = dbConnect.connection.prepareStatement("SELECT UserName FROM user_lantagare WHERE ID IN (SELECT User FROM loans)");
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT user_lantagare.UserName, litterature.Titel FROM loans INNER JOIN user_lantagare ON user_lantagare.ID = loans.User INNER JOIN litterature ON litterature.ID = loans.Litterature");
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						loanLine = "";
						loanLine = loanLine + "Låntagare: " + resultSet.getString(1);
						loanLine = loanLine + "| Lånat verk: " + resultSet.getString(2);
						list.add(loanLine);
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


