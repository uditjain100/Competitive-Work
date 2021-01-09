package Bit_Manipulation;

public class Lecture_05_Bits {

	public static void main(String[] args) {

		// Set01
		countingBits(10);
		System.out.println(OFF_TO_ON_kth_bit(4, 2));
		System.out.println(ON_TO_OFF_kth_bit(7, 2));
		System.out.println(setBits1(7));
		System.out.println(setBits2(7));
		System.out.println(Least_Significant_SetBit(6));
		System.out.println(One_Unique_Number_Repetation_k(new int[] { 1, 1, 1, 1, 3, 5, 5, 5, 5 }, 4));
		System.out.print(Two_Unique_Number_Repetation_k(new int[] { 1, 1, 1, 1, 3, 5, 4, 5, 5, 5 })[0] + " , ");
		System.out.println(Two_Unique_Number_Repetation_k(new int[] { 1, 1, 1, 1, 3, 5, 4, 5, 5, 5 })[1]);
		System.out.println(Power_Of_Two(9));
		System.out.println(Power_Of_Four(44));

	}

	// Important
	public static int[] countingBits(int n) {

		int[] ans = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			ans[i] = ans[(i & (i - 1))] + 1;
			System.out.print(ans[i] + " , ");
		}

		return ans;

	}

	// ON_TO_ON
	public static int OFF_TO_ON_kth_bit(int num, int k) {

		int mask = (1 << (k - 1));
		return (num | mask);

	}

	// OFF_TO_OFF
	public static int ON_TO_OFF_kth_bit(int num, int k) {

		int mask = (~(1 << (k - 1)));
		return (num & mask);

	}

	public static int setBits1(int num) {

		int count = 0;
		while (num != 0) {
			if ((num & 1) == 1) {
				count++;
			}
			num >>>= 1;
		}
		return count;

	}

	// (log(n)) complexity
	public static int setBits2(int num) {

		int count = 0;
		while (num != 0) {
			num &= (num - 1);
			count++;
		}
		return count;
	}

	public static int Least_Significant_SetBit(int num) {
		return (num & (-num));
	}

	public static int One_Unique_Number_Repetation_k(int[] arr, int k) {

		int[] bit = new int[32];
		for (int i = 0; i < arr.length; i++) {
			int j = 31;
			while (arr[i] != 0) {
				bit[j--] += (arr[i] & 1);
				arr[i] >>>= 1;
			}
		}
		for (int i = 0; i < bit.length; i++) {
			bit[i] %= k;
		}
		int ans = 0;
		for (int i = bit.length - 1; i >= 0; i--) {
			ans += bit[i] * Math.pow(2, bit.length - i - 1);
		}
		return ans;

	}

	// Condition: Repeated nums should be repeated even number of times
	public static int[] Two_Unique_Number_Repetation_k(int[] arr) {

		int num = arr[0];
		for (int i = 1; i < arr.length; i++) {
			num ^= arr[i];
		}

		int fsb_mask = (num & (-num));

		int a = 0;
		int b = 0;
		for (int element : arr) {
			if ((element & fsb_mask) == 0) {
				a ^= element;
			} else {
				b ^= element;
			}
		}

		return new int[] { a, b };

	}

	public static boolean Power_Of_Two(int num) {
		return (num > 0) && (num & (num - 1)) == 0;
	}

	public static boolean Power_Of_Four(int num) {

		if (num == 1 || num == 0) {
			return false;
		}

		int j = 0;
		int count = 0;
		while (num != 0) {
			if ((num & 1) == 1 && (j & 1) == 1) {
				return false;
			} else if ((num & 1) == 1 && (j & 1) == 0) {
				count++;
			}
			num >>>= 1;
			j++;
		}
		if (count == 1) {
			return true;
		}
		return false;

	}

}
