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


}
