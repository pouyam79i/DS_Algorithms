package ds.non_linear;

import utils.Path;

import java.util.*;

public class WeightedGraph {
    private class Node{
        private String label;
        private List<Edge> edges;
        public Node(String label){
            this.label = label;
            edges = new LinkedList<>();
        }
        public void addEdge(Node to, Integer weight){
            edges.add(new Edge(this, to, weight));
        }
        public List<Edge> getEdgesList(){
            return edges;
        }
        @Override
        public String toString(){
            return label;
        }
    }

    private class Edge{
        private Node from;
        private Node to;
        private int weight;
        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public String toString() {
            return from + "->" + to + ":" + weight;
        }
    }

    private class NodeEntry{
        private Node node;
        private int priority;
        public NodeEntry(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }

    private Map<String, Node> vertices;

    public WeightedGraph(){
        vertices = new HashMap<>();
    }

    public void addNode(String label){
        if (!vertices.containsKey(label)){
            var newNode = new Node(label);
            vertices.put(label, newNode);
        }
    }

    public void addEdge(String from, String to, int weight){
        var fromNode = vertices.get(from);
        var toNode = vertices.get(to);
        if(fromNode == null || toNode == null)
            throw new IllegalArgumentException();

        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    public WeightedGraph getMinSpanningTree(){
        var tree = new WeightedGraph();
        if (vertices.isEmpty())
            return tree;

        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparing(e -> e.weight));

        for (var startNode : vertices.values()){
            if(tree.vertices.size() == vertices.size())
                break;
            if(tree.containsNode(startNode.label))
                continue;

            tree.addNode(startNode.label);
            queue.addAll(startNode.getEdgesList());

            while (tree.vertices.size() < vertices.size()){
                if (queue.isEmpty()) break;

                var edge = queue.remove();
                var nextNode = edge.to;

                if(tree.containsNode(nextNode.label))
                    continue;

                tree.addNode(nextNode.label);
                tree.addEdge(edge.from.label, nextNode.label, edge.weight);

                queue.addAll(nextNode.getEdgesList());
            }
        }

        return tree;
    }

    public boolean hasCycle(){

        Set<Node> all = new HashSet<>(vertices.values());
        Set<Node> visited = new HashSet<>();

        while (!all.isEmpty())
            if(hasCycle(all.iterator().next(), null, all, visited))
                return true;

        return false;
    }

    private boolean hasCycle(Node root, Node parent, Set<Node> all, Set<Node> visited){
        if (visited.contains(root))
            return  false;
        visited.add(root);
        all.remove(root);

        for (var edge : root.getEdgesList()){
            var neighbor = edge.to;
            if (neighbor == parent)
                continue;
            if(visited.contains(neighbor))
                return true;
            if(hasCycle(neighbor, root, all, visited))
                return true;
        }

        return false;
    }

    // implementing dijkstra algorithm.
    // assume weights must be >= 0,
    // no self cycle,
    // it is undirected but between 2 nodes we have only one edge.
    public Path shortestPathDistance(String from, String to){
        var fromNode = vertices.get(from);
        var toNode = vertices.get(to);
        if(fromNode == null || toNode == null)
            throw new IllegalArgumentException();

        // start node table
        Map<Node, Integer> distance = new HashMap<>();
        Map<Node, Node> previousNode = new HashMap<>();
        Set<Node> visited = new HashSet<>();

        // pq
        PriorityQueue<NodeEntry> queue = new PriorityQueue<>(
                Comparator.comparing(ne -> ne.priority)
        );

        // find path
        int priority = 0;
        queue.add(new NodeEntry(fromNode, 0));
        distance.put(fromNode, 0);

        while (!queue.isEmpty()){
            var current = queue.remove().node;
            visited.add(current);
            for (var edges : current.getEdgesList()){
                if (!visited.contains(edges.to)){
                    queue.add(new NodeEntry(edges.to, priority + 1));
                    var newDistance = edges.weight + distance.get(current);
                    if (newDistance < distance.getOrDefault(edges.to, Integer.MAX_VALUE)){
                        distance.put(edges.to, newDistance);
                        previousNode.put(edges.to, current);
                    }
                }
            }
            priority++;
        }

        // build path
        return buildPath(toNode, previousNode);
    }

    private Path buildPath(Node target, Map<Node, Node> previousNode){
        Path path = new Path();
        Stack<Node> stack = new Stack<>();
        for(var current = target; current != null;){
            stack.push(current);
            current = previousNode.getOrDefault(current, null);
        }

        while (!stack.isEmpty())
            path.add(stack.pop().label);

        return path;
    }

    public boolean containsNode(String label){
        return vertices.containsKey(label);
    }

    public void print(){
        for (var node : vertices.values().toArray(new Node[0]))
            System.out.println(node + " is connected to " + node.getEdgesList());
    }

}
