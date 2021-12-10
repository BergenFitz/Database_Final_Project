
public class TeamUnit {
	private int id;
	private int leagueID;
	private String name;
	private String captain;
	private int wins;
	private int loses;
	
	public TeamUnit(int id, int leagueID, String name, String captain, int wins, int loses){
		this.id = id;
		this.leagueID = leagueID;
		this.name = name;
		this.captain = captain;
		this.wins = wins;
		this.loses = loses;
	}
	
	public String toString() {
		return name + ": Wins - " + String.valueOf(wins) + " Loses - " + String.valueOf(loses);
	}
	public int getID() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getLeagueID() {
		return leagueID;
	}
	public String getCaptain() {
		return captain;
	}
	public int getWins() {
		return wins;
	}
	public int getLoses() {
		return loses;
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
