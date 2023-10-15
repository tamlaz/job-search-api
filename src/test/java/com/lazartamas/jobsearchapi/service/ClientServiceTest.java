package com.lazartamas.jobsearchapi.service;

import com.lazartamas.jobsearchapi.domain.Client;
import com.lazartamas.jobsearchapi.dto.incoming.ClientRegistrationFormData;
import com.lazartamas.jobsearchapi.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepositoryMock;

    private ClientService clientService;

    @BeforeEach
    void init() {
        clientService = new ClientService(clientRepositoryMock);
    }


    @Test
    void testRegisterClient() {
        ClientRegistrationFormData formData = new ClientRegistrationFormData();
        formData.setEmail("test@test.com");
        formData.setName("Tester");

        when(clientRepositoryMock.save(any(Client.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());

        String apiKey = clientService.registerClient(formData);

        assertNotNull(apiKey);

        verify(clientRepositoryMock, times(1)).save(any(Client.class));
        verifyNoMoreInteractions(clientRepositoryMock);

    }
}
