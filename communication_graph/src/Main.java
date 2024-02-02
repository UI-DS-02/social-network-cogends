import connection.*;

import java.util.ArrayList;
import java.util.Scanner;
import Exception.InvalidInputException;

public class Main {
    public static void main(String[] args) {
        Controller.readFile();
        Scanner scanner = new Scanner(System.in);
        String str= scanner.nextLine();
        while (!str.equals("exit")){
            try {
                String[] strings = str.split(" ");
                ArrayList<Point_person> people;
                if(strings.length==1) {
                    people=Controller.getSimilar(Integer.parseInt(strings[0]),new Scale());
                }
                else if (strings.length==5){
                    people =Controller.getSimilar(Integer.parseInt(strings[0]),new Scale(strings[1],strings[2],strings[3],strings[4]));
                }else throw new InvalidInputException();
                for (Point_person person:people ){
                    System.out.println("id: "+person.getPerson().getId()+" "+"name: "+person.getPerson().getName());
                }
            }catch (Exception |InvalidInputException e) {
                System.out.println(e.getMessage());
            }
            str= scanner.nextLine();
        }

    }
}