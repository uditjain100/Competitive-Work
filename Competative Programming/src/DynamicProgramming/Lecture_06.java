package DynamicProgramming;

import java.util.PriorityQueue;

public class Lecture_06 {

	public static void main(String[] args) {

		int arr[] = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
		display(LongestIncreasingSubsequenceTableLTR(arr));
		display(LongestIncreasingSubsequenceTableRTL(arr));
		display(LongestDecreasingSubsequenceTableLTR(arr));
		display(LongestDecreasingSubsequenceTableRTL(arr));
		display(LongestIncDecSubsequenceTable(arr));
		display(LongestDecIncSubsequenceTable(arr));
		display(LongestBitonicSubarray(arr));
		display(LongestInverseBitonicSubarray(arr));
		display(MaxSumIncSubsequenceLTR(arr));
		display(MaxSumIncSubsequenceRTL(arr));
		display(MaxSumDecSubsequenceLTR(arr));
		display(MaxSumDecSubsequenceRTL(arr));
		display(MaxSumIncDecSubsequence(arr));
		display(MaxSumDecIncSubsequence(arr));
		display(MaxSumBitonicSubsequence(arr));
		display(MaxSumInverseBitonicSubsequence(arr));

		int[][] a = { { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 } };
		System.out.println(russianDoll(a));
		System.out.println(NumberofLongestIncreasingSubsequenceLTR(arr));

	}

	public static int[] LongestIncreasingSubsequenceTableLTR(int[] arr) {
		int[] lis = new int[arr.length];
		lis[0] = 1;
		int maxis = 1;
		for (int i = 1; i < arr.length; i++) {
			int max = 0;
			for (int j = i - 1; j >= 0; j--)
				if (arr[j] < arr[i])
					max = Math.max(max, lis[j]);
			lis[i] = max + 1;
			maxis = Math.max(maxis, lis[i]);
		}
		System.out.println(maxis);
		return lis;
	}

