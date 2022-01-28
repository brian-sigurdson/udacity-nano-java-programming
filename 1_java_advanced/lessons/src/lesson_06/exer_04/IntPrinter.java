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

        int input = 10;

        // TODO: Create a list of n Threads and run them all in parallel with the System.out.println statement.
        List<Thread> myThreads = new ArrayList<>();

        for (int i = 0; i < input; i++) {
            final String itemNum = Integer.toString(i);

            myThreads.add(
                    new Thread(
                            () -> System.out.println("Thread #" + Thread.currentThread().getId() + " printed " + itemNum)));

            // part of the author's solution
            // myThreads.add(new Thread(new IntRunner(i)));
        }

        for (Thread thread: myThreads) {
            thread.start();
        }

        // I don't see the necessity of calling join.
        for (Thread thread: myThreads) {
            thread.join();
        }
    }

    /**
     * I like this solution by the course author.
     * I thought that we were expected to use a lambda, so I did.
     */
//    private static final class IntRunner implements Runnable {
//
//        private final int value;
//
//        IntRunner(int value) {
//            this.value = value;
//        }
//
//        @Override
//        public void run() {
//            System.out.println("Thread #" + Thread.currentThread().getId() + " printed " + value);
//        }
//    }
}

