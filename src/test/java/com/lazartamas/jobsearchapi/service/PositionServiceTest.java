package com.lazartamas.jobsearchapi.service;

import com.lazartamas.jobsearchapi.domain.Position;
import com.lazartamas.jobsearchapi.dto.incoming.PositionFormData;
import com.lazartamas.jobsearchapi.repository.PositionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PositionServiceTest {

    @Mock
    private PositionRepository positionRepositoryMock;

    @InjectMocks
    private PositionService positionService;
    private Position firstPosition;
    private Position secondPosition;

    @BeforeEach
    void init() {
        firstPosition = new Position();
        firstPosition.setId(1L);
        firstPosition.setJobTitle("First Test Job");
        firstPosition.setLocation("Chicago");
        secondPosition = new Position();
        secondPosition.setId(2L);
        secondPosition.setJobTitle("Second Test Job");
        secondPosition.setLocation("New York");
    }

    @Test
    void testCreatePosition() {
        PositionFormData positionFormData = new PositionFormData();
        positionFormData.setJobTitle("Backend Engineer");
        positionFormData.setLocation("Boston");

        when(positionRepositoryMock.save(any(Position.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());

        String url = positionService.createPosition(positionFormData);
        assertNotNull(url);

        verify(positionRepositoryMock, times(1)).save(any(Position.class));
        verifyNoMoreInteractions(positionRepositoryMock);

    }

    @Test
    void testGenerateUrl() {
        assertNotNull(positionService.generateUrl(firstPosition.getId()));
    }
}
