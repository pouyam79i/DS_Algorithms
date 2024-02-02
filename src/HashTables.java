import java.util.HashMap;
import java.util.Map;

public class HashTables {

    public void FirstNonRepeatedChars(String input){
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = input.toCharArray();
        for (char c : chars){
            if (c == ' ') continue;
            var count = map.getOrDefault(c, 0);
            map.put(c, count+1);
        }
        for (char c : chars)
            // laziness in if - but do not do this :)
            if (c != ' ' && map.get(c) == 1 ){
                System.out.println(c);
                return;
            }
    }

}
