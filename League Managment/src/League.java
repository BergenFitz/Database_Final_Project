import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;
import java.util.LinkedList;

public class League {
	private static JMenuItem menuBack;
	private static JMenuBar bar;
	private static JList<String> userTeamList;
	private static JLabel title;
	private static JLabel createTitle;
	private static JLabel deleteTitle;
	private static JLabel teamName;
	private static JLabel invalidTxt;
	private static JButton lookAt;
	private static JButton create;
	private static JButton delete;
	private static JTextField teamNameTxt;
	 
	public League(UserUnit User, LeagueUnit league, Database db) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("");
		frame.add(panel);
		
		//Create the menu bar.
		bar = new JMenuBar();
		menuBack = new JMenuItem("<Back");
		bar.add(menuBack);
		frame.setJMenuBar(bar);
		
		// Title 
		title = new JLabel("Teams in " + league.getName());
		title.setBounds(100,20,700,50);
		title.setFont(new Font("Courier", Font.BOLD,40));
		panel.add(title);
		
		// create Title 
		createTitle = new JLabel("Create Team");
		createTitle.setBounds(650,175,400,50);
		createTitle.setFont(new Font("Courier", Font.BOLD,20));
		createTitle.setVisible(false);
		panel.add(createTitle);
		
		// Delete Title 
		deleteTitle = new JLabel("Delete League:");
		deleteTitle.setBounds(650,175,400,50);
		deleteTitle.setFont(new Font("Courier", Font.BOLD,20));
		deleteTitle.setVisible(false);
		panel.add(deleteTitle);
		
		// team name
		teamName= new JLabel("Team Name:");
		teamName.setBounds(600,225,600,50);
		teamName.setVisible(false);
		panel.add(teamName);
		
		// team name
		invalidTxt= new JLabel("Not An Accetible Team Name");
		invalidTxt.setBounds(700,360,700,50);
		invalidTxt.setVisible(false);
		panel.add(invalidTxt);
		
		//team List
		LinkedList<TeamUnit> pog = db.getAllTeamsInLeague(league.getID());
		DefaultListModel<String> model = new DefaultListModel<String>();
		userTeamList = new JList<String>(model);
		//Set length of the of the list to the number of leagues user is in
		model.setSize(pog.size());
		makeList(pog, model);
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
		
		// create Button
		create = new JButton("Create");
		create.setBounds(650, 300, 100, 25);
		
		// delete Button
		delete = new JButton("Delete");
		delete.setBounds(700, 250, 100, 25);
		
		//create text feild
		teamNameTxt = new JTextField("Enter Team Name.",25);
		teamNameTxt.setSize(250, 20);
		teamNameTxt.setLocation(675, 245);
		teamNameTxt.setVisible(false);
		panel.add(teamNameTxt);

		create.setVisible(false);
		panel.add(create);
		
		delete.setVisible(false);
		panel.add(delete);
		
		//check if they are a member and that they are not already in a team in the league
		if(!User.getUsername().equals("Guest") && !User.getUsername().equals("Admin") && !db.userInLeauge(User.getUsername(), league)) {
			create.setVisible(true);
			createTitle.setVisible(true);
			teamName.setVisible(true);
			teamNameTxt.setVisible(true);
		}
		else if(User.getUsername().equals("Admin")) {
			deleteTitle.setVisible(true);
			delete.setVisible(true);
		}
		
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
		lookAt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userTeamList.getSelectedIndex() != -1) {
					TeamUnit selected = pog.get(userTeamList.getSelectedIndex());
					frame.dispose();
					new Team(User, selected, db);
				}
			}
		});
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newTeamName = "";
				newTeamName = teamNameTxt.getText();
				System.out.println(newTeamName);
				if(!ProfanityCheck(newTeamName) && !newTeamName.equals("Enter Team Name.") && !db.TeamNameTaken(newTeamName, league)) {
					
					//Add the team to the database
					if(db.createNewTeam(league.getID(), newTeamName, User.getUsername())) {
						// add the captain to the team and the league.
						db.addPlayerToLeague(league.getID(), User.getUsername());
						db.addPlayeToTeam(db.getTeamID(newTeamName), User.getUsername());
						frame.dispose();
						new League(User, league, db);
					}
					else {
						invalidTxt.setVisible(false);
						invalidTxt.setVisible(true);
					}
				}
				else {
					invalidTxt.setVisible(false);
					invalidTxt.setVisible(true);
				}
			}
		});
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.deleteLeague(league.getID());
				frame.dispose();
				new HomePage(User,db);
			}
		});
		
	}
	
	public static boolean ProfanityCheck(String toCheck) {
		String[] profanity = {"shit","fuck", "ass"};
		for(int i = 0; i < profanity.length; i++) {
			if(toCheck.contains(profanity[i])) {
				return true;
			}
		}
		return false;
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
	
	public static void main(String[] args) {
		
		//new League("Admin",new LeagueUnit(123, "Rockets", "Softball"));
	}
}
