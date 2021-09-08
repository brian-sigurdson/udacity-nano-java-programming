package lesson_03.exer_08;

import java.nio.file.Files;
import java.nio.file.Path;

public final class MakeShards {
    private static final int SHARD_SIZE = 100;

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: MakeShards [input file] [output folder]");
            return;
        }

        Path input = Path.of(args[0]);
        Path outputFolder = Files.createDirectory(Path.of(args[1]));

        // TODO: Read the unsorted words from the "input" Path, line by line. Write the input words to
        //       many shard files. Each shard file should contain at most SHARD_SIZE words, in sorted
        //       order. All the words should be accounted for in the output shard files; you should not
        //       skip any words. Write the shard files in the newly created "outputFolder", using the
        //       getOutputFileName(int) method to name the individual shard files.
    }

    private static String getOutputFileName(int shardNum) {
        return String.format("shard%02d.txt", shardNum);
    }
}

