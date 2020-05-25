package Recursion;

import java.util.ArrayList;
import java.util.Collections;

public class Lecture_01_Recursion {

	public static void main(String[] args) {

		// Set01
		System.out.println(getss("abc"));
		printss("abc", "");
		System.out.println(num_of_ss("abc", ""));
		System.out.println("****************");

		// Set02
		System.out.println(get_per("abc"));
		print_per("abc", "");
		System.out.println(no_of_per("abc", ""));
		print_per_sequentially("abc", "");
		System.out.println(num_per_sequentially("abc", ""));
		System.out.println("****************");

		// Set03
		System.out.println(get_per_dup("aac"));
		print_per_dup("aac", "");
		System.out.println(no_of_per_dup("aac", ""));
		System.out.println("********************");

		// Set04
		System.out.println(get_Keypad("11"));
		print_Keypad("111", "");
		System.out.println(num__Keypad("111", ""));
		System.out.println("********************");

		// Set05
		System.out.println(Nokia_Keypad("1011", ""));

	}

	public static ArrayList<String> getss(String str) {

		if (str.length() == 0) {
			ArrayList<String> er = new ArrayList<String>();
			er.add("");
			return er;
		}

		char cc = str.charAt(0);
		String ros = str.substring(1);

		ArrayList<String> mr = new ArrayList<String>();
		ArrayList<String> rr = getss(ros);

		for (String key : rr) {
			mr.add(key);
			mr.add(cc + key);
		}

		return mr;

	}

	public static void printss(String str, String ans) {

		if (str.length() == 0) {
			System.out.print(ans + " , ");
			return;
		}

		char cc = str.charAt(0);
		String ros = str.substring(1);

		printss(ros, ans);
		printss(ros, ans + cc);

	}

	public static int num_of_ss(String str, String ans) {

		if (str.length() == 0) {
			return 1;
		}

		int count = 0;
		char cc = str.charAt(0);
		String ros = str.substring(1);

		count += num_of_ss(ros, ans);
		count += num_of_ss(ros, ans + cc);

		return count;
	}

	public static ArrayList<String> get_per(String str) {

		if (str.length() == 0) {
			ArrayList<String> er = new ArrayList<String>();
			er.add("");
			return er;
		}

		char cc = str.charAt(0);
		String ros = str.substring(1);

		ArrayList<String> mr = new ArrayList<String>();
		ArrayList<String> rr = get_per(ros);

		for (String key : rr) {
			for (int i = 0; i <= key.length(); i++) {
				mr.add(key.substring(0, i) + cc + key.substring(i));
			}
		}

		return mr;

	}

	public static void print_per(String str, String ans) {

		if (str.length() == 0) {
			System.out.print(ans + " , ");
			return;
		}

		char cc = str.charAt(0);
		String ros = str.substring(1);

		for (int i = 0; i <= ans.length(); i++) {
			print_per(ros, ans.substring(0, i) + cc + ans.substring(i));
		}

	}

	public static int no_of_per(String str, String ans) {

		if (str.length() == 0) {
			return 1;
		}

		char cc = str.charAt(0);
		String ros = str.substring(1);

		int count = 0;
		for (int i = 0; i <= ans.length(); i++) {
			count += no_of_per(ros, ans.substring(0, i) + cc + ans.substring(i));
		}

		return count;

	}

	public static void print_per_sequentially(String str, String ans) {

		if (str.length() == 0) {
			System.out.print(ans + " , ");
			return;
		}

		for (int i = 0; i < str.length(); i++) {
			print_per_sequentially(str.substring(0, i) + ans.substring(i + 1), ans + str.charAt(i));
		}

	}

	public static int num_per_sequentially(String str, String ans) {

		if (str.length() == 0) {
			return 1;
		}

		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			count += num_per_sequentially(str.substring(0, i) + str.substring(i + 1), ans + str.charAt(i));
		}

