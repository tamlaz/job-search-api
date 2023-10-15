package com.lazartamas.jobsearchapi.validator;

import com.lazartamas.jobsearchapi.dto.incoming.PositionFormData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PositionFormDataValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PositionFormData.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        PositionFormData formData = (PositionFormData) target;


        if (formData.getJobTitle().length() > 50) {
            errors.rejectValue("jobTitle", "jobTitle.too.long");
        }

        if (formData.getLocation().length() > 50) {
            errors.rejectValue("location", "location.too.long");
        }

    }
}
