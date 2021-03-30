package application;

public class aap {
    public static void main(String[] args) {
        System.out.println("Hellllllloooooooooo ...");
        System.out.println(fun(1000100));
    }

    public static int fun(int binary) {
        int d = 0;
        int n = 0;
        while (binary != 0) {
            int rem = binary % 10;
            d += rem * Math.pow(2, n);
            binary = binary / 10;
            n++;
        }
        return d;
    }
}
