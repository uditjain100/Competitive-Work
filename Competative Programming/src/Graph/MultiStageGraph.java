package Graph;

import java.util.ArrayList;

public class MultiStageGraph {

    public static void main(String[] args) {

    }

    public static int ShortestPath(ArrayList<Integer>[] graph, int[][] cost) {
        int[] dp = new int[graph.length];
        for (int i = graph.length - 2; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int j = i + 1; j < graph.length; j++)
                if (graph[i].contains(j))
                    min = Math.min(min, dp[j] + cost[i][j]);
            dp[i] = min;
        }
        return dp[0];
    }

}
