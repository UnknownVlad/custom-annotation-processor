package org.example.customannotationprocessor;

import org.example.customannotationprocessor.impl.DataRandomizerRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomAnnotationProcessorApplicationTests {
    @Autowired
    private DataRandomizerRepositoryImpl dataRandomizerRepository;

    @Test
    void contextLoads() {
        System.out.println(dataRandomizerRepository.generate());
    }
}
