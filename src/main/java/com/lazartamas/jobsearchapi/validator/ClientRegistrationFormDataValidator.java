package com.lazartamas.jobsearchapi.validator;

import com.lazartamas.jobsearchapi.domain.Client;
import com.lazartamas.jobsearchapi.dto.incoming.ClientRegistrationFormData;
import com.lazartamas.jobsearchapi.repository.ClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ClientRegistrationFormDataValidator implements Validator {

    private final ClientRepository clientRepository;

    public ClientRegistrationFormDataValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ClientRegistrationFormData.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        String emailRegex = "^[A-Za-z0-9.%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        ClientRegistrationFormData formData = (ClientRegistrationFormData) target;

        if (formData.getName().length() > 100) {
            errors.rejectValue("name", "name.is.too.long");
        }

        System.out.println(formData.getEmail().matches(emailRegex));

        if (!(formData.getEmail().matches(emailRegex))) {
            errors.rejectValue("email", "email.not.valid");
        }

        Optional<Client> client = clientRepository.findClientByEmail(formData.getEmail());

        if (client.isPresent()) {
            errors.rejectValue("email", "email.not.unique");
        }

    }
}
