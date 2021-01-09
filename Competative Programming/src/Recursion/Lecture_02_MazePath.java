package Recursion;

import java.util.ArrayList;

public class Lecture_02_MazePath {

	public static void main(String[] args) {
		
//		// Set01
//		System.out.println(get_MazePath(1, 1, 2, 2));
//		print_MazePath(1, 1, 2, 2, "");
//		System.out.println(num_MazePath(1, 1, 2, 2, ""));
//		System.out.println("****************************");
//
//		// Set02
//		System.out.println(get_MazePath_Dia(1, 1, 4, 4));
//		print_MazePath_Dia(1, 1, 4, 4, "");
//		System.out.println(num_MazePath_Dia(1, 1, 4, 4, ""));
//		System.out.println("****************************");
//
//		// Set03
//		System.out.println(get_MazePath_MultiMove(1, 1, 4, 4));
//		print_MazePath_MultiMove(1, 1, 4, 4, "");
//		System.out.println(num_MazePath_MultiMove(1, 1, 4, 4, ""));
//		System.out.println("****************************");
//
//		// Set04
		int r = 3;
		int c = 3;
		boolean[][] board = new boolean[r][c];
//		System.out.println(get_MazePath_8_Dir(1, 1, r, c, board) + "  " + get_MazePath_8_Dir(1, 1, r, c, board).size());
//		print_MazePath_8_Dir(1, 1, r, c, board, "");
//		System.out.println(num_MazePath_8_Dir(1, 1, r, c, board, ""));
//		System.out.println("****************************");
//
//		// Set05 (FLOOD_FILL)
//		System.out.println(get_MazePath_8_Dir_MultiMove(1, 1, r, c, board) + "  "
//				+ get_MazePath_8_Dir_MultiMove(1, 1, r, c, board).size());
//		print_MazePath_8_Dir_MultiMove(1, 1, r, c, board, "");
//		System.out.println(num_MazePath_8_Dir_MultiMove(1, 1, r, c, board, ""));
//		System.out.println("****************************");
//
		// Set06
		int h1 = get_height_of_MazePath_8_Dir_MultiMover(get_MazePath_8_Dir_MultiMove(1, 1, r, c, board));
		int h2 = print_height_of_MazePath_8_Dir_MultiMover(1, 1, r, c, board);
		int i1 = get_maxlen_of_MazePath_8_Dir_MultiMover(get_MazePath_8_Dir_MultiMove(1, 1, r, c, board));
		pair i2 = print_maxlen_of_MazePath_8_Dir_MultiMover(1, 1, r, c, board);
		int j1 = get_minlen_of_MazePath_8_Dir_MultiMover(get_MazePath_8_Dir_MultiMove(1, 1, r, c, board));
		pair j2 = print_minlen_of_MazePath_8_Dir_MultiMover(1, 1, r, c, board);
		System.out.println(h1 / 2 + " " + h2 + " " + i1 / 2 + " " + i2.path + " " + i2.len + " " + j1 / 2 + " "
				+ j2.path + " " + j2.len);

	}

	public static ArrayList<String> get_MazePath(int cr, int cc, int er, int ec) {

		if (cr == er && cc == ec) {
			ArrayList<String> br = new ArrayList<String>();
			br.add("");
			return br;
		}

		if (cc > ec || cr > er) {
			ArrayList<String> br = new ArrayList<String>();
			return br;
		}

		ArrayList<String> mr = new ArrayList<String>();
		ArrayList<String> rrh = get_MazePath(cr, cc + 1, er, ec);

		for (String key : rrh) {
			mr.add(key + "H");
		}

		ArrayList<String> rrv = get_MazePath(cr + 1, cc, er, ec);

		for (String key : rrv) {
			mr.add(key + "V");
		}

		return mr;

	}

	public static void print_MazePath(int cr, int cc, int er, int ec, String ans) {

		if (cc == ec && cr == er) {
			System.out.print(ans + " , ");
			return;
		}

		if (cc > ec || cr > er) {
			return;
		}

		print_MazePath(cr, cc + 1, er, ec, ans + "H");
		print_MazePath(cr + 1, cc, er, ec, ans + "V");

	}

	public static int num_MazePath(int cr, int cc, int er, int ec, String ans) {

		if (cc == ec && cr == er) {
			return 1;
		}

		if (cc > ec || cr > er) {
			return 0;
		}

		int count = 0;
		count += num_MazePath(cr, cc + 1, er, ec, ans + "H");
		count += num_MazePath(cr + 1, cc, er, ec, ans + "V");

		return count;

	}

