package Heap;

import java.util.ArrayList;

public class Lecture_01 {

	public static class Heap {

		ArrayList<Integer> list;
		boolean isMaxHeap = false;

		public Heap(int[] arr, boolean isMaxHeap) {
			this.list = new ArrayList<Integer>();
			this.isMaxHeap = isMaxHeap;
			for (int ele : arr)
				list.add(ele);
			for (int i = list.size() - 1; i >= 0; i--)
				downHeapify(i);
		}

		public Heap(int[] arr) {
			this.list = new ArrayList<Integer>();
			for (int ele : arr)
				list.add(ele);
			for (int i = list.size() - 1; i >= 0; i--)
				downHeapify(i);
		}

		public Heap() {
			list.clear();
		}

		public int compareTo(int x, int y) {
			if (isMaxHeap)
				return this.list.get(x) - this.list.get(y);
			else
				return this.list.get(y) - this.list.get(x);
		}

		public void downHeapify(int pi) {
			int lci = 2 * pi + 1;
			int rci = 2 * pi + 2;
			int finalpi = pi;

			if (lci < list.size() && compareTo(lci, finalpi) > 0)
				finalpi = lci;
			if (rci < list.size() && compareTo(rci, finalpi) > 0)
				finalpi = rci;
			if (finalpi != pi) {
				swap(finalpi, pi);
				downHeapify(finalpi);
			}
		}

		public void upHeapify(int ci) {
			int pi = (ci - 1) / 2;
			int initialpi = ci;
			if (pi >= 0 && compareTo(initialpi, pi) > 0)
				initialpi = pi;

			if (initialpi != pi) {
				swap(ci, initialpi);
				upHeapify(initialpi);
			}
		}

		public void swap(int i, int j) {
			int data1 = list.get(i);
			int data2 = list.get(j);

			list.set(i, data2);
			list.set(j, data1);
		}

		public boolean isEmpty() {
			return list.size() == 0;
		}

		public int size() {
			return this.size();
		}

		public int top() {
			if (!this.isEmpty())
				return list.get(0);
			return -1;
		}

		public void add(int data) {
			list.add(data);
			upHeapify(list.size() - 1);
		}

		public int remove() {
			if (this.isEmpty())
				return -1;

			swap(0, list.size() - 1);
			int rd = list.remove(list.size() - 1);
			downHeapify(0);
			return rd;
		}

		public int height(int idx) {
			if (idx >= list.size())
				return -1;

			int rh = height(2 * idx + 1);
			int lh = height(2 * idx + 2);
			return Math.max(rh, lh) + 1;
		}

		public int Treesize(int idx) {
			if (idx >= list.size())
				return 0;

			int rh = Treesize(2 * idx + 1);
			int lh = Treesize(2 * idx + 2);
			return rh + lh + 1;
		}

	}

	public static void main(String[] args) {

		int[] arr = { 9, 2, 5, 4, 7, 6, 8, 3 };
		HeapSort(arr);
		for (int c : arr)
			System.out.print(c + ", ");
		System.out.println();

	}

	public static void HeapSort(int[] arr) {
		Heap heap = new Heap(arr, false);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = heap.remove();
		}
	}

}
