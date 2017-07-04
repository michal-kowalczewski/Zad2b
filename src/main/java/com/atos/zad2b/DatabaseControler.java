package com.atos.zad2b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseControler {
	String url = "jdbc:sqlserver://localhost;databaseName=zad2b;integratedSecurity=true";
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	int updateSet;

	public List<User> getAll(){
		List<User> usersList = new ArrayList<User>();
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url);

			String query = "SELECT * from Users";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				usersList.add(user);
			}
		}
		catch (Exception e) {  
			e.printStackTrace();  
		}  
		finally {  
			if (rs != null) try { rs.close(); } catch(Exception e) {}  
			if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
			if (con != null) try { con.close(); } catch(Exception e) {}  
		} 
		return usersList;
	}

	public void insertUser(User user){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url);

			String query = "Insert into Users(UserName, UserPassword, UserDescription) "
					+ "VALUES ('" + user.getUserName() + "', '" + user.getUserPassword() + "', '" + user.getUserDescription() + "');";
			stmt = con.createStatement();
			updateSet = stmt.executeUpdate(query);
		}
		catch (Exception e) {  
			e.printStackTrace();  
		}
		finally {  
			if (rs != null) try { rs.close(); } catch(Exception e) {}  
			if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
			if (con != null) try { con.close(); } catch(Exception e) {}  
		} 
	}
}
