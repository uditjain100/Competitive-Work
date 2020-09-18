package DynamicProgramming;

public class Lecture_02 {

	public static void main(String[] args) {

		// System.out.println(friendsPairing(10));
		// int n = 10;
		// int[] fp = new int[n + 1];
		// System.out.println(friendsPairingDP(n, fp));
		// display(fp);
		// System.out.println(friendsPairingTable(10));

		// System.out.println(goldMine());
		// System.out.println(goldMineDP());
		// System.out.println(goldMineTable());

		// System.out.println(setIntoSubsets(5, 3));
		// System.out.println(setIntoSubsetsDP(5, 3));
		// System.out.println(setIntoSubsetsTable(5, 3));

		int[][] lcs = new int[8][8];
		System.out.println(longestPalindromeSubsequenceDP("aabbccaa", 0, 7, lcs, isSubstringPalindrome("aabbccaa")));
		display(lcs);

		// System.out.println(countAllPalindromeSubsequence("abcd", 0, 3));
		// System.out.println(countAllPalindromeSubsequenceDP("abcd", 0, 3, new
		// int[4][4]));
		// System.out.println(countAllPalindromeSubsequenceTable("abcd"));

	}

	public static void display(int[] arr) {
		for (int ele : arr)
			System.out.print(ele + ", ");
		System.out.println();
	}

	public static void display(int[][] arr) {
		for (int[] a : arr) {
			for (int ele : a)
				System.out.print(ele + ", ");
			System.out.println();
		}
		System.out.println();
	}

	public static int friendsPairing(int n) {
		if (n == 0 || n == 1)
			return 1;
		int single = friendsPairing(n - 1);
		int pairUp = friendsPairing(n - 2) * (n - 1);
		return single + pairUp;
	}

	public static int friendsPairingDP(int n, int[] fp) {
		if (n == 0 || n == 1)
			return fp[n] = 1;
		if (fp[n] != 0)
			return fp[n];
		int single = friendsPairingDP(n - 1, fp);
		int pairUp = friendsPairingDP(n - 2, fp) * (n - 1);
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

		int cmd = Integer.MAX_VALUE;
		int cmr = Integer.MAX_VALUE;

		if (sr + 1 <= er)
			cmd = minPath(arr, sr + 1, sc, er, ec);
		if (sc + 1 <= ec)
			cmr = minPath(arr, sr, sc + 1, er, ec);

		return Math.min(cmr, cmd) + arr[sr][sc];
	}

