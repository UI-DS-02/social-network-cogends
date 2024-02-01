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

    public void setId(int id) {
        this.id = id;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUniversityLocation() {
        return universityLocation;
    }

    public void setUniversityLocation(String universityLocation) {
        this.universityLocation = universityLocation;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Set<String> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<String> specialties) {
        this.specialties = specialties;
    }

    public Map<Integer, Person> getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Map<Integer, Person> connectionId) {
        this.connectionId = connectionId;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

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
