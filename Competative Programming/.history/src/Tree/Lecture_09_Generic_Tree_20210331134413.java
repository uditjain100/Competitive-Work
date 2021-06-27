package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Lecture_09_Generic_Tree {

	public static void main(String[] args) {
		constructor();
	}

	public static class Node {
		int data;
		ArrayList<Node> childs;

		public Node(int data) {
			this.data = data;
			this.childs = new ArrayList<Lecture_09_Generic_Tree.Node>();
		}
	}

	private static Node root = null;

	public static void constructor() {

		int[] preOrder = { 10, 20, 30, -1, 40, -1, 50, -1, -1, 60, 70, -1, 80, 90, -1, 100, -1, -1, 110, -1, -1, 120,
				130, -1, -1, -1 };
		root = constuctTree(preOrder, 0);
		System.out.println("Tree Structure : ");
		display(root);
		System.out.print("Preorder : ");
		PreOrder(root);
		System.out.println();
		System.out.print("Levelorder : ");
		levelOrder(root);
		System.out.println();
		System.out.println("Size : " + size(root));
		System.out.println("Height : " + height(root));
		System.out.println("Find : " + find(root, 100));
		ArrayList<Integer> path = new ArrayList<Integer>();
		System.out.println("RTNP : " + RTNP(root, 100, path) + " : " + path);
		System.out.println("IsMirror : " + isMirror(root, root));
		// linearize(root);
		// display(root);
		flattern(root);
		display(root);
	}

	public static Node constuctTree(int[] preOrder, int idx) {
		Stack<Node> stack = new Stack<Lecture_09_Generic_Tree.Node>();
		for (int i = 0; i < preOrder.length - 1; i++) {
			if (preOrder[i] != -1) {
				Node node = new Node(preOrder[i]);
				stack.add(node);
			} else {
				Node rn = stack.pop();
				stack.peek().childs.add(rn);
			}
		}
		return stack.peek();
	}

	public static void display(Node node) {
		String str = "[" + node.data + "] -> ";
		for (Node child : node.childs)
			str += child.data + " , ";

		System.out.println(str);
		for (Node child : node.childs)
			display(child);

	}

	public static void PreOrder(Node node) {
		System.out.print(node.data + " , ");
		for (Node child : node.childs)
			PreOrder(child);
	}

	public static void levelOrder(Node node) {
		LinkedList<Node> queue = new LinkedList<Lecture_09_Generic_Tree.Node>();
		queue.addLast(node);

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Node rn = queue.removeFirst();
				System.out.print(rn.data + " , ");
				for (Node child : rn.childs) {
					queue.addLast(child);
				}
			}
		}

	}

	public static int size(Node node) {
		int s = 0;
		for (Node child : node.childs)
			s += size(child);

		return s + 1;
	}

	public static int height(Node node) {
		int ht = 0;
		for (Node child : node.childs) {
			int ch = height(child);
			ht = Math.max(ht, ch);
		}
		return ht + 1;
	}

	public static boolean find(Node node, int data) {

		if (node.data == data)
			return true;

		boolean res = false;
		for (Node child : node.childs)
			res = res || find(child, data);

		return res;
	}

	// Root to Node Path
	public static boolean RTNP(Node node, int data, ArrayList<Integer> path) {
		if (node.data == data) {
			path.add(node.data);
			return true;
		}

		boolean res = false;
		path.add(node.data);
		for (Node child : node.childs) {
			res = res || RTNP(child, data, path);
		}

		if (!res)
			path.remove(path.size() - 1);
		return res;
	}

	public static boolean isMirror(Node node1, Node node2) {
		if (node1.childs.size() != node2.childs.size() || node1.data != node2.data)
			return false;
		boolean res = false;
		for (int i = 0, j = node2.childs.size() - 1; i < j; i++, j--)
			res = res && isMirror(node1.childs.get(i), node2.childs.get(j));
		return res;
	}

	public static Node linearize(Node node) {
		if (node.childs.size() == 0)
			return node;
		Node lastTail = linearize(node.childs.get(node.childs.size() - 1));
		for (int i = node.childs.size() - 2; i > 0; i--) {
			Node secondLast = linearize(node.childs.get(i - 1));
			secondLast.childs.add(node.childs.get(i + 1));
			node.childs.remove(node.childs.size() - 1);
		}
		return lastTail;
	}

	public static void flattern(Node node) {
		ArrayList<Node> allChilds = new ArrayList<Lecture_09_Generic_Tree.Node>();
		for (Node child : node.childs) {
			flattern(child);
			allChilds.add(child);
			for (Node ch : child.childs) {
				allChilds.add(ch);
			}
			child.childs.clear();
		}
		node.childs.clear();
		node.childs = allChilds;
	}

}
