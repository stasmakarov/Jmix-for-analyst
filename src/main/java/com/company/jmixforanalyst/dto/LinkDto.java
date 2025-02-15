package com.company.jmixforanalyst.dto;

import java.time.LocalDateTime;

public record LinkDto(
        String id,
        String name,
        String toDiagramId,
        String toItemId,
        String toItemName,
        String fromDiagramId,
        String fromItemId,
        String fromItemName,
        String description,
        String type,
        String toDiagramName,
        String fromDiagramName,
        LocalDateTime createdOn,
        LocalDateTime updatedOn,
        String updatedBy
) {}
