package com.berkemaktav.flightsearchapi.model;

import com.berkemaktav.flightsearchapi.validation.UniqueCity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "airports")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @UniqueCity
    private String cityName;

    public Airport() {
    }

    public Airport(long id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public Airport(String cityName) {
        this.cityName = cityName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return id == airport.id && Objects.equals(cityName, airport.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cityName);
    }
}
