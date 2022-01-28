package lesson_02.exer_03;

public final class Main {
    public static void main(String[] args) {
        BinaryOperation add = (a, b) -> a + b;
        System.out.println(add.apply(1,3));
        assert 5 == add.apply(2, 3);
    }
}
