package connection;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static connection.Controller.insertConnection;

public class Person implements Comparable<Person> {
    private int id;
    private String name;
    private String dateOfBirth;
    private String universityLocation;
    private String field;
    private String workplace;
    private Set<String> specialties;
    private Map<Integer,Person>connectionId;

    public Person(int id, String name, String history,String universityLocation,String field,String workplace, Set<String> specialties,ArrayList<Integer>connectionId) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = history;
        this.universityLocation=universityLocation;
        this.field = field;
        this.workplace=workplace;
        this.complete=true;
        this.specialties=specialties;
        this.connectionId= insertConnection(connectionId,this);
    }
    boolean complete;
    public Person(int id) {
        this.id = id;
        this.complete=false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setHistory(String history) {
        this.dateOfBirth = history;
    }

    public Map<Integer, Person> getConnections() {
        return connectionId;
    }
    public void setConnections(ArrayList<Integer> new_connections) {
        this.connectionId = insertConnection(new_connections,this);
    }

    public Set<String> getSkills() {
        return specialties;
    }

    public void setSkills(Set<String> skills) {
        this.specialties = skills;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    @Override
    public int compareTo(Person o) {
        if (this.id==o.id)
            return 0;
        return 1;

    }
}
