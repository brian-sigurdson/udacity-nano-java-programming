package lesson_04.exer_17;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public final class ExpirationChecker {

    private final MetadataFetcher metadataFetcher;
    private final Clock clock;

    // TODO: Mark this constructor with @javax.inject.Inject and give it parameters for the clock and
    //       MetadataFetcher, so that those parameters can be injected by Guice.
    @javax.inject.Inject
    ExpirationChecker(Clock clock, MetadataFetcher metadataFetcher) {
//        metadataFetcher = new MetadataFetcherImpl();
        this.metadataFetcher = metadataFetcher;
        this.clock = clock;
    }

    public List<Path> getExpiredFiles(List<Path> paths, Duration expiration) {
        return paths.stream()
                .filter((path) -> isExpired(path, expiration))
                .collect(Collectors.toList());
    }

    private boolean isExpired(Path path, Duration expiration) {
        // TODO: Update this code to get the current time from a Clock instance instead of
        //       Instant.now().
//        Instant now = Instant.now();
        Instant now = clock.instant();

        try {
            Instant modifiedTime = metadataFetcher.getLastModifiedTime(path);
            return now.isAfter(modifiedTime.plus(expiration));
        } catch (IOException e) {
            return false;
        }
    }
}

