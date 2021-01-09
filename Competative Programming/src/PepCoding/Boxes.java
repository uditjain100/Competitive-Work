package PepCoding;

public class Boxes {

    public static void main(String[] args) {
        int ans = 0;
        // ans = ballsCombination(new int[5], 0, 1, 3);
        // ans = ballsCombination2(5, 0, 1, 3, "");
        // ans = ballsPermutations(new int[5], 1, 3);
        // ans = ballsPermutations2(5, new boolean[3], 1, 3, 0, "");
        System.out.println(ans);
    }

    // 1. Here we are choosing boxes and finding combinations

    public static int ballsCombination(int[] boxes, int idx, int count, int k) {
        if (count == k + 1) {
            for (int ele : boxes)
                System.out.print(ele);
            System.out.println();
            return 1;
        }

        int res = 0;
        for (int i = idx; i < boxes.length; i++) {
            boxes[i] = count;
            res += ballsCombination(boxes, i + 1, count + 1, k);
            boxes[i] = 0;
        }
        return res;
    }

    // 2. Here we are choosing items and finding combinations

    public static int ballsCombination2(int n, int idx, int count, int k, String ans) {

        if (idx == n) {
            if (count == k + 1) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int res = 0;
        if (count >= k + 1) {
            res += ballsCombination2(n, idx + 1, count, k, ans + "0");
        } else {
            res += ballsCombination2(n, idx + 1, count + 1, k, ans + (count));
            res += ballsCombination2(n, idx + 1, count, k, ans + "0");
        }
        return res;
    }

    // 3.Here we are choosing boxes and finding permutations

    public static int ballsPermutations(int[] boxes, int count, int k) {

        if (count == k + 1) {
            for (int ele : boxes)
                System.out.print(ele);
            System.out.println();
            return 1;
        }

        int res = 0;
        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i] == 0) {
                boxes[i] = count;
                res += ballsPermutations(boxes, count + 1, k);
                boxes[i] = 0;
            }
        }
        return res;
    }

    // 4.Here we are choosing items and finding permutations

    public static int ballsPermutations2(int n, boolean[] items, int count, int k, int idx, String ans) {

        if (idx == n) {
            if (count == k + 1) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int res = 0;
        for (int i = 0; i < items.length; i++) {
            if (!items[i]) {
                items[i] = true;
                res += ballsPermutations2(n, items, count + 1, k, idx + 1, ans + (i + 1));
                items[i] = false;
            }
        }
        res += ballsPermutations2(n, items, count, k, idx + 1, ans + 0);
        return res;
    }

    

}
