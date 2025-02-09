package com.company.jmixforanalyst.service;

import com.company.jmixforanalyst.dto.DiagramDto;
import com.company.jmixforanalyst.dto.DiagramListResponse;
import com.company.jmixforanalyst.dto.DiagramUploadResponse;
import com.company.jmixforanalyst.entity.StormDiagramDto;
import io.jmix.core.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Component("jal_StormService")
public class StormService {

    private static final Logger log = LoggerFactory.getLogger(StormService.class);
    private List<StormDiagramDto> stormDiagramDtos;

    public StormService() {
        this.stormDiagramDtos = new ArrayList<>();
    }

    @Autowired
    private Metadata metadata;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${stormbpmn.client.base-url:https://stormbpmn.com}")
    private String stormBaseUrl;

    @Value("${stormbpmn.client.api-key:hxkov11nhu14vmmv}")
    private String stormApiKey;

    public void setStormDiagramDtos(List<StormDiagramDto> stormDiagramDtos) {
        this.stormDiagramDtos = stormDiagramDtos;
    }

    public List<StormDiagramDto> getStormDiagramDtos() {
        return stormDiagramDtos;
    }


    public DiagramListResponse getDiagrams(int page) {
        String apiUrl = stormBaseUrl + "/public-api/v1/get-diagram-list";

        // Build URL with query parameter
        String urlWithParams = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("page", page)
                .toUriString();

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", stormApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Make GET request
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<DiagramListResponse> response = restTemplate.exchange(
                urlWithParams, HttpMethod.GET, requestEntity, DiagramListResponse.class
        );
        HttpStatusCode statusCode = response.getStatusCode();
        log.info("Storm response: {}", statusCode.value());
        return response.getBody();
    }



    public DiagramUploadResponse uploadDiagrams(List<StormDiagramDto> diagrams) {
        String apiUrl = stormBaseUrl + "/public-api/v1/upload-diagrams";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Api-Key", stormApiKey);

        HttpEntity<List<StormDiagramDto>> requestEntity = new HttpEntity<>(diagrams, headers);

        ResponseEntity<DiagramUploadResponse> response = restTemplate.exchange(
                apiUrl, HttpMethod.POST, requestEntity, DiagramUploadResponse.class
        );

        return response.getBody();
    }

    public StormDiagramDto convertToStormDiagramDto(DiagramDto diagramDto) {

        StormDiagramDto stormDiagramDto = metadata.create(StormDiagramDto.class);
        stormDiagramDto.setId(diagramDto.id());
        stormDiagramDto.setName(diagramDto.name());
        stormDiagramDto.setAuthor(diagramDto.authorUsername());
        stormDiagramDto.setUpdatedBy(diagramDto.updatedBy());
        stormDiagramDto.setCreated(diagramDto.createdOn());
        stormDiagramDto.setUpdated(diagramDto.updatedOn());
        stormDiagramDto.setSource(diagramDto.status());
        stormDiagramDto.setType(diagramDto.type());

        return stormDiagramDto;
    }
}