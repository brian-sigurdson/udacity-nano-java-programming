package lesson_06.exer_04;

import java.util.ArrayList;
import java.util.List;

public final class IntPrinter {
    public static void main(String[] args) throws Exception {
//        if (args.length != 1) {
//            System.out.println("Usage: Main <number of threads>");
//            return;
//        }
//
//        int n = Integer.parseInt(args[0]);

        int n = 5;

        // TODO: Create a list of n Threads and run them all in parallel with the System.out.println
        //       statement.
        List<Thread> myThreads = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            myThreads.add()
        }


        for (int i = 0; i < n; i++) {
            System.out.println("Thread #" + Thread.currentThread().getId() + " printed " + i);
        }
    }
}

