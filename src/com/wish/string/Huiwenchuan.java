package com.wish.string;

/**
 * @author wish
 * @ClassName Huiwenchuan.java
 * @Description 最大回文串
 * @createTime 2021年03月06日 13:16:00
 */
public class Huiwenchuan {
    public static String longestPalindrome(String s) {
        int maxLength = 0;
        int startIndex = 0;
        for(int i = 0; i<s.length(); i ++) {
            int len0 = maxLength(s, i, i);
            int len1 = 0;
            if(i < s.length() - 1) {
                len1 = maxLength(s, i, i + 1);
            }
            int len = Math.max(len0, len1);
            if(len > maxLength) {
                maxLength = len;
                startIndex = i - (len - 1) / 2;
            }
        }
        return s.substring(startIndex, maxLength + startIndex);
    }

    public static int maxLength(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start --;
            end ++;
        }
        return end - start - 1;
    }

    public static void main(String[] args) {
        String s = "bb";
        System.out.println(longestPalindrome(s));
    }
}
