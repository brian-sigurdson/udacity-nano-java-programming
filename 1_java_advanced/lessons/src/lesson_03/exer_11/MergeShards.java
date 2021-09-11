package lesson_03.exer_11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
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

        List<Path> inputs = Files.walk(Path.of(args[0]), 1).skip(1).collect(Collectors.toList());
        List<BufferedReader> readers = new ArrayList<>(inputs.size());
        Path pathToOutputFile = Path.of(args[1]);

        try {
            // create one reader for each shard file
            inputs.forEach(n -> {
                try {
                    readers.add(Files.newBufferedReader(n, StandardCharsets.UTF_8));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // create a priority queue with initial capacity
            PriorityQueue<WordEntry> priorityQueue = new PriorityQueue<>(inputs.size());

            String line;
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(pathToOutputFile, StandardCharsets.UTF_8))
            {
                // iterate over the input readers and read one word from the file
                for (int i = 0; i < readers.size(); i++) {
                    line = readers.get(i).readLine();
                    // process the line
                    MergeShards.processLine(priorityQueue, line, readers.get(i));
                }

                // while the queue has elements to write
                while (!priorityQueue.isEmpty()) {
                    // remove the highest priority element from the queue
                    WordEntry wordEntry = priorityQueue.poll();
                    // write it to the output file
                    bufferedWriter.write(wordEntry.word + System.lineSeparator());
                    // use the reader associated with the word just written to read in a word from the same file
                    line = wordEntry.reader.readLine();
                    // process line
                    MergeShards.processLine(priorityQueue, line, wordEntry.reader);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } finally {
            // close the readers
            readers.forEach(n -> {
                try {
                    n.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void processLine(PriorityQueue<WordEntry> priorityQueue, String line, BufferedReader bufferedReader) {
        try {
            if (line == null) {
                // file is empty
                bufferedReader.close();
            } else {
                // add it to the queue
                priorityQueue.add(new WordEntry(line, bufferedReader));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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


