package com.solara.qr_server.services;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Component responsible for generating unique seed values.
 * <p>
 * The generated value is a UUID (version 4) converted to a string
 * with hyphens removed.
 */
@Component
public class SeedGenerator {
    public String generateValue() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}