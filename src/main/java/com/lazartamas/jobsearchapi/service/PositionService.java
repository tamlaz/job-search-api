package com.lazartamas.jobsearchapi.service;

import com.lazartamas.jobsearchapi.domain.Position;
import com.lazartamas.jobsearchapi.dto.incoming.PositionFormData;
import com.lazartamas.jobsearchapi.repository.PositionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PositionService {

    private final PositionRepository positionRepository;
    private final String BASE_URL = "http://localhost:8080/positions";

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public String createPosition(PositionFormData formData) {
        Position positionToSave = new Position(formData);
        positionRepository.save(positionToSave);
        positionToSave.setUrl(generateUrl(positionToSave.getId()));
        return positionToSave.getUrl();
    }

    protected String generateUrl(Long id) {
        return BASE_URL + "/" + id;
    }
}
