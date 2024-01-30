public class ArrayList {

    private int[] array;
    private int count;

    public ArrayList(int length){
        if (length < 1){
            length = 1;
        }
        array = new int[length];
        count = 0;
    }

    // add item at the end of array
    public void insert(int item){
        if (count >= array.length){
            int[] newArray = new int[array.length * 2];
            for (int i = 0; i < count; i++){
                newArray[i] = array[i];
            }
        }
        array[count] = item;
        count++;
    }

    // search item by index
    public int getItemByIndex(int index){
        if (index < 0 || index >= count){
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public void removeAt(int index){
        if (index < 0 || index >= count){
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < count; i++){
            if ((i + 1) < count)
                array[i] = array[i+1];
        }
        count--;
    }

    // search by item
    public int indexOf(int item){
        for (int i = 0; i < count; i++){
            if (array[i] == item) return  i;
        }
        return -1;
    }

    public void print(){
        System.out.print("[");
        for (int i = 0; i < count; i++){
            System.out.print(" ");
            System.out.print(array[i]);
        }
        System.out.println(" ]");
    }

}
