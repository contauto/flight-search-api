package com.berkemaktav.flightsearchapi.dto;

import com.berkemaktav.flightsearchapi.model.Airport;

import java.time.LocalDateTime;

public record FlightDto(
        Airport departureAirport,
        Airport arrivalAirport,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        double price) {
}