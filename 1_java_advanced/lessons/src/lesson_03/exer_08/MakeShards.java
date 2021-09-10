package lesson_03.exer_08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MakeShards {
    private static final int SHARD_SIZE = 100;
    private static final List<String> shard = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: MakeShards [input file] [output folder]");
            return;
        }

        /*
        Okay, all of this actually works from the cli, but i still barely understand it.
        Take the time to watch the PS vids on file i/o and get a better understanding.
        I won't be an expert, but I won't be guessing either.

        Although
         */
        Path input = Path.of(args[0]);
        Path outputFolder = Path.of(args[1]);

        if (!Files.isDirectory(outputFolder)){
            // create the dir only if it doesn't exist
            outputFolder = Files.createDirectory(Path.of(String.valueOf(outputFolder)));
        }

        BufferedReader reader = Files.newBufferedReader(input, StandardCharsets.UTF_8);
        String line;
        int cnt = 0;
        int shard_num = 0;
        while ((line = reader.readLine()) != null) {
//            System.out.println(line);

            if (cnt++ < MakeShards.SHARD_SIZE) {
                // store the next word
                shard.add(line);
            } else {
                // time to write to the next file
                cnt = 0;
                try( BufferedWriter bufferedWriter = Files.newBufferedWriter(outputFolder, StandardCharsets.UTF_8);) {
                    Collections.sort(shard);
                    for (String val: shard) {
                        bufferedWriter.write(val + System.lineSeparator());
                    }
                }


            }
        }
        reader.close();

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

