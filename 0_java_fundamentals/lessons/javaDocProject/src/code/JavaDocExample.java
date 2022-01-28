package code;

/**
 * This is the first JavaDoc.
 * @author Brian Sigurdson
 */
public class JavaDocExample {
    /**
     * A sample method to create a JavaDoc.
     * @param value - some value
     * @return - value plus one.
     */
    public static int aMethod(int value) {
        return 1 + value;
    }

    public static void main (String[] args) {
        System.out.println(JavaDocExample.aMethod(args.length));
    }
}
