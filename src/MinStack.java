import java.util.EmptyStackException;

public class MinStack {

    int[] array;
    int minElm;
    int ptr;

    public MinStack(int size){
        if (size < 1)
            size = 1;
        array = new int[size];
        ptr = -1;
   }

    public void push(int item){
        if (isFull())
            throw new StackOverflowError();
        ptr++;
        if (ptr == 0){
            minElm = item;
        }else if (minElm > item){
                array[ptr] = 2*item - minElm;
                minElm  = item;
                return;
        }
            array[ptr] = item;
    }

    public int pop(){
        if (isEmpty())
            throw new EmptyStackException();
        int v = array[ptr];
        if ( v < minElm){
            array[ptr] = minElm;
            minElm = 2 * minElm - v;
        }
        return array[ptr--];
    }

    public int peek(){
        if (isEmpty())
            throw new EmptyStackException();
        return Math.max(array[ptr], minElm);
    }

    public int getMin(){
        return minElm;
    }

    public boolean isEmpty(){
        return ptr < 0;
    }

    public boolean isFull(){
        return ptr+1 == array.length;
    }

    public void print(){
        if (ptr < 0){
            System.out.println("[ ]");
        }else{
            System.out.print("[ ");
            for(int i = 0; i <= ptr; i++){
                System.out.print(array[i] + " ");
            }
            System.out.print("] ");
            System.out.println("- min: " + minElm);
        }
    }

}
