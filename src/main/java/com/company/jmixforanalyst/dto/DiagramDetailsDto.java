package com.company.jmixforanalyst.dto;

import java.time.LocalDateTime;
import java.util.List;

public record DiagramDetailsDto(
        String id,
        LocalDateTime createdOn,
        LocalDateTime updatedOn,
        String updateBy,
        String userFolderName,
        String teamFolderName,
        String userFolderId,
        Integer teamFolderId,
        String body,  // BPMN XML content
        int favoritesCount,
        boolean favored,
        String name,
        String status,
        String authorUsername,
        boolean you,
        int versionNumber,
        String description,
        boolean isPublic,
        String type,
        List<TagDto> tags,
        Integer totalApprovals,
        Integer trueApprovals,
        Integer falseApprovals,
        List<LinkDto> outcommingLinks,
        List<LinkDto> incommingLinks,
        Integer autosaveIndex,
        String processType,
        String linkedDiagramId,
        String linkedDiagramName
) {}


