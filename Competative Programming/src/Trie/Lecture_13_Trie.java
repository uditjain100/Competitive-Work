package Trie;

import java.util.ArrayList;

public class Lecture_13_Trie {

	public static void main(String[] args) {

		char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' },
				{ 'i', 'f', 'l', 'v' } };
		ArrayList<String> words = new ArrayList<String>();
		words.add("oath");
		words.add("pea");
		words.add("eat");
		words.add("rain");
		System.out.println(findWords(board, words));
	}

	public static class Node {
		Node[] childs;
		boolean wordEnd;
		String word;

		public Node() {
			this.childs = new Node[26];
			this.wordEnd = false;
			this.word = "";
		}
	}

	private static Node root = new Node();

	public static void insert(String word) {
		Node curr = root;
		for (int i = 0; i < word.length(); i++) {
			if (curr.childs[word.charAt(i) - 'a'] == null) {
				curr.childs[word.charAt(i) - 'a'] = new Node();
			}
			curr = curr.childs[word.charAt(i) - 'a'];
		}
		curr.wordEnd = true;
		curr.word = word;
	}

	public static boolean search(String word) {
		Node curr = root;
		for (int i = 0; i < word.length(); i++) {
			if (curr.childs[word.charAt(i) - 'a'] == null) {
				return false;
			}
			curr = curr.childs[word.charAt(i) - 'a'];
		}
		return curr.wordEnd;
	}

	public static boolean startsWith(String word) {
		Node curr = root;
		for (int i = 0; i < word.length(); i++) {
			if (curr.childs[word.charAt(i) - 'a'] == null) {
				return false;
			}
			curr = curr.childs[word.charAt(i) - 'a'];
		}
		return true;
	}

	public static boolean search_like_google(String word, int idx, Node curr) {
		if (idx == word.length()) {
			return curr.wordEnd;
		}
		boolean res = false;
		if (word.charAt(idx) == '.') {
			for (int i = 0; i < 26; i++) {
				if (curr.childs[i] != null) {
					res = res || search_like_google(word, idx + 1, curr.childs[i]);
				}
			}
		} else {
			if (curr.childs[word.charAt(idx) - 'a'] != null) {
				res = res || search_like_google(word, idx + 1, curr.childs[word.charAt(idx) - 'a']);
			}
		}
		return res;
	}

	public static ArrayList<String> res = new ArrayList<String>();

	public static ArrayList<String> findWords(char[][] arr, ArrayList<String> words) {

		if (arr.length == 0 || arr[0].length == 0 || words.size() == 0) {
			return res;
		}

		for (int i = 0; i < words.size(); i++) {
			insert(words.get(i));
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (root.childs[arr[i][j] - 'a'] != null) {
					dfs(arr, i, j, root.childs[arr[i][j] - 'a']);
				}
			}
		}
		return res;

	}

	public static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void dfs(char[][] arr, int row, int col, Node curr) {

		if (curr.wordEnd) {
			curr.wordEnd = false;
			res.add(curr.word);
		}

		char cc = arr[row][col];
		arr[row][col] = '$';
		for (int d = 0; d < dir.length; d++) {
			int x = row + dir[d][0];
			int y = col + dir[d][1];
			if (x >= 0 && x < arr.length && y >= 0 && y < arr[col].length && arr[x][y] != '$'
					&& curr.childs[arr[x][y] - 'a'] != null) {
				dfs(arr, x, y, curr.childs[arr[x][y] - 'a']);
			}
		}
		arr[row][col] = cc;

	}

}
