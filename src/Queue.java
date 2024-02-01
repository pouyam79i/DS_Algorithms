import java.util.Arrays;
import java.util.EmptyStackException;

public class Queue {

    private int[] array;
    private int front, back, count;

    public Queue(int size){
        if (size < 1)
            size = 1;
        array = new int[size];
        count = 0;
        front = -1;
        back = -1;
    }

    public void enqueue(int item){
        if (count == array.length)
            throw new StackOverflowError();
        if (front == -1){
            front = back = 0;
            array[0] = item;
        }
        else{
            back++;
            back %= array.length;
            array[back] = item;
        }
        count++;
    }

    public int dequeue(){
        if (count == 0)
            throw new EmptyStackException();
        int item = array[front];
        array[front] = 0;
        if (back == front){
            back = front = -1;
        }else {
            front++;
            front %= array.length;
        }
        count--;
        return item;
    }

    public int peek(){
        if (count == 0)
            throw new EmptyStackException();
        return array[front];
    }

    public int size(){
        return count;
    }

    public void print(){
        System.out.println(Arrays.toString(array));
    }

}
