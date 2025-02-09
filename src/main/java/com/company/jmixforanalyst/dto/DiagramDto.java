package com.company.jmixforanalyst.dto;

import java.time.LocalDateTime;
import java.util.List;

public record DiagramDto(
        String id,
        LocalDateTime createdOn,
        LocalDateTime updatedOn,
        String name,
        String status,
        String authorUsername,
        String updatedBy,
        boolean isPublic,
        int versionNumber,
        String description,
        String teamName,
        String type,
        List<TagDto> tags
) {}

