package DynamicProgramming;

public class Lecture_03 {

	public static void main(String[] args) {

		System.out.println(longestCommonSusbsequence("qwerty", "wrty", 5, 3));
		System.out.println(longestCommonSusbsequenceDP("qwerty", "yrwe", 0, 0, new int[6][4]));
		System.out.println(longestCommonSusbsequenceTable("qwerty", "yrwe"));

		int[] a = { 1, 3, 7, 1, 7, 5 };
		int[] b = { 1, 9, 2, 5, 1 };
		int[][] temp = new int[a.length + 1][b.length + 1];
		System.out.println(uncrossedLinesDP(a, b, 0, 0, temp));
		System.out.println(uncrossedLinesTable(a, b));

		System.out.println(CoinChangeCombination(new int[] { 2, 3, 5, 7 }, 10, 0, 0));
		System.out.println(CoinChangeCombinationDP(new int[] { 2, 3, 5, 7 }, 10, 0, 0, new int[5][11]));
		System.out.println(CoinChangeCombinationTable(new int[] { 2, 3, 5, 7 }, 10));

		int[] ax = new int[11];
		System.out.println(CoinChangePermutation02(new int[] { 2, 3, 5, 7 }, 10));
		System.out.println(CoinChangePermutationDP02(new int[] { 2, 3, 5, 7 }, 10, ax));
		System.out.println(CoinChangePermutationTable02(new int[] { 2, 3, 5, 7 }, 10));

		System.out.println(CoinChangeCombination02(new int[] { 2, 3, 5, 7 }, 10, 0));
		System.out.println(CoinChangeCombinationTable02(new int[] { 2, 3, 5, 7 }, 10));

		CoinChange(new int[] { 2, 3, 5, 7 }, 10, 0, 0);
		System.out.println(ccmin);
	}

	public static int longestCommonSusbsequence(String str1, String str2, int i, int j) {
		if (i == -1 || j == -1)
			return 0;

		int len = 0;
		if (str1.charAt(i) == str2.charAt(j))
			len += longestCommonSusbsequence(str1, str2, i - 1, j - 1) + 1;
		else
			len += Math.max(longestCommonSusbsequence(str1, str2, i - 1, j),
					longestCommonSusbsequence(str1, str2, i, j - 1));

		return len;
	}

	public static int longestCommonSusbsequenceDP(String str1, String str2, int i, int j, int[][] lcs) {
		if (i == str1.length() || j == str2.length())
			return 0;

		if (lcs[i][j] != 0)
			return lcs[i][j];

		int len = 0;
		if (str1.charAt(i) == str2.charAt(j))
			len += longestCommonSusbsequenceDP(str1, str2, i + 1, j + 1, lcs) + 1;
		else
			len += Math.max(longestCommonSusbsequenceDP(str1, str2, i + 1, j, lcs),
					longestCommonSusbsequenceDP(str1, str2, i, j + 1, lcs));

		return lcs[i][j] = len;
	}

	public static int longestCommonSusbsequenceTable(String str1, String str2) {

		int[][] lcs = new int[str1.length() + 1][str2.length() + 1];
		for (int i = str1.length(); i >= 0; i--) {
			for (int j = str2.length(); j >= 0; j--) {
				if (i == str1.length() || j == str2.length()) {
					lcs[i][j] = 0;
					continue;
				}
				int len = 0;
				if (str1.charAt(i) == str2.charAt(j))
					len += lcs[i + 1][j + 1] + 1;
				else
					len += Math.max(lcs[i + 1][j], lcs[i][j + 1]);
				lcs[i][j] = len;
			}
		}
		return lcs[0][0];
	}

	public static int lcstmax = 0;

	public static int longestCommonSubstring(String str1, String str2, int i, int j) {
		if (i == str1.length() || j == str2.length())
			return 0;
		longestCommonSubstring(str1, str2, i, j + 1);
		longestCommonSubstring(str1, str2, i + 1, j);

		if (str1.charAt(i) == str2.charAt(j)) {
			int temp = longestCommonSubstring(str1, str2, i + 1, j + 1);
			lcstmax = Math.max(lcstmax, temp);
			return temp;
		}
		return 0;
	}

	public static int longestCommonSubstringDP(String str1, String str2, int i, int j, int[][] lcst) {
		if (i == str1.length() || j == str2.length())
			return lcst[i][j] = 0;

		if (lcst[i][j] != 0)
			return lcst[i][j];

		longestCommonSubstringDP(str1, str2, i, j + 1, lcst);
		longestCommonSubstringDP(str1, str2, i + 1, j, lcst);

		if (str1.charAt(i) == str2.charAt(j)) {
			int temp = longestCommonSubstringDP(str1, str2, i + 1, j + 1, lcst);
			lcstmax = Math.max(lcstmax, temp);
			return lcst[i][j] = temp;
		}
		return 0;
	}

