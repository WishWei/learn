package com.wish.kmp;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * KMP 字符串匹配算法
 *
 * @author wish
 * @date 2021/1/9 15:36
 */
public class KmpSolution {

    /**
     * 求next数组
     * @param t
     * @return
     */
    public static int[] getNextArray(String t)
    {
        char[] chars = t.toCharArray();
        int next[] = new int[chars.length];
        int j=0, k=-1;
        next[0] =- 1;
        while(j < t.length() - 1)
        {
            if(k == -1 || chars[j] == chars[k]) //t[k]我有这么长的前缀，你能匹配上吗，能匹配到k就是我们的公共最大前后缀长度。要不起？那我的子前缀要得起吗 见next
            {
                j++;k++;
                next[j] = k;
            }
            else k = next[k];//走到else，说明这个子模式串并没有k个长度的公共最大前缀和后缀，k--？这样效率太低，直接求k下标对应最大相同前缀和后缀的最大相同长度作为下标。因为之前是能匹配上的，求出的next[k],前几位也是能匹配上的（前缀=后缀）
        }
        return next;
    }


    /**
     * kmp匹配字符串位置
     * @param s
     * @param p
     * @return
     */
    public static int kmp(String s, String p) {
        int m = s.length(), n = p.length(), i = 0, j = 0;
        int[] next = getNextArray(p);
        while (i < m && j < n) {
            if (j == - 1 || s.charAt(i) == p.charAt(j)) {
                ++i; ++j;
            } else {
                //不能匹配则到查找能匹配的模式串的最大前缀，因为前缀=后缀，所以这个前缀一定是匹配的，就看移动后的j能匹配吗
                j = next[j];
            }
        }
        return (j == n) ? i - j : -1;
    }


    public static void main(String[] args) {
        String source = "sdfsadabcababcsdf";
        String pattern = "abcababc";
        int[] nextArray = getNextArray(pattern);
        System.out.println(pattern);
        System.out.println(Arrays.stream(nextArray).boxed().map(String::valueOf).collect(Collectors.joining()));
        int index = kmp(source, pattern);
        System.out.println(index);
    }
}
