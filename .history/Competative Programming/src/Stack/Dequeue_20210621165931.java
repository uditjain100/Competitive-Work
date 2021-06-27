package Stack;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Dequeue {
    public static void main(String[] args) {

    }

    public static void slidingMax(int[] arr, int k) {

        ArrayList<Integer> list = new ArrayList<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            while (queue.isEmpty() && arr[queue.getLast()] < arr[i])
                queue.removeLast();
            queue.addLast(i);
        }
        list.add(arr[queue.getFirst()]);
        for (int i = k; i < arr.length; i++) {
            while (queue.isEmpty() && arr[queue.getLast()] < arr[i])
                queue.removeLast();
            queue.addLast(i);
        }
    }

}
