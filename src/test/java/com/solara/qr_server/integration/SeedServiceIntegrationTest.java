package com.solara.qr_server.integration;

import com.solara.qr_server.model.Seed;
import com.solara.qr_server.services.SeedService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SeedServiceIntegrationTest {

    @Autowired
    private SeedService seedService;

    @Test
    void generateSeed_shouldReturnValidSeedWithExpirationInFuture() {
        Seed seed = seedService.generateSeed();

        assertNotNull(seed);
        assertNotNull(seed.getSeed());
        assertFalse(seed.getSeed().isEmpty());

        assertNotNull(seed.getExpiresAt());
        assertTrue(seed.getExpiresAt().isAfter(Instant.now()));
    }
}
