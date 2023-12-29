package com.berkemaktav.flightsearchapi.service;

import com.berkemaktav.flightsearchapi.dto.AirportDto;
import com.berkemaktav.flightsearchapi.model.Airport;
import com.berkemaktav.flightsearchapi.repository.AirportRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AirportService {
    private final AirportRepository airportRepository;
    private final Logger logger = LoggerFactory.getLogger(AirportService.class);

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public AirportDto addAirport(Airport airport) {
        Airport saved = airportRepository.save(airport);
        logger.info("Airport saved successfully. Airport: {}", saved);
        return new AirportDto(saved.getCityName());
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    public void deleteAirportById(Long id) {
        airportRepository.deleteById(id);
    }

    public AirportDto updateAirport(long id, Airport airport) {
        Airport existingAirport = airportRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        existingAirport.setCityName(airport.getCityName());
        Airport saved = airportRepository.save(existingAirport);
        logger.info("Airport updated successfully. Airport: {}", saved);
        return new AirportDto(saved.getCityName());
    }
}
