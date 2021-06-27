package Stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class Dequeue {
    public static void main(String[] args) {

        int[] arr = { 2, 1, 8, 6, 9, 4, 3, 5 };
        int[] a = nextSmaller(arr);
        int[] b = previousSmaller(arr);
        for (int ele : a)
            System.out.print(ele + ", ");
        System.out.println();
        for (int ele : b)
            System.out.print(ele + ", ");

    }

    public static ArrayList<Integer> slidingMax(int[] arr, int k) {

        ArrayList<Integer> list = new ArrayList<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && arr[queue.getLast()] < arr[i])
                queue.removeLast();
            queue.addLast(i);
        }
        list.add(arr[queue.getFirst()]);
        for (int i = k; i < arr.length; i++) {
            if (queue.getFirst() == i - k)
                queue.removeFirst();
            while (!queue.isEmpty() && arr[queue.getLast()] < arr[i])
                queue.removeLast();
            queue.addLast(i);
            list.add(arr[queue.getFirst()]);
        }
        return list;
    }

    public static int[] nextSmaller(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            res[i] = -1;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i])
                arr[stack.pop()] = arr[i];
            stack.push(i);
        }
        return res;
    }

    public static int[] previousSmaller(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            res[i] = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i])
                arr[stack.pop()] = arr[i];
            stack.push(i);
        }
        return res;
    }

}