	public static int longestCommonSubstringTable(String str1, String str2) {

		int lcstmax = 0;
		int[][] lcst = new int[str1.length() + 1][str2.length() + 1];
		for (int i = str1.length(); i >= 0; i--) {
			for (int j = str2.length(); j >= 0; j--) {
				if (i == str1.length() || j == str2.length())
					continue;
				if (str1.charAt(i) == str2.charAt(j)) {
					int temp = lcst[i + 1][j + 1] + 1;
					lcstmax = Math.max(lcstmax, temp);
					lcst[i][j] = temp;
				}
			}
		}
		return lcst[0][0];
	}

	public static int uncrossedLines(int[] a, int[] b, int i, int j) {

		if (i == a.length || j == b.length) {
			return 0;
		}

		int len = 0;
		if (a[i] == b[j]) {
			len += uncrossedLines(a, b, i + 1, j + 1) + 1;
		} else {
			len += Math.max(uncrossedLines(a, b, i + 1, j), uncrossedLines(a, b, i, j + 1));
		}
		return len;
	}

	public static int uncrossedLinesDP(int[] a, int[] b, int i, int j, int[][] ul) {

		if (i == a.length || j == b.length) {
			return ul[i][j] = 0;
		}

		if (ul[i][j] != 0)
			return ul[i][j];

		int len = 0;
		if (a[i] == b[j]) {
			len += uncrossedLinesDP(a, b, i + 1, j + 1, ul) + 1;
		} else {
			len += Math.max(uncrossedLinesDP(a, b, i + 1, j, ul), uncrossedLinesDP(a, b, i, j + 1, ul));
		}
		return ul[i][j] = len;
	}

	public static int uncrossedLinesTable(int[] a, int[] b) {

		int[][] ul = new int[a.length + 1][b.length + 1];
		for (int i = a.length; i >= 0; i--) {
			for (int j = b.length; j >= 0; j--) {
				if (i == a.length || j == b.length) {
					ul[i][j] = 0;
					continue;
				}

				int len = 0;
				if (a[i] == b[j]) {
					len += ul[i + 1][j + 1] + 1;
				} else {
					len += Math.max(ul[i + 1][j], ul[i][j + 1]);
				}
				ul[i][j] = len;
			}
		}
		return ul[0][0];
	}

	public static int maxDotProduct(int[] a, int[] b, int i, int j) {

		if (i == a.length || j == b.length) {
			return -10 ^ 7;
		}

		int val = a[i] * b[j];
		int ba = maxDotProduct(a, b, i + 1, j + 1) + val;
		int fa = maxDotProduct(a, b, i + 1, j);
		int sa = maxDotProduct(a, b, i, j + 1);
		return Math.max(Math.max(val, ba), Math.max(fa, sa));
	}

	public static int maxDotProductDP(int[] a, int[] b, int i, int j, int[][] mdp) {

		if (i == a.length || j == b.length) {
			return mdp[i][j] = -10 ^ 7;
		}

		if (mdp[i][j] != 0)
			return mdp[i][j];

		int val = a[i] * b[j];
		int ba = maxDotProductDP(a, b, i + 1, j + 1, mdp) + val;
		int fa = maxDotProductDP(a, b, i + 1, j, mdp);
		int sa = maxDotProductDP(a, b, i, j + 1, mdp);
		return mdp[i][j] = Math.max(Math.max(val, ba), Math.max(fa, sa));
	}

	public static int maxDotProductTable(int[] a, int[] b) {
		int[][] mdp = new int[a.length + 1][b.length + 1];
		for (int i = a.length; i >= 0; i--) {
			for (int j = b.length; j >= 0; j--) {
				if (i == a.length || j == b.length) {
					mdp[i][j] = -10 ^ 7;
					continue;
				}
				int val = a[i] * b[j];
				int ba = mdp[i + 1][j + 1] + val;
				int fa = mdp[i][j + 1];
				int sa = mdp[i + 1][j];
				mdp[i][j] = Math.max(Math.max(val, ba), Math.max(fa, sa));
			}
		}
		return mdp[0][0];
	}

	public static int CoinChangePremutation(int[] arr, int target, int i, int j) {
		if (i == arr.length || target == j) {
			if (target == j) {
				return 1;
			}
			return 0;
		}

		int count = 0;
		if (j + arr[i] <= target)
			count += CoinChangePremutation(arr, target, 0, j + arr[i]);
		count += CoinChangePremutation(arr, target, i + 1, j);

		return count;
	}

	public static int CoinChangePermutationDP(int[] arr, int target, int i, int j, int[][] ccp) {
		if (i == arr.length || target == j) {
			if (target == j) {
				return ccp[i][j] = 1;
			}
			return 0;
		}

		if (ccp[i][j] != 0) {
			return ccp[i][j];
		}

		int count = 0;
		if (j + arr[i] <= target)
			count += CoinChangePermutationDP(arr, target, 0, j + arr[i], ccp);
		count += CoinChangePermutationDP(arr, target, i + 1, j, ccp);

		return ccp[i][j] = count;
	}