	public static ArrayList<String> get_MazePath_Dia(int cr, int cc, int er, int ec) {

		if (cr == er && cc == ec) {
			ArrayList<String> br = new ArrayList<String>();
			br.add("");
			return br;
		}

		if (cc > ec || cr > er) {
			ArrayList<String> br = new ArrayList<String>();
			return br;
		}

		ArrayList<String> mr = new ArrayList<String>();

		ArrayList<String> rrh = get_MazePath_Dia(cr, cc + 1, er, ec);
		for (String key : rrh) {
			mr.add(key + "H");
		}

		ArrayList<String> rrv = get_MazePath_Dia(cr + 1, cc, er, ec);
		for (String key : rrv) {
			mr.add(key + "V");
		}

		if (cc == cr || cc + cr == er) {
			ArrayList<String> rrd = get_MazePath_Dia(cr + 1, cc + 1, er, ec);
			for (String key : rrd) {
				mr.add(key + "D");
			}
		}

		return mr;

	}

	public static void print_MazePath_Dia(int cr, int cc, int er, int ec, String ans) {

		if (cc == ec && cr == er) {
			System.out.print(ans + " , ");
			return;
		}

		if (cc > ec || cr > er) {
			return;
		}

		print_MazePath_Dia(cr, cc + 1, er, ec, ans + "H");
		print_MazePath_Dia(cr + 1, cc, er, ec, ans + "V");

		if (cc == cr || cc + cr == er) {
			print_MazePath_Dia(cr + 1, cc + 1, er, ec, ans + "D");
		}

	}

	public static int num_MazePath_Dia(int cr, int cc, int er, int ec, String ans) {

		if (cc == ec && cr == er) {
			return 1;
		}

		if (cc > ec || cr > er) {
			return 0;
		}

		int count = 0;
		count += num_MazePath_Dia(cr, cc + 1, er, ec, ans + "H");
		count += num_MazePath_Dia(cr + 1, cc, er, ec, ans + "V");

		if (cc == cr || cc + cr == er) {
			count += num_MazePath_Dia(cr + 1, cc + 1, er, ec, ans + "D");
		}

		return count;

	}

	public static ArrayList<String> get_MazePath_MultiMove(int cr, int cc, int er, int ec) {

		if (cr == er && cc == ec) {
			ArrayList<String> br = new ArrayList<String>();
			br.add("");
			return br;
		}

		if (cc > ec || cr > er) {
			ArrayList<String> br = new ArrayList<String>();
			return br;
		}

		ArrayList<String> mr = new ArrayList<String>();

		for (int i = 1; i < ec; i++) {
			ArrayList<String> rrh = get_MazePath_MultiMove(cr, cc + i, er, ec);

			for (String key : rrh) {
				mr.add(key + "H" + i);
			}
		}
		for (int i = 1; i < er; i++) {
			ArrayList<String> rrv = get_MazePath_MultiMove(cr + i, cc, er, ec);

			for (String key : rrv) {
				mr.add(key + "V" + i);
			}
		}
		for (int i = 1; i < er && i < ec; i++) {
			if (cc == cr || cc + cr == er) {
				ArrayList<String> rrd = get_MazePath_MultiMove(cr + i, cc + 1, er, ec);
				for (String key : rrd) {
					mr.add(key + "D" + i);
				}
			}
		}

		return mr;

	}

	public static void print_MazePath_MultiMove(int cr, int cc, int er, int ec, String ans) {

		if (cc == ec && cr == er) {
			System.out.print(ans + " , ");
			return;
		}

		if (cc > ec || cr > er) {
			return;
		}

		for (int i = 1; i < ec; i++) {
			print_MazePath_MultiMove(cr, cc + i, er, ec, ans + "H" + i);
		}
		for (int i = 1; i < er; i++) {

			print_MazePath_MultiMove(cr + i, cc, er, ec, ans + "V" + i);
		}
		for (int i = 1; i < er && i < ec; i++) {
			if (cc == cr || cc + cr == er) {
				print_MazePath_MultiMove(cr + i, cc + i, er, ec, ans + "D" + i);
			}
		}
	}

