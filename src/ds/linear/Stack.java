import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

public class Stack {

    private final List<Character> leftBrackets  = Arrays.asList('(','[', '<', '{');
    private final List<Character> rightBrackets = Arrays.asList(')',']', '>', '}');

    private int[] array;
    private int ptr1;
    private int ptr2;

    public Stack(int size){
        if (size < 1)
            size = 1;
        array = new int[size];
        ptr1 = -1;
        ptr2 = size;
   }

   public void push1(int item){
        if (ptr1 + 1 == ptr2){
            throw new StackOverflowError();
        }
        ptr1++;
        array[ptr1] = item;
   }

    public void push2(int item){
        if (ptr2 - 1 == ptr1){
            throw new StackOverflowError();
        }
        ptr2--;
        array[ptr2] = item;
    }

    public int pop1(){
        if (ptr1 < 0){
            throw new EmptyStackException();
        }
        return array[ptr1--];
    }

    public int pop2(){
        if (ptr2 == array.length){
            throw new EmptyStackException();
        }
        return array[ptr2++];
    }

    public int peek1(){
        if (ptr1 < 0){
            throw new EmptyStackException();
        }
        return array[ptr1];
    }

    public int peek2(){
        if (ptr2 == array.length){
            throw new EmptyStackException();
        }
        return array[ptr2];
    }

    public boolean isFull(){
        return (ptr1 + 1) == ptr2;
    }

    public boolean empty1(){
        return ptr1 < 0;
    }

    public boolean empty2(){
        return ptr2 == array.length;
    }

    public String SeverseString(String s){
        if (s==null)
            throw new IllegalArgumentException();
        java.util.Stack<Character> stack = new java.util.Stack<Character>();
        for (char ch : s.toCharArray())
            stack.push(ch);
        StringBuffer reversed = new StringBuffer();
        while (!stack.empty())
            reversed.append(stack.pop());
        return reversed.toString();
    }

    public boolean ValidateBalancedExpression(String s){
        java.util.Stack<Character> stack = new java.util.Stack<Character>();
        for (char ch : s.toCharArray()){
            if (isLeftBracket(ch))
                stack.push(ch);
            else if (isRightBracket(ch)){
                if (stack.empty()) return false;
                if (!isMatchBracket(stack.pop(), ch)) return false;
            }
        }
        return stack.empty();
    }

    private boolean isLeftBracket(char c){
        return leftBrackets.contains(c);
    }

    private boolean isRightBracket(char c){
        return rightBrackets.contains(c);
    }

    private boolean isMatchBracket(char left, char right){
        return leftBrackets.indexOf(left) == rightBrackets.indexOf(right);
    }

}
