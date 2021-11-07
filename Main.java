package challengeConnectFour;

/**
 * A game of connect 4, played in the console window<hr>
 * Many of the variables are declared as "global variables", i'm not 100% sure of the correct terminology, this is a
 * cheap n dirty way of accessing them from any class in the program. Probably garbage way of doing things but sure.<hr>
 * @author Peter Marley
 *
 */
public class Main {

	// *** DYNAMIC SETTINGS ***
	public static int[][] board; // the game board, accessible from all methods as it is a global variable for this class
	public static final int[] gameSize = selectDifficulty();
	public static final int gameWidth = gameSize[0];
	public static final int gameHeight = gameSize[1];
	public static final int totalRounds = 30; // every round both players get a move

	// *** TEST SETTINGS ***
	//	public static int[][] board; // the game board, accessible from all methods as it is a global variable for this class
	//	public static int[] gameSize = { 20, 20 };
	//	public static final int gameWidth = gameSize[0];
	//	public static final int gameHeight = gameSize[1];
	//	public static final int totalRounds = 30; // every round both players get a move

	/**
	 * game end conditions <br>
	 * 0 = game has not ended<br>
	 * 1 = player 1 has won<br>
	 * 2 = player 2 has won<br>
	 * 3 = board is full - tie
	 */
	public static int gameEndCondition = 0;

	public static void main(String[] args) {

		// *** DYNAMIC BOARD *** this is the "real" way of creating the board, using dynamic settings
		board = new int[gameHeight][gameWidth];

		// *** TEST BOARD *** you can use this to test different board setups - make sure you use the test settings above
		//		board = new int[][] {
		//				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		//				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		//				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		//				{ 0, 0, 0, 0, 0, 1, 2, 0, 0, 0 },
		//				{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
		//				{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
		//				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		//				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		//				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		//				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
		//		};
		//		System.out.printf("TEST PRINT Game size (width x height): %d x %d%n%n", board[0].length, board.length);

		playGame();
	}

	/**
	 * play the game!
	 */
	public static void playGame() {
		//System.out.println("TEST PRINT rounds: " + totalRounds);
		//System.out.println();

		boolean isGameover = false;
		for (int i = 0; i < totalRounds && gameEndCondition == 0; i++) {
			// Play a round for each player while game isnt over (neither player won and not a draw)
			for (int j = 1; j <= 2 && !isGameover; j++) {
				isGameover = playRound(j);
			}
		}
		Prints.displayBoard();
		Prints.displayEnd(gameEndCondition);
	}

	/**
	 * play a round for player
	 * 
	 * @param player
	 * @return A boolean - is the game over?
	 */
	public static boolean playRound(int player) {
		//System.out.println("TEST PRINT player: " + player);
		char playerPiece = (player == 1) ? 'X' : 'O';
		boolean successfulInsertion = false;
		boolean isGameover = false;
		Prints.displayBoard();
		int playerChoice = 0;
		while (!successfulInsertion) { // keep attempting to insert a piece on board until a successful insertion is made
			if (playerChoice != 0) {
				System.out.println("OOPS! That column is already full!");
			}
			System.out.printf("Player %d: Select a column (1-%d) to drop your %c%n", player, gameWidth, playerPiece);
			playerChoice = GetNum.get_ranged_int(1, gameWidth);
			successfulInsertion = insertPiece(playerChoice - 1, player);
		}
		isGameover = CheckEnd.checkEnd(playerChoice - 1, player);
		return isGameover;
	}

	/**
	 * This method inserts a piece into the games board and returns a boolean denoting if the insertion was successfull
	 * 
	 * @param playerChoice An int - the column a player inserted their piece
	 * @param player       An int - the player number (1 or 2)
	 * @return A Boolean - A successful piece insertion
	 */
	public static boolean insertPiece(int playerChoice, int player) {
		// check the column for pieces and put it in the correct row
		int rowWithPiece = gameHeight + 1; // set initial value to an impossible one
		boolean successfulInsert = false;
		for (int i = 0; i < gameHeight; i++) { // check where the highest piece in that column is
			if (board[i][playerChoice] != 0) { // check if there is a piece played in that position
				rowWithPiece = i; // there is a piece in this row
				break;
			}
			if (i == gameHeight - 1) { // if loop gets to bottom row and hasnt found a piece yet
				rowWithPiece = gameHeight;
			}
		}
		if (rowWithPiece != 0) { // if rowWithPiece is 0 then that column is full and the insertion was not successful
			board[rowWithPiece - 1][playerChoice] = player;
			successfulInsert = true;
		}
		return successfulInsert;
	}

	/**
	 * This method:<br>
	 * <br>
	 * 1 - Displays the menu<br>
	 * <br>
	 * 2a - selects difficulty<br>
	 * 2b - prompts for custom difficulty if required<br>
	 * <br>
	 * 3 - returns the settings in a 2-length 1d array
	 * 
	 * @return An int[] containing the game width and size
	 */
	public static int[] selectDifficulty() {
		Prints.displayMenu();
		int selection = GetNum.get_ranged_int(1, 3);
		/*
		 * Settings Array:
		 * index 0: rows
		 * index 1: columns
		 */
		int[] settings = new int[2];
		int minWidth = 4;
		int maxWidth = 20;
		int minHeight = 4;
		int maxHeight = 20;
		switch (selection) {
		case 1:
			settings[0] = 7;
			settings[1] = 6;
			break;
		case 2:
			settings[0] = 12;
			settings[1] = 9;
			break;
		case 3:
			System.out.print("Choose a width (" + minWidth + "-" + maxWidth + ")");
			settings[0] = GetNum.get_ranged_int(minWidth, maxWidth);
			System.out.print("Choose a height (" + minHeight + "-" + maxHeight + ")");
			settings[1] = GetNum.get_ranged_int(minHeight, maxHeight);
			break;
		default:
			settings[0] = 7;
			settings[1] = 6;
			System.out.println("Default settings used");
		}
		return settings;
	}

}
