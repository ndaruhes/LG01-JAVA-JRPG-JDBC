import java.util.Scanner;

public class Main {
	
	//Akibat dari membuat Design Pattern Facade
	//Main menjadi jauh lebih singkat dan sederhana
	Scanner scan = new Scanner(System.in);
	GameHelper gh = new GameHelper();
	UserHelper uh = new UserHelper();
	Character player;
	User currentUser;
	
	//load character yang sudah pernah register.
	public void loadCharacter() {
		player = gh.loadCharacter(currentUser);
		gh.printAndEnter("Successfully loaded your character");
	}
	
	
	public void fight() {
		Character enemy = gh.defineEnemy(player);
		int input = 0;
		while(!player.checkDeath() && !enemy.checkDeath()) {
			gh.cls();
			System.out.println(" BATTLE ARENA");
			System.out.println(" ==========================================================");
			
			player.printInfo();
			
			gh.playerMove(player, enemy);
			if(gh.checkEnemyDeath(player, enemy))
				return;
			enemy.printInfo();
				
			gh.enemyMove(player, enemy);
				
			gh.printAndEnter(" Press enter to continue...");
			
		}
			gh.printAndEnter("You Died enter to go back to the main menu...");
	}
	
	public void play() {
		while(true) {
			gh.cls();
			player.printInfo();
			gh.printPlayMenu();
			int input = scan.nextInt();
			scan.nextLine();
			
			if(input == 1) {
				fight();
				uh.updateUser(player, currentUser);
			}else if (input == 2) {
				player.rest();
				gh.printAndEnter(" Press enter to continue...");
				
			}else if(input == 3) {
				gh.printScoreBoard();
			}else if(input == 4) {
				gh.printAndEnter(" Thanks for playing the game..\n enter to exit...");
				currentUser = null;
				player = null;
				return;
			}
		}
		
	}
	
	public void loadAndPlayGame() {
		player = gh.loadCharacter(currentUser);
		play();
	}
	
	public void start() {
		while(true) {
			gh.cls();
			gh.printStartMenu();
			int input = gh.getInt();
			
			if(input == 1) {
				currentUser = uh.loginUser();
				if(currentUser == null) {
					gh.printAndEnter("User doesn't exist");
					continue;
				}
				loadAndPlayGame();
			}else if(input == 2) {
				currentUser = uh.registerUser();
				gh.printAndEnter(" Successfully created new player\n Press enter to continue...");
				gh.printAndEnter("Welcome to the game...");
				loadAndPlayGame();
			}else if(input == 3) {
				return;
			}
		}
	}
	
	public Main() {
		start();
	}

	public static void main(String[] args) {
		new Main();
	}

}
