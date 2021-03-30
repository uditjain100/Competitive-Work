package DynamicProgramming;

public class Lecture_03 {

	public static void main(String[] args) {

		System.out.println(longestCommonSusbsequence("qwerty", "wrty", 5, 3));
		int[][] lcs = new int[7][5];
		System.out.println(longestCommonSusbsequenceDP("pqprqrp", "qpqrr", 0, 0, lcs));
		display(lcs);
		System.out.println(longestCommonSusbsequenceTable("qwerty", "yrwe"));

		// int[] a = { 1, 3, 7, 1, 7, 5 };
		// int[] b = { 1, 9, 2, 5, 1 };
		// int[][] temp = new int[a.length + 1][b.length + 1];
		// System.out.println(uncrossedLinesDP(a, b, 0, 0, temp));
		// System.out.println(uncrossedLinesTable(a, b));

		// System.out.println(CoinChangePremutationSubsequence(new int[] { 2, 3, 5, 7 },
		// 10, 0, 0));
		// int[][] ccp = new int[4][11];
		// System.out.println(CoinChangePermutationSubsequenceDP(new int[] { 2, 3, 5, 7
		// }, 10, 0, 0, ccp));
		// display(ccp);

		// System.out.println(CoinChangePermutationBinomial(new int[] { 2, 3, 5, 7 },
		// 10));
		// int[] ccp2 = new int[11];
		// System.out.println(CoinChangePermutationBinomialDP(new int[] { 2, 3, 5, 7 },
		// 10, ccp2));
		// display(ccp2);
		// System.out.println(CoinChangePermutationBinomialTable(new int[] { 2, 3, 5, 7
		// }, 10));

		// System.out.println(CoinChangeCombinationSubsequence(new int[] { 2, 3, 5, 7 },
		// 10, 0, 0));
		// int[][] ccc = new int[4][11];
		// System.out.println(CoinChangeCombinationSubsequenceDP(new int[] { 2, 3, 5, 7
		// }, 10, 0, 0, ccc));
		// display(ccc);
		// System.out.println(CoinChangeCombinationSubsequenceTable(new int[] { 2, 3, 5,
		// 7 }, 10));

		// System.out.println(CoinChangeCombinationBinomial(new int[] { 2, 3, 5, 7 },
		// 10, 0));
		// System.out.println(CoinChangeCombinationBinomialTable(new int[] { 2, 3, 5, 7
		// }, 10));

		// CoinChange(new int[] { 2, 3, 5, 7 }, 10, 0, 0);
		// System.out.println(ccmin);

		// System.out.println(CoinChangeTable(new int[] { 2, 3, 5, 7 }, 10));
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
			len = longestCommonSusbsequenceDP(str1, str2, i + 1, j + 1, lcs) + 1;
		else
			len = Math.max(longestCommonSusbsequenceDP(str1, str2, i + 1, j, lcs),
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
		if (i == a.length || j == b.length)
			return 0;

		int len = 0;
		if (a[i] == b[j])
			len = uncrossedLines(a, b, i + 1, j + 1) + 1;
		else
			len = Math.max(uncrossedLines(a, b, i + 1, j), uncrossedLines(a, b, i, j + 1));
		return len;
	}

	public static int uncrossedLinesDP(int[] a, int[] b, int i, int j, int[][] ul) {
		if (i == a.length || j == b.length)
			return ul[i][j] = 0;

		if (ul[i][j] != 0)
			return ul[i][j];

		int len = 0;
		if (a[i] == b[j])
			len = uncrossedLinesDP(a, b, i + 1, j + 1, ul) + 1;
		else
			len = Math.max(uncrossedLinesDP(a, b, i + 1, j, ul), uncrossedLinesDP(a, b, i, j + 1, ul));
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
				if (a[i] == b[j])
					len += ul[i + 1][j + 1] + 1;
				else
					len += Math.max(ul[i + 1][j], ul[i][j + 1]);
				ul[i][j] = len;
			}
		}
		return ul[0][0];
	}

	public static int maxDotProduct(int[] a, int[] b, int i, int j) {

		if (i == a.length || j == b.length)
			return -10 ^ 7;

		int val = a[i] * b[j];
		int ba = maxDotProduct(a, b, i + 1, j + 1) + val;
		int fa = maxDotProduct(a, b, i + 1, j);
		int sa = maxDotProduct(a, b, i, j + 1);
		return Math.max(Math.max(val, ba), Math.max(fa, sa));
	}

	public static int maxDotProductDP(int[] a, int[] b, int i, int j, int[][] mdp) {

		if (i == a.length || j == b.length)
			return mdp[i][j] = -10 ^ 7;

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

	// Infinite Coins Questions
	public static int CoinChangePremutationSubsequence(int[] arr, int target, int idx, int sum) {
		if (idx == arr.length || target == sum) {
			if (target == sum)
				return 1;
			return 0;
		}

		int count = 0;
		if (sum + arr[idx] <= target)
			count += CoinChangePremutationSubsequence(arr, target, 0, sum + arr[idx]);
		count += CoinChangePremutationSubsequence(arr, target, idx + 1, sum);
		return count;
	}

	public static int CoinChangePermutationSubsequenceDP(int[] arr, int target, int idx, int sum, int[][] ccp) {
		if (idx == arr.length || target == sum) {
			if (target == sum)
				return ccp[idx][sum] = 1;
			return 0;
		}

		if (ccp[idx][sum] != 0)
			return ccp[idx][sum];

		int count = 0;
		if (sum + arr[idx] <= target)
			count += CoinChangePermutationSubsequenceDP(arr, target, 0, sum + arr[idx], ccp);
		count += CoinChangePermutationSubsequenceDP(arr, target, idx + 1, sum, ccp);
		return ccp[idx][sum] = count;
	}

	// CoinChangePermutationSubsequenceTable Not Possible

	public static int CoinChangePermutationBinomial(int[] arr, int target) {
		if (target == 0)
			return 1;
		int count = 0;
		for (int i = 0; i < arr.length; i++)
			if (target - arr[i] >= 0)
				count += CoinChangePermutationBinomial(arr, target - arr[i]);
		return count;
	}

	public static int CoinChangePermutationBinomialDP(int[] arr, int target, int[] ccp) {
		if (target == 0)
			return ccp[target] = 1;

		if (ccp[target] != 0)
			return ccp[target];

		int count = 0;
		for (int i = 0; i < arr.length; i++)
			if (target - arr[i] >= 0)
				count += CoinChangePermutationBinomialDP(arr, target - arr[i], ccp);
		return ccp[target] = count;
	}

	public static int CoinChangePermutationBinomialTable(int[] arr, int target) {
		int[] ccp = new int[target + 1];
		ccp[0] = 1;
		for (int i = 1; i <= target; i++) {
			int count = 0;
			for (int j = 0; j < arr.length; j++)
				if (i - arr[j] >= 0)
					count += ccp[i - arr[j]];
			ccp[i] = count;
		}
		return ccp[target];
	}

	public static int CoinChangeCombinationSubsequence(int[] arr, int target, int i, int j) {
		if (i == arr.length || target == j) {
			if (target == j)
				return 1;
			return 0;
		}

		int count = 0;
		if (j + arr[i] <= target)
			count += CoinChangeCombinationSubsequence(arr, target, i, j + arr[i]);
		count += CoinChangeCombinationSubsequence(arr, target, i + 1, j);
		return count;
	}

	public static int CoinChangeCombinationSubsequenceDP(int[] arr, int target, int i, int j, int[][] ccc) {
		if (i == arr.length || target == j) {
			if (target == j)
				return ccc[i][j] = 1;
			return 0;
		}

		if (ccc[i][j] != 0)
			return ccc[i][j];

		int count = 0;
		if (j + arr[i] <= target)
			count += CoinChangeCombinationSubsequenceDP(arr, target, i, j + arr[i], ccc);
		count += CoinChangeCombinationSubsequenceDP(arr, target, i + 1, j, ccc);

		return ccc[i][j] = count;
	}

	public static int CoinChangeCombinationSubsequenceTable(int[] arr, int target) {
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

	public static int CoinChangeCombinationBinomial(int[] arr, int target, int idx) {
		if (target == 0)
			return 1;

		int count = 0;
		for (int i = idx; i < arr.length; i++)
			if (target - arr[i] >= 0)
				count += CoinChangeCombinationBinomial(arr, target - arr[i], i);
		return count;
	}

	// Memorization is not possible of CoinChangeCombination02 in 1D
	public static int CoinChangeCombinationBinomialTable(int[] arr, int target) {
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
			return;
		}

		for (int i = idx; i < arr.length; i++)
			if (target - arr[i] >= 0)
				CoinChange(arr, target - arr[i], i, res + 1);

	}

	public static int CoinChangeDP(int[] arr, int target, int[] cc) {
		if (target == 0)
			return cc[target] = 0;

		if (cc[target] != 0)
			return cc[target];

		int height = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (target - arr[i] >= 0) {
				int rh = CoinChangeDP(arr, target - arr[i], cc);
				if (rh != Integer.MAX_VALUE && height > rh + 1)
					height = rh + 1;
			}
		}
		return cc[target] = height;
	}

	public static int CoinChangeTable(int[] arr, int target) {
		int[] cc = new int[target + 1];
		for (int t = 0; t <= target; t++) {
			if (t == 0) {
				cc[t] = 0;
				continue;
			}

			int height = Integer.MAX_VALUE;
			for (int i = 0; i < arr.length; i++) {
				if (t - arr[i] >= 0) {
					int rh = cc[t - arr[i]];
					if (rh != Integer.MAX_VALUE && height > rh + 1)
						height = rh + 1;
				}
			}
			cc[t] = height;
		}
		return cc[target];
	}

}
