package com.solara.qr_server.model;


import java.time.Instant;

/**
 * Represents a QR code seed along with its expiration timestamp.
 * <p>
 * This class is used as a data transfer object to provide the
 * generated seed and its expiration date in API responses.
 */
public class Seed {
    private String seed;
    private Instant expiresAt;

    public Seed(String seed, Instant expiresAt) {
        this.seed = seed;
        this.expiresAt = expiresAt;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
}
