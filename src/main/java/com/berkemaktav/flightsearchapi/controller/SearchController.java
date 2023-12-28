package com.berkemaktav.flightsearchapi.controller;

import com.berkemaktav.flightsearchapi.dto.SearchFlight;
import com.berkemaktav.flightsearchapi.model.Flight;
import com.berkemaktav.flightsearchapi.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/search")
public class SearchController {
    private final FlightService flightService;
    private final Logger logger = LoggerFactory.getLogger(SearchController.class);

    public SearchController(FlightService flightService) {
        this.flightService = flightService;
    }



    @Operation(summary = "get flights by time and airport")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Flights retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class)))})
    @GetMapping
    public List<Flight> getFlightsByTimeAndAirport(@RequestParam() SearchFlight first,
                                                   @RequestParam(required = false) SearchFlight second) {
        logger.info("getFlightsByTimeAndAirport() called");
        return flightService.getFlightsByTimeAndAirport(first, second);
    }
}
