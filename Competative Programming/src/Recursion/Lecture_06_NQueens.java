package Recursion;

public class Lecture_06_NQueens {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int row = 4;
		int col = 4;
		int noq = 4;
		System.out.println("1.");
		System.out.println(NQueen(new boolean[row][col], 0, 0, noq, ""));

		row_array = new boolean[row];
		col_array = new boolean[col];
		diagonal_array = new boolean[row + col - 1];
		antiDiagonal_array = new boolean[row + col - 1];

//		System.out.println("2.");
//		System.out.println(Optimised_NQueen(new boolean[row][col], 0, 0, noq, ""));
//		System.out.println("3.");
//		System.out.println(Optimised_NQueen_Bits(new boolean[row][col], 0, 0, noq, ""));
//		System.out.println("4.");
//		System.out.println(Optimised_NQueen_Bits_02(new boolean[row][col], 0, noq, ""));
//		System.out.println("5.");
//		System.out.println(Optimised_NQueen_Bits_02_Combination(new boolean[row + 2][col + 2], 0, noq, ""));
//		System.out.println("6.");
//		System.out.println(Optimised_NQueen_Bits_Subsequence(new boolean[row + 2][col + 2], 0, noq, ""));
//		System.out.println("7.");
//		System.out.println(Optimised_NQueen_Bits_02_Subsequence(new boolean[row + 2][col + 2], 0, noq, ""));

	}

	public static int NQueen(boolean[][] board, int idx, int qpsf, int tnq, String ans) {

		if (tnq == qpsf) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = idx; i < board.length * board[0].length; i++) {
			int row = i / board.length;
			int col = i % board.length;
			if (isValidPosition(board, row, col)) {
				board[row][col] = true;
				count += NQueen(board, i + 1, qpsf + 1, tnq,
						ans + "B:[" + row + "," + col + "] -> Q:" + (qpsf + 1) + " , ");
				board[row][col] = false;
			}
		}
		return count;
	}

	public static boolean isValidPosition(boolean[][] board, int row, int col) {

		for (int i = col - 1; i >= 0; i--) {
			if (board[row][i])
				return false;
		}
		for (int i = row - 1; i >= 0; i--) {
			if (board[i][col])
				return false;
		}
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j])
				return false;
		}
		for (int i = row - 1, j = col + 1; i >= 0 && j < board[row].length; i--, j++) {
			if (board[i][j])
				return false;
		}
		return true;
	}

	public static boolean[] row_array;
	public static boolean[] col_array;
	public static boolean[] diagonal_array;
	public static boolean[] antiDiagonal_array;

	public static int Optimised_NQueen(boolean[][] board, int idx, int qpsf, int tnq, String ans) {

		if (tnq == qpsf) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = idx; i < board.length * board[0].length; i++) {
			int row = i / board.length;
			int col = i % board.length;
			if (!row_array[row] && !col_array[col] && !diagonal_array[row - col + board[0].length - 1]
					&& !antiDiagonal_array[row + col]) {
				row_array[row] = true;
				col_array[col] = true;
				diagonal_array[row - col + board[0].length - 1] = true;
				antiDiagonal_array[row + col] = true;
				count += Optimised_NQueen(board, i + 1, qpsf + 1, tnq,
						ans + "B:[" + row + "," + col + "] -> Q:" + (qpsf + 1) + " , ");
				row_array[row] = false;
				col_array[col] = false;
				diagonal_array[row - col + board[0].length - 1] = false;
				antiDiagonal_array[row + col] = false;
			}
		}
		return count;
	}

	public static int row_board = 0;
	public static int col_board = 0;
	public static int diagonal_board = 0;
	public static int anti_diagonal_board = 0;

	public static int Optimised_NQueen_Bits(boolean[][] board, int idx, int qpsf, int tnq, String ans) {

		if (tnq == qpsf) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = idx; i < board.length * board[0].length; i++) {
			int row = i / board.length;
			int col = i % board.length;
			if ((row_board & (1 << row)) == 0 && (col_board & (1 << col)) == 0
					&& (diagonal_board & (1 << (row - col + board[0].length - 1))) == 0
					&& (anti_diagonal_board & (1 << (row + col))) == 0) {

				row_board ^= (1 << row);
				col_board ^= (1 << col);
				diagonal_board ^= (1 << row - col + board.length - 1);
				anti_diagonal_board ^= (1 << row + col);

				count += Optimised_NQueen_Bits(board, i + 1, qpsf + 1, tnq,
						ans + "B:[" + row + "," + col + "] -> Q:" + (qpsf + 1) + " , ");

				row_board ^= (1 << row);
				col_board ^= (1 << col);
				diagonal_board ^= (1 << row - col + board.length - 1);
				anti_diagonal_board ^= (1 << row + col);

			}
		}
		return count;
	}

	// Only when row == col == number of queens
	public static int Optimised_NQueen_Bits_02(boolean[][] board, int r, int tnq, String ans) {

		if (r == board.length || tnq == 0) {
			if (tnq == 0) {
				System.out.println(ans);
				return 1;
			}
			return 0;
		}

		int count = 0;
		for (int i = 0; i < board[r].length; i++) {
			int row = r;
			int col = i;
			if ((row_board & (1 << row)) == 0 && (col_board & (1 << col)) == 0
					&& (diagonal_board & (1 << (row - col + board[0].length - 1))) == 0
					&& (anti_diagonal_board & (1 << (row + col))) == 0) {

				row_board ^= (1 << row);
				col_board ^= (1 << col);
				diagonal_board ^= (1 << row - col + board.length - 1);
				anti_diagonal_board ^= (1 << row + col);

				count += Optimised_NQueen_Bits_02(board, r + 1, tnq - 1,
						ans + "B:[" + row + "," + col + "] -> Q:" + (board.length - tnq) + " , ");

				row_board ^= (1 << row);
				col_board ^= (1 << col);
				diagonal_board ^= (1 << row - col + board.length - 1);
				anti_diagonal_board ^= (1 << row + col);

			}
		}
		return count;
	}

	// If we have more number boxes and less number of queens
	public static int Optimised_NQueen_Bits_02_Combination(boolean[][] board, int r, int tnq, String ans) {

		if (r == board.length || tnq == 0) {
			if (tnq == 0) {
				System.out.println(ans);
				return 1;
			}
			return 0;
		}

		int count = 0;
		for (int house = r; house < board.length; house++) {
			for (int i = 0; i < board[0].length; i++) {
				int row = house;
				int col = i;
				if ((row_board & (1 << row)) == 0 && (col_board & (1 << col)) == 0
						&& (diagonal_board & (1 << (row - col + board[0].length - 1))) == 0
						&& (anti_diagonal_board & (1 << (row + col))) == 0) {

					row_board ^= (1 << row);
					col_board ^= (1 << col);
					diagonal_board ^= (1 << row - col + board.length - 1);
					anti_diagonal_board ^= (1 << row + col);

					count += Optimised_NQueen_Bits_02_Combination(board, house + 1, tnq - 1,
							ans + "B:[" + row + "," + col + "] -> Q:" + (board.length - tnq) + " , ");

					row_board ^= (1 << row);
					col_board ^= (1 << col);
					diagonal_board ^= (1 << row - col + board.length - 1);
					anti_diagonal_board ^= (1 << row + col);

				}
			}
		}
		return count;
	}

	public static int Optimised_NQueen_Bits_Subsequence(boolean[][] board, int idx, int tnq, String ans) {

		if (idx == board.length * board[0].length || tnq == 0) {
			if (tnq == 0) {
				System.out.println(ans);
				return 1;
			}
			return 0;
		}

		int count = 0;
		int row = idx / board.length;
		int col = idx % board.length;
		if ((row_board & (1 << row)) == 0 && (col_board & (1 << col)) == 0
				&& (diagonal_board & (1 << (row - col + board[0].length - 1))) == 0
				&& (anti_diagonal_board & (1 << (row + col))) == 0) {

			row_board ^= (1 << row);
			col_board ^= (1 << col);
			diagonal_board ^= (1 << row - col + board.length - 1);
			anti_diagonal_board ^= (1 << row + col);

			count += Optimised_NQueen_Bits_Subsequence(board, idx + 1, tnq - 1,
					ans + "B:[" + row + "," + col + "] -> Q:" + (board.length - tnq) + " , ");

			row_board ^= (1 << row);
			col_board ^= (1 << col);
			diagonal_board ^= (1 << row - col + board.length - 1);
			anti_diagonal_board ^= (1 << row + col);

		}
		count += Optimised_NQueen_Bits_Subsequence(board, idx + 1, tnq, ans);

		return count;
	}

	public static int Optimised_NQueen_Bits_02_Subsequence(boolean[][] board, int r, int tnq, String ans) {

		if (r == board.length || tnq == 0) {
			if (tnq == 0) {
				System.out.println(ans);
				return 1;
			}
			return 0;
		}

		int count = 0;
		for (int i = 0; i < board[0].length; i++) {
			int row = r;
			int col = i;
			if ((row_board & (1 << row)) == 0 && (col_board & (1 << col)) == 0
					&& (diagonal_board & (1 << (row - col + board[0].length - 1))) == 0
					&& (anti_diagonal_board & (1 << (row + col))) == 0) {

				row_board ^= (1 << row);
				col_board ^= (1 << col);
				diagonal_board ^= (1 << row - col + board.length - 1);
				anti_diagonal_board ^= (1 << row + col);

				count += Optimised_NQueen_Bits_02_Subsequence(board, r + 1, tnq - 1,
						ans + "B:[" + row + "," + col + "] -> Q:" + (board.length - tnq) + " , ");

				row_board ^= (1 << row);
				col_board ^= (1 << col);
				diagonal_board ^= (1 << row - col + board.length - 1);
				anti_diagonal_board ^= (1 << row + col);

			}
		}
		count += Optimised_NQueen_Bits_02_Subsequence(board, r + 1, tnq, ans);

		return count;
	}

}
