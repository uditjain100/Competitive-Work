package application;

public class aap {
    public static void main(String[] args) {
        System.out.println("Hellllllloooooooooo ...");
        System.out.println(fun(1000100));

        String line;

        DataInputStream in = new DataInputStream(System.in);

        int[] arr = new int[2];
        int i = 0;
        while ((line = in.readLine()) != null) {
            arr[i++] = (int) Integer.parseInt(line);
        }
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
