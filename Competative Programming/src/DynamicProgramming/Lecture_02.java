package DynamicProgramming;

public class Lecture_02 {

	public static void main(String[] args) {

		System.out.println(friendsPairing(10));
		System.out.println(friendsPairingDP(10));
		System.out.println(friendsPairingTable(10));

		System.out.println(goldMine());
		System.out.println(goldMineDP());
		System.out.println(goldMineTable());

	}

	public static int friendsPairing(int n) {
		if (n <= 1)
			return 1;
		int single = friendsPairing(n - 1);
		int pairUp = friendsPairing(n - 2) * (n - 1);
		return single + pairUp;
	}

	public static int[] fp = new int[100000];

	public static int friendsPairingDP(int n) {
		if (n <= 1)
			return 1;
		if (fp[n] != 0)
			return fp[n];
		int single = friendsPairingDP(n - 1);
		int pairUp = friendsPairingDP(n - 2) * (n - 1);
		return fp[n] = single + pairUp;
	}

	public static int friendsPairingTable(int n) {
		int[] fp = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			if (i <= 1) {
				fp[i] = 1;
				continue;
			}
			int single = fp[i - 1];
			int pairUp = fp[i - 2] * (i - 1);
			fp[i] = single + pairUp;
		}
		return fp[n];
	}

	public static int minPath(int[][] arr, int sr, int sc, int er, int ec) {

		if (sr == er && sc == ec)
			return arr[sr][sc];

		int cost = Integer.MAX_VALUE;
		int cmd = Integer.MAX_VALUE;
		int cmr = Integer.MAX_VALUE;

		if (sr + 1 <= er)
			cmd = minPath(arr, sr + 1, sc, er, ec);
		if (sc + 1 <= ec)
			cmr = minPath(arr, sr, sc + 1, er, ec);

		cost = Math.min(cmr, cmd);

		return cost + arr[sr][sc];
	}

	public static int[][] mp = new int[100][100];

	public static int minPathDP(int[][] arr, int sr, int sc, int er, int ec) {

		if (sr == er && sc == ec)
			return arr[sr][sc];

		if (mp[sr][sc] != 0)
			return mp[sr][sc];

		int cost = Integer.MAX_VALUE;
		int cmd = Integer.MAX_VALUE;
		int cmr = Integer.MAX_VALUE;

		if (sr + 1 <= er)
			cmd = minPathDP(arr, sr + 1, sc, er, ec);
		if (sc + 1 <= ec)
			cmr = minPathDP(arr, sr, sc + 1, er, ec);

		cost = Math.min(cmr, cmd);

		return mp[sr][sc] = cost + arr[sr][sc];
	}

	public static int minPathTable(int[][] arr, int er, int ec) {

		int[][] mp = new int[er + 1][ec + 1];
		for (int sr = er; sr >= 0; sr--) {
			for (int sc = ec; sc >= 0; sc--) {
				if (sr == er && sc == ec) {
					mp[sr][sc] = arr[sr][sc];
					continue;
				}

				int cmd = Integer.MAX_VALUE;
				int cmr = Integer.MAX_VALUE;
				if (sr + 1 <= er)
					cmd = mp[sr + 1][sc];
				if (sc + 1 <= ec)
					cmr = mp[sr][sc + 1];
				mp[sr][sc] = Math.min(cmr, cmd) + arr[sr][sc];
			}
		}
		return mp[0][0];
	}

	public static int goldMine() {
		int arr[][] = { { 1, 3, 1, 5 }, { 2, 2, 4, 1 }, { 5, 0, 2, 3 }, { 0, 6, 1, 2 } };
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, goldMineProblem(arr, i, 0, arr.length - 1, arr[0].length - 1));
		}
		return max;
	}

	public static int goldMineProblem(int[][] arr, int sr, int sc, int er, int ec) {

		if (sc == ec) {
			return arr[sr][sc];
		}

		int gold = Integer.MIN_VALUE;
		int a = Integer.MIN_VALUE;
		int b = Integer.MIN_VALUE;
		int c = Integer.MIN_VALUE;

		if (sr + 1 <= er && sc + 1 <= ec)
			a = goldMineProblem(arr, sr + 1, sc + 1, er, ec);
		if (sr - 1 >= 0 && sc + 1 <= ec)
			b = goldMineProblem(arr, sr - 1, sc + 1, er, ec);
		if (sc + 1 <= ec)
			c = goldMineProblem(arr, sr, sc + 1, er, ec);
		gold = Math.max(a, Math.max(b, c));
		return gold + arr[sr][sc];
	}

	public static int goldMineDP() {
		int arr[][] = { { 1, 3, 1, 5 }, { 2, 2, 4, 1 }, { 5, 0, 2, 3 }, { 0, 6, 1, 2 } };
		int[][] gm = new int[arr.length][arr[0].length];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, goldMineProblemDP(arr, i, 0, arr.length - 1, arr[0].length - 1, gm));
		}
		return max;
	}

	public static int goldMineProblemDP(int[][] arr, int sr, int sc, int er, int ec, int[][] gm) {

		if (sc == ec) {
			return arr[sr][sc];
		}

		if (gm[sr][sc] != 0)
			return gm[sr][sc];

		int gold = Integer.MIN_VALUE;
		int a = Integer.MIN_VALUE;
		int b = Integer.MIN_VALUE;
		int c = Integer.MIN_VALUE;

		if (sr + 1 <= er && sc + 1 <= ec)
			a = goldMineProblemDP(arr, sr + 1, sc + 1, er, ec, gm);
		if (sr - 1 >= 0 && sc + 1 <= ec)
			b = goldMineProblemDP(arr, sr - 1, sc + 1, er, ec, gm);
		if (sc + 1 <= ec)
			c = goldMineProblemDP(arr, sr, sc + 1, er, ec, gm);
		gold = Math.max(a, Math.max(b, c));
		return gm[sr][sc] = gold + arr[sr][sc];
	}

	public static int goldMineTable() {
		int arr[][] = { { 1, 3, 1, 5 }, { 2, 2, 4, 1 }, { 5, 0, 2, 3 }, { 0, 6, 1, 2 } };
		return goldMineProblemTable(arr, arr.length - 1, arr[0].length - 1);
	}

	public static int goldMineProblemTable(int[][] arr, int er, int ec) {

		int[][] gm = new int[er + 1][ec + 1];

		for (int sc = ec; sc >= 0; sc--) {
			for (int sr = er; sr >= 0; sr--) {
				if (sc == ec) {
					gm[sr][sc] = arr[sr][sc];
					continue;
				}

				int gold = Integer.MIN_VALUE;
				int a = Integer.MIN_VALUE;
				int b = Integer.MIN_VALUE;
				int c = Integer.MIN_VALUE;

				if (sr + 1 <= er && sc + 1 <= ec)
					a = gm[sr + 1][sc + 1];
				if (sr - 1 >= 0 && sc + 1 <= ec)
					b = gm[sr - 1][sc + 1];
				if (sc + 1 <= ec)
					c = gm[sr][sc + 1];
				gold = Math.max(a, Math.max(b, c));
				gm[sr][sc] = gold + arr[sr][sc];
			}
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < gm.length; i++) {
			max = Math.max(max, gm[i][0]);
		}
		return max;
	}

}
