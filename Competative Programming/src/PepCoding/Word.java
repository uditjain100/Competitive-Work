package PepCoding;

import java.util.HashMap;

public class Word {

    public static void main(String[] args) {
        int ans = 0;
        // String str = "aabb";
        // HashMap<Character, Integer> map = new HashMap<>();
        // for (char ch : str.toCharArray()) {
        // map.putIfAbsent(ch, 0);
        // map.put(ch, map.get(ch) + 1);
        // }
        // ans = generateUniquePermutations(str, 0, 4, map, "");
        // HashMap<Character, Integer> map = new HashMap<>();
        // for (char ch : str.toCharArray()) {
        // map.put(ch, -1);
        // }
        // ans = generateUniquePermutations2("aabb", new Character[4], 0, map);
        // ans = generateWordsCombinations("abcde", 0, 0, 3, "");
        // ans = generateWordsPermutations("abcde", 0, 3, "");
        // ans = generateWordsPermutations2("abcde", 0, 0, 3, new Character[3]);
        // String str = "aabbbccdde";
        // HashMap<Character, Integer> map = new HashMap<>();
        // for (char ch : str.toCharArray()) {
        //     map.putIfAbsent(ch, 0);
        //     if (map.get(ch) < 2)
        //         map.put(ch, map.get(ch) + 1);
        // }
        // ans = generateWordsCombinations3("abcde", 0, map, 0, 3, "");
        // System.out.println(ans);
    }

    public static void display(Character[] arr) {
        for (char ch : arr)
            System.out.print(ch + " ");
        System.out.println();
    }

    // 1. Here permutations of string with duplicate characters is done
    // and unique characters are choosen to fill the places of answer string
    // Also boxes and words (to be put) are same
    // Similar to queenPermutations2 in Queens

    public static int generateUniquePermutations(String str, int idx, int n, HashMap<Character, Integer> map,
            String ans) {

        if (idx == n) {
            System.out.println(ans);
            return 1;
        }
        int res = 0;
        for (char ch : map.keySet()) {
            if (map.get(ch) != 0) {
                map.put(ch, map.get(ch) - 1);
                res += generateUniquePermutations(str, idx + 1, n, map, ans + ch);
                map.put(ch, map.get(ch) + 1);
            }
        }
        return res;
    }

    // 2. Here permutations of string with duplicate characters is done
    // and boxes are choosen to fill them with unique characters of given string
    // Also boxes and words (to be put) are same
    // Similar to queenPermutations1 in Queens

    public static int generateUniquePermutations2(String str, Character[] arr, int idx,
            HashMap<Character, Integer> map) {

        if (idx == str.length()) {
            for (char ch : arr)
                System.out.print(ch);
            System.out.println();
            return 1;
        }

        int res = 0;
        char ch = str.charAt(idx);
        int lo = map.get(ch);
        for (int i = lo + 1; i < str.length(); i++) {
            if (arr[i] == null) {
                arr[i] = ch;
                map.put(ch, i);
                res += generateUniquePermutations2(str, arr, idx + 1, map);
                map.put(ch, lo);
                arr[i] = null;
            }
        }
        return res;
    }

    // 3. Here combinations of string with duplicate characters is done
    // and unique characters are choosen to fill the places of ans string of size k
    // Also boxes and words (to be put) are not same
    // Similar to generateUniquePermutations

    public static int generateWordsCombinations(String str, int idx, int count, int k, String ans) {
        if (count == k) {
            System.out.println(ans);
            return 1;
        }
        int res = 0;
        for (int i = idx; i < str.length(); i++) {
            char ch = str.charAt(i);
            res += generateWordsCombinations(str, i + 1, count + 1, k, ans + ch);
        }
        return res;
    }

    public static int generateWordsPermutations(String str, int count, int k, String ans) {

        if (count == k) {
            System.out.println(ans);
            return 1;
        }

        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!ans.contains("" + ch))
                res += generateWordsPermutations(str, count + 1, k, ans + ch);
        }
        return res;
    }

    public static int generateWordsPermutations2(String str, int idx, int count, int k, Character[] ans) {

        if (idx == str.length()) {
            if (count == k) {
                display(ans);
                return 1;
            }
            return 0;
        }
        int res = 0;
        char ch = str.charAt(idx);
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == null) {
                ans[i] = ch;
                res += generateWordsPermutations2(str, idx + 1, count + 1, k, ans);
                ans[i] = null;
            }
        }
        res += generateWordsPermutations2(str, idx + 1, count, k, ans);
        return res;
    }

}
