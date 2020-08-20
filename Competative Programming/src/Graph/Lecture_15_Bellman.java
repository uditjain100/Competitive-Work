package Graph;

import java.util.Arrays;

public class Lecture_15_Bellman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static boolean Bellman_Ford(int[][] graph, int src) {

		int[][] dp = new int[graph.length][graph.length + 1];
		for (int[] d : dp)
			Arrays.fill(d, Integer.MAX_VALUE);
		dp[src][0] = 0;
		boolean isNegativeCycle = false;

		for (int i = 1; i <= graph.length; i++) {
			for (int j = 0; j < graph.length; j++)
				dp[j][i] = dp[j][i - 1];
			for (int[] a : graph) {
				if (dp[a[0]][i - 1] == Integer.MAX_VALUE)
					continue;
				int temp = dp[a[1]][i];
				dp[a[1]][i] = Math.min(dp[a[1]][i], dp[a[1]][i - 1] + a[2]);
				if (i == graph.length && temp != dp[a[1]][i])
					isNegativeCycle = true;
			}
		}
		return isNegativeCycle;
	}

}
