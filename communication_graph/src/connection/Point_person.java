package connection;

public class Point_person implements Comparable<Point_person>{
    private int point;
    private Person person;

    @Override
    public int compareTo(Point_person o) {
       if(point==o.point)
           return 0;
       if (point>o.point)
           return 1;
       else return -1;
    }

    public Point_person(int point, Person person) {
        this.point = point;
        this.person = person;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
