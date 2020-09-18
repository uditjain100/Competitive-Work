package Graph;

import java.util.Arrays;

public class Floyd_Warshell {

    public static void main(String[] args) {
        display(FloydWarshell(new int[4][4]));
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

    public static int[][] FloydWarshell(int[][] graph) {
        int[][] tempGraph = new int[graph.length][graph.length];
        for (int[] arr : tempGraph)
            Arrays.fill(arr, Integer.MAX_VALUE);
        for (int k = 0; k < graph.length; k++) {
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    if (k % 2 == 0)
                        graph[i][j] = Math.min(graph[i][j], tempGraph[i][k] + tempGraph[k][j]);
                    else
                        tempGraph[i][j] = Math.min(tempGraph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        if (graph.length % 2 == 1)
            return graph;
        return tempGraph;
    }

}