	public static int num_MazePath_MultiMove(int cr, int cc, int er, int ec, String ans) {

		if (cc == ec && cr == er) {
			return 1;
		}

		if (cc > ec || cr > er) {
			return 0;
		}

		int count = 0;
		for (int i = 1; i < ec; i++) {
			count += num_MazePath_MultiMove(cr, cc + i, er, ec, ans + "H" + i);
		}
		for (int i = 1; i < er; i++) {
			count += num_MazePath_MultiMove(cr + i, cc, er, ec, ans + "V" + i);
		}
		for (int i = 1; i < er && i < ec; i++) {
			if (cc == cr || cc + cr == er) {
				count += num_MazePath_MultiMove(cr + i, cc + i, er, ec, ans + "D" + i);
			}
		}
		return count;

	}

	public static boolean isValid(int cc, int cr, boolean[][] board) {

		if (cr < 1 || cc < 1 || cr > board.length || cc > board[cr - 1].length || board[cr - 1][cc - 1]) {
			return false;
		}

		return true;

	}

	public static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { -1, -1 }, { 1, -1 },
			{ -1, 1 } };
	public static String[] dir_res = { "D", "R", "U", "L", "S", "N", "E", "W" };

	public static ArrayList<String> get_MazePath_8_Dir(int cr, int cc, int er, int ec, boolean[][] board) {

		if (cc == ec && cr == er) {
			ArrayList<String> br = new ArrayList<String>();
			br.add("");
			return br;
		}

		ArrayList<String> mr = new ArrayList<String>();

		board[cr - 1][cc - 1] = true;

		for (int i = 0; i < 8; i++) {
			if (isValid(cc + dir[i][0], cr + dir[i][1], board)) {
				ArrayList<String> rr = get_MazePath_8_Dir(cr + dir[i][1], cc + dir[i][0], er, ec, board);
				for (String key : rr) {
					mr.add(key + dir_res[i]);
				}
			}
		}

		board[cr - 1][cc - 1] = false;

		return mr;

	}

	public static void print_MazePath_8_Dir(int cr, int cc, int er, int ec, boolean[][] board, String ans) {

		if (cc == ec && cr == er) {
			System.out.print(ans + " , ");
			return;
		}

		board[cr - 1][cc - 1] = true;

		for (int i = 0; i < 8; i++) {
			if (isValid(cc + dir[i][0], cr + dir[i][1], board)) {
				print_MazePath_8_Dir(cr + dir[i][1], cc + dir[i][0], er, ec, board, ans + dir_res[i]);
			}
		}

		board[cr - 1][cc - 1] = false;

	}

	public static int num_MazePath_8_Dir(int cr, int cc, int er, int ec, boolean[][] board, String ans) {

		if (cc == ec && cr == er) {
			return 1;
		}

		int count = 0;
		board[cr - 1][cc - 1] = true;

		for (int i = 0; i < 8; i++) {
			if (isValid(cc + dir[i][0], cr + dir[i][1], board)) {
				count += num_MazePath_8_Dir(cr + dir[i][1], cc + dir[i][0], er, ec, board, ans + dir_res[i]);
			}
		}

		board[cr - 1][cc - 1] = false;
		return count;
	}

	public static ArrayList<String> get_MazePath_8_Dir_MultiMove(int cr, int cc, int er, int ec, boolean[][] board) {

		if (cc == ec && cr == er) {
			ArrayList<String> br = new ArrayList<String>();
			br.add("");
			return br;
		}

		ArrayList<String> mr = new ArrayList<String>();

		board[cr - 1][cc - 1] = true;

		for (int moves = 1; moves < er && moves < ec; moves++) {
			for (int i = 0; i < 8; i++) {
				if (isValid(cc + moves * dir[i][0], cr + moves * dir[i][1], board)) {
					ArrayList<String> rr = get_MazePath_8_Dir_MultiMove(cr + moves * dir[i][1], cc + moves * dir[i][0],
							er, ec, board);
					for (String key : rr) {
						mr.add(key + dir_res[i] + moves);
					}
				}
			}
		}

		board[cr - 1][cc - 1] = false;

		return mr;

	}

	public static void print_MazePath_8_Dir_MultiMove(int cr, int cc, int er, int ec, boolean[][] board, String ans) {

		if (cc == ec && cr == er) {
			System.out.print(ans + " , ");
			return;
		}

		board[cr - 1][cc - 1] = true;

		for (int moves = 1; moves < er && moves < ec; moves++) {
			for (int i = 0; i < 8; i++) {
				if (isValid(cc + moves * dir[i][0], cr + moves * dir[i][1], board)) {
					print_MazePath_8_Dir_MultiMove(cr + moves * dir[i][1], cc + moves * dir[i][0], er, ec, board,
							ans + dir_res[i] + moves);
				}
			}
		}

		board[cr - 1][cc - 1] = false;

	}

	public static int num_MazePath_8_Dir_MultiMove(int cr, int cc, int er, int ec, boolean[][] board, String ans) {

		if (cc == ec && cr == er) {
			return 1;
		}

		int count = 0;
		board[cr - 1][cc - 1] = true;

		for (int moves = 1; moves < er && moves < ec; moves++) {
			for (int i = 0; i < 8; i++) {
				if (isValid(cc + moves * dir[i][0], cr + moves * dir[i][1], board)) {
					count += num_MazePath_8_Dir_MultiMove(cr + moves * dir[i][1], cc + moves * dir[i][0], er, ec, board,
							ans + dir_res[i] + moves);
				}
			}
		}

		board[cr - 1][cc - 1] = false;
		return count;
	}

	public static int get_height_of_MazePath_8_Dir_MultiMover(ArrayList<String> list) {

		int max = Integer.MIN_VALUE;
		for (String key : list) {
			if (key.length() > max)
				max = key.length();
		}
		return max;
	}

	public static int print_height_of_MazePath_8_Dir_MultiMover(int cr, int cc, int er, int ec, boolean[][] board) {

		if (cc == ec && cr == er) {
			return 0;
		}

		int height = 0;
		board[cr - 1][cc - 1] = true;
		for (int moves = 1; moves < er && moves < ec; moves++) {
			for (int i = 0; i < 8; i++) {
				if (isValid(cc + moves * dir[i][0], cr + moves * dir[i][1], board)) {
					int rh = print_height_of_MazePath_8_Dir_MultiMover(cr + moves * dir[i][1], cc + moves * dir[i][0],
							er, ec, board);
					height = Math.max(height, rh);
				}
			}
		}
		board[cr - 1][cc - 1] = false;
		return height + 1;
	}

	public static int get_maxlen_of_MazePath_8_Dir_MultiMover(ArrayList<String> list) {

		int max = Integer.MIN_VALUE;
		for (String key : list) {
			if (key.length() > max)
				max = key.length();
		}
		return max;
	}

	public static class pair {
		String path;
		int len;

		public pair(String path, int len) {
			this.path = path;
			this.len = len;
		}
	}

	public static pair print_maxlen_of_MazePath_8_Dir_MultiMover(int cr, int cc, int er, int ec, boolean[][] board) {

		if (cc == ec && cr == er) {
			pair bp = new pair("", 0);
			return bp;
		}

		pair ap = new pair("", 0);
		board[cr - 1][cc - 1] = true;
		for (int moves = 1; moves < er && moves < ec; moves++) {
			for (int i = 0; i < 8; i++) {
				if (isValid(cc + moves * dir[i][0], cr + moves * dir[i][1], board)) {
					pair rp = print_maxlen_of_MazePath_8_Dir_MultiMover(cr + moves * dir[i][1], cc + moves * dir[i][0],
							er, ec, board);
					if (rp.len + 1 > ap.len) {
						ap.len = rp.len + 1;
						ap.path = rp.path + dir_res[i] + moves;
					}
				}
			}
		}
		board[cr - 1][cc - 1] = false;
		return ap;
	}

	public static int get_minlen_of_MazePath_8_Dir_MultiMover(ArrayList<String> list) {

		int min = Integer.MAX_VALUE;
		for (String key : list) {
			if (key.length() < min)
				min = key.length();
		}
		return min;
	}

	public static pair print_minlen_of_MazePath_8_Dir_MultiMover(int cr, int cc, int er, int ec, boolean[][] board) {

		if (cc == ec && cr == er) {
			pair bp = new pair("", 0);
			return bp;
		}

		pair ap = new pair("", Integer.MAX_VALUE);
		board[cr - 1][cc - 1] = true;
		for (int moves = 1; moves < er && moves < ec; moves++) {
			for (int i = 0; i < 8; i++) {
				if (isValid(cc + moves * dir[i][0], cr + moves * dir[i][1], board)) {
					pair rp = print_minlen_of_MazePath_8_Dir_MultiMover(cr + moves * dir[i][1], cc + moves * dir[i][0],
							er, ec, board);
					if (rp.len != Integer.MAX_VALUE && rp.len + 1 < ap.len) {
						ap.len = rp.len + 1;
						ap.path = rp.path + dir_res[i] + moves;
					}
				}
			}
		}
		board[cr - 1][cc - 1] = false;
		return ap;
	}

}
