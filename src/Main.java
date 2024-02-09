import ds.non_linear.Graph;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addEdge("A", "B");
        graph.addEdge("A", "D");
        graph.addEdge("C", "A");
        graph.addEdge("D", "B");

        graph.print();
    }
}
