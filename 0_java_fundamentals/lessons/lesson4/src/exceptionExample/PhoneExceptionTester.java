package exceptionExample;

public class PhoneExceptionTester {
    public static void main(String[] args) {
        String[] numbers = new String[] {"123-456", null, "234-444", "234-444"};

        for(String number: numbers) {
            try {
                System.out.println(new Phone("iPhone", number));
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getLocalizedMessage());
            } 
        }
    }
}
