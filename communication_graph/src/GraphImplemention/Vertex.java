package GraphImplemention;

public interface Vertex<V> {
    public V getElement();

    int numOutgoing();

    int numIncoming();
}
