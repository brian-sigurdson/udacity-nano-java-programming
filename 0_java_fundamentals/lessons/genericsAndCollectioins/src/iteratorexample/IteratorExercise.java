package iteratorexample;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IteratorExercise {
    public static void main(String[] args) {
        List<String> names = new LinkedList<>();
        names.add("mike");
        names.add("bob");
        names.add("alice");

        Iterator<String> iterator = names.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
