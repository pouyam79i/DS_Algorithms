import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

public class Queue {

    private class Node {
        private int value;
        private Node next;
        public Node(int value){
            this.value = value;
        }
    }

    private Node front, back;
    private int count;

    public Queue(){
        front = back = null;
        count = 0;
    }

    public void enqueue(int value){
        Node newNode = new Node(value);
        if (front == null){
            front = back = newNode;
        }else {
            back.next = newNode;
            back = newNode;
        }
        count++;
    }

    public int dequeue(){
        if (isEmpty())
            throw new EmptyStackException();
        var v = front.value;
        if(front == back){
            front = back = null;
        }else{
            Node previous = front;
            front = previous.next;
            previous.next = null;
        }
        count--;
        return v;
    }

    public int peek(){
        if (isEmpty())
            throw new EmptyStackException();
        return front.value;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public void reverseFirstKthElement(int kth){
        // O(k+n)
        if (kth < 1) return;
        if (kth > count) throw new IllegalStateException();
        Stack<Integer> stack = new Stack<>();
        int restOfQ = count - kth;
        for (int i = 0; i < kth; i++)
            stack.push(dequeue());
        for (int i = 0; i < kth; i++)
            enqueue(stack.pop());
        for (int i = 0; i < restOfQ; i++)
            enqueue(dequeue());
    }

    public void print(){
        Node current = front;
        System.out.print("[");
        while (current != null){
            System.out.print(" " + current.value);
            current = current.next;
        }
        System.out.println(" ]");
    }

}
