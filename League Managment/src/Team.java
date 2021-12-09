import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class Team {

	private static JMenuItem menuBack;
	private static JMenuBar bar;
	private static JList<String> userTeamList;
	private static JLabel title;
	private static JLabel winsTitle;
	private static JLabel losesTitle;
	private static JLabel winsDis;
	private static JLabel losesDis;
	private static JLabel addTitle;
	private static JLabel teamName;
	private static JLabel invalidTxt;
	private static JButton lookAt;
	private static JButton create;
	private static JTextField teamNameTxt;
	
	public Team (String Username, TeamUnit Team, Database db) {
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
		title = new JLabel("Players On " + Team.toString());
		title.setBounds(100,20,700,50);
		title.setFont(new Font("Courier", Font.BOLD,40));
		panel.add(title);
		
		// create Title 
		addTitle = new JLabel("Add Teammate");
		addTitle.setBounds(650,175,400,50);
		addTitle.setFont(new Font("Courier", Font.BOLD,20));
		addTitle.setVisible(false);
		panel.add(addTitle);
		
		// team name
		teamName= new JLabel("Team Name:");
		teamName.setBounds(600,225,600,50);
		teamName.setVisible(false);
		panel.add(teamName);
		
		// invalid Member
		invalidTxt= new JLabel("Not A Valid Username");
		invalidTxt.setBounds(700,360,700,50);
		invalidTxt.setVisible(false);
		panel.add(invalidTxt);
		
		// Wins title
		winsTitle= new JLabel("Wins:");
		winsTitle.setBounds(600,400,700,50);
		winsTitle.setFont(new Font("Courier", Font.BOLD,40));
		winsTitle.setVisible(true);
		panel.add(winsTitle);
				
		// Lose title
		losesTitle= new JLabel("Loses:");
		losesTitle.setBounds(750,400,700,50);
		losesTitle.setFont(new Font("Courier", Font.BOLD,40));
		losesTitle.setVisible(true);
		panel.add(losesTitle);
		
		// Display total wins
		winsDis= new JLabel(String.valueOf(Team.getWins()));
		winsDis.setBounds(600,450,700,50);
		winsDis.setFont(new Font("Courier", Font.BOLD,40));
		winsDis.setVisible(true);
		panel.add(winsDis);
		
		// Display total loses
		losesDis= new JLabel(String.valueOf(Team.getLoses()));
		losesDis.setBounds(750,450,700,50);
		losesDis.setFont(new Font("Courier", Font.BOLD,40));
		losesDis.setVisible(true);
		panel.add(losesDis);
		
		//team List
		LinkedList<TeamUnit> pog = new LinkedList<TeamUnit>();
		DefaultListModel<String> model = new DefaultListModel<String>();
		userTeamList = new JList<String>(model);
		//Set length of the of the list to the number of leagues user is in
		model.setSize(pog.size());
		
		userTeamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userTeamList.setBounds(100,75,400,400);
		JScrollPane pane = new JScrollPane(userTeamList);
		pane.setBounds(100,75,400,400);
		panel.add(pane);
		
		// create Button
		create = new JButton("Add Player");
		create.setBounds(650, 300, 100, 25);
		
		//create text feild
		teamNameTxt = new JTextField("Enter New Teammate.",25);
		teamNameTxt.setSize(250, 20);
		teamNameTxt.setLocation(675, 245);
		teamNameTxt.setVisible(false);
		panel.add(teamNameTxt);

		create.setVisible(false);
		panel.add(create);
		
		//check if they are a member and that they are not already in a team in the league
		if(Username == Team.getCaptain()) {
			create.setVisible(true);
			addTitle.setVisible(true);
			teamName.setVisible(true);
			teamNameTxt.setVisible(true);
		}
		
		// set frame to visible
		frame.setSize(1000,1000);
		frame.setVisible(true);
		
		//action listeners
		menuBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new HomePage(Username, db);
			}
		});
		
		// Add player to a team
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newTeamMember = "";
				newTeamMember = teamNameTxt.getText();
				// check if it is a valid Username.
				if(!newTeamMember.equals("Enter New Teammate.")) {
					frame.dispose();
					new Team(Username, Team, db);
				}
				else {
					invalidTxt.setVisible(true);
				}
			}
		});
	}
	// This function is used to test the team class
	public static void main(String[] args) {
		//new Team("TestUser", new TeamUnit(1, 123, "Rockets", "TestUser", 7, 5));
	}

}
