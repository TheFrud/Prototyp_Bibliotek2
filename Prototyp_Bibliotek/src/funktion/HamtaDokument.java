package funktion;

import javax.naming.*;
import javax.sql.DataSource;

import modell.Dokument;
import modell.Lager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class HamtaDokument{

	private DataSource ds;
	Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
		
	  public HamtaDokument(){
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
		  
			public ArrayList<Dokument> hamtaDokument(){
				ArrayList<Dokument> list = new ArrayList<Dokument>();
				try{
					// Hämta alla dokument och lägg i dokumentlista.
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from dokument");
					resultSet = preparedStatement.executeQuery();
					// Dokumentobjekt skapas och läggs i lista.
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
			public Dokument hamtaDokumentMedIsbn(String isbn){
				Dokument dokument = new Dokument();
				try{
					// Hämta dokument med hjälp av isbn
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from dokument WHERE isbn = ?");
					preparedStatement.setString(1, isbn);
					resultSet = preparedStatement.executeQuery();
					// Dokumentobjekt skapas.
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
			
			public Dokument hamtaDokumentMedId(String id){
				Dokument dokument = new Dokument();
				try{
					// Hämta dokument med hjälp av id
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from dokument WHERE isbn IN (SELECT ISBN FROM lager WHERE DokumentID = ?)");
					preparedStatement.setString(1, id);
					resultSet = preparedStatement.executeQuery();
					// Dokumentobjekt skapas.
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

			public ArrayList<Dokument> hamtaDokument(String title){
				ArrayList<Dokument> list = new ArrayList<Dokument>();
				try{
					// Hämta dokument med en viss titel.
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from dokument WHERE Titel = ?");
					preparedStatement.setString(1, title);
					resultSet = preparedStatement.executeQuery();
					// Skapa dokumentobjekt och lägg i lista
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
			
			public int hamtaAntalTitlar(){
				int amount = 0;
				try{
					// Hämta alla dokument.
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from dokument");
					resultSet = preparedStatement.executeQuery();
					// öka mängden med "1" för varje returnerad rad.
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
				HamtaDokument gts = new HamtaDokument();
				try{
					// Hämta alla lagerobjekt med ett visst isbn
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from lager WHERE ISBN = ?");
					preparedStatement.setString(1, isbn);
					resultSet = preparedStatement.executeQuery();
					// Skapa lagerobjekt och lägg i lista.
					while(resultSet.next()){
						list.add(new Lager(resultSet.getInt(1), gts.hamtaDokumentMedIsbn(isbn), resultSet.getString(3), resultSet.getString(5), resultSet.getByte(4), resultSet.getString(6) ));
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
				HamtaDokument gts = new HamtaDokument();
				try{
					// Hämta alla dokument i lager.
					connection = getConnection();
					preparedStatement = connection.prepareStatement("SELECT * from lager");
					resultSet = preparedStatement.executeQuery();
					// Skapa lagerobjekt och lägg i lista.
					while(resultSet.next()){
						list.add(new Lager(resultSet.getInt(1), gts.hamtaDokumentMedIsbn(resultSet.getString(5)), resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4), resultSet.getString(6)));
					}
					// Sortera lista efter startdatum. >>> Se implementerat comparableinterface i lagerklassen!
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

