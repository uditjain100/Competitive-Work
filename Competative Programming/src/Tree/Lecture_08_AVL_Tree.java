package Tree;

public class Lecture_08_AVL_Tree {

	public static void main(String[] args) {
		constructor();
	}

	public static class Node {
		int data;
		Node left;
		Node right;
		int height;

		public Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
			this.height = -1;
		}
	}

	private static Node root = null;

	public static void constructor() {

		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		root = construct(arr, 0, arr.length - 1);
		display(root);

	}

	public static int bal_factor(Node node) {
		if (node == null)
			return 0;
		return height(node.left) - height(node.right);
	}

	public static int height(Node node) {
		if (node == null)
			return -1;
		return Math.max(height(node.left), height(node.right)) + 1;
	}

	public static Node construct(int[] arr, int si, int ei) {
		if (si > ei)
			return null;
		int mid = (si + ei) / 2;
		Node node = new Node(arr[mid]);
		node.left = construct(arr, si, mid - 1);
		node.right = construct(arr, mid + 1, ei);
		return node;
	}

	public static void display(Node node) {

		if (node == null)
			return;

		String str = "";
		str += (node.left != null) ? node.left.data : ".";
		str += " <- " + node.data + " -> ";
		str += (node.right != null) ? node.right.data : ".";
		System.out.println(str);

		display(node.left);
		display(node.right);
	}

	public static int maxEle(Node node) {
		if (node.right == null)
			return node.data;

		return maxEle(node.right);
	}

	public static Node addData(Node node, int data) {
		if (node == null)
			return new Node(data);

		if (data < node.data)
			addData(node.left, data);
		else
			addData(node.right, data);

		node.height = height(node);
		int bf = bal_factor(node);
		if (bf > 1 && data < node.left.data) {
			return rightRotate(node);
		}

		if (bf < -1 && data > node.right.data) {
			return leftRotate(node);
		}

		if (bf > 1 && data > node.left.data) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		if (bf < -1 && data < node.right.data) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		return node;
	}

	private static Node leftRotate(Node c) {

		Node b = c.right;
		Node T2 = b.left;

		b.left = c;
		c.right = T2;

		c.height = height(c);
		b.height = height(b);

		return b;
	}

	public static Node removeNode(Node node, int data) {

		if (node == null)
			return new Node(data);

		if (data < node.data)
			removeNode(node.left, data);
		else if (data > node.data)
			removeNode(node.right, data);
		else {
			if (node.left == null || node.right == null)
				return (node.left == null) ? node.right : node.left;

			int max = maxEle(node.left);
			node.data = max;
			node.left = removeNode(node.left, max);

		}

		return node;
	}

	public static Node rightRotate(Node c) {

		Node b = c.left;
		Node T3 = b.right;

		b.right = c;
		c.left = T3;

		c.height = height(c);
		b.height = height(b);

		return b;

	}

}
