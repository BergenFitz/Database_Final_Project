import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;
import java.util.LinkedList;
public class AllLeagues {

	private static JMenuItem menuBack;
	private static JMenuBar bar;
	private static JList<String> userTeamList;
	private static JLabel title;
	private static JLabel createTitle;
	private static JLabel teamName;
	private static JLabel leagueSport;
	private static JLabel invalidTxt;
	private static JButton lookAt;
	private static JButton create;
	private static JTextField leagueNameTxt;
	private static JTextField leagueSportTxt;
	
	public AllLeagues(UserUnit User, Database db) {
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
			title = new JLabel("Leagues");
			title.setBounds(100,20,400,50);
			title.setFont(new Font("Courier", Font.BOLD,40));
			panel.add(title);
			
			// create Title 
			createTitle = new JLabel("Create League:");
			createTitle.setBounds(650,175,400,50);
			createTitle.setFont(new Font("Courier", Font.BOLD,20));
			createTitle.setVisible(false);
			panel.add(createTitle);
			
			// team name
			teamName= new JLabel("League Name:");
			teamName.setBounds(575,225,600,50);
			teamName.setVisible(false);
			panel.add(teamName);
			
			// league Sport
			leagueSport= new JLabel("League Sport:");
			leagueSport.setBounds(575,275,600,50);
			leagueSport.setVisible(false);
			panel.add(leagueSport);
			
			// invalid text
			invalidTxt= new JLabel("Not An Accetible League Name");
			invalidTxt.setBounds(700,360,700,50);
			invalidTxt.setVisible(false);
			panel.add(invalidTxt);
			
			//team List
			LinkedList<LeagueUnit> pog = db.getAllLeagues();
			DefaultListModel<String> model = new DefaultListModel<String>();
			userTeamList = new JList<String>(model);
			//Set length of the of the list to the number of leagues user is in
			model.setSize(pog.size());
			model = makeList(pog, model);
			userTeamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			userTeamList.setBounds(100,75,400,400);
			JScrollPane pane = new JScrollPane(userTeamList);
			pane.setBounds(100,75,400,400);
			panel.add(pane);
			
			// select Button
			lookAt = new JButton("Select");
			lookAt.setBounds(400, 500, 75, 25);
			lookAt.setVisible(true);
			panel.add(lookAt);
			
			// create Button
			create = new JButton("Create");
			create.setBounds(650, 350, 100, 25);
			
			//create text feild
			leagueNameTxt = new JTextField("Enter League Name.",25);
			leagueNameTxt.setSize(250, 20);
			leagueNameTxt.setLocation(675, 245);
			leagueNameTxt.setVisible(false);
			panel.add(leagueNameTxt);
			
			//create text feild
			leagueSportTxt = new JTextField("Enter League Sport.",25);
			leagueSportTxt.setSize(250, 20);
			leagueSportTxt.setLocation(675, 295);
			leagueSportTxt.setVisible(false);
			panel.add(leagueSportTxt);

			create.setVisible(false);
			panel.add(create);
			
			//check if they are a member and that they are not already in a team in the league
			if(User.getUsername().equals("Admin")) {
				create.setVisible(true);
				createTitle.setVisible(true);
				teamName.setVisible(true);
				leagueNameTxt.setVisible(true);
				leagueSportTxt.setVisible(true);
				leagueSport.setVisible(true);
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
						LeagueUnit selected = pog.get(userTeamList.getSelectedIndex());
						frame.dispose();
						new League(User, selected, db);
					}
				}
			});
			create.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String newLeagueName = "";
					String newLeagueSport = "";
					newLeagueName = leagueNameTxt.getText();
					newLeagueSport = leagueSportTxt.getText();
					if(db.createNewLeague(newLeagueName, newLeagueSport)) {
						frame.dispose();
						new AllLeagues(User, db);
					}
					else {
						invalidTxt.setVisible(false);
						invalidTxt.setVisible(true);
					}
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
	
	public static void main(String[] args) {
		//TODO Auto-generated method stub
		//new AllLeagues("Admin");
	}

}
