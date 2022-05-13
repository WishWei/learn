package com.wish.dynamic;

/**
 * @author wish
 * @Date 2022/5/14 00:17
 */
public class UglyNumber {
    public static int nthUglyNumber(int n) {
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        int []dp = new int[n];
        dp[0] =1;
        for(int i =1;i < n; i++) {
            dp[i] = Math.min(Math.min(dp[i2] * 2, dp[i3] * 3), dp[i5] * 5);
            if(dp[i] == dp[i2] * 2) {
                i2++;
            }
            if(dp[i] == dp[i3] * 3) {
                i3++;
            }
            if(dp[i] == dp[i5] * 5) {
                i5++;
            }
        }
        return dp[n-1];

    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }
}
