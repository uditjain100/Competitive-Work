package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Lecture_11_DirectedGraph {

	public static void main(String[] args) {
		constructor();
	}

	public static int N = 16;
	public static ArrayList<Integer>[] graph;

	public static void constructor() {
		graph = new ArrayList[N];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		// Graph 01 :
		// addEdge(graph, 1, 0);
		// addEdge(graph, 2, 1);
		// addEdge(graph, 3, 1);
		// addEdge(graph, 5, 4);
		// addEdge(graph, 5, 2);
		// addEdge(graph, 6, 4);
		// addEdge(graph, 6, 3);
		// addEdge(graph, 7, 5);
		// addEdge(graph, 7, 6);
		// display(graph);
		// System.out.println(inDegree(graph));
		// System.out.println(outDegree(graph));
		// display(reverseGraph(graph));
		// System.out.println(topological_sort(graph));
		// isCycle(graph);

		// Graph 02 :
		addEdge(graph, 1, 1);
		addEdge(graph, 1, 3);
		addEdge(graph, 2, 1);
		addEdge(graph, 3, 2);
		addEdge(graph, 3, 5);
		addEdge(graph, 4, 1);
		addEdge(graph, 4, 2);
		addEdge(graph, 4, 12);
		addEdge(graph, 4, 13);
		addEdge(graph, 5, 8);
		addEdge(graph, 5, 6);
		addEdge(graph, 6, 7);
		addEdge(graph, 6, 8);
		addEdge(graph, 6, 10);
		addEdge(graph, 7, 10);
		addEdge(graph, 8, 9);
		addEdge(graph, 8, 10);
		addEdge(graph, 9, 5);
		addEdge(graph, 9, 11);
		addEdge(graph, 10, 11);
		addEdge(graph, 10, 9);
		addEdge(graph, 10, 14);
		addEdge(graph, 11, 12);
		addEdge(graph, 13, 11);
		addEdge(graph, 11, 14);
		addEdge(graph, 12, 13);
		addEdge(graph, 13, 15);
		addEdge(graph, 14, 13);
		addEdge(graph, 15, 14);
		display(graph);
		SCC(graph);
	}

	public static void display(ArrayList<Integer>[] graph) {
		int count = 0;
		for (ArrayList<Integer> vtx : graph) {
			System.out.print(count++ + " -> ");
			System.out.println(vtx);
		}
	}

	public static boolean containsEdge(ArrayList<Integer>[] graph, int u, int v) {

		for (Integer nbr : graph[u]) {
			if (nbr == v) {
				return true;
			}
		}

		return false;
	}

	public static void addEdge(ArrayList<Integer>[] graph, int u, int v) {
		graph[u].add(v);
	}

	public static void removeEdge(ArrayList<Integer>[] graph, int u, int v) {
		graph[u].remove(v);
	}

	public static void removeVertex(ArrayList<Integer>[] graph, int u) {
		while (graph[u].size() != 0) {
			if (containsEdge(graph, graph[u].get(0), u)) {
				removeEdge(graph, graph[u].get(0), u);
			}
			removeEdge(graph, u, graph[u].get(0));
		}
	}

	public static ArrayList<Integer> inDegree(ArrayList<Integer>[] graph) {
		ArrayList<Integer> ID = new ArrayList<Integer>(N);
		for (int i = 0; i < N; i++) {
			ID.add(0);
		}
		for (ArrayList<Integer> vtx : graph) {
			for (Integer nbr : vtx) {
				ID.set(nbr, ID.get(nbr) + 1);
			}
		}
		return ID;
	}

	public static ArrayList<Integer> outDegree(ArrayList<Integer>[] graph) {
		ArrayList<Integer> OD = new ArrayList<Integer>(N);
		for (int i = 0; i < N; i++) {
			OD.add(0);
		}
		int i = 0;
		for (ArrayList<Integer> vtx : graph) {
			OD.set(i++, vtx.size());
		}
		return OD;
	}

	public static ArrayList<Integer>[] reverseGraph(ArrayList<Integer>[] graph) {

		ArrayList<Integer>[] reversed_graph = new ArrayList[N];
		for (int i = 0; i < reversed_graph.length; i++) {
			reversed_graph[i] = new ArrayList<Integer>();
		}

		int i = 0;
		for (ArrayList<Integer> vtx : graph) {
			for (Integer nbr : vtx) {
				addEdge(reversed_graph, nbr, i);
			}
			i++;
		}
		return reversed_graph;
	}

	public static void ts_dfs(ArrayList<Integer>[] graph, int src, ArrayList<Integer> path, boolean[] visited) {
		visited[src] = true;
		for (Integer nbr : graph[src])
			if (!visited[nbr])
				ts_dfs(graph, nbr, path, visited);
		path.add(src);
	}

	public static ArrayList<Integer> topological_sort(ArrayList<Integer>[] graph) {
		ArrayList<Integer> ts = new ArrayList<Integer>();
		boolean[] visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				ts_dfs(graph, i, ts, visited);
			}
		}
		Collections.reverse(ts);
		return ts;
	}

	// Khan's Algorithm (BFS for topological sort)
	// Only for detection of Cycle but not for counting of number of cycles
	public static void isCycle(ArrayList<Integer>[] graph) {

		ArrayList<Integer> in_Degree = inDegree(graph);
		LinkedList<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < in_Degree.size(); i++) {
			if (in_Degree.get(i) == 0) {
				queue.addLast(i);
			}
		}
		ArrayList<Integer> path_ts = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int rvtx = queue.removeFirst();
				path_ts.add(rvtx);
				for (Integer nbr : graph[rvtx]) {
					in_Degree.set(nbr, in_Degree.get(nbr) - 1);
					if (in_Degree.get(nbr) == 0) {
						queue.addLast(nbr);
					}
				}
			}
		}

		if (path_ts.size() != N) {
			System.out.println("Cycle Exists!!");
		} else {
			Collections.reverse(path_ts);
			System.out.println("Topological sort -> " + path_ts);
		}

	}

	// KosaRaju Algorithm
	public static void SCC(ArrayList<Integer>[] graph) {

		ArrayList<Integer> topologicalSort = topological_sort(graph);
		ArrayList<Integer>[] reversed_graph = reverseGraph(graph);
		boolean[] visited = new boolean[N];
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < topologicalSort.size(); i++) {
			ArrayList<Integer> path = new ArrayList<Integer>();
			if (!visited[topologicalSort.get(i)]) {
				ts_dfs(reversed_graph, topologicalSort.get(i), path, visited);
			}
			res.add(path);
		}
		Iterator<ArrayList<Integer>> i = res.iterator();
		while (i.hasNext()) {
			ArrayList<Integer> vtx = i.next();
			if (vtx.size() == 0) {
				i.remove();
			}
		}
		for (ArrayList<Integer> component : res) {
			System.out.println(component);
		}

	}
}
