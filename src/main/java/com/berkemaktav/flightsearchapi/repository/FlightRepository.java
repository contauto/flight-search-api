package com.berkemaktav.flightsearchapi.repository;

import com.berkemaktav.flightsearchapi.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>{
    @Query("SELECT f FROM Flight f WHERE f.departureAirport = ?1 AND f.arrivalAirport = ?2 AND f.departureTime = ?3")
     Iterable<Flight> getOneWayFlights(String departureCity, String arrivalCity, LocalDate departureDate);

    @Query("SELECT f FROM Flight f WHERE f.departureAirport = ?1 AND f.arrivalAirport = ?2 AND f.departureTime = ?3 OR f.departureAirport = ?2 AND f.arrivalAirport = ?1 AND f.departureTime = ?4")
    Iterable<Flight> getRoundTripFlights(String departureCity, String arrivalCity, LocalDate departureDate, LocalDate comebackDate);
}
