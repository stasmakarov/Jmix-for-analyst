package com.company.jmixforanalyst.dto;

import com.company.jmixforanalyst.entity.StormDiagramDto;

import java.util.List;
import java.util.Map;

public class DiagramUploadResponse {

    private Map<String, List<String>> errors; // Errors like empty name, empty definition, invalid XML
    private List<StormDiagramDto> createdDiagrams; // Successfully created diagrams
    private List<UpdatedDiagramDto> updatedDiagrams; // Successfully updated diagrams

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<String>> errors) {
        this.errors = errors;
    }

    public List<StormDiagramDto> getCreatedDiagrams() {
        return createdDiagrams;
    }

    public void setCreatedDiagrams(List<StormDiagramDto> createdDiagrams) {
        this.createdDiagrams = createdDiagrams;
    }

    public List<UpdatedDiagramDto> getUpdatedDiagrams() {
        return updatedDiagrams;
    }

    public void setUpdatedDiagrams(List<UpdatedDiagramDto> updatedDiagrams) {
        this.updatedDiagrams = updatedDiagrams;
    }
}

