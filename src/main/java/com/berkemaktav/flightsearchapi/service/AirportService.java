package com.berkemaktav.flightsearchapi.service;

import com.berkemaktav.flightsearchapi.model.Airport;
import com.berkemaktav.flightsearchapi.repository.AirportRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public Airport addAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    public void deleteAirportById(Long id) {
        airportRepository.deleteById(id);
    }

    public Airport updateAirport(long id, Airport airport) {
        Airport existingAirport = airportRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        existingAirport.setCityName(airport.getCityName());
        return airportRepository.save(existingAirport);
    }
}
