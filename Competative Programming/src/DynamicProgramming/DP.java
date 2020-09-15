package DynamicProgramming;

public class DP {

	public static void main(String[] args) {
		System.out.println(fibonacciDP(5, new long[6]));
		System.out.println(fibonacci(5));
		System.out.println(fibonacciTable(5));

		System.out.println(tribonacciDP(5, new long[6]));
		System.out.println(tribonacci(5));
		System.out.println(tribonacciTable(5));

		System.out.println(MazePathTable(2, 2));
		int n = 3;
		int[][] mp = new int[n][n];
		System.out.println(MazePathDP(0, 0, n - 1, n - 1, mp));
		display(mp);
		System.out.println(MazePath(0, 0, 2, 2));

		System.out.println(MazePathMultiMove(0, 0, 3, 3));
		int n = 4;
		int[][] mpmm = new int[n][n];
		System.out.println(MazePathMultiMoveDP(0, 0, n - 1, n - 1, mpmm));
		display(mpmm);
		System.out.println(MazePathMultiMoveTable(n - 1, n - 1));

		System.out.println(BoardPath(0, 10));
		int n = 10;
		int[] bp = new int[n + 1];
		System.out.println(BoardPathDP(0, n, bp));
		display(bp);
		System.out.println(BoardPathTable(10));

		System.out.println(BoardPathBiased(new int[] { 1, 2, 3, 4, 5, 6 }, 0, 10));
		int n = 10;
		int[] bpbiased = new int[n + 1];
		System.out.println(BoardPathBiasedDP(new int[] { 1, 2, 3, 4, 5, 6 }, 0, 10, bpbiased));
		display(bpbiased);
		System.out.println(BoardPathBiasedTable(new int[] { 1, 2, 3, 4, 5, 6 }, 10));
	}

	public static void display(int[] arr) {
		for (int ele : arr)
			System.out.print(ele + ", ");
		System.out.println();
	}

	public static void display(int[][] arr) {
		for (int[] a : arr) {
			for (int ele : a)
				System.out.print(ele + ", ");
			System.out.println();
		}
		System.out.println();
	}

	public static long fibonacci(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;

		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public static long fibonacciDP(int n, long[] fibList) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		if (fibList[n] != 0)
			return fibList[n];

		long res = fibonacciDP(n - 1, fibList) + fibonacciDP(n - 2, fibList);
		return fibList[n] = res;
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

	public static long tribonacciDP(int n, long[] tribList) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		if (n == 2)
			return 1;

		if (tribList[n] != 0)
			return tribList[n];

		long res = tribonacciDP(n - 1, tribList) + tribonacciDP(n - 2, tribList) + tribonacciDP(n - 3, tribList);
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

		int count = 0;
		if (sr + 1 <= er)
			count += MazePath(sr + 1, sc, er, ec);
		if (sc + 1 <= ec)
			count += MazePath(sr, sc + 1, er, ec);
		if (sc + 1 <= ec && sr + 1 <= er)
			count += MazePath(sr + 1, sc + 1, er, ec);

		return count;
	}

	public static int MazePathDP(int sr, int sc, int er, int ec, int[][] mp) {

		if (sr == er && sc == ec) {
			return mp[sr][sc] = 1;
		}
		if (mp[sr][sc] != 0)
			return mp[sr][sc];

		int count = 0;
		if (sr + 1 <= er)
			count += MazePathDP(sr + 1, sc, er, ec, mp);
		if (sc + 1 <= ec)
			count += MazePathDP(sr, sc + 1, er, ec, mp);
		if (sc + 1 <= ec && sr + 1 <= er)
			count += MazePathDP(sr + 1, sc + 1, er, ec, mp);
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

		int count = 0;
		for (int i = 1, j = 1; i <= er && j <= ec; i++, j++) {
			if (sr + i <= er)
				count += MazePathMultiMove(sr + i, sc, er, ec);
			if (sc + j <= ec)
				count += MazePathMultiMove(sr, sc + j, er, ec);
			if (sc + i <= ec && sr + j <= er)
				count += MazePathMultiMove(sr + i, sc + j, er, ec);
		}
		return count;
	}

	public static int MazePathMultiMoveDP(int sr, int sc, int er, int ec, int[][] mpmm) {

		if (sr == er && sc == ec) {
			return mpmm[sr][sc] = 1;
		}

		if (mpmm[sr][sc] != 0)
			return mpmm[sr][sc];

		int count = 0;
		for (int i = 1, j = 1; i <= er && j <= ec; i++, j++) {
			if (sr + i <= er)
				count += MazePathMultiMoveDP(sr + i, sc, er, ec, mpmm);
			if (sc + j <= ec)
				count += MazePathMultiMoveDP(sr, sc + j, er, ec, mpmm);
			if (sc + i <= ec && sr + j <= er)
				count += MazePathMultiMoveDP(sr + i, sc + j, er, ec, mpmm);
		}
		mpmm[sr][sc] = count;
		return count;
	}

	public static int MazePathMultiMoveTable(int er, int ec) {

		int[][] mpmm = new int[er + 1][ec + 1];
		for (int sr = er; sr >= 0; sr--) {
			for (int sc = ec; sc >= 0; sc--) {
				if (sr == er && sc == ec) {
					mpmm[sr][sc] = 1;
					continue;
				}

				int count = 0;
				for (int i = 1, j = 1; i <= er && j <= ec; i++, j++) {
					if (sr + i <= er)
						count += mpmm[sr + i][sc];
					if (sc + j <= ec)
						count += mpmm[sr][sc + j];
					if (sr + i <= er && sc + j <= ec)
						count += mpmm[sr + i][sc + j];
				}
				mpmm[sr][sc] = count;
			}
		}
		display(mpmm);
		return mpmm[0][0];
	}

	public static int BoardPath(int si, int ei) {
		if (si == ei)
			return 1;

		int count = 0;
		for (int dice = 1; dice <= 6; dice++)
			if (si + dice <= ei)
				count += BoardPath(si + dice, ei);
		return count;
	}

	public static int BoardPathDP(int si, int ei, int[] bp) {
		if (si == ei)
			return bp[si] = 1;
		if (bp[si] != 0)
			return bp[si];

		int count = 0;
		for (int dice = 1; dice <= 6; dice++)
			if (si + dice <= ei)
				count += BoardPathDP(si + dice, ei, bp);
		return bp[si] = count;
	}

	public static int BoardPathTable(int ei) {
		int[] bp = new int[ei + 1];
		for (int si = ei; si >= 0; si--) {
			if (si == ei) {
				bp[si] = 1;
				continue;
			}
			int count = 0;
			for (int dice = 1; dice <= 6; dice++)
				if (si + dice <= ei)
					count += bp[si + dice];
			bp[si] = count;
		}
		display(bp);
		return bp[0];
	}

	public static int BoardPathBiased(int[] arr, int si, int ei) {
		if (si == ei)
			return 1;

		int count = 0;
		for (int dice : arr)
			if (si + dice <= ei)
				count += BoardPathBiased(arr, si + dice, ei);
		return count;
	}

	public static int BoardPathBiasedDP(int[] arr, int si, int ei, int[] bpBiased) {

		if (si == ei)
			return 1;

		if (bpBiased[si] != 0)
			return bpBiased[si];

		int count = 0;
		for (int dice : arr)
			if (si + dice <= ei)
				count += BoardPathBiasedDP(arr, si + dice, ei, bpBiased);
		return bpBiased[si] = count;
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
