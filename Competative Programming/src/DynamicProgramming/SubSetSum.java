package DynamicProgramming;

public class SubSetSum {

    public static void main(String[] args) {

        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        System.out.println(SubsetSumOneSolution(arr, 9, 10, ""));
        System.out.println(SubsetSum(arr, 9, 10, ""));
        int[][] dp = new int[10][11];
        System.out.println(SubsetSumDP(arr, 9, 10, dp));
        display(dp);
        System.out.println(SubsetSumTable(arr, 10));
        System.out.println(SubsetSumOptimal(arr, 9, 10, SubsetSumCheck(arr, 10)));
    }

    public static void display(int[] arr) {
        for (int ele : arr)
            System.out.print(ele + ", ");
        System.out.println();
    }

    public static void display(int[][] arr) {
        for (int[] a : arr) {
            for (int ele : a)
                System.out.print(ele + ", ");
            System.out.println();
        }
        System.out.println();
    }

    // Method 01
    public static boolean SubsetSumOneSolution(int[] arr, int idx, int sum, String ans) {
        if (idx == 0 || sum == 0) {
            if (sum == 0) {
                System.out.println(ans);
                return true;
            }
            return false;
        }

        boolean res = false;
        if (sum - arr[idx - 1] >= 0)
            res = res || SubsetSumOneSolution(arr, idx - 1, sum - arr[idx - 1], ans + arr[idx - 1]);
        res = res || SubsetSumOneSolution(arr, idx - 1, sum, ans);
        return res;
    }

    // Method 02
    public static int SubsetSum(int[] arr, int idx, int sum, String ans) {
        if (idx == 0 || sum == 0) {
            if (sum == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int res = 0;
        if (sum - arr[idx - 1] >= 0)
            res += SubsetSum(arr, idx - 1, sum - arr[idx - 1], ans + arr[idx - 1]);
        res += SubsetSum(arr, idx - 1, sum, ans);
        return res;
    }

    // Method 03
    public static int SubsetSumDP(int[] arr, int idx, int sum, int[][] dp) {
        if (idx == 0 || sum == 0) {
            if (sum == 0)
                return dp[idx][sum] = 1;
            return 0;
        }

        if (dp[idx][sum] != 0)
            return dp[idx][sum];

        int res = 0;
        if (sum - arr[idx - 1] >= 0)
            res += SubsetSumDP(arr, idx - 1, sum - arr[idx - 1], dp);
        res += SubsetSumDP(arr, idx - 1, sum, dp);
        return dp[idx][sum] = res;
    }

    // Method 04
    public static int SubsetSumTable(int[] arr, int target) {
        int[][] dp = new int[arr.length + 1][target + 1];
        for (int i = 0; i <= arr.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (i == 0 || j == 0) {
                    if (j == 0)
                        dp[i][j] = 1;
                    continue;
                }
                int res = 0;
                if (j - arr[i - 1] >= 0)
                    res += dp[i - 1][j - arr[i - 1]];
                res += dp[i - 1][j];
                dp[i][j] = res;
            }
        }
        display(dp);
        return dp[arr.length][target];
    }

    // Method 05
    public static int[][] SubsetSumCheck(int[] arr, int target) {
        boolean[][] dp = new boolean[arr.length + 1][target + 1];
        for (int i = 0; i <= arr.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (i == 0 || j == 0) {
                    if (j == 0)
                        dp[i][j] = true;
                    continue;
                }

                if (j - arr[i - 1] >= 0)
                    dp[i][j] = dp[i - 1][j - arr[i - 1]];
                dp[i][j] |= dp[i - 1][j];
            }
        }
        int[][] res = new int[arr.length + 1][target + 1];
        for (int i = 0; i <= arr.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (dp[i][j])
                    res[i][j] = 1;
                else
                    res[i][j] = 0;
            }
        }
        return res;
    }

    public static int SubsetSumOptimal(int[] arr, int idx, int sum, int[][] check) {
        if (idx == 0 || sum == 0) {
            if (sum == 0)
                return 1;
            return 0;
        }

        int res = 0;
        if (sum - arr[idx - 1] >= 0 && check[idx - 1][sum - arr[idx - 1]] == 1)
            res += SubsetSumOptimal(arr, idx - 1, sum - arr[idx - 1], check);
        if (check[idx - 1][sum] == 1)
            res += SubsetSumOptimal(arr, idx - 1, sum, check);
        return res;
    }

    

}
