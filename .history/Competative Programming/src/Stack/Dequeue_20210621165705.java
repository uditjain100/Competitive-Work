package Stack;

import java.util.ArrayDeque;

public class Dequeue {
    public static void main(String[] args) {

    }

    public static void slidingMax(int[] arr, int k) {

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            while (queue.isEmpty() && arr[queue.getLast()] < arr[i])
                queue.removeLast();
            queue.addLast(i);
        }
    }

}
