package com.wish.kmp;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author wish
 * @date 2021/3/8 17:42
 */
public class WishKmpSolution {

    public static void main(String[] args) {
        String source = "sdfsadabcababcsdf";
        String pattern = "abcababc";
        int[] nextArray = getNextArray(pattern);
        System.out.println(pattern);
        System.out.println(Arrays.stream(nextArray).boxed().map(String::valueOf).collect(Collectors.joining()));
        int index = kmp(source, pattern);
        System.out.println(index);
    }
    public static int kmp(String s, String p) {
        int[] next = getNextArray(p);
        int i = 0;
        int k = 0;
        while(i < s.length() && k < p.length()) {
            if(k == -1 || s.charAt(i) == p.charAt(k)) {
                i++;
                k++;
            }else {
                k = next[k];
            }
        }
        return p.length() == k ? i - k: -1;
    }

    public static int[] getNextArray(String pattern) {
        int[] next = new int[pattern.length()];
        int k = -1;
        int i = 0;
        next[0] = -1;
        while(i < pattern.length() - 1) {
            if(k == -1 || pattern.charAt(k) == pattern.charAt(i)) {
                i++;
                k++;
                next[i] = k;
            }else {
                k = next[k];
            }
        }
        return next;
    }
}
