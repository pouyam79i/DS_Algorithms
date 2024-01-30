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
            Node current = first;
            if (current == last){
                first.next = newNode;
            }else{
                while (current.next != null){
                    current = current.next;
                }
                current.next = newNode;
            }
            last = newNode;
        }
        size++;
    }

    public void removeFirst() {
        if (size == 0){
            throw new EmptyStackException();
        }
        if (size == 1){
            first = last = null;
        } else if (size > 1) {
            Node nextNode = first.next;
            first.next = null;
            first = nextNode;
        }
        size--;
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

}
