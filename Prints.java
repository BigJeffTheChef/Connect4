package challengeConnectFour;

/**
 * This Class holds the various testing methods for the program
 * 
 * @author Peter Marley
 *
 */
public class Prints {
	/**
	 * This is a method for printing the contents of the board[][], without fancy formatting. Only call this for debugging purposes
	 * 
	 * @param game An int[][] - Main.board
	 */
	public static void displayBoardContents(int[][] game) {
		System.out.print("   ");
		for (int i = 0; i < Main.gameWidth; i++) {
			System.out.printf("%2d ", i);
		}
		System.out.printf("%n---");
		for (int i = 0; i < Main.gameWidth; i++) {
			System.out.printf("---", i);
		}
		System.out.println();
		for (int i = 0; i < game.length; i++) {
			System.out.printf("%d |",i);
			for (int j = 0; j < game[i].length; j++) {
				System.out.printf("%2d ", game[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * This prints the end state of the game: with the victory or draw message
	 * 
	 * @param gameEndCondition An int representing the games end condition
	 */
	public static void displayEnd(int gameEndCondition) {
		//System.out.println("TEST PRINT game is over!");
		//System.out.print("gameEndCondition: ");
		String endMessage = "";
		switch (gameEndCondition) {
		case 0:
			endMessage = "Game is not really over...";
			break;
		case 1:
			endMessage = "Player 1 is victorious! Have a beer!";
			break;
		case 2:
			endMessage = "Player 2 is victorious! Have a whiskey!";
			break;
		case 3:
			endMessage = "DRAW! Board is full and there is no winner. Everyone have a stiff drink and talk it over...";
			break;
		default:
			endMessage = "OOPS! gameEndCondition is set to something strange. debug more!...%ngameEndCondition = " + gameEndCondition + "%n";
		}
		System.out.printf("%n*****************************************%n" + endMessage + "%n*****************************************%n");
	}
	
	/**
	 * Prints the menu
	 */
	public static void displayMenu() {
		System.out.println("Welcome to Connect Four");
		System.out.println();
		System.out.println("Select an option:");
		System.out.println("1) Normal board (7x6)");
		System.out.println("2) Large board (12x9)");
		System.out.println("3) Custom size");
	}
	
	/**
	 * Displays the game board - which is read from board[][]
	 */
	public static void displayBoard() {
		for (int i = 0; i < Main.board[0].length; i++) { // print number headings
			if (i != 0) {
				System.out.print(" ");
			}
			System.out.printf(" %2d", i + 1);
		}
		System.out.println();
		for (int i = 0; i < Main.board.length; i++) { // print the actual board
			for (int j = 0; j < Main.board[i].length; j++) {
				System.out.print("|_");
				if (Main.board[i][j] != 0) {
					if (Main.board[i][j] == 1) {
						System.out.print("X");
					} else {
						System.out.print("O");
					}
					//System.out.print(game[i][j]);
				} else {
					System.out.print("_");
				}
				System.out.print("_");
				if (j == Main.board[i].length - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
		}
	}

}
