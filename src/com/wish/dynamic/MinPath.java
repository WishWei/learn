package com.wish.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wish
 * @ClassName MinPath.java
 * @Description 最小路径, 动态规划
 * @createTime 2021年03月06日 14:14:00
 */
public class MinPath {
    public static int minPathSum(int[][] grid) {
        int[][] minPath = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j ++) {
                int sum = 0;
                if(i == 0 && j > 0) {
                    sum = minPath[i][j - 1];
                }
                if(j == 0 && i > 0) {
                    sum = minPath[i - 1][j];
                }
                if(j > 0 && i > 0) {
                    int sumLeft = minPath[i][j - 1];
                    int sumUp = minPath[i - 1][j];
                    sum = Math.min(sumLeft, sumUp);
                }
                minPath[i][j] = sum + grid[i][j];

            }
        }
        return minPath[grid.length -1][grid[0].length -1];
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minPathSum(grid));
        List<String> list = new ArrayList<>();
    }
}
