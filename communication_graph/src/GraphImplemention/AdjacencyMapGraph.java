package GraphImplemention;

import java.util.HashMap;
import java.util.Map;
public class AdjacencyMapGraph<V extends Comparable<V>, E> implements Graph<V, E> {
    class InnerVertex<V> implements Vertex<V> {
        private V element;
        private Position<Vertex<V>> position;
        private Map<Vertex<V>, Edge<E>> outgoing, incoming;

        public InnerVertex(V element, boolean graphIsDirected) {
            this.element = element;
            outgoing = new HashMap<>();
            if (graphIsDirected)
                incoming = new HashMap<>();
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

        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }

        public Map<Vertex<V>, Edge<E>> getIncoming() {
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

    public AdjacencyMapGraph(boolean isDirected) {
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

    public int outDegree(Vertex<V> vertex) {
        InnerVertex<V> vertex1 = validate(vertex);
        return vertex1.getOutgoing().size();
    }

    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        InnerVertex<V> vertex1 = validate(vertex);
        return vertex1.getOutgoing().values();
    }

    public int inDegree(Vertex<V> vertex) {
        InnerVertex<V> vertex1 = validate(vertex);
        return vertex1.getIncoming().size();
    }

    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        InnerVertex<V> vertex1 = validate(vertex);
        return vertex1.getIncoming().values();
    }

    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
        InnerVertex<V> origin = validate(u);
        return origin.getOutgoing().get(v);
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
        return edge.getEndPoints();
    }

    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        Vertex<V>[] endpoints = edge.getEndPoints();
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
                edge.setPosition(edges.addLast(edge));
                InnerVertex<V> origin = validate(u);
                InnerVertex<V> dest = validate(v);
                origin.getOutgoing().put(v, edge);
                dest.getIncoming().put(u, edge);
                return edge;
            } else throw new IllegalArgumentException("edge from u to v exists");
        } else throw new IllegalArgumentException("vertex is invalid");

    }

    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) {
        if (getEdge(u, v) == null) {
            InnerEdge<E> edge = new InnerEdge<>(u, v, element);
            edge.setPosition(edges.addLast(edge));
            InnerVertex<V> origin = validate(u);
            InnerVertex<V> dest = validate(v);
            origin.getOutgoing().put(v, edge);
            dest.getIncoming().put(u, edge);
            return edge;
        } else throw new IllegalArgumentException("edge from u to v exists");
    }

    public void removeVertex(Vertex<V> v) {
        InnerVertex<V> vertex = validate(v);
        for (Edge<E> edge : vertex.getOutgoing().values())
            removeEdgeV(edge);
        for (Edge<E> edge : vertex.getIncoming().values())
            removeEdgeV(edge);
        vertices.remove(vertex.getPosition());
    }

    private void removeEdgeV(Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        edges.remove(edge.getPosition());
    }

    public void removeEdge(Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        Vertex<V>[] endPoints = edge.getEndPoints();
        InnerVertex<V> vertex = validate(endPoints[0]);
        for (Edge<E> edge0 : vertex.getOutgoing().values())
            removeEdgeV(edge0);
        for (Edge<E> edge0 : vertex.getIncoming().values())
            removeEdgeV(edge0);
        InnerVertex<V> vertex2 = validate(endPoints[1]);
        for (Edge<E> edge0 : vertex2.getOutgoing().values())
            removeEdgeV(edge0);
        for (Edge<E> edge0 : vertex2.getIncoming().values())
            removeEdgeV(edge0);
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
