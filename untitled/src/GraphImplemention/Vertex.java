package GraphImplemention;

public interface Vertex<V> {
    public V getElement();

    public int numOutgoing();

    public int numIncoming();
}
