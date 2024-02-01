package connection;
        import GraphImplemention.Edge;
        import GraphImplemention.Graph;
        import GraphImplemention.Vertex;
        import java.util.*;
public class Controller {
    public static Map<Integer,Person> insertConnection(ArrayList<Integer>new_connections,Person person){
        Map<Integer,Person> connections=new HashMap<>();
        for (Integer str :new_connections){
            Person person0= Model.graph.insertVertex(new Person(str)).getElement();
            Model.graph.insertEdge(person,person0,0);
            connections.put(str,person0);
        }
        return connections;
    }
    public static ArrayList<Person> getSimilar(int id ,Scale scale){
        ArrayList<Person> samPerson = new ArrayList<>();
        BFS(Model.graph,Model.graph.getVertex(new Person(id)),new HashSet<>(),samPerson);
        return samPerson;
    }
    public static void insertPerson(int id, String name, String history,String universityLocation,String field,String workplace, Set<String> specialities,ArrayList<Integer>connectionTd){
        Person person =Model.graph.insertVertex(new Person(id,name,history,universityLocation,field,workplace,specialities,connectionTd)).getElement();
        if (!person.getComplete()){
            person.setName(name);
            person.setHistory(history);
            person.setSkills(specialities);
            person.setConnections(connectionTd);
            person.setComplete(true);
        }
    }
    private static <V, E> void BFS(Graph<V, E> graph, Vertex<V> s, Set<Vertex<V>> known,ArrayList<Person> samPerson) {
        LinkedList<Vertex<V>> level = new LinkedList<>();
        known.add(s);
        level.addLast(s);
        int counter=1;
        while (!level.isEmpty()){
            LinkedList<Vertex<V>> nextLevel = new LinkedList<>();
            for (Vertex<V> u : level){
                for (Edge<E> e : graph.outgoingEdges(u)){
                    Vertex<V> v = graph.opposite(u,e);
                    if (!known.contains(v)){
                        known.add(v);
                        checkConnections((Person) s.getElement(), (Person) v.getElement(),counter,samPerson);
                        nextLevel.addLast(v);
                    }
                }
            }
            counter++;
            if (counter==6){
                break;
            }
            level=nextLevel;
        }
    }
    private static <V, E> void checkConnections(Person s,Person v,int counter,ArrayList<Person> samPerson) {

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
