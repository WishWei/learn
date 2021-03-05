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
        quickSortWrap(array);
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
}
