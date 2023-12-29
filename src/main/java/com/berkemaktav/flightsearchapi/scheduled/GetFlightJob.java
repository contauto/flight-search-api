package com.berkemaktav.flightsearchapi.scheduled;


import com.berkemaktav.flightsearchapi.model.Flight;
import com.berkemaktav.flightsearchapi.repository.FlightRepository;
import com.berkemaktav.flightsearchapi.util.CronUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class GetFlightJob {

    private final FlightRepository flightRepository;
    @Value("${api.flight.url}")
    private String API_URL;

    public GetFlightJob(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Scheduled(cron = CronUtil.EVERY_HOUR)
    public void getFlightData() {
        List<Flight> flights = new RestTemplate().getForObject(API_URL, List.class);
        if (Objects.isNull(flights)) {
            return;
        }
        flightRepository.saveAll(flights);

    }
}
