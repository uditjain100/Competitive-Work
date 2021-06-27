package Algorithms;

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

    public class pair {
        boolean a;
        boolean b;
        boolean c;

        public pair(boolean a, boolean b, boolean c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static int iterative_toh(int n, char a, char b, char c) {

        Stach<>



    }

}
