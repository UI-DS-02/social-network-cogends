package connection;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static connection.Controller.insert;

public class Person {
    private int id;
    private String name;
    private String family;
    private String history;
   private Map<Integer,Person>connections;
   private Set<String>skills;

    public Person(int id, String name, String family, String history, Set<String> skills,ArrayList<String>new_connections) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.history = history;
        this.skills = skills;
        insert(new_connections);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setConnections(Map<Integer, Person> connections) {
        this.connections = connections;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}
