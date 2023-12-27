package com.berkemaktav.flightsearchapi.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SearchFlight(
        @NotNull
        String departureCity,
        @NotNull
        String arrivalCity,
        @NotNull
        LocalDate departureDate
) {
}
