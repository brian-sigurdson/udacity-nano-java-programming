package setexample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetExercise {
    public static void main(String[] args) {
        List<String> numbers = new ArrayList<>();
        numbers.add("1");
        numbers.add("2");
        numbers.add("3");
        numbers.add("3");
        numbers.add("3");

        Set<String> uniqueNumbers = new HashSet<>(numbers);

        for (String number: numbers) {
            System.out.println(number);
        }

        for (String number: uniqueNumbers) {
            System.out.println(number);
        }
    }
}
