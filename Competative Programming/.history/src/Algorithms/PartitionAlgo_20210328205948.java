package Algorithms;

public class PartitionAlgo {

	public static void main(String[] args) {

		int[] arr = { 2, 5, 1, 8, 9, 4, 5, 6, 3, 2, 8, 10, 7 };
		Partition(arr);
		for (int ele : arr)
			System.out.print(ele + ", ");

	}

	public static void Partition(int[] arr) {

		int pivot = arr[arr.length - 1];
		int i = 0;
		for (int j = i; j < arr.length - 2; j++) {
			if (arr[j] > pivot) {
				int temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
				i++;
			}
			for (int ele : arr)
				System.out.print(ele + ", ");

		}
		int temp = arr[i];
		arr[i] = arr[arr.length - 1];
		arr[arr.length - 1] = temp;
	}

}
