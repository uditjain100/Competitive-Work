package Algorithms;

public class Searching {

    public static void main(String[] args) {
        linearSearchClient();
        linearSearchRecursiveClient();
        binarySearchCient();
        binarySearchRecursiveCient();
    }

    public static void binarySearchCient() {

        System.out.println("Binary search Iterative : ");
        // Worst Case
        double start = System.currentTimeMillis();
        System.out.println("Index : " + binarySearch(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 9));
        double end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start));

        // Best Case
        start = System.currentTimeMillis();
        System.out.println("Index : " + binarySearch(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 1));
        end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start));

        // Average Case
        start = System.currentTimeMillis();
        System.out.println("Index : " + binarySearch(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 5));
        end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start));
    }

    public static void binarySearchRecursiveCient() {

        System.out.println("Binary search Recursive : ");
        // Worst Case
        double start = System.currentTimeMillis();
        System.out.println("Index : " + binarySearchR(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 9, 0, 8));
        double end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start));

        // Best Case
        start = System.currentTimeMillis();
        System.out.println("Index : " + binarySearchR(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 1, 0, 8));
        end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start));

        // Average Case
        start = System.currentTimeMillis();
        System.out.println("Index : " + binarySearchR(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 5, 0, 8));
        end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start));
    }

    public static void linearSearchClient() {

        System.out.println("Linear search Iterative : ");
        // Worst Case
        double start = System.currentTimeMillis();
        System.out.println("Index : " + linearSearch(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 9));
        double end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start));

        // Best Case
        start = System.currentTimeMillis();
        System.out.println("Index : " + linearSearch(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 1));
        end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start));

        // Average Case
        start = System.currentTimeMillis();
        System.out.println("Index : " + linearSearch(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 5));
        end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start));

    }

    public static void linearSearchRecursiveClient() {

        System.out.println("Linear search Recursive : ");
        // Worst Case
        double start = System.currentTimeMillis();
        System.out.println("Index : " + linearSearchR(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 9, 0));
        double end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start));

        // Best Case
        start = System.currentTimeMillis();
        System.out.println("Index : " + linearSearchR(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 1, 0));
        end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start));

        // Average Case
        start = System.currentTimeMillis();
        System.out.println("Index : " + linearSearchR(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 5, 0));
        end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start));

    }

    public static int linearSearch(int[] arr, int data) {
        int i = 0;
        for (int ele : arr) {
            if (ele == data)
                return i;
            i++;
        }
        return -1;
    }

    public static int linearSearchR(int[] arr, int data, int idx) {
        if (idx == arr.length)
            return -1;
        if (arr[idx] == data)
            return idx;
        return linearSearchR(arr, data, idx + 1);
    }

    public static int binarySearch(int[] arr, int data) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] < data)
                l = mid + 1;
            else if (arr[mid] > data)
                r = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    public static int binarySearchR(int[] arr, int data, int l, int r) {
        if (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] < data)
                return binarySearchR(arr, data, mid + 1, r);
            else if (arr[mid] > data)
                return binarySearchR(arr, data, l, mid - 1);
            else
                return mid;
        } else {
            return -1;
        }
    }

}
