import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class PenlanRFCJuniorSection {
	//	data file variable name.
	private static final String FILE_NAME = "JuniorSection.txt";
	//	Array list which holds all players
	static ArrayList<Player> allPlayers = new ArrayList<Player>();
	static ArrayList<Player> playerDetailsChange = new ArrayList<Player>();
	//	a temp array list to hold players when changing details
	static ArrayList<Player> playerTemp = new ArrayList<Player>();

	static ArrayList<Player> under7 = new ArrayList<Player>();
	static ArrayList<Player> under8 = new ArrayList<Player>();
	static ArrayList<Player> under9 = new ArrayList<Player>();
	static ArrayList<Player> under10 = new ArrayList<Player>();
	static ArrayList<Player> under11 = new ArrayList<Player>();
	static Scanner in = new Scanner(System.in);
	static int i;

	Iterator<Player> itr = allPlayers.iterator();

	/*
	 * Main method which checks to see if a file is available to read data from.
	 * Initiate the main menu
	 * write data to file 
	 * METHOD OK
	 */
	public static void main(String[] args) throws IOException, ParseException {
		System.out.println("Welcome To The Penlan RFC Junior Section");
		System.out.println("*****************************************\n");
		checkFile();
		readFile();
		menu(1);
		writeFile();


	}

	/*
	 * Method to check if a data file exists. If not call method to create one.
	 * METHOD OK
	 */
	public static void checkFile() throws IOException{
		File f = new File(FILE_NAME);
		if (!f.exists()){
			writeFile();

		}

	}

	/*
	 * Method to read data in from the specified file. data used to create a Player and then saved
	 * to an Array
	 */
	public static void readFile() throws FileNotFoundException{
		File f = new File(FILE_NAME);
		Scanner in = new Scanner(f);

		while (in.hasNextLine()){
			String name = in.nextLine();
			String dateOfBirth = in.nextLine();
			String parentGuardian = in.nextLine();
			String contactNumber = in.nextLine();
			int ageGroup = in.nextInt();
			in.nextLine();
			int noOfTries = in.nextInt();
			in.nextLine();
			String registeredStatus = in.nextLine();
			boolean isRegistered = registeredStatus.equalsIgnoreCase("registered");
			Player player = new Player(name, dateOfBirth, parentGuardian, contactNumber, ageGroup, noOfTries, isRegistered);
			allPlayers.add(player);
			addToAgeGroup(player);

		}
		in.close();

	}

	public static void menu(int menuID) throws IOException{


		Menu m1 = new Menu("Choose one of the following options", in);
		m1.addOption("Add New Player");  //all ok
		m1.addOption("Delete a Player");
		m1.addOption("View All Players");
		m1.addOption("View Under 7 Players");
		m1.addOption("View Under 8 Players");
		m1.addOption("View Under 9 Players");
		m1.addOption("View Under 10 Players");
		m1.addOption("View Under 11 Players");
		m1.addOption("Search for a player");
		m1.addOption("Quit");

		boolean done;
		do {
			done = false;

			int choice = m1.executeMenu("\n" + "Please Select An Option: ");

			// Print a blank line to separate menu from the printed sentence.
			System.out.println();

			switch (choice) {
			case 1:
				addNewPlayer();
				break;
			case 2:
				deletePlayer();
				break;
			case 3:
				viewPlayers(1);
				break;
			case 4:	
				viewPlayers(7);
				break;
			case 5:
				viewPlayers(8);
				break;
			case 6:
				viewPlayers(9);
				break;
			case 7:
				viewPlayers(10);
				break;
			case 8:
				viewPlayers(11);
				break;
			case 9:
				searchForPlayer();
				break;
			case 10:
				System.out.println("Thank you");
				done = true;				
				break;
			}
			// Print a blank line to separate the next menu.
			System.out.println();			
		} while(!done);	

	}

	public static void playerOptionsMenu(){

		Menu m2 = new Menu("What would you like to change? ", in);
		m2.addOption("Player Name");
		m2.addOption("Date of Birth");
		m2.addOption("Parent/Guardian");
		m2.addOption("Contact Number");
		m2.addOption("Add Tries");
		m2.addOption("Change Registration Status");
		m2.addOption("Delete Player");
		m2.addOption("Return to Previous Menu");

		boolean done;
		do {
			done = false;

			int choice = m2.executeMenu("/n" + "Please Select An Option: ");

			// Print a blank line to separate menu from the printed sentence.
			System.out.println();

			switch (choice) {
			case 1:
				editPlayerName();
				break;
			case 2:
				//				editPlayerDob();
				break;
			case 3:
				editPlayerParentGuardian();
				break;
			case 4:
				editPlayerContactNumber();
				break;
			case 5:
				editPlayerTries();
				break;
			case 6:
				editPlayerRegistration();
				break;
				//				case 7:
				//					deletePlayer();
				//					break;
			case 8:			
				System.out.println("Thank you");
				done = true;				
				break;
			}
			// Print a blank line to separate the next menu.
			System.out.println();			
		} while(!done);	

	}

	/*
	 * METHOD OK
	 */
	public static void addNewPlayer() throws IOException{


		System.out.println("Please enter player name: ");
		String name = in.nextLine();
		System.out.println("Please enter player DoB in format DD.MM.YY: ");
		String dob = in.nextLine();
		System.out.println("Please enter the Parent/Guardians Name: ");
		String parentGuradian = in.nextLine();
		System.out.println("Please enter a contact number: ");
		String contactNumber = in.nextLine();
		System.out.println("Please enter the player age group: ");
		int ageGroup = in.nextInt();
		in.nextLine();
		System.out.println("Is the player Registered Y/N? ");
		String playerRegisterd = in.nextLine();
		boolean isRegistered = playerRegisterd.equalsIgnoreCase("y");
		Player newPlayer = new Player(name, dob, parentGuradian, contactNumber, ageGroup, isRegistered);
		allPlayers.add(newPlayer);



	}

	/*
	 * Method to view player details. 
	 */
	public static void viewPlayers(int ageGrade){
		Collections.sort(allPlayers, Player.playerNameComparator);
		Iterator<Player> itr = allPlayers.iterator();
		boolean foundPlayers = false;
		if (ageGrade == 1){
			while(itr.hasNext()) {
				foundPlayers = true;
				Player element = itr.next();
				System.out.println(element.printPlayerOverview());
			}
		}else {
			while (itr.hasNext()){
				Player element = itr.next();
				if (ageGrade == element.getAgeGroup()){
					foundPlayers = true;
					System.out.println(element.printPlayerOverview());

				}	

			}
		}

		if(!foundPlayers){
			System.out.println("There are no players avaialable" + "\n" );
			return;

		}

		System.out.println("Select a player number to see more details. Enter 0 to return to previous menu: ");
		int choosenPlayer = in.nextInt();
		in.nextLine();
		if (!(choosenPlayer == 0)){
			viewChangePlayerDetails(choosenPlayer, allPlayers);
		}


		return;
	}

	public static void viewChangePlayerDetails(int choosenPlayer, ArrayList<Player> player){

		System.out.println(player.get(choosenPlayer-1).printDetails());
		System.out.println("Do yu want to make changes to " + player.get(choosenPlayer-1).getName() + "'s record? Y/N");
		String makeChange = in.nextLine();

		if (makeChange.equalsIgnoreCase("y")){
			playerTemp.add(player.get(choosenPlayer-1));

			//			if (!(ageGroup == allPlayers)){
			for (int i = 0; i < allPlayers.size(); i ++){
				if (allPlayers.get(i).getName().equalsIgnoreCase(player.get(choosenPlayer-1).getName())){
					allPlayers.remove(i);
				}
				//				}
			}
			//			else {
			//				int age = ageGroup.get(choosenPlayer-1).getAgeGroup();
			//				if (age == 7){
			//					for (int i = 0; i < under7.size(); i ++){
			//						if (under7.get(i).getName().equalsIgnoreCase(ageGroup.get(choosenPlayer-1).getName())){
			//							under7.remove(i);
			//						}
			//					}
			//				}
			//				if (age == 8){
			//					for (int i = 0; i < under8.size(); i ++){
			//						if (under8.get(i).getName().equalsIgnoreCase(ageGroup.get(choosenPlayer-1).getName())){
			//							under8.remove(i);
			//						}
			//					}
			//				}
			//				if (age == 9){
			//					for (int i = 0; i < under9.size(); i ++){
			//						if (under9.get(i).getName().equalsIgnoreCase(ageGroup.get(choosenPlayer-1).getName())){
			//							under9.remove(i);
			//						}
			//					}
			//				}
			//				if (age == 10){
			//					for (int i = 0; i < under10.size(); i ++){
			//						if (under10.get(i).getName().equalsIgnoreCase(ageGroup.get(choosenPlayer-1).getName())){
			//							under10.remove(i);
			//						}
			//					}
			//				}
			//				if (age == 11){
			//					for (int i = 0; i < under11.size(); i ++){
			//						if (under11.get(i).getName().equalsIgnoreCase(ageGroup.get(choosenPlayer-1).getName())){
			//							under11.remove(i);
			//						}
			//					}
			//				}
			//			}

			playerOptionsMenu();
		}viewPlayers(0);

	}




	public static void editPlayerName(){
		System.out.println("Please enter players new name: ");
		String newName = in.nextLine();
		playerTemp.get(0).setName(newName);
		System.out.println(playerTemp.get(0).printDetails());
		System.out.println("Is this correct Y/N? ");
		String changeConfirm = in.nextLine();
		if (changeConfirm.equalsIgnoreCase("y")){
			allPlayers.add(playerTemp.get(0));
			playerTemp.remove(0);
		}

	}

	public static void editPlayerDob(Player p){
		System.out.println("Please enter players Date Of Birth: ");
		String newDob = in.nextLine();
		p.setDateOfBirth(newDob);
		System.out.println(p.printDetails());
		System.out.println("Is this correct Y/N? ");
		String changeConfirm = in.nextLine();
		if (changeConfirm.equalsIgnoreCase("y")){
			addToAgeGroup(p);
		}

	}

	public static void editPlayerParentGuardian(){
		System.out.println("Please enter players new parent/guardian: ");
		String newParGuar = in.nextLine();
		playerDetailsChange.get(0).setParentGuardian(newParGuar);
		System.out.println(playerDetailsChange.get(0).printDetails());
		System.out.println("Is this correct Y/N? ");
		String changeConfirm = in.nextLine();
		if (changeConfirm.equalsIgnoreCase("y")){
			addToAgeGroup(playerDetailsChange.get(0));
			playerDetailsChange.remove(0);
		}

	}

	public static void editPlayerContactNumber(){
		System.out.println("Please enter players new contact number: ");
		String newContactNumber = in.nextLine();
		playerDetailsChange.get(0).setContactNumber(newContactNumber);
		System.out.println(playerDetailsChange.get(0).printDetails());
		System.out.println("Is this correct Y/N? ");
		String changeConfirm = in.nextLine();
		if (changeConfirm.equalsIgnoreCase("y")){
			addToAgeGroup(playerDetailsChange.get(0));
			playerDetailsChange.remove(0);
		}

	}

	public static void editPlayerTries(){
		System.out.println("Please enter players number of tries: ");
		int numberOfTries = in.nextInt();
		playerDetailsChange.get(0).setNoOfTries(numberOfTries);
		System.out.println(playerDetailsChange.get(0).printDetails());
		System.out.println("Is this correct Y/N? ");
		String changeConfirm = in.nextLine();
		if (changeConfirm.equalsIgnoreCase("y")){
			addToAgeGroup(playerDetailsChange.get(0));
			playerDetailsChange.remove(0);
		}

	}

	public static void editPlayerRegistration(){
		System.out.println("Is the " + playerDetailsChange.get(0).getName() + "Registerd? ");
		String isRegistered = in.nextLine();
		boolean registered = isRegistered.equalsIgnoreCase("y");
		if(!registered){
			playerDetailsChange.get(0).setRegistered(false);
		}
		else{
			playerDetailsChange.get(0).setRegistered(true);
		}
		System.out.println(playerDetailsChange.get(0).printDetails());
		System.out.println("Is this correct Y/N? ");
		String changeConfirm = in.nextLine();
		if (changeConfirm.equalsIgnoreCase("y")){
			addToAgeGroup(playerDetailsChange.get(0));
			playerDetailsChange.remove(0);
		}

	}

	public static void deletePlayer(){
		System.out.println("Which age group is the player you wish to delete in?");
		int ageGrade = in.nextInt();
		Collections.sort(allPlayers, Player.playerNameComparator);
		Iterator<Player> itr = allPlayers.iterator();
		while (itr.hasNext()){
			Player element = itr.next();
			if (ageGrade == element.getAgeGroup()){

				System.out.println(element.printPlayerOverview());

			}	

		} System.out.println("Which player do you want to delete?");
		int playerToDelete = in.nextInt();
		allPlayers.remove(playerToDelete);

	}

	public static void searchForPlayer(){
		System.out.println("Please enter the first and last name of the player you wish to find: ");
		String searchPlayer = in.nextLine();
		for (Player search:allPlayers){
			if ( searchPlayer.equalsIgnoreCase(search.getName())){
				System.out.println(search.printDetails());

			}



		}System.out.println("There is no player with that name. Please check spelling or try a different name.");
	}

	public static void addToAgeGroup(Player player){
		if (player.getAgeGroup() == 7){
			under7.add(player);
		}
		else if (player.getAgeGroup() == 8){
			under8.add(player);
		}
		else if (player.getAgeGroup() == 9){
			under9.add(player);
		}
		else if (player.getAgeGroup() == 10){
			under10.add(player);
		}
		else if (player.getAgeGroup() == 11){
			under11.add(player);
		}
	}

	public static void writeFile() throws IOException{

		PrintWriter out = new PrintWriter(FILE_NAME);

		for (int i = 0; i < allPlayers.size(); i ++){
			allPlayers.get(i).writeToFile(out);
		}
		out.close();
	}

}
