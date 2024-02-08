package ds.non_linear;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Trie {
    private static final int ALPHABET_SIZE = 26;
    private class Node{
        private char value;
        private HashMap<Character, Node> children;
        private boolean isEndOfWord;
        public Node(char value){
            this.value = value;
            children = new HashMap<>();
        }
        public boolean containsChild(char child){
            return children.containsKey(child);
        }
        public void putChild(char child){
            children.put(child, new Node(child));
        }
        public Node getChild(char ch){
            return children.getOrDefault(ch, null);
        }
        public void remove(char ch){
            children.remove(ch);
        }
        public boolean hasChildren(){
            return !children.isEmpty();
        }
        public Node[] getChildren(){
            return children.values().toArray(new Node[0]);
        }
        @Override
        public String toString(){
            return "" + value;
        }
    }

    private Node root;

    public Trie(){
        root = new Node(' ');
    }

    public void insert(String word){
        if (word == null || word.isEmpty())
            throw new IllegalArgumentException();
        var current = root;
        for (char ch : word.toCharArray()){
            if(!current.containsChild(ch))
                current.putChild(ch);
            current = current.getChild(ch);
        }
        current.isEndOfWord = true;
    }

    public boolean contains(String word){
        if (word == null || word.isEmpty())
            return false;
        var current = root;
        for (char ch : word.toCharArray()){
            if(!current.containsChild(ch))
                return false;
            current = current.getChild(ch);
        }
        return current.isEndOfWord;
    }

    private boolean containsRecursive(Node root, String word, int index){

        if(index == word.length())
            return root.isEndOfWord;

        var ch = word.charAt(index);
        var child = root.getChild(ch);

        if (child == null)
            return false;

        if(child.value != ch)
            return false;

        return containsRecursive(child, word, index+1);
    }

    public boolean containsRecursive(String word){
        if (word == null || word.isEmpty())
            return false;
        return containsRecursive(root, word, 0);
    }


    private void remove(Node root, String word, int index){
        if (index == word.length()){
            root.isEndOfWord = false;
            return;
        }

        var ch = word.charAt(index);
        var child = root.getChild(ch);

        remove(child, word, index+1);
        if(!child.isEndOfWord && !child.hasChildren())
            root.remove(ch);
    }

    public void remove(String word){
        if (word == null || word.isEmpty())
            return;
        remove(root, word, 0);
    }

    private void autoCompleteSuggests(Node root, String word, List<String> suggestions){
        if(root.isEndOfWord)
            suggestions.add(word);

        for (var child: root.getChildren())
            autoCompleteSuggests(child, word+child.value, suggestions);
    }

    public List<String> autoCompleteSuggests(String word){
        List<String> suggestions = new LinkedList<>();
        if (word == null || word.isEmpty())
            return suggestions;
        var current = root;
        for (char ch : word.toCharArray()){
            if (!current.containsChild(ch))
                return suggestions;
            current = current.getChild(ch);
        }
        autoCompleteSuggests(current, word, suggestions);
        return suggestions;
    }

    public int countWords(){
        return countWords(root);
    }

    private int countWords(Node root){
        int total = 0;

        if(root.isEndOfWord)
            total++;

        for (var child : root.getChildren())
            total += countWords(child);

        return total;
    }

    private String getLongestPrefix(int shortestLength){
        StringBuilder prefix = new StringBuilder();
        var current = root;
        int count = 0;
        while (current.getChildren().length == 1){
            if (count == shortestLength)
                break;
            current = current.getChildren()[0];
            prefix.append(current.value);
            count++;
        }
        return prefix.toString();
    }

    public static String longestPrefix(String[] words){
        if (words == null || words.length == 0)
            return "";
        Trie trie = new Trie();
        int shortestLen = Integer.MAX_VALUE;
        for (var word : words){
            if (word == null || word.isEmpty()) return "";
            if (word.length() < shortestLen)
                shortestLen = word.length();
            trie.insert(word);
        }
        return trie.getLongestPrefix(shortestLen);
    }

}
