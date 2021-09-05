package mapsexample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapExercise {
    public static void main(String[] args) {
        Map<String, Person> mapOfPeople = new HashMap<>();

        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("bob", "bob@example.com"));
        people.add(new Person("becky", "becky@example.com"));
        people.add(new Person("cheryl", "cheryl@example.com"));

        for (Person thePerson: people) {
            mapOfPeople.put(thePerson.getEmail(), thePerson);
        }

        for (String email: mapOfPeople.keySet()) {
            System.out.println(email);
        }

        for (Person person: mapOfPeople.values()) {
            System.out.println(person);
        }

        System.out.println("Get Becky: " + mapOfPeople.get("becky@example.com"));
        System.out.println("Contains bob@example.com: " + mapOfPeople.containsKey("bob@example.com"));
    }
}
