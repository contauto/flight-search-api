package com.berkemaktav.flightsearchapi.controller;

import com.berkemaktav.flightsearchapi.dto.FlightDto;
import com.berkemaktav.flightsearchapi.model.Flight;
import com.berkemaktav.flightsearchapi.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/flights")
public class FlightController {
    private final FlightService flightService;
    private final Logger logger = LoggerFactory.getLogger(FlightController.class);

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @Operation(summary = "get all flights")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Flights retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class)))})
    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAllFlights() {
        logger.info("getAllFlights() called");
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @Operation(summary = "add flight")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "Flight added successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class)))})
    @PostMapping
    public ResponseEntity<FlightDto> addFlight(@Valid @RequestParam Flight flight) {
        logger.info("addFlight() called");
        return new ResponseEntity<>(flightService.addFlight(flight), null, 201);
    }

    @Operation(summary = "get flight by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Flight retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class)))})
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        logger.info("getFlightById() called");
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @Operation(summary = "delete flight by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Flight deleted successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class)))})
    @DeleteMapping("/{id}")
    public void deleteFlightById(@PathVariable Long id) {
        logger.info("deleteFlightById() called");
        flightService.deleteFlightById(id);
    }

    @Operation(summary = "update flight by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Flight updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class)))})
    @PutMapping("/{id}")
    public ResponseEntity<FlightDto> updateFlightById(@PathVariable Long id, @Valid Flight flight) {
        logger.info("updateFlightById() called");
        return ResponseEntity.ok(flightService.updateFlightById(id, flight));
    }

}
