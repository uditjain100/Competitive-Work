package application;

public class aap {
    public static void main(String[] args) {
        System.out.println("Hellllllloooooooooo ...");
        System.out.println(fun(1000100));
    }

    public static int fun(int b) {
        int d = 0;
        int n = 0;
        while (b != 0) {
            int rem = b % 10;
            d += rem * Math.pow(2, n);
            b = b / 10;
            n++;
        }
        return d;
    }
}
