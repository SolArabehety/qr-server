package com.solara.qr_server.services;

import com.solara.qr_server.model.Seed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SeedServiceTest {
    private static final int EXPIRATION_MINUTES = 10;
    private static final String MOCK_SEED_VALUE = "mocked-seed-123";

    private SeedGenerator seedGenerator;
    private SeedService seedService;

    @BeforeEach
    void setUp() {
        seedGenerator = mock(SeedGenerator.class);
        seedService = new SeedService(EXPIRATION_MINUTES, seedGenerator);
    }

    @Test
    void generateSeed_shouldReturnSeedWithExpectedValueAndExpiration() {
        when(seedGenerator.generateValue()).thenReturn(MOCK_SEED_VALUE);
        Instant beforeCall = Instant.now();

        Seed seed = seedService.generateSeed();
        Instant afterCall = Instant.now();

        assertNotNull(seed);
        assertEquals(MOCK_SEED_VALUE, seed.getSeed());
        assertNotNull(seed.getExpiresAt());
        assertTrue(seed.getExpiresAt().isAfter(beforeCall.plusSeconds(599)));
        assertTrue(seed.getExpiresAt().isBefore(afterCall.plusSeconds(601)));

        verify(seedGenerator, times(1)).generateValue();
    }

    @Test
    void constructor_shouldThrowExceptionWhenExpirationIsZeroOrNegative() {
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> new SeedService(0, seedGenerator));
        assertEquals("expirationMinutes must be greater than 0", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> new SeedService(-5, seedGenerator));
        assertEquals("expirationMinutes must be greater than 0", ex2.getMessage());

        assertDoesNotThrow(() -> new SeedService(5, seedGenerator));
    }
}
