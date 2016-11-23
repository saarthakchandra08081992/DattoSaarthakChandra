import java.util.Scanner;

class GameOfLife {

	final static int numRounds = 1;

	static int countSurrounding(int[][] board, int a, int b) {
		int count = 0;
		try {
			int[][] surrounding = { { a - 1, b - 1 }, { a - 1, b }, { a - 1, b + 1 }, { a, b - 1 }, { a, b + 1 },
					{ a + 1, b - 1 }, { a + 1, b }, { a + 1, b + 1 } };
			for (int i[] : surrounding) {
				// Continue if we find invalid array access indexes.
				if (i[0] < 0 || i[1] < 0 || i[0] >= board[0].length || i[1] >= board[0].length)
					continue;
				if (board[i[0]][i[1]] == 1) {
					count++;
				}
			}
		} catch (Exception ex) {
			System.err.println("Exception in countSurrounding : " + ex.getMessage());
		}
		return count;
	}

	public static void main(String[] args) {

		Board boardLife = new Board();
		String filePath = "";

		if (args.length > 0)
			filePath = args[0];

		if (filePath == null || filePath.trim().length() == 0) {
			Scanner scan = new Scanner(System.in);
			while (true) {
				System.out.println("Please enter the file path : ");
				filePath = scan.nextLine();
				if (filePath.trim().length() > 0)
					// If we have a non-empty valid input
					break;
			}
		}

		int[][] nextBoard = boardLife.getBoard(filePath);
		if (nextBoard != null) {
			int[][] board = new int[nextBoard.length][nextBoard[0].length];

			for (int gen = 0; gen < numRounds; gen++) {

				// SO THAT WE CAN GENERALIZE LATER ON, TO N ROUNDS, where we
				// need to
				// copy board
				// from the previous iteration
				// Else this line is wasteful
				for (int i = 0; i < nextBoard.length; i++) {
					for (int j = 0; j < nextBoard[i].length; j++) {
						board[i][j] = nextBoard[i][j];
					}
				}
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board[i].length; j++) {
						if (board[i][j] == 1
								&& !(countSurrounding(board, i, j) == 2 || countSurrounding(board, i, j) == 3)) {
							nextBoard[i][j] = 0;
						} else if (board[i][j] == 0 && countSurrounding(board, i, j) == 3) {
							nextBoard[i][j] = 1;
						}
					}
				}
			}
			boardLife.printBoard(nextBoard);
		}
	}
}