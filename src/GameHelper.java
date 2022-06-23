import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//Class berisikan method-method sebagai implementasi
//Design Pattern Facade. Berisikan method-method
//untuk menjalankan game

public class GameHelper {
	
	Scanner scan = new Scanner(System.in);
	DatabaseHelper db = new DatabaseHelper();
	public GameHelper() {
		// TODO Auto-generated constructor stub
	}

	public void cls() {
		for(int i = 0; i<40; i++) {
			System.out.println("");
		}
	};
	
	public void printStartMenu() {
		System.out.println(" Knights VS Wizard");
		System.out.println(" =================================");
		System.out.println(" 1. Login");
		System.out.println(" 2. Register");
		System.out.println(" 3. Exit");
		System.out.print(" >> ");
	}
	
	public void printScoreBoard() {
		ArrayList<User> users = db.getAllUser();
		ArrayList<Country> countries = db.getCountry();
		
		System.out.println(" Top 10 Global Scoreboard");
		System.out.println(" =============================");
		
		int index = 1;
		for(User u : users) {
			if(index == 10) {
				break;
			}
			System.out.print(" " + index++ + ". " + u.getUsername() + " (");
			for(Country c : countries) {
				if(c.getCountryID().equals(u.getCountryID())) {
					System.out.print(c.getCountryName());
					break;
				}
			}
			System.out.println(") Score: " + u.getScore());
			
		}
		printAndEnter("Press enter to continue...");
		
	}
	
	public void printPlayMenu() {
		System.out.println(" 1. Enter a Fight");
		System.out.println(" 2. Rest");
		System.out.println(" 3. Scoreboard");
		System.out.println(" 4. End Game");
		System.out.print(" >> ");
	}
	
	public void printPlayerMoves(Character player) {
		if(player instanceof Knight) {
			System.out.println(" Choose your move: ");
			System.out.println(" 1. Attack");
			System.out.println(" 2. Defend");
			System.out.print(" >> ");
		}else {
			System.out.println(" Choose your move: ");
			System.out.println(" 1. Attack");
			System.out.println(" 2. Heal");
			System.out.print(" >> ");
		}
	}
	
	public void printAndEnter(String msg) {
		System.out.println(msg);
		scan.nextLine();
	}
	
	public int getRandom(int num) {
		Random rand = new Random();
		return rand.nextInt(num); 
	}
	
	public Character defineEnemy(Character player) {
		if(player instanceof Knight) {
			return new Wizard("Wizard",player.getLevel());
		}else {
			return new Knight("Knight", player.getLevel());
		}
		
	}
	
	public int getInt() {
		int input = scan.nextInt();
		scan.nextLine();
		return input;
	}
	
	public Character loadCharacter(User user) {
		
		Character c = null;
		if(user.getClassName().equals("Knight")) {
			c = new Knight(user.getUsername(), user.getLevel());
			c.setExp(user.getExp());
		}else {
			c = new Wizard(user.getUsername(), user.getLevel());
			c.setExp(user.getExp());
		}
		
		return c;
			
	}
	
	public Character definePlayer(int input) {
		if(input == 1) {
			return new Knight("Knight", 1);
		}else if(input == 2) {
			return new Wizard("Wizard", 1);
		}else {
			return null;
		}
		
	}
	
	
	public void attackEnemy(Character player, Character enemy) {
		if(player instanceof Knight) {
			int attackDamage = player.attack();
			enemy.takeDamage(attackDamage);
		}else {
			int attackDamage = player.attack();
			if(((Knight) enemy).isDefending()) {
				((Knight)enemy).Defend(attackDamage);
				((Knight)enemy).setDefending(false);
			}else {
				enemy.takeDamage(attackDamage);
			}
		}
		
	}
	
	public void enemyAttack(Character player, Character enemy) {
		if(player instanceof Knight) {
			int enemyAttackDamage = enemy.attack();
			if(((Knight) player).isDefending()) {
				((Knight) player).Defend(enemyAttackDamage);
				((Knight) player).setDefending(false);
			}else {
				player.takeDamage(enemyAttackDamage);
			}
		}else {
			int enemyAttackDamage = enemy.attack();
			player.takeDamage(enemyAttackDamage);
		}
	}
	
	public boolean checkEnemyDeath(Character player, Character enemy) {
		if(enemy.checkDeath() == true) {
			player.obtainExp();
			System.out.println(" You gained Exp !");
			System.out.println(" Press enter to continue...");
			scan.nextLine();
			return true;
		}
		return false;
	}
	
	public boolean checkManaAvailability(Character player) {
		if(!((Wizard) player).checkMana()) {
			System.out.println(" Mana is not enough!!");
			System.out.println(" Press enter to continue...");
			scan.nextLine();
			return false;
		}
		return true;
	}
	
	public boolean doActionBasedOnInput(Character player, Character enemy, int input) {
		if(player instanceof Knight) {
			if(input == 1) {
				attackEnemy(player, enemy);
			}else if (input == 2) {
				
				((Knight) player).setDefending(true);
			}
			return true;
		}else {
			if(input == 1) {
				attackEnemy(player,enemy);
			}else if (input == 2) {
				if(!checkManaAvailability(player)) 
					return false;
				((Wizard) player).heal();
			}
		}
		return true;
	}
	
	public boolean willWizardHeal(Character enemy, int randomMove) {
		return randomMove > 50 && ((Wizard) enemy).checkMana();
	}
	
	public boolean willKnightDefend(Character enemy, int randomMove) {
		return randomMove > 50 && !((Knight) enemy).isDefending();
	}
	
	public void playerMove(Character player, Character enemy) {
		int input = 0;
		while(true) {
			printPlayerMoves(player);
			input = getInt();
			if(input == 1 || input == 2)
				if(doActionBasedOnInput(player, enemy, input))
					break;
				
		}
	}
	
	public void enemyMove(Character player, Character enemy) {
		int randomMove = getRandom(100);
		if(player instanceof Knight) {
			if( willWizardHeal(enemy, randomMove)) {
				((Wizard) enemy).heal();
			}else {
				enemyAttack(player, enemy);
			}
		}else {
			if(willKnightDefend(enemy, randomMove)) {
				((Knight) enemy).setDefending(true);
			}else {
				enemyAttack(player, enemy);
			}
		}
	}
	
}
