package com.company.jmixforanalyst.dto;

public class UpdatedDiagramDto {
    private String stormDiagramId;
    private String sourceDiagramId;

    public String getStormDiagramId() {
        return stormDiagramId;
    }

    public void setStormDiagramId(String stormDiagramId) {
        this.stormDiagramId = stormDiagramId;
    }

    public String getSourceDiagramId() {
        return sourceDiagramId;
    }

    public void setSourceDiagramId(String sourceDiagramId) {
        this.sourceDiagramId = sourceDiagramId;
    }
}
