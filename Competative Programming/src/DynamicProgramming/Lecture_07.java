package DynamicProgramming;

import java.util.Arrays;

public class Lecture_07 {

	public static void main(String[] args) {

		int[] arr = { 2, 3, 4, 5, 6, 7 };
		System.out.println(MinCostMatrixMuliplication(arr, 0, arr.length - 1));

		int[][] mp = new int[arr.length][arr.length];
		for (int[] ele : mp)
			Arrays.fill(ele, -1);
		System.out.println(MinCostMatrixMuliplicationDP(arr, 0, arr.length - 1, mp));
		System.out.println(MinCostMatrixMuliplicationTable(arr));
		System.out.println(MinCostMatrixMuliplicationTableResult(arr));

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

	// Cut Type
	public static int MinCostMatrixMuliplication(int[] arr, int si, int ei) {
		if (si + 1 == ei)
			return 0;

		int minCost = Integer.MAX_VALUE;
		for (int i = si + 1; i < ei; i++) {
			int leftCost = MinCostMatrixMuliplication(arr, si, i);
			int rightCost = MinCostMatrixMuliplication(arr, i, ei);
			int myCost = leftCost + (arr[si] * arr[i] * arr[ei]) + rightCost;
			minCost = Math.min(minCost, myCost);
		}
		return minCost;
	}

	public static int MinCostMatrixMuliplicationDP(int[] arr, int si, int ei, int[][] mp) {
		if (si + 1 == ei)
			return mp[si][ei] = 0;

		if (mp[si][ei] != -1)
			return mp[si][ei];

		int minCost = Integer.MAX_VALUE;
		for (int i = si + 1; i < ei; i++) {
			int leftCost = MinCostMatrixMuliplicationDP(arr, si, i, mp);
			int rightCost = MinCostMatrixMuliplicationDP(arr, i, ei, mp);
			int myCost = leftCost + (arr[si] * arr[i] * arr[ei]) + rightCost;
			minCost = Math.min(minCost, myCost);
		}
		return mp[si][ei] = minCost;
	}

	public static int MinCostMatrixMuliplicationTable(int[] arr) {
		int[][] mp = new int[arr.length][arr.length];
		for (int gap = 1; gap < arr.length; gap++) {
			for (int si = 0, ei = gap; ei < arr.length; si++, ei++) {
				if (si + 1 == ei) {
					mp[si][ei] = 0;
					continue;
				}

				int minCost = Integer.MAX_VALUE;
				for (int i = si + 1; i < ei; i++) {
					int leftCost = mp[si][i];
					int rightCost = mp[i][ei];
					int myCost = leftCost + (arr[si] * arr[i] * arr[ei]) + rightCost;
					minCost = Math.min(minCost, myCost);
				}
				mp[si][ei] = minCost;
			}
		}
		return mp[0][arr.length - 1];
	}

	public static int MinCostMatrixMuliplicationTableResult(int[] arr) {
		int[][] mp = new int[arr.length][arr.length];
		String[][] res = new String[arr.length][arr.length];
		String ans = "";

		for (int gap = 1; gap < arr.length; gap++) {
			for (int si = 0, ei = gap; ei < arr.length; si++, ei++) {
				if (si + 1 == ei) {
					mp[si][ei] = 0;
					res[si][ei] = "" + (char) (si + 'A');
					continue;
				}

				int minCost = Integer.MAX_VALUE;
				ans = "";
				for (int i = si + 1; i < ei; i++) {
					int leftCost = mp[si][i];
					int rightCost = mp[i][ei];
					int myCost = leftCost + (arr[si] * arr[i] * arr[ei]) + rightCost;
					if (minCost > myCost) {
						minCost = myCost;
						ans = "(" + res[si][i] + res[i][ei] + ")";
					}
				}
				mp[si][ei] = minCost;
				res[si][ei] = ans;
			}
		}
		System.out.println(res[0][arr.length - 1]);
		return mp[0][arr.length - 1];
	}

	public static int selfCost(int[] freq, int si, int ei) {
		int ans = 0;
		for (int i = si; i <= ei; i++)
			ans += freq[i];
		return ans;
	}

	public static int optimalBST(int[] freq, int si, int ei) {
		int ans = Integer.MAX_VALUE;
		for (int i = si; i <= ei; i++) {
			int leftCost = (i == si) ? 0 : optimalBST(freq, si, i - 1);
			int rightCost = (i == ei) ? 0 : optimalBST(freq, i + 1, ei);
			int myCost = leftCost + selfCost(freq, si, ei) + rightCost;
			ans = Math.min(myCost, ans);
		}
		return ans;
	}

	public static int optimalBST_DP(int[] freq, int si, int ei, int[][] ob) {

		if (ob[si][ei] != 0)
			return ob[si][ei];

		int ans = Integer.MAX_VALUE;
		for (int i = si; i <= ei; i++) {
			int leftCost = (i == si) ? 0 : optimalBST_DP(freq, si, i - 1, ob);
			int rightCost = (i == ei) ? 0 : optimalBST_DP(freq, i + 1, ei, ob);
			int myCost = leftCost + selfCost(freq, si, ei) + rightCost;
			ans = Math.min(myCost, ans);
		}
		return ob[si][ei] = ans;
	}

	public static int optimalBST_Table(int[] freq) {

		int[][] ob = new int[freq.length][freq.length];
		int[] prefixSum = new int[freq.length + 1];
		for (int i = 1; i < prefixSum.length; i++)
			prefixSum[i] = prefixSum[i - 1] + freq[i - 1];

		for (int gap = 0; gap < freq.length; gap++) {
			for (int si = 0, ei = gap; ei < freq.length; si++, ei++) {
				int ans = Integer.MAX_VALUE;
				for (int i = si; i <= ei; i++) {
					int leftCost = (i == si) ? 0 : ob[si][i - 1];
					int rightCost = (i == ei) ? 0 : ob[i + 1][ei];
					int myCost = leftCost + (prefixSum[ei + 1] - prefixSum[si]) + rightCost;
					ans = Math.min(myCost, ans);
				}
				ob[si][ei] = ans;
			}
		}
		return ob[0][freq.length - 1];
	}

	public static int burstBalloons(int[] arr, int si, int ei, int[][] bb) {
		if (bb[si][ei] != 0)
			return bb[si][ei];

		int leftVal = (si == 0) ? 1 : arr[si - 1];
		int rightVal = (ei == arr.length - 1) ? 1 : arr[ei + 1];

		int ans = Integer.MIN_VALUE;
		for (int i = si; i <= ei; i++) {
			int leftCost = (si == i) ? 0 : burstBalloons(arr, si, i - 1, bb);
			int rightCost = (ei == i) ? 0 : burstBalloons(arr, i + 1, ei, bb);
			int myCost = leftCost + (leftVal * arr[i] * rightVal) + rightCost;
			ans = Math.max(ans, myCost);
		}
		return bb[si][ei] = ans;
	}

}
