
public class UserUnit {
	private static String username;
	private static String password;
	private static String firstname;
	private static String lastname;
	
	public UserUnit(String username, String password, String firstname, String lastname) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFullName() {
		return firstname + " " + lastname;
	}
	public void setNewPassword(String newPass) {
		password = newPass;
	}
}
