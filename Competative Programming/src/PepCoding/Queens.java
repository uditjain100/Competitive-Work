package PepCoding;

public class Queens {

    public static void main(String[] args) {
        int ans = 0;
        // ans = queenCombinations(2, 1, 0, 0, "");
        // ans = queenCombinations2(new boolean[2][2], 2, 1, 0, 0);
        // ans = queenCombinations3(2, 1, 0, "");
        // ans = queenCombinations4(new boolean[2][2], 2, 1, 0);
        // ans = nQueensCombinations(new boolean[4][4], 4, 1, 0);
        System.out.println(ans);

        int res = 0;
        // res = queenPermutataions(new int[2][2], 1, 2);
        // res = queenPermutataions2(new boolean[2], 2, 1, 0, 0, "");
        // res = queenPermutataions4(new boolean[2], 2, 1, 0, "");
        // res = nQueensPermutations(new boolean[4][4], new int[4][4], 1, 4);
        System.out.println(res);
    }

    public static void display(boolean[][] arr) {
        for (boolean[] a : arr) {
            for (boolean ele : a) {
                if (ele)
                    System.out.print(1 + " ");
                else
                    System.out.print(0 + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void display(int[][] arr) {
        for (int[] a : arr) {
            for (int ele : a) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 2. Here n queens are choosen to fill with (n*n) box
    // Similar to ballsCombination2 in Boxes

    public static int queenCombinations(int n, int count, int r, int c, String ans) {

        if (r == n) {
            if (count == n + 1) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        
        int res = 0;
        if (c == n) {
            res += queenCombinations(n, count, r + 1, 0, ans + "\n");
            return res;
        }
        if (count <= n) {
            res += queenCombinations(n, count + 1, r, c + 1, ans + count);
            res += queenCombinations(n, count, r, c + 1, ans + "0");
        } else {
            res += queenCombinations(n, count, r, c + 1, ans + "0");
        }
        return res;
    }

    // 1. Here n boxes are choosen to fill with n queens
    // Similar to ballsCombination in Boxes

    public static int queenCombinations2(boolean[][] board, int n, int count, int r, int c) {

        if (r == n) {
            if (count == n + 1) {
                display(board);
                return 1;
            }
            return 0;
        }
        int res = 0;
        if (c == n) {
            res += queenCombinations2(board, n, count, r + 1, 0);
            return res;
        }
        if (count <= n) {
            board[r][c] = true;
            res += queenCombinations2(board, n, count + 1, r, c + 1);
            board[r][c] = false;
            res += queenCombinations2(board, n, count, r, c + 1);
        } else {
            res += queenCombinations2(board, n, count, r, c + 1);
        }
        return res;

    }

    // 4. Here n queens are choosen to fill by (n*n) box
    // Similar to queenCombinations2 but it is treated as ID array

    public static int queenCombinations3(int n, int count, int idx, String ans) {

        if (idx == (n * n)) {
            if (count == n + 1) {
                System.out.println(ans);
                System.out.println();
                return 1;
            }
            return 0;
        }
        int r = idx / n;
        int c = idx % n;
        if (r != 0 && c == 0)
            ans += "\n";
        int res = 0;
        if (count <= n) {
            res += queenCombinations3(n, count + 1, idx + 1, ans + count);
            res += queenCombinations3(n, count, idx + 1, ans + "0");
        } else {
            res += queenCombinations3(n, count, idx + 1, ans + "0");
        }
        return res;
    }

    // 3. Here n boxes are choosen to fill with n queens
    // Similar to queenCombinations but it is treated as ID array

    public static int queenCombinations4(boolean[][] board, int n, int count, int idx) {

        if (idx == (n * n)) {
            if (count == n + 1) {
                display(board);
                return 1;
            }
            return 0;
        }
        int r = idx / n;
        int c = idx % n;
        int res = 0;
        if (count <= n) {
            board[r][c] = true;
            res += queenCombinations4(board, n, count + 1, idx + 1);
            board[r][c] = false;
            res += queenCombinations4(board, n, count, idx + 1);
        } else {
            res += queenCombinations4(board, n, count, idx + 1);
        }
        return res;

    }

    public static boolean IsQueenSafe(boolean[][] arr, int row, int col) {
        int n = arr.length;
        int m = arr[0].length;
        if (arr[row][col])
            return false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][col])
                return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[row][i])
                return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (arr[i][j])
                return false;
        }
        for (int i = row + 1, j = col - 1; i < n && j >= 0; i++, j--) {
            if (arr[i][j])
                return false;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < m; i--, j++) {
            if (arr[i][j])
                return false;
        }
        for (int i = row + 1, j = col + 1; i < n && j < m; i++, j++) {
            if (arr[i][j])
                return false;
        }
        return true;
    }

    public static int nQueensCombinations(boolean[][] board, int n, int count, int idx) {

        if (idx == (n * n)) {
            if (count == n + 1) {
                display(board);
                return 1;
            }
            return 0;
        }
        int r = idx / n;
        int c = idx % n;
        int res = 0;
        if (count <= n) {
            if (IsQueenSafe(board, r, c)) {
                board[r][c] = true;
                res += nQueensCombinations(board, n, count + 1, idx + 1);
                board[r][c] = false;
            }
            res += nQueensCombinations(board, n, count, idx + 1);
        } else {
            res += nQueensCombinations(board, n, count, idx + 1);
        }
        return res;
    }
    // **********************************************************************************************

    // Permutations Questions

    // 1. Here n boxes are choosen to fill with n queens
    // Similar to ballsPermutations in Boxes

    public static int queenPermutataions(int[][] board, int count, int n) {

        if (count == n + 1) {
            display(board);
            return 1;
        }

        int res = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == 0) {
                    board[r][c] = count;
                    res += queenPermutataions(board, count + 1, n);
                    board[r][c] = 0;
                }
            }
        }
        return res;
    }

