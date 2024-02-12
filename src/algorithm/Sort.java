package algorithm;

public class Sort {

    public static void bubbleSort(int[] array){
        boolean isSorted;
        for(int i=0; i < array.length; i++){
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

    private static void swap(int first, int second, int[] array){
        array[first] += array[second];
        array[second] = array[first] - array[second];
        array[first] -= array[second];
    }

}
