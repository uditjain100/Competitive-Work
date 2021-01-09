package PepCoding;

import java.util.HashMap;

public class Recursion {

    public static void main(String[] args) {

        String str = "";

        HashMap<String,String> map = new HashMap<>();

        queensCombinations(0, 2, 0, 0, "");
    }

    public static void abbrevation(String str, int idx, int count, String ans) {
        if (idx >= str.length() || count > str.length()) {
            System.out.println(ans);
            return;
        }

        abbrevation(str, idx + 1, count + 1, ans + str.charAt(idx));
        for (int i = 1; i <= (str.length() - count); i++)
            if ((ans.length() == 0) || (ans.charAt(ans.length() - 1) < '0' || ans.charAt(ans.length() - 1) > '9'))
                abbrevation(str, idx + i, count + i, ans + i);
    }

    public static int maxScore(String[] arr, int[] farr, int[] score, int idx) {

        if (idx == arr.length)
            return 0;

        int max = 0;
        for (int i = idx; i < arr.length; i++) {
            int[] carr = new int[26];
            boolean temp = false;
            for (char ch : arr[i].toCharArray()) {
                carr[ch - 'a']++;
                if (carr[ch - 'a'] > farr[ch - 'a']) {
                    temp = true;
                    break;
                }
            }
            if (temp)
                continue;
            int count = 0;
            for (char ch : arr[i].toCharArray()) {
                farr[ch - 'a']--;
                count += score[ch - 'a'];
            }
            int ra = maxScore(arr, farr, score, i + 1) + count;
            max = Math.max(max, ra);
            for (int j = 0; j < 26; j++)
                farr[j] += carr[j];
        }
        return max;
    }

    public static void queensCombinations(int qpsf, int tq, int r, int c, String ans) {
        if (r == tq) {
            if (qpsf == tq)
                System.out.println(ans);
            return;
        }

        if (c >= tq) {
            queensCombinations(qpsf, tq, r + 1, 0, ans + "\n");
            return;
        }
        if (qpsf < tq) {
            queensCombinations(qpsf + 1, tq, r, c + 1, ans + "q");
            queensCombinations(qpsf, tq, r, c + 1, ans + "-");
        } else
            queensCombinations(qpsf, tq, r, c + 1, ans + "-");
    }
}
