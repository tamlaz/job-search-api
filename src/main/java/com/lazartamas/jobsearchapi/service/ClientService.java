package com.lazartamas.jobsearchapi.service;

import com.lazartamas.jobsearchapi.domain.Client;
import com.lazartamas.jobsearchapi.dto.incoming.ClientRegistrationFormData;
import com.lazartamas.jobsearchapi.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public String registerClient(ClientRegistrationFormData formData) {
        Client clientToSave = new Client(formData);
        clientRepository.save(clientToSave);
        return clientToSave.getApiKey();
    }


}
