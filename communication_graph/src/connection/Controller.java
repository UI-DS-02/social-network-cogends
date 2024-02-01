package connection;

        import GraphImplemention.Edge;
        import GraphImplemention.Graph;
        import GraphImplemention.Vertex;

        import java.util.*;

public class Controller {
    public static Map<Integer,Person> insertConnection(ArrayList<String>new_connections,Person person){
        Map<Integer,Person> connections=new HashMap<>();
        for (String str :new_connections){
            Person person0= Model.graph.insertVertex(new Person(Integer.parseInt(str))).getElement();
            Model.graph.insertEdge(person,person0,0);
            connections.put(Integer.parseInt(str),person0);
        }
        return connections;
    }
    public static void insertPerson(int id, String name, String family, String history, Set<String> skills,ArrayList<String>new_connections){
        Person person =Model.graph.insertVertex(new Person(id,name,family,history,skills,new_connections)).getElement();
        if (!person.getComplete()){
            person.setName(name);
            person.setFamily(family);
            person.setHistory(history);
            person.setSkills(skills);
            person.setConnections(new_connections);
            person.setComplete(true);
        }
    }
    private static <V, E> int BFS(Graph<V, E> graph, Vertex<V> s, Vertex<V> g, Set<Vertex<V>> known) {
        LinkedList<Vertex<V>> level = new LinkedList<>();
        boolean found=false;
        known.add(s);
        level.addLast(s);
        int counter=0;
        while (!level.isEmpty()){
            LinkedList<Vertex<V>> nextLevel = new LinkedList<>();
            for (Vertex<V> u : level){
                for (Edge<E> e : graph.outgoingEdges(u)){
                    Vertex<V> v = graph.opposite(u,e);
                    if (!known.contains(v)){
                        known.add(v);
                        if (v.getElement()==g.getElement()){
                            found=true;
                            break;
                        }
                        nextLevel.addLast(v);
                    }
                }
            }
            counter++;
            level=nextLevel;
        }
        return !found ? -1 :counter ;
    }
    static <V,E>int DFSComplete(Graph<V,E> g){
        Set<Vertex<V>> known = new HashSet<>();
        Map<Edge<E>,Vertex<V>> forest = new HashMap<>();
        int counter=0;
        for (Vertex<V> u : g.vertices()){
            if (!known.contains(u)) {
                DFS(g,u,known,forest);
                counter++;
            }

        }
        return counter;
    }
    private static <V, E> void DFS(Graph<V, E> g, Vertex<V> u, Set<Vertex<V>> known, Map< Edge<E>,Vertex<V>> forest) {
        known.add(u);
        for(Edge<E> e : g.outgoingEdges(u)) {
            Vertex<V> v= g.opposite(u,e);
            if (!known.contains(v)){
                forest.put(e,v);
                DFS(g,v,known,forest);
            }
        }
    }
}
