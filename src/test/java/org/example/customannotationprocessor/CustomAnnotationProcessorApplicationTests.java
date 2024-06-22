package org.example.customannotationprocessor;

import org.example.customannotationprocessor.components.interfaces.DataRandomizerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomAnnotationProcessorApplicationTests {
    @Autowired
    private DataRandomizerRepository dataRandomizerRepository;

    @Test
    void contextLoads() {
        System.out.println(dataRandomizerRepository.generate());
    }
}
