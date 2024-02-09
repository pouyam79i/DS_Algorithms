package ds.non_linear;

import javax.crypto.spec.DESedeKeySpec;
import java.util.*;

public class Graph {

    private class Node{
        private String label;
        public Node(String label){
            this.label = label;
        }
        @Override
        public String toString(){
            return label;
        }
    }

    // vertices of graph
    private Map<String, Node> vertices;
    // edges of graph
    private Map<Node, List<Node>> edges;
    // number of vertices
    private int v;
    // number of edges
    private int e;

    public Graph(){
        vertices = new HashMap<>();
        edges = new HashMap<>();
    }

    public void addNode(String label){
        if (!vertices.containsKey(label)){
            var newNode = new Node(label);
            vertices.put(label, newNode);
            edges.put(newNode, new LinkedList<>());
        }
    }

    public void addEdge(String from, String to){
        var fromNode = vertices.getOrDefault(from, null);
        var toNode = vertices.getOrDefault(to, null);
        if (fromNode == null || toNode == null)
            throw new IllegalArgumentException();

        var adjacncyList = edges.get(fromNode);
        if(!adjacncyList.contains(toNode))
            adjacncyList.add(toNode);
    }

    public void removeNode(String label){
        var node = vertices.getOrDefault(label, null);
        if (node == null)
            return;

        for(var adjacencyList : edges.values())
            adjacencyList.remove(node);

        edges.remove(node);
        vertices.remove(label);
    }

    public void removeEdge(String from, String to){
        var fromNode = vertices.getOrDefault(from, null);
        var toNode = vertices.getOrDefault(to, null);
        if (fromNode == null || toNode == null)
            return;

        edges.get(fromNode).remove(toNode);
    }

    private void traversDepthFirstRecursive(Node root, Set<Node> visited){
        System.out.println(root);
        visited.add(root);

        for (var neighbor : edges.get(root))
            if(!visited.contains(neighbor))
                traversDepthFirstRecursive(neighbor, visited);
    }

    private void traversDepthFirstIterative(Node root){
        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            var current = stack.pop();
            System.out.println(current);
            visited.add(current);
            for (var neighbor : edges.get(current))
                if (!visited.contains(neighbor))
                    stack.push(neighbor);
        }

    }

    public void traversDepthFirst(String start){
        var node = vertices.getOrDefault(start, null);
        if (node == null)
            return;
        System.out.println("Recursive:");
        traversDepthFirstRecursive(node, new HashSet<>());
        System.out.println("Iterative:");
        traversDepthFirstIterative(node);
    }


    private void traversBreadthFirst(Node root){
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()){
            var current = queue.remove();

            if (visited.contains(current)) continue;

            System.out.println(current);
            visited.add(current);

            for (var neighbor : edges.get(current))
                if (!visited.contains(neighbor))
                    queue.add(neighbor);

        }
    }

    public void traversBreadthFirst(String start){
        var node = vertices.getOrDefault(start, null);
        if (node == null)
            return;
        traversBreadthFirst(node);
    }

    public void print(){
        for (var node : vertices.values().toArray(new Node[0]))
            System.out.println(node + " is connected to " + edges.get(node));
    }

}
