package com.wish.sort;

/**
 * 排序
 *
 * @author wish
 * @date 2021/3/5 21:13
 */
public class Sort {


    public static void main(String[] args) {
        int[] array = {3,1,5,2,6,9,7};
        selectSort(array);
        printArray(array);
    }

    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    /**
     * 插入排序
     * @param array
     */
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int pre = i - 1;
            while (pre >= 0 && array[pre] > current) {
                array[pre + 1] = array[pre];
                pre--;
            }
            array[pre+1] = current;
        }
    }

    /**
     * 冒泡排序
     * @param array
     */
    public static void bubbleSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i ; j++) {
                if(array[j + 1] < array[j] ) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSortWrap(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * 快速排序
     * 以第一个元素作为基准元素，从坑0挖出来。然后先从右边找到第一个比基准元素小的，放到这个坑里
     * 然后右边出现了一个坑，然后从左开始找到第一个比基准元素大的元素，放到右边这个坑
     * 重复这个步骤，直到左边和右边的下标相遇
     *
     * 以相遇这个下标，划分为两个递归
     * @param array
     * @param leftIndex
     * @param rightIndex
     */
    public static void quickSort(int[] array,int leftIndex, int rightIndex) {
        int originLeft = leftIndex;
        int originRight = rightIndex;
        if(leftIndex >= rightIndex) {
            return;
        }
        int base = array[leftIndex];

        while (leftIndex < rightIndex) {
            while (array[rightIndex] > base && leftIndex < rightIndex) {
                rightIndex--;
            }
            array[leftIndex] = array[rightIndex];

            while (array[leftIndex] < base && leftIndex < rightIndex) {
                leftIndex++;
            }
            array[rightIndex] = array[leftIndex];

        }
        int baseIndex = leftIndex;
        array[baseIndex] = base;
        quickSort(array, originLeft, baseIndex - 1);
        quickSort(array, baseIndex + 1, originRight );
    }

    /**
     * 选择排序
     * @param array
     */
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            int minIndex = i;
            for (int j = i; j < array.length ; j++) {
                if(min > array[j]){
                    min = array[j];
                    minIndex = j;
                }
            }
            array[minIndex] = array[i];
            array[i] = min;
        }
    }
}
