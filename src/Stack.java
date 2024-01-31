import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

public class Stack {

    private final List<Character> leftBrackets  = Arrays.asList('(','[', '<', '{');
    private final List<Character> rightBrackets = Arrays.asList(')',']', '>', '}');

    private LinkedList stk;

    public Stack(){
        stk = new LinkedList();
    }

    public void push(int item){
        stk.addFirst(item);
    }

    public int pop(){
        return stk.removeFirst();
    }

    public int peek(){
        return stk.peekFirst();
    }

    public boolean empty(){
        return stk.size() == 0;
    }

    public void print(){
        stk.print();
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
