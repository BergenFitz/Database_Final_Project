import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login {
	public Login(Database db) {
		// Create the greeting screen.
		JFrame frame = new JFrame("Welcome to MyLeague");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create a JPanel object.
		JPanel pane = new JPanel();
		pane.setOpaque(true);
		pane.setLayout(null);
		
		// Create a new JLabel object welcoming the user to the YMCA:
		JLabel greet = new JLabel("<html><i>Welcome to the MyLeague!</i><html>", JLabel.CENTER);
		greet.setSize(300, 30);
		greet.setLocation(175, 150);
		// Add the label to the pane.
		pane.add(greet);
		
		// The member login button.
		JButton memberLogin = new JButton("Member");
		memberLogin.setSize(200, 30);
		memberLogin.setLocation(50, 200);
		// Add the button to the pane.
		pane.add(memberLogin);
		
		// Create new member.
		JButton createMem = new JButton("Create New Member");
		createMem.setSize(200, 30);
		createMem.setLocation(250, 200);
		pane.add(createMem);
				
		// The Guest login button.
		JButton guestLogin = new JButton("Guest");
		guestLogin.setSize(200, 30);
		guestLogin.setLocation(450, 200);
		pane.add(guestLogin);
		
		frame.setContentPane(pane);
		// Set the width and height of the screen.
		frame.setSize(700, 500);
		frame.setLocationByPlatform(true);
		// Make the frame visible.
		frame.setVisible(true);
		
		// Add an action listener that listens for when the member login button is clicked.
		memberLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberLoginWindow(frame, db);
			}
		});
		
		// Add an action listener that listens for when the create member login button is clicked.
		createMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewMember(frame, db);
			}
		});
		
		// Add an action listener that listens for when the guest login button is clicked.
		guestLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HomePage(new UserUnit ("Guest", null, null, null), db);
				frame.dispose();
			}
		});
	}
	
	public void memberLoginWindow(JFrame frame, Database db) {
		JFrame loginFrame = new JFrame("Member Login");
		
		// Create a JPanel object for logging in a staff member.
		JPanel loginpane = new JPanel();
		loginpane.setOpaque(true);
		loginpane.setLayout(null);
		
		// The title of the staff login screen
		JLabel title = new JLabel("Please provide your name and password.", JLabel.CENTER);
		title.setSize(300, 30);
		title.setLocation(150, 100);
		loginpane.add(title);
		
		// The Username prompt.
		JLabel firstn = new JLabel("Username: ", JLabel.LEFT);
		firstn.setSize(70, 15);
		firstn.setLocation(50, 150);
		loginpane.add(firstn);
				
		// The Password prompt.
		JLabel lastn = new JLabel("Password: ", JLabel.LEFT);
		lastn.setSize(70, 15);
		lastn.setLocation(50, 180);
		loginpane.add(lastn);
		
		// The username text field.
		JTextField username = new JTextField(25);
		username.setSize(250, 20);
		username.setLocation(135, 148);
		loginpane.add(username);
		
		// The password text field.
		JTextField password = new JPasswordField(25);
		password.setSize(250, 20);
		password.setLocation(135, 178);
		loginpane.add(password);
		
		// Make a button for logging in.
		JButton check = new JButton("Log In");
		check.setSize(120, 30);
		check.setLocation(240, 360);
		loginpane.add(check);
		
		// Password/Username incorrect
		JLabel message = new JLabel("Userame/Password Invalid");
		message.setSize(300, 25);
		message.setLocation(240, 300);
		message.setVisible(false);
		loginpane.add(message);
		
		// Make the login screen visible to the user.
		loginFrame.setContentPane(loginpane);
		loginFrame.setSize(600, 450);
		loginFrame.setLocationByPlatform(true);
		loginFrame.setVisible(true);
		
		// When the login button is clicked, evaluate the provided username and password to see if they are correct.
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// The actual first name in text.
				String usernameTxt = "";
				// The actual password in text.
				String passwordTxt = "";
				
				// Get the actual username and password in strings for comparison.
				usernameTxt = username.getText();
				passwordTxt = password.getText();
				UserUnit UserTesting = db.getUserPassword(usernameTxt);
				if(passwordTxt.equals(UserTesting.getPassword())) {
					new HomePage(UserTesting, db);
					loginFrame.dispose();
					frame.dispose();
				}
				else {
					message.setVisible(true);
				}
			}
		});
	}
	public void createNewMember(JFrame frame, Database db) {
		JFrame loginFrame = new JFrame("Create New Member");
		
		// Create a JPanel object for logging in a staff member.
		JPanel loginpane = new JPanel();
		loginpane.setOpaque(true);
		loginpane.setLayout(null);
		
		// The title of the staff login screen
		JLabel title = new JLabel("Please provide your full name, unique username, and password.", JLabel.CENTER);
		title.setSize(300, 30);
		title.setLocation(150, 100);
		loginpane.add(title);
		
		// The first name prompt.
		JLabel firstn = new JLabel("First Name: ", JLabel.LEFT);
		firstn.setSize(70, 15);
		firstn.setLocation(100, 150);
		loginpane.add(firstn);
				
		// The last name prompt.
		JLabel lastn = new JLabel("Last Name: ", JLabel.LEFT);
		lastn.setSize(70, 15);
		lastn.setLocation(100, 180);
		loginpane.add(lastn);
		
		// The password prompt.
		JLabel usern = new JLabel("Username: ", JLabel.LEFT);
		usern.setSize(70, 15);
		usern.setLocation(100, 210);
		loginpane.add(usern);
		
		// The password prompt.
		JLabel passw = new JLabel("Password: ", JLabel.LEFT);
		passw.setSize(70, 15);
		passw.setLocation(100, 240);
		loginpane.add(passw);
		
		// The first name text field.
		JTextField firstName = new JTextField(25);
		firstName.setSize(250, 20);
		firstName.setLocation(185, 150);
		loginpane.add(firstName);
				
		// The last name text field.
		JTextField lastName = new JTextField(25);
		lastName.setSize(250, 20);
		lastName.setLocation(185, 180);
		loginpane.add(lastName);
		
		// The username text field.
		JTextField username = new JTextField(25);
		username.setSize(250, 20);
		username.setLocation(185, 210);
		loginpane.add(username);
		
		// The password text field.
		JTextField password = new JPasswordField(25);
		password.setSize(250, 20);
		password.setLocation(185, 240);
		loginpane.add(password);
		
		// Make a button for logging in.
		JButton check = new JButton("Create");
		check.setSize(120, 30);
		check.setLocation(240, 300);
		loginpane.add(check);
		
		// Username already taken message
		JLabel message = new JLabel("Username Unavalible");
		message.setSize(300, 25);
		message.setLocation(240, 300);
		message.setVisible(false);
		loginpane.add(message);
		
		// Make the login screen visible to the user.
		loginFrame.setContentPane(loginpane);
		loginFrame.setSize(600, 450);
		loginFrame.setLocationByPlatform(true);
		loginFrame.setVisible(true);
		
		// When the login button is clicked, evaluate the provided username and password to see if they are correct.
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// The actual first name in text.
				String firstnameTxt = "";
				// The actual last name in text.
				String lastnameTxt = "";
				// The actual last name in text.
				String usernameTxt = "";
				// The actual password in text.
				String passwordTxt = "";
				
				// Get the actual username and password in strings for comparison.
				firstnameTxt = firstName.getText();
				lastnameTxt = lastName.getText();
				usernameTxt = username.getText();
				passwordTxt = password.getText();
				
				if(db.createNewUser(usernameTxt, passwordTxt, firstnameTxt, lastnameTxt)) {
					
					new HomePage(new UserUnit(usernameTxt,passwordTxt,firstnameTxt,lastnameTxt), db);
					loginFrame.dispose();
					frame.dispose();
				}
				else {
					message.setVisible(true);
				}
			}
		});
	}
	// This main is used for running test on login functions and running whole project.
	public static void main(String[] args) {
		//new Login();
	}

}
