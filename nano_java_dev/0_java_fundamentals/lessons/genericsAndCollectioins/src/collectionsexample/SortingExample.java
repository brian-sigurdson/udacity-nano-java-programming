package collectionsexample;

import java.util.ArrayList;
import java.util.Collections;

public class SortingExample {
    public static void main(String[] args) {
        ArrayList<String> theList = new ArrayList<>();
        theList.add("a");
        theList.add("z");
        theList.add("n");

        for (String name: theList) {
            System.out.println(name);
        }

        Collections.sort(theList);
        System.out.println("---- sorted ----");

        for (String name: theList) {
            System.out.println(name);
        }
    }
}
