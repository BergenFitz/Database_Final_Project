import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.util.LinkedList;

public class MyLeague {
	private static JMenuItem menuBack;
	private static JMenuItem menuUser;
	private static JMenuItem menuMyTeam;
	private static JMenuItem menuLeagues;
	private static JMenuBar bar;
	private static JList<String> userLeagueList;
	 private static JLabel title;
	 private static JButton lookAt;
	public MyLeague(String Username) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(Username);
		frame.add(panel);
		
		//Create the menu bar.
		bar = new JMenuBar();
		menuBack = new JMenuItem("<Back");
		menuUser = new JMenuItem(Username);
		menuMyTeam = new JMenuItem("My Team");
		menuLeagues = new JMenuItem("Leagues");
		bar.add(menuBack);
		bar.add(menuUser);
		bar.add(menuMyTeam);
		bar.add(menuLeagues);
		frame.setJMenuBar(bar);
		
		// Title 
		title = new JLabel("My Leagues");
		title.setBounds(200,20,400,50);
		title.setFont(new Font("Courier", Font.BOLD,40));
		panel.add(title);
		
		//League List
		LinkedList<LeagueUnit> pog = new LinkedList<LeagueUnit>();
		DefaultListModel<String> model = new DefaultListModel<String>();
		userLeagueList = new JList<String>(model);
		//Set length of the of the list to the number of leagues user is in
		model.setSize(pog.size());
		
		userLeagueList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userLeagueList.setBounds(100,75,400,400);
		JScrollPane pane = new JScrollPane(userLeagueList);
		pane.setBounds(100,75,400,400);
		panel.add(pane);
		
		// Join Button
		lookAt = new JButton("Select");
		lookAt.setBounds(400, 500, 75, 25);
		lookAt.setVisible(true);
		panel.add(lookAt);
		
		// set frame to visible
		frame.setSize(1000,1000);
		frame.setVisible(true);
		
		//Action Listeners
		menuBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new HomePage(Username);
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
		menuLeagues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AllLeagues(Username);
			}
		});
		// ActionListener for join button
		lookAt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// look at the selected league
				LeagueUnit selected = pog.get(userLeagueList.getSelectedIndex());
				frame.dispose();
				new League(Username, selected);
			}
		});
	}
	// Used to test the MyLeague Class
	public static void main(String[] args) {
		new MyLeague("TestUser");
	}

}