		return count;
	}

	public static ArrayList<String> get_per_dup(String str) {

		if (str.length() == 0) {
			ArrayList<String> er = new ArrayList<String>();
			er.add("");
			return er;
		}

		char cc = str.charAt(0);
		String ros = str.substring(1);

		ArrayList<String> mr = new ArrayList<String>();
		ArrayList<String> rr = get_per(ros);

		for (String key : rr) {
			for (int i = 0; i <= key.length(); i++) {
				mr.add(key.substring(0, i) + cc + key.substring(i));
			}
		}

		Collections.sort(mr);
		for (int i = 0; i < mr.size() - 1; i++) {
			if (mr.get(i).equals(mr.get(i + 1))) {
				mr.remove(i + 1);
			}
		}

		return mr;

	}

	public static void print_per_dup(String str, String ans) {

		if (str.length() == 0) {
			System.out.print(ans + " , ");
			return;
		}
		boolean[] arr = new boolean[26];
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!arr[ch - 'a']) {
				arr[ch - 'a'] = true;
				print_per_dup(str.substring(0, i) + str.substring(i + 1), ans + ch);
			}
		}

	}

	public static int no_of_per_dup(String str, String ans) {

		if (str.length() == 0) {
			return 1;
		}
		int count = 0;
		boolean[] arr = new boolean[26];
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!arr[ch - 'a']) {
				arr[ch - 'a'] = true;
				count += no_of_per_dup(str.substring(0, i) + str.substring(i + 1), ans + ch);
			}
		}

		return count;

	}

	public static ArrayList<String> get_Keypad(String str) {

		if (str.length() == 0) {
			ArrayList<String> er = new ArrayList<String>();
			er.add("");
			return er;
		}

		char cc = str.charAt(str.length() - 1);
		String ros = str.substring(0, str.length() - 1);

		ArrayList<String> mr = new ArrayList<String>();
		ArrayList<String> rr = get_Keypad(ros);

		for (String key : rr) {
			String code = getcode(cc);
			for (int i = 0; i < code.length(); i++) {
				mr.add(key + code.charAt(i));
			}
		}

		return mr;

	}

	public static void print_Keypad(String str, String ans) {

		if (str.length() == 0) {
			System.out.print(ans + " , ");
			return;
		}

		char cc = str.charAt(0);
		String ros = str.substring(1);
		String code = getcode(cc);

		for (int i = 0; i < code.length(); i++) {
			print_Keypad(ros, ans + code.charAt(i));
		}

		if (str.length() > 1) {
			char ch = str.charAt(1);
			String temp = "" + cc + ch;
			String ros2 = str.substring(2);
			if (temp.equals("10") || temp.equals("11")) {
				String code2 = getCode(temp);
				for (int i = 0; i < code2.length(); i++) {
					print_Keypad(ros2, ans + code2.charAt(i));
				}
			}

		}

	}

	public static int num__Keypad(String str, String ans) {

		if (str.length() == 0) {
			return 1;
		}

		int count = 0;
		char cc = str.charAt(0);
		String ros = str.substring(1);
		String code = getcode(cc);

		for (int i = 0; i < code.length(); i++) {
			count += num__Keypad(ros, ans + code.charAt(i));
		}

		if (str.length() > 1) {
			char ch = str.charAt(1);
			String temp = "";
			temp += cc + ch;
			String ros2 = str.substring(2);
			if (temp.equals("10") || temp.equals("11")) {
				String code2 = getCode(temp);
				for (int i = 0; i < code2.length(); i++) {
					count += num__Keypad(ros2, ans + code2.charAt(i));
				}
			}

		}

		return count;
	}

	public static String getCode(String str) {
		if (str.equals("10")) {
			return "!@#";
		} else if (str.equals("11")) {
			return "/*-";
		}
		return null;
	}

	public static int Nokia_Keypad(String str, String ans) {

		if (str.length() == 0) {
			System.out.print(ans + " , ");
			return 1;
		}

		char cc = str.charAt(0);
		String ros = str.substring(1);
		if (cc == '0')
			return 0;

		int count = 0;
		count += Nokia_Keypad(ros, ans + Code[cc - '1']);

		if (str.length() > 1) {
			int idx = Integer.parseInt("" + cc + str.charAt(1));
			ros = str.substring(2);
			if (idx >= 10 && idx <= 26) {
				count += Nokia_Keypad(ros, ans + Code[idx - 1]);
			}
		}

		return count;

	}

	public static String[] Code = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };

	public static String getcode(char ch) {
		if (ch == '1')
			return "abc";
		else if (ch == '2')
			return "def";
		else if (ch == '3')
			return "ghi";
		else if (ch == '4')
			return "jk";
		else if (ch == '5')
			return "lmno";
		else if (ch == '6')
			return "pqr";
		else if (ch == '7')
			return "stu";
		else if (ch == '8')
			return "vwx";
		else if (ch == '9')
			return "yz";
		else if (ch == '0')
			return "0#";
		else
			return "";
	}

}
