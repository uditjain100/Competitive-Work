package Graph;

import java.util.ArrayList;
import java.util.LinkedList;

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
        haspath(graph, 1, 6, new boolean[25], new ArrayList<>());
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
        if (src == des) {
            System.out.println(path);
            return;
        }
        visited[src] = true;

        for (Edge nbr : graph[src])
            if (!visited[nbr.v]) {
                path.add(nbr.v);
                haspath(graph, nbr.v, des, visited, path);
                path.remove(path.size() - 1);
            }
        visited[src] = false;
    }

    public static void bfs(ArrayList<Edge>[] graph, int src) {

        LinkedList<Integer> queue = new LinkedList();
        queue.add(src);
        boolean[] visited = new boolean[graph.length + 1];

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int rv = queue.removeFirst();

                if (visited[rv])
                    continue;
                System.out.println(rv);
                ;
                visited[rv] = true;
                for (Edge nbr : graph[rv])
                    if (!visited[nbr.v])
                        queue.addLast(nbr.v);
            }
        }
    }

}
