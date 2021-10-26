package lesson_02.lesson_03.exer_15;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

public final class Main {
    public static void main(String[] args) throws Exception {

//        if (ar

        UdacisearchClient client =
                new UdacisearchClient(
                        "CatFacts LLC",
                        17,
                        8000,
                        5,
                        Instant.now(),
                        Duration.ofDays(180),
                        ZoneId.of("America/Los_Angeles"),
                        "555 Meowmers Ln, Riverside, CA 92501");

//        Path outputPath = Path.of(args[0]);
        Path path = Path.of("output.bin");

        // TODO: Write the "client" variable to the "outputPath", using a ObjectOutputStream. Then,
        //       read it back and deserialize it using a ObjectInputStream.

        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(client);
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path))) {
            UdacisearchClient deserialized = (UdacisearchClient) inputStream.readObject();

            System.out.println(deserialized);
        }
    }
}

