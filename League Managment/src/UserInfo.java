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
	
	public UserInfo(String Username) {
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
		
		// set frame to visible
		frame.setSize(1000,1000);
		frame.setVisible(true);
		
		//Action Listeners
		menuLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new HomePage(Username);
			}
		});
		menuMyTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new MyTeam(Username);	
			}
		});
		menuMyLeague.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new MyLeague(Username);
			}
		});
		menuLeagues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AllLeagues(Username);
			}
		});
	}
	public static void main(String[] args) {
		new UserInfo("TestUser");

	}

}
