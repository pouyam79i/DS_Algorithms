import java.util.EmptyStackException;
import java.util.Stack;

public class QueueWithTwoStack {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public QueueWithTwoStack(){
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void enqueue(int item){
        stack1.push(item);
    }

    public int dequeue(){
        if (isEmpty())
            throw new EmptyStackException();

        moveStack1ToStack2();

        return stack2.pop();
    }

    public int peek(){
        if (isEmpty())
            throw new EmptyStackException();

        moveStack1ToStack2();

        return stack2.peek();
    }

    public boolean isEmpty(){
        return stack1.empty() && stack2.empty();
    }

    private void moveStack1ToStack2() {
        if (stack2.empty())
            while (!stack1.empty())
                stack2.push((stack1.pop()));
    }

}
