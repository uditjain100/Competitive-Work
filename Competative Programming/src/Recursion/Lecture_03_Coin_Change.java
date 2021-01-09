package Recursion;

public class Lecture_03_Coin_Change {

	public static void main(String[] args) {
		
		// Set01
		int[] arr = { 2, 3, 5, 7 };
		System.out.println("\nSize : " + Coinchange_Permutations_Only_Once(arr, 10, "", new boolean[5]));
		System.out.println("\nSize : " + Coinchange_Combinations_Only_Once(arr, 10, 0, ""));
		System.out.println("\nSize : " + Coinchange_Combinations(arr, 10, 0, ""));
		System.out.println("\nSize : " + Coinchange_Permutations(arr, 10, ""));
		
	}

	public static int Coinchange_Permutations_Only_Once(int[] arr, int target, String ans, boolean[] temp) {

		if (target == 0) {
			System.out.print(ans + " , ");
			return 1;
		}

		if (target < 0) {
			return 0;
		}

		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (!temp[i]) {
				temp[i] = true;
				count += Coinchange_Permutations_Only_Once(arr, target - arr[i], ans + arr[i], temp);
				temp[i] = false;
			}
		}
		return count;

	}

	public static int Coinchange_Combinations_Only_Once(int[] arr, int target, int li, String ans) {

		if (target == 0) {
			System.out.print(ans + " , ");
			return 1;
		}

		if (target < 0) {
			return 0;
		}

		int count = 0;
		for (int i = li; i < arr.length; i++) {
			count += Coinchange_Combinations_Only_Once(arr, target - arr[i], i + 1, ans + arr[i]);
		}
		return count;

	}

	public static int Coinchange_Combinations(int[] arr, int target, int li, String ans) {

		if (target == 0) {
			System.out.print(ans + " , ");
			return 1;
		}

		if (target < 0) {
			return 0;
		}

		int count = 0;
		for (int i = li; i < arr.length; i++) {
			count += Coinchange_Combinations(arr, target - arr[i], i, ans + arr[i]);
		}

		return count;

	}

	public static int Coinchange_Permutations(int[] arr, int target, String ans) {

		if (target == 0) {
			System.out.print(ans + " , ");
			return 1;
		}

		if (target < 0) {
			return 0;
		}

		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			count += Coinchange_Permutations(arr, target - arr[i], ans + arr[i]);
		}

		return count;

	}

}
