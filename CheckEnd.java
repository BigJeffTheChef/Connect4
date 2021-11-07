package challengeConnectFour;
/**
 * This class is just a container for all the game ending condition checks
 * @author Peter Marley
 *
 */
public class CheckEnd {
	/**
	 * Computes the possible outcomes: player 1 win, player 2 win, draw, or game has not ended?
	 * @param column An int - the column the player slid their piece into
	 * @param player An int - the player who made the move
	 * @return A boolean - has the game ended?
	 */
	public static boolean checkEnd(int column, int player) {
		boolean hasGameEnded = false;
		boolean boardFull = checkDraw();
		
		// find the row the players piece is on
		int row = Main.gameHeight - 1; // set row to a default of the bottom row (unneccessary?)
		for (int i = 0; i < Main.gameHeight; i++) { 
			if (Main.board[i][column] != 0) {
				row = i;
				break;
			}
		}
		
		boolean isWin = checkWin(row, column, player);
		
		if (isWin) {
			hasGameEnded = true;
			Main.gameEndCondition = player;
		} else if (boardFull) {
			hasGameEnded = true;
			Main.gameEndCondition = 3; // 3 == tie/ board is full
		}
		return hasGameEnded;
	}

	/**
	 * Checks if the game was a draw, i.e. was the top row filled
	 * @return A boolean - was the game a draw?
	 */
	public static boolean checkDraw() {
		boolean boardFull = true;
		for (int i = 0; i < Main.gameWidth; i++) {
			if (Main.board[0][i] == 0) {
				boardFull = false;
				break;
			}
		}
		return boardFull;
	}

	/**
	 * Consecutively calls the 4 methods to check the 4 different axis
	 * @param pieceRow An int - the row of the played piece
	 * @param pieceCol An int - the column of the played piece
	 * @param player An int - the player who made this move
	 * @return A boolean - was a win found?
	 */
	public static boolean checkWin(int pieceRow, int pieceCol, int player) {
		boolean isWin = false;
		isWin = checkVertical(pieceCol, player);
		if (!isWin) {
			isWin = checkHorizontal(pieceRow, player);
		}
		if (!isWin) {
			isWin = checkDiagLLtoUR(pieceRow, pieceCol, player);
		}
		if (!isWin) {
			isWin = checkDiagULtoLR(pieceRow, pieceCol, player);
		}
		return isWin;
	}

	/**
	 * Check if there are 4 in a row for this player along the upper-left to lower-right axis
	 * @param row An int - the row of the played piece
	 * @param col An int - the column of the played piece
	 * @param player An int - the player number for this move
	 * @return A boolean - were 4 consecutive pieces (a win) found on this axis?
	 */
	public static boolean checkDiagULtoLR(int row, int col, int player) {
		//Prints.displayBoardContents(Main.board);
		int consecCount = 0;
		for (int i = 1; row > 0 && col > 0 && i <= 3; i++) { // get upper left most valid position (max 3 places from last played piece)
			col--;
			row--;
		}
		// now row and col are upper-left most position from last piece played on board
		for (int i = 1; (row < Main.gameHeight - 1 && col < Main.gameWidth - 1) && consecCount != 4 && i <= 8; i++) {
			if (Main.board[row][col] == player) {
				consecCount++;
			} else {
				consecCount = 0;
			}
			col++;
			row++;
		}
		return (consecCount == 4) ? true : false;
	}

	/**
	 * Check if there are 4 in a row for this player along the lower-left to upper-right axis
	 * @param row An int - the row of the played piece
	 * @param col An int - the column of the played piece
	 * @param player An int - the player number for this move
	 * @return A boolean - were 4 consecutive pieces (a win) found on this axis?
	 */
	public static boolean checkDiagLLtoUR(int row, int col, int player) {
		//Prints.displayBoardContents(Main.board);
		int consecCount = 0;
		for (int i = 1; row < Main.gameHeight - 1 && col > 0 && i <= 3; i++) { // get lower left most valid position (max 3 places from last played piece)
			col--;
			row++;
		}
		// now row and col are bottom-left most from last piece played on board
		for (int i = 1; (row > 0 && col < Main.gameWidth) && consecCount != 4 && i <= 8; i++) {
			if (Main.board[row][col] == player) {
				consecCount++;
			} else {
				consecCount = 0;
			}
			col++;
			row--;
		}
		return (consecCount == 4) ? true : false;
	}

	/**
	 * Check if there are 4 in a row for this player along the horizontal axis
	 * @param row An int - the row of the played piece
	 * @param player An int - the player number for this move
	 * @return A boolean - were 4 consecutive pieces (a win) found on this axis?
	 */
	public static boolean checkHorizontal(int row, int player) {
		int consecCount = 0;
		boolean isWin = false;
		for (int i = 0; i < Main.gameHeight; i++) {
			if (Main.board[row][i] == player) {
				consecCount++;
			} else {
				consecCount = 0;
			}
			if (consecCount == 4) {
				break;
			}
		}
		if (consecCount == 4) {
			isWin = true;
		}
		return isWin;
	}

	/**
	 * Check if there are 4 in a row for this player along the vertical axis
	 * @param column An int - the column of the played piece
	 * @param player An int - the player number for this move
	 * @return A boolean - were 4 consecutive pieces (a win) found on this axis?
	 */
	public static boolean checkVertical(int column, int player) {
		int consecCount = 0;
		boolean isWin = false;
		for (int i = 0; i < Main.gameHeight; i++) {
			if (Main.board[i][column] == player) {
				consecCount++;
			} else {
				consecCount = 0;
			}
			if (consecCount == 4) {
				break;
			}
		}
		if (consecCount == 4) {
			isWin = true;
			Main.gameEndCondition = player;
		}
		return isWin;
	}
}
