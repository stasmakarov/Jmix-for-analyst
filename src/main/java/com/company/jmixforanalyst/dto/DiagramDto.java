package com.company.jmixforanalyst.dto;

import java.time.LocalDateTime;
import java.util.List;

public record DiagramDto(
        String id,
        LocalDateTime createdOn,
        LocalDateTime updatedOn,
        String teamFolderName,
        boolean isPublic,
        int versionNumber,
        String name,
        String status,
        String updatedBy,
        String authorUsername,
        String description,
        String type,
        List<TagDto> tags,
        String teamName,
        Double quality,
        String processType
) {}