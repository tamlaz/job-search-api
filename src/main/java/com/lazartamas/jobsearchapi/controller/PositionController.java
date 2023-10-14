package com.lazartamas.jobsearchapi.controller;

import com.lazartamas.jobsearchapi.dto.incoming.PositionFormData;
import com.lazartamas.jobsearchapi.service.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }


    @PostMapping
    public ResponseEntity<String> createPosition(@RequestBody @Validated PositionFormData formData) {
        String positionUrl = positionService.createPosition(formData);
        return new ResponseEntity<>(positionUrl, HttpStatus.CREATED);
    }
}
