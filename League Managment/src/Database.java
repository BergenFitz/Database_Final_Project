import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
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
	private String url = "jdbc:sqlite:C:\\Users\\bergf\\Database_Final_Project\\LeagueManagementDatabase.db";
	
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
	
	/**
	 * Below is all the queries and delete and updates to the Database
	 * @throws SQLException 
	 */
	public String getUserPassword (String Username) {
		String query = "SELECT Password FROM Users WHERE Username = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(query);
			stmt.setString(1, Username);
			ResultSet results = stmt.executeQuery();
			String resultPassword = "";
			while(results.next()) {
				resultPassword = results.getString("Password");
			}
			return resultPassword;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Invalid Username has been Detected";
		}
	}
	
	public void setNewPassword (String Username, String newPass) {
		
		
	}
	
	public boolean userInLeauge(String Username, LeagueUnit League) {
		String query = "";
		
		return false;
	}
	
	public boolean createNewUser(String Username, String Password, String FirstName, String LastName) {
		String insert = "INSERT INTO Users(Username, Password, FirstName, LastName) VALUES(?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(insert);
			stmt.setString(1, Username);
			stmt.setString(2, Password);
			stmt.setString(3, FirstName);
			stmt.setString(4, LastName);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean createNewTeam(int LeagueID, String Name, String CaptainName) {
		String insert = "INSERT INTO Teams(LeagueID, Name, Captain, Wins, Loses) VALUES(?, ?, ?, 0, 0)";
		try {
			PreparedStatement stmt = connection.prepareStatement(insert);
			stmt.setInt(1, LeagueID);
			stmt.setString(2, Name);
			stmt.setString(3, CaptainName);
			stmt.executeUpdate();
			// Add the Catain of the team to the New Team.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public int getSportID(String sport) {
		String query = "SELECT ID FROM Sport WHERE NAME = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, sport);
			ResultSet results = stmt.executeQuery();
			int sportID = -1;
			while(results.next()) {
				sportID = results.getInt("ID");
			}
			return sportID;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public boolean createNewLeague(String Name, String Sport) {
		String insert = "INSERT INTO Leagues(Name SportType) VALUES(?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(insert);
			stmt.setString(1, Name);
			stmt.setInt(2, getSportID(Sport));
			stmt.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public LinkedList<LeagueUnit> getAllLeagues(){
		LinkedList<LeagueUnit> resultList = new LinkedList<LeagueUnit>();
		String query = "SELECT Leagues.ID AS LID, Leagues.Name AS LName, Sport.Name AS SName FROM Leagues JOIN Sport ON Leagues.SportType = Sport.ID";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(query);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				// Add new League to the list.
				resultList.add(new LeagueUnit(results.getInt("LID"), results.getString("LName"), results.getString("SName")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}
}
