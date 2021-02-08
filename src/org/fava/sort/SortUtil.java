package org.fava.sort;

import org.omg.CORBA.ARG_IN;

import java.util.Arrays;

public class SortUtil {

    public static void bubbleSort(int[] array) {
        if (array.length <= 1) {
            return;
        }

        for (int i = 0; i < array.length - 1; ++i) {
            for (int j = 0; j < array.length - 1 - i; ++j) {
                if (array[j] > array[j + 1]) {
                    int t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
        }
    }

    public static void selectionSort(int[] array) {
        if (array.length <= 1) {
            return;
        }

        for (int i = 0; i < array.length - 1; ++i) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; ++j) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int t = array[i];
                array[i] = array[minIndex];
                array[minIndex] = t;
            }
        }
    }

    public static void insertionSort(int[] array) {
        if (array.length <= 1) {
            return;
        }

        for (int i = 0; i < array.length - 1; ++i) {
            int current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && array[preIndex] > current) {
                array[preIndex + 1] = array[preIndex];
                --preIndex;
            }
            array[preIndex + 1] = current;
        }
    }

    public static void shellSort(int[] array) {
        if (array.length <= 1) {
            return;
        }

        int gap = array.length / 2;
        while (gap > 0) {
            for (int i = 0; i < array.length - gap; ++i) {
                int current = array[i + gap];
                int preIndex = i;
                while (preIndex >=0 && array[preIndex] > current) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = current;
            }
            gap /= 2;
        }
    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            System.out.printf("%d ", array[i]);
        }
        System.out.printf("\n");
    }

    public static void main(String[] args) {
        int[] a = {20,40,30,10,60,50};
//        bubbleSort(a);
//        selectionSort(a);
//        insertionSort(a);
//        shellSort(a);
        print(a);
    }
}
