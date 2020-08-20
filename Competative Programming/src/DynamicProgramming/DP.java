package DynamicProgramming;

import java.util.ArrayList;

public class DP {

	public static void main(String[] args) {

		System.out.println(fibonacciDP(5));
		System.out.println(fibonacci(5));
		System.out.println(fibonacciTable(5));

		System.out.println(tribonacciDP(5));
		System.out.println(tribonacci(5));
		System.out.println(tribonacciTable(5));

		System.out.println(MazePathTable(2, 2));
		System.out.println(MazePathDP(0, 0, 2, 2));
		System.out.println(MazePath(0, 0, 2, 2));

		System.out.println(MazePathMultiMoveTable(8, 8));
		System.out.println(MazePathMultiMoveDP(0, 0, 8, 8));
		System.out.println(MazePathMultiMove(0, 0, 8, 8));

		System.out.println(BoardPathDP(0, 10));
		System.out.println(BoardPath(0, 10));
		System.out.println(BoardPathTable(10));

		System.out.println(BoardPathBiased(new int[] { 1, 2, 3, 4, 5, 6 }, 0, 10));
		System.out.println(BoardPathBiasedDP(new int[] { 1, 2, 3, 4, 5, 6 }, 0, 10));
		System.out.println(BoardPathBiasedTable(new int[] { 1, 2, 3, 4, 5, 6 }, 10));

	}
	
