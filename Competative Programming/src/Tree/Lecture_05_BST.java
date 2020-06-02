package Tree;

import java.util.ArrayList;

import Tree.Lecture_05_BST.Node;

public class Lecture_05_BST {

	public static void main(String[] args) {
		constructor();
	}

	public static class Node {
		int data;
		Node left;
		Node right;

		public Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}

	private static Node root;

	public static void constructor() {

		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		root = construct(arr, 0, arr.length - 1);
		display(root);

		System.out.println(height(root));
		System.out.println(size(root));
		System.out.println(find(root, 5));
		System.out.println(find_it(root, 12));
		System.out.println(maxEle(root));
		System.out.println(minEle(root));
		ArrayList<Integer> res = new ArrayList<Integer>();
		rangeIn(root, 5, 13, res);
		System.out.println(res);
		System.out.println(RTNP(root, 9));
		ArrayList<Integer> path = new ArrayList<Integer>();
		RTNP(root, 9, path);
		System.out.println(path);
		System.out.println(LCA(root, 6, 7));
		System.out.println(LCA_IT(root, 6, 7));
		ArrayList<Integer> res02 = new ArrayList<Integer>();
		rangeIn_02(root, 5, 13, res02);
		System.out.println(res02);

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

	public static int height(Node node) {
		if (node == null)
			return -1;
		return Math.max(height(node.left), height(node.right)) + 1;
	}

	public static int size(Node node) {
		if (node == null)
			return 0;
		return size(node.left) + size(node.right) + 1;
	}

	public static boolean find(Node node, int data) {

		Node curr = node;
		if (curr == null)
			return false;

		boolean res = false;
		if (data > curr.data) {
			res = res || find(curr.right, data);
		} else if (data < curr.data) {
			res = res || find(curr.left, data);
		} else {
			return true;
		}
		return res;
	}

	public static boolean find_it(Node node, int data) {

		Node curr = node;
		while (curr != null) {
			if (curr.data > data) {
				curr = curr.left;
			} else if (curr.data < data) {
				curr = curr.right;
			} else {
				return true;
			}
		}
		return false;
	}

	public static int maxEle(Node node) {
		if (node.right == null)
			return node.data;

		return maxEle(node.right);
	}

	public static int minEle(Node node) {
		if (node.left == null)
			return node.data;

		return minEle(node.left);
	}

	public static ArrayList<Integer> RTNP(Node node, int data) {

		if (node == null)
			return new ArrayList<Integer>();

		if (node.data == data) {
			ArrayList<Integer> br = new ArrayList<>();
			br.add(node.data);
			return br;
		} else if (node.data > data) {
			ArrayList<Integer> lr = RTNP(node.left, data);
			if (lr.size() != 0) {
				lr.add(node.data);
				return lr;
			}
		} else {
			ArrayList<Integer> rr = RTNP(node.right, data);
			if (rr.size() != 0) {
				rr.add(node.data);
				return rr;
			}
		}
		return new ArrayList<Integer>();
	}

	public static void RTNP(Node node, int data, ArrayList<Integer> path) {

		if (node == null)
			return;

		if (node.data == data) {
			path.add(node.data);
			return;
		} else if (node.data > data) {
			RTNP(node.left, data, path);
			if (path.size() != 0) {
				path.add(node.data);
			}
		} else {
			RTNP(node.right, data, path);
			if (path.size() != 0) {
				path.add(node.data);
			}
		}
	}

	public static int LCA(Node node, int a, int b) {

		if (node == null) {
			return -1;
		}

		if (node.data > a && node.data > b) {
			return LCA(node.left, a, b);
		} else if (node.data < a && node.data < b) {
			return LCA(node.right, a, b);
		} else {
			return (find(node, a) && find(node, b)) ? node.data : -1;
		}
	}

	public static int LCA_IT(Node node, int a, int b) {

		while (node != null) {
			if (node.data > a && node.data > b) {
				node = node.left;
			} else if (node.data < a && node.data < b) {
				node = node.right;
			} else {
				return (find(node, a) && find(node, b)) ? node.data : -1;
			}
		}
		return -1;
	}

	// In Order
	public static void rangeIn(Node node, int low, int high, ArrayList<Integer> res) {
		if (node == null)
			return;
		rangeIn(node.left, low, high, res);
		// Sorted Order
		if (node.data >= low && node.data <= high)
			res.add(node.data);
		rangeIn(node.right, low, high, res);
	}

	// Pre Order
	public static void rangeIn_02(Node node, int low, int high, ArrayList<Integer> res) {
		if (node == null)
			return;
		if (node.data >= low && node.data <= high)
			res.add(node.data);
		if (node.data > low && node.data > high)
			rangeIn_02(node.left, low, high, res);
		else if (node.data < low && node.data < high)
			rangeIn_02(node.right, low, high, res);
		else {
			rangeIn_02(node.left, low, high, res);
			rangeIn_02(node.right, low, high, res);
		}
	}

	public static class AllSolution {

		int size = 0;
		int height = 0;
		boolean find = false;

		Node pred = null;
		Node succ = null;
		Node prev = null;

	}

	public static void allSol(Node node, int data, int level, AllSolution pair) {

		if (node == null)
			return;

		pair.size++;
		pair.height = Math.max(pair.height, level);
		pair.find = pair.find || node.data == data;

		if (node.data == data && pair.pred == null)
			pair.pred = pair.prev;

		if (pair.prev != null && pair.prev.data == data && pair.succ == null)
			pair.succ = node;

		allSol(node.left, data, level + 1, pair);
		allSol(node.right, data, level + 1, pair);

	}

	// Logic InOrder
	public static void PredAndSucc(Node node, Node pred, Node succ, int data) {

		Node curr = node;
		while (curr != null) {
			if (curr.data == data) {
				if (curr.left == null) {
					System.out.println("Pred : " + ((pred != null) ? pred.data : "-1"));
				} else {
					pred = curr.left;
					while (pred.right != null) {
						pred = pred.right;
					}
					System.out.println("Pred : " + ((pred != null) ? pred.data : "-1"));
				}

				if (curr.right == null) {
					System.out.println("Succ : " + ((succ != null) ? succ.data : "-1"));
				} else {
					succ = curr.right;
					while (pred.left != null) {
						pred = pred.left;
					}
					System.out.println("Succ : " + ((succ != null) ? succ.data : "-1"));
				}

			} else if (data < curr.data) {
				succ = curr;
				curr = curr.left;
			} else {
				pred = curr;
				curr = curr.right;
			}
		}

	}

	public static Node ConstructPreorder(int[] preOrder, int idx, int lb, int ele, int ub) {

		if (ele < lb || ele > ub || idx == preOrder.length) {
			return null;
		}

		Node node = new Node(ele);
		idx++;
		node.left = ConstructPreorder(preOrder, idx, lb, preOrder[idx], ele);
		node.right = ConstructPreorder(preOrder, idx, ele, preOrder[idx], ub);
		return node;
	}

	public static Node ConstructPreorder_02(int[] preOrder, int idx, int lb, int ub) {

		if (idx == preOrder.length || preOrder[idx] < lb || preOrder[idx] > ub) {
			return null;
		}

		Node node = new Node(preOrder[idx++]);
		node.left = ConstructPreorder_02(preOrder, idx, lb, preOrder[idx]);
		node.right = ConstructPreorder_02(preOrder, idx, preOrder[idx], ub);
		return node;
	}

	public static int HeightPreorder(int[] preOrder, int idx, int lb, int ele, int ub) {

		if (ele < lb || ele > ub || idx == preOrder.length)
			return -1;

		idx++;
		int lh = HeightPreorder(preOrder, idx, lb, preOrder[idx], ele);
		int rh = HeightPreorder(preOrder, idx, ele, preOrder[idx], ub);
		return Math.max(lh, rh) + 1;
	}

	public static int HeightPreorder_02(int[] preOrder, int idx, int lb, int ub) {

		if (idx == preOrder.length || preOrder[idx] < lb || preOrder[idx] > ub)
			return -1;

		idx++;
		int lh = HeightPreorder_02(preOrder, idx, lb, preOrder[idx]);
		int rh = HeightPreorder_02(preOrder, idx, preOrder[idx], ub);
		return Math.max(lh, rh) + 1;
	}

	public static Node addData(Node node, int data) {
		if (node == null)
			return new Node(data);

		if (data < node.data)
			addData(node.left, data);
		else
			addData(node.right, data);

		return node;
	}

	public static Node addData_IT(Node node, int data) {
		if (node == null)
			return new Node(data);

		Node prev = node;

		while (node != null) {
			prev = node;
			if (data < node.data) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		if (data < prev.data)
			prev.left = new Node(data);
		else
			prev.right = new Node(data);
		return node;
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

}
