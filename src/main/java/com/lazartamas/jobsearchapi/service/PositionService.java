package com.lazartamas.jobsearchapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lazartamas.jobsearchapi.domain.Position;
import com.lazartamas.jobsearchapi.dto.incoming.PositionFormData;
import com.lazartamas.jobsearchapi.dto.outgoing.PositionListItem;
import com.lazartamas.jobsearchapi.repository.PositionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Transactional
public class PositionService {

    private final PositionRepository positionRepository;
    private WebClient webClient;

    @Value("${reed-jobs-url}")
    private String jobsUrl;

    @Value("${reed-jobs-apikey}")
    private String apiKey;

    private final String BASE_URL = "http://localhost:8080/positions";

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
                .baseUrl(jobsUrl)
                .filter(ExchangeFilterFunctions.basicAuthentication(apiKey, ""))
                .build();
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

    public List<PositionListItem> searchJobsByKeywordAndLocation(PositionFormData positionFormData) {
        List<PositionListItem> jobsFromDatabase = positionRepository
                .findByKeywordAndLocation(positionFormData.getJobTitle(), positionFormData.getLocation());
        List<PositionListItem> jobsFromReed = searchJobsWithReedApi(positionFormData.getJobTitle(), positionFormData.getLocation());
        jobsFromDatabase.addAll(jobsFromReed);
        return jobsFromDatabase;
    }

    protected List<PositionListItem> searchJobsWithReedApi(String keyword, String location) {
        JsonNode jobsResponse = convertJobsFromReedApi(keyword,location);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(jobsResponse.get("results"), new TypeReference<List<PositionListItem>>() {
        });

    }

    protected JsonNode convertJobsFromReedApi(String keyword, String location) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/1.0/search")
                        .queryParam("keywords", keyword)
                        .queryParam("locationName", location)
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
    }
}
