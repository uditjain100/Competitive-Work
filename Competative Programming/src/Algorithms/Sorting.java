package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Sorting {

	public static void main(String[] args) {

		int[] arr = { 9, 2, 5, 4, 7, 6, 8, 3 };
		ShellSort(arr);
		for (int c : arr)
			System.out.print(c + ", ");
		System.out.println();
		int[] res = mergeSort(arr);
		for (int c : res)
			System.out.print(c + ", ");
	}

	public static int radixSort(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int ele : arr)
			max = Math.max(max, ele);

		int nod = (int) Math.floor(Math.log10(max) + 1);

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
			for (int i = 1; i <= nod; i++) {
				int n1 = a % (int) Math.pow(10, i);
				int n2 = b % (int) Math.pow(10, i);
				n1 /= (int) Math.pow(10, i - 1);
				n2 /= (int) Math.pow(10, i - 1);
				if (n1 != n2)
					return n1 - n2;
			}
			return b - a;
		});

		for (int ele : arr)
			pq.add(ele);

		while (!pq.isEmpty())
			System.out.println(pq.remove());
	}

	// Space O(1)
	// Time O(n^2)
	public static void BubbleSortLTR(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j + 1] < arr[j]) {
					int temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
				}
			}
		}

	}

	// Space O(1)
	// Time O(n^2)
	public static void BubbleSortRTL(int[] arr) {

		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = arr.length - 1; j >= arr.length - i; j--) {
				if (arr[j - 1] > arr[j]) {
					int temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}
		}

	}

	// Space O(1)
	// Time O(n^2)
	public static void SelectionSortLTR(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int idx = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[idx])
					idx = j;
			}
			if (idx != i) {
				int temp = arr[idx];
				arr[idx] = arr[i];
				arr[i] = temp;
			}
		}
	}

	// Space O(1)
	// Time O(n^2)
	public static void SelectionSortRTL(int[] arr) {
		for (int i = arr.length - 1; i >= 0; i--) {
			int idx = i;
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] > arr[idx])
					idx = j;
			}
			if (idx != i) {
				int temp = arr[idx];
				arr[idx] = arr[i];
				arr[i] = temp;
			}
		}
	}

	// Space O(1)
	// Time O(n^2)
	public static void InsertionSortLTR(int[] arr) {

		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int j = i - 1;
			while (j >= 0 && temp < arr[j]) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = temp;
		}

	}

	// Space O(1)
	// Time O(n^2)
	public static void InsertionSortRTL(int[] arr) {

		for (int i = arr.length - 2; i >= 0; i--) {
			int temp = arr[i];
			int j = i + 1;
			while (j < arr.length && temp > arr[j]) {
				arr[j - 1] = arr[j];
				j++;
			}
			arr[j - 1] = temp;
		}

	}

	// Space O(n)
	// Time O(n*log(n))
	public static int[] mergeSort(int[] arr) {
		return mergeSortReturn(arr, 0, arr.length - 1);
	}

	public static int[] mergeSortReturn(int[] arr, int si, int ei) {

		if (si == ei)
			return new int[] { arr[si] };

		int mid = (si + ei) / 2;

		int[] firstHalf = mergeSortReturn(arr, si, mid);
		int[] secondHalf = mergeSortReturn(arr, mid + 1, ei);

		return mergeTwoSortedArrays(firstHalf, secondHalf);

	}

	public static int[] mergeTwoSortedArrays(int[] arr1, int[] arr2) {
		int[] res = new int[arr1.length + arr2.length];

		int i = 0;
		int j = 0;
		int k = 0;
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] <= arr2[j]) {
				res[k++] = arr1[i++];
			} else {
				res[k++] = arr2[j++];
			}
		}

		if (i < arr1.length) {
			while (i < arr1.length) {
				res[k++] = arr1[i++];
			}
		}
		if (j < arr2.length) {
			while (j < arr2.length) {
				res[k++] = arr2[j++];
			}
		}
		return res;
	}

	// Space O(n)
	// Time O(n*log(n))
	public static void QuickSort(int[] arr) {
		QuickSortReturn(arr, 0, arr.length - 1);
	}

	public static void QuickSortReturn(int[] arr, int si, int ei) {
		int left = si;
		int right = ei;
		int mid = (left + right) / 2;
		int pivot = arr[mid];
		while (left < right) {
			while (arr[left] < pivot) {
				left++;
			}
			while (arr[right] > pivot) {
				right--;
			}
			if (left <= right) {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
			}
			QuickSortReturn(arr, si, right);
			QuickSortReturn(arr, left, ei);
		}
	}

	// Space O(n)
	// Time O(n)
	public static void CountingSort(int[] arr) {

		int max = Integer.MIN_VALUE;
		for (int ele : arr) {
			max = Math.max(max, ele);
		}
		int[] freq = new int[max + 1];
		for (int ele : arr) {
			freq[ele]++;
		}
		int i = 0;
		int k = 0;
		for (int ele : freq) {
			while (ele-- > 0) {
				arr[k++] = i;
			}
			i++;
		}
	}

	// Space O(1)
	// Time O(n*2)
	public static void ShellSort(int[] arr) {
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				int temp = arr[i];
				int j = i;
				for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
					arr[j] = arr[j - gap];
				}
				arr[j] = temp;
			}
		}
	}

	// Space O(n+k)
	// Time O(d*(n+k))
	public static void RadixSort(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int ele : arr)
			max = Math.max(max, ele);
		for (int pos = 1; (max / pos) > 0; pos *= 10)
			radix_pos_count_sort(arr, pos);
	}

	public static void radix_pos_count_sort(int[] arr, int pos) {

		int[] op = new int[arr.length];
		int[] count = new int[10];

		for (int i = 0; i < arr.length; i++)
			count[(arr[i] / pos) % 10]++;

		for (int i = 1; i < 10; i++)
			count[i] += count[i - 1];

		for (int i = arr.length - 1; i >= 0; i--)
			op[--count[(arr[i] / pos) % 10]] = arr[i];

		for (int i = 0; i < arr.length; i++)
			arr[i] = op[i];
	}

	public static void BucketSort(float[] arr) {

		ArrayList<Float>[] buckets = new ArrayList[arr.length];
		for (int i = 0; i < arr.length; i++)
			buckets[i] = new ArrayList<>();

		for (int i = 0; i < arr.length; i++)
			buckets[(int) (arr[i] * arr.length)].add(arr[i]);

		for (ArrayList<Float> bucket : buckets)
			Collections.sort(bucket);

		int idx = 0;
		for (int i = 0; i < buckets.length; i++)
			for (int j = 0; j < buckets[i].size(); j++)
				arr[idx++] = buckets[i].get(j);

	}

}
