package com.wish.slidewindow;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 滑动窗口最大值
 */
public class SlideWindow {
    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> windowMax = new ArrayList<>();
        // 条件检查
        if (num == null || size < 1 || num.length < 1|| size > num.length) {
        return windowMax;
    }
        Deque<Integer> indexQueue = new LinkedList<>();
        /**
         *         窗口还没有被填满时，找最大值的索引
         */
        for (int i = 0; i < size && i < num.length; i++) {
        // 如果索引对应的值比之前存储的索引值对应的值大或者相等，就删除之前存储的值
        while (!indexQueue.isEmpty() && num[i] >= num[indexQueue.getLast()]) {
            indexQueue.removeLast();
        }
        //  添加索引
        indexQueue.addLast(i);
    }
        // 窗口已经被填满了
        for (int i = size; i < num.length; i++) {
        // 第一个窗口的最大值保存
        windowMax.add(num[indexQueue.getFirst()]);
        // 如果新加入窗口的的值比队列中的值大，就删除队列的值
        while (!indexQueue.isEmpty() && num[i] >= num[indexQueue.getLast()]) {
            indexQueue.removeLast();
        }
        // 删除队列中已经滑出窗口的数据对应的下标
        if (!indexQueue.isEmpty() && indexQueue.getFirst() <= (i - size)) {
            indexQueue.removeFirst();
        }
        // 可能的最大的下标索引入队
        indexQueue.addLast(i);
    }
        // 最后一个窗口最大值入队
        windowMax.add(num[indexQueue.getFirst()]);
        return windowMax;
    }

    public static void main(String[] args) {
        int[] numbers = {2,6,2,5,7,1,3,4,6,2,1,7};
        SlideWindow slideWindow = new SlideWindow();
        List<Integer> indexs = slideWindow.maxInWindows(numbers,3);
        String result = indexs.stream().map(integer -> String.valueOf(integer)).collect(Collectors.joining());
        System.out.println(result);

    }
}