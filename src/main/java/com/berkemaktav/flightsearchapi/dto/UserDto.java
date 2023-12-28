package com.berkemaktav.flightsearchapi.dto;

import com.berkemaktav.flightsearchapi.model.Role;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UserDto(
        @NotNull
        String name,
        @NotNull
        String username,
        @NotNull
        String password,
        @NotNull
        Set<Role> authorities
) {
}
