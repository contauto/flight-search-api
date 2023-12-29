package com.berkemaktav.flightsearchapi.controller;

import com.berkemaktav.flightsearchapi.dto.UserDto;
import com.berkemaktav.flightsearchapi.model.Auth;
import com.berkemaktav.flightsearchapi.model.User;
import com.berkemaktav.flightsearchapi.service.JwtService;
import com.berkemaktav.flightsearchapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/1.0/auth")
public class AuthController {

    private final UserService service;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final Logger log = Logger.getLogger(AuthController.class.getName());

    public AuthController(UserService service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Operation(summary = "Add user")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "User added successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))})
    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto request) {
        return ResponseEntity.ok(service.createUser(request));
    }

    @Operation(summary = "Generate token")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "Token generated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))})
    @PostMapping("/sign-in")
    public ResponseEntity<String> generateToken(@RequestBody @Valid Auth request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(jwtService.generateToken(request.username()), HttpStatus.CREATED);
        }
        log.info("invalid username " + request.username());
        throw new UsernameNotFoundException("invalid username {} " + request.username());
    }
}
