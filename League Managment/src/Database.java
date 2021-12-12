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
	public UserUnit getUserPassword (String Username) {
		String query = "SELECT Username, FirstName, LastName, Password FROM Users WHERE Username = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(query);
			stmt.setString(1, Username);
			ResultSet results = stmt.executeQuery();
			UserUnit resultUser = new UserUnit("X","","errorUser","errorUser");
			while(results.next()) {
				resultUser = new UserUnit(results.getString("Username"), 
						results.getString("Password"), 
						results.getString("FirstName"), 
						results.getString("LastName"));
			}
			return resultUser;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void setNewPassword (String Username, String newPass) {
		String insert = "UPDATE Users SET Password = ? WHERE username = ?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(insert);
			stmt.setString(1, Username);
			stmt.setString(2, newPass);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean userInLeauge(String Username, LeagueUnit League) {
		String query = "SELECT Username FROM Athletes_League WHERE Username = ? AND ID = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(query);
			stmt.setString(1, Username);
			stmt.setInt(2, League.getID());
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void deleteLeague(int leagueID) {
		// delete the league
		String insert = "DELETE FROM Leagues WHERE ID = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(insert);
			stmt.setInt(1, leagueID);
			stmt.executeUpdate();
			// Add the Catain of the team to the New Team.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// delete all teams in the league
		insert = "DELETE FROM Teams WHERE LeagueID = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(insert);
			stmt.setInt(1, leagueID);
			stmt.executeUpdate();
			// Add the Catain of the team to the New Team.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteTeam(int teamID) {
		// delete the Team
		String insert = "DELETE FROM Teams WHERE ID = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(insert);
			stmt.setInt(1, teamID);
			stmt.executeUpdate();
			// Add the Catain of the team to the New Team.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteUser(String username) {
		String insert = "DELETE FROM Users WHERE Username = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(insert);
			stmt.setString(1, username);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addPlayerToLeague( int leagueID, String username) {
		String insert = "INSERT INTO Athletes_League VALUES(?,?);";
		try {
			PreparedStatement stmt = connection.prepareStatement(insert);
			stmt.setInt(1, leagueID);
			stmt.setString(2, username);
			stmt.executeUpdate();
			// Add the Catain of the team to the New Team.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public void addPlayeToTeam( int teamID, String username) {
		String insert = "INSERT INTO Athletes_Team VALUES(?,?);";
		try {
			PreparedStatement stmt = connection.prepareStatement(insert);
			stmt.setInt(1, teamID);
			stmt.setString(2, username);
			stmt.executeUpdate();
			// Add the Catain of the team to the New Team.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
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
 	
 	public int getTeamID(String teamName) {
		String query = "SELECT ID FROM Teams WHERE NAME = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, teamName);
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
	
	public LinkedList<TeamUnit> getAllTeamsInLeague(int leagueID){
		LinkedList<TeamUnit> resultList = new LinkedList<TeamUnit>();
		String query = "SELECT ID, LeagueID, Name, Captain, Wins, Loses FROM Teams WHERE LeagueID = ? ORDER BY Wins DESC";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, leagueID);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				// Add new League to the list.
				resultList.add(new TeamUnit(results.getInt("ID"), results.getInt("LeagueID"), results.getString("Name"), results.getString("Captain"), results.getInt("Wins"), results.getInt("Loses")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}
	
	public LinkedList<LeagueUnit> getMyLeagues(String Username){
		LinkedList<LeagueUnit> resultList = new LinkedList<LeagueUnit>();
		String query = "SELECT Athletes_League.Username AS Username, LID, LName, SName FROM Athletes_League JOIN (SELECT Leagues.ID AS LID, Leagues.Name AS LName, Sport.Name AS SName FROM Leagues JOIN Sport ON Leagues.SportType = Sport.ID)AS SUB ON Athletes_League.ID = SUB.LID WHERE Athletes_League.Username = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(query);
			stmt.setString(1, Username);
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
	
	public LinkedList<TeamUnit> getMyTeams(String Username){
		LinkedList<TeamUnit> resultList = new LinkedList<TeamUnit>();
		String query = "SELECT Teams.ID AS TID, Teams.Name AS TName, Teams.LeagueID AS LID, Teams.Captain AS Captain, Teams.Wins AS W, Teams.Loses AS L From Athletes_Team JOIN Teams ON Athletes_Team.ID = Teams.ID WHERE Athletes_Team.Username = ? ORDER BY Teams.Name";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(query);
			stmt.setString(1, Username);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				// Add new League to the list.
				resultList.add(new TeamUnit(results.getInt("TID"), results.getInt("LID"), results.getString("TName"), results.getString("Captain"), results.getInt("W"), results.getInt("L")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}

	public LinkedList<String> getAllUserOnTeam(int teamID){
		LinkedList<String> resultList = new LinkedList<String>();
		String query = "SELECT Users.Username AS Username, Users.Password AS Password,Users.FirstName AS FirstName, Users.LastName AS LastName FROM Users JOIN Athletes_Team ON Users.Username = Athletes_Team.Username Where Athletes_Team.ID = ? ORDER BY LastName";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, teamID);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				// Add new League to the list.
				resultList.add(results.getString("Username") + " ----- " +results.getString("FirstName") + " " +results.getString("LastName"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}
	
 	public boolean userExist(String username) {
		String query = "SELECT Username From Users WHERE Username = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, username);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
 	}
 	
 	public boolean TeamNameTaken(String name, LeagueUnit league) {
		String query = "SELECT * From Teams WHERE Name IN (?) AND LeagueID = ?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setInt(2, league.getID());
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
 	}
	
}
