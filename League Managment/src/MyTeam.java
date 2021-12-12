import java.awt.Font;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;

public class MyTeam {
	
	private static JMenuItem menuBack;
	private static JMenuItem menuUser;
	private static JMenuItem menuMyLeague;
	private static JMenuItem menuLeagues;
	private static JMenuBar bar;
	private static JList<String> userTeamList;
	private static JLabel title;
	private static JButton lookAt;
	
	public MyTeam(UserUnit User, Database db) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("");
		frame.add(panel);
		
		//Create the menu bar.
		bar = new JMenuBar();
		menuBack = new JMenuItem("<Back");
		menuUser = new JMenuItem(User.getFullName());
		menuMyLeague = new JMenuItem("My League");
		menuLeagues = new JMenuItem("Leagues");
		bar.add(menuBack);
		bar.add(menuUser);
		bar.add(menuMyLeague);
		bar.add(menuLeagues);
		frame.setJMenuBar(bar);
		
		// Title 
		title = new JLabel("Your Teams:");
		title.setBounds(175,20,400,50);
		title.setFont(new Font("Courier", Font.BOLD,40));
		panel.add(title);
		
		//team List
		LinkedList<TeamUnit> pog = db.getMyTeams(User.getUsername());
		DefaultListModel<String> model = new DefaultListModel<String>();
		userTeamList = new JList<String>(model);
		//Set length of the of the list to the number of leagues user is in
		model.setSize(pog.size());
		makeList(pog,model);
		userTeamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userTeamList.setBounds(100,75,400,400);
		JScrollPane pane = new JScrollPane(userTeamList);
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
		
		//action listeners
		menuBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new HomePage(User, db);
			}
		});
		menuUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new UserInfo(User, db);
			}
		});
		menuMyLeague.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new MyLeague(User, db);	
			}
		});
		menuLeagues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AllLeagues(User, db);
			}
		});
		lookAt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userTeamList.getSelectedIndex() != -1) {
					TeamUnit selected = pog.get(userTeamList.getSelectedIndex());
					frame.dispose();
					new Team(User, selected,db);
				}
			}
		});
	}
	
	private DefaultListModel<String> makeList(LinkedList<TeamUnit> rog, DefaultListModel<String> modell) {
		for (int i = 0; i < rog.size(); i++) {
			// We take an individual register item out of the list.
			TeamUnit team = rog.get(i);
			// We use that register item to get the program that it corresponds to.
			String input = "<html>" + team.toString()+"</html>";
			modell.add(i,input);
		}
		return modell;
	}
	// This function is used to test MyTeam class
	public static void main(String[] args) {
		//new MyTeam("TestUser");
	}

}
