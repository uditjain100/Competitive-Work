package Recursion;

import java.util.HashSet;

public class Lecture_08_LeetCode {

	public static HashSet<String> map;

	public static void main(String[] args) {

		// Set01
		String[] list = { "like", "samsung", "i", "ilike", "sam", "sung", "and", "man", "go", "mango" };
		map = new HashSet<String>();
		for (String element : list) {
			map.add(element);
		}
		String word = "ilikesamsungandmango";
		System.out.println(WordBreak(word, ""));

		// Set02
		str1 = "send";
		str2 = "more";
		str3 = "money";
		String unique = findUniqueCharacter(str1, str2, str3);
		System.out.println(CryptoPuzzle(unique, 0));

		// Set03
		char[][] board = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
				{ '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
				{ '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
				{ '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
				{ '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
				{ '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' },
				{ '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
				{ '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
				{ '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
				{ '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } };
		System.out.println(CrossWord(0, board));

	}

	public static int WordBreak(String str, String ans) {
		if (str.length() == 0) {
			System.out.println(ans);
			return 1;
		}
		int count = 0;
		for (int i = 1; i <= str.length(); i++) {
			String smallstr = str.substring(0, i);
			if (map.contains(smallstr)) {
				count += WordBreak(str.substring(i), ans + smallstr + " ");
			}
		}
		return count;
	}

	public static String str1 = "";
	public static String str2 = "";
	public static String str3 = "";
	public static int numUsed = 0;
	public static int[] mapping = new int[26];

	public static int stringToNumber(String str) {
		int res = 0;
		for (int i = 0; i < str.length(); i++) {
			res = res * 10 + mapping[str.charAt(i) - 'a'];
		}
		return res;
	}

	public static String findUniqueCharacter(String str1, String str2, String str3) {

		String str = str1 + str2 + str3;
		int freq = 0;
		for (int i = 0; i < str.length(); i++) {
			freq |= (1 << (str.charAt(i) - 'a'));
		}
		str = "";
		for (int i = 0; i < 26; i++) {
			if ((freq & (1 << i)) != 0) {
				str += (char) (i + 'a');
			}
		}
		if (str.length() >= 10)
			return null;
		return str;
	}

	public static int CryptoPuzzle(String str, int idx) {

		if (idx == str.length()) {
			int num1 = stringToNumber(str1);
			int num2 = stringToNumber(str2);
			int num3 = stringToNumber(str3);
			if ((num1 + num2 == num3) && mapping[str1.charAt(0) - 'a'] != 0 && mapping[str2.charAt(0) - 'a'] != 0
					&& mapping[str3.charAt(0) - 'a'] != 0) {
				System.out.print(str1 + " -> " + num1 + " , ");
				System.out.print(str2 + " -> " + num2 + " , ");
				System.out.println(str3 + " -> " + num3);
				return 1;
			}
			return 0;
		}

		int count = 0;
		for (int num = 0; num < 10; num++) {
			int mask = (1 << num);
			if ((numUsed & mask) == 0) {
				numUsed ^= mask;
				mapping[str.charAt(idx) - 'a'] = num;
				count += CryptoPuzzle(str, idx + 1);
				numUsed ^= mask;
				mapping[str.charAt(idx) - 'a'] = 0;
			}
		}
		return count;
	}

	public static String[] words = { "agra", "norway", "england", "gwalior" };

	public static boolean canPlaceWordH(int row, int col, String word, char[][] board) {

		if (col == 0 && col + word.length() < board[row].length) {
			if (board[row][col + word.length()] != '+') {
				return false;
			}
		} else if (col + word.length() == board[row].length && word.length() != board[row].length) {
			if (board[row][col - 1] != '+') {
				return false;
			}
		} else {
			if ((col - 1 >= 0 && board[row][col - 1] != '+')
					|| (col + word.length() < board[row].length && board[row][col + word.length()] != '+')) {
				return false;
			}
		}

		for (int i = 0; i < word.length(); i++) {
			if ((col + i) < board[row].length && board[row][col + i] != '-' && board[row][col + i] != word.charAt(i)) {
				return false;
			}
		}
		return true;

	}

	public static boolean[] placeWordH(int row, int col, String word, char[][] board) {

		boolean[] mark = new boolean[word.length()];
		for (int i = 0; i < word.length(); i++) {
			if (board[row][col + i] == '-') {
				mark[i] = true;
				board[row][col + i] = word.charAt(i);
			}
		}

		return mark;

	}

	public static void unPlaceWordH(int row, int col, String word, boolean[] mark, char[][] board) {

		for (int i = 0; i < word.length(); i++) {
			if (mark[i])
				board[row][col + i] = '-';
		}

	}

	public static boolean canPlaceWordV(int row, int col, String word, char[][] board) {

		if (row == 0 && row + word.length() < board.length) {
			if (board[row + word.length()][col] != '+') {
				return false;
			}
		} else if (row + word.length() == board.length && word.length() != board.length) {
			if (board[row - 1][col] != '+') {
				return false;
			}
		} else {
			if ((row - 1 >= 0 && board[row - 1][col] != '+')
					|| (row + word.length() < board.length && board[row + word.length()][col] != '+')) {
				return false;
			}
		}

		for (int i = 0; i < word.length(); i++) {
			if ((row + i) < board.length && board[row + i][col] != '-' && board[row + i][col] != word.charAt(i)) {
				return false;
			}
		}
		return true;

	}

	public static boolean[] placeWordV(int row, int col, String word, char[][] board) {

		boolean[] mark = new boolean[word.length()];
		for (int i = 0; i < word.length(); i++) {
			if (board[row + i][col] == '-') {
				mark[i] = true;
				board[row + i][col] = word.charAt(i);
			}
		}

		return mark;

	}

	public static void unPlaceWordV(int row, int col, String word, boolean[] mark, char[][] board) {

		for (int i = 0; i < word.length(); i++) {
			if (mark[i])
				board[row + i][col] = '-';
		}
	}

	public static int CrossWord(int idx, char[][] board) {

		if (idx == words.length) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					System.out.print(board[i][j] + " , ");
				}
				System.out.println();
			}
			return 1;
		}

		int count = 0;
		String word = words[idx];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == '-' || board[i][j] == word.charAt(0)) {
					if (canPlaceWordH(i, j, word, board)) {
						boolean[] mark = placeWordH(i, j, word, board);
						count += CrossWord(idx + 1, board);
						unPlaceWordH(i, j, word, mark, board);
					}
					if (canPlaceWordV(i, j, word, board)) {
						boolean[] mark = placeWordV(i, j, word, board);
						count += CrossWord(idx + 1, board);
						unPlaceWordV(i, j, word, mark, board);
					}
				}
			}
		}
		return count;
	}

}
