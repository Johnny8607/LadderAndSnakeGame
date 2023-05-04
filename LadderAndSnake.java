/** 
 * 
 * General explanation: This program runs a Ladder and Snake game. The game can be played with a maximum of 2 players.
 * The goal of the game is to reach the maximum position in the board which is 100. Nevertheless, hidden snakes can prevent 
 * you from achieving the goal. Luckily, there's also hidden ladders that can help you jump from position to another!
 * Conclusively, this program is made with 3 different classes: Player, Ladder and Snake.
 */

import java.util.Scanner;

/**
 * @author JohnnyDang
 */
public class LadderAndSnake {

/**
 * Declaration and initialization of instance attributes.
 */
	private String[][] board_game = new String[10][10];
	private Ladder[] ladder = new Ladder[9];
	private Snake[] snake = new Snake[8];
	private int num_players;

/**
 * Default constructor 
 */
	public LadderAndSnake() {

	}

/**
 * Parameterized constructor
 * @param num_players; takes num_players and validate the input by the user.
 * Method validate_num_players() is shown bellow.
 */
	public LadderAndSnake(int num_players) {
		validate_num_players(num_players);

	}
	
/**
 * Public method which returns an integer
 * @return number of players that play the game.
 */
	public int getPlayers() {
		return num_players;
	}

/**
 * Void method which fills the 2D-array board with position from 1-100.
 */
	public void FillBoard(){

		int count = 0;
		for (int i = 9; i >= 0; i--) {

			if (i % 2 != 0) {

				for (int j = 0; j < 10; j++) {
					count++;
					board_game[i][j] = Integer.toString(count);
				}
			} else {
				for (int j = 9; j > -1; j--) {
					count++;
					board_game[i][j] = Integer.toString(count);
				}
			}
		}
		
	}
	
/**
 * Void method which displays the filled board.
 */
	public void DisplayBoard(){
		
		for (int i = 0; i < 10; i++) {

			for (int j = 0; j < 10; j++) {
				System.out.print("\t" + board_game[i][j]);
			}

			System.out.println("\n");
		}
		
	}

/**
 * Void method which replace the number position of a specific location in the board with the name of the player who took the place.
 * @param Object Player
 */
	public void setPositionBoard(Player player) {
		
		for (int i = 0; i < 10; i++) {

			for (int j = 0; j < 10; j++) {
				if (board_game[i][j].equals(Integer.toString(player.getPosition())))
					board_game[i][j] = player.getName();

			}
		}
		
	}
/**
 * Method which validates the number of player inputed and displays a message depending on the condition that is satisfied. 
 * @param num_players
 */
	public void validate_num_players(int num_players) {

		System.out.println("");
		if (num_players == 2) {
			this.num_players = 2;

		} else if (num_players > 2) {
			System.out.println("Initialization was attempted for " + num_players + " member of players; "
					+ "however, this is only expected for an extented version of the game. \nValue will be set to 2.");
			this.num_players = 2;

		} else if (num_players < 2) {
			System.out.println("Error: Cannot execute the game with less than 2 players! Will exit.");
			System.exit(0);
		}

	}

/**
 * Method which returns a random integer ranging from 1-6. 
 * @return random integer between 1-6.
 */
	public int flipDice() {
		return (int) (Math.random() * 6 + 1);
	}

/**
 * Method which initiates the core engine of the game where the players start to play the game.
 */
	public void play() {
		
/**
 * Declaration of the Scanner in order to prompt the user for inputs.
 */
		Scanner key = new Scanner(System.in);

/**
 * Welcome message is displayed. 
 */
		System.out.println("*****************************************************");
		System.out.println();
		System.out.println("Welcome to the Ladder and Snake game made by Mr.Dang! ");
		System.out.println();
		System.out.println("*****************************************************");
		System.out.println();

/**
 * Message to prompt the user for the number of players.
 * Validation of the number of player enter.
 */
		
		boolean isNumber = false;
		String num_players;

		do {
			System.out.print("Enter the number of players: ");
			num_players = key.nextLine();

			for (int i = 0; i < num_players.length(); i++) {
				
				if (Character.isDigit(num_players.charAt(i))) {
					isNumber = true;
				} else {
					System.out.println("Please enter a number!");
					isNumber = false;
					break;
				}
			}
		} while (!isNumber);

		validate_num_players(Integer.parseInt(num_players));

/**
 * Setting the arrays ladder and snake.
 */
		setArrayLadder();
		setArraySnake();
		
		System.out.println();
/**
 * While loop which initiates the game until the user answer input "N" (no).
 */
		while (ReadytoPlay()) {
			System.out.println("LETS'S GO!\n");
			System.out.println("Please enter a name with a maximum of 6 characters.");

/**
 * Creation of player 1 and player 2.
 * Set the name of player 1 and player 2.
 */
			System.out.print("Player 1 enter your name: ");
			String name1 = key.nextLine().toUpperCase();

			System.out.print("Player 2 enter your name: ");
			String name2 = key.nextLine().toUpperCase();

			if(name1.equals(name2)) {
				name1 += 1;
				name2 +=2;
			}
			
			Player player1 = new Player(name1);
			Player player2 = new Player(name2);

/**
 * Welcome message with the name of the players.
 */
			System.out.println("\nWelcome to the game " + player1.getName() + " and " + player2.getName() + "!");
			System.out.println();

/**
 * Message displayed to indicate that we are deciding which player starts.
 */
			System.out.println("Now deciding which player will start playing ");
			System.out.println("Press Enter to flip the dice!");
			String junk = key.nextLine();

/**
 * Method which rolls the dice and assigns the dice value to each player.
 */
			FirstPlayer(player1, player2);

/**
 * Decides which player starts.
 */
			
			String turn = "";
			if(player1.getDiceValue() > player2.getDiceValue())
				turn = "player1";
			else turn = "player2";
			
/**
 * Board is displayed.
 */
			System.out.println("Press Enter to display the board!");
			String junk1 = key.nextLine();
			
/**
 * Two methods which fills and displays the board, respectively.
 */
			FillBoard();
			DisplayBoard();

/**
 * Declaration and initialization of the final position.
 */
			final int FINAL_POSITION = 100;
			
/**
 * Starting the game until one of the player reaches square 100.
 */
				while(player1.getPosition() != FINAL_POSITION && player2.getPosition() != FINAL_POSITION) {
					boolean DISPLAY_DEFAULT_MESSAGE = true;

/**
 * Player 1's turn to play.
 */
					if(turn == "player1") {
						
						System.out.println(player1.getName() + " press enter to flip the dice!");
						String junk2 = key.nextLine();
					/**
					 * Add the value of the dice to the position of the player1.
					 */
						player1.setDiceValue(flipDice());
						player1.setPosition(player1.getPosition() + player1.getDiceValue());
						
					/**
					 * Check if the player has reached the bottom of a ladder.
					 * If yes, the ending position of the ladder will get switched with the name of the player and the default message will not be displayed.
					 */
						for (int i = 0 ; i < ladder.length ; i++) {
							
							if(ladder[i].getStartPosition() == player1.getPosition()) {
								
								player1.setPosition(ladder[i].getEndPosition());
								
								
								setPositionBoard(player1);
								DisplayBoard();
							/**
							 * Displayed message
							 */
								System.out.println(player1.getName() + " got a dice value of " + player1.getDiceValue());
								System.out.println(player1.getName() + " has reached the bottom of a ladder at square " + ladder[i].getStartPosition() +
										" and is now moving up to square " + player1.getPosition() + " :D");
								System.out.println("Game is not over yet, keep fleeping!\n");
								DISPLAY_DEFAULT_MESSAGE = false;
								
							}
						}
					/**
					 * Check if the player has reached the head of a snake.
					 * If yes, the position at the tail of the snake will get switched with the name of the player and the default message will not be displayed
					 */
						for (int i = 0; i < snake.length ; i++) {
							
							if(snake[i].getStartPosition() == player1.getPosition()) {
								
								player1.setPosition(snake[i].getEndPosition());
								
								setPositionBoard(player1);
								DisplayBoard();
								
								/**
								 * Displayed message
								 */
								System.out.println(player1.getName() + " got a dice value of " + player1.getDiceValue());
								System.out.println("Sadly, " + player1.getName() + " has reached the head of a snake at square " + snake[i].getStartPosition() +
										" and is now moving down to square " + player1.getPosition() + " T_T");
								System.out.println("Game is not over yet, keep fleeping!\n");
								DISPLAY_DEFAULT_MESSAGE = false;
							}
						}
						
					/**
					 * Check if the position of the player is greater than 100.
					 * If yes, player moves frontward until square 100, then moves backward to the new position assigned. 
					 * The default message will not be displayed.
					 */
						if(player1.getPosition() > 100) {
							
							/**
							 * Algorithm shown bellow.
							 */
							PositionGreater100(player1);
							DISPLAY_DEFAULT_MESSAGE = false;
							
						}
						
					/**
					 * Check if the position is 100.
					 * If yes, the player won and the while-loop will end.
					 * The default message will not be displayed 
					 */
						else if(player1.getPosition() == 100) {
							Winner(player1);
							DISPLAY_DEFAULT_MESSAGE = false;
						}
							
					/**
					 * Check if player1 has reached the same position as player2.
					 * If yes, assign the name of the player 1 at the position of player2, and reset the position of the player2 at 0.
					 * The default message will not get displayed. 
					 */
						else if(player1.getPosition() == player2.getPosition()) {
							
							EqualPosition(player1,player2);
							DISPLAY_DEFAULT_MESSAGE = false;
						}
						
					/**
					 * If none of the case aforementioned got pass through, the default message will be displayed.
					 */
						if(DISPLAY_DEFAULT_MESSAGE)
							DisplayDefaultMessage(player1);

					/**
					 * In order to not have the name of the player1 at 2 location, we need to reset the board and re-assign
					 * the name of the player1 at the most recent position it has been assigned to.
					 */
						FillBoard();
						setPositionBoard(player1);
						
					/**
					 * Player2 is playing on the next round.
					 */
						turn = "player2";					
						
					}
					
/**
 * Player 2's turn to play.
 */
					else {
						
						System.out.println(player2.getName() + " press enter to flip the dice!");
						String junk3 = key.nextLine();
						
					/**
					  * Add the value of the dice to the position of the player2.
					*/
						player2.setDiceValue(flipDice());
						player2.setPosition(player2.getPosition() + player2.getDiceValue());
					
					/**
					 * Check if the player has reached the bottom of a ladder.
					 * If yes, the ending position of the ladder will get switched with the name of the player and the default message will not be displayed.
					 */
						for (int i = 0 ; i < ladder.length ; i++) {
							
							if(ladder[i].getStartPosition() == player2.getPosition()) {
								
								player2.setPosition(ladder[i].getEndPosition());
								
								setPositionBoard(player2);
								DisplayBoard();
								
								/**
								 * Displayed message
								 */
								System.out.println(player2.getName() + " got a dice value of " + player2.getDiceValue());
								System.out.println(player2.getName() + " has reached the bottom of a ladder at square " + ladder[i].getStartPosition() +
										" and is now moving up to square " + player2.getPosition() + " :D");
								System.out.println("Game is not over yet, keep fleeping!\n");
								DISPLAY_DEFAULT_MESSAGE = false;
							}
						}
					/**
					 * Check if the player has reached the head of a snake.
					 * If yes, the position at the tail of the snake will get switched with the name of the player and the default message will not be displayed
					 */				
						for (int i = 0; i < snake.length ; i++) {
							
							if(snake[i].getStartPosition() == player2.getPosition()) {
								
								player2.setPosition(snake[i].getEndPosition());
								
								setPositionBoard(player2);
								DisplayBoard();
								
								/**
								 * Displayed message 
								 */
								System.out.println(player2.getName() + " got a dice value of " + player2.getDiceValue());
								System.out.println("Sadly, " + player2.getName() + " has reached the head of a snake at square " + snake[i].getStartPosition() +
										" and is now moving down to square " + player2.getPosition() + " T_T");
								System.out.println("Game is not over yet, keep fleeping!\n");
								DISPLAY_DEFAULT_MESSAGE = false;
							}
						}
					
					/**
					 * Check if the position of the player is greater than 100.
					 * If yes, player moves frontward until square 100, then moves backward to the new position assigned. 
					 * The default message will not be displayed.
					 */
						if(player2.getPosition() > 100) {
							
							/**
							 * Algorithm shown bellow.
							 */
							PositionGreater100(player2);
							DISPLAY_DEFAULT_MESSAGE = false;
							
						}
					
					/**
					 * Check if the position is 100.
					 * If yes, the player won and the while-loop will end.
					 * The default message will not be displayed 
					 */
						else if(player2.getPosition() == 100) {
							Winner(player2);
							DISPLAY_DEFAULT_MESSAGE = false;
						}
							
					/**
					 * Check if player1 has reached the same position as player2.
					 * If yes, assign the name of the player 2 at the position of player1, and reset the position of the player1 at 0.
					 * The default message will not get displayed. 
					 */
						else if(player2.getPosition() == player1.getPosition()) {
							
							EqualPosition(player2,player1);
							DISPLAY_DEFAULT_MESSAGE = false;
							
						}
						
					/**
					 * If none of the case aforementioned got pass through, the default message will be displayed.
					 */					
						if(DISPLAY_DEFAULT_MESSAGE)
							DisplayDefaultMessage(player2);

					/**
					 * In order to not have the name of the player2 at 2 location, we need to reset the board and re-assign
					 * the name of the player2 at the most recent position it has been assigned to.
					 */
						FillBoard();
						setPositionBoard(player2);
						
					/**
					 * Player1 is playing on the next round.
					 */
						turn = "player1";
						
					}
					
				}
			
		/**
		 * Method which asks the players if they want to play again.
		 */
			PlayAgain();
		}

		key.close();
	}
	
/**
 * Void method which asks the players if they want to re play.
 */
	public void PlayAgain() {
		Scanner key = new Scanner(System.in);

		boolean again = true;
		while (again) {

			System.out.println("\nDo you want to keep playing? (answer with Y or N) ");
			String s1 = key.next();

			if (s1.toUpperCase().equals("Y")) {
				break;
			} else if (s1.toUpperCase().equals("N"))
				System.out.println("\nThank you for playing at this amazing game!");
				System.exit(0);

		}
		
	}

/**
 * Method which asks the players if they are ready to play and returns a boolean.
 * @return True or False depending on the condition that is satisfied.
 */
	public boolean ReadytoPlay() {
		Scanner key = new Scanner(System.in);

		boolean again = true;
		while (again) {

			System.out.println("Are you ready to play the game? (answer with Y or N): ");
			String s2 = key.nextLine();

			if (s2.toUpperCase().equals("Y")) {
				return true;
			} else if (s2.toUpperCase().equals("N"))
				again = false;
		}
		return false;
	}

/**
 * Method which decides which player starts first.
 * @param player1
 * @param player2
 */
	public void FirstPlayer(Player player1, Player player2) {
		Scanner key = new Scanner(System.in);

		int count = 0;
		String s1 = "";
		while (s1.equals("")) {

			player1.setDiceValue(flipDice());
			player2.setDiceValue(flipDice());

			System.out.println("Player 1 got a dice value of " + player1.getDiceValue());
			System.out.println("Player 2 got a dice value of " + player2.getDiceValue());

			if (player1.getDiceValue() == player2.getDiceValue()) {
				System.out.println("A tie was acheved between Player 1 and Player 2. Attempting to break the tie");
				count++;

				System.out.println("\nPress \"Enter\" to keep flipping again!");
				s1 = key.nextLine();
			}

			else {
				count++;
				break;
			}

		}
		if (player1.getDiceValue() > player2.getDiceValue())
			System.out.println("\nReached final decision on order of playing: Player 1 then Player 2. It took " + count
					+ " attempts before a decision could be made.\n");

		else
			System.out.println("\nReached final decision on order of playing: Player 2 then Player 1. It took " + count
					+ " attempts before a decision could be made.\n");

	}

/**
 * Method which initialized the array attribute ladder.
 */
	public void setArrayLadder() {
		
		Ladder l1 = new Ladder(1,38), l2 = new Ladder(4,14), l3 = new Ladder(9,31), l4 = new Ladder(21,42), l5 = new Ladder(28,84),
				l6 = new Ladder(36,44), l7 = new Ladder(51,67), l8 = new Ladder(71,91), l9 = new Ladder(80,100);
		
		ladder[0] = l1; ladder[1] = l2; ladder[2] = l3; ladder[3] = l4; ladder[4] = l5; ladder[5] = l6; ladder[6] = l7; ladder[7] = l8; ladder[8] = l9;
		
	}
	
/**
 * Method which initialized the array attribute snake.
*/
	public void setArraySnake() {
		
		Snake s1 = new Snake(16,6), s2 = new Snake(48,30), s3 = new Snake(64,60), s4 = new Snake(79,19), s5 = new Snake(93,68),
				s6 = new Snake(95,24), s7 = new Snake(97,76), s8 = new Snake(98,78);
		
		snake[0] = s1; snake[1] = s2; snake[2] = s3; snake[3] = s4; snake[4] = s5; snake[5] = s6; snake[6] = s7; snake[7] = s8;
		
	}

/**
 * Method which displays the default message.
 * @param Object Player.
 */
	public void DisplayDefaultMessage(Player player) {
		setPositionBoard(player) ;
		DisplayBoard();
		System.out.println(player.getName() + " got a dice value of " + player.getDiceValue() + " and is now located in square " + player.getPosition());
		System.out.println("Game is not over yet, keep fleeping!\n");
	}

/**
 * Method that checks if the position of the player is greater than 100 and place the player at a new position.
 * @param Object Player.
 */
	public void PositionGreater100(Player player) {
		
		System.out.println("That was close!");
		
		int backward = player.getPosition() - 100;
		int frontward = Math.abs(player.getDiceValue() - backward);
		player.setPosition(100-backward);
		
		setPositionBoard(player);
		DisplayBoard();
		
		System.out.println(player.getName() + " got a dice value of " + player.getDiceValue());
		System.out.println(player.getName() + " moves " + frontward + " squares up to 100, then moves " + backward + 
				" squares backward to square " + player.getPosition());
		System.out.println("Game is not over yet, keep fleeping!\n");
	}
	
/**
 * Method that displays the winner.
 * @param player
 */
	public void Winner(Player player) {
		setPositionBoard(player) ;
		DisplayBoard();
		System.out.println(player.getName() + " JUST WON THE GAME WOO!!");
	}
	
/**
 * Method that verify is 2 players are at the same location.
 * Replace the position with the player that just arrived.
 * Reset to 0 the position of the player that got kicked out.
 * @param player1
 * @param player2
 */
	public void EqualPosition(Player player1, Player player2) {
		for (int i = 0; i < 10; i++) {

			for (int j = 0; j < 10; j++) {
				if (board_game[i][j].equals(player2.getName()))
					board_game[i][j] = player1.getName();

			}
		}
		player2.setPosition(0);
		
		System.out.println(player1.getName() + " got a dice value of " + player1.getDiceValue());
		System.out.println(player1.getName() + " just arrived at the cell " + player1.getPosition() + " where " + player2.getName() + 
				" was also located. Unfortunately, " + player2.getName() + " is being reset to 0. " + player1.getName() + " takes the spot :)");
	
		DisplayBoard();
		
		System.out.println("Game is not over yet, keep fleeping!\n");
	}

}
