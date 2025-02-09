package com.company.jmixforanalyst.dto;

import java.util.List;

public record DiagramListResponse(
        int totalElements,
        int page,
        int size,
        List<DiagramDto> returnDiagrams
) {}

