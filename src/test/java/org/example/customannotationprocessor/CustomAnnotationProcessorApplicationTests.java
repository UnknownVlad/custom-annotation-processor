package org.example.customannotationprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.example.customannotationprocessor.components.interfaces.DataRandomizerRepository;
import org.example.customannotationprocessor.impl.DataRandomizerRepositoryImpl;
import org.example.customannotationprocessor.pojos.UserPojo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomAnnotationProcessorApplicationTests {
    @Autowired
    private DataRandomizerRepository<UserPojo> dataRandomizerRepository;

    @SneakyThrows
    @Test
    void contextLoads() {
        UserPojo generated = dataRandomizerRepository.generate();
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println("GENERATED");
        System.out.println(
                objectMapper.writeValueAsString(generated)
        );
    }
}
