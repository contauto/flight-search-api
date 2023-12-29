package com.berkemaktav.flightsearchapi.controller;

import com.berkemaktav.flightsearchapi.dto.AirportDto;
import com.berkemaktav.flightsearchapi.model.Airport;
import com.berkemaktav.flightsearchapi.service.AirportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/airports")
public class AirportController {
    private final AirportService airportService;
    private final Logger logger = LoggerFactory.getLogger(AirportController.class);


    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @Operation(summary = "Add airport")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "Airport added successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Airport.class)))})
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<AirportDto> addAirport(@RequestParam @Valid Airport airport) {
        logger.info("AirportController.addAirport() called");
        return new ResponseEntity<>(airportService.addAirport(airport),HttpStatus.CREATED);
    }

    @Operation(summary = "Get airport by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Airport retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Airport.class)))})
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        logger.info("AirportController.getAirportById() called");
        return ResponseEntity.ok(airportService.getAirportById(id));
    }

    @Operation(summary = "Delete airport by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Airport deleted successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Airport.class)))})
    @DeleteMapping("/{id}")
    public void deleteAirportById(@PathVariable Long id) {
        logger.info("AirportController.deleteAirportById() called");
        airportService.deleteAirportById(id);
    }

    @Operation(summary = "Update airport by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Airport updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Airport.class)))})
    @PutMapping("/{id}")
    public ResponseEntity<AirportDto> updateAirport(@PathVariable Long id, @RequestParam @Valid Airport airport) {
        logger.info("AirportController.updateAirport() called");
        return ResponseEntity.ok(airportService.updateAirport(id, airport));
    }
}
