package lesson_02.exer_10;

import java.util.HashMap;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class Calculator {

    HashMap<String, BinaryOperator<Integer>> operators = new HashMap<>();

    public void registerOperation(String symbol, BinaryOperator<Integer> operator) {
        operators.put(symbol.strip(), operator);
    }

    public Integer calculate(int a, String symbol, int b) {
        return operators.get(symbol).apply(a,b);
    }

}
