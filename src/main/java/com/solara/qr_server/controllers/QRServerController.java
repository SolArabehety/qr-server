package com.solara.qr_server.controllers;


import com.solara.qr_server.model.Seed;
import com.solara.qr_server.services.SeedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that handles QR seed-related endpoints.
 * <p>g
 * Exposes HTTP endpoints for generating seeds to be used in QR codes.
 */
@RestController
@RequestMapping("/seed")
public class QRServerController {
    private final SeedService seedService;

    public QRServerController(SeedService seedService) {
        this.seedService = seedService;
    }
    @GetMapping
    public Seed generateSeed() {
        return seedService.generateSeed();
    }
}