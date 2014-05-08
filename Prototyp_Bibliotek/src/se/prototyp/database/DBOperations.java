// ANVÄNDS EJ
// MEN LÅT VARA KVAR

package se.prototyp.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBOperations {

	DBConnection dbConnect = new DBConnection();
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
	public boolean checkUserExistance(String userName, String password){
    	boolean memberExist = false;
    	try{
    		preparedStatement = dbConnect.connection.prepareStatement("SELECT * FROM user WHERE UserName = ? AND Password = ?");
    		preparedStatement.setString(1, userName);
    		preparedStatement.setString(2, password);
    		resultSet = preparedStatement.executeQuery();
    		if(resultSet.next()){
    			memberExist = true;
    		}
    	}
    	catch(SQLException se){
    		System.out.println(se.getMessage());
    	}
    	return memberExist;
    }
	
	public boolean addUser(String userName, String firstName, String familyName, String password){
		boolean userAdded = false;
		try{
			preparedStatement = dbConnect.connection.prepareStatement("INSERT INTO user (UserName, FirstName, SecondName, Password) VALUES(?,?,?,?)");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, familyName);
			preparedStatement.setString(4, password);
			int change = preparedStatement.executeUpdate();
			if(change > 0){
				userAdded = true;
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return userAdded;
	}
	public ArrayList<String> getBooks(){
		ArrayList<String> list = new ArrayList<String>();
		String bookLine;
		try{
			preparedStatement = dbConnect.connection.prepareStatement("SELECT * from litterature");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				bookLine = "";
				bookLine = bookLine + "ID: " +  String.valueOf(resultSet.getInt(1));
				bookLine = bookLine + "Titel: " + resultSet.getString(2);
				list.add(bookLine);
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return list;
	}
	public ArrayList<String> getBooks(String title){
		ArrayList<String> list = new ArrayList<String>();
		String bookLine;
		try{
			preparedStatement = dbConnect.connection.prepareStatement("SELECT * from litterature WHERE Titel = ?");
			preparedStatement.setString(1, title);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				bookLine = "";
				bookLine = bookLine + "ID: " +  String.valueOf(resultSet.getInt(1));
				bookLine = bookLine + "Titel: " + resultSet.getString(2);
				list.add(bookLine);
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return list;
	}
	public int addLiterature(String title){
		int change = 0;
		try{
			preparedStatement = dbConnect.connection.prepareStatement("INSERT INTO litterature (Titel) VALUES (?)");
			preparedStatement.setString(1, title);
			change = preparedStatement.executeUpdate();
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return change;
		
	}
	public int getNumberOfTitles(){
		int amount = 0;
		try{
			preparedStatement = dbConnect.connection.prepareStatement("SELECT * from litterature");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				amount++;
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return amount;
		
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
			preparedStatement = dbConnect.connection.prepareStatement("SELECT user_lantagare.UserName, litterature.Titel FROM loans INNER JOIN user_lantagare ON user_lantagare.ID = loans.User INNER JOIN litterature ON litterature.ID = loans.Litterature");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				loanLine = "";
				loanLine = loanLine + "Låntagare: " + resultSet.getString(1);
				loanLine = loanLine + "| Lånat verk: " + resultSet.getString(2);
				list.add(loanLine);
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		
		return list;
	}

	
}
