package Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

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

		System.out.println(LCA_02(root, 40, 90) + " , " + ((LCA_Node != null) ? LCA_Node.data : "null"));
		System.out.println(kdownToTarget(root, 20, 2));
		System.out.println(kdownToTarget_02(root, 20, 2));
		System.out.println(kdownToTarget_03(root, 20, 2));

		System.out.println("Diameter -> " + Diameter(root));
		System.out.println("Diameter -> " + Diameter_02(root).diameter + " , Height -> " + Diameter_02(root).height);
		Diameter_03(root);
		System.out.println("Diameter -> " + dia);

		System.out.print("LevelOrder -> ");
		levelOrder_01(root);
		System.out.println();
		System.out.println();
		levelOrder_02(root);
		System.out.println();
		System.out.println();
		levelOrder_03(root);
		System.out.println();
		levelOrder_04(root);

		System.out.println();
		System.out.println();
		System.out.print("LeftView -> ");
		left_view(root);

		System.out.println();
		System.out.print("RightView -> ");
		right_view(root);

		System.out.println();
		System.out.println("VerticalOrder -> ");
		verticalOrder(root);

		System.out.print("VerticalOrderSum -> ");
		verticalOrderSum(root);

		System.out.println();
		System.out.print("BottomView -> ");
		bottom_view(root);

		System.out.println();
		System.out.print("TopView -> ");
		top_view(root);

		System.out.println();
		System.out.println("DiagonalOrder -> ");
		diagonal_view(root);

		System.out.print("DiagonalOrderSum -> ");
		diagonal_view_sum(root);

		System.out.println();
		System.out.println("AntiDiagonalOrder -> ");
		anti_diagonal_view(root);

		System.out.print("AntiDiagonalOrderSum -> ");
		anti_diagonal_view_sum(root);

		System.out.println();
		System.out.print("Vertical Order (Weight) -> ");
		VerticalOrder(root);

		System.out.println();
		System.out.print("DLL -> ");
		DLL_Display(root);
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

	public static Node LCA_Node = null;

	// Lowest Common Ancestor without extra space
	public static boolean LCA_02(Node node, int p, int q) {

		if (node == null)
			return false;

		boolean selfDone = false;
		if (node.data == p || node.data == q) {
			selfDone = true;
		}

		boolean leftDone = LCA_02(node.left, p, q);
		if (LCA_Node != null)
			return true;
		boolean rightDone = LCA_02(node.right, p, q);
		if (LCA_Node != null)
			return true;

		if ((leftDone && selfDone) || (rightDone && selfDone) || (leftDone && rightDone)) {
			LCA_Node = node;
		}

		return selfDone || leftDone || rightDone;

	}

	public static void kDown(Node node, int k, Node blockNode, ArrayList<Node> ans) {

		if (node == null || node == blockNode)
			return;

		if (k == 0) {
			ans.add(node);
			return;
		}

		kDown(node.left, k - 1, blockNode, ans);
		kDown(node.right, k - 1, blockNode, ans);

	}

	public static void kDown_02(Node node, int k, Node blockNode) {

		if (node == null || node == blockNode)
			return;

		if (k == 0) {
			System.out.print(node.data + " ");
			return;
		}

		kDown_02(node.left, k - 1, blockNode);
		kDown_02(node.right, k - 1, blockNode);

	}

	public static void kDown_03(Node node, int k) {

		if (node == null)
			return;

		if (k == 0) {
			System.out.print(node.data + " ");
			return;
		}

		kDown_03(node.left, k - 1);
		kDown_03(node.right, k - 1);

	}

	// LeetCode 863
	public static ArrayList<Integer> kdownToTarget(Node node, int target, int k) {

		ArrayList<Node> path = new ArrayList<Lecture_01.Node>();
		RTNP(node, target, path);

		ArrayList<Node> ans = new ArrayList<Lecture_01.Node>();
		Node blockNode = null;
		for (int i = 0; i < path.size(); i++) {
			if (k < i)
				break;
			kDown(path.get(i), k - i, blockNode, ans);
			blockNode = path.get(i);
		}
		ArrayList<Integer> res = new ArrayList<>();
		for (Node n : ans) {
			res.add(n.data);
		}
		return res;
	}

	public static int kdownToTarget_02(Node node, int target, int k) {

		if (node == null)
			return -1;

		if (node.data == target) {
			kDown_02(node, k, null);
			return 1;
		}

		int leftDistance = kdownToTarget_02(node.left, target, k);
		if (leftDistance != -1) {
			if (k >= leftDistance)
				kDown_02(node, k - leftDistance, node.left);
			return leftDistance + 1;
		}

		int rightDistance = kdownToTarget_02(node.right, target, k);
		if (rightDistance != -1) {
			if (k >= rightDistance)
				kDown_02(node, k - rightDistance, node.right);
			return rightDistance + 1;
		}

		return -1;
	}

	public static int kdownToTarget_03(Node node, int target, int k) {

		if (node == null)
			return -1;

		if (node.data == target) {
			kDown_03(node, k);
			return 1;
		}

		int leftDistance = kdownToTarget_03(node.left, target, k);
		if (leftDistance != -1) {
			if (k == leftDistance) {
				System.out.println(node.data + " ");
			} else {
				kDown_03(node.right, k - leftDistance - 1);
			}
			return leftDistance + 1;
		}

		int rightDistance = kdownToTarget_03(node.right, target, k);
		if (rightDistance != -1) {
			if (k == rightDistance) {
				System.out.println(node.data + " ");
			} else {
				kDown_03(node.left, k - rightDistance - 1);
			}
			return rightDistance + 1;
		}

		return -1;
	}

	public static int Diameter(Node node) {

		if (node == null)
			return 0;

		int dial = Diameter(node.left);
		int diar = Diameter(node.right);

		int lh = height(node.left);
		int rh = height(node.right);

		int dia = lh + rh + 2;

		return Math.max(dia, Math.max(dial, diar));

	}

	public static class diaPair {
		int diameter;
		int height;

		public diaPair(int diameter, int height) {
			this.diameter = diameter;
			this.height = height;
		}

	}

	public static diaPair Diameter_02(Node node) {

		if (node == null)
			return new diaPair(0, -1);

		diaPair dial = Diameter_02(node.left);
		diaPair diar = Diameter_02(node.right);

		diaPair res = new diaPair(0, -1);
		res.diameter = Math.max((dial.height + diar.height + 2), Math.max(dial.diameter, diar.diameter));
		res.height = Math.max(dial.height, diar.height) + 1;

		return res;

	}

	public static int dia;

	public static int Diameter_03(Node node) {
		if (node == null)
			return -1;

		int dial = Diameter_03(node.left);
		int diar = Diameter_03(node.right);

		dia = Math.max(dia, dial + diar + 2);
		return Math.max(dial, diar) + 1;
	}

	public static void levelOrder_01(Node root) {

		LinkedList<Node> queue = new LinkedList<Lecture_01.Node>();
		queue.addLast(root);

		while (!queue.isEmpty()) {
			Node rn = queue.removeFirst();
			System.out.print(rn.data + " ");
			if (rn.left != null)
				queue.addLast(rn.left);
			if (rn.right != null)
				queue.addLast(rn.right);
		}

	}

	public static void levelOrder_02(Node root) {

		LinkedList<Node> queue = new LinkedList<Lecture_01.Node>();
		LinkedList<Node> child = new LinkedList<Lecture_01.Node>();
		queue.addLast(root);

		int count = 0;
		System.out.print("Level " + count + " -> ");
		while (!queue.isEmpty()) {
			Node rn = queue.removeFirst();
			System.out.print(rn.data + " ");
			if (rn.left != null)
				child.addLast(rn.left);
			if (rn.right != null)
				child.addLast(rn.right);
			if (queue.isEmpty()) {
				LinkedList<Node> temp = queue;
				queue = child;
				child = temp;
				count++;
				System.out.println();
				System.out.print("Level " + count + " -> ");
			}
		}

	}

	// Deleminator Method
	public static void levelOrder_03(Node root) {

		LinkedList<Node> queue = new LinkedList<Lecture_01.Node>();
		queue.addLast(root);
		queue.addLast(null);

		int count = 0;
		System.out.print("Level " + count + " -> ");
		while (!queue.isEmpty()) {

			if (queue.getFirst() == null && queue.size() == 1) {
				queue.removeFirst();
				continue;
			}

			Node rn = queue.removeFirst();
			System.out.print(rn.data + " ");
			if (rn.left != null)
				queue.addLast(rn.left);
			if (rn.right != null)
				queue.addLast(rn.right);
			if (queue.getFirst() == null) {
				queue.removeFirst();
				queue.addLast(null);
				count++;
				System.out.println();
				System.out.print("Level " + count + " -> ");
			}
		}

	}

	public static void levelOrder_04(Node root) {
		LinkedList<Node> queue = new LinkedList<Lecture_01.Node>();
		queue.addLast(root);
		int count = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			System.out.println();
			System.out.print("Level " + count + " -> ");
			count++;
			while (size-- > 0) {
				Node rn = queue.removeFirst();
				System.out.print(rn.data + " ");
				if (rn.left != null)
					queue.addLast(rn.left);
				if (rn.right != null)
					queue.addLast(rn.right);
			}
		}
	}

	public static void left_view(Node root) {
		LinkedList<Node> queue = new LinkedList<Lecture_01.Node>();
		queue.addLast(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			System.out.print(queue.getFirst().data + " ");
			while (size-- > 0) {
				Node rn = queue.removeFirst();
				if (rn.left != null)
					queue.addLast(rn.left);
				if (rn.right != null)
					queue.addLast(rn.right);
			}
		}
	}

	public static void right_view(Node root) {
		LinkedList<Node> queue = new LinkedList<Lecture_01.Node>();
		queue.addLast(root);
		Node prev = null;

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Node rn = queue.removeFirst();
				prev = rn;
				if (rn.left != null)
					queue.addLast(rn.left);
				if (rn.right != null)
					queue.addLast(rn.right);
			}
			System.out.print(prev.data + " ");
		}
	}

	public static int leftMost = Integer.MAX_VALUE;
	public static int rightMost = Integer.MIN_VALUE;

	public static void width(Node root, int level) {

		if (root == null)
			return;

		leftMost = Math.min(leftMost, level);
		rightMost = Math.max(rightMost, level);

		width(root.left, level - 1);
		width(root.right, level + 1);
	}

	public static class levelPair {
		Node node;
		int level;

		public levelPair(Node node, int level) {
			this.node = node;
			this.level = level;
		}
	}

	public static void verticalOrder(Node node) {

		width(node, 0);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		int w = rightMost - leftMost + 1;
		for (int i = 0; i < w; i++) {
			res.add(new ArrayList<Integer>());
		}

		LinkedList<levelPair> queue = new LinkedList<>();
		queue.addLast(new levelPair(node, -leftMost));

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				levelPair rn = queue.removeFirst();
				res.get(rn.level).add(rn.node.data);
				if (rn.node.left != null)
					queue.addLast(new levelPair(rn.node.left, rn.level - 1));
				if (rn.node.right != null)
					queue.addLast(new levelPair(rn.node.right, rn.level + 1));
			}
		}
		int count = 0;
		for (ArrayList<Integer> list : res) {
			System.out.print("Level " + count++ + " -> ");
			System.out.println(list);
		}
	}

	public static void verticalOrderSum(Node node) {

		width(node, 0);
		ArrayList<Integer> res = new ArrayList<>();
		int w = rightMost - leftMost + 1;
		for (int i = 0; i < w; i++) {
			res.add(0);
		}

		LinkedList<levelPair> queue = new LinkedList<>();
		queue.addLast(new levelPair(node, -leftMost));

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				levelPair rn = queue.removeFirst();
				res.set(rn.level, res.get(rn.level) + rn.node.data);
				if (rn.node.left != null)
					queue.addLast(new levelPair(rn.node.left, rn.level - 1));
				if (rn.node.right != null)
					queue.addLast(new levelPair(rn.node.right, rn.level + 1));
			}
		}
		System.out.println(res);
	}

	public static void bottom_view(Node node) {

		width(node, 0);
		ArrayList<Integer> res = new ArrayList<>();
		int w = rightMost - leftMost + 1;
		for (int i = 0; i < w; i++) {
			res.add(0);
		}

		LinkedList<levelPair> queue = new LinkedList<>();
		queue.addLast(new levelPair(node, -leftMost));

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				levelPair rn = queue.removeFirst();
				res.set(rn.level, rn.node.data);
				if (rn.node.left != null)
					queue.addLast(new levelPair(rn.node.left, rn.level - 1));
				if (rn.node.right != null)
					queue.addLast(new levelPair(rn.node.right, rn.level + 1));
			}
		}
		System.out.println(res);
	}

	public static void top_view(Node node) {

		width(node, 0);
		ArrayList<Integer> res = new ArrayList<>();
		int w = rightMost - leftMost + 1;
		for (int i = 0; i < w; i++) {
			res.add(-1);
		}

		LinkedList<levelPair> queue = new LinkedList<>();
		queue.addLast(new levelPair(node, -leftMost));

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				levelPair rn = queue.removeFirst();
				if (res.get(rn.level) == -1)
					res.set(rn.level, rn.node.data);
				if (rn.node.left != null)
					queue.addLast(new levelPair(rn.node.left, rn.level - 1));
				if (rn.node.right != null)
					queue.addLast(new levelPair(rn.node.right, rn.level + 1));
			}
		}
		System.out.println(res);
	}

	public static void diagonal_view(Node node) {

		width(node, 0);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		int w = 1 - leftMost;
		for (int i = 0; i < w; i++) {
			res.add(new ArrayList<Integer>());
		}

		LinkedList<levelPair> queue = new LinkedList<>();
		queue.addLast(new levelPair(node, -leftMost));

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				levelPair rn = queue.removeFirst();
				res.get(rn.level).add(rn.node.data);
				if (rn.node.left != null)
					queue.addLast(new levelPair(rn.node.left, rn.level - 1));
				if (rn.node.right != null)
					queue.addLast(new levelPair(rn.node.right, rn.level));
			}
		}
		int count = 0;
		for (ArrayList<Integer> list : res) {
			System.out.print("Level " + count++ + " -> ");
			System.out.println(list);
		}

	}

	public static void diagonal_view_sum(Node node) {

		width(node, 0);
		ArrayList<Integer> res = new ArrayList<>();
		int w = 1 - leftMost;
		for (int i = 0; i < w; i++) {
			res.add(0);
		}

		LinkedList<levelPair> queue = new LinkedList<>();
		queue.addLast(new levelPair(node, -leftMost));

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				levelPair rn = queue.removeFirst();
				res.set(rn.level, res.get(rn.level) + rn.node.data);
				if (rn.node.left != null)
					queue.addLast(new levelPair(rn.node.left, rn.level - 1));
				if (rn.node.right != null)
					queue.addLast(new levelPair(rn.node.right, rn.level));
			}
		}
		System.out.println(res);

	}

	public static void anti_diagonal_view(Node node) {

		width(node, 0);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		int w = 1 + rightMost;
		for (int i = 0; i < w; i++) {
			res.add(new ArrayList<Integer>());
		}

		LinkedList<levelPair> queue = new LinkedList<>();
		queue.addLast(new levelPair(node, 0));

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				levelPair rn = queue.removeFirst();
				res.get(rn.level).add(rn.node.data);
				if (rn.node.left != null)
					queue.addLast(new levelPair(rn.node.left, rn.level));
				if (rn.node.right != null)
					queue.addLast(new levelPair(rn.node.right, rn.level + 1));
			}
		}
		int count = 0;
		for (ArrayList<Integer> list : res) {
			System.out.print("Level " + count++ + " -> ");
			System.out.println(list);
		}

	}

	public static void anti_diagonal_view_sum(Node node) {

		width(node, 0);
		ArrayList<Integer> res = new ArrayList<>();
		int w = 1 + rightMost;
		for (int i = 0; i < w; i++) {
			res.add(0);
		}

		LinkedList<levelPair> queue = new LinkedList<>();
		queue.addLast(new levelPair(node, 0));

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				levelPair rn = queue.removeFirst();
				res.set(rn.level, res.get(rn.level) + rn.node.data);
				if (rn.node.left != null)
					queue.addLast(new levelPair(rn.node.left, rn.level));
				if (rn.node.right != null)
					queue.addLast(new levelPair(rn.node.right, rn.level + 1));
			}
		}
		System.out.println(res);

	}

	// LeetCode
	public static class levelPair_02 implements Comparable<levelPair_02> {
		Node node;
		int level;

		public levelPair_02(Node node, int level) {
			this.node = node;
			this.level = level;
		}

		@Override
		public int compareTo(levelPair_02 op) {
			if (this.level == op.level)
				return this.node.data - op.node.data;
			return this.level - op.level;
		}

	}

	public static void VerticalOrder(Node node) {

		width(node, 0);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		int w = rightMost - leftMost + 1;
		for (int i = 0; i < w; i++) {
			res.add(new ArrayList<Integer>());
		}

		PriorityQueue<levelPair_02> queue = new PriorityQueue<levelPair_02>();
		PriorityQueue<levelPair_02> child = new PriorityQueue<levelPair_02>();
		queue.add(new levelPair_02(node, -leftMost));

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				levelPair_02 rn = queue.poll();
				res.get(rn.level).add(rn.node.data);
				if (rn.node.left != null)
					child.add(new levelPair_02(rn.node.left, rn.level - 1));
				if (rn.node.right != null)
					child.add(new levelPair_02(rn.node.right, rn.level + 1));
			}
			PriorityQueue<levelPair_02> temp = queue;
			queue = child;
			child = temp;
		}
		int count = 0;
		System.out.println();
		for (ArrayList<Integer> list : res) {
			System.out.print("Level " + count++ + " -> ");
			System.out.println(list);
		}
	}

	public static Node DLLhead = null;
	public static Node DLLprev = null;

	// Doubly Linked List
	public static void DLL(Node node) {

		if (node == null)
			return;

		DLL(node.left);

		if (DLLhead == null) {
			DLLhead = node;
		} else {
			DLLprev.right = node;
			node.left = DLLprev;
		}

		DLLprev = node;

		DLL(node.right);

	}

	public static void DLL_Display(Node node) {
		DLL(node);
		while (DLLhead != null) {
			System.out.print(DLLhead.data + " ");
			DLLhead = DLLhead.right;
		}
	}

}
