import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Database {
/*
 * Set up the database
 * 
 * Connect to the database
 * 
 * Make modification to the database
 * 
 * query data
 * 
 * disconnect for database
 */
	// Database
	private String url = "jdbc:squl:C:\\Users\\bergf\\Database_Final_Project\\LeagueManagementDatabase.db";
	
	private Connection connection;
	
	// Singleton pattern
	private static final Database INSTANCE = new Database();
	
	private Database() {	}
	
	public static Database getInstance() {
		return INSTANCE;
	}
	
	public void connect() throws SQLException {
		connection = DriverManager.getConnection(url);
	}
	
	public void disconnect() throws SQLException {
		connection.close();
	}
	
	public ResultSet runQuery(String query) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet results = stmt.executeQuery();
		
		return results;
	}
	
	
}
