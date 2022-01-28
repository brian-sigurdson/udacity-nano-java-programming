package lesson_04.exer_14;

import java.io.BufferedReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public final class MergeShards {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: MergeShards [input folder] [output file]");
            return;
        }

        /**
         * this is his solution from a prior exercise, not mine.
         * i'm using it, because it is more in line with this intended exercise.
         */

        List<Path> inputs = Files.walk(Path.of(args[0]), 1).skip(1).collect(Collectors.toList());
        // TODO: Get rid of this List<BufferedReader> and use a MultiFileReader below instead.
//        List<BufferedReader> readers = new ArrayList<>(inputs.size());
        Path outputPath = Path.of(args[1]);

        // TODO: Replace this try-finally with a try-with-resources. The "try" statement should create
        //       a MultiFileReader that is used in the "try" block to read from the files.
        try (
            MultiFileReader multiFileReader = new MultiFileReader(inputs);
            Writer writer = Files.newBufferedWriter(outputPath);
        )
        {
            PriorityQueue<WordEntry> words = new PriorityQueue<>();

            List<BufferedReader> bufferedReaders = multiFileReader.getReaders();
            for (BufferedReader bufferedReader: bufferedReaders) {
                String word = bufferedReader.readLine();
                if (word != null) {
                    words.add(new WordEntry(word, bufferedReader));
                }
            }

            while (!words.isEmpty()) {
                WordEntry entry = words.poll();
                writer.write(entry.word);
                writer.write(System.lineSeparator());
                String word = entry.reader.readLine();
                if (word != null) {
                    words.add(new WordEntry(word, entry.reader));
                }
            }
        }

//        try {
//            for (Path input : inputs) {
//                readers.add(Files.newBufferedReader(input));
//            }
//
//            PriorityQueue<WordEntry> words = new PriorityQueue<>();
//            for (BufferedReader reader : readers) {
//                String word = reader.readLine();
//                if (word != null) {
//                    words.add(new WordEntry(word, reader));
//                }
//            }
//
//            try (Writer writer = Files.newBufferedWriter(outputPath)) {
//                while (!words.isEmpty()) {
//                    WordEntry entry = words.poll();
//                    writer.write(entry.word);
//                    writer.write(System.lineSeparator());
//                    String word = entry.reader.readLine();
//                    if (word != null) {
//                        words.add(new WordEntry(word, entry.reader));
//                    }
//                }
//            }
//        } finally {
//            // TODO: If you are correctly using try-with-resources, this "finally" block is no longer
//            //       necessary. Remove it!
//            for (BufferedReader reader : readers) {
//                try {
//                    reader.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    private static final class WordEntry implements Comparable<WordEntry> {
        private final String word;
        private final BufferedReader reader;

        private WordEntry(String word, BufferedReader reader) {
            this.word = Objects.requireNonNull(word);
            this.reader = Objects.requireNonNull(reader);
        }

        @Override
        public int compareTo(WordEntry other) {
            return word.compareTo(other.word);
        }
    }
}



