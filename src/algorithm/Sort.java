package algorithm;

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

    private static void swap(int first, int second, int[] array){
        if (first == second)
            return;
        array[first] = array[first] + array[second];
        array[second] = array[first] - array[second];
        array[first] = array[first] - array[second];
    }

}
