import GraphImplemention.AdjacencyMatrixGraph;

public class Main {
    public static void main(String[] args) {
        AdjacencyMatrixGraph g = new AdjacencyMatrixGraph<>(5,false);
        g.insertEdge(g.insertVertex(2),g.insertVertex(3),1);
        g.removeVertex(g.insertVertex(2));
        System.out.println(1);

    }
}

