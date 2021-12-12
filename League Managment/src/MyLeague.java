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
	 
	public MyLeague(UserUnit User, Database db) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(User.getFullName());
		frame.add(panel);
		
		//Create the menu bar.
		bar = new JMenuBar();
		menuBack = new JMenuItem("<Back");
		menuUser = new JMenuItem(User.getFullName());
		menuMyTeam = new JMenuItem("My Team");
		menuLeagues = new JMenuItem("Leagues");
		bar.add(menuBack);
		bar.add(menuUser);
		bar.add(menuMyTeam);
		bar.add(menuLeagues);
		frame.setJMenuBar(bar);
		
		// Title 
		title = new JLabel("Your Leagues");
		title.setBounds(150,20,400,50);
		title.setFont(new Font("Courier", Font.BOLD,40));
		panel.add(title);
		
		//League List
		LinkedList<LeagueUnit> pog = db.getMyLeagues(User.getUsername());
		DefaultListModel<String> model = new DefaultListModel<String>();
		userLeagueList = new JList<String>(model);
		//Set length of the of the list to the number of leagues user is in
		model.setSize(pog.size());
		makeList(pog,model);
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
				new HomePage(User,db);
			}
		});
		menuUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new UserInfo(User,db);
			}
		});
		menuMyTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new MyTeam(User,db);	
			}
		});
		menuLeagues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AllLeagues(User,db);
			}
		});
		// ActionListener for join button
		lookAt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// look at the selected league
				LeagueUnit selected = pog.get(userLeagueList.getSelectedIndex());
				frame.dispose();
				new League(User, selected,db);
			}
		});
	}
	
	private DefaultListModel<String> makeList(LinkedList<LeagueUnit> rog, DefaultListModel<String> modell) {
		for (int i = 0; i < rog.size(); i++) {
			// We take an individual register item out of the list.
			LeagueUnit League = rog.get(i);
			// We use that register item to get the program that it corresponds to.
			String input = "<html>" + League.getName() + ", " + League.getSport() +"</html>";
			modell.add(i,input);
		}
		return modell;
	}
	// Used to test the MyLeague Class
	public static void main(String[] args) {
		//new MyLeague("TestUser");
	}

}
