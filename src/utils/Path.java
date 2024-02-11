package utils;

import java.util.LinkedList;
import java.util.List;

public class Path {
    List<String> nodes;
    public Path(){
        nodes = new LinkedList<>();
    }
    public void add(String node){
        nodes.add(node);
    }
    @Override
    public String toString() {
        return nodes.toString();
    }
}
