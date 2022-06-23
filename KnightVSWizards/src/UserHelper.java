import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UserHelper {

	DatabaseHelper db = new DatabaseHelper();
	GameHelper gh = new GameHelper();
	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
	ArrayList<Country> countries = new ArrayList<Country>();
	
	public UserHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUsername() {
		String templateUsername;
		do {
			System.out.print(" Username [5 - 20 Characters]: ");
			templateUsername = scan.nextLine();
		}while(templateUsername.length() < 5 || templateUsername.length() > 20);
		return templateUsername;
	}
	
	public String getPassword() {
		String templatePassword;
		do {
			System.out.print(" Password [8 - 30 Characters]: ");
			templatePassword = scan.nextLine();
		}while(templatePassword.length() < 8 || templatePassword.length() > 30);
		return templatePassword;
	}
	
	public String getClassName() {
		String templateClassName;
		do {
			System.out.print(" Type [Knight or Wizard]: ");
			templateClassName = scan.nextLine();
		}while(!(templateClassName.equals("Knight") || templateClassName.equals("Wizard")));
		return templateClassName;
	}
	
	public String getCountryID() {
		int input;
		do {
			
			countries = db.getCountry();
			
			int index = 1;
			for(Country c : countries) {
				c.printInfo(index++);
			}
			
			System.out.print(" Country [ 1 - " + countries.size() + " ]: ");
			input = gh.getInt();
		}while(!(input >= 1 && input <= countries.size()));
		return countries.get(input-1).getCountryID();
	}
	
	//function facade yang berguna untuk register user dan memasukkan ke db
	public User registerUser() {
		String templateUserID,templateUsername,templatePassword,templateClassName,templateCountryID;
		Integer templateLevel,templateExp,templateScore;
	
		templateUserID = "US" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
		templateUsername = getUsername();
		templatePassword = getPassword();
		templateClassName = getClassName();
		templateCountryID = getCountryID();
		templateLevel = 1;
		templateExp = 0;
		templateScore = 0;
		
		User currentUser = new User(templateUserID,templateUsername,templatePassword,
				   templateClassName,templateCountryID,templateLevel,
				   templateExp,templateScore);
		db.insertUser(currentUser);
		
		return currentUser;
	}
	
	//ini untuk function menerima data dari user dan validasi kembali ke database.
	public User loginUser() {
		String username, password;
		username = getUsername();
		password = getPassword();
		User currentUser = db.getUser(username, password);			
		return currentUser;
	}
	
	//function untuk update data user
	public void updateUser(Character c, User u) {
		u.setLevel(c.getLevel());
		u.setExp(c.getExp());
		u.setScore(c.calculateScore());
		db.updateUser(u);
	}
}