	public static int[] LongestIncreasingSubsequenceTableRTL(int[] arr) {
		int[] lis = new int[arr.length];
		lis[arr.length - 1] = 1;
		int maxis = 1;
		for (int i = arr.length - 2; i >= 0; i--) {
			int max = 0;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[i])
					max = Math.max(max, lis[j]);
			}
			lis[i] = max + 1;
			maxis = Math.max(maxis, lis[i]);
		}
		System.out.println(maxis);
		return lis;
	}

	public static int[] LongestDecreasingSubsequenceTableLTR(int[] arr) {
		int[] lis = new int[arr.length];
		lis[0] = 1;
		int maxis = 1;
		for (int i = 1; i < arr.length; i++) {
			int max = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] > arr[i])
					max = Math.max(max, lis[j]);
			}
			lis[i] = max + 1;
			maxis = Math.max(maxis, lis[i]);
		}
		System.out.println(maxis);
		return lis;
	}

	public static int[] LongestDecreasingSubsequenceTableRTL(int[] arr) {
		int[] lis = new int[arr.length];
		lis[arr.length - 1] = 1;
		int maxis = 1;
		for (int i = arr.length - 2; i >= 0; i--) {
			int max = 0;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] > arr[i])
					max = Math.max(max, lis[j]);
			}
			lis[i] = max + 1;
			maxis = Math.max(maxis, lis[i]);
		}
		System.out.println(maxis);
		return lis;
	}

	public static int[] LongestIncDecSubsequenceTable(int[] arr) {

		int[] lisltr = LongestIncreasingSubsequenceTableLTR(arr);
		int[] lisrtl = LongestIncreasingSubsequenceTableRTL(arr);

		int[] lis = new int[arr.length];
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			lis[i] = lisltr[i] + lisrtl[i] - 1;
			max = Math.max(max, lis[i]);
		}
		System.out.println(max);
		return lis;
	}

	public static int[] LongestDecIncSubsequenceTable(int[] arr) {

		int[] ldsltr = LongestDecreasingSubsequenceTableLTR(arr);
		int[] ldsrtl = LongestDecreasingSubsequenceTableRTL(arr);

		int[] lds = new int[arr.length];
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			lds[i] = ldsltr[i] + ldsrtl[i] - 1;
			max = Math.max(max, lds[i]);
		}
		System.out.println(max);
		return lds;
	}

	public static int[] LongestBitonicSubarray(int[] arr) {
		int[] a = LongestIncreasingSubsequenceTableLTR(arr);
		int[] b = LongestDecreasingSubsequenceTableLTR(arr);
		int[] c = LongestIncDecSubsequenceTable(arr);
		int[] res = new int[arr.length];

		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			res[i] = Math.max(Math.max(a[i], b[i]), c[i]);
			max = Math.max(max, res[i]);
		}
		System.out.println(max);
		return res;
	}

	public static int[] LongestInverseBitonicSubarray(int[] arr) {
		int[] a = LongestIncreasingSubsequenceTableLTR(arr);
		int[] b = LongestDecreasingSubsequenceTableLTR(arr);
		int[] c = LongestDecIncSubsequenceTable(arr);
		int[] res = new int[arr.length];

		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			res[i] = Math.max(Math.max(a[i], b[i]), c[i]);
			max = Math.max(max, res[i]);
		}
		System.out.println(max);
		return res;
	}

	public static int[] MaxSumIncSubsequenceLTR(int[] arr) {
		int[] lis = new int[arr.length];
		lis[0] = arr[0];
		int maxSum = arr[0];
		for (int i = 1; i < arr.length; i++) {
			int sum = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] < arr[i])
					sum = Math.max(sum, lis[j]);
			}
			lis[i] = sum + arr[i];
			maxSum = Math.max(maxSum, lis[i]);
		}
		System.out.println(maxSum);
		return lis;
	}

	public static int[] MaxSumIncSubsequenceRTL(int[] arr) {
		int[] lis = new int[arr.length];
		lis[0] = arr[0];
		int maxSum = arr[0];
		for (int i = arr.length - 1; i >= 0; i--) {
			int sum = 0;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[i])
					sum = Math.max(sum, lis[j]);
			}
			lis[i] = sum + arr[i];
			maxSum = Math.max(maxSum, lis[i]);
		}
		System.out.println(maxSum);
		return lis;
	}

	public static int[] MaxSumDecSubsequenceLTR(int[] arr) {
		int[] lis = new int[arr.length];
		lis[0] = arr[0];
		int maxSum = arr[0];
		for (int i = 1; i < arr.length; i++) {
			int sum = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] > arr[i])
					sum = Math.max(sum, lis[j]);
			}
			lis[i] = sum + arr[i];
			maxSum = Math.max(maxSum, lis[i]);
		}
		System.out.println(maxSum);
		return lis;
	}

	public static int[] MaxSumDecSubsequenceRTL(int[] arr) {
		int[] lis = new int[arr.length];
		lis[0] = arr[0];
		int maxSum = arr[0];
		for (int i = arr.length - 1; i >= 0; i--) {
			int sum = 0;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] > arr[i])
					sum = Math.max(sum, lis[j]);
			}
			lis[i] = sum + arr[i];
			maxSum = Math.max(maxSum, lis[i]);
		}
		System.out.println(maxSum);
		return lis;
	}

	public static int[] MaxSumIncDecSubsequence(int[] arr) {

		int[] lisltr = MaxSumIncSubsequenceLTR(arr);
		int[] lisrtl = MaxSumIncSubsequenceRTL(arr);

		int[] lis = new int[arr.length];
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			lis[i] = lisltr[i] + lisrtl[i] - arr[i];
			max = Math.max(max, lis[i]);
		}
		System.out.println(max);
		return lis;
	}

	public static int[] MaxSumDecIncSubsequence(int[] arr) {

		int[] ldsltr = MaxSumDecSubsequenceLTR(arr);
		int[] ldsrtl = MaxSumDecSubsequenceRTL(arr);

		int[] lds = new int[arr.length];
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			lds[i] = ldsltr[i] + ldsrtl[i] - arr[i];
			max = Math.max(max, lds[i]);
		}
		System.out.println(max);
		return lds;
	}

	public static int[] MaxSumBitonicSubsequence(int[] arr) {

		int[] a = MaxSumIncSubsequenceLTR(arr);
		int[] b = MaxSumDecSubsequenceLTR(arr);
		int[] c = MaxSumIncDecSubsequence(arr);
		int[] res = new int[arr.length];

		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			res[i] = Math.max(Math.max(a[i], b[i]), c[i]);
			max = Math.max(max, res[i]);
		}
		System.out.println(max);
		return res;

	}

	public static int[] MaxSumInverseBitonicSubsequence(int[] arr) {

		int[] a = MaxSumIncSubsequenceLTR(arr);
		int[] b = MaxSumDecSubsequenceLTR(arr);
		int[] c = MaxSumDecIncSubsequence(arr);
		int[] res = new int[arr.length];

		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			res[i] = Math.max(Math.max(a[i], b[i]), c[i]);
			max = Math.max(max, res[i]);
		}
		System.out.println(max);
		return res;

	}

	public static int minDeletionSortedArray(int[] arr) {
		int[] lis = new int[arr.length];
		lis[0] = 1;
		int maxis = 1;
		for (int i = 1; i < arr.length; i++) {
			int max = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] <= arr[i])
					max = Math.max(max, lis[j]);
			}
			lis[i] = max + 1;
			maxis = Math.max(maxis, lis[i]);
		}
		return arr.length - maxis;
	}

	public static int russianDoll(int[][] arr) {
		if (arr.length == 0 || arr[0].length == 0)
			return 0;
		if (arr[0].length == 1)
			return 0;

		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> {
			return a[0] - b[0];
		});
		for (int[] ele : arr)
			pq.add(ele);
		int idx = 0;
		while (!pq.isEmpty()) {
			arr[idx++] = pq.remove();
		}
		int[] lis = new int[arr.length];
		lis[0] = 1;
		int maxis = 1;
		for (int i = 1; i < arr.length; i++) {
			int max = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j][0] < arr[i][0] && arr[j][1] < arr[i][1])
					max = Math.max(max, lis[j]);
			}
			lis[i] = max + 1;
			maxis = Math.max(maxis, lis[i]);
		}
		return maxis;
	}

	// Alphabetical Order
	// Job Scheduling

	// Error
	public static int NumberofLongestIncreasingSubsequenceLTR(int[] arr) {
		int[] lis = new int[arr.length];
		int[] res = new int[arr.length];
		lis[0] = 1;
		res[0] = 1;
		int maxlis = 0;
		for (int i = 1; i < arr.length; i++) {
			int max = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] < arr[i])
					max = Math.max(max, lis[j]);
			}
			int count = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] < arr[i] && max == lis[j])
					count += res[j];
			}
			res[i] = count;
			lis[i] = max + 1;
			maxlis = Math.max(maxlis, lis[i]);
		}
		int maxCount = 0;
		for (int i = 0; i < arr.length; i++) {
			if (lis[i] == maxlis)
				maxCount = Math.max(res[i], maxCount);
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.print(lis[i] + ", ");
		}
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			System.out.print(res[i] + ", ");
		}
		System.out.println();

		return maxCount;
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
	
}
