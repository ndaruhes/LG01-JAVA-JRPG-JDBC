import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

//Berisikan method-method untuk insert, view,
//dan update, semua method ditaruh disebuah class
//tersendiri untuk mengimplementasi Design Pattern
//Facade method juga

public class DatabaseHelper {

	Connect con = Connect.getInstance();
	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
	ArrayList<Country> countries = new ArrayList<Country>();
	ArrayList<User> users = new ArrayList<User>();
	public DatabaseHelper() {
	}

	
	public void insertUser(User u) {
		String query = String.format("INSERT INTO user VALUES" +
				 "('%s','%s','%s','%s','%s','%d','%d','%d')",
				 u.getUserID(),u.getUsername(),u.getPassword(),
				 u.getClassName(),u.getCountryID(),u.getLevel(),
				 u.getExp(),u.getScore());
		con.executeUpdate(query);
	}
	
	public ArrayList<User> getAllUser(){
		String userID,className,countryID, username, password;
		Integer level,exp,score;
		String query = "SELECT * FROM user ORDER BY Score DESC";
		ResultSet rs = con.executeQuery(query);
		try {
			while(rs.next()) {
				userID = rs.getString("UserID");
				className = rs.getString("ClassName");
				countryID = rs.getString("CountryID");
				username = rs.getString("Username");
				password = rs.getString("Password");
				level = rs.getInt("Level");
				exp = rs.getInt("Exp");
				score = rs.getInt("Score");
				
				User curr = new User(userID,username,password,
									 className,countryID,level,
									 exp,score);
				users.add(curr);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public User getUser(String username, String password) {
		String userID,className,countryID;
		Integer level,exp,score;
		String query = "SELECT * FROM user " +
					   "WHERE Username = '" + username + "' AND Password = '" + password +"'";
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				userID = rs.getString("UserID");
				className = rs.getString("ClassName");
				countryID = rs.getString("CountryID");
				level = rs.getInt("Level");
				exp = rs.getInt("Exp");
				score = rs.getInt("Score");
				
				User curr = new User(userID,username,password,
									 className,countryID,level,
									 exp,score);
				return curr;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Country> getCountry() {
		countries.clear();
		String query = "SELECT * FROM country";
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				String countryID = rs.getString("CountryID");
				String countryName = rs.getString("CountryName");
				
				countries.add(new Country(countryID, countryName));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return countries;
	}
	
	public void updateUser(User u) {
		String query = String.format("UPDATE user SET " +
									 "Exp = '%d', Score = %d, Level = '%d' WHERE " +
									 "Username = '%s'",
									 u.getExp(), u.getScore(), u.getLevel(), u.getUsername());
		con.executeUpdate(query);
	}
//------------------------------------------------------------------------------------------------------

}
