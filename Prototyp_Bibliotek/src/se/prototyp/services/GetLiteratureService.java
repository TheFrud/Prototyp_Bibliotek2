package se.prototyp.services;

import javax.naming.*;
import javax.sql.DataSource;

import model.Dokument;
import model.Lager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

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
		  
			public ArrayList<Dokument> getTitles(){
				ArrayList<Dokument> list = new ArrayList<Dokument>();
				try{
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from dokument");
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						list.add(new Dokument(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)
								, resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)
								, resultSet.getString(8), resultSet.getString(9)));
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
			public Dokument hamtaDokument(String isbn){
				Dokument dokument = new Dokument();
				try{
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from dokument WHERE isbn = ?");
					preparedStatement.setString(1, isbn);
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						dokument = new Dokument(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)
								, resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)
								, resultSet.getString(8), resultSet.getString(9));
					}
					return dokument;
				}
				catch(SQLException sqle){
					System.out.println(sqle.getMessage());
					return dokument;
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
			
			public ArrayList<Dokument> getTitles(String title){
				ArrayList<Dokument> list = new ArrayList<Dokument>();
				try{
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from dokument WHERE Titel = ?");
					preparedStatement.setString(1, title);
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						list.add(new Dokument(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)
								, resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)
								, resultSet.getString(8), resultSet.getString(9)));
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
					preparedStatement = connection.prepareStatement("SELECT * from dokument");
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
			public ArrayList<Lager> hamtaLager(String isbn){
				ArrayList<Lager> list = new ArrayList<Lager>();
				GetLiteratureService gts = new GetLiteratureService();
				try{
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from lager WHERE ISBN = ?");
					preparedStatement.setString(1, isbn);
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						list.add(new Lager(resultSet.getInt(1), gts.hamtaDokument(isbn), resultSet.getString(3), resultSet.getString(5), resultSet.getByte(4), resultSet.getString(6) ));
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
			public ArrayList<Lager> hamtaSenasteLagerdokument(){
				ArrayList<Lager> list = new ArrayList<Lager>();
				GetLiteratureService gts = new GetLiteratureService();
				try{
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from lager");
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						list.add(new Lager(resultSet.getInt(1), gts.hamtaDokument(resultSet.getString(5)), resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4), resultSet.getString(6)));
					}
					Collections.sort(list);
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
}