	public static long fibonacci(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;

		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public static long[] fibList = new long[100000];

	public static long fibonacciDP(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;

		if (fibList[n] != 0)
			return fibList[n];

		long res = fibonacciDP(n - 1) + fibonacciDP(n - 2);
		fibList[n] = res;

		return res;
	}

	public static long fibonacciTable(int n) {
		long[] fib = new long[n + 1];
		for (int i = 0; i < fib.length; i++) {
			if (i == 0 || i == 1) {
				fib[i] = i;
				continue;
			}
			fib[i] = fib[i - 1] + fib[i - 2];
		}
		return fib[fib.length - 1];
	}

	public static long fibonacciOptimised(int n) {
		if (n == 0 || n == 1)
			return n;

		int a = 0;
		int b = 1;
		int res = 0;
		for (int i = 0; i < n; i++) {
			res = a + b;
			a = b;
			b = res;
		}
		return res;
	}

	public static long tribonacci(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		if (n == 2)
			return 1;

		return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
	}

	public static long[] tribList = new long[100000];

	public static long tribonacciDP(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		if (n == 2)
			return 1;

		if (tribList[n] != 0)
			return tribList[n];

		long res = tribonacciDP(n - 1) + tribonacciDP(n - 2) + tribonacciDP(n - 3);
		tribList[n] = res;

		return res;
	}

	public static long tribonacciTable(int n) {
		long[] trib = new long[n + 1];
		for (int i = 0; i < trib.length; i++) {
			if (i == 0 || i == 1) {
				trib[i] = i;
				continue;
			}
			if (i == 2) {
				trib[i] = 1;
				continue;
			}
			trib[i] = trib[i - 1] + trib[i - 2] + trib[i - 3];
		}
		return trib[trib.length - 1];
	}

	public static int MazePath(int sr, int sc, int er, int ec) {

		if (sr == er && sc == ec) {
			return 1;
		}
		if (sr > er || sc > ec)
			return 0;

		int count = 0;
		count += MazePath(sr + 1, sc, er, ec);
		count += MazePath(sr, sc + 1, er, ec);
		count += MazePath(sr + 1, sc + 1, er, ec);

		return count;
	}

	public static int[][] mp = new int[100][100];

	public static int MazePathDP(int sr, int sc, int er, int ec) {

		if (sr == er && sc == ec) {
			return 1;
		}
		if (sr > er || sc > ec)
			return 0;

		if (mp[sr][sc] != 0)
			return mp[sr][sc];

		int count = 0;
		count += MazePathDP(sr + 1, sc, er, ec);
		count += MazePathDP(sr, sc + 1, er, ec);
		count += MazePathDP(sr + 1, sc + 1, er, ec);
		mp[sr][sc] = count;

		return count;
	}

	public static int MazePathTable(int er, int ec) {

		int[][] mp = new int[er + 1][ec + 1];
		for (int sr = er; sr >= 0; sr--) {
			for (int sc = ec; sc >= 0; sc--) {
				if (sr == er && sc == ec) {
					mp[sr][sc] = 1;
					continue;
				}
				int count = 0;
				if (sr + 1 <= er)
					count += mp[sr + 1][sc];
				if (sc + 1 <= ec)
					count += mp[sr][sc + 1];
				if (sr + 1 <= er && sc + 1 <= ec)
					count += mp[sr + 1][sc + 1];

				mp[sr][sc] = count;

			}
		}
		return mp[0][0];
	}

	public static int MazePathMultiMove(int sr, int sc, int er, int ec) {

		if (sr == er && sc == ec) {
			return 1;
		}
		if (sr > er || sc > ec)
			return 0;

		int count = 0;
		for (int i = 1, j = 1; i <= er && j <= ec; i++, j++) {
			count += MazePathMultiMove(sr + i, sc, er, ec);
			count += MazePathMultiMove(sr, sc + j, er, ec);
			count += MazePathMultiMove(sr + i, sc + j, er, ec);
		}

		return count;
	}

	public static int[][] mpmm = new int[100][100];

	public static int MazePathMultiMoveDP(int sr, int sc, int er, int ec) {

		if (sr == er && sc == ec) {
			return 1;
		}
		if (sr > er || sc > ec)
			return 0;

		if (mpmm[sr][sc] != 0)
			return mpmm[sr][sc];

		int count = 0;
		for (int i = 1, j = 1; i <= er && j <= ec; i++, j++) {
			count += MazePathMultiMoveDP(sr + i, sc, er, ec);
			count += MazePathMultiMoveDP(sr, sc + j, er, ec);
			count += MazePathMultiMoveDP(sr + i, sc + j, er, ec);
		}
		mpmm[sr][sc] = count;
		return count;
	}

	public static int MazePathMultiMoveTable(int er, int ec) {

		int[][] mpmm = new int[er + 1][ec + 1];
		for (int sr = er; sr >= 0; sr--) {
			for (int sc = ec; sc >= 0; sc--) {
				if (sr == er && sc == ec) {
					mp[sr][sc] = 1;
					continue;
				}

				int count = 0;
				for (int i = 1, j = 1; i <= er && j <= ec; i++, j++) {
					if (sr + 1 < er)
						count += MazePathMultiMoveDP(sr + i, sc, er, ec);
					if (sc + 1 < ec)
						count += MazePathMultiMoveDP(sr, sc + j, er, ec);
					if (sr + 1 < er && sc + 1 < ec)
						count += MazePathMultiMoveDP(sr + i, sc + j, er, ec);
				}
				mpmm[sr][sc] = count;
			}
		}

		return mpmm[0][0];
	}

	public static int BoardPath(int si, int ei) {

		if (si == ei)
			return 1;

		if (si > ei)
			return 0;

		int count = 0;
		for (int dice = 1; dice <= 6; dice++) {
			count += BoardPath(si + dice, ei);
		}
		return count;
	}

	public static int[] bp = new int[10];

	public static int BoardPathDP(int si, int ei) {

		if (si == ei)
			return 1;

		if (si > ei)
			return 0;

		if (bp[si] != 0) {
			return bp[si];
		}
		int count = 0;
		for (int dice = 1; dice <= 6; dice++) {
			count += BoardPathDP(si + dice, ei);
		}
		bp[si] = count;
		return count;
	}

	public static int BoardPathTable(int ei) {

		int[] bp = new int[ei + 1];
		for (int si = ei; si >= 0; si--) {
			if (si == ei) {
				bp[si] = 1;
				continue;
			}
			int count = 0;
			for (int dice = 1; dice <= 6; dice++) {
				if (si + dice <= ei)
					count += bp[si + dice];
			}
			bp[si] = count;
		}

		return bp[0];
	}

	public static int BoardPathBiased(int[] arr, int si, int ei) {

		if (si == ei)
			return 1;

		if (si > ei)
			return 0;

		int count = 0;
		for (int dice : arr)
			count += BoardPathBiased(arr, si + dice, ei);

		return count;
	}

	public static int[] bpBiased = new int[10];

	public static int BoardPathBiasedDP(int[] arr, int si, int ei) {

		if (si == ei)
			return 1;

		if (si > ei)
			return 0;

		if (bpBiased[si] != 0)
			return bpBiased[si];

		int count = 0;
		for (int dice : arr)
			count += BoardPathBiased(arr, si + dice, ei);

		bpBiased[si] = count;
		return count;
	}

	public static int BoardPathBiasedTable(int[] arr, int ei) {

		int[] bpBiased = new int[ei + 1];
		for (int si = ei; si >= 0; si--) {
			if (si == ei) {
				bpBiased[si] = 1;
				continue;
			}
			int count = 0;
			for (int dice : arr)
				if (si + dice <= ei)
					count += bpBiased[si + dice];

			bpBiased[si] = count;
		}
		return bpBiased[0];
	}

}
