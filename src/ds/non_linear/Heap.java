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

        int child = count++;
        heap[child] = value;
        if (child == 0) return;

        // bubble up
        while (child > 0 && (heap[child] > heap[parent(child)])){
            swap(child, parent(child));
            child = parent(child);
        }
    }

    public int remove(){
        if (isEmpty())
            throw new IllegalStateException();

        int max = heap[0];
        count--;

        heap[0] = heap[count];
        heap[count] = 0;
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
        return max;
    }

    private int parent(int child){
        return (child - 1) / 2;
    }

    private int child(int parent){
        return parent * 2 + 1;
    }

    private void swap(int first, int second){
        if (first < 0 || second < 0) return;

        heap[first] += heap[second];
        heap[second] = heap[first] - heap[second];
        heap[first] -= heap[second];
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

}
