package com.berkemaktav.flightsearchapi.validation;

import com.berkemaktav.flightsearchapi.model.Airport;
import com.berkemaktav.flightsearchapi.repository.AirportRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class UniqueCityValidator implements ConstraintValidator<UniqueCity, String> {
    @Autowired
    AirportRepository airportRepository;

    @Override
    public boolean isValid(String cityName, ConstraintValidatorContext constraintValidatorContext) {
        Airport airport = airportRepository.findByCityName(cityName).orElse(null);
        return Objects.isNull(airport);
    }
}
