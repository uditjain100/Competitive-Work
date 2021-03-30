package Algorithms;

public class PartitionAlgo {
	public static void main(String[] args) {

		int[] arr = { 2, 5, 7, 1, 8, 9, 4, 5, 6, 8, 2, 3 };
		int pivotPosition = Partition(arr);
		System.out.println(pivotPosition);
		for (int ele : arr)
			System.out.print(ele + ", ");

	}

	public static int Partition(int[] arr, int l, int r) {

		int pivot = arr[r];
		int i = l;
		for (int j = i; j < arr.length - 2; j++) {
			if (arr[j] > pivot) {
				int temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
				i++;
			}
		}
		int temp = arr[i];
		arr[i] = arr[r - 1];
		arr[r - 1] = temp;
		return i;
	}

}
