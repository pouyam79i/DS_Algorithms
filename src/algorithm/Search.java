package algorithm;

public class Search {

    public static int linearSearch(int[] array, int target){
        for (int i = 0; i < array.length; i++)
            if (array[i] == target)
                return i;
        return -1;
    }

    public static int binarySearch(int[] array, int target){
//        return binarySearchRecursive(array, target, 0, array.length-1);
        return binarySearchIterative(array, target);
    }

    public static int ternarySearch(int[] array, int target){
        return ternarySearch(array, target, 0, array.length-1);
    }

    public static int jumpSearch(int[] array, int target){
        if (array.length == 0)
            return -1;

        int blockSize = (int) Math.sqrt(array.length);
        int start=0;
        int next=0;

        while (start < array.length){
            next = Math.min(blockSize + start, array.length);
            if (target <= array[next-1])
                break;
            start = next;
        }

        for (int i = start; i < next; i++)
            if (target == array[i])
                return i;

        return -1;
    }

    public static int exponentialSearch(int[] array, int target){
        if (array.length == 0)
            return -1;

        int bound = 1;

        while (bound < array.length && target > array[bound-1])
            bound = Math.min(bound*2, array.length);

        return ternarySearch(array, target, bound/2, bound-1);
    }

    private static int ternarySearch(int[] array, int target, int begin, int end){
        if (begin > end)
            return -1;

        var partition = (end-begin) / 3;
        var b1 = begin + partition;
        var b2 = end - partition;

        if (target < array[b1]){
            return ternarySearch(array, target, begin, b1-1);
        } else if (target == array[b1]) {
            return b1;
        } else if (target < array[b2]) {
            return ternarySearch(array, target, b1+1, b2-1);
        } else if (target == array[b2]) {
            return b2;
        } else {
            return ternarySearch(array, target, b2+1, end);
        }
    }

    private static int binarySearchRecursive(int[] array, int target, int begin, int end){
        if (begin > end)
            return -1;

        int mid = (begin+end)/2;

        if (target < array[mid])
            return binarySearchRecursive(array, target, begin, mid-1);
        else if (target > array[mid])
            return binarySearchRecursive(array, target, mid+1, end);
        else
            return mid;
    }

    private static int binarySearchIterative(int[] array, int target){
        int begin = 0, end = array.length-1;

        while (begin <= end){
            var mid = (begin + end) / 2;

            if (target < array[mid])
                end = mid - 1;
            else if (target > array[mid])
                begin = mid + 1;
            else
                return mid;
        }
        return -1;
    }

}