package com.berkemaktav.flightsearchapi.service;

import com.berkemaktav.flightsearchapi.dto.FlightDto;
import com.berkemaktav.flightsearchapi.dto.SearchFlight;
import com.berkemaktav.flightsearchapi.model.Flight;
import com.berkemaktav.flightsearchapi.repository.FlightRepository;
import jakarta.persistence.EntityExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final Logger logger = LoggerFactory.getLogger(FlightService.class);

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public FlightDto addFlight(Flight flight) {
        Flight saved = flightRepository.save(flight);
        logger.info("Flight saved successfully. Flight: {}", saved);
        return new FlightDto(saved.getDepartureAirport(),
                saved.getArrivalAirport(),
                saved.getDepartureTime(),
                saved.getArrivalTime(),
                saved.getPrice());
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(long id) {
        return flightRepository.findById(id).orElseThrow();
    }

    public void deleteFlightById(long id) {
        flightRepository.deleteById(id);
    }

    public FlightDto updateFlightById(long id, Flight flight) {
        Flight flightToUpdate = flightRepository.findById(id).orElseThrow(EntityExistsException::new);
        flightToUpdate.setDepartureAirport(flight.getDepartureAirport());
        flightToUpdate.setArrivalAirport(flight.getArrivalAirport());
        flightToUpdate.setDepartureTime(flight.getDepartureTime());
        flightToUpdate.setArrivalTime(flight.getArrivalTime());
        flightToUpdate.setPrice(flight.getPrice());
        Flight saved = flightRepository.save(flightToUpdate);
        logger.info("Flight updated successfully. Flight: {}", saved);
        return new FlightDto(saved.getDepartureAirport(),
                saved.getArrivalAirport(),
                saved.getDepartureTime(),
                saved.getArrivalTime(),
                saved.getPrice());
    }

    public List<Flight> getFlightsByTimeAndAirport(SearchFlight departure, SearchFlight comeback) {
        if (Objects.isNull(departure)) {
            throw new IllegalArgumentException("Departure flight cannot be null");
        } else if (Objects.isNull(comeback)) {
            return flightRepository.getOneWayFlights(departure.departureCity(), departure.arrivalCity(), departure.departureDate());
        } else {
            return flightRepository.getRoundTripFlights(departure.departureCity(), departure.arrivalCity(), departure.departureDate(), comeback.departureDate());
        }
    }
}