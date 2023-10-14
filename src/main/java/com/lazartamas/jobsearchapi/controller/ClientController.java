package com.lazartamas.jobsearchapi.controller;

import com.lazartamas.jobsearchapi.dto.incoming.ClientRegistrationFormData;
import com.lazartamas.jobsearchapi.service.ClientService;
import com.lazartamas.jobsearchapi.validator.ClientRegistrationFormDataValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientRegistrationFormDataValidator formDataValidator;

    public ClientController(ClientService clientService, ClientRegistrationFormDataValidator formDataValidator) {
        this.clientService = clientService;
        this.formDataValidator = formDataValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(formDataValidator);
    }

    @PostMapping("register")
    public ResponseEntity<String> registerClient(@RequestBody @Validated ClientRegistrationFormData registrationFormData) {
        String apiKey = clientService.registerClient(registrationFormData);
        return ResponseEntity.status(HttpStatus.CREATED)
               .header("apikey", apiKey)
               .body("Successful registration, find your API key in the response header");
    }
}
