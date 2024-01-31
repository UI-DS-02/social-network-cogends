package GraphImplemention;

public interface Graph<V,E>{
    public int numVertices();
    public Iterable<Vertex<V>> vertices();
    public int numEdges();
    public Iterable<Edge<E>> edges();
    public int outDegree(Vertex<V> vertex);
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex);
    public int inDegree(Vertex<V> vertex);
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex);
    public Edge<E> getEdge(Vertex<V> u,Vertex<V> v);
    public Vertex<V>[] endVertices(Edge<E> inputEdge);
    public Vertex<V> opposite(Vertex<V> v , Edge<E> e);
    public Vertex<V> insertVertex(V element);
    public Edge<E> insertEdge(V u , V v , E element);
    public void removeVertex(Vertex<V> v);
    public void removeEdge(Edge<E> v);
}
