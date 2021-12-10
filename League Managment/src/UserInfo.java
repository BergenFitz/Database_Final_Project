import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class UserInfo {

	private static JMenuItem menuLogout;
	private static JMenuItem menuMyTeam;
	private static JMenuItem menuMyLeague;
	private static JMenuItem menuLeagues;
	private static JMenuBar bar;
	private static JLabel title;
	private static JLabel usernameTitle;
	private static JLabel nameTitle;
	private static JLabel passwordTitle;
	private static JButton delete;
	private static JButton change;
	private static JTextField NewPassTxt;
	private static JTextField confirmNewPassTxt;
	private static JLabel NewPass;
	private static JLabel confirmNewPass;
	private static JLabel passerror;
	private static JLabel passMessage;
	
	public UserInfo(String Username, Database db) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(Username);
		frame.add(panel);
		
		//Create the menu bar.
		bar = new JMenuBar();
		menuLogout = new JMenuItem("<back");
		menuMyTeam = new JMenuItem("My Team");
		menuMyLeague = new JMenuItem("My League");
		menuLeagues = new JMenuItem("Leagues");
		bar.add(menuLogout);
		if(Username != "Guest") {
			bar.add(menuMyTeam);
			bar.add(menuMyLeague);
		}
		bar.add(menuLeagues);
		frame.setJMenuBar(bar);
		
		// Title 
		title = new JLabel("User Info:");
		title.setBounds(100,50,400,50);
		title.setFont(new Font("Courier", Font.BOLD,50));
		title.setVisible(true);
		panel.add(title);
		
		// username Title 
		usernameTitle = new JLabel(Username);
		usernameTitle.setBounds(100,100,400,50);
		usernameTitle.setFont(new Font("Courier", Font.BOLD,20));
		usernameTitle.setVisible(true);
		panel.add(usernameTitle);
		
		// name Title 
		nameTitle = new JLabel("Name");
		nameTitle.setBounds(100,150,400,50);
		nameTitle.setFont(new Font("Courier", Font.BOLD,20));
		nameTitle.setVisible(true);
		panel.add(nameTitle);
		
		// password Title 
		passwordTitle = new JLabel("Password");
		passwordTitle.setBounds(100,200,400,50);
		passwordTitle.setFont(new Font("Courier", Font.BOLD,20));
		passwordTitle.setVisible(true);
		panel.add(passwordTitle);
		
		// delete Button
		delete = new JButton("Delete");
		delete.setBounds(100, 300, 75, 25);
		delete.setVisible(true);
		panel.add(delete);
		
		// delete Button
		change = new JButton("Enter");
		change.setBounds(700, 500, 75, 25);
		change.setVisible(true);
		panel.add(change);
		
		//create text feild
		NewPassTxt = new JTextField(25);
		NewPassTxt.setSize(250, 20);
		NewPassTxt.setLocation(700, 400);
		NewPassTxt.setVisible(true);
		panel.add(NewPassTxt);
		
		//create text feild
		confirmNewPassTxt = new JTextField(25);
		confirmNewPassTxt.setSize(250, 20);
		confirmNewPassTxt.setLocation(700, 450);
		confirmNewPassTxt.setVisible(true);
		panel.add(confirmNewPassTxt);
		
		// password Title 
		NewPass = new JLabel("New Password:");
		NewPass.setBounds(600,400,100,25);
		NewPass.setFont(new Font("Courier", Font.BOLD,12));
		NewPass.setVisible(true);
		panel.add(NewPass);
		
		// password Title 
		confirmNewPass = new JLabel("Confirm Password:");
		confirmNewPass.setBounds(575,450,150,25);
		confirmNewPass.setFont(new Font("Courier", Font.BOLD,12));
		confirmNewPass.setVisible(true);
		panel.add(confirmNewPass);
		
		passMessage = new JLabel("Change Password");
		passMessage.setBounds(700, 350, 200, 25);
		passMessage.setFont(new Font("Courier", Font.BOLD,20));
		passMessage.setVisible(true);
		panel.add(passMessage);
		
		passerror = new JLabel("New Password Doesn't Match!");
		passerror.setBounds(675, 525, 200, 25);
		passerror.setFont(new Font("Courier", Font.BOLD,12));
		passerror.setVisible(false);
		panel.add(passerror);
		
		// password Title 
		passwordTitle = new JLabel("Password");
		passwordTitle.setBounds(100,200,400,50);
		passwordTitle.setFont(new Font("Courier", Font.BOLD,20));
		passwordTitle.setVisible(true);
		panel.add(passwordTitle);
		
		// set frame to visible
		frame.setSize(1000,1000);
		frame.setVisible(true);
		
		//Action Listeners
		menuLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new HomePage(Username, db);
			}
		});
		menuMyTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new MyTeam(Username, db);	
			}
		});
		menuMyLeague.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new MyLeague(Username, db);
			}
		});
		menuLeagues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AllLeagues(Username, db);
			}
		});
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.deleteUser(Username);
				frame.dispose();
				new Login(db);
			}
		});
		change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(NewPassTxt.getText().equals(e)) {
					db.setNewPassword(Username, Username);
				}
				else {
					passerror.setVisible(true);
				}
			}
		});
	}
	public static void main(String[] args) {
		new UserInfo("TestUser",Database.getInstance());

	}

}
