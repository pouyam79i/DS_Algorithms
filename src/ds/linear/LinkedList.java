import java.util.EmptyStackException;

public class LinkedList {

    private class Node {
        private int value;
        private Node next;
        public Node(int value){
            this.value = value;
        }
    }

    private Node first;
    private Node last;
    private int size;

    // actually no need to do this constructor.
    public LinkedList(){
        first = last = null;
        size = 0;
    }

    public void addFirst(int item){
        Node newNode = new Node(item);
        if (first == null){
            first = newNode;
            last = newNode;
        }
        else{
            newNode.next = first;
            first = newNode;
        }
        size++;
    }

    public void addLast(int item){
        Node newNode = new Node(item);
        if (first == null){
            first = newNode;
            last = newNode;
        }
        else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public int removeFirst() {
        if (size == 0){
            throw new EmptyStackException();
        }
        int value = first.value;
        if (size == 1){
            first = last = null;
        } else if (size > 1) {
            Node nextNode = first.next;
            first.next = null;
            first = nextNode;
        }
        size--;
        return value;
    }

    public void removeAt(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        if (index == 0){
            removeFirst();
        }
        // size > 1 and index > 0
        else{
            Node previous = first;
            Node current = first;
            for (int i = 0; i < index; i++){
                previous = current;
                current = current.next;
            }
            previous.next = current.next;
            current.next = null;
            if (current == last){
                previous = last;
            }
            size--;
        }
    }

    public int indexOf(int item){
        Node current = first;
        for (int i = 0; i < size; i++){
            if (item == current.value)
                return i;
            current = current.next;
        }
        return -1;
    }

    public int peekFirst(){
        if (first == null){
            throw new EmptyStackException();
        }
        return first.value;
    }

    public boolean contains(int item){
        return indexOf(item) != -1;
    }

    public void reverse(){
        if (size <= 1)
            return;
        last = first;
        Node current = first.next;
        while (current != null){
            Node nextNode = current.next;
            current.next = first;
            first = current;
            current = nextNode;
        }
        last.next = null;
    }

    public int size(){
        return size;
    }

    public void print(){
        Node current = first;
        System.out.print("[");
        while (current != null){
            System.out.print(" ");
            System.out.print(current.value);
            current = current.next;
        }
        System.out.println(" ]");
    }

    public int kthFromTheEnd(int kthFromTheEnd){
        if (kthFromTheEnd == 0){
            return last.value;
        }
        Node kth = first;
        Node finalNode = first;
        for (int i=0; i < kthFromTheEnd; i++){
            finalNode = finalNode.next;
            if (finalNode == null){
                throw new IndexOutOfBoundsException();
            }
        }
        while (finalNode != last){
            finalNode = finalNode.next;
            kth = kth.next;
        }
        return kth.value;
    }

    public void printMiddleElement(){
        Node mid, forward;
        mid = forward = first;
        if (forward == null){
            System.out.println("{}");
            return;
        }
        boolean isEven = false;
        while (forward.next != null){
            forward = forward.next;
            if (forward.next == null){
                isEven = true;
                break;
            }
            forward = forward.next;
            mid = mid.next;
        }
        System.out.print("{");
        System.out.print(mid.value);
        if (isEven){
            System.out.print("," + mid.next.value);
        }
        System.out.println("}");
    }

    public boolean hasLoop(){
        Node fast, slow;
        fast = slow = first;
        while (fast != null){
            fast = fast.next;
            if (fast == null)
                break;
            fast = fast.next;
            if(slow == fast)
                return true;
            slow = slow.next;
        }
        return false;
    }

}