	public static int minPathDP(int[][] arr, int sr, int sc, int er, int ec, int[][] mp) {
		if (sr == er && sc == ec)
			return arr[sr][sc];
		if (mp[sr][sc] != 0)
			return mp[sr][sc];

		int cmd = Integer.MAX_VALUE;
		int cmr = Integer.MAX_VALUE;

		if (sr + 1 <= er)
			cmd = minPathDP(arr, sr + 1, sc, er, ec, mp);
		if (sc + 1 <= ec)
			cmr = minPathDP(arr, sr, sc + 1, er, ec, mp);

		return mp[sr][sc] = Math.min(cmr, cmd) + arr[sr][sc];
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

		if (sc == ec)
			return arr[sr][sc];

		int a = Integer.MIN_VALUE;
		int b = Integer.MIN_VALUE;
		int c = Integer.MIN_VALUE;

		if (sr + 1 <= er && sc + 1 <= ec)
			a = goldMineProblem(arr, sr + 1, sc + 1, er, ec);
		if (sr - 1 >= 0 && sc + 1 <= ec)
			b = goldMineProblem(arr, sr - 1, sc + 1, er, ec);
		if (sc + 1 <= ec)
			c = goldMineProblem(arr, sr, sc + 1, er, ec);
		return Math.max(a, Math.max(b, c)) + arr[sr][sc];
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
		if (sc == ec)
			return arr[sr][sc];

		if (gm[sr][sc] != 0)
			return gm[sr][sc];

		int a = Integer.MIN_VALUE;
		int b = Integer.MIN_VALUE;
		int c = Integer.MIN_VALUE;

		if (sr + 1 <= er && sc + 1 <= ec)
			a = goldMineProblemDP(arr, sr + 1, sc + 1, er, ec, gm);
		if (sr - 1 >= 0 && sc + 1 <= ec)
			b = goldMineProblemDP(arr, sr - 1, sc + 1, er, ec, gm);
		if (sc + 1 <= ec)
			c = goldMineProblemDP(arr, sr, sc + 1, er, ec, gm);
		return gm[sr][sc] = Math.max(a, Math.max(b, c)) + arr[sr][sc];
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

				int a = Integer.MIN_VALUE;
				int b = Integer.MIN_VALUE;
				int c = Integer.MIN_VALUE;

				if (sr + 1 <= er && sc + 1 <= ec)
					a = gm[sr + 1][sc + 1];
				if (sr - 1 >= 0 && sc + 1 <= ec)
					b = gm[sr - 1][sc + 1];
				if (sc + 1 <= ec)
					c = gm[sr][sc + 1];
				gm[sr][sc] = Math.max(a, Math.max(b, c)) + arr[sr][sc];
			}
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < gm.length; i++) {
			max = Math.max(max, gm[i][0]);
		}
		return max;
	}

	public static int setIntoSubsets(int n, int k) {

		if (n == 0 || k == 0 || k > n)
			return 0;

		if (k == 1 || n == k)
			return 1;

		int count = 0;
		count += setIntoSubsets(n - 1, k - 1);
		count += setIntoSubsets(n - 1, k) * k;
		return count;
	}

	public static int setIntoSubsetsDP(int n, int k, int[][] sis) {
		if (n == 0 || k == 0 || k > n)
			return 0;

		if (k == 1 || n == k)
			return sis[k][n] = 1;

		if (sis[k][n] != 0)
			return sis[k][n];

		int count = 0;
		count += setIntoSubsetsDP(n - 1, k - 1, sis);
		count += setIntoSubsetsDP(n - 1, k, sis) * k;
		return sis[k][n] = count;
	}

	public static int setIntoSubsetsTable(int n, int k) {
		int[][] sis = new int[k + 1][n + 1];
		for (int i = 0; i <= k; i++) {
			for (int j = 0; j <= n; j++) {
				if (j == 0 || i == 0 || i > j) {
					sis[i][j] = 0;
					continue;
				}

				if (i == 1 || i == j) {
					sis[i][j] = 1;
					continue;
				}

				int count = 0;
				if (i - 1 >= 0 && j - 1 >= 0)
					count += sis[i - 1][j - 1];
				if (i - 1 >= 0)
					count += sis[i][j - 1] * i;
				sis[i][j] = count;

			}
		}
		return sis[k][n];
	}

	public static boolean[][] isSubstringPalindrome(String str) {
		boolean[][] sp = new boolean[str.length()][str.length()];
		for (int gap = 0; gap < str.length(); gap++) {
			for (int i = 0, j = gap; j < str.length(); i++, j++) {
				if (gap == 0)
					sp[i][j] = true;
				else if (gap == 1 && str.charAt(i) == str.charAt(j))
					sp[i][j] = true;
				else if (str.charAt(i) == str.charAt(j) && sp[i + 1][j - 1])
					sp[i][j] = true;
			}
		}
		return sp;
	}

	public static int[] longestPalindromeSubstring(String str) {
		int[][] sp = new int[str.length()][str.length()];
		int maxLen = Integer.MIN_VALUE;
		int si = 0;
		int ei = 0;
		for (int gap = 0; gap < str.length(); gap++) {
			for (int i = 0, j = gap; j < str.length(); i++, j++) {
				if (gap == 0)
					sp[i][j] = 1;
				else if (gap == 1 && str.charAt(i) == str.charAt(j))
					sp[i][j] = 2;
				else if (str.charAt(i) == str.charAt(j) && sp[i + 1][j - 1] != 0)
					sp[i][j] = gap + 1;
				if (maxLen < sp[i][j]) {
					maxLen = sp[i][j];
					si = i;
					ei = j;
				}
			}
		}
		int[] res = new int[] { maxLen, si, ei };
		return res;
	}

	public static int countAllPalindromeSubstring(String str) {
		int[][] sp = new int[str.length()][str.length()];
		int count = 0;
		for (int gap = 0; gap < str.length(); gap++) {
			for (int i = 0, j = gap; j < str.length(); i++, j++) {
				if (gap == 0)
					sp[i][j] = 1;
				else if (gap == 1 && str.charAt(i) == str.charAt(j))
					sp[i][j] = 2;
				else if (str.charAt(i) == str.charAt(j) && sp[i + 1][j - 1] != 0)
					sp[i][j] = gap + 1;
				count += (sp[i][j] != 0) ? 1 : 0;
			}
		}
		return count;
	}

	public static int longestPalindromeSubsequence(String str, int si, int ei, boolean[][] isPalindrome) {
		if (isPalindrome[si][ei])
			return ei - si + 1;

		int len = 0;
		if (str.charAt(si) == str.charAt(ei))
			len = longestPalindromeSubsequence(str, si + 1, ei - 1, isPalindrome) + 2;
		else
			len = Math.max(longestPalindromeSubsequence(str, si + 1, ei, isPalindrome),
					longestPalindromeSubsequence(str, si, ei - 1, isPalindrome));
		return len;
	}

	public static int longestPalindromeSubsequenceDP(String str, int si, int ei, int[][] lps,
			boolean[][] isPalindrome) {

		if (isPalindrome[si][ei])
			return lps[si][ei] = ei - si + 1;
		if (lps[si][ei] != 0)
			return lps[si][ei];

		int len = 0;
		if (str.charAt(si) == str.charAt(ei))
			len = longestPalindromeSubsequenceDP(str, si + 1, ei - 1, lps, isPalindrome) + 2;
		else
			len = Math.max(longestPalindromeSubsequenceDP(str, si + 1, ei, lps, isPalindrome),
					longestPalindromeSubsequenceDP(str, si, ei - 1, lps, isPalindrome));
		return lps[si][ei] = len;
	}

	public static int longestPalindromeSubsequenceTable(String str, boolean[][] isPalindrome) {
		int[][] lps = new int[str.length()][str.length()];
		for (int gap = 0; gap < str.length(); gap++) {
			for (int si = 0, ei = gap; ei < str.length(); si++, ei++) {
				if (isPalindrome[si][ei]) {
					lps[si][ei] = ei - si + 1;
					continue;
				}

				int len = 0;
				if (str.charAt(si) == str.charAt(ei))
					len = lps[si + 1][ei - 1] + 2;
				else
					len = Math.max(lps[si + 1][ei], lps[si][ei - 1]);
				lps[si][ei] = len;
			}
		}
		return lps[0][str.length() - 1];
	}

	public static int distinctSubseq(String str, String target, int sl, int tl) {
		if (sl < tl)
			return 0;
		if (tl == 0)
			return 1;

		if (str.charAt(sl - 1) == target.charAt(tl - 1))
			return distinctSubseq(str, target, sl - 1, tl - 1) + distinctSubseq(str, target, sl - 1, tl);
		return distinctSubseq(str, target, sl - 1, tl);
	}

	public static int distinctSubseqDP(String str, String target, int sl, int tl, int[][] ds) {
		if (sl < tl)
			return 0;
		if (tl == 0)
			return ds[sl][tl] = 1;
		if (ds[sl][tl] != 0)
			return ds[sl][tl];

		if (str.charAt(sl - 1) == target.charAt(tl - 1))
			return ds[sl][tl] = distinctSubseqDP(str, target, sl - 1, tl - 1, ds)
					+ distinctSubseqDP(str, target, sl - 1, tl, ds);

		return ds[sl][tl] = distinctSubseqDP(str, target, sl - 1, tl, ds);
	}

	public static int distinctSubseqTable(String str, String target) {
		int[][] ds = new int[str.length() + 1][target.length() + 1];
		for (int sl = 0; sl <= str.length(); sl++) {
			for (int tl = 0; tl <= target.length(); tl++) {
				if (sl < tl) {
					ds[sl][tl] = 0;
					continue;
				}
				if (tl == 0) {
					ds[sl][tl] = 1;
					continue;
				}

				if (str.charAt(sl - 1) == target.charAt(tl - 1))
					ds[sl][tl] = ds[sl - 1][tl - 1] + ds[sl - 1][tl];
				else
					ds[sl][tl] = ds[sl - 1][tl];
			}
		}
		return ds[str.length()][target.length()];
	}

	public static int countAllPalindromeSubsequence(String str, int i, int j) {
		if (i > j)
			return 0;
		if (i == j)
			return 1;

		int middleString = countAllPalindromeSubsequence(str, i + 1, j - 1);
		int excludeLast = countAllPalindromeSubsequence(str, i, j - 1);
		int excludeFirst = countAllPalindromeSubsequence(str, i + 1, j);

		int ans = excludeFirst + excludeLast;
		return (str.charAt(i) == str.charAt(j)) ? ans + 1 : ans - middleString;
	}

	public static int countAllPalindromeSubsequenceDP(String str, int i, int j, int[][] cap) {
		if (i > j)
			return 0;
		if (i == j)
			return 1;

		if (cap[i][j] != 0)
			return cap[i][j];

		int middleString = countAllPalindromeSubsequenceDP(str, i + 1, j - 1, cap);
		int excludeLast = countAllPalindromeSubsequenceDP(str, i, j - 1, cap);
		int excludeFirst = countAllPalindromeSubsequenceDP(str, i + 1, j, cap);

		int ans = excludeFirst + excludeLast;
		return cap[i][j] = ans + ((str.charAt(i) == str.charAt(j)) ? 1 : -middleString);
	}

	public static int countAllPalindromeSubsequenceTable(String str) {
		int[][] cap = new int[str.length()][str.length()];
		for (int gap = 0; gap < str.length(); gap++) {
			for (int i = 0, j = gap; j < str.length(); i++, j++) {
				if (i == j) {
					cap[i][j] = 1;
					continue;
				}

				int middleString = cap[i + 1][j - 1];
				int excludeLast = cap[i][j - 1];
				int excludeFirst = cap[i + 1][j];

				int ans = excludeFirst + excludeLast;
				cap[i][j] = (str.charAt(i) == str.charAt(j)) ? ans + 1 : ans - middleString;
			}
		}
		return cap[0][str.length() - 1];
	}

}