	public static int CoinChangePermutation02(int[] arr, int target) {

		if (target == 0)
			return 1;

		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (target - arr[i] >= 0)
				count += CoinChangePermutation02(arr, target - arr[i]);
		}

		return count;
	}

	public static int CoinChangePermutationDP02(int[] arr, int target, int[] ccp) {

		if (target == 0)
			return ccp[target] = 1;

		if (ccp[target] != 0)
			return ccp[target];

		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (target - arr[i] >= 0)
				count += CoinChangePermutationDP02(arr, target - arr[i], ccp);
		}

		return ccp[target] = count;
	}

	public static int CoinChangePermutationTable02(int[] arr, int target) {
		int[] ccp = new int[target + 1];
		ccp[0] = 1;
		for (int i = 1; i <= target; i++) {

			int count = 0;
			for (int j = 0; j < arr.length; j++) {
				if (i - arr[j] >= 0)
					count += ccp[i - arr[j]];
			}
			ccp[i] = count;
		}
		return ccp[target];
	}

	public static int CoinChangeCombination(int[] arr, int target, int i, int j) {
		if (i == arr.length || target == j) {
			if (target == j) {
				return 1;
			}
			return 0;
		}

		int count = 0;
		if (j + arr[i] <= target)
			count += CoinChangeCombination(arr, target, i, j + arr[i]);
		count += CoinChangeCombination(arr, target, i + 1, j);

		return count;
	}

	public static int CoinChangeCombinationDP(int[] arr, int target, int i, int j, int[][] ccc) {
		if (i == arr.length || target == j) {
			if (target == j) {
				return ccc[i][j] = 1;
			}
			return 0;
		}

		if (ccc[i][j] != 0) {
			return ccc[i][j];
		}

		int count = 0;
		if (j + arr[i] <= target)
			count += CoinChangeCombinationDP(arr, target, i, j + arr[i], ccc);
		count += CoinChangeCombinationDP(arr, target, i + 1, j, ccc);

		return ccc[i][j] = count;
	}

	public static int CoinChangeCombinationTable(int[] arr, int target) {

		int[][] ccc = new int[arr.length + 1][target + 1];
		for (int i = arr.length; i >= 0; i--) {
			for (int j = target; j >= 0; j--) {
				if (i == arr.length || j == target) {
					if (j == target) {
						ccc[i][j] = 1;
						continue;
					}
					ccc[i][j] = 0;
					continue;
				}
				int count = 0;
				if (j + arr[i] <= target)
					count += ccc[i][j + arr[i]];
				count += ccc[i + 1][j];
				ccc[i][j] = count;
			}
		}
		return ccc[0][0];
	}

	public static int CoinChangeCombination02(int[] arr, int target, int idx) {
		if (target == 0)
			return 1;

		int count = 0;
		for (int i = idx; i < arr.length; i++) {
			if (target - arr[i] >= 0)
				count += CoinChangeCombination02(arr, target - arr[i], i);
		}

		return count;
	}

	// Memorization is not possible of CoinChangeCombination02 in 1D
	public static int CoinChangeCombinationTable02(int[] arr, int target) {
		int[] ccp = new int[target + 1];
		ccp[0] = 1;
		for (int ele : arr)
			for (int i = ele; i <= target; i++)
				ccp[i] += ccp[i - ele];
		return ccp[target];
	}

	// 2p + 3q + 5r + 7s = 10
	public static int numberOfsolutionOfLinearEquation(int[] coefficients, int rhs) {
		int[] ccp = new int[rhs + 1];
		ccp[0] = 1;
		for (int ele : coefficients)
			for (int i = ele; i <= rhs; i++)
				ccp[i] += ccp[i - ele];
		return ccp[rhs];
	}

	public static int ccmin = Integer.MAX_VALUE;

	public static void CoinChange(int[] arr, int target, int idx, int res) {
		if (target == 0) {
			ccmin = Math.min(res, ccmin);
			System.out.println("ccmin : " + ccmin);
			return;
		}

		for (int i = idx; i < arr.length; i++) {
			if (target - arr[i] >= 0)
				CoinChange(arr, target - arr[i], i, res + 1);
		}

	}

//	public static int CoinChangeTable(int[] arr, int target) {
//		int[] ccp = new int[target + 1];
//		ccp[0] = 1;
//		int min = Integer.MAX_VALUE;
//		for (int ele : arr) {
//			int res = 0;
//			for (int i = ele; i <= target; i++) {
//				ccp[i] += ccp[i - ele];
//				if (ccp[i - ele] != 0)
//					res++;
//			}
//			min = Math.min(min, res);
//		}
//		return min;
//	}

}
