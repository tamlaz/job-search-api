package com.lazartamas.jobsearchapi.controller;

import com.lazartamas.jobsearchapi.dto.incoming.PositionFormData;
import com.lazartamas.jobsearchapi.dto.outgoing.PositionListItem;
import com.lazartamas.jobsearchapi.service.PositionService;
import com.lazartamas.jobsearchapi.validator.PositionFormDataValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    private final PositionService positionService;
    private final PositionFormDataValidator positionFormDataValidator;

    public PositionController(PositionService positionService, PositionFormDataValidator positionFormDataValidator) {
        this.positionService = positionService;
        this.positionFormDataValidator = positionFormDataValidator;
    }


    @InitBinder()
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(positionFormDataValidator);
    }


    @PostMapping
    public ResponseEntity<String> createPosition(@RequestBody @Validated PositionFormData positionFormData) {
        String positionUrl = positionService.createPosition(positionFormData);
        return new ResponseEntity<>(positionUrl, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PositionListItem>> getJobsByKeywordAndLocation(@RequestBody @Validated PositionFormData positionFormData) {
        return new ResponseEntity<>(positionService.searchJobsByKeywordAndLocation(positionFormData), HttpStatus.OK);
    }
}
