package org.example.clientmicroservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.clientmicroservice.config.UserAuthenticationProvider;
import org.example.clientmicroservice.dto.AddAssuranceDto;
import org.example.clientmicroservice.dto.UserDto;
import org.example.clientmicroservice.dto.LoginDto;
import org.example.clientmicroservice.dto.SignUpDto;
import org.example.clientmicroservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {
    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    private final RestTemplate restTemplate;
    private final List<AddAssuranceDto> availableInsurances = new ArrayList<>();


    @PostMapping("/login")
    public ResponseEntity<ResponseMessage<UserDto>> login(@RequestBody @Valid LoginDto loginDto){
        UserDto userDto = userService.login(loginDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto));
        String successMessage = "Login successful!";
        return ResponseEntity.ok(new ResponseMessage<>(successMessage, userDto));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage<UserDto>> register(@RequestBody @Valid SignUpDto client){
        UserDto createdClient = userService.register(client);
        createdClient.setToken(userAuthenticationProvider.createToken(createdClient));
        String successMessage = "Registration successful!";
        return ResponseEntity.created(URI.create("/users" + createdClient.getCin()))
                .body(new ResponseMessage<>(successMessage, createdClient));
    }


    @GetMapping("/getInsurances")
    public ResponseEntity<List<AddAssuranceDto>> getAllAssurancesFromAdmin() {
        String adminServiceUrl = "http://localhost:8087/admin/all";
        AddAssuranceDto[] assurances = restTemplate.getForObject(adminServiceUrl, AddAssuranceDto[].class);
        List<AddAssuranceDto> assuranceList = Arrays.asList(assurances);
        return ResponseEntity.ok(assuranceList);
    }

    @GetMapping("/getInsurance/{idAssurance}")
    public ResponseEntity<AddAssuranceDto> getInsurance(@PathVariable int idAssurance) {
        String adminServiceUrl = "http://localhost:8087/admin/" + idAssurance;
        AddAssuranceDto selectedInsurance = restTemplate.getForObject(adminServiceUrl, AddAssuranceDto.class);

        return Optional.ofNullable(selectedInsurance)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/chooseInsurance/{idAssurance}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> chooseInsurance(
            @PathVariable String idAssurance,
            @RequestBody AddAssuranceDto addAssuranceDto,
            Authentication authentication) {

        // Check if the user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {

            // Simulate storing the selected insurance
            availableInsurances.add(new AddAssuranceDto(idAssurance, addAssuranceDto.getNom(),
                    addAssuranceDto.getDescription(), addAssuranceDto.getPrix()));

            // Perform additional processing or validation on the selected insurance and idAssurance
            // You can add your specific logic here

            String successMessage = "Insurance with ID " + idAssurance + " chosen successfully!";
            return ResponseEntity.ok(successMessage);
        } else {
            // User is not authenticated, return an error response
            String errorMessage = "User must be logged in to choose an insurance.";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
        }
    }




}




