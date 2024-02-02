package GraphImplemention;
import java.util.ArrayList;

public class AdjacencyList<V extends Comparable<V>, E> implements Graph<V, E> {
    class InnerVertex<V> implements Vertex<V> {
        private V element;
        private Position<Vertex<V>> position;
        private ArrayList<Edge<E>> outgoing, incoming;

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
        public int numOutgoing() {
            return outgoing.size();
        }
        public int numIncoming() {
            return incoming.size();
        }

        public ArrayList<Edge<E>> getOutgoing() {
            return outgoing;
        }

        public ArrayList<Edge<E>> getIncoming() {
            return incoming;
        }
    }
    class InnerEdge<E> implements Edge<E> {
        private E element;
        private Position<Edge<E>> position;

        public InnerEdge(Vertex<V> u, Vertex<V> v, E element) {
            this.element = element;
        }
        public E getElement() {
            return element;
        }

        public void setPosition(Position<Edge<E>> position) {
            this.position = position;
        }

        public Position<Edge<E>> getPosition() {
            return position;
        }
    }
    private boolean isDirected;
    int numEdge=0;
    private LinkedPositionalList<Vertex<V>> vertices = new LinkedPositionalList<>();

    public AdjacencyList(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public int numVertices() {
        return vertices.size();
    }

    public Iterable<Vertex<V>> vertices() {
        return vertices;
    }

    public int numEdges() {
        return numEdge;
    }

    public Iterable<Edge<E>> edges() {
        ArrayList<Edge<E>> edges = new ArrayList<>();
        for (Vertex<V> vertex : vertices){
            edges.addAll(validate(vertex).incoming);
            edges.addAll(validate(vertex).outgoing);
        }
        return edges;
    }

    public int outDegree(Vertex<V> vertex) {
        InnerVertex<V> vertex1 = validate(vertex);
        return vertex1.getOutgoing().size();
    }

    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        InnerVertex<V> vertex1 = validate(vertex);
        return vertex1.getOutgoing();
    }

    public int inDegree(Vertex<V> vertex) {
        InnerVertex<V> vertex1 = validate(vertex);
        return vertex1.getIncoming().size();
    }

    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        InnerVertex<V> vertex1 = validate(vertex);
        return vertex1.getIncoming();
    }

    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
        InnerVertex<V> origin = validate(u);
        InnerVertex<V> des = validate(v);
        for (Edge<E> edge : origin.getOutgoing()){
            for (Edge<E> edge1 : des.getIncoming()){
                if (edge.equals(edge1)){
                    return edge;
                }
            }
        }
        return null;
    }

    public Vertex<V> getVertex(V element) {
        for (Vertex<V> vertex0 : vertices) {
            if (vertex0.getElement().compareTo(element) == 0) {
                return vertex0;
            }
        }
        return null;
    }

    public Vertex<V>[] endVertices(Edge<E> inputEdge) {
        InnerEdge<E> edge = validate(inputEdge);
        Vertex<V>[] endPoint=(Vertex<V>[]) new Vertex[2];
        for (Vertex<V> vertex :vertices){
            for (Edge<E> edge1: validate(vertex).getOutgoing()){
                if (edge.equals(edge1)){
                    endPoint[0]=vertex;
                }
            }
            for (Edge<E> edge1: validate(vertex).getIncoming()){
                if (edge.equals(edge1)){
                    endPoint[1]=vertex;
                }
            }
        }
        return endPoint;
    }

    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        Vertex<V>[] endpoints = endVertices(edge);
        if (endpoints[0] == v)
            return endpoints[1];
        else if (endpoints[1] == v)
            return endpoints[0];
        else throw new IllegalArgumentException("v is not incident to this edge ");
    }

    public Vertex<V> insertVertex(V element) {
        Vertex<V> vertex0=getVertex(element);
        if (vertex0==null){
            InnerVertex<V> vertex = new InnerVertex<>(element, isDirected);
            vertex.setPosition(vertices.addLast(vertex));
            return vertex;
        }
        return validate(vertex0);
    }

    public Edge<E> insertEdge(V uElement, V vElement, E element) {
        Vertex<V> u = getVertex(uElement);
        Vertex<V> v = getVertex(vElement);
        if (u != null && v != null) {
            if (getEdge(u, v) == null) {
                InnerEdge<E> edge = new InnerEdge<>(u, v, element);
                InnerVertex<V> origin = validate(u);
                InnerVertex<V> dest = validate(v);
                ArrayList<Edge<E>>edges= (ArrayList<Edge<E>>) edges();
              // edge.setPosition(edges.);
                origin.getOutgoing().add(edge);
                dest.getIncoming().add(edge);
                numEdge++;
                return edge;
            } else throw new IllegalArgumentException("edge from u to v exists");
        } else throw new IllegalArgumentException("vertex is invalid");

    }

    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) {
        if (getEdge(u, v) == null) {
            InnerEdge<E> edge = new InnerEdge<>(u, v, element);
            InnerVertex<V> origin = validate(u);
            InnerVertex<V> dest = validate(v);
           // edge.setPosition();
            origin.getOutgoing().add(edge);
            dest.getIncoming().add(edge);
            numEdge++;
            return edge;
        }
        return null;
    }

    public void removeVertex(Vertex<V> v) {
        InnerVertex<V> vertex = validate(v);
        for (Edge<E> edge : vertex.getOutgoing())
            vertex.getOutgoing().remove(edge);
        for (Edge<E> edge : vertex.getIncoming())
            vertex.getIncoming().remove(edge);
        vertices.remove(vertex.getPosition());
    }
    public void removeEdge(Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        for (Vertex<V> vertex :vertices){
            for (Edge<E> edge1: validate(vertex).getOutgoing()){
                if (edge.equals(edge1)){
                    validate(vertex).getOutgoing().remove(edge);
                }
            }
            for (Edge<E> edge1: validate(vertex).getIncoming()){
                if (edge.equals(edge1)){
                    validate(vertex).getIncoming().remove(edge);
                }
            }
        }
    }

    private InnerVertex<V> validate(Vertex<V> vertex) {
        if (vertex != null) {
            return (InnerVertex<V>) vertex;
        }
        throw new IllegalArgumentException("vertex is invalid");
    }

    private InnerEdge<E> validate(Edge<E> edge) {
        if (edge != null) {
            return (InnerEdge<E>) edge;
        }
        throw new IllegalArgumentException("edge is invalid");
    }
}

