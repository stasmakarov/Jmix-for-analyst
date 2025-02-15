package com.company.jmixforanalyst.service;

import com.company.jmixforanalyst.dto.*;
import com.company.jmixforanalyst.entity.StormData;
import com.company.jmixforanalyst.entity.StormDiagramDto;
import io.jmix.bpm.entity.ContentStorage;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.core.security.CurrentAuthentication;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Component("jal_StormService")
public class StormService {

    private static final Logger log = LoggerFactory.getLogger(StormService.class);
    private static List<StormDiagramDto> stormDiagrams = new ArrayList<>();

    private final DataManager dataManager;
    private final CurrentAuthentication currentAuthentication;
    private final Metadata metadata;
    private final RestTemplate restTemplate;

    @Value("${stormbpmn.client.base-url:https://stormbpmn.com}")
    private String stormBaseUrl;

    @Value("${stormbpmn.client.api-key:hxkov11nhu14vmmv}")
    private String stormApiKey;

    public StormService(DataManager dataManager,
                        CurrentAuthentication currentAuthentication,
                        Metadata metadata,
                        RestTemplate restTemplate) {
        this.dataManager = dataManager;
        this.currentAuthentication = currentAuthentication;
        this.metadata = metadata;
        this.restTemplate = restTemplate;
    }

    public static List<StormDiagramDto> getStormDiagrams() {
        return stormDiagrams;
    }

    public ResponseEntity<DiagramListResponse> getDiagrams(int page) {
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
        return response;
    }

    public List<StormDiagramDto> loadFromStorm() {
        stormDiagrams.clear();
        ResponseEntity<DiagramListResponse> response = getDiagrams(0);
        HttpStatusCode statusCode = response.getStatusCode();
        log.info("Storm response: {}", statusCode.value());

        if (statusCode.is2xxSuccessful()) {
            DiagramListResponse responseBody = response.getBody();
            if (responseBody != null) {
                List<DiagramDto> diagramDtos = responseBody.returnDiagrams();
                for (DiagramDto dto : diagramDtos) {
                    stormDiagrams.add(convertToStormDiagramDto(dto));
                }
            }
        }
        return stormDiagrams;
    }

    public DiagramDetailsDto getDiagramById(String diagramId) {
        String apiUrl = stormBaseUrl + "/public-api/v1/get-diagram-by-id";

        // Build URL with query parameters
        String urlWithParams = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("diagramId", diagramId)
                .toUriString();

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", stormApiKey);  // Correct API Key header
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HTTP request entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Perform GET request
        ResponseEntity<DiagramDetailsDto> response = restTemplate.exchange(
                urlWithParams, HttpMethod.GET, requestEntity, DiagramDetailsDto.class
        );

        return response.getBody();
    }



    public DiagramUploadResponse uploadDiagrams(List<ProcessDto> diagrams) {
        String apiUrl = stormBaseUrl + "/public-api/v1/upload-diagrams";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Api-Key", stormApiKey);

        HttpEntity<List<ProcessDto>> requestEntity = new HttpEntity<>(diagrams, headers);

        ResponseEntity<DiagramUploadResponse> response = restTemplate.exchange(
                apiUrl, HttpMethod.POST, requestEntity, DiagramUploadResponse.class
        );

        return response.getBody();
    }

    public StormDiagramDto convertToStormDiagramDto(DiagramDto diagramDto) {

        StormDiagramDto stormDiagramDto = metadata.create(StormDiagramDto.class);
        stormDiagramDto.setId(diagramDto.id());
        stormDiagramDto.setTeamFolderName(diagramDto.teamFolderName());
        stormDiagramDto.setVersionNumber(diagramDto.versionNumber());
        stormDiagramDto.setName(diagramDto.name());
        stormDiagramDto.setStatus(diagramDto.status());
        stormDiagramDto.setAuthor(diagramDto.authorUsername());
        stormDiagramDto.setUpdatedBy(diagramDto.updatedBy());
        stormDiagramDto.setCreated(diagramDto.createdOn());
        stormDiagramDto.setUpdated(diagramDto.updatedOn());
        stormDiagramDto.setType(diagramDto.type());
        stormDiagramDto.setDescription(diagramDto.description());

        String tags = diagramDto.tags() == null ? null :
                String.join(", ", diagramDto.tags().stream().map(TagDto::name).toList());
        stormDiagramDto.setTags(tags);

        return stormDiagramDto;
    }

    public void saveDraft(String id) {
        DiagramDetailsDto diagramDto = getDiagramById(id);
        ContentStorage draft = dataManager.create(ContentStorage.class);
        StormData stormData = dataManager.create(StormData.class);
        String username = currentAuthentication.getUser().getUsername();

        draft.setName(diagramDto.name());
        draft.setAuthor(username);
        draft.setContent(diagramDto.body().getBytes());
        draft.setType("bpmn-model-draft");
        ContentStorage saved = dataManager.save(draft);

        stormData.setParent(saved);
        stormData.setId(diagramDto.id());
        stormData.setDescription(diagramDto.description());
        stormData.setStatus(diagramDto.status());
        stormData.setTeamFolderName(diagramDto.teamFolderName());
        stormData.setVersionNumber(diagramDto.versionNumber());
        stormData.setUpdatedBy(diagramDto.updateBy());
        dataManager.save(stormData);

        log.info("Storm diagram saved as draft: {}-{}", diagramDto.id(), diagramDto.name());
    }

    public void uploadToStorm(ContentStorage contentStorage) {
        String xml = new String(contentStorage.getContent(), java.nio.charset.StandardCharsets.UTF_8);
        String escapedXml = StringEscapeUtils.escapeJson(xml);
        StormData stormData = getStormDataByParent(contentStorage);
        String id = stormData != null ? stormData.getId() : null;

        ProcessDto processDto = new ProcessDto(id, escapedXml, contentStorage.getName(), "Loaded from Jmix");
        List<ProcessDto> uploadList = List.of(processDto);

        DiagramUploadResponse diagramUploadResponse = uploadDiagrams(uploadList);
        Map<String, List<String>> errors = diagramUploadResponse.getErrors();
        for (Map.Entry<String, List<String>> entry : errors.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();

            for (String value : values) {
                log.error("Key: {} - {}", key, value);
            }
        }
    }

    public StormData getStormDataByParent(ContentStorage contentStorage) {
        try {
            return dataManager.load(StormData.class)
                    .query("select e from jal_StormData e where e.parent = :parent")
                    .parameter("parent", contentStorage)
                    .one();
        } catch (Exception e) {
            return null;
        }
    }

    public Optional<StormDiagramDto> getStormDiagramDtoById(String id) {
        return stormDiagrams.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }
}