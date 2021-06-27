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

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void dfs(ArrayList<Edge>[] graph, int src, boolean[] visited) {

        visited[src] = true;

        System.out.println(src);
        for (Edge nbr : graph[src])
            if (!visited[nbr.v])
                dfs(graph, nbr.v, visited);

    }

}
