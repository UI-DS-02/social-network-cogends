package GraphImplemention;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AdjacencyMatrixGraph<V extends Comparable<V>,E> implements Graph<V,E> {
    class InnerVertex<V> implements Vertex<V> {
        private V element;
        private Position<Vertex<V>> position;
        private Map<Vertex<V>, Edge<E>> outgoing, incoming;
        private int index;

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

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
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

    // ATTRIBUTES:
    private Edge<E>[][] square;
    private Vertex<V>[] vertices;
    private boolean isDirected;
    private int numOfEdges;
    private int numOfVertices=0;
    private int maxNumVertices=0;

    // CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges

    public AdjacencyMatrixGraph(int maxNumVertices, boolean directed) {

        //TO-DO
        this.vertices=new Vertex[maxNumVertices];
        this.square = new Edge[maxNumVertices][maxNumVertices];
//        E[] e = (E[]) Array.newInstance(sample.getClass(),maxNumVertices);
//        this.square = (E[][]) Array.newInstance(e.getClass(),maxNumVertices);
//        for (int i =0 ; i<maxNumVertices ; i++){
//            this.square[i] = (E[]) Array.newInstance(sample.getClass(),maxNumVertices);
//        }

        for (int row = 0; row < square.length; row++) {
            for (int col = 0; col < square.length; col++) {
                square[row][col] = null;
            }
        }
        this.isDirected = directed;
        this.numOfEdges = 0;
        this.maxNumVertices=maxNumVertices;
    }


    // 1. IMPLEMENTATION METHOD numVertices:

    @Override
    public int numVertices() {
        return numOfVertices;
    }
    // 2. IMPLEMENTATION METHOD vertices:

    @Override
    public Iterable<Vertex<V>> vertices() {
        return new Iterable<Vertex<V>>() {
            @Override
            public Iterator<Vertex<V>> iterator() {
                return new Iterator<Vertex<V>>() {
                    int index =0;
                    @Override
                    public boolean hasNext() {
                        return index<numOfVertices;
                    }

                    @Override
                    public Vertex<V> next() {
                        return vertices[index++];
                    }
                };
            }
        };
    }

    // 3. IMPLEMENTATION METHOD numEdges:
    @Override
    public int numEdges() {
        //TO-DO
        return numOfEdges;
    }

    @Override
    public Iterable<Edge<E>> edges() {
        Edge[] edges = new InnerEdge[numOfEdges];
        int index=0;
        for (int i =0 ; i< numOfVertices ; i++){
            for (int j =0 ; j< numOfVertices ; j++){
                if (this.square[i][j]!=null) {
                    edges[index++]=square[i][j];
                }
            }
        }
     return new Iterable<Edge<E>>() {
         @Override
         public Iterator<Edge<E>> iterator() {
             return new Iterator<Edge<E>>() {
                 int indexE=0;
                 @Override
                 public boolean hasNext() {
                     return indexE<numOfEdges;
                 }

                 @Override
                 public Edge<E> next() {
                     return edges[indexE++];
                 }
             };
         }
     };
    }

    @Override
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
        for (Vertex<V> vertex0 : vertices()) {
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

    public Edge<E> insertEdge(V uElement, V vElement, E element) {
        InnerVertex<V> u = validate(getVertex(uElement));
        InnerVertex<V> v = validate(getVertex(vElement));
        if(square.length <= u.getIndex() || square.length <= v.getIndex() ||
                0 >= u.getIndex() || 0 >= v.getIndex() ) {
            return null;
        }
        else if(isDirected) {
            InnerEdge<E> edge = new InnerEdge<>(u,v,element);
            if(square[u.getIndex()][v.getIndex()] == null) {
                square[u.getIndex()][v.getIndex()] =edge;
                numOfEdges++;
            }
            else {
                square[u.getIndex()][v.getIndex()] = edge;
            }
            u.getOutgoing().put(v, edge);
            v.getIncoming().put(u, edge);
            return edge;
        }
        else {
            InnerEdge<E> edge = new InnerEdge<>(u,v,element);
            InnerEdge<E> edgeRe = new InnerEdge<>(v,u,element);
            if(square[u.getIndex()][v.getIndex()] == null) {
                square[u.getIndex()][v.getIndex()] = edge;
                square[v.getIndex()][u.getIndex()] = edgeRe;
                numOfEdges++;
            }
            else {
                square[u.getIndex()][v.getIndex()] = edge;
                square[v.getIndex()][u.getIndex()] = edgeRe;
            }
            u.getOutgoing().put(v, edge);
            v.getIncoming().put(u, edge);
            return edge;
        }
    }

    public Edge<E> insertEdge(Vertex<V> uVertex, Vertex<V> vVertex, E element) {
        InnerVertex<V> u = validate(uVertex);
        InnerVertex<V> v = validate(vVertex);
        if(square.length <= u.getIndex() || square.length <= v.getIndex() ||
                0 > u.getIndex() || 0 > v.getIndex() ) {
            return null;
        }
        else if(isDirected) {
            InnerEdge<E> edge = new InnerEdge<>(u,v,element);
            if(square[u.getIndex()][v.getIndex()] == null) {
                square[u.getIndex()][v.getIndex()] =edge;
                numOfEdges++;
            }
            else {
                square[u.getIndex()][v.getIndex()] = edge;
            }
            u.getOutgoing().put(v, edge);
            v.getIncoming().put(u, edge);
            return edge;
        }
        else {
            InnerEdge<E> edge = new InnerEdge<>(u,v,element);
            InnerEdge<E> edgeRe = new InnerEdge<>(v,u,element);
            if(square[u.getIndex()][v.getIndex()] == null) {
                square[u.getIndex()][v.getIndex()] = edge;
                square[v.getIndex()][u.getIndex()] = edgeRe;
                numOfEdges++;
            }
            else {
                square[u.getIndex()][v.getIndex()] = edge;
                square[v.getIndex()][u.getIndex()] = edgeRe;
            }
            u.getOutgoing().put(v, edge);
            v.getIncoming().put(u, edge);
            return edge;
        }
    }
    public void removeEdge(Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        Vertex<V>[] endPoints = edge.getEndPoints();
        InnerVertex<V> u = validate(endPoints[0]);
        InnerVertex<V> v = validate(endPoints[1]);
        if(square.length <= u.getIndex() || square.length <= v.getIndex() ||
                0 >= u.getIndex() || 0 >= v.getIndex() ){
        }else if (square[u.getIndex()][v.getIndex()] != null){

        }
        else if(isDirected) {
            square[u.getIndex()][v.getIndex()] = null;
            numOfEdges--;
            u.outgoing.remove(edge);
            v.incoming.remove(edge);
        }
        else {
            square[u.getIndex()][v.getIndex()] = null;
            square[v.getIndex()][u.getIndex()] = null;
            numOfEdges--;
            u.outgoing.remove(edge);
            v.incoming.remove(edge);
        }
    }

    public Vertex<V> insertVertex(V element) {
        Vertex<V> v =getVertex(element);
        if (v==null){
            InnerVertex<V> vertex = new InnerVertex<>(element, isDirected);
            if (numOfVertices<maxNumVertices){
                vertex.setIndex(numOfVertices);
                vertices[numOfVertices++]=vertex;
            }else {
                maxNumVertices*=2;
                Vertex[] vertices1=new Vertex[maxNumVertices];
                Edge[][] edges1 = new Edge[maxNumVertices][maxNumVertices];
                for (int row = 0; row < numOfVertices; row++) {
                    for (int col = 0; col < numOfVertices; col++) {
                        edges1[row][col] = square[row][col];
                    }
                }
                for (int row = 0; row < numOfVertices; row++) {
                    vertices1[row] = vertices[row];
                }
                this.vertices=vertices1;
                this.square = edges1;
                vertex.setIndex(numOfVertices);
                vertices[numOfVertices++]=vertex;
            }
            return vertex;
        }
        return v;
    }
    @Override
    public void removeVertex(Vertex<V> v) {
        InnerVertex<V> vertex = validate(getVertex(v.getElement()));
        for (int row = vertex.getIndex(); row < numOfVertices-1; row++) {
            vertices[row] = vertices[row+1];
        }
        vertices[numOfVertices-1]=null;
        for (int row = vertex.getIndex(); row < numOfVertices-1; row++) {
            square[row][vertex.getIndex()] = null;
        }
        for (int row = vertex.getIndex(); row < numOfVertices-1; row++) {
            for (int col =  vertex.getIndex(); col < numOfVertices-1; col++) {
                square[row][col] = square[row][col+1];
            }
        }
        for (int row = vertex.getIndex(); row < numOfVertices-1; row++) {
            square[row][numOfVertices-1] = null;
        }
        for (int col =  vertex.getIndex(); col < numOfVertices-1; col++) {
            square[numOfVertices-1][vertex.getIndex()] = null;
        }
        for (int col =  vertex.getIndex(); col < numOfVertices-1; col++) {
            for (int row = vertex.getIndex(); row < numOfVertices-1; row++) {
                square[row][col] = square[row+1][col];
            }
        }
        for (int col =  vertex.getIndex(); col < numOfVertices-1; col++) {
            square[numOfVertices-1][col] = null;
        }
        InnerVertex<V> vertex0 = validate(v);
        for (Edge<E> edge : vertex0.getOutgoing().values())
            removeEdge(edge);
        for (Edge<E> edge : vertex0.getIncoming().values())
            removeEdge(edge);
    }
    public void removeVertex(V v) {
        InnerVertex<V> vertex = validate(getVertex(v));
        for (int row = vertex.getIndex(); row < numOfVertices-1; row++) {
            vertices[row] = vertices[row+1];
        }
        for (int row = vertex.getIndex(); row < numOfVertices-1; row++) {
            for (int col =  vertex.getIndex(); col < numOfVertices-1; col++) {
                square[row][col] = square[row][col+1];
            }
        }
        for (int col =  vertex.getIndex(); col < numOfVertices-1; col++) {
            for (int row = vertex.getIndex(); row < numOfVertices-1; row++) {
                square[row][col] = square[row+1][col];
            }
        }
        for (Edge<E> edge : vertex.getOutgoing().values())
            removeEdge(edge);
        for (Edge<E> edge : vertex.getIncoming().values())
            removeEdge(edge);
    }
}