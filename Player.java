import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Player {

	private String name;
	private String dateOfBirth;
	private String parentGuardian;
	private String contactNumber;
	private int noOfTries;
	private int ageGroup;
	private boolean isRegistered;
	static int playerId = 0;
	private int myPlayerId;
	
	
	public Player(String name, String dateOfBirth, String parentGuardian, String contact, int ageGroup, int tries, boolean registered){
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.parentGuardian = parentGuardian;
		this.contactNumber = contact;
		this.noOfTries = tries;
		this.ageGroup = ageGroup;
		this.isRegistered = registered;
		this.myPlayerId = getUniquePlayerId();
		
	}
	
	public Player(String name, String dateOfBirth, String parentGuardian, String contact, int ageGroup, boolean registered){
		this(name, dateOfBirth, parentGuardian, contact, ageGroup, 0, registered);
		myPlayerId = getUniquePlayerId();
	}

	public int getUniquePlayerId() {
		this.playerId = playerId +1;
		return playerId;
		
	}
	
	public int getPlayerId() {
		return myPlayerId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date dateOfBirthFormat(String dob) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
		
		Date dateOfBirth = dateFormat.parse(dob);
		return dateOfBirth;
		
	}

	public String getParentGuardian() {
		return parentGuardian;
	}

	public void setParentGuardian(String parentGuardian) {
		this.parentGuardian = parentGuardian;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getNoOfTries() {
		return noOfTries;
	}

	public void setNoOfTries(int noOfTries) {
		this.noOfTries = noOfTries;
	}

	public int getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(int ageGroup) {
		this.ageGroup = ageGroup;
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public String toString() {
		return "Player name: " + name + "dateOfBirth: " + dateOfBirth;
	}

	public String printPlayerOverview(){
		return myPlayerId + "	" + "Players Name: " + name + "\n" + "	Age Group: " + ageGroup +"\n" ;
		
	}
	
	public String printDetails(){
		String playerRegistered = null;
		if (isRegistered){
			playerRegistered = "Registered";
		}
		else{
			playerRegistered = "Not Registered";
		}
		return "Player Name: " + name + "\n" + "Date of Birth: " + dateOfBirth + "\n" + "Name of Parent/Guradian: "
		+ parentGuardian + "\n" + "Contact Number: " + contactNumber + "\n" + "Number of Tries Scored: " + noOfTries + "\n"
		+ "Player Registered? " + playerRegistered + "\n";
	}

	public void writeToFile(PrintWriter out) {
		String playerRegistered = null;
		if (isRegistered){
			playerRegistered = "Registered";
		}
		else{
			playerRegistered = "Not Registered";
		}
		out.println(name);
		out.println(dateOfBirth);
		out.println(parentGuardian);
		out.println(contactNumber);
		out.println(ageGroup);
		out.println(noOfTries);
		out.println(playerRegistered);
		
	}

	
	  public static Comparator<Player> playerNameComparator = new Comparator<Player>() {

			public int compare(Player s1, Player s2) {
			   String playerName1 = s1.getName().toUpperCase();
			   String playerName2 = s2.getName().toUpperCase();

			   //ascending order
			   return playerName1.compareTo(playerName2);

			  
		    }};


	
	
	
	
}
