package Stack;

import java.util.Arrays;

public class Lecture_01 {

	public static class Stack {

		private int[] arr;
		private int tos;
		private int size;

		public Stack() {
			this.arr = new int[1000000];
			this.tos = -1;
			this.size = 0;
		}

		public Stack(int N) {
			this.arr = new int[N];
			this.tos = -1;
			this.size = 0;
		}

		public int size() {
			return this.size;
		}

		public boolean isEmpty() {
			return this.size == 0;
		}

		public boolean isFull() {
			return this.size == this.arr.length;
		}

		public void push(int data) throws Exception {
			if (this.isFull()) {
				throw new Exception("Stack is Full");
			}
			this.arr[++tos] = data;
			this.size++;
		}

		public int pop() throws Exception {
			if (this.isEmpty()) {
				throw new Exception("Stack is Empty");
			}

			int rd = this.arr[tos];
			this.arr[tos--] = 0;
			this.size--;
			return rd;
		}

		public int top() throws Exception {
			if (this.isEmpty()) {
				throw new Exception("Stack is Empty");
			}
			return this.arr[tos];
		}

		@Override
		public String toString() {
			String str = "";
			for (int i = 0; i < this.size; i++)
				str += this.arr[i] + ", ";
			return str;
		}
	}

	public static void main(String[] args) throws Exception {

		int[] arr = { 2, 1, 8, 6, 9, 4, 3, 5 };

		int[] ans1 = nextGreaterLTR(arr);
		for (int i = 0; i < ans1.length; i++)
			System.out.print(ans1[i] + ", ");
		System.out.println();

		int[] ans2 = nextGreaterRTL(arr);
		for (int i = 0; i < ans2.length; i++)
			System.out.print(ans2[i] + ", ");
		System.out.println();

		int[] ans3 = nextSmallerLTR(arr);
		for (int i = 0; i < ans3.length; i++)
			System.out.print(ans3[i] + ", ");
		System.out.println();

		int[] ans4 = nextSmallerRTL(arr);
		for (int i = 0; i < ans4.length; i++)
			System.out.print(ans4[i] + ", ");
		System.out.println();

	}

	public static int[] nextGreaterLTR(int[] arr) throws Exception {

		Stack stack1 = new Stack(arr.length);
		Stack stack2 = new Stack(arr.length);

		int[] ans = new int[arr.length];
		Arrays.fill(ans, -2);

		for (int i = 0; i < arr.length; i++) {

			if (i == 0) {
				stack1.push(arr[i]);
				stack2.push(i);
				continue;
			}

			if (stack1.top() < arr[i]) {
				while (!stack1.isEmpty() && stack1.top() < arr[i]) {
					stack1.pop();
					int idx = stack2.pop();
					ans[idx] = i;
				}
				stack1.push(arr[i]);
				stack2.push(i);
			} else {
				stack1.push(arr[i]);
				stack2.push(i);
			}

		}

		for (int i = 0; i < ans.length; i++)
			if (ans[i] == -2)
				ans[i] = ans.length;

		return ans;

	}

	public static int[] nextGreaterRTL(int[] arr) throws Exception {

		Stack stack1 = new Stack(arr.length);
		Stack stack2 = new Stack(arr.length);

		int[] ans = new int[arr.length];
		Arrays.fill(ans, -2);

		for (int i = arr.length - 1; i >= 0; i--) {

			if (i == arr.length - 1) {
				stack1.push(arr[i]);
				stack2.push(i);
				continue;
			}

			if (stack1.top() < arr[i]) {
				while (!stack1.isEmpty() && stack1.top() < arr[i]) {
					stack1.pop();
					int idx = stack2.pop();
					ans[idx] = i;
				}
				stack1.push(arr[i]);
				stack2.push(i);
			} else {
				stack1.push(arr[i]);
				stack2.push(i);
			}

		}

		for (int i = 0; i < ans.length; i++)
			if (ans[i] == -2)
				ans[i] = -1;

		return ans;

	}

	public static int[] nextSmallerLTR(int[] arr) throws Exception {

		Stack stack1 = new Stack(arr.length);
		Stack stack2 = new Stack(arr.length);

		int[] ans = new int[arr.length];
		Arrays.fill(ans, -2);

		for (int i = 0; i < arr.length; i++) {

			if (i == 0) {
				stack1.push(arr[i]);
				stack2.push(i);
				continue;
			}

			if (stack1.top() > arr[i]) {
				while (!stack1.isEmpty() && stack1.top() > arr[i]) {
					stack1.pop();
					int idx = stack2.pop();
					ans[idx] = i;
				}
				stack1.push(arr[i]);
				stack2.push(i);
			} else {
				stack1.push(arr[i]);
				stack2.push(i);
			}

		}

		for (int i = 0; i < ans.length; i++)
			if (ans[i] == -2)
				ans[i] = ans.length;

		return ans;

	}

	public static int[] nextSmallerRTL(int[] arr) throws Exception {

		Stack stack1 = new Stack(arr.length);
		Stack stack2 = new Stack(arr.length);

		int[] ans = new int[arr.length];
		Arrays.fill(ans, -2);

		for (int i = arr.length - 1; i >= 0; i--) {

			if (i == arr.length - 1) {
				stack1.push(arr[i]);
				stack2.push(i);
				continue;
			}

			if (stack1.top() > arr[i]) {
				while (!stack1.isEmpty() && stack1.top() > arr[i]) {
					stack1.pop();
					int idx = stack2.pop();
					ans[idx] = i;
				}
				stack1.push(arr[i]);
				stack2.push(i);
			} else {
				stack1.push(arr[i]);
				stack2.push(i);
			}

		}

		for (int i = 0; i < ans.length; i++)
			if (ans[i] == -2)
				ans[i] = -1;

		return ans;

	}

	public static int rainWaterTrapping(int[] arr) {
		int height = arr.length;
		int li = 0;
		int ri = height - 1;
		int lMaxH = 0;
		int rMaxH = 0;
		int water = 0;

		while (li <= ri) {
			lMaxH = Math.max(lMaxH, arr[li]);
			rMaxH = Math.max(rMaxH, arr[ri]);
			if (lMaxH <= rMaxH) {
				water += lMaxH - arr[li++];
			} else {
				water += rMaxH - arr[ri--];
			}
		}
		return water;
	}

}
