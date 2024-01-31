package GraphImplemention;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EdgeList<V extends Comparable<V>, E> implements Graph<V, E> {
    class InnerVertex<V> implements Vertex<V> {
        private V element;
        private Position<Vertex<V>> position;
        private List<Vertex<V>> outgoing, incoming;

        public InnerVertex(V element, boolean graphIsDirected) {
            this.element = element;
            outgoing = new ArrayList<>();
            if (graphIsDirected)
                incoming = new ArrayList<>();
            else incoming = outgoing;
        }
        @Override
        public V getElement() {
            return element;
        }
        public void setPosition(Position<Vertex<V>> position) {
            this.position = position;
        }

        public Position<Vertex<V>> getPosition() {
            return position;
        }

        @Override
        public int numOutgoing() {
            return outgoing.size();
        }

        @Override
        public int numIncoming() {
            return incoming.size();
        }

        public List<Vertex<V>> getOutgoing() {
            return outgoing;
        }

        public List<Vertex<V>> getIncoming() {
            return incoming;
        }
    }
    class InnerEdge<E> implements Edge<E> {
        private E element;
        private Position<Edge<E>> position;
        private Vertex<V>[] endPoints;

        public InnerEdge(Vertex<V> u, Vertex<V> v, E element) {
            this.element = element;
            endPoints = (Vertex<V>[]) new Vertex[]{u, v};
        }

        public E getElement() {
            return element;
        }

        public Vertex<V>[] getEndPoints() {
            return endPoints;
        }

        public void setPosition(Position<Edge<E>> position) {
            this.position = position;
        }

        public Position<Edge<E>> getPosition() {
            return position;
        }
    }
    private boolean isDirected;
    private LinkedPositionalList<Vertex<V>> vertices = new LinkedPositionalList<>();
    private LinkedPositionalList<Edge<E>> edges = new LinkedPositionalList<>();

    public EdgeList(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public int numVertices() {
        return vertices.size();
    }

    public Iterable<Vertex<V>> vertices() {
        return vertices;
    }

    public int numEdges() {
        return edges.size();
    }

    public Iterable<Edge<E>> edges() {
        return edges;
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return 0;
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return null;
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return 0;
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return null;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
        return null;
    }

    @Override
    public Vertex<V>[] endVertices(Edge<E> inputEdge) {
        return new Vertex[0];
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        return null;
    }

    @Override
    public Vertex<V> insertVertex(V element) {
        return null;
    }

    @Override
    public Edge<E> insertEdge(V u, V v, E element) {
        return null;
    }

    @Override
    public void removeVertex(Vertex<V> v) {

    }

    @Override
    public void removeEdge(Edge<E> v) {

    }


}
