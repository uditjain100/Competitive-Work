package Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Lecture_02 {

	public static void main(String[] args) {

//		By Priority Queue is min heap in java

		int[] arr = { 1, 8, 4, 6, 45, 100, -2, -50, 14, 56 };
//		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
//		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		boolean isMax = true;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> {
			if (isMax)
				return b - a;
			else
				return a - b;
		});
		for (int ele : arr)
			pq.add(ele);
		while (!pq.isEmpty())
			System.out.print(pq.remove() + ", ");
		System.out.println();
//		PriorityObject();

		System.out.println(Kthlargest(arr, 7));
		System.out.println(KthSmallest(arr, 2));
		Occurece("usqwfhsscqucuwssfSUC");
	}

	public static void PriorityObject() {

		int[][] arr = { { 1, -95, 4 }, { 4, 36, 40 }, { 5, 28, -48 }, { 9, 52, 95 }, { 12, 22, -27 }, { 100, -2, 0 } };
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> {
			return a[2] - b[2];
		});
		for (int[] ele : arr)
			pq.add(ele);
		while (!pq.isEmpty()) {
			int[] a = pq.remove();
			System.out.println(a[0] + ", " + a[1] + ", " + a[2]);
		}

	}

	public static int Kthlargest(int[] arr, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int ele : arr) {
			pq.add(ele);
			if (pq.size() > k) {
				pq.remove();
			}
		}
		return pq.peek();
	}

	public static int KthSmallest(int[] arr, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> {
			return b - a;
		});
		for (int ele : arr) {
			pq.add(ele);
			if (pq.size() > k) {
				pq.remove();
			}
		}
		return pq.peek();
	}

	public static void Occurece(String str) {
		HashMap<Character, ArrayList<Integer>> map = new HashMap<Character, ArrayList<Integer>>();
		for (int i = 0; i < str.length(); i++) {
			char cc = str.charAt(i);
			if (map.containsKey(cc)) {
				ArrayList<Integer> list = map.get(cc);
				list.add(i);
				map.put(cc, list);
			} else {
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(i);
				map.put(cc, list);
			}
		}
		System.out.println(map);
	}

}
