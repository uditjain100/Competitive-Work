package DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;

public class Lecture_08 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		int[] dp = new int[12];
//		System.out.println(DecodeWays("13561054946", dp));
//		for (int ele : dp)
//			System.out.print(ele + ", ");
//		System.out.println();
//		int[] dp1 = new int[12];
//		System.out.println(DecodeWays_02("13561054946", 0, dp1));
//		for (int ele : dp1)
//			System.out.print(ele + ", ");
//		System.out.println();
//		System.out.println(DecodeWaysTable_02("13561054946"));
//
//		System.out.println(per("abbc", ""));
//		System.out.println(per_02("abbc", ""));
//
//		HashMap<Character, Integer> map = freq("abbc");
//		HashMap<Character, Integer> ansmap = new HashMap<Character, Integer>();
//		ansmap.put('a', 0);
//		ansmap.put('b', 0);
//		ansmap.put('c', 0);
//		System.out.println(map);
//		System.out.println(NoOfSubsequences("abcabc"));

		System.out.println(DecodeWaysll("**", 0, new int[4]));

	}

	public static int DecodeWays(String str, int[] dp) {

		if (str.length() == 0)
			return dp[str.length()] = 1;

		if (dp[str.length()] != 0)
			return dp[str.length()];

		int count = 0;

		count += (str.charAt(0) != '0') ? DecodeWays(str.substring(1), dp) : 0;

		if (str.length() > 1) {
			String s = str.substring(0, 2);
			int data = Integer.parseInt(s);
			if (data == 0)
				return 0;
			if (data > 9 && data < 27)
				count += DecodeWays(str.substring(2), dp);
		}
		return dp[str.length()] = count;
	}

	public static int DecodeWays_02(String str, int idx, int[] dp) {

		if (idx == str.length())
			return dp[idx] = 1;

		if (dp[idx] != 0)
			return dp[idx];

		int count = 0;

		count += (str.charAt(idx) != '0') ? DecodeWays_02(str, idx + 1, dp) : 0;

		if (idx < str.length() - 1) {
			String s = str.substring(idx, idx + 2);
			int data = Integer.parseInt(s);
			if (data == 0)
				return 0;
			if (data > 9 && data < 27)
				count += DecodeWays_02(str, idx + 2, dp);
		}
		return dp[idx] = count;

	}

	public static int DecodeWaysTable_02(String str) {

		int[] dp = new int[str.length() + 1];
		for (int idx = str.length(); idx >= 0; idx--) {
			if (idx == str.length()) {
				dp[idx] = 1;
				continue;
			}

			int count = 0;
			count += (str.charAt(idx) != '0') ? dp[idx + 1] : 0;

			if (idx < str.length() - 1) {
				String s = str.substring(idx, idx + 2);
				int data = Integer.parseInt(s);
				if (data == 0)
					return 0;
				if (data > 9 && data < 27)
					count += dp[idx + 2];
			}
			dp[idx] = count;
		}
		return dp[0];
	}

	public static HashMap<Character, Integer> freq(String str) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		for (char ch : str.toCharArray()) {
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			} else {
				map.put(ch, 1);
			}
		}
		return map;
	}

	public static int NoOfSubsequences(String str) {
		int aCount = 0;
		int bCount = 0;
		int cCount = 0;
		for (char ch : str.toCharArray()) {
			if (ch == 'a')
				aCount += aCount + 1;
			else if (ch == 'b')
				bCount += aCount + bCount;
			else
				cCount += bCount + cCount;
		}
		return cCount;
	}

	public static int DistinctSubsequences(String str) {

		int[] dp = new int[str.length()];
		int[] lo = new int[26];
		Arrays.fill(lo, -1);
		dp[0] = 1;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			dp[i + 1] = dp[i] * 2;

			if (lo[ch - 'a'] != -1) {
				dp[i + 1] = dp[lo[ch - 'a'] - 1];
			}
			lo[ch - 'a'] = i + 1;
		}
		return dp[str.length() - 1];
	}

	public static int DecodeWaysll(String str, int idx, int[] dp) {
		if (idx == str.length())
			return dp[idx] = 1;

		if (dp[idx] != 0)
			return dp[idx];

		int count = 0;

		if (str.charAt(idx) == '*') {
			for (int i = 1; i <= 9; i++) {
				count += DecodeWaysll(str.substring(0, idx) + i + str.substring(idx + 1), idx, dp);
			}
		} else {
			count += (str.charAt(idx) != '0') ? DecodeWaysll(str, idx + 1, dp) : 0;
			if (idx < str.length() - 1) {
				String s = str.substring(idx, idx + 2);
				if (s.charAt(0) != '*' && s.charAt(1) != '*') {
					int data = Integer.parseInt(s);
					if (data == 0)
						return 0;
					if (data > 9 && data < 27)
						count += DecodeWaysll(str, idx + 2, dp);
				} else if (s.charAt(0) == '*') {
					for (int i = 1; i <= 9; i++) {
						count += DecodeWaysll(str.substring(0, idx) + i + str.substring(idx + 1), idx, dp);
					}
				} else {
					for (int i = 1; i <= 9; i++) {
						count += DecodeWaysll(str.substring(0, idx + 1) + i + str.substring(idx + 2), idx, dp);
					}
				}
			}
		}
		return dp[idx] = count;
	}

}
