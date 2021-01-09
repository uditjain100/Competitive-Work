package Recursion;

import java.util.ArrayList;

public class Lecture_07_Sudoku {

	public static void main(String[] args) {
		
		// Set01
		int[][] arr = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
				{ 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
		System.out.println(Sudoku(arr, 0));
		System.out.println(isSudokuPossible(arr, 0));

		// Set02
		System.out.println(Optimised_Sudoku(arr, 0, makeList(arr)));

		row_array = new int[arr.length];
		col_array = new int[arr.length];
		cube = new int[(int) Math.sqrt(arr.length)][(int) Math.sqrt(arr.length)];
		set_masks(arr);
		System.out.println(Optimised_Sudoku_Bits(arr, 0, makeList(arr)));
		display(arr);

	}

	public static void display(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static boolean isSafeToPlace(int[][] board, int row, int col, int num) {

		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == num || board[row][i] == num) {
				return false;
			}
		}

		int x = (row / (int) Math.sqrt(board.length)) * (int) Math.sqrt(board.length);
		int y = (col / (int) Math.sqrt(board.length)) * (int) Math.sqrt(board.length);

		for (int i = x; i < x + (int) Math.sqrt(board.length); i++) {
			for (int j = y; j < y + (int) Math.sqrt(board.length); j++) {
				if (board[i][j] == num) {
					return false;
				}
			}
		}

		return true;
	}

	public static int Sudoku(int[][] board, int idx) {

		if (idx == board.length * board[0].length) {
			display(board);
			return 1;
		}

		int count = 0;
		int row = idx / board.length;
		int col = idx % board[row].length;
		if (board[row][col] != 0) {
			count += Sudoku(board, idx + 1);
		} else {
			for (int num = 1; num <= board.length; num++) {
				if (isSafeToPlace(board, row, col, num)) {
					board[row][col] = num;
					count += Sudoku(board, idx + 1);
					board[row][col] = 0;
				}
			}
		}
		return count;
	}

	// For Single Answer
	public static boolean isSudokuPossible(int[][] board, int idx) {

		if (idx == board.length * board[0].length) {
			display(board);
			return true;
		}

		boolean res = false;
		int row = idx / board.length;
		int col = idx % board[row].length;
		if (board[row][col] != 0) {
			res = res || isSudokuPossible(board, idx + 1);
		} else {
			for (int num = 1; num <= board.length; num++) {
				if (isSafeToPlace(board, row, col, num)) {
					board[row][col] = num;
					res = res || isSudokuPossible(board, idx + 1);
					board[row][col] = 0;
				}
			}
		}
		return res;
	}

	public static ArrayList<Integer> makeList(int[][] arr) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == 0)
					list.add((i * arr.length) + j);
			}
		}
		return list;
	}

	public static int Optimised_Sudoku(int[][] board, int idx, ArrayList<Integer> list) {

		if (idx == list.size()) {
			display(board);
			return 1;
		}

		int count = 0;
		int row = list.get(idx) / board.length;
		int col = list.get(idx) % board[row].length;
		for (int num = 1; num <= board.length; num++) {
			if (isSafeToPlace(board, row, col, num)) {
				board[row][col] = num;
				count += Optimised_Sudoku(board, idx + 1, list);
				board[row][col] = 0;
			}
		}
		return count;
	}

	public static void set_masks(int[][] arr) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] != 0) {
					int mask = (1 << arr[i][j]);
					row_array[i] ^= mask;
					col_array[j] ^= mask;
					cube[i / (int) Math.sqrt(arr.length)][j / (int) Math.sqrt(arr.length)] ^= mask;
				}
			}
		}

	}

	public static int[] row_array;
	public static int[] col_array;
	public static int[][] cube;

	public static int Optimised_Sudoku_Bits(int[][] board, int idx, ArrayList<Integer> list) {

		if (idx == list.size()) {
			display(board);
			return 1;
		}

		int count = 0;
		int row = list.get(idx) / board.length;
		int col = list.get(idx) % board[row].length;
		for (int num = 1; num <= board.length; num++) {
			int mask = (1 << num);
			if ((row_array[row] & mask) == 0 && (col_array[col] & mask) == 0
					&& (cube[row / (int) Math.sqrt(board.length)][col / (int) Math.sqrt(board.length)] & mask) == 0) {
				board[row][col] = num;
				row_array[row] ^= mask;
				col_array[col] ^= mask;
				cube[row / (int) Math.sqrt(board.length)][col / (int) Math.sqrt(board.length)] ^= mask;
				count += Optimised_Sudoku_Bits(board, idx + 1, list);
				board[row][col] = 0;
				row_array[row] ^= mask;
				col_array[col] ^= mask;
				cube[row / (int) Math.sqrt(board.length)][col / (int) Math.sqrt(board.length)] ^= mask;
			}
		}
		return count;
	}

}
