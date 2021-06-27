package Graph;

import java.util.ArrayList;

public class Practice {

    public static void main(String[] args) {

    }

    public static class Edge {
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void construct() {

    }

    public static void dfs(ArrayList<Edge>[] graph, int src, boolean[] visited) {

        visited[src] = true;

        for (Edge nbr : graph[src])
            if (!visited[nbr.v])
                dfs(graph, nbr, visited);

    }

}
