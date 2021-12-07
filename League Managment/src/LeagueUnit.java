
public class LeagueUnit {
	private int id;
	private String name;
	private String sport;
	
	public LeagueUnit(int id, String name, String sport) {
		this.id = id;
		this.name = name;
		this.sport = sport;
	}
	
	public String toString() {
		return String.valueOf(id) + " " + name + " " + sport;
	}
	public String getName() {
		return name;
	}
	public String getSport() {
		return sport;
	}
	public int getID() {
		return id;
	}
	// used to test
	public static void main(String[] args) {
		LeagueUnit test = new LeagueUnit(123, "Fitzgerald's Bar", "Softball");
		System.out.println(test.toString());

	}

}
