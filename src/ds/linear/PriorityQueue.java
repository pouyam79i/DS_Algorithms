import java.util.Arrays;
import java.util.EmptyStackException;

public class PriorityQueue {

    private int[] array;
    private int count;

    public PriorityQueue(int size){
        if (size < 1) size = 1;
        array = new int[size];
    }

    public void add(int item){
        if (isFull()) throw new IllegalStateException();
        // shift
        int i;
        for (i=count-1; i >= 0; i--){
            if (item < array[i]){
                array[i+1] = array[i];
            }else{
                break;
            }
        }
        array[i+1] = item;
        count++;
    }

    public int remove(){
        if (isEmpty()) throw new EmptyStackException();
        var v = array[--count];
        array[count] = 0;
        return v;
    }

    public boolean isFull(){
        return count == array.length;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public void print(){
        System.out.println(Arrays.toString(array));
    }

}
