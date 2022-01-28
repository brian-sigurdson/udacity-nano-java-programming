package lesson_04.exer_07;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

public final class Main {

    public static void main(String[] args) {
        UdacisearchClient.Builder builder = new UdacisearchClient.Builder();
        builder.setName("Cat Facts").setId(17).setQuarterlyBudget(8000).setNumEmployees(5);
        builder.setContractStart(Instant.now()).setContractLength(Duration.ofDays(180));
        builder.setTimeZone(ZoneId.of("America/Los_Angeles")).setBillingAddress("555 Meowmers Ln, Riverside, CA 92501");

        UdacisearchClient client = builder.build();

//        UdacisearchClient client =
//                new UdacisearchClient(
//                        "CatFacts LLC",
//                        17,
//                        8000,
//                        5,
//                        Instant.now(),
//                        Duration.ofDays(180),
//                        ZoneId.of("America/Los_Angeles"),
//                        "555 Meowmers Ln, Riverside, CA 92501");

        System.out.println(client);
    }
}

