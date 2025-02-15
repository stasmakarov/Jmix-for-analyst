package com.company.jmixforanalyst.dto;

import java.util.List;

public record ProcessDto(
        String id,     // External process ID
        String xml,    // BPMN XML content (escaped in JSON)
        String name,   // Process name
        String source  // Source system
) {}

