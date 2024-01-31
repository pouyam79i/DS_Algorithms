import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

public class Stack {

    private final List<Character> leftBrackets  = Arrays.asList('(','[', '<', '{');
    private final List<Character> rightBrackets = Arrays.asList(')',']', '>', '}');

    private ArrayList stk;

    public Stack(){
        stk = new ArrayList(2);
    }

    public void push(int item){
        stk.insert(item);
    }

    public int pop(){
        if (empty())
            throw new EmptyStackException();
        return stk.removeAt(stk.size() - 1);
    }

    public int peek(){
        if (empty())
            throw new EmptyStackException();
        return stk.getItemByIndex(stk.size() - 1);
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
