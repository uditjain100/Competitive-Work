package Algorithms;

import java.util.Stack;

public class TowerOfHanoi {

    public static void main(String[] args) {
        System.out.println("Steps Required : " + TOH(3, 'A', 'B', 'C'));
    }

    public static int TOH(int n, char a, char b, char c) {
        int count = 0;
        if (n - 1 > 0)
            count += TOH(n - 1, a, c, b);
        System.out.println("Move Plate from " + a + " Poll to " + b + " Poll.");
        if (n - 1 > 0)
            count += TOH(n - 1, c, b, a);
        return count + 1;
    }

    public static class pair {
        int n;
        char a;
        char b;
        char c;
        boolean atc;
        boolean print;
        boolean ctb;

        public pair(int n, char a, char b, char c, boolean atc, boolean print, boolean ctb) {
            this.n = n;
            this.a = a;
            this.b = b;
            this.c = c;
            this.atc = atc;
            this.print = print;
            this.ctb = ctb;
        }
    }

    public static int iterative_toh(int n, char a, char b, char c) {

        Stack<pair> stack = new Stack<>();
        stack.push(new pair(n, a, b, c, false, false, false));
        while (stack.isEmpty()) {
            if (!stack.peek().atc) {
                stack.peek().atc = true;
                if (stack.peek().n != 1)
                    stack.push(new pair(stack.peek().n - 1, a, c, b, false, false, false));
            } else if (!stack.peek().print) {

            } else if (!stack.peek().ctb) {

            } else {

            }
        }

    }

}
