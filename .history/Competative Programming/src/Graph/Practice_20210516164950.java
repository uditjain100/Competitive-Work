package Graph;

import java.util.ArrayList;

public class Practice {

    public static void main(String[] args) {
        ArrayList<Edge>[] graph = new ArrayList[25];
        for (int i = 0; i < 25; i++)
            graph[i] = new ArrayList<Edge>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 3);
        addEdge(graph, 5, 6, 8);
        addEdge(graph, 2, 5, 100);
        dfs(graph, 0, new boolean[25]);
    }

    public static class Edge {
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
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

    public static void haspath(ArrayList<Edge>[] graph, int src, int des, boolean[] visited, ArrayList<Integer> path) {
        visited[src] = true;

        for (Edge nbr : graph[src])
            if (!visited[nbr.v]) {
                path.add(nbr.v);
                haspath(graph, nbr.v, des, visited, path);
                path.remove(path.size() - 1);
            }
        visited[src] = false;

    }

}
