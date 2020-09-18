package DynamicProgramming;

public class Lecture_05 {

	public static void main(String[] args) {

		// System.out.println(CoinChangeCombinationBinomial(new int[] { 2, 3, 5, 7 },
		// 10, 0, 0));
		// System.out.println(CoinChangeCombinationSubsequence(new int[] { 2, 3, 5, 7 },
		// 10, 4));

		// int[] arr = new int[] { 2, 3, 5, 7 };
		// int[][] cccs = new int[arr.length + 1][11];
		// System.out.println(CoinChangeCombinationSubsequenceDP(arr, 10, 4, cccs));
		// System.out.println(CoinChangeCombinationSubsequenceTable(arr, 10));
		// int[][] ans = CoinChangeCombinationSubsequenceTableBIT(arr, 10);
		// System.out.println(CoinChangeCombinationSubsequenceDPBIT(arr, 10, 4, "",
		// ans));

		// int[] wt = { 10, 20, 30 };
		// int[] values = { 60, 100, 120 };
		// int[][] t = Knapsack01(wt, 50);
		// KnapSack011(wt, values, 50, wt.length, 0, t);
		// System.out.println(maxValue);
		// System.out.println(KnapSack02(wt, values, 50, wt.length, new int[wt.length +
		// 1][51]));
		// System.out.println(KnapSackTable03(wt, values, 50));

		// int[] wts = { 1, 3, 4, 5 };
		// int[] value = { 10, 40, 50, 70 };
		// System.out.println(unBoundedKnapsack01(wts, value, 0, 8, 0));
		// System.out.println(maxVal);
		// System.out.println(unBoundedKnapsack02(wts, value, 0, 8));
		// System.out.println(unBoundedKnapsackDP02(wts, value, 0, 8, new int[wts.length
		// + 1][9]));
		// System.out.println(unBoundedKnapsackTable02(wts, value, 8));
		// System.out.println(unBoundedKnapsackTable03(wts, value, 8));
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

	// Single coin Questions
	public static int CoinChangeCombinationBinomial(int[] arr, int target, int idx, int sum) {
		if (sum == target)
			return 1;

		int count = 0;
		for (int i = idx; i < arr.length; i++)
			if (target - arr[i] >= 0)
				count += CoinChangeCombinationBinomial(arr, target, i + 1, sum + arr[i]);
		return count;
	}

	public static int CoinChangeCombinationSubsequence(int[] arr, int target, int idx) {
		if (idx == 0 || target == 0) {
			if (target == 0)
				return 1;
			return 0;
		}

		int count = 0;
		if (target - arr[idx - 1] >= 0)
			count += CoinChangeCombinationSubsequence(arr, target - arr[idx - 1], idx - 1);
		count += CoinChangeCombinationSubsequence(arr, target, idx - 1);
		return count;
	}

	public static int CoinChangeCombinationSubsequenceDP(int[] arr, int target, int idx, int[][] cccs) {
		if (idx == 0 || target == 0) {
			if (target == 0)
				return cccs[idx][target] = 1;
			return cccs[idx][target] = 0;
		}

		if (cccs[idx][target] != 0)
			return cccs[idx][target];

		int count = 0;
		if (target - arr[idx - 1] >= 0)
			count += CoinChangeCombinationSubsequenceDP(arr, target - arr[idx - 1], idx - 1, cccs);
		count += CoinChangeCombinationSubsequenceDP(arr, target, idx - 1, cccs);
		return cccs[idx][target] = count;
	}

	public static int CoinChangeCombinationSubsequenceTable(int[] arr, int target) {
		int[][] cccs = new int[arr.length + 1][target + 1];
		for (int i = 0; i <= arr.length; i++) {
			for (int j = 0; j <= target; j++) {
				if (i == 0 || j == 0) {
					if (j == 0) {
						cccs[i][j] = 1;
						continue;
					}
					cccs[i][j] = 0;
					continue;
				}

				int count = 0;
				if (j - arr[i - 1] >= 0)
					count += cccs[i - 1][j - arr[i - 1]];
				count += cccs[i - 1][j];
				cccs[i][j] = count;
			}
		}
		display(cccs);
		return cccs[arr.length][target];
	}

	public static int[][] CoinChangeCombinationSubsequenceTableBIT(int[] arr, int target) {
		boolean[][] cccs = new boolean[arr.length + 1][target + 1];
		for (int i = 0; i <= arr.length; i++) {
			for (int j = 0; j <= target; j++) {
				if (i == 0 || j == 0) {
					if (j == 0)
						cccs[i][j] = true;
					continue;
				}
				if (j - arr[i - 1] >= 0)
					cccs[i][j] = cccs[i - 1][j - arr[i - 1]];
				cccs[i][j] |= cccs[i - 1][j];
			}
		}
		int[][] res = new int[arr.length + 1][target + 1];
		for (int i = 0; i < cccs.length; i++) {
			for (int j = 0; j < cccs[i].length; j++) {
				if (cccs[i][j])
					res[i][j] = 1;
				else
					res[i][j] = 0;
			}
		}
		return res;
	}

	public static int CoinChangeCombinationSubsequenceDPBIT(int[] arr, int target, int idx, String ans, int[][] cccs) {
		if (idx == 0 || target == 0) {
			if (target == 0) {
				System.out.println(ans);
				return 1;
			}
			return 0;
		}

		int count = 0;
		if (target - arr[idx - 1] >= 0 && cccs[idx - 1][target - arr[idx - 1]] == 1)
			count += CoinChangeCombinationSubsequenceDPBIT(arr, target - arr[idx - 1], idx - 1, ans + arr[idx - 1],
					cccs);
		if (cccs[idx - 1][target] == 1)
			count += CoinChangeCombinationSubsequenceDPBIT(arr, target, idx - 1, ans, cccs);
		return count;
	}

	// 0-1Knapsack Problem
	// Method 01 (Memorization Method)
	public static int KnapSackDP(int[] wt, int[] values, int weight, int idx, int[][] cccs) {
		if (idx == 0 || weight == 0)
			return 0;

		if (cccs[idx][weight] != 0)
			return cccs[idx][weight];

		int maxVal = Integer.MIN_VALUE;
		if (weight - wt[idx - 1] >= 0)
			maxVal = Math.max(maxVal, KnapSackDP(wt, values, weight - wt[idx - 1], idx - 1, cccs) + values[idx - 1]);
		maxVal = Math.max(maxVal, KnapSackDP(wt, values, weight, idx - 1, cccs));
		return cccs[idx][weight] = maxVal;
	}

	// 0-1Knapsack Problem
	// Method 02 (Tabulation Method)
	public static int KnapSackTable(int[] wt, int[] values, int weight) {
		int[][] cccs = new int[wt.length + 1][weight + 1];
		for (int i = 0; i <= wt.length; i++) {
			for (int j = 0; j <= weight; j++) {
				if (i == 0 || j == 0) {
					cccs[i][j] = 0;
					continue;
				}

				int maxVal = Integer.MIN_VALUE;
				if (j - wt[i - 1] >= 0)
					maxVal = Math.max(maxVal, cccs[i - 1][j - wt[i - 1]] + values[i - 1]);
				maxVal = Math.max(maxVal, cccs[i - 1][j]);
				cccs[i][j] = maxVal;
			}
		}
		return cccs[wt.length][weight];
	}

	// 0-1Knapsack Problem
	// Method 03 (Boolean Method)
	public static int[][] KnapsackBIT(int[] wt, int weight) {
		boolean[][] cccs = new boolean[wt.length + 1][weight + 1];
		for (int i = 0; i <= wt.length; i++) {
			for (int j = 0; j <= weight; j++) {
				if (i == 0 || j == 0) {
					cccs[i][j] = true;
					continue;
				}
				if (j - wt[i - 1] >= 0)
					cccs[i][j] = cccs[i - 1][j - wt[i - 1]];
				cccs[i][j] = cccs[i][j] || cccs[i - 1][j];
			}
		}
		int[][] res = new int[wt.length + 1][weight + 1];
		for (int i = 0; i < cccs.length; i++) {
			for (int j = 0; j < cccs[i].length; j++) {
				if (cccs[i][j])
					res[i][j] = 1;
				else
					res[i][j] = 0;
			}
		}
		display(res);
		return res;
	}

	public static int maxValue = Integer.MIN_VALUE;

	public static void KnapSackBIToptimaall(int[] wt, int[] values, int weight, int idx, int ans, int[][] cccs) {
		if (idx == 0 || weight == 0) {
			maxValue = Math.max(maxValue, ans);
			return;
		}
		if (weight - wt[idx - 1] >= 0 && cccs[idx - 1][weight - wt[idx - 1]] == 1)
			KnapSackBIToptimaall(wt, values, weight - wt[idx - 1], idx - 1, ans + values[idx - 1], cccs);
		if (cccs[idx - 1][weight] == 1)
			KnapSackBIToptimaall(wt, values, weight, idx - 1, ans, cccs);
	}

	// KnapsackUnbounded Problem
	// Method 01 (Global Variable Method)
	public static int maxVal = Integer.MIN_VALUE;

	public static int unBoundedKnapsack01(int[] wt, int[] values, int idx, int target, int ans) {
		if (idx == wt.length || target == 0) {
			if (target == 0) {
				maxVal = Math.max(maxVal, ans);
				return 1;
			}
			return 0;
		}

		int count = 0;
		if (target - wt[idx] >= 0)
			count += unBoundedKnapsack01(wt, values, idx, target - wt[idx], ans + values[idx]);
		count += unBoundedKnapsack01(wt, values, idx + 1, target, ans);
		return count;
	}

	// KnapsackUnbounded Problem
	// Method 02 (Recursive Combination Infinite Method)
	public static int unBoundedKnapsack02(int[] wt, int[] values, int idx, int target) {
		if (idx == wt.length) {
			return 0;
		}

		int maxValue = Integer.MIN_VALUE;
		if (target - wt[idx] >= 0)
			maxValue = Math.max(maxValue, unBoundedKnapsack02(wt, values, idx, target - wt[idx]) + values[idx]);
		maxValue = Math.max(maxValue, unBoundedKnapsack02(wt, values, idx + 1, target));
		return maxValue;
	}

	// KnapsackUnbounded Problem
	// Method 03 (Memorization Combination Infinite Method)
	public static int unBoundedKnapsackDP02(int[] wt, int[] values, int idx, int target, int[][] ukp) {
		if (idx == wt.length) {
			return ukp[idx][target] = 0;
		}

		if (ukp[idx][target] != 0)
			return ukp[idx][target];

		int maxValue = Integer.MIN_VALUE;
		if (target - wt[idx] >= 0)
			maxValue = Math.max(maxValue, unBoundedKnapsackDP02(wt, values, idx, target - wt[idx], ukp) + values[idx]);
		maxValue = Math.max(maxValue, unBoundedKnapsackDP02(wt, values, idx + 1, target, ukp));
		return ukp[idx][target] = maxValue;
	}

	// KnapsackUnbounded Problem
	// Method 04 (Tabular Combination Infinite Proper Method)
	public static int unBoundedKnapsackTable02(int[] wt, int[] values, int target) {
		int[][] ukp = new int[wt.length + 1][target + 1];
		for (int i = wt.length; i >= 0; i--) {
			for (int j = 0; j <= target; j++) {
				if (i == wt.length) {
					ukp[i][j] = 0;
					continue;
				}

				int maxValue = Integer.MIN_VALUE;
				if (j - wt[i] >= 0)
					maxValue = Math.max(maxValue, ukp[i][j - wt[i]] + values[i]);
				maxValue = Math.max(maxValue, ukp[i + 1][j]);
				ukp[i][j] = maxValue;
			}
		}
		return ukp[0][target];
	}

	// KnapsackUnbounded Problem
	// Method 05 (Tabular Combination Infinite Smart Method)
	public static int unBoundedKnapsackTable03(int[] wt, int[] values, int target) {
		int[] ukp = new int[target + 1];
		ukp[0] = 0;
		for (int i = 0; i < wt.length; i++)
			for (int j = wt[i]; j <= target; j++)
				ukp[j] = Math.max(ukp[j], ukp[j - wt[i]] + values[i]);
		return ukp[target];
	}

	public static int editDistance(String w1, String w2, int i, int j, int[][] ed) {
		if (i == 0 || j == 0)
			return ed[i][j] = (i == 0) ? j : i;

		if (ed[i][j] != 0)
			return ed[i][j];

		if (w1.charAt(i - 1) == w2.charAt(j - 1))
			return ed[i][j] = editDistance(w1, w2, i - 1, j - 1, ed);

		int insert = editDistance(w1, w2, i, j - 1, ed);
		int replace = editDistance(w1, w2, i - 1, j - 1, ed);
		int delete = editDistance(w1, w2, i - 1, j, ed);

		return ed[i][j] = Math.min(Math.min(insert, replace), delete) + 1;
	}

	public static int editDistanceTable(String w1, String w2) {
		int[][] ed = new int[w1.length() + 1][w2.length() + 1];
		for (int i = 0; i <= w1.length(); i++) {
			for (int j = 0; j <= w2.length(); j++) {
				if (i == 0)
					ed[i][j] = j;
				else if (j == 0)
					ed[i][j] = i;
				else if (w1.charAt(i - 1) == w2.charAt(j - 1))
					ed[i][j] = ed[i - 1][j - 1];
				else
					ed[i][j] = Math.min(Math.min(ed[i][j - 1], ed[i - 1][j - 1]), ed[i - 1][j]) + 1;
			}
		}
		return ed[w1.length()][w2.length()];
	}

	public static int targetSum(int[] arr, int sum, int target, int idx, int[][] ts) {

		if (target == sum || idx == 0) {
			if (target == sum)
				return ts[idx][sum] = 1;
			return ts[idx][sum] = 0;
		}

		if (ts[idx][sum] != -1)
			return ts[idx][sum];

		int include = targetSum(arr, sum - arr[idx - 1], target, idx - 1, ts);
		int exclude = targetSum(arr, sum + arr[idx - 1], target, idx - 1, ts);

		return ts[idx][sum] = include + exclude;
	}

	public static int targetSumTable(int[] arr, int target) {
		int[][] ts = new int[arr.length + 1][2 * target + 1];
		ts[0][0 + target] = 1;
		for (int i = 0; i < arr.length + 1; i++) {
			for (int j = 0; j < 2 * target + 1; j++) {
				if (target == j || i == 0) {
					if (target == j) {
						ts[i][j] = 1;
						continue;
					}
					ts[i][j] = 0;
					continue;
				}
				ts[i][j] = ts[i - 1][j - arr[i - 1]] + ts[i - 1][j + arr[i - 1]];
			}
		}
		return ts[arr.length][2 * target];
	}

}
