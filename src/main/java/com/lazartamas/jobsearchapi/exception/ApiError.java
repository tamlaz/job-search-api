package com.lazartamas.jobsearchapi.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiError {
    private String errorCode;
    private String error;
    private String details;

    public ApiError(String errorCode, String error, String details) {
        this.errorCode = errorCode;
        this.error = error;
        this.details = details;
    }
}

