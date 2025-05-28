package com.solara.qr_server.services;

import com.solara.qr_server.model.Seed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Service responsible for generating unique seed values
 * with an associated expiration timestamp.
 * <p>
 * This service can be used QR generation, authentication flows,
 * temporary token generation, or any process that requires
 * short-lived unique identifiers.
 */
@Service
public class SeedService {
    private static final Logger logger = LoggerFactory.getLogger(SeedService.class);

    private final int expirationMinutes;
    private final SeedGenerator seedGenerator;

    public SeedService(@Value("${seed.expiration-minutes}") int expirationMinutes,
                       SeedGenerator seedGenerator) {

        if (expirationMinutes <= 0) {
            throw new IllegalArgumentException("expirationMinutes must be greater than 0");
        }

        this.expirationMinutes = expirationMinutes;
        this.seedGenerator = seedGenerator;
    }

    /**
     * Generates a new {@link Seed} with a unique value and a calculated expiration time.
     * <p>
     * The seed value is a UUID with hyphens removed, and the expiration timestamp is
     * calculated based on the configured expiration duration.
     *
     * @return a new {@link Seed} containing the generated value and its expiration timestamp
     */
    public Seed generateSeed() {
        String seedValue = seedGenerator.generateValue();
        Instant expiration = Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES);
        logger.debug("Generated seed: {}, expires at: {}", seedValue, expiration);
        return new Seed(seedValue, expiration);
    }

}