package Recursion;

public class Lecture_04_Queen_Problem {

	public static void main(String[] args) {
		
		// Set01
		int[] arr = { 2, 3, 5, 7 };
		System.out.println("\nSize : " + Coinchange_Permutations_Only_Once(arr, 10, 0, "", new boolean[4]));
		System.out.println("\nSize : " + CoinChange_Combinations_Only_Once(arr, 10, 0, ""));
		System.out.println("\nSize : " + CoinChange_Combinations(arr, 10, 0, ""));
		System.out.println("\nSize : " + Coinchange_Permutations(arr, 10, 0, ""));

		// Set02
		System.out.println(Queen_1D_Combination(new int[5], 0, 0, 3, ""));
		System.out.println(Queen_1D_Permutation(new int[5], 0, 3, "", new boolean[5]));
		System.out.println(Queen_2D_Combination(new int[4][4], 0, 0, 4, ""));
		System.out.println(Queen_2D_Permutation(new int[4][4], 0, 4, "", new boolean[4][4]));

	}

	public static int Coinchange_Permutations_Only_Once(int[] arr, int target, int idx, String ans, boolean[] board) {

		if (idx == arr.length || target == 0) {
			if (target == 0) {
				System.out.print(ans + " ");
				return 1;
			}
			return 0;
		}

		int count = 0;
		if (target - arr[idx] >= 0 && !board[idx]) {
			board[idx] = true;
			count += Coinchange_Permutations_Only_Once(arr, target - arr[idx], 0, ans + arr[idx], board);
			board[idx] = false;
		}
		count += Coinchange_Permutations_Only_Once(arr, target, idx + 1, ans, board);

		return count;
	}

	public static int CoinChange_Combinations_Only_Once(int[] arr, int target, int idx, String ans) {

		if (idx == arr.length || target == 0) {
			if (target == 0) {
				System.out.print(ans + " , ");
				return 1;
			}
			return 0;
		}

		int count = 0;
		if (target - arr[idx] >= 0) {
			count += CoinChange_Combinations_Only_Once(arr, target - arr[idx], idx + 1, ans + arr[idx]);
		}
		count += CoinChange_Combinations_Only_Once(arr, target, idx + 1, ans);

		return count;

	}

	public static int CoinChange_Combinations(int[] arr, int target, int idx, String ans) {

		if (idx == arr.length || target == 0) {
			if (target == 0) {
				System.out.print(ans + " , ");
				return 1;
			}
			return 0;
		}

		int count = 0;
		if (target - arr[idx] >= 0) {
			count += CoinChange_Combinations(arr, target - arr[idx], idx, ans + arr[idx]);
		}
		count += CoinChange_Combinations(arr, target, idx + 1, ans);

		return count;

	}

	public static int Coinchange_Permutations(int[] arr, int target, int idx, String ans) {

		if (idx == arr.length || target == 0) {
			if (target == 0) {
				System.out.print(ans + " , ");
				return 1;
			}
			return 0;
		}

		int count = 0;
		if (target - arr[idx] >= 0) {
			count += Coinchange_Permutations(arr, target - arr[idx], 0, ans + arr[idx]);
		}
		count += Coinchange_Permutations(arr, target, idx + 1, ans);

		return count;

	}

	public static int Queen_1D_Combination(int[] arr, int idx, int qpsf, int tnq, String ans) {

		if (tnq == qpsf) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = idx; i < arr.length; i++) {
			count += Queen_1D_Combination(arr, 1 + i, qpsf + 1, tnq, ans + "B:" + i + " Q:" + qpsf + " , ");
		}

		return count;

	}

	public static int Queen_1D_Permutation(int[] arr, int qpsf, int tnq, String ans, boolean[] temp) {

		if (tnq == qpsf) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (!temp[i]) {
				temp[i] = true;
				count += Queen_1D_Permutation(arr, qpsf + 1, tnq, ans + "B:" + i + " Q:" + qpsf + " , ", temp);
				temp[i] = false;
			}
		}

		return count;

	}

	public static int Queen_2D_Combination(int[][] arr, int idx, int qpsf, int tnq, String ans) {

		if (tnq == qpsf) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = idx; i < arr.length * arr[0].length; i++) {
			int row = i / arr.length;
			int col = i % arr.length;
			count += Queen_2D_Combination(arr, i + 1, qpsf + 1, tnq,
					ans + "B:[" + row + "," + col + "] Q:" + qpsf + " , ");
		}

		return count;

	}

	public static int Queen_2D_Permutation(int[][] arr, int qpsf, int tnq, String ans, boolean[][] temp) {

		if (tnq == qpsf) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = 0; i < arr.length * arr[0].length; i++) {
			int row = i / arr.length;
			int col = i % arr.length;
			if (!temp[row][col]) {
				temp[row][col] = true;
				count += Queen_2D_Permutation(arr, qpsf + 1, tnq, ans + "B:[" + row + "," + col + "] Q:" + qpsf + " , ",
						temp);
				temp[row][col] = false;
			}
		}

		return count;

	}

}
