package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Lecture_12_UnionFind {

	public static void main(String[] args) {
		constructor();
	}

	public static int[] parent;
	public static int[] setSize;

	public Lecture_12_UnionFind(int n) {
		parent = new int[n];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
		setSize = new int[n];
	}

	public static int findParent(int vtx) {
		if (parent[vtx] == vtx)
			return vtx;
		return parent[vtx] = findParent(parent[vtx]);
	}

	public static void mergeSet(int l1, int l2) {
		if (setSize[l1] < setSize[l2]) {
			parent[l1] = l2;
			setSize[l2] += setSize[l1];
		} else {
			parent[l2] = l1;
			setSize[l1] += setSize[l2];
		}
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

	@SuppressWarnings("unchecked")
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

	public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
		graph[u].add(new Edge(v, w));
		graph[v].add(new Edge(u, w));
	}

	// Kruskal Algorithm
	public static ArrayList<Edge>[] MST(int[][] arr) {

		// for ascending => this - other
		// for descending => other - this
		// Sorting on the basis of weight of edges
		Arrays.sort(arr, (int[] a, int[] b) -> {
			return a[2] - b[2];
		});

		parent = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			parent[i] = i;
		}
		@SuppressWarnings("unchecked")
		ArrayList<Edge>[] mst = new ArrayList[7];
		for (int i = 0; i < mst.length; i++) {
			mst[i] = new ArrayList<>();
		}

		for (int i = 0; i < arr.length; i++) {
			int p1 = findParent(arr[i][0]);
			int p2 = findParent(arr[i][1]);
			if (p1 != p2) {
				mergeSet(arr[i][0], arr[i][1]);
				addEdge(mst, arr[i][0], arr[i][1], arr[i][2]);
			}
		}
		return mst;
	}

	public static class Node {
		int src;
		int parent;
		int weight;
		int wsf;

		public Node(int src, int parent, int weight) {
			this.src = src;
			this.parent = parent;
			this.weight = weight;
		}

		public Node(int src, int parent, int weight, int wsf) {
			this.src = src;
			this.parent = parent;
			this.weight = weight;
			this.wsf = wsf;
		}
	}

	// Source Dependent
	public static ArrayList<Edge>[] Dijikstra(ArrayList<Edge>[] graph, int src) {

		@SuppressWarnings("unchecked")
		ArrayList<Edge>[] d_graph = new ArrayList[graph.length];
		for (int i = 0; i < d_graph.length; i++) {
			d_graph[i] = new ArrayList<>();
		}
		boolean[] visited = new boolean[graph.length];
		PriorityQueue<Node> pqueue = new PriorityQueue<>((Node a, Node b) -> {
			return a.wsf - b.wsf;
		});
		pqueue.add(new Node(src, -1, 0, 0));

		while (!pqueue.isEmpty()) {
			int size = pqueue.size();
			while (size-- > 0) {
				Node rn = pqueue.poll();
				if (visited[rn.src]) {
					continue;
				}
				if (rn.parent != -1) {
					addEdge(d_graph, rn.src, rn.parent, rn.weight);
				}
				visited[rn.src] = true;
				for (Edge edge : graph[rn.src]) {
					if (!visited[edge.v]) {
						pqueue.add(new Node(edge.v, rn.src, edge.w, rn.weight + edge.w));
					}
				}
			}
		}
		return d_graph;
	}

	// Prim's Algo is Source Independent
	// Prims's Algo and Kruskal Algo both are used for finding MSTrees but Prim:s
	// Algo should be used when we
	// don't have to create another refrence tree

	public static ArrayList<Edge>[] Prims(ArrayList<Edge>[] graph, int src) {

		@SuppressWarnings("unchecked")
		ArrayList<Edge>[] p_graph = new ArrayList[graph.length];
		for (int i = 0; i < p_graph.length; i++) {
			p_graph[i] = new ArrayList<>();
		}
		boolean[] visited = new boolean[graph.length];
		PriorityQueue<Node> pqueue = new PriorityQueue<>((Node a, Node b) -> {
			return a.weight - b.weight;
		});
		pqueue.add(new Node(src, -1, 0));

		while (!pqueue.isEmpty()) {
			int size = pqueue.size();
			while (size-- > 0) {
				Node rn = pqueue.poll();
				if (visited[rn.src]) {
					continue;
				}
				if (rn.parent != -1) {
					addEdge(p_graph, rn.src, rn.parent, rn.weight);
				}
				visited[rn.src] = true;
				for (Edge edge : graph[rn.src]) {
					if (!visited[edge.v]) {
						pqueue.add(new Node(edge.v, rn.src, edge.w));
					}
				}
			}
		}
		return p_graph;
	}

}
