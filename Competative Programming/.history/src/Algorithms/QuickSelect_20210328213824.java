package Algorithms;

public class QuickSelect {

    public static PartitionAlgo p = new PartitionAlgo();

    public static void main(String[] args) {

        int[] arr = { 8, 2, 6, 4 };
        int k = 3;
        System.out.println(fun(arr, 0, arr.length - 1, k));
    }

    public static int fun(int[] arr, int l, int r, int k) {
        int pivotPosition = p.Partition(arr, l, r);
        if (k - 1 < pivotPosition) {
            return fun(arr, l, pivotPosition - 1, k);
        } else if (k - 1 > pivotPosition) {
            return fun(arr, pivotPosition + 1, r, k);
        } else {
            return arr[pivotPosition];
        }
    }

}
