package com.company.jmixforanalyst.service;

import com.company.jmixforanalyst.dto.DiagramListResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StormServiceTest {

    @Autowired
    StormService stormService;

    @Test
    void getDiagrams() {
        DiagramListResponse diagrams = stormService.getDiagrams(1);
        int size = diagrams.size();
        Assertions.assertNotEquals(size, 0);
    }

    @Test
    void uploadDiagrams() {
    }
}