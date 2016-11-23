import java.io.File;
import java.util.Scanner;

public class Board {

	private int[][] board;

	private void readFile(String fileName) {
		try {
			Scanner scanner = new Scanner(new File(fileName));
			String entries = "";
			while (scanner.hasNext()) {
				entries += String.valueOf(scanner.next());
			}
			int inputLength = entries.toCharArray().length;
			double rootOfLength = Math.sqrt(inputLength);
			// Since we have non-square input size
			if (rootOfLength - Math.floor(rootOfLength) > 0.0)
				System.err.println("Invalid Input");
			else
				parseInput(entries); // Parse into the 2d array
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	private void parseInput(String entries) {
		int numRowsOrCols = (int) Math.sqrt(entries.length());
		// initialize the board
		board = new int[numRowsOrCols][numRowsOrCols];
		for (int i = 0; i < numRowsOrCols; i++) {
			for (int j = 0; j < numRowsOrCols; j++) {
				board[i][j] = (int) entries.charAt(numRowsOrCols * i + j) - 48;
			}
		}
	}

	public void printBoard(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public int[][] getBoard(String path) {
		readFile(path);
		return board;
	}

}
