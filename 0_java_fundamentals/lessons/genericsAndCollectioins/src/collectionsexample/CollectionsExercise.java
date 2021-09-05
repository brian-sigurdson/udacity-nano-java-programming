package collectionsexample;

import java.util.LinkedList;
import java.util.List;

public class CollectionsExercise {
    public static void main(String[] args) {
        List<String> listOfItems = new LinkedList<>();

        listOfItems.add("a");
        listOfItems.add("b");
        listOfItems.add("c");

        for (String item: listOfItems) {
            System.out.println(item);
        }
    }
}
