package DynamicProgramming;

public class Lecture_05 {

	public static void main(String[] args) {

		System.out.println(CoinChangeCombination(new int[] { 2, 3, 5, 7 }, 10, 0));
		System.out.println(CoinChangeCombination01(new int[] { 2, 3, 5, 7 }, 10, 0));
		int[] arr = new int[] { 2, 3, 5, 7 };
		int[][] a = new int[arr.length + 1][11];
		System.out.println(CoinChangeCombinationDP01(arr, 10, 0, a));
		System.out.println(CoinChangeCombinationTable01(arr, 10));
	}

	// Single coin
	public static int CoinChangeCombination(int[] arr, int target, int idx) {
		if (target == 0)
			return 1;

		int count = 0;
		for (int i = idx; i < arr.length; i++)
			if (target - arr[i] >= 0)
				count += CoinChangeCombination(arr, target - arr[i], i + 1);

		return count;
	}

	public static int CoinChangeCombination01(int[] arr, int target, int idx) {
		if (idx == arr.length || target == 0) {
			if (target == 0)
				return 1;
			return 0;
		}

		int count = 0;
		if (target - arr[idx] >= 0)
			count += CoinChangeCombination01(arr, target - arr[idx], idx + 1);
		count += CoinChangeCombination01(arr, target, idx + 1);
		return count;
	}

	public static int CoinChangeCombinationDP01(int[] arr, int target, int idx, int[][] cccs) {
		if (idx == arr.length || target == 0) {
			if (target == 0)
				return cccs[idx][target] = 1;
			return cccs[idx][target] = 0;
		}

		int count = 0;
		if (target - arr[idx] >= 0)
			count += CoinChangeCombinationDP01(arr, target - arr[idx], idx + 1, cccs);
		count += CoinChangeCombinationDP01(arr, target, idx + 1, cccs);
		return cccs[idx][target] = count;
	}

	public static int CoinChangeCombinationTable01(int[] arr, int target) {

		int[][] cccs = new int[arr.length + 1][target + 1];
		for (int i = arr.length; i >= 0; i--) {
			for (int j = 0; j <= target; j++) {
				if (i == arr.length || j == 0) {
					if (j == 0) {
						cccs[i][j] = 1;
						continue;
					}
					cccs[i][j] = 0;
					continue;
				}

				int count = 0;
				if (j - arr[i] >= 0)
					count += cccs[i + 1][j - arr[i]];
				count += cccs[i + 1][j];
				cccs[i][j] = count;
			}
		}
		return cccs[0][target];
	}

}
