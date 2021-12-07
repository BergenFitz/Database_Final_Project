import javax.swing.*;
import java.awt.event.*;

public class HomePage {
	private static JMenuItem menuLogout;
	private static JMenuItem menuUser;
	private static JMenuItem menuMyTeam;
	private static JMenuItem menuMyLeague;
	private static JMenuItem menuLeagues;
	private static JMenuBar bar;
	
	public HomePage(String Username) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(Username);
		frame.add(panel);
		
		//Create the menu bar.
		bar = new JMenuBar();
		menuLogout = new JMenuItem("Logout");
		menuUser = new JMenuItem(Username);
		menuMyTeam = new JMenuItem("My Team");
		menuMyLeague = new JMenuItem("My League");
		menuLeagues = new JMenuItem("Leagues");
		bar.add(menuLogout);
		bar.add(menuUser);
		if(Username != "Guest") {
			bar.add(menuMyTeam);
			bar.add(menuMyLeague);
		}
		bar.add(menuLeagues);
		frame.setJMenuBar(bar);
		
		// set frame to visible
		frame.setSize(1000,1000);
		frame.setVisible(true);
		
		//Action Listeners
		menuLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Login();
			}
		});
		menuUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new UserInfo(Username);
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
	
	// This method used to test homepage
	public static void main(String[] args) {
		new HomePage("TestUser");
	}

}
