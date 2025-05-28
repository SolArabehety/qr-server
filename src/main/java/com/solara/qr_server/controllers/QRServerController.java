package com.solara.qr_server.controllers;


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

    @GetMapping
    public String generateSeed() {
        return "test";
    }
}