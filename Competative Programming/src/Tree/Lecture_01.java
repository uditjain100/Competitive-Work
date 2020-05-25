package Tree;

import java.util.ArrayList;

public class Lecture_01 {

	public static void main(String[] args) {
		solve();
	}

	public static void solve() {

		int[] preOrder = { 10, 20, 40, -1, -1, 50, 80, -1, -1, 90, -1, -1, 30, 60, 100, -1, -1, -1, 70, 110, -1, -1,
				120, -1, -1 };
		Node root = constructTree(preOrder);
		Display(root);

		System.out.println("Size -> " + size(root));
		System.out.println("Height -> " + height(root));
		System.out.println("Maximum -> " + Max(root));
		System.out.println("Minimum -> " + Min(root));
		System.out.println("Find data -> " + find(root, 500));

		System.out.print("PreOrder -> ");
		preorder(root);
		System.out.println();
		System.out.print("InOrder -> ");
		inorder(root);
		System.out.println();
		System.out.print("PostOrder -> ");
		postorder(root);
		System.out.println();

		ArrayList<Node> list = new ArrayList<Lecture_01.Node>();
		RTNP(root, 100, list);
		System.out.println(list);
		System.out.println(RTNP_02(root, 100));
		System.out.println(RTNP_03(root, 100));
		System.out.println(LCA(root, 40, 90).data);

	}

	public static class Node {
		int data;
		Node left;
		Node right;

		public Node(int data) {
			this.data = data;
		}
	}

	public static int idx = 0;

	public static Node constructTree(int[] preOrder) {
		if (idx >= preOrder.length || preOrder[idx] == -1) {
			idx++;
			return null;
		}
		Node node = new Node(preOrder[idx++]);
		node.left = constructTree(preOrder);
		node.right = constructTree(preOrder);
		return node;
	}

	public static void Display(Node node) {
		if (node == null)
			return;

		String str = "";
		str += (node.left != null) ? node.left.data : ".";
		str += " <- " + node.data + " -> ";
		str += (node.right != null) ? node.right.data : ".";
		System.out.println(str);

		Display(node.left);
		Display(node.right);

	}

	public static int size(Node node) {
		if (node == null)
			return 0;
		return size(node.left) + size(node.right) + 1;
	}

	public static int height(Node node) {
		if (node == null)
//	here -1 indicated the height in terms of number of edges and if
//	it is 0 then this will be height in terms of number of nodes.
			return -1;
		return Math.max(height(node.left), height(node.right)) + 1;
	}

	public static int Max(Node node) {
		if (node == null)
			return Integer.MIN_VALUE;
		return Math.max(node.data, Math.max(Max(node.left), Max(node.right)));
	}

	public static int Min(Node node) {
		if (node == null)
			return Integer.MAX_VALUE;
		return Math.min(node.data, Math.min(Min(node.left), Min(node.right)));
	}

	// Root to NOde path algo
	public static boolean find(Node node, int text) {
		if (node == null)
			return false;
		if (node.data == text)
			return true;
		return find(node.left, text) || find(node.right, text);
	}

	public static void preorder(Node node) {

		if (node == null)
			return;

		System.out.print(node.data + " ");
		preorder(node.left);
		preorder(node.right);

	}

	public static void inorder(Node node) {

		if (node == null)
			return;

		inorder(node.left);
		System.out.print(node.data + " ");
		inorder(node.right);

	}

	public static void postorder(Node node) {

		if (node == null)
			return;

		postorder(node.left);
		postorder(node.right);
		System.out.print(node.data + " ");

	}

	public static boolean RTNP(Node node, int data, ArrayList<Node> path) {

		if (node == null) {
			return false;
		}

		if (node.data == data) {
			path.add(node);
			return true;
		}

		boolean res = RTNP(node.left, data, path) || RTNP(node.right, data, path);
		if (res)
			path.add(node);
		return res;
	}

	public static ArrayList<Node> RTNP_02(Node node, int data) {

		if (node == null) {
			return new ArrayList<Lecture_01.Node>();
		}

		if (node.data == data) {
			ArrayList<Node> br = new ArrayList<Lecture_01.Node>();
			br.add(node);
			return br;
		}

		ArrayList<Node> lrr = RTNP_02(node.left, data);
		if (lrr.size() != 0) {
			lrr.add(node);
			return lrr;
		}
		ArrayList<Node> rrr = RTNP_02(node.right, data);
		if (rrr.size() != 0) {
			rrr.add(node);
			return rrr;
		}

		return new ArrayList<Lecture_01.Node>();
	}

	public static ArrayList<Node> RTNP_03(Node node, int data) {

		ArrayList<Node> res = new ArrayList<Lecture_01.Node>();

		if (node == null) {
			return res;
		}

		if (node.data == data) {
			res.add(node);
			return res;
		}

		res = RTNP_03(node.left, data);
		if (res.size() != 0) {
			res.add(node);
			return res;
		}
		res = RTNP_03(node.right, data);
		if (res.size() != 0) {
			res.add(node);
			return res;
		}

		return res;
	}

	// Lowest Common Ancestor
	public static Node LCA(Node node, int p, int q) {
		ArrayList<Node> path1 = new ArrayList<Lecture_01.Node>();
		ArrayList<Node> path2 = new ArrayList<Lecture_01.Node>();
		RTNP(node, p, path1);
		RTNP(node, q, path2);

		int i = path1.size() - 1;
		int j = path2.size() - 1;

		Node ans = null;

		while (i >= 0 && j >= 0) {
			if (path1.get(i).data != path2.get(j).data)
				break;
			ans = path1.get(i);
			i--;
			j--;
		}
		return ans;
	}
}
