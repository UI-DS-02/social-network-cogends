package GraphImplemention;

import java.util.ArrayList;

public class EdgeListGraph<V extends Comparable<V>, E> implements Graph<V, E> {
    class InnerVertex<V> implements Vertex<V> {
        private final V element;
        private Position<Vertex<V>> position;

        public InnerVertex(V element) {
            this.element = element;
        }

        @Override
        public V getElement() {
            return element;
        }

        @Override
        public int numOutgoing() {
            int degree=0;
            for (Edge<E> edge : edges) {
                InnerEdge<E> e1 = validate(edge);
                if (e1.getEndPoints()[0].equals(position.getElement()) || e1.getEndPoints()[1].equals(position.getElement())) {
                    degree++;
                }
            }
            return degree;
        }

        @Override
        public int numIncoming() {
            return numOutgoing();
        }

        public void setPosition(Position<Vertex<V>> position) {
            this.position = position;
        }

        public Position<Vertex<V>> getPosition() {
            return position;
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

    public EdgeListGraph(boolean isDirected) {
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
        int degree = 0;
        for (Edge<E> edge : edges) {
            InnerEdge<E> e1 = validate(edge);
            Vertex<V>[] endpoints = e1.getEndPoints();
            if (endpoints[0].equals(vertex)) {
                degree++;
            }
        }
        return degree;
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        ArrayList<Edge<E>>edges1=new ArrayList<>();
        for (Edge<E>edge:edges){
            InnerEdge<E> e1 = validate(edge);
            if(e1.getEndPoints()[0].equals(vertex)){
                edges1.add(e1);
            }
        }
        return edges1;
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        int degree = 0;
        for (Edge<E> edge : edges) {
            InnerEdge<E> e1 = validate(edge);
            Vertex<V>[] endpoints = e1.getEndPoints();
            if (endpoints[1].equals(vertex) ) {
                degree++;
            }
        }
        return degree;
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        ArrayList<Edge<E>>edges1=new ArrayList<>();
        for (Edge<E>edge:edges){
            InnerEdge<E> e1 = validate(edge);
            if(e1.getEndPoints()[1].equals(vertex)){
                edges1.add(e1);
            }
        }
        return edges1;
    }

    public int degree(Vertex<V> vertex) {
        int degree = 0;
        for (Edge<E> edge : edges) {
            InnerEdge<E> e1 = validate(edge);
            Vertex<V>[] endpoints = e1.getEndPoints();
            if (endpoints[0].equals(vertex) || endpoints[1].equals(vertex)) {
                degree++;
            }
        }
        return degree;
    }

    public Edge<E> getEdge(Vertex<V> v, Vertex<V> u) {
        InnerVertex<V> v1 = validate(v);
        for (Edge<E> edge : edges) {
            InnerEdge<E> e1 = validate(edge);
            Vertex<V>[] endpoints = e1.getEndPoints();
            if ((endpoints[0].equals(u) || endpoints[1].equals(u)) && (endpoints[1].equals(v) || endpoints[0].equals(v))) {
                return edge;
            }
        }
        return null;
    }

    @Override
    public Vertex<V>[] endVertices(Edge<E> inputEdge) {
        InnerEdge<E> edge = validate(inputEdge);
        return edge.getEndPoints();
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        Vertex<V>[] endpoints = edge.getEndPoints();
        if (endpoints[0] == v)
            return endpoints[1];
        else if (endpoints[1] == v)
            return endpoints[0];
        else throw new IllegalArgumentException("v is not incident to this edge ");
    }

    @Override
    public Vertex<V> insertVertex(V element) {
        InnerVertex<V> vertex = new InnerVertex<>(element);
        vertex.setPosition(vertices.addLast(vertex));
        return vertex;
    }

    public Vertex<V> getVertex(V element) {
        for (Vertex<V> vertex0 : vertices) {
            if (vertex0.getElement().compareTo(element) == 0) {
                return vertex0;
            }
        }
        return null;
    }

    @Override
    public Edge<E> insertEdge(V u, V v, E element) {
        Vertex<V> u_vertex = getVertex(u);
        Vertex<V> v_vertex = getVertex(v);
        if (u != null && v != null) {
            if (getEdge(u_vertex, v_vertex) == null) {
                InnerEdge<E> edge = new InnerEdge<>(u_vertex, v_vertex, element);
                edge.setPosition(edges.addLast(edge));

                InnerVertex<V> origin = validate(u_vertex);
                InnerVertex<V> dest = validate(v_vertex);
                edge.getEndPoints()[0] = origin;
                edge.getEndPoints()[1] = dest;
                return edge;
            } else throw new IllegalArgumentException("edge from u to v exists");
        } else throw new IllegalArgumentException("vertex is invalid");
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

    public void removeVertex(Vertex<V> v) {
        InnerVertex<V> vertex = validate(v);
        vertices.remove(vertex.getPosition());
    }

    @Override
    public void removeEdge(Edge<E> v) {
        InnerEdge<E> edge = validate(v);
        edges.remove(edge.getPosition());
    }


}