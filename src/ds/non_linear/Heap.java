package ds.non_linear;

import java.util.Arrays;

// Deploy a max heap
public class Heap {

    private int[] heap;
    private int count;

    public Heap(int size){
        if (size < 10) size = 10;
        heap = new int[size];
    }

    public void insert(int value){
        if (isFull())
            throw new IllegalStateException();

        heap[count++] = value;
        bubbleUp();
    }

    public int remove(){
        if (isEmpty())
            throw new IllegalStateException();

        int max = heap[0];
        count--;
        heap[0] = heap[count];
        heap[count] = 0;

        bubbleDown();
        return max;
    }

    private void bubbleUp(){
        int child = count - 1;
        if (child == 0) return;

        // bubble up
        while (child > 0 && (heap[child] > heap[parent(child)])){
            swap(child, parent(child));
            child = parent(child);
        }
    }

    private void bubbleDown(){
        int parentIndex = 0;
        int maxChildIndex  = child(parentIndex);

        // bubble down
        while (maxChildIndex < count){
            if (maxChildIndex+1 < count && (heap[maxChildIndex] < heap[maxChildIndex+1]))
                maxChildIndex++; // choose second child

            if (heap[parentIndex] < heap[maxChildIndex])
                swap(parentIndex, maxChildIndex);

            parentIndex = maxChildIndex;
            maxChildIndex = child(parentIndex);
        }
    }

    private int parent(int child){
        return (child - 1) / 2;
    }

    private int child(int parent){
        return parent * 2 + 1;
    }

    private void swap(int first, int second){
        swap(heap, first, second);
    }

    private void swap(int[] array, int first, int second){
        if (first < 0 || second < 0) return;

        array[first] += array[second];
        array[second] = array[first] - array[second];
        array[first] -= array[second];
    }

    public int size(){
        return count;
    }

    public String toString(){
        return "heap=" + Arrays.toString(heap);
    }

    public boolean isFull(){
        return count == heap.length;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    private static boolean isMaxHeap(int[] array, int index){

        int lastParentIndex = array.length/2 - 1;
        if (index > lastParentIndex)
            return true;

        int leftChild = index*2 + 1;
        int rightChild = index*2 + 2;

        if (array[leftChild] > array[index]) return false;
        if (rightChild < array.length && array[rightChild] > array[index]) return false;

        return isMaxHeap(array, leftChild) && isMaxHeap(array, rightChild);
    }

    public static boolean isMaxHeap(int[] array){
        if (array == null) throw new IllegalStateException();
        return isMaxHeap(array, 0);
    }



    public static void heapify(int[] array){
        if (array == null || array.length <= 1) return;
        int lastParent = (array.length/2) - 1;

        int parent;
        int leftChild;
        int rightChild;
        int maxChild;
        int swap;

        for (int i = lastParent; i >= 0; i--){

            for (int j = i; j < array.length;){

                // find max child index
                leftChild = j * 2 + 1;
                if (leftChild >= array.length) break;
                rightChild = leftChild + 1;
                maxChild = leftChild;
                if (rightChild < array.length && array[rightChild] > array[leftChild]){
                    maxChild = rightChild;
                }

                // swap if child is greater
                if (array[j] < array[maxChild]){
                    swap = array[j];
                    array[j] = array[maxChild];
                    array[maxChild] = swap;
                }

                j = maxChild;
            }

        }

    }

}
