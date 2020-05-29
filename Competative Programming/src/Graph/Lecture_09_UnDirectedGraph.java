package Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class Lecture_09_UnDirectedGraph {

	public static void main(String[] args) {
		constructor();
	}

	public static class Edge {
		int v;
		int w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	public static int N = 7;
	public static ArrayList<Edge>[] graph;

	public static void constructor() {
		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<Edge>();
		}

		addEdge(graph, 0, 1, 10);
		addEdge(graph, 0, 3, 10);
		addEdge(graph, 1, 2, 10);
		addEdge(graph, 2, 3, 40);
		addEdge(graph, 3, 4, 2);
		addEdge(graph, 4, 5, 2);
		addEdge(graph, 4, 6, 3);
		addEdge(graph, 5, 6, 8);
		addEdge(graph, 2, 5, 100);
		display(graph);
		System.out.println(containsEdge(graph, 0, 2));
		System.out.println(hasPath(graph, 0, 6, new boolean[N], "0, "));
		allPath(graph, 0, 6, new boolean[N], "0, ");
		System.out.print("PreOrder -> ");
		preOrder(graph, 0, new boolean[N], "0, ");
		hamiltonPathOrCycle(graph, 0, 0, 0, new boolean[N], "0, ");
		System.out.println("Connected Components -> ***************  ");
		GCC(graph);
		BFS(graph, 0, 6, new boolean[N]);
		BFS_Deliminator(graph, 0, 6, new boolean[N]);
		BFS_Level(graph, 0, 6, new boolean[N]);
		BFS_Best(graph, 0, 6, new boolean[N]);
	}

	public static void display(ArrayList<Edge>[] graph) {

		int count = 0;
		for (ArrayList<Edge> arr : graph) {
			System.out.print(count++ + " -> ");
			System.out.print("[");
			for (int i = 0; i < arr.size(); i++) {
				System.out.print("{" + arr.get(i).v + "," + arr.get(i).w + "} , ");
			}
			System.out.println("]");
		}
	}

	public static boolean containsEdge(ArrayList<Edge>[] graph, int u, int v) {

		boolean res1 = false;
		boolean res2 = false;
		for (Edge nbr : graph[u]) {
			if (nbr.v == v) {
				res1 = true;
			}
		}
		for (Edge nbr : graph[v]) {
			if (nbr.v == u) {
				res2 = true;
			}
		}
		if (res1 && res2) {
			return true;
		}
		return false;
	}

	public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
		graph[u].add(new Edge(v, w));
		graph[v].add(new Edge(u, w));
	}

	public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
		graph[u].remove(v);
		graph[v].remove(u);
	}

	public static void removeVertex(ArrayList<Edge>[] graph, int u) {
		while (graph[u].size() != 0) {
			removeEdge(graph, u, graph[u].get(0).v);
		}
	}

	public static boolean hasPath(ArrayList<Edge>[] graph, int u, int v, boolean[] visited, String path) {
		if (u == v) {
			System.out.println(path);
			return true;
		}
		visited[u] = true;
		boolean res = false;
		for (Edge nbr : graph[u]) {
			if (!visited[nbr.v]) {
				res = res || hasPath(graph, nbr.v, v, visited, path + nbr.v + ", ");
			}
		}
		visited[u] = false;
		return res;
	}

	public static void allPath(ArrayList<Edge>[] graph, int u, int v, boolean[] visited, String path) {
		if (u == v) {
			System.out.println(path);
		}
		visited[u] = true;
		for (Edge nbr : graph[u]) {
			if (!visited[nbr.v]) {
				allPath(graph, nbr.v, v, visited, path + nbr.v + ", ");
			}
		}
		visited[u] = false;
	}

	public static void preOrder(ArrayList<Edge>[] graph, int src, boolean[] visited, String path) {
		System.out.println(path);
		visited[src] = true;
		for (Edge nbr : graph[src]) {
			if (!visited[nbr.v]) {
				preOrder(graph, nbr.v, visited, path + nbr.v + ", ");
			}
		}
		visited[src] = false;
	}

	// Highest Weight , Lowest Weight , Ceiling , Flour
	public static class Things {

		public static int hw = Integer.MIN_VALUE;
		public static int lw = Integer.MAX_VALUE;
		public static int ceil = Integer.MIN_VALUE;
		public static int flour = Integer.MAX_VALUE;

	}

	public static void allThings(ArrayList<Edge>[] graph, int weight, int src, int dst, boolean[] visited, int data) {

		if (src == dst) {
			System.out.println();
			Things.hw = Math.max(Things.hw, weight);
			Things.lw = Math.max(Things.lw, weight);
			if (data < weight) {
				Things.ceil = Math.min(Things.ceil, weight);
			}
			if (data > weight) {
				Things.flour = Math.min(Things.flour, weight);
			}
		}

		visited[src] = true;
		for (Edge nbr : graph[src]) {
			if (!visited[nbr.v]) {
				allThings(graph, weight + nbr.w, nbr.v, dst, visited, data);
			}
		}
		visited[src] = false;

	}

	public static void hamiltonPathOrCycle(ArrayList<Edge>[] graph, int src, int count, int original_src,
			boolean[] visited, String path) {

		if (count == graph.length - 1) {
			if (containsEdge(graph, src, original_src)) {
				System.out.println("Hamilton Cycle exists -> " + path);
			} else {
				System.out.println("Hamilton Path exists -> " + path);
			}
		}

		visited[src] = true;
		for (Edge nbr : graph[src]) {
			if (!visited[nbr.v]) {
				hamiltonPathOrCycle(graph, nbr.v, count + 1, original_src, visited, path + nbr.v + ", ");
			}
		}
		visited[src] = false;

	}

	public static void dfs_for_gcc(ArrayList<Edge>[] graph, int src, boolean[] visited, ArrayList<Integer> list) {
		visited[src] = true;
		list.add(src);
		for (Edge nbr : graph[src]) {
			if (!visited[nbr.v]) {
				dfs_for_gcc(graph, nbr.v, visited, list);
			}
		}
	}

	// Get Connected Components
	public static void GCC(ArrayList<Edge>[] graph) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		boolean[] visited = new boolean[graph.length];
		for (int i = 0; i < graph.length; i++) {
			if (!visited[i]) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				dfs_for_gcc(graph, i, visited, list);
				res.add(list);
			}
		}
		for (ArrayList<Integer> path : res) {
			System.out.println(path);
		}

	}

	public static class BFS_Pair {

		int vtx;
		String path;
		int level;

		public BFS_Pair(int vtx, String path) {
			this.vtx = vtx;
			this.path = path;
		}

		public BFS_Pair(int vtx, String path, int level) {
			this.vtx = vtx;
			this.path = path;
			this.level = level;
		}
	}

	// Basic breadth first search
	// It is useful for finding shortest path
	public static void BFS(ArrayList<Edge>[] graph, int src, int dst, boolean[] visited) {

		LinkedList<BFS_Pair> queue = new LinkedList<>();
		queue.addLast(new BFS_Pair(src, "" + src + ", "));

		int noc = 0;
		int nop = 0;

		while (!queue.isEmpty()) {
			BFS_Pair rp = queue.removeFirst();

			if (visited[rp.vtx]) {
				noc++;
				System.out.println("Cycle Exists (" + noc + ")-> " + rp.path);
				continue;
			}

			if (rp.vtx == dst) {
				nop++;
				System.out.println("Shortest Path (" + nop + ")-> " + rp.path);
			}

			visited[rp.vtx] = true;
			for (Edge nbr : graph[rp.vtx]) {
				if (!visited[nbr.v]) {
					queue.addLast(new BFS_Pair(nbr.v, rp.path + nbr.v + ", "));
				}
			}
		}

	}

	// Breadth first search for levels (Deliminator Method)
	public static void BFS_Deliminator(ArrayList<Edge>[] graph, int src, int dst, boolean[] visited) {

		LinkedList<BFS_Pair> queue = new LinkedList<>();
		queue.addLast(new BFS_Pair(src, "" + src + ", "));
		queue.addLast(null);

		int noc = 0;
		int nop = 0;
		int level = 0;

		while (!queue.isEmpty()) {

			if (queue.size() == 1 && queue.getFirst() == null) {
				queue.removeFirst();
				continue;
			}

			BFS_Pair rp = queue.removeFirst();

			if (visited[rp.vtx]) {
				noc++;
				System.out.println("Cycle Exists (" + noc + ")-> " + rp.path);
				continue;
			}

			if (rp.vtx == dst) {
				nop++;
				System.out.println("Shortest Path (" + nop + ")-> " + rp.path);
			}

			visited[rp.vtx] = true;
			for (Edge nbr : graph[rp.vtx]) {
				if (!visited[nbr.v]) {
					queue.addLast(new BFS_Pair(nbr.v, rp.path + nbr.v + ", "));
				}
			}

			if (queue.getFirst() == null) {
				level++;
				queue.removeFirst();
				queue.addLast(null);
			}

		}
		System.out.println("Number of Levels -> " + (level + 1));

	}

	// bfs_pair is modified which have level property also
	public static void BFS_Level(ArrayList<Edge>[] graph, int src, int dst, boolean[] visited) {

		LinkedList<BFS_Pair> queue = new LinkedList<>();
		queue.addLast(new BFS_Pair(src, "" + src + ", ", 1));

		int noc = 0;
		int nop = 0;

		while (!queue.isEmpty()) {
			BFS_Pair rp = queue.removeFirst();

			if (visited[rp.vtx]) {
				noc++;
				System.out.println("Cycle Exists (" + noc + ")-> " + rp.path + " Level No. -> " + rp.level);
				continue;
			}

			if (rp.vtx == dst) {
				nop++;
				System.out.println("Shortest Path (" + nop + ")-> " + rp.path + " Level No. -> " + rp.level);
			}

			visited[rp.vtx] = true;
			for (Edge nbr : graph[rp.vtx]) {
				if (!visited[nbr.v]) {
					queue.addLast(new BFS_Pair(nbr.v, rp.path + nbr.v + ", ", rp.level + 1));
				}
			}
		}

	}

	// Best algorithm for level
	public static void BFS_Best(ArrayList<Edge>[] graph, int src, int dst, boolean[] visited) {

		LinkedList<Integer> queue = new LinkedList<>();
		queue.addLast(src);

		int level = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int rvtx = queue.removeFirst();

				if (visited[rvtx]) {
					continue;
				}

				if (rvtx == dst) {
					System.out.println("Number of Levels -> " + level);
				}

				visited[rvtx] = true;
				for (Edge edge : graph[rvtx]) {
					if (!visited[edge.v]) {
						queue.addLast(edge.v);
					}
				}
			}
			level++;
		}
	}

}
