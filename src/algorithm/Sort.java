package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort {
    public static void bubbleSort(int[] array){
        boolean isSorted;
        for (int i=0; i < array.length; i++){
            isSorted = true;
            for (int j=1; j < array.length - i; j++)
                if (array[j-1] > array[j]){
                    swap(j-1, j, array);
                    isSorted = false;
                }
            if(isSorted)
                return;
        }
    }

    public static void selectionSort(int[] array){
        int minValueIndex;
        for (int i = 0; i < array.length; i++){
            minValueIndex = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j] < array[minValueIndex])
                    minValueIndex = j;
            swap(i, minValueIndex, array);
        }
    }

    public static void insertionSort(int[] array){
        for (int i = 0; i < array.length; i++)
            for (int j = i; j > 0; j--)
                if (array[j-1] > array[j])
                    swap(j-1, j, array);
                else
                    break;
    }

    public static void mergeSort(int[] array){
        if (array.length < 2)
            return;

        // divide the arrays
        int middle = array.length / 2;
        int[] left = new int[middle];
        for (int i = 0; i < middle; i++)
            left[i] = array[i];
        int[] right = new int[array.length - middle];
        for(int i = middle; i < array.length; i++)
            right[i-middle] = array[i];

        // sort each side
        mergeSort(left);
        mergeSort(right);

        // merge them into one\
        merge(left, right, array);
    }

    public static void quickSort(int[] array){
        quickSortPartition(0, array.length-1, array);
    }

    public static void countingSort(int[] array){
        // find range;
        int minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;
        for (var item : array){
            if (item < minValue)
                minValue = item;
            if (item > maxValue)
                maxValue = item;
        }

        // manage range;
        int range = maxValue - minValue + 1;
        if (range == 1)
            return;

        // count
        int[] countingArray = new int[range];
        for (var item : array)
            countingArray[item - minValue]++;

        // sort
        int k = 0, i;
        for (int j = 0; j < countingArray.length; j++)
            for (i = 0; i < countingArray[j]; i++)
                array[k++] = j+minValue;
    }

    public static void bucketSort(int[] array, int max_bucket){
        int k = 0;
        for(var bucket : createBucket(array, max_bucket)){
            Collections.sort(bucket);
            for (var item: bucket)
                array[k++] = item;
        }
    }

    private static List<List<Integer>> createBucket(int[] array, int number){
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < number; i++)
            buckets.add(new ArrayList<>());
        for (var item : array)
            buckets.get(item/number).add(item);
        return buckets;
    }

    private static void quickSortPartition(int begin, int end, int[] array){
        if (begin >= end)
            return;

        // select last index as pivot
        int piv = end;

        // partition
        piv = partition(begin, end, piv, array);

        // sort each part
        quickSortPartition(begin, piv-1, array);
        quickSortPartition(piv+1, end, array);
    }

    private static int partition(int begin, int end, int piv, int[] array){
        int boundary = begin - 1;

        for(int i = begin; i <= end; i++)
            if (array[i] <= array[piv])
                swap(i , ++boundary, array);

        return boundary;
    }

    private static void merge(int[] left, int[] right, int[] res){
        int i, j, k;
        i=j=k=0;

        while (i < left.length && j < right.length)
            if (left[i] <= right[j])
                res[k++] = left[i++];
            else
                res[k++] = right[j++];

        while (i < left.length)
            res[k++] = left[i++];
        while (j < right.length)
            res[k++] = right[j++];
    }

    private static void swap(int first, int second, int[] array){
        if (first == second)
            return;
        array[first] = array[first] + array[second];
        array[second] = array[first] - array[second];
        array[first] = array[first] - array[second];
    }
}
