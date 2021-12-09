import java.sql.SQLException;
// This Class is like the exe file for the program.
public class LeagueManagement {

	public static void main(String[] args) {
		Database db = Database.getInstance();
		
		try {
			db.connect();
			// Successful connection to database.
			System.out.println("Connected!!");
		} catch (SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
		}
		
		
		
		
		
		try {
			db.disconnect();
			// Successful Disconnection 
			System.out.println("Disconnected");
		} catch (SQLException e) {
			System.out.println("Couldn't Disconnect from Database....");
			e.printStackTrace();
		}

	}

}
