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

    public static void mergeSort(int[] array, int left, int right) {
        if (right - left < 2 || left < 0 || right > array.length) {
            return;
        }

        int mid = (left + right) / 2;
        int[] leftHalf = new int [mid - left];
        for (int i = 0; i < mid - left; ++i) {
            leftHalf[i] = array[left + i];
        }
        int[] rightHalf = new int [right - mid];
        for (int i = 0; i < right - mid; ++i) {
            rightHalf[i] = array[mid + i];
        }
        mergeSort(leftHalf, 0, mid - left);
        mergeSort(rightHalf, 0, right - mid);
        for (int index = left, i = 0, j = 0; index < right; ++index) {
            if (i >= mid - left) {
                array[index] = rightHalf[j++];
            } else if (j >= right - mid) {
                array[index] = leftHalf[i++];
            } else if (leftHalf[i] < rightHalf[j]) {
                array[index] = leftHalf[i++];
            } else {
                array[index] = rightHalf[j++];
            }
        }
    }

    public static void quickSort(int[] array, int start, int end) {
        if (end - start < 1 || start < 0 || end >= array.length) {
            return;
        }

        int pivot = (int) (start + Math.random() * (end - start + 1));
        int t = array[pivot];
        array[pivot] = array[end];
        array[end] = t;

        int smallIndex = start - 1;
        for (int i = start; i <=end; ++i) {
            if (array[i] <= array[end]) {
                ++smallIndex;
                int tt = array[i];
                array[i] = array[smallIndex];
                array[smallIndex] = tt;
            }
        }

        if (smallIndex > start) {
            quickSort(array, start, smallIndex - 1);
        }
        if (smallIndex < end) {
            quickSort(array, smallIndex + 1, end);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    private static void adjustHeap(int[] array, int index, int length) {
        int maxIndex = index;
        if (2 * index + 1 < length && array[2 * index + 1] > array[maxIndex]) {
            maxIndex = 2 * index + 1;
        }
        if (2 * index + 2 < length && array[2 * index + 2] > array[maxIndex]) {
            maxIndex = 2 * index + 2;
        }
        if (maxIndex != index) {
            swap(array, maxIndex, index);
            adjustHeap(array, maxIndex, length);
        }
    }

    public static void heapSort(int[] array) {
        if (array.length <= 1) {
            return;
        }

        for (int i = array.length / 2 - 1; i >= 0; --i) {
            adjustHeap(array, i, array.length);
        }

        int length = array.length;
        while (length > 1) {
            swap(array, 0, length - 1);
            --length;
            adjustHeap(array, 0, length);
        }
    }

    public static void countingSort(int[] array) {
        if (array.length <= 1) {
            return;
        }

        int min = array[0];
        int max = array[0];
        for (int i = 0; i < array.length; ++i) {
            if (min > array[i]) {
                min = array[i];
            }
            if (max < array[i]) {
                max = array[i];
            }
        }

        int[] countingMap = new int[max - min + 1];
        Arrays.fill(countingMap, 0);
        for (int i = 0; i < array.length; ++i) {
            countingMap[array[i] - min]++;
        }

        int index = 0;
        int i = 0;
        while (index < array.length) {
            if (countingMap[i] != 0) {
                array[index++] = i + min;
                --countingMap[i];
            } else {
                ++i;
            }
        }
    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            System.out.printf("%d ", array[i]);
        }
        System.out.printf("\n");
    }

    public static void main(String[] args) {
        int[] a = {70,20,40,90,30,20,10,60,50,80,60,100};
//        bubbleSort(a);
//        selectionSort(a);
//        insertionSort(a);
//        shellSort(a);
//        mergeSort(a, 0, a.length);
//        quickSort(a, 0,  a.length - 1);
//        heapSort(a);
//        countingSort(a);
        print(a);
    }
}
