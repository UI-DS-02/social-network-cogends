package connection;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static connection.Controller.insertConnection;

public class Person implements Comparable<Person> {
    private int id;
    private String name;
    private String family;
    private String history;
    private Map<Integer,Person>connections;
    private Set<String>skills;
    private Boolean complete;

    public Person(int id, String name, String family, String history, Set<String> skills,ArrayList<String>new_connections) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.history = history;
        this.skills = skills;
        this.complete=true;
        this.connections=insertConnection(new_connections,this);
    }
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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Map<Integer, Person> getConnections() {
        return connections;
    }
    public void setConnections(ArrayList<String>new_connections) {
        this.connections = insertConnection(new_connections,this);
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
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
