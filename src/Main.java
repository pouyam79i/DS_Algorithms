import ds.non_linear.Graph;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");

        graph.addEdge("A", "B");
        graph.addEdge("A", "E");
        graph.addEdge("C", "A");
        graph.addEdge("C", "B");
        graph.addEdge("C", "D");
        graph.addEdge("B", "E");
        graph.addEdge("D", "E");

        graph.print();

        graph.traversBreadthFirst("C");

    }
}
