package connection;
        import GraphImplemention.Edge;
        import GraphImplemention.Graph;
        import GraphImplemention.Vertex;
        import org.json.simple.JSONArray;
        import org.json.simple.JSONObject;
        import org.json.simple.parser.JSONParser;
        import org.json.simple.parser.ParseException;

        import java.io.FileReader;
        import java.io.IOException;
        import java.util.*;
public class Controller {
    public static Map<Integer,Person> insertConnection(ArrayList<Integer>new_connections,Person person){
        Map<Integer,Person> connections=new HashMap<>();
        for (Integer str :new_connections){
            Vertex<Person> person0= Model.graph.insertVertex(person);
            Vertex<Person> person1= Model.graph.insertVertex(new Person(str));
            Model.graph.insertEdge(person0,person1,0);
            connections.put(str,person0.getElement());
        }
        return connections;
    }
    public static ArrayList<Point_person> getSimilar(int id ,Scale scale){
        ArrayList<Point_person> samPerson = new ArrayList<>();
        BFS(Model.graph,Model.graph.getVertex(new Person(id)),new HashSet<>(),samPerson,scale);
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
    private static <V, E> void BFS(Graph<V, E> graph, Vertex<V> s, Set<Vertex<V>> known,ArrayList<Point_person> samPerson,Scale scale) {
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
                        checkConnections((Person) s.getElement(), (Person) v.getElement(),counter,samPerson,scale);
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
    private static <V, E> void checkConnections(Person s,Person v,int counter,ArrayList<Point_person> samPerson,Scale scale) {
        int point = calculate(s,v,counter,scale);
        Point_person pointPerson = new Point_person(point,v);
        samPerson.add(pointPerson);
        Comparator<Point_person> comparator = new Comparator<Point_person>() {
            @Override
            public int compare(Point_person o1, Point_person o2) {
                if(o1.getPoint()==o2.getPoint())
                    return 0;
                if (o1.getPoint()>o2.getPoint())
                    return 1;
                else return -1;
            }
        };
        samPerson.sort(comparator);
        if (samPerson.size()>20){
            samPerson.remove(0);
        }
    }
    private static int calculate(Person s,Person v,int counter,Scale scale){
        int point=0;
        int same_skills=0;
        for( String str:s.getSkills()){
            if (v.getSkills()!=null){
                if(v.getSkills().contains(str)){
                    same_skills++;
                }
            }
        }
       point+=scale.getScaleOfSimilarSkill()*same_skills;

        if (s.getWorkplace()!=null &&v.getWorkplace()!=null){
            if(v.getWorkplace().equalsIgnoreCase(s.getWorkplace())){
                point+=scale.getScaleOfWorkPlace();
            }
        }
        if (v.getField()!=null &&s.getField()!=null){
            if(v.getField().equalsIgnoreCase(s.getField())){
                point+=scale.getScaleOfField();
            }
        }
        if (v.getUniversityLocation()!=null &&s.getUniversityLocation()!=null){
            if(v.getUniversityLocation().equalsIgnoreCase(s.getUniversityLocation())){
                point+=scale.getScaleOfUniversity();
            }
        }
        point+=(6-counter)*scale.getDegree();
        return point;
    }
    private static <V,E>int DFSComplete(Graph<V,E> g){
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
    public static void readFile(){
        JSONParser parser = new JSONParser();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader("users.json"));
            for (Object o : a) {

                JSONObject person = (JSONObject) o;

                String id = (String) person.get("id");

                String name = (String) person.get("name");

                String dateOfBirth = (String) person.get("dateOfBirth");

                String universityLocation = (String) person.get("universityLocation");

                String field = (String) person.get("field");

                String workplace = (String) person.get("workplace");
                Set<String> set=new HashSet<>();
                JSONArray specialties = (JSONArray) person.get("specialties");
                for (Object c : specialties) {
                    set.add((String)c);
                }
                ArrayList<Integer> arrayList=new ArrayList<>();
                JSONArray connectionId = (JSONArray) person.get("connectionId");
                for (Object c : connectionId) {
                    arrayList.add(Integer.parseInt((String) c));
                }
                insertPerson(Integer.parseInt(id),name,dateOfBirth,universityLocation,field,workplace, set,arrayList);
//                Model.graph.insertVertex(person1);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