    // 2. Here n queens are choosen to fill with (n*n) box
    // Similar to ballsPermutations2 in Boxes

    public static int queenPermutataions2(boolean[] queens, int n, int count, int r, int c, String ans) {

        if (r == n) {
            if (count == n + 1) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        if (c == n)
            return queenPermutataions2(queens, n, count, r + 1, 0, ans + "\n");
        int res = 0;
        for (int i = 0; i < queens.length; i++) {
            if (!queens[i]) {
                queens[i] = true;
                res += queenPermutataions2(queens, n, count + 1, r, c + 1, ans + (i + 1));
                queens[i] = false;
            }
        }
        res += queenPermutataions2(queens, n, count, r, c + 1, ans + "0");
        return res;
    }

    // 3. Here n boxes are choosen to fill with n queens
    // Similar to queenPermutataions but it is treated as ID array

    public static int queenPermutataions3(int[][] board, int count, int n) {

        if (count == n + 1) {
            display(board);
            return 1;
        }

        int res = 0;
        for (int i = 0; i < (n * n); i++) {
            int r = i / n;
            int c = i % n;
            if (board[r][c] == 0) {
                board[r][c] = count;
                res += queenPermutataions3(board, count + 1, n);
                board[r][c] = 0;
            }
        }
        return res;
    }

    // 4. Here n queens are choosen to fill by (n*n) box
    // Similar to queenPermutataions2 but it is treated as ID array

    public static int queenPermutataions4(boolean[] queens, int n, int count, int idx, String ans) {

        if (idx == (n * n)) {
            if (count == n + 1) {
                System.out.println(ans);
                System.out.println();
                return 1;
            }
            return 0;
        }

        int r = idx / n;
        int c = idx % n;

        if (r != 0 && c == 0)
            ans += "\n";

        int res = 0;
        for (int i = 0; i < queens.length; i++) {
            if (!queens[i]) {
                queens[i] = true;
                res += queenPermutataions4(queens, n, count + 1, idx + 1, ans + (i + 1));
                queens[i] = false;
            }
        }
        res += queenPermutataions4(queens, n, count, idx + 1, ans + "0");
        return res;
    }

    public static int nQueensPermutations(boolean[][] chess, int[][] board, int count, int n) {

        if (count == n + 1) {
            display(board);
            return 1;
        }

        int res = 0;
        for (int i = 0; i < (n * n); i++) {
            int r = i / n;
            int c = i % n;
            if (IsQueenSafe(chess, r, c)) {
                chess[r][c] = true;
                board[r][c] = count;
                res += nQueensPermutations(chess, board, count + 1, n);
                board[r][c] = 0;
                chess[r][c] = false;
            }
        }
        return res;
    }

}
