import java.util.*;
import java.util.LinkedList;

public class HashTables {

    private class Pair{
        int key;
        String value;
        public Pair(int key, String value){
            this.key = key;
            this.value = value;
        }
    }

    // using chaining method!
    private final LinkedList[] table;

    public HashTables(int tableSize){
        if (tableSize < 10){
            tableSize = 10;
        }
        table = new LinkedList[tableSize];
    }

    public String get(int key){
        Iterator<Pair> each = getOrCreateListByKey(key).iterator();
        while (each.hasNext()) {
            Pair pair = each.next();
            if (pair.key == key) {
                return pair.value;
            }
        }
        return null;
    }

    public void put(int key, String value){
        Iterator<Pair> each = getOrCreateListByKey(key).iterator();
        while (each.hasNext()) {
            Pair pair = each.next();
            if (pair.key == key) {
                pair.value = value;
                return;
            }
        }
        getOrCreateListByKey(key).add(new Pair(key, value));
    }

    public String remove(int key){
        Iterator<Pair> each = getOrCreateListByKey(key).iterator();
        while (each.hasNext()) {
            Pair pair = each.next();
            if (pair.key == key) {
                String value = pair.value;
                each.remove();
                return value;
            }
        }
        return null;
    }

    private LinkedList<Pair> getOrCreateListByKey(int key){
        int hashValue = hashFunction(key);
        LinkedList<Pair> list = table[hashValue];
        if (list == null){
            list = new LinkedList<>();
            table[hashValue] = list;
        }
        return list;
    }

    private int hashFunction(int key){
        return key % table.length;
    }

//    other practices
    public void findMostRepeatedChar(String input){
        if (input == null || input.isEmpty()){
            return;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int repTime = 0;
        char mostRepChar = ' ';
        for (char ch : input.toCharArray()){
            if (ch == ' ') continue;
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            if (repTime < map.get(ch)){
                if (ch != mostRepChar){
                    mostRepChar = ch;
                }
                repTime = map.get(ch);
            }
        }
        System.out.println("Most rep char: " + mostRepChar + " rep times: " + repTime);
    }

    public void countPairsWithDiff(int[] array, int diff){
        if (array == null || array.length == 0){
            return;
        }
        HashMap<Integer, Boolean> map = new HashMap<>();
        int count = 0;
        LinkedList<String> pairs = new LinkedList<>();

        // adding items to the set
        for (int i : array) map.put(i, false);

        for (int i : array) {
            if (!map.get(i)){
                if (map.containsKey(i+diff)){
                    map.put(i, true);
                    count++;
                    pairs.add("(" + i + ", " + (i+diff) + ")");
                }
            }
        }

        System.out.println("Total Pairs: " + count);
        System.out.println(pairs);
    }

}
