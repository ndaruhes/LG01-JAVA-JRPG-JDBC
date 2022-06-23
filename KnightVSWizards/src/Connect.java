import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE_NAME = "oop_project";
	
	private final String HOST = "localhost";
	private final String PORT = "3306";
	
	private final String CONNECTION = String.format("jdbc:mysql://%s:%s/%s", 
			HOST,
			PORT,
			DATABASE_NAME);
	
	private Connection con;
	private Statement stmt;
	private static Connect instance = null;
	//Implementasi Design Pattern Singleton
	public static Connect getInstance() {
		if(instance==null) {
			instance = new Connect();
		}
		return instance;
	}
	
	private Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con  = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeQuery(String query) {
		try {
			return stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void executeUpdate(String query) {
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
